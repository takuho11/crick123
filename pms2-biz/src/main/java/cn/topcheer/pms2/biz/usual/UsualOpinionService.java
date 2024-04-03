package cn.topcheer.pms2.biz.usual;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.usual.UsualOpinion;
import cn.topcheer.pms2.dao.usual.UsualOpinionDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service(value = "UsualOpinionService")
public class UsualOpinionService extends GenericService<UsualOpinion> {
    public UsualOpinionDao getUsualOpinionDao() {
        return (UsualOpinionDao) this.getGenericDao();
    }

    @Autowired
    DBHelper dbHelper;

    @Autowired
    public void setUsualOpinionDao(UsualOpinionDao usualOpinionDao) {
        this.setGenericDao(usualOpinionDao);
    }


    /**
     * 常用意见保存
     */
    public void saveorAddOpinions(JSONObject json) {
        UsualOpinion usualOpinion = this.findById(json.get("id") + "");

        if(usualOpinion==null){
            usualOpinion = new UsualOpinion();
        }
//                this.dbHelper.getOnlyStringValue("select roleid from sys_identity where userid = ?", new Object[]{userid});
        usualOpinion.setId(json.get("id") + "");
        usualOpinion.setUserid(json.get("userid") + "");
        usualOpinion.setRoleid(json.get("roleid") +"");
        usualOpinion.setBackopinion(json.get("backopinion") + "");
        this.merge(usualOpinion);
    }

    public void deleteOneOpinion(JSONObject jsonObject) {
        this.deleteById(jsonObject.get("id") + "");
    }


    public List<Map> selectOpinions(JSONObject jsonObject){
      return  this.dbHelper.getRows("select id,backopinion from usual_opinion where userid = ?",new Object[]{jsonObject.get("userid")});
    }

}
