/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2016-1-25 13:39:30
 */
package cn.topcheer.pms2.biz.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.minidev.entity.MiniSdBizDefTabItemField;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.sys.SysUserPhone;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.sys.SysUserPhoneDao;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * SysRole 服务类
 */
@Service(value = "SysUserPhoneService")
public class SysUserPhoneService extends GenericService<SysUserPhone> {

    public SysUserPhoneDao getSysUserPhoneDao() {
        return (SysUserPhoneDao) this.getGenericDao();
    }

    @Autowired
    public void setSysUserPhoneDao(SysUserPhoneDao sysUserPhoneDao) {

        this.setGenericDao(sysUserPhoneDao);
    }

    @Autowired
    private SysOperationrecordService sysOperationrecordService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private DBHelper dbHelper;


//	this.sysOperationrecordService.commonSaveOperation(roleId,"用户删除角色数据","用户删除角色："+roleId);


    public void editData(JSONObject jsonObject) throws Exception {
        String id = jsonObject.get("id") + "";
        SysUserPhone sysUserPhone = this.findById(id);

        if (sysUserPhone == null) {
            sysUserPhone = new SysUserPhone();
            sysUserPhone.setId(Util.NewGuid());
        }

        Util.ApplyObject(sysUserPhone, jsonObject);
        sysUserPhone.setSavedate(new Date());
        this.merge(sysUserPhone);

    }


    public void switchData(JSONObject jsonObject) {
        String id = jsonObject.get("id") + "";
        SysUserPhone sysUserPhone = this.findById(id);

        if (sysUserPhone == null) {
            throw new ServiceException("未找到数据。");
        }

        sysUserPhone.setStatus(jsonObject.get("status") + "");
        sysUserPhone.setSavedate(new Date());
        this.merge(sysUserPhone);

    }


    public List<Map<String, String>> initDataByUserid(SysUser sysUser) {
//        List<Map> list = this.dbHelper.getRows("select mobile from sys_user_phone " +
//                "where userid = ? and status = '启用' " +
//                "union " +
//                "select mobile from sys_user " +
//                "where id = ? ", new Object[]{sysUser.getId(), sysUser.getId()});
//
//        if (list.size() > 0) {
//            for (int i = 0; i < list.size(); i++) {
//                list.get(i).put("mobile", this.translateMobilephone(list.get(i).get("mobile") + ""));
//            }
//        }

        List<Map<String, String>> list = new ArrayList<>();

        // 取SysUser表中的手机号
        if (StringUtils.isNotBlank(sysUser.getMobile())) {
            Map<String, String> map = new HashMap<>(1);
            map.put("mobile", translateMobilephone(sysUser.getMobile()));
            list.add(map);
        }

        // 取SysUserPhone表中的手机号
        HqlBuilder<SysUserPhone> builder = HqlBuilder.builder();
        builder.eq(SysUserPhone::getUserid, sysUser.getId());
        builder.eq(SysUserPhone::getStatus, "启用");
        List<SysUserPhone> userPhoneList = this.find(builder);
        for (SysUserPhone data : userPhoneList) {
            Map<String, String> map = new HashMap<>(1);
            map.put("mobile", translateMobilephone(data.getMobile()));
            list.add(map);
        }

        return list;
    }


    /**
     * 手机号码脱敏
     *
     * @param mobilephone
     * @return
     */
    public static String translateMobilephone(String mobilephone) {
        if (!Util.isEoN(mobilephone)) {
            int a = mobilephone.length();
            if (a <= 7) {
                return mobilephone;
            }
            StringBuilder tel = new StringBuilder(mobilephone.substring(0, 3));
            for (int i = 0; i < (a - 7); i++) {
                tel.append("*");
            }
            return tel + mobilephone.substring(a - 4, a);
        }
        return null;
    }


    /**
     * 获取用户手机号列表
     *
     * @param userid 用户id
     * @return List
     * @author szs
     * @date 2024-02-27
     */
    public List<SysUserPhone> getUserPhoneList(String userid) {
        HqlBuilder<SysUserPhone> builder = HqlBuilder.builder();
        builder.eq(SysUserPhone::getUserid, userid);
        builder.addOrder("savedate", "asc");
        return this.find(builder);
    }


    /**
     * 批量保存
     *
     * @param userid        用户id
     * @param userPhoneList 手机号集合
     * @author szs
     * @date 2024-02-27
     */
    public void batchSave(String userid, List<SysUserPhone> userPhoneList) {
        // 查询已有数据
        List<SysUserPhone> list = getUserPhoneList(userid);

        // 获取待删除的ids
        Set<String> toDelIds = list.stream().map(SysUserPhone::getId).collect(Collectors.toSet());

        // 遍历处理数据
        Date date = new Date();
        for (SysUserPhone data : userPhoneList) {
            if (StringUtils.isNotBlank(data.getId())) {
                toDelIds.remove(data.getId());
            } else {
                data.setId(Util.NewGuid());
            }

            data.setUserid(userid);
            data.setSavedate(date);
            this.merge(data);
        }

        // 删除多余数据
        if (toDelIds.size() > 0) {
            this.deleteByIds(new ArrayList<>(toDelIds));
        }


    }


}
