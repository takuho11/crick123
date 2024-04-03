package cn.topcheer.pms2.biz.zjk;


import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.zjk.ZjkRyjbxxUpdate;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.zjk.ZjkRegisterDao;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by XL on 2019/5/7.
 */
@Service(value="ZjkRegisterService")
public class ZjkRegisterService extends GenericService<SysUser> {

    public ZjkRegisterDao getZjkRegisterDao() {
        return (ZjkRegisterDao) this.getGenericDao();
    }
    @Autowired
    public void setZjkRegisterDao(ZjkRegisterDao zjkRegisterDao) {
        this.setGenericDao(zjkRegisterDao);
    }
    @Autowired
    private ZjkRyjbxxUpdateService zjkRyjbxxUpdateService;
    @Autowired
    ZjkRyjbxxxkflUpdateService zjkRyjbxxxkflUpdateService;
    @Autowired
    ZjkRyjbxxInfoUpdateService zjkRyjbxxInfoUpdateService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private DBHelper dbHelper;

    public JSONObject judgeIsCanChange(String sourceid,String type){
        JSONObject resObj = new JSONObject();
        if("user".equals(type)){ //个人数据仓修改单位信息，需判断专家信息是否在变更流程
            SysUser sysUser = this.sysUserService.findById(AuthUtil.getUserId());
            List<Map> zjkRyjbxxUpdates = this.dbHelper.getRows("select t.* " +
                    "from ZJK_RYJBXX_UPDATE t where t.id_number = ?",new Object[]{sysUser.getCertificateno()});

            if(zjkRyjbxxUpdates.size()>0){
                List<Map> isExists = this.dbHelper.getRows("select id from expert_temp " +
                        "where sourceid = ? and status = '审核中'",new Object[]{zjkRyjbxxUpdates.get(0).get("ID")});
                String isExist = (String) isExists.get(0).get("ID");
                if(!Util.isEoN(isExist)){
                    resObj.put("result",false);
                    resObj.put("errMsg","存在变更中的专家信息，无法修改单位信息");
                }else{
                    resObj.put("result",true);
                }
            }else{
                resObj.put("result",true);
            }
        }else{ //专家信息变更，需判断个人数据仓修改单位信息是否在变更流程
            ZjkRyjbxxUpdate zjkRyjbxxUpdate = this.zjkRyjbxxUpdateService.findById(sourceid);
            List<Map> sysUsers = this.dbHelper.getRows("select t " +
                    "from SysUser t where t.certificateno = ?",new Object[]{zjkRyjbxxUpdate.getId_number()});

            if(sysUsers.size()>0){
                List<Map> isExist = this.dbHelper.getRows("select id from register_temp " +
                        "where sourceid = ? and status = '审核中' and type = 'user'",new Object[]{sysUsers.get(0).get("ID")});

                if(isExist.size()>0){
                    resObj.put("result",false);
                    resObj.put("errMsg","存在变更中的修改单位信息，无法进行专家信息变更");
                }else{
                    resObj.put("result",true);
                }
            }else{
                resObj.put("result",true);
            }
        }

        return resObj;
    }

}
