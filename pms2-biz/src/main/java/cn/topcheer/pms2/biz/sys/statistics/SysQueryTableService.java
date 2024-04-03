/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2021-8-17 13:59:15
 *
 */
package cn.topcheer.pms2.biz.sys.statistics;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysIdentity;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.sys.statistics.SysQueryParam;
import cn.topcheer.pms2.api.sys.statistics.SysQueryTable;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.common.exception.BusinessException;
import cn.topcheer.pms2.dao.sys.statistics.SysQueryTableDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SysQueryTable 服务类
 */
@Service
public class SysQueryTableService extends GenericService<SysQueryTable> {

	@Resource
	SysQueryParamService sysQueryParamService;
	@Resource
	DBHelper dbHelper;
	@Resource
	SysQueryClobService sysQueryClobService;
	@Resource
	SysUserService sysUserService;

	public SysQueryTableDao getSysQueryTableDao() {
		return (SysQueryTableDao) this.getGenericDao();
	}

	@Autowired
	public void setSysQueryTableDao(SysQueryTableDao sysQueryTableDao) {

		this.setGenericDao(sysQueryTableDao);
	}

	public List<SysQueryTable> getQueryTableByMemo(JSONObject jsonObject){
		List<SysQueryTable> sysQueryTableList= null;
		if (!Util.isEoN(jsonObject.get("memo"))) {
			String memo=jsonObject.getString("memo");
			sysQueryTableList = this.findByHql("select t from SysQueryTable t where t.memo = ?0 ",new Object[]{memo});
		}else {
			throw new ServiceException("缺少参数");
		}
		return sysQueryTableList;
	}

	public List<Map> getDataByType(JSONObject jsonObject){
		List<Map> data=new ArrayList<>();
		SysUser sysuser = this.sysUserService.findById(AuthUtil.getUserId());
		/*-----------------------------获取查询sql---开始------------------*/
		JSONArray findArray=new JSONArray();
		String infindsql="";
		SysQueryParam sysQueryParam=new SysQueryParam();
		sysQueryParam.setValue("type");
		sysQueryParam.setInsql(" and t.type=?0");
		sysQueryParam.setLinktype("");
		infindsql=this.getArrayAndSql(jsonObject,findArray,infindsql,sysQueryParam);
		sysQueryParam.setValue("typeone");
		sysQueryParam.setInsql(" and t.typeone=?1");
		infindsql=this.getArrayAndSql(jsonObject,findArray,infindsql,sysQueryParam);
		sysQueryParam.setValue("typetwo");
		sysQueryParam.setInsql(" and t.typetwo=?2");
		infindsql=this.getArrayAndSql(jsonObject,findArray,infindsql,sysQueryParam);
		//List<Map> list=this.dbHelper.getRows("select t.* from sys_query_table t where 1=1 "+infindsql,findArray.toArray());
		List<SysQueryTable> sysQueryTableList=this.findByHql("select t.* from sys_query_table t where 1=1 "+infindsql,findArray.toArray());
		/*-----------------------------查询数据---开始------------------*/
		if (sysQueryTableList.size()>0) {
			data = getMaps(jsonObject, sysuser, data, sysQueryTableList.get(0));
		}
		return data;
	}

	public JSONObject getDataByIds(JSONObject jsonObject){
		JSONObject returnData=new JSONObject();
		List<Map> data=new ArrayList<>();
		SysUser sysuser = this.sysUserService.findById(AuthUtil.getUserId());
		JSONArray array=jsonObject.getJSONArray("idArray");
		/*-----------------------------获取查询sql---开始------------------*/
		for (int i = 0; i < array.size(); i++) {
			String sourceid=array.getString(i);
			//List<Map> list=this.dbHelper.getRows("select t.* from Sys_Query_Table t where t.id=?",new Object[]{sourceid});
			SysQueryTable sysQueryTable=this.findById(sourceid);
			/*-----------------------------查询数据---开始------------------*/
			data = getMaps(jsonObject, sysuser, data, sysQueryTable);
			returnData.put(sysQueryTable.getDatakey(),data);
		}
		return returnData;
	}

	public List<Map> getDataById(JSONObject jsonObject,String sourceid){
		List<Map> data=new ArrayList<>();
		SysUser sysuser = this.sysUserService.findById(AuthUtil.getUserId());
		/*-----------------------------获取查询sql---开始------------------*/
		//List<Map> list=this.dbHelper.getRows("select t.* from Sys_Query_Table t where t.id=?",new Object[]{sourceid});
		SysQueryTable sysQueryTable=this.findById(sourceid);
		/*-----------------------------查询数据---开始------------------*/
		data = getMaps(jsonObject, sysuser, data, sysQueryTable);
		return data;
	}

	public List<Map> getMaps(JSONObject jsonObject, SysUser sysuser, List<Map> data, SysQueryTable sysQueryTable) {
		String infindsql="";
		if (!Util.isEoN(sysQueryTable)) {
			String dataSql;
			if (Util.isEoN(sysQueryTable.getSqlid())) {//一般放在这个表，如果过长放大字段表
				dataSql = sysQueryTable.getSql();
			} else {
				dataSql = this.dbHelper.getOnlyStringValue("select t.source from sys_query_clob t where t.id=?",new Object[]{sysQueryTable.getSqlid()});
			}
			String sourceid=sysQueryTable.getId();
			String endsql= Util.isEoN(sysQueryTable.getEndsql())?"":sysQueryTable.getEndsql();
			List<SysQueryParam> beforeParamList=this.sysQueryParamService.findByHql("select t from SysQueryParam t where t.sourceid =?0 and t.type=?1 order by seq ",new Object[]{sourceid,"beforeParam"});
			JSONArray paramvalueList=new JSONArray();
			if(beforeParamList.size()>0){
				for (int i1 = 0; i1 < beforeParamList.size(); i1++) {
					if ("SysUserId".equals(beforeParamList.get(i1).getValue())) {
						paramvalueList.add(sysuser.getId());
						infindsql+=(" "+beforeParamList.get(i1).getInsql());
					} else if ("SysUserUserId".equals(beforeParamList.get(i1).getValue())) {
						paramvalueList.add(sysuser.getUserid());
						infindsql+=(" "+beforeParamList.get(i1).getInsql());
					} else if ("SysUserEnterpriseId".equals(beforeParamList.get(i1).getValue())) {
						paramvalueList.add(sysuser.getEnterPriseId());
						infindsql+=(" "+beforeParamList.get(i1).getInsql());
					} else if ("SysUserRoles".equals(beforeParamList.get(i1).getValue())) {
						if ("must".equals(beforeParamList.get(i1).getLinktype())||!Util.isEoN(jsonObject.get("SysUserRoles"))) {
							List<SysIdentity> sysIdentitys=sysuser.getSysIdentitys();
							String insql="";
							for (int i2 = 0; i2 < sysIdentitys.size(); i2++) {
                                SysIdentity sysIdentity=sysIdentitys.get(i2);
                                insql +=(i2==0?"and ( "+beforeParamList.get(i1).getInsql(): " or "+beforeParamList.get(i1).getInsql());
                                paramvalueList.add("%"+sysIdentity.getSysRole().getId()+"%");
                            }
							insql+=" ) ";
							infindsql+=insql;
						}
					} else{
						infindsql=this.getArrayAndSql(jsonObject,paramvalueList," "+infindsql,beforeParamList.get(i1));
					}
				}
			}
			String allSql=dataSql+infindsql+endsql;
			//log.info("SysQueryTable:"+allSql);
			//System.out.print("SysQueryTable:"+allSql);
			data=this.dbHelper.getRows(allSql,paramvalueList.toArray());
		}
		return data;
	}

	private String getArrayAndSql(JSONObject jsonObject,JSONArray array,String infindsql,SysQueryParam sysQueryParam) {
		String key=""+sysQueryParam.getValue();
		String insql=" "+sysQueryParam.getInsql();
		String type=""+sysQueryParam.getLinktype();
		String memo=""+sysQueryParam.getMemo();
		if(!Util.isEoN(jsonObject.get(key))&&("".equals(type)||"=".equals(type))){
			array.add(jsonObject.get(key).toString());
			infindsql+=insql;
		}else if(!Util.isEoN(jsonObject.get(key))&&"like".equals(type)){
			array.add("%"+jsonObject.get(key).toString()+"%");
			infindsql+=insql;
		}else if(!Util.isEoN(jsonObject.get(key))&&"in".equals(type)){
			JSONArray array1=jsonObject.getJSONArray(key);
			String arraysql="(";
			for (int i = 0; i < array1.size(); i++) {
				if(i<array1.size()-1){
					arraysql+=("'"+array1.getString(i)+"',");
				}else {
					arraysql+=("'"+array1.getString(i)+"'");
				}
			}
			arraysql+=")";
			insql=insql.replace("?",arraysql);
			infindsql+=insql;
		}else if(!Util.isEoN(jsonObject.get(key))&&type.contains("likelevel")){//同一个参数，多次使用，可以insql写死或者为空，table写死
			int levelnum=Integer.parseInt(type.replace("likelevel_",""));
			for (int i = 0; i < levelnum; i++) {
				array.add("%"+jsonObject.get(key).toString()+"%");
			}
			infindsql+=insql;
		}else if(!Util.isEoN(jsonObject.get(key))&&type.contains("samelevel")){
			int levelnum=Integer.parseInt(type.replace("samelevel_",""));
			for (int i = 0; i < levelnum; i++) {
				array.add(jsonObject.get(key).toString());
			}
			infindsql+=insql;
		}else if(!Util.isEoN(jsonObject.get(key))&&type.contains("iflike_")&&!Util.isEoN(memo)&&jsonObject.get(key).equals(memo)){
			int levelnum=Integer.parseInt(type.replace("iflike_",""));
			for (int i = 0; i < levelnum; i++) {
				array.add("%"+jsonObject.get(key).toString()+"%");
			}
			infindsql+=insql;
		}else if(!Util.isEoN(jsonObject.get(key))&&type.contains("ifsame_")&&!Util.isEoN(memo)&&jsonObject.get(key).equals(memo)){
			int levelnum=Integer.parseInt(type.replace("ifsame_",""));
			for (int i = 0; i < levelnum; i++) {
				array.add(jsonObject.get(key).toString());
			}
			infindsql+=insql;
		}else if(!Util.isEoN(jsonObject.get(key))&&type.contains("ifnotlike_")&&!Util.isEoN(memo)){
			String[] aa=memo.split("&&");
			boolean notlike=true;
			for (int i = 0; i < aa.length; i++) {
				if (notlike) {
					notlike=!jsonObject.get(key).equals(aa[i]);
				}
			}
			if (notlike) {
				int levelnum=Integer.parseInt(type.replace("ifnotlike_",""));
				for (int i = 0; i < levelnum; i++) {
					array.add("%"+jsonObject.get(key).toString()+"%");
				}
				infindsql+=insql;
			}
		}else if(!Util.isEoN(jsonObject.get(key))&&type.contains("ifnotsame_")&&!Util.isEoN(memo)&&jsonObject.get(key).equals(memo)){
			String[] aa=memo.split("&&");
			boolean notlike=true;
			for (int i = 0; i < aa.length; i++) {
				notlike=!jsonObject.get(key).equals(aa[i]);
			}
			if (notlike) {
				int levelnum=Integer.parseInt(type.replace("ifnotsame_",""));
				for (int i = 0; i < levelnum; i++) {
					array.add(jsonObject.get(key).toString());
				}
				infindsql+=insql;
			}
		}
		return infindsql;
	}

	public void saveData(){
		//SysQueryTable/saveData.do
		String sourceid="100001kjjlhtj_wcdw_dywcdwdq_hj";//放插入的id
		//插入的语句
		String content=" ";
		SysQueryTable sysQueryTable=this.findById(sourceid);
		//小字段
		//sysQueryTable.setSql(content);
		//大语句
		this.merge(sysQueryTable);
		this.sysQueryClobService.saveToClob("Sys_Query_Table", sourceid, "sqlid", content,sourceid);
	}
}
