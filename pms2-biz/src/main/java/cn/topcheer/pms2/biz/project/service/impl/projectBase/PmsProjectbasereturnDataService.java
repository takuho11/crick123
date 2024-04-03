/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2019-9-23 16:16:02
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbasereturnData;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbasereturnDataDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * PmsProjectbasereturnData 服务类
 */
@Service(value="PmsProjectbasereturnDataService")
public class PmsProjectbasereturnDataService extends GenericService<PmsProjectbasereturnData> {
	@Autowired
	DBHelper dbHelper;

	public PmsProjectbasereturnDataDao getPmsProjectbasereturnDataDao() {
		return (PmsProjectbasereturnDataDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectbasereturnDataDao(PmsProjectbasereturnDataDao pmsProjectbasereturnDataDao) {

		this.setGenericDao(pmsProjectbasereturnDataDao);
	}

	/**
	 * 【申报流程退回】--获取最新的退回的申报信息  通过项目id
	 * @param projectbaseid
	 * @return
	 */
	public JSONObject initNewRreturnDataByPid(String projectbaseid){
		JSONObject json = new JSONObject();
		List<PmsProjectbasereturnData> list = this.findByHql("select c from cn.topcheer.pms2.api.project.entity.PmsProjectbasereturnData c" +
				" where c.projectbaseid=? order by c.returnnum desc",new Object[]{projectbaseid});
		if(!Util.isEoN(list)&&list.size()>0){
			PmsProjectbasereturnData data = list.get(0);
			String strData = data.getReturndata();
			json = JSONObject.fromObject(strData);
		}
		return json;
	}


	/**
	 * 【申报流程退回】--保存当前退回版本的申报信息  通过项目id
	 * @param projectbaseid
	 * @param returnData
	 * @return
	 */
	public Boolean saveReturnDataByPid(String projectbaseid,JSONObject returnData){
		PmsProjectbasereturnData data = new PmsProjectbasereturnData();
		data.setId(Util.NewGuid());
		data.setProjectbaseid(projectbaseid);
		data.setReturndata(returnData.toString());
		List<Map> returnList = dbHelper.getRows("select id from pms_projectbasereturn_data where projectbaseid = ?",new Object[]{projectbaseid});
		if(!Util.isEoN(returnList)&&returnList.size()>0){
			data.setReturnnum(returnList.size()+1);
		}else{
			data.setReturnnum(1);
		}
		this.merge(data);
		return true;

	}

	
}
