/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2016-4-13 14:57:19
 *
 */
package cn.topcheer.pms2.biz.project.service.impl;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PmsProjectdescription 服务类
 */
@Service(value="PmsProjectdescriptionService")
public class PmsProjectdescriptionService extends GenericService<PmsProjectdescription> {

	public PmsProjectdescriptionDao getPmsProjectdescriptionDao() {
		return (PmsProjectdescriptionDao) this.getGenericDao();
	}

	@Autowired
	DBHelper dbHelper;
	@Autowired
	public void setPmsProjectdescriptionDao(PmsProjectdescriptionDao pmsProjectdescriptionDao) {

		this.setGenericDao(pmsProjectdescriptionDao);
	}

	//查询批次说明
	public List findDescriptionByBatchid(Object batchid) {
		// TODO Auto-generated method stub
		String sql = "";
		if(Util.isEoN(batchid)){
			sql = "select e.id,e.batchid,e.batchname,e.legalbasis,"+
				"e.chargesituation,e.briefintroduction,e.detailedinformation,"+
				"e.acceptancecondition,e.handlingmaterial,e.managementprocess from pms_projectdescription e";
		}else{
			sql = "select e.id,e.batchid,e.batchname,e.legalbasis,"+
					"e.chargesituation,e.briefintroduction,e.detailedinformation,"+
					"e.acceptancecondition,e.handlingmaterial,e.managementprocess from pms_projectdescription e"
					+ " where e.batchid = ?";
		}
		List<Map> list= dbHelper.getRows(sql, Util.isEoN(batchid)?null:(new Object[]{batchid}));
		List<Map> tarMap = new ArrayList<Map>();
		if(list!=null&&list.size()>0){
			for(Map map:list){
				Map tempMap = new HashMap();
				tempMap.put("id", map.get("id"));
				tempMap.put("batchid", map.get("batchid"));
				tempMap.put("batchname", map.get("batchname"));
				if(!Util.isEoN(map.get("legalbasis"))&&Util.isUUID(map.get("legalbasis"))){
					String selectClob = "select c.source from pms_clob c where c.sourceid = ? and c.columnname = 'legalbasis'";
					tempMap.put("legalbasis", dbHelper.getValue(selectClob, "source",new Object[]{map.get("legalbasis")}));
				}else{
					tempMap.put("legalbasis", map.get("legalbasis"));
				}
				if(!Util.isEoN(map.get("chargesituation"))&&Util.isUUID(map.get("chargesituation"))){
					String selectClob = "select c.source from pms_clob c where c.sourceid = ? and c.columnname = 'chargesituation'";
					tempMap.put("chargesituation", dbHelper.getValue(selectClob, "source",new Object[]{map.get("chargesituation")}));
				}else{
					tempMap.put("chargesituation", map.get("chargesituation"));
				}
				if(!Util.isEoN(map.get("briefintroduction"))&&Util.isUUID(map.get("briefintroduction"))){
					String selectClob = "select c.source from pms_clob c where c.sourceid = ? and c.columnname = 'briefintroduction'";
					tempMap.put("briefintroduction", dbHelper.getValue(selectClob, "source",new Object[]{map.get("briefintroduction")}));
				}else{
					tempMap.put("briefintroduction", map.get("briefintroduction"));
				}
				if(!Util.isEoN(map.get("detailedinformation"))&&Util.isUUID(map.get("detailedinformation"))){
					String selectClob = "select c.source from pms_clob c where c.sourceid = ? and c.columnname = 'detailedinformation'";
					tempMap.put("detailedinformation", dbHelper.getValue(selectClob, "source",new Object[]{map.get("detailedinformation")}));
				}else{
					tempMap.put("detailedinformation", map.get("detailedinformation"));
				}
				if(!Util.isEoN(map.get("acceptancecondition"))&&Util.isUUID(map.get("acceptancecondition"))){
					String selectClob = "select c.source from pms_clob c where c.sourceid = ? and c.columnname = 'acceptancecondition'";
					tempMap.put("acceptancecondition", dbHelper.getValue(selectClob, "source",new Object[]{map.get("acceptancecondition")}));
				}else{
					tempMap.put("acceptancecondition", map.get("acceptancecondition"));
				}
				if(!Util.isEoN(map.get("handlingmaterial"))&&Util.isUUID(map.get("handlingmaterial"))){
					String selectClob = "select c.source from pms_clob c where c.sourceid = ? and c.columnname = 'handlingmaterial'";
					tempMap.put("handlingmaterial", dbHelper.getValue(selectClob, "source",new Object[]{map.get("handlingmaterial")}));
				}else{
					tempMap.put("handlingmaterial", map.get("handlingmaterial"));
				}
				if(!Util.isEoN(map.get("managementprocess"))&&Util.isUUID(map.get("managementprocess"))){
					String selectClob = "select c.source from pms_clob c where c.sourceid = ? and c.columnname = 'managementprocess'";
					tempMap.put("managementprocess", dbHelper.getValue(selectClob, "source",new Object[]{map.get("managementprocess")}));
				}else{
					tempMap.put("managementprocess", map.get("managementprocess"));
				}
				tarMap.add(tempMap);
			}
		}
		return tarMap;
	}
	public boolean saveToPmsClob(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		if(!Util.isEoN(jsonObject)){
			String batch = "select id from pms_projectdescription e where e.batchid=?";
			if(Util.isEoN(dbHelper.getValue(batch, "id",new Object[]{jsonObject.get("batchid")}))){
				String legalbasisid = Util.NewGuid();
				String chargesituationid = Util.NewGuid();
				String briefintroductionid = Util.NewGuid();
				String detailedinformationid = Util.NewGuid();
				String acceptanceconditionid = Util.NewGuid();
				String handlingmaterialid = Util.NewGuid();
				String managementprocessid = Util.NewGuid();
				String insertSql = "insert into pms_projectdescription(id,batchid,batchname,legalbasis,chargesituation,briefintroduction,detailedinformation,acceptancecondition,handlingmaterial,managementprocess)"
						+ "values(?,?,?,?," +
						"?,?,?,?," +
						"?,?)";
				String insqlClob1 = "insert into pms_clob(id,sourceid,columnname,source)"
						+ "values(?,?,'legalbasis',?)";
				String insqlClob2 = "insert into pms_clob(id,sourceid,columnname,source)"
						+ "values(?,?,'chargesituation',?)";
				String insqlClob3 = "insert into pms_clob(id,sourceid,columnname,source)"
						+ "values(?,?,'briefintroduction',?)";
				String insqlClob4 = "insert into pms_clob(id,sourceid,columnname,source)"
						+ "values(?,?,'detailedinformation',?)";
				String insqlClob5 = "insert into pms_clob(id,sourceid,columnname,source)"
						+ "values(?,?,'acceptancecondition',?)";
				String insqlClob6 = "insert into pms_clob(id,sourceid,columnname,source)"
						+ "values(?,?,'handlingmaterial',?)";
				String insqlClob7 = "insert into pms_clob(id,sourceid,columnname,source)"
						+ "values(?,?,'managementprocess',?)";
				try {
					dbHelper.runSql(insertSql,new Object[]{Util.NewGuid(),jsonObject.get("batchid"),jsonObject.get("batchname"),legalbasisid,chargesituationid
					,briefintroductionid,detailedinformationid,acceptanceconditionid,handlingmaterialid,managementprocessid});
					dbHelper.insertClobSql(insqlClob1, new Object[]{Util.NewGuid(),legalbasisid,jsonObject.get("legalbasis")});
					dbHelper.insertClobSql(insqlClob2, new Object[]{Util.NewGuid(),chargesituationid,jsonObject.get("chargesituation")});
					dbHelper.insertClobSql(insqlClob3, new Object[]{Util.NewGuid(),briefintroductionid,jsonObject.get("briefintroduction")});
					dbHelper.insertClobSql(insqlClob4, new Object[]{Util.NewGuid(),detailedinformationid,jsonObject.get("detailedinformation")});
					dbHelper.insertClobSql(insqlClob5, new Object[]{Util.NewGuid(),acceptanceconditionid,jsonObject.get("acceptancecondition")});
					dbHelper.insertClobSql(insqlClob6, new Object[]{Util.NewGuid(),handlingmaterialid,jsonObject.get("handlingmaterial")});
					dbHelper.insertClobSql(insqlClob7, new Object[]{Util.NewGuid(),managementprocessid,jsonObject.get("managementprocess")});
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				
				String legalbasisid = Util.NewGuid();
				String chargesituationid = Util.NewGuid();
				String briefintroductionid = Util.NewGuid();
				String detailedinformationid = Util.NewGuid();
				String acceptanceconditionid = Util.NewGuid();
				String handlingmaterialid = Util.NewGuid();
				String managementprocessid = Util.NewGuid();
				String insertSql = "update pms_projectdescription set batchname=?,legalbasis=?,chargesituation=?,briefintroduction=?" +
						",detailedinformation=?,acceptancecondition=?," +
						"handlingmaterial=?,managementprocess=? where batchid = ?";
				String delClob1 = "delete pms_clob where sourceid =?";
				String delClob2 = "delete pms_clob where sourceid =?";
				String delClob3 = "delete pms_clob where sourceid =?";
				String delClob4 = "delete pms_clob where sourceid =?";
				String delClob5 = "delete pms_clob where sourceid =?";
				String delClob6 = "delete pms_clob where sourceid =?";
				String delClob7 = "delete pms_clob where sourceid =?";
				try {
					dbHelper.runSql(delClob1,new Object[]{jsonObject.get("legalbasisid")});
					dbHelper.runSql(delClob2,new Object[]{jsonObject.get("chargesituationid")});
					dbHelper.runSql(delClob3,new Object[]{jsonObject.get("briefintroductionid")});
					dbHelper.runSql(delClob4,new Object[]{jsonObject.get("detailedinformationid")});
					dbHelper.runSql(delClob5,new Object[]{jsonObject.get("acceptanceconditionid")});
					dbHelper.runSql(delClob6,new Object[]{jsonObject.get("handlingmaterialid")});
					dbHelper.runSql(delClob7,new Object[]{jsonObject.get("managementprocessid")});
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String insqlClob1 = "insert into pms_clob(id,sourceid,columnname,source)"
						+ "values(?,?,'legalbasis',?)";
				String insqlClob2 = "insert into pms_clob(id,sourceid,columnname,source)"
						+ "values(?,?,'chargesituation',?)";
				String insqlClob3 = "insert into pms_clob(id,sourceid,columnname,source)"
						+ "values(?,?,'briefintroduction',?)";
				String insqlClob4 = "insert into pms_clob(id,sourceid,columnname,source)"
						+ "values(?,?,'detailedinformation',?)";
				String insqlClob5 = "insert into pms_clob(id,sourceid,columnname,source)"
						+ "values(?,?,'acceptancecondition',?)";
				String insqlClob6 = "insert into pms_clob(id,sourceid,columnname,source)"
						+ "values(?,?,'handlingmaterial',?)";
				String insqlClob7 = "insert into pms_clob(id,sourceid,columnname,source)"
						+ "values(?,?,'managementprocess',?)";
				try {
					dbHelper.runSql(insertSql,new Object[]{jsonObject.get("batchname"),legalbasisid,chargesituationid,briefintroductionid,detailedinformationid,
							acceptanceconditionid,handlingmaterialid,managementprocessid,jsonObject.get("batchid")});
					dbHelper.insertClobSql(insqlClob1, new Object[]{Util.NewGuid(),legalbasisid,legalbasisid,jsonObject.get("legalbasis")});
					dbHelper.insertClobSql(insqlClob2, new Object[]{Util.NewGuid(),chargesituationid,jsonObject.get("chargesituation")});
					dbHelper.insertClobSql(insqlClob3, new Object[]{Util.NewGuid(),briefintroductionid,jsonObject.get("briefintroduction")});
					dbHelper.insertClobSql(insqlClob4, new Object[]{Util.NewGuid(),detailedinformationid,jsonObject.get("detailedinformation")});
					dbHelper.insertClobSql(insqlClob5, new Object[]{Util.NewGuid(),acceptanceconditionid,jsonObject.get("acceptancecondition")});
					dbHelper.insertClobSql(insqlClob6, new Object[]{Util.NewGuid(),handlingmaterialid,jsonObject.get("handlingmaterial")});
					dbHelper.insertClobSql(insqlClob7, new Object[]{Util.NewGuid(),managementprocessid,jsonObject.get("managementprocess")});
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		}
		return true;
	}
}
