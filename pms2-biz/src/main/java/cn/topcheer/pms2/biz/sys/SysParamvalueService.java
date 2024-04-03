/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2016-1-8 10:23:54
 *
 */
package cn.topcheer.pms2.biz.sys;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.sys.SysParamvalue;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.jpa.sys.SysParamvalueDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * SysParamvalue 服务类
 */
@Service(value="SysParamvalueService")
public class SysParamvalueService extends GenericService<SysParamvalue> {
	public SysParamvalueDao getSysParamvalueDao() {
		return (SysParamvalueDao) this.getGenericDao();
	}
	@Autowired
	public void setSysParamvalueDao(SysParamvalueDao sysParamvalueDao) {

		this.setGenericDao(sysParamvalueDao);
	}

	@Autowired
	DBHelper dbHelper;

	public List<Map<String,Object>> findParamValuesByCode(String code)
	{
		String hql="select new map( t.code as code,t.value as text) from ? t join t.sysParamtype tp where tp.code=? order by t.seq,t.code";
		List<Map<String,Object>> list= this.findByHql(hql, code, SysParamvalue.class.getName());
		
		return list;
	}

	public List<Map> findParamsByParamid(String Paramid){
		String sql = "select * from Sys_Paramvalue where paramid = ?";
		List<Map> list = dbHelper.getRows(sql,new  Object[]{Paramid});
		return list;
	}

    public List<Map> findParamsByCodeForphone(String code){
        String sql = "select  t.code as code,t.value as text from Sys_Paramvalue t join sys_Paramtype tp on tp.id=t.paramid where tp.code=? order by t.seq,t.code";
        List<Map> list = dbHelper.getRows(sql,new  Object[]{code});
        return list;
    }

	public boolean savePbwp(JSONArray jsonArray){
		String ids = jsonArray.stream().map(obj-> {
			JSONObject returnObj = new JSONObject();
			JSONObject jsonObj = (JSONObject)obj;
			return jsonObj.getString("id");
		}).collect(Collectors.joining(","))+"";
		Query query = this.getQuery("select t from SysParamvalue t where t.code = 'pbwp'");
		SysParamvalue sysParamvalue = (SysParamvalue)query.uniqueResult();
		if(sysParamvalue!=null){
			sysParamvalue.setValue(ids);
			this.merge(sysParamvalue);
		}else{
			SysParamvalue sysParamvalue_new = new SysParamvalue();
			sysParamvalue_new.setId(UUID.randomUUID().toString());
			sysParamvalue_new.setCode("pbwp");
			sysParamvalue_new.setValue(ids);
			sysParamvalue_new.setMemo("屏蔽网评按钮的权限角色ID");
			this.merge(sysParamvalue_new);
		}

		return true;
	}

	//屏蔽网评操作按钮 能看所有批次的权限 ----监督处 厅领导 等
	public String[] getPbwpRoleIds(){
		Query query = this.getQuery("select t from SysParamvalue t where t.code = 'pbwp'");
		SysParamvalue sysParamvalue = (SysParamvalue)query.uniqueResult();
		if(sysParamvalue!=null){
			String ids = sysParamvalue.getValue();
			if(ids!=null&&ids.length()>0){
				return ids.split(",");
			}
		}
		return null;
	}



	public List<Map> getZyzBySubject(String subjectcode){
		List<Map> list = new ArrayList<>();
		if(!"".equals(subjectcode)){
			String ywhy = this.getOnlyValueBySql("select ywhy from pms_subject_type where code = ?",new Object[]{subjectcode});
			if(Util.isEoN(ywhy)){ //查找上一级
				if(subjectcode.length()>3){
					subjectcode = subjectcode.substring(0,subjectcode.length()-2);
					ywhy = this.getOnlyValueBySql("select ywhy from pms_subject_type where code = ?",new Object[]{subjectcode});
					if(Util.isEoN(ywhy)) { //查找上一级
						if(subjectcode.length()>3){
							subjectcode = subjectcode.substring(0,subjectcode.length()-2);
							ywhy = this.getOnlyValueBySql("select ywhy from pms_subject_type where code = ?",new Object[]{subjectcode});
							if(Util.isEoN(ywhy)) { //查找上一级
								list = dbHelper.getRows("select * from sys_paramvalue where paramid = 'zyz'",null);
							}else{ //按需返回
								list = dbHelper.getRows("select * from sys_paramvalue where instr(?,code) > 0 and paramid = 'zyz'",new Object[]{ywhy});
							}
						}else{
							list = dbHelper.getRows("select * from sys_paramvalue where paramid = 'zyz'",null);
						}
					}else{ //按需返回
						list = dbHelper.getRows("select * from sys_paramvalue where instr(?,code) > 0 and paramid = 'zyz'",new Object[]{ywhy});
					}
				}else{
					list = dbHelper.getRows("select * from sys_paramvalue where paramid = 'zyz'",null);
				}
			}else{ //按需返回
				list = dbHelper.getRows("select * from sys_paramvalue where instr(?,code) > 0 and paramid = 'zyz'",new Object[]{ywhy});
			}
		}
		return list;
	}


	/**
	 * 根据paramid获取值
	 * @param paramid
	 * @return
     */
	public List<Map> getParamvalueByParamid(String paramid){
		return dbHelper.getRows("select e.value from sys_paramvalue e" +
				" where e.paramid = ? order by e.code",new Object[]{paramid});
	}

	public SysParamvalue findByName(String name){
		List<SysParamvalue> list = this.findByProperty("name",name);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}


	public SysParamvalue findParamsClassByCodeAndTcodeOne(String tcode,String vcode){
		String hql = "select t from cn.topcheer.pms.pojo.SysParamvalue t join t.sysParamtype tp where tp.code=? and t.code = ? ";
		List<SysParamvalue> list = this.findByHql(hql,tcode,vcode);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
