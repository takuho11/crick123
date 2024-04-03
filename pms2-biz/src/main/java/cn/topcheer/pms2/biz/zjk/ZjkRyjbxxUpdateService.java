/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2017-1-23 9:49:28
 *
 */
package cn.topcheer.pms2.biz.zjk;

import cn.topcheer.halberd.app.api.framework.entity.sys.PmsEnterprise;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysIdentity;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysRole;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.JsonConfigUtil;
import cn.topcheer.halberd.app.api.utils.JsonDateValueProcessor;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.annotation.FieldDes;
import cn.topcheer.pms2.api.bi.BiTalentWe;
import cn.topcheer.pms2.api.flow.entity.FlowRecord;
import cn.topcheer.pms2.api.pml.vo.Page;
import cn.topcheer.pms2.api.zjk.ZjkRyjbxxInfoUpdate;
import cn.topcheer.pms2.api.zjk.ZjkRyjbxxUpdate;
import cn.topcheer.pms2.api.zjk.ZjkRyjbxxxkflUpdate;
import cn.topcheer.pms2.biz.bi.BiTalentWeService;
import cn.topcheer.pms2.biz.flow.service.impl.FlowRecordService;
import cn.topcheer.pms2.biz.pml.service.impl.PageService;
import cn.topcheer.pms2.biz.pml.service.impl.SysDownRecordService;
import cn.topcheer.pms2.biz.sys.*;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.zjk.ZjkRyjbxxUpdateDao;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.poi.hssf.usermodel.*;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * ZjkRyjbxxUpdate 服务类
 */
@Service(value = "ZjkRyjbxxUpdateService")
public class ZjkRyjbxxUpdateService extends GenericService<ZjkRyjbxxUpdate> {

	public ZjkRyjbxxUpdateDao getZjkRyjbxxUpdateDao() {
		return (ZjkRyjbxxUpdateDao) this.getGenericDao();
	}

	@Autowired
	public void setZjkRyjbxxUpdateDao(ZjkRyjbxxUpdateDao zjkRyjbxxUpdateDao) {

		this.setGenericDao(zjkRyjbxxUpdateDao);
	}

	@Autowired
	DBHelper dbHelper;
	@Autowired
	SysUserService sysUserService;
	@Autowired
	PmsEnterpriseService pmsEnterpriseService;
	@Autowired
	SysIdentityService sysIdentityService;
	@Autowired
	private ZjkRyjbxxxkflUpdateService zjkRyjbxxxkflUpdateService;
	@Autowired
	ZjkRyjbxxInfoUpdateService zjkRyjbxxInfoUpdateService;

	@Autowired
	private PageService pageService;
	@Autowired
	private SysOperationrecordService sysOperationrecordService;
	@Autowired
	private PmsRoleService sysRoleService;
	@Autowired
	private FlowRecordService flowRecordService;
	@Autowired
	private SysDownRecordService sysDownRecordService;
	@Autowired
	BiTalentWeService biTalentWeService;


	public ZjkRyjbxxUpdate findByPid(String pid) {
		// TODO Auto-generated method stub
		String hql = "from cn.topcheer.pms.pojo.ZjkRyjbxxUpdate info where info.id = ?0 ";
		List<ZjkRyjbxxUpdate> projectbases = this.getZjkRyjbxxUpdateDao()
				.findByHql(hql, pid);
		if (projectbases != null && projectbases.size() > 0)
			return projectbases.get(0);
		else
			return null;
	}

	public ZjkRyjbxxUpdate findzcByPid(String pid) {
		// TODO Auto-generated method stub
		String hql = "from cn.topcheer.pms.pojo.ZjkRyjbxxUpdate info where info.id = ?0 ";
		List<ZjkRyjbxxUpdate> projectbases = this.getZjkRyjbxxUpdateDao()
				.findByHql(hql, pid);
		if (projectbases != null && projectbases.size() > 0)
			return projectbases.get(0);
		else
			return null;
	}

	public List IsExistIdNumber(String idnumber) {
		/*
		 * // TODO Auto-generated method stub String hql =
		 * "from cn.topcheer.pms.pojo.ZjkRyjbxx as u where u.id_number ='"
		 * +idnumber+"'"; List<ZjkRyjbxx> projectbases =
		 * this.getZjkRyjbxxUpdateDao().findByHql(hql, null); if (projectbases
		 * != null && projectbases.size() > 0) return projectbases; else return
		 * null;
		 */
		String sql = "select * from zjk_ryjbxx_update t where t.id_number=?";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{idnumber});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}


	public boolean savezcAllProject(JSONObject jsonObject, String xmsbsid) {
		String hql4 = "from cn.topcheer.pms.pojo.ZjkRyjbxxxkflUpdate t where t.person_id =?0 and Xmzlfl ='true'";
		zjkRyjbxxxkflUpdateService.deleteList(zjkRyjbxxxkflUpdateService
				.findByHql(hql4, xmsbsid));

		JSONArray disciplines = jsonObject.getJSONArray("disciplines");// 项目学科分类

		// 项目学科分类ryjbxxxkfl
		if (!Util.isEoN(disciplines) && disciplines.size() > 0) {
			for (int i = 0; i < disciplines.size(); i++) {
				ZjkRyjbxxxkflUpdate inst = new ZjkRyjbxxxkflUpdate();
				Util.ApplyObject(inst, disciplines.getJSONObject(i));
				inst.setId(Util.NewGuid());
				inst.setSeq(i);
				inst.setFirstleveldiscipline((disciplines.getJSONObject(i).get(
						"firstleveldiscipline") + ""));
				inst.setSubordinatediscipline((disciplines.getJSONObject(i)
						.get("subordinatediscipline") + ""));
				inst.setThirdleveldiscipline((disciplines.getJSONObject(i).get(
						"thirdleveldiscipline") + ""));
				inst.setPerson_id(xmsbsid);
				inst.setXmzlfl("true");
				zjkRyjbxxxkflUpdateService.save(inst);
			}
		}

		return true;
	}

	/**
	 * @author chong
	 * @return 基本情况
	 * @throws IOException
	 */
	public List findexpert(String pid) {
		String sql = "select t.id,t.organization_address,t.jtgzdw,t.minicurrentstate,t.region,t.person_id,t.name,t.managedomain,t.panels,t.psgztj,t.sscs,t.gender,t.birthday,t.organization,t.cdxmlx,t.zjlbxx1,t.keywords"
				+ ",t.organization_property,t.position_title,t.professional_title,t.education,t.degree,t.yorngjzj,t.professional_date"
				+ ",t.mobile_telephone,t.office_telephone,t.email,t.postal_address,t.postcode,t.submitdate,t.userid"
				+ ",t.id_type,t.id_number,t.bank_name,t.bank_account,t.industry,t.research_direction,t.firstleveldiscipline"
				+ ",t.subordinatediscipline,t.thirdleveldiscipline,t.discipline,t.rktj,t.zjlbxx,t.expert_type from ZJK_RYJBXX_UPDATE t where t.userid=?";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{pid});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return 基本情况
	 * @throws IOException
	 */
	public List findexpertbyuserid(String pid) {
		String sql = "select t.id,s.userid,t.minicurrentstate,t.region,t.person_id,t.name,t.managedomain,t.panels,t.psgztj,t.sscs,t.gender,t.birthday,t.organization,t.cdxmlx,t.zjlbxx1,t.keywords"
				+ ",t.organization_property,t.position_title,t.professional_title,t.education,t.degree,t.yorngjzj,t.professional_date"
				+ ",t.mobile_telephone,t.office_telephone,t.email,t.postal_address,t.postcode"
				+ ",t.id_type,t.id_number,t.bank_name,t.bank_account,t.industry,t.research_direction,t.firstleveldiscipline"
				+ ",t.subordinatediscipline,t.thirdleveldiscipline,t.discipline,t.rktj,t.zjlbxx,t.expert_type from ZJK_RYJBXX_UPDATE t inner join sys_user s on s.id=t.userid where t.userid=?";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{pid});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return 基本情况
	 * @throws IOException
	 */
	public String findbyuserid(String userid) {
		String sql = "select t.id from ZJK_RYJBXX_UPDATE t where t.userid=?";
		String id = dbHelper.getOnlyStringValue(sql, new Object[]{userid});
		return id;
	}

	/**
	 * @author chong
	 * @return 基本情况
	 * @throws IOException
	 */
	public List findexpertones(String pid) {
		String sql = "select t.id,t.region,t.zshszdxm,t.person_id,t.expert_type,t.sjzj,t.xjzj,t.shjzj,t.name,t.managedomain,t.panels,t.psgztj,t.sscs,t.gender,t.birthday,t.organization,t.cdxmlx,t.zjlbxx1,t.keywords"
				+ ",t.organization_property,t.position_title,t.professional_title,t.education,t.degree,t.yorngjzj"
				+ ",t.mobile_telephone,t.office_telephone,t.email,t.postal_address,t.postcode"
				+ ",t.id_type,t.id_number,t.bank_name,t.bank_account,t.industry,t.research_direction,t.firstleveldiscipline"
				+ ",t.subordinatediscipline,t.thirdleveldiscipline,t.discipline,t.rktj,t.zjlbxx,t.expert_type from ZJK_RYJBXX_UPDATE t where t.id=?";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{pid});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author wuchong
	 * @return 基本情况
	 * @throws IOException
	 */
	public List findexpertById(String id) {
		String sql = "select t.id,t.person_id,t.jtgzdw,t.jszc,t.organization_address,t.name,t.professional_date,t.psgztj,t.sscs,t.gender,t.birthday,t.organization,t.cdxmlx,t.zjlbxx1,t.keywords"
				+ ",t.organization_property,t.position_title,t.professional_title,t.education,t.degree"
				+ ",t.mobile_telephone,t.office_telephone,t.email,t.postal_address,t.postcode"
				+ ",t.id_type,t.id_number,t.bank_name,t.bank_account,t.industry,t.research_direction,t.firstleveldiscipline"
				+ ",t.subordinatediscipline,t.thirdleveldiscipline,t.discipline,t.rktj,t.zjlbxx,t.expert_type from ZJK_RYJBXX_UPDATE t where t.id=?";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{id});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else

			return null;
	}

	/**
	 * @author chong
	 * @return 基本情况
	 * @throws IOException
	 */
	public List finduserbyid(String id) {
		String sql = "select t.id,t.departmentid  from SYS_IDENTITY t where t.userid=?";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{id});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return 基本情况
	 * @throws IOException
	 */
	public List findexpertByIdone(String id) {
		String sql = "select t.id,t.name,t.region,t.mobile_telephone,t.id_type,t.id_number,s.userid,t.sjzj,t.shjzj,t.xjzj from ZJK_RYJBXX_UPDATE  t inner join sys_user s on s.id=t.userid where t.id=?";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{id});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return 基本情况
	 * @throws IOException
	 */
	public List finduserByIdone(String id) {
		String sql = "select s.id,s.userstate from sys_user s where s.id=?";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{id});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return 专家绑定的单位名称
	 * @throws IOException
	 */
	public List finduntil(String id) {
		String sql = "select  p.id,p.name from pms_enterprise p inner join sys_identity s on s.purvieworganizeid=p.id where s.userid=?";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{id});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return 基本情况
	 * @throws IOException
	 */
	public List findexpertByIdtwo(String id) {
		String sql = "select s.id,s.userstate,s.name,s.mobile,s.certificatetype,s.certificateno,s.userid,t.sjzj,t.shjzj,t.xjzj " +
				"from ZJK_RYJBXX_UPDATE  t left join sys_user s on s.id=t.userid where t.id_number=? or s.certificateno=?";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{id,id});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return 专家评审工作条件
	 * @throws IOException
	 */
	public List findzjfsgztj() {
		String sql = "select t.id,t.value,t.memo from sys_paramvalue t where t.paramid = '8800'";
		List<Map> listMap = dbHelper.getRows(sql, null);
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return 专家工作履历
	 * @throws IOException
	 */
	public List findworkhistorys(String pid) {
		String sql = "select t.id,t.person_id,t.seq,t.country,t.organization,t.unified_socialcredit_code"
				+ ",t.organization_code,t.position_title,t.position_level,t.professional_title,t.start_time"
				+ ",t.end_time,t.research_direction,t.research_content,t.research_type from ZJK_RYGZLL_UPDATE t where t.person_id=? order by t.seq asc";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{pid});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return 教育经历
	 * @throws IOException
	 */
	public List findeducationbackgrounds(String pid) {
		String sql = "select t.id,t.person_id,t.seq,t.country,t.school_name,t.university_code,t.university_uscc"
				+ ",t.major,t.education,t.degree,t.training,t.start_time,t.end_time,t.teacher "
				+ "from ZJK_RYJYXX_UPDATE t where t.person_id=? order by t.seq asc";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{pid});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return 学术兼职
	 * @throws IOException
	 */
	public List findsocialworks(String pid) {
		String sql = "select t.id,t.person_id,t.seq,t.part_time_organization,t.position_title"
				+ ",t.star_time,t.end_time,t.session_time from ZJK_XSJZXX_UPDATE t where t.person_id=? order by t.seq asc";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{pid});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return 学术评审
	 * @throws IOException
	 */
	public List findacademicaccreditations(String pid) {
		String sql = "select t.id,t.person_id,t.seq,t.review_content,t.start_time,t.end_time,t.consignor"
				+ " from zjk_xspsxx_update t where t.person_id=? order by t.seq asc";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{pid});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return v
	 * @throws IOException
	 */
	public List findryjbxxxkfl(String pid) {
		String sql = "select t.id,t.person_id,t.firstleveldiscipline,t.subordinatediscipline,t.thirdleveldiscipline,t.discipline,t.cdxmlx "
				+ "from ZJK_RYJBXXXKFL_UPDATE t where t.person_id=? and t.xmzlfl='true' order by t.seq asc";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{pid});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @throws IOException
	 */
	public List findpanelss(String pid) {
		String sql = "select t.id,t.panels,t.managedomain,t.person_id,t.firstleveldiscipline,t.subordinatediscipline,t.thirdleveldiscipline,t.discipline,t.cdxmlx "
				+ "from ZJK_RYJBXXXKFL_UPDATE t where t.person_id=? and t.xmzlfl='kjj' order by t.seq asc";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{pid});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return v
	 * @throws IOException
	 */
	public List findryjbxxxkfl1(String pid) {
		String sql = "select t.id,t.zylb,t.person_id,t.firstleveldiscipline,t.subordinatediscipline,t.thirdleveldiscipline,t.discipline,t.cdxmlx "
				+ "from ZJK_RYJBXXXKFL_UPDATE t where t.person_id=? and t.xmzlfl is null order by t.seq asc";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{pid});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return 查看单位id
	 * @throws IOException
	 */
	public List finddwid(String pid) {
		String sql = "select t.purvieworganizeid from SYS_IDENTITY t where t.userid=?";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{pid});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	public List<Map> findAllexpert() {
		String sql = "select * from zjk_ryjbxx_update ";
		List<Map> expertLis = dbHelper.getRows(sql, null);
		return expertLis;
	}

	/**
	 * @author chong
	 * @return 基本情况
	 * @throws IOException
	 */
	public List findexpertone(String pid) {
		String sql = "select t.id,t.person_id,t.name,t.gender,t.birthday,t.organization,t.cdxmlx,t.zjlbxx1,t.keywords"
				+ ",t.organization_property,t.position_title,t.professional_title,t.education,t.degree"
				+ ",t.mobile_telephone,t.office_telephone,t.email,t.postal_address,t.postcode"
				+ ",t.id_type,t.id_number,t.bank_name,t.bank_account,t.industry,t.research_direction,t.firstleveldiscipline"
				+ ",t.subordinatediscipline,t.thirdleveldiscipline,t.discipline,t.rktj,t.zjlbxx,t.expert_type from ZJK_RYJBXX t where t.dwid=?";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{pid});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @return 查找当前处室的专家
	 * @throws IOException
	 */
	public List findexpertbypasser(String pid) {
		String pid1 = "%"+ pid +"%";
		String sql = "select t.id,t.userid,t.name,t.organization,t.mobile_telephone,t.minicurrentstate,t.expert_type,t.organization_property,t.submitdate " +
				"from ZJK_RYJBXX t where t.passer like ?";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{pid1});
		if (listMap != null && listMap.size() > 0)
			return listMap;
		else
			return null;
	}

	/**
	 * @author chong
	 * @param user
	 * @param filter
	 * @return
	 */
	public List findZjkRyjbxxByFilter(SysUser user, String filter,
									  String filter2, String filter3, String filter4, String filter5) {
		String insql = " and 1=1";
		String insql2 = " and 1=1";
		String insql3 = " and 1=1";
		String insql4 = " and 1=1";
		String insql5 = " and 1=1";
		JSONArray array = new JSONArray();
		// 专家类型
		if ("".equals(filter) || filter == null || "false".equals(filter)) {
			insql = " and 1=1";
		} else {
			insql = " and t.expert_type in (?) ";
			array.add(filter);
		}
		// 院士
		if ("".equals(filter2) || filter2 == null || "false".equals(filter2)) {
			insql2 = " and 1=1";
		} else {
			insql2 = " and t.yuanshi = ? ";
			array.add(filter2);
		}
		// 信用等级
		if ("".equals(filter3) || filter3 == null || "false".equals(filter3)) {
			insql3 = " and 1=1";
		} else {
			insql3 = " and t.identifyrank in (?) ";
			array.add(filter3);
		}
		// 省外专家比例
		if ("".equals(filter4) || filter4 == null || "false".equals(filter4)) {
			insql4 = " and 1=1";
		} else {
			insql4 = " and t.expertratio = ? " ;
			array.add(filter4);
		}
		// 技术职称
		if ("".equals(filter5) || filter5 == null || "false".equals(filter5)) {
			insql5 = " and 1=1";
		} else {
			insql5 = " and t.position_title in (?) ";
			array.add(filter5);
		}
		String sql = " select t.expert_type,t.yuanshi,t.identifyrank,t.expertratio,t.position_title,t.rktj,t.name,t.mobile_telephone,t.minicurrentstateid,t.minicurrentstate,t.sscs "
				+ " from zjk_ryjbxx t "
				+ " where 1=1 "
				+ insql
				+ insql2
				+ insql3 + insql4 + insql5;
		List<Map> list = this.dbHelper.getRows(sql, array.toArray());
		return list;
	}

	/**
	 * 已回退
	 *
	 * @author wuchong
	 * @param user
	 * @param filter
	 * @param state
	 * @return
	 */
	public List findAllProjectbase_zjrkyth(SysUser user, String filter,
			String state) {
		String insql = " and 1=1";
		JSONArray array = new JSONArray();
		array.add(state);
		array.add(user.getId());
		if ("".equals(filter) || filter == null || "false".equals(filter)) {
			insql = " and 1=1";
		} else {
			insql = " and (j.name like ? or j.organization like ? or j.organization_property like ? ) ";
			array.add("%"+filter+"%");
			array.add("%"+filter+"%");
			array.add("%"+filter+"%");
		}
		String sql = "select distinct j.id,j.person_id,j.userid,j.name,j.organization,organization_property,j.expert_type,j.belonglabid,j.submitdate,j.minicurrentstate from zjk_ryjbxx j "
				+ " where j.minicurrentstateid  in (?)"
				+ " and j.userid = ?" + insql;
		List<Map> list = this.dbHelper.getRows(sql, array.toArray());
		return list;
	}

	/**
	 * 专家入库的流程-已审核
	 * 
	 * @author wuchongString
	 * @return
	 */
	public List findAllProjectbase_zjrk_ycl(SysUser user, String filter) {
		String insql = " and 1=1";
		JSONArray array = new JSONArray();
		array.add(user.getId());
		if ("".equals(filter) || filter == null || "false".equals(filter)) {
			insql = " and 1=1";
		} else {
			insql = " and (j.name like ? or j.organization like ? or j.organization_property like ? ) ";
			array.add("%"+filter+"%");
			array.add("%"+filter+"%");
			array.add("%"+filter+"%");
		}

		String sql = "select distinct j.id,j.mobile_telephone,j.person_id,j.userid,j.name,j.organization,j.organization_property,j.expert_type,j.belonglabid,j.submitdate,j.minicurrentstate from zjk_ryjbxx j"
				+ " where j.id in (select distinct(w.sourceid) from pms_workflow_record w where w.approvalresult='通过' and w.workflowstatecode like 'A020%' and w.operateuserid=?)"
				+ insql;
		List<Map> list = this.dbHelper.getRows(sql, array.toArray());
		return list;
	}

	/**
	 * 专家入库的流程-已审核
	 * 
	 * @author wuchongString
	 * @return
	 */
	public List findexpertbypasserbyname(String pid, String filter) {
		String insql = " and 1=1";
		JSONArray array = new JSONArray();
		array.add("%"+pid+"%");
		if ("".equals(filter) || filter == null || "false".equals(filter)) {
			insql = " and 1=1";
		} else {
			insql = " and (t.name like ? or t.organization like ? or t.organization_property like ? ) ";
			array.add("%"+filter+"%");
			array.add("%"+filter+"%");
			array.add("%"+filter+"%");
		}

		String sql = "select t.id,t.userid,t.name,t.organization,t.mobile_telephone,t.minicurrentstate,t.expert_type,t.organization_property,t.submitdate from ZJK_RYJBXX t where t.passer like ?" + insql;
		List<Map> list = this.dbHelper.getRows(sql, array.toArray());
		return list;
	}

	/**
	 * 专家入库的流程-已完成
	 * 
	 * @author wuchong
	 * @return
	 */
	public List findAllProjectbase_zjrk_ywc(SysUser user, String filter) {
		String insql = " and 1=1";
		JSONArray array = new JSONArray();
		array.add(user.getId());
		if ("".equals(filter) || filter == null || "false".equals(filter)) {
			insql = " and 1=1";
		} else {
			insql = " and (j.name like ? or j.organization like ? or j.organization_property like ? ) ";
			array.add("%"+filter+"%");
			array.add("%"+filter+"%");
			array.add("%"+filter+"%");
		}

		String sql = "select distinct j.id,j.person_id,j.userid,j.name,j.organization,j.organization_property,j.expert_type,j.belonglabid,j.submitdate,j.minicurrentstate from zjk_ryjbxx j"
				+ " where j.minicurrentstateid = 'A020100'"
				+ " and j.id in (select distinct(w.sourceid) from pms_workflow_record w where w.operateuserid=?)" + insql;
		List<Map> list = this.dbHelper.getRows(sql, array.toArray());
		return list;
	}

	/**
	 * 通过userid查找id
	 * 
	 * @author wuchong
	 * @param user
	 * @return
	 */
	public String findByuserid(SysUser user) {
		String sql = "select t.id from zjk_ryjbxx t where t.userid=? ";
		String id = this.dbHelper.getOnlyStringValue(sql, new Object[]{user.getId()});
		return id;
	}

	/**
	 * 通过userid查找专家详细信息互联互通
	 */
	public List findExpertJbxxByid(String id) {
		String sql = "select t.* from zjk_ryjbxx t where t.id=? ";
		List<Map> list = this.dbHelper.getRows(sql, new Object[]{id});
		return list;
	}

	/**
	 * 通过userid查找专家教育信息互联互通
	 */
	public List findExpertJyxxByid(String id) {
		String sql = "select t.* from zjk_ryjyxx t where t.person_id=? ";
		List<Map> list = this.dbHelper.getRows(sql, new Object[]{id});
		return list;
	}

	/**
	 * 通过userid查找专家工作履历信息互联互通
	 */
	public List findExpertGzllByid(String id) {
		String sql = "select t.* from zjk_rygzll t where t.person_id=? ";
		List<Map> list = this.dbHelper.getRows(sql, new Object[]{id});
		return list;
	}

	/**
	 * 通过id查找项目基本信息互联互通
	 */
	public List findJbxxByid(String id) {
		String sql = "select t.id,t.projectbasename,t.projecttotalsum,j.projectplantype,j.chiefdepartment,j.projectmanagedomain,to_char(j.startdate,'YYYY-MM-DD') as startdate ,to_char(j.enddate,'YYYY-MM-DD') as enddate,y.researchcontent,b.annually "
				+ "from pms_projectbase t "
				+ "inner join crt_contract_jbxx j on t.id=j.projectbaseid "
				+ "left join crt_contract_yjnr y on t.id=y.projectbaseid "
				+ "left join pms_planprojectbatch b on t.planprojectbatchid=b.id where t.id=?";
		List<Map> list = this.dbHelper.getRows(sql, new Object[]{id});
		return list;
	}

	/**
	 * 通过id查找负责人信息互联互通
	 */
	public List findFzrByid(String id) {
		String sql = "select t.* from crt_contract_xmry t where t.rytype='项目负责人'' and t.projectbaseid =?";
		List<Map> lists = this.dbHelper.getRows(sql, new Object[]{id});
		return lists;
	}

	// 根据id查询承担单位信息
	public List<Map> getCddw(String id) {
		String sql = "select t.organizationname,t.legalcode,t.addresscode,t.commintucateaddress,t.organizationphone,t.organizationfax " +
				"from crt_contract_cddw t where t.dwzc ='主办单位' and t.projectbaseid =?";
		List<Map> lists = this.dbHelper.getRows(sql, new Object[]{id});
		return lists;
	}

	// 根据id查询合作单位信息
	public List<Map> getHzdw(String id) {
		String sql = "select t.organizationname,t.legalcode from crt_contract_cddw t where t.dwzc !='主办单位' and t.projectbaseid =?";
		List<Map> lists = this.dbHelper.getRows(sql, new Object[]{id});
		return lists;
	}

	// 根据id查询项目人员
	public List<Map> getXmry(String id) {
		String sql = "select t.name,t.certificatename,t.certificatenumber from crt_contract_xmry t where t.rytype!='项目负责人' and t.projectbaseid =?";
		List<Map> lists = this.dbHelper.getRows(sql, new Object[]{id});
		return lists;
	}

	// 根据状态查询专家
	public List<Map> findExpertByStatus(String status) {
		String sql;
		if (status.equals("")) {
			sql = "select t.* from zjk_ryjbxx t where t.status !='上传成功' or t.status is null";
		} else {
			sql = "select t.* from zjk_ryjbxx t where t.status ='上传成功' ";
		}
		List<Map> lists = this.dbHelper.getRows(sql, null);
		return lists;
	}

	/**
	 * 通过专家Id ，把专家标注成核心专家
	 * @param id
	 */
	public Boolean markCoreExpert(HttpServletRequest request,String id){
		ZjkRyjbxxUpdate zjkRyjbxxUpdate = this.findById(id);
		if(!Util.isEoN(zjkRyjbxxUpdate)){  //主要以这个表为主
			zjkRyjbxxUpdate.setIscoreexpert("1");
			//添加操作记录
			String note = "";
			if(!Util.isEoN(zjkRyjbxxUpdate.getName())){
				note = note  + "专家姓名："+zjkRyjbxxUpdate.getName()+"，";
			}
			if(!Util.isEoN(zjkRyjbxxUpdate.getId_number())){
				note = note  + "证件号码："+zjkRyjbxxUpdate.getId_number()+"，";
			}
			if(!Util.isEoN(zjkRyjbxxUpdate.getMobile_telephone())){
				note = note  + "手机号码："+zjkRyjbxxUpdate.getMobile_telephone()+"，";
			}
			note = note + "标注为核心专家";
			this.sysOperationrecordService.commonSaveOperation(request,id,"标注核心专家",note);
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 通过模糊专家姓名获取专家信息
	 * @param jsonObject
	 * @return
	 */
	public List<Map> fetchZjInfoByName(JSONObject jsonObject) {
		List<Map> result = new ArrayList<>();
		String sql = " select r.id,r.NAME,r.ORGANIZATION,r.ID_NUMBER " +
				" from ZJK_RYJBXX_UPDATE r " +
				" where rkflag = '1' and NAME like ?";
		result = dbHelper.getRows(sql, new Object[]{"%" + jsonObject.get("name") + "%"});
		return result;
	}


	/**
	 * 同步用户基本信息到专家库
	 * @param userid
	 * @return
	 */
	public Boolean synchronizeJbxxToZjk(String userid) {
		String certificateno = this.dbHelper.getOnlyStringValue("select certificateno from sys_user where id = ?",new Object[]{userid});
		String isExpert = this.dbHelper.getOnlyStringValue("select id from zjk_ryjbxx_update where id_number = ?",new Object[]{certificateno});

		if(Util.isEoN(isExpert)){
			return true;
		}else{
			JSONObject userInfo = new JSONObject();

			String usersql = "select e.name,e.gender,e.certificatename as id_type,e.certificatenumber as id_number, " +
					"e.nation,e.birthday,e.profession as workforprofession,e.email,e.mobile as mobile_telephone, " +
					"e.doctortutor as subsidy,e.ispostdoctoral as istutor,e.telephone as office_telephone, " +
					"e.address as postal_address,e.postalcode as postcode,e.familyphone as home_telephone, " +
					"e.fax,e.website,e.keywords,e.birthplace,e.foreignlanguage,e.languagelevel,e.remittanceaddress, " +
					"e.introduce,e.nationality," +
					"w1.unitname as school,w1.profession as major,w1.degree as degree,w1.education as education, " +
					"w2.post as professional_title,w2.degree as position_title,w2.degreedate as positiondate " +
					"from sys_user t " +
					"left join bi_mainbase b on b.declarantid = t.id " +
					"and b.planprojectbatchid = 'DATAWAREHOUSE-USER-REGISTER' " +
					"left join bi_talent_bi e on e.mainid = b.id " +
					"left join bi_talent_we w1 on w1.mainid = b.id and w1.type = 'learn_current' " +
					"left join bi_talent_we w2 on w2.mainid = b.id and w2.type = 'work_current' " +
					"where t.id = ?";
			List<Map> userlist = this.dbHelper.getRows(usersql, new Object[]{userid});

			if (userlist != null && userlist.size() > 0) {
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
				JSONArray jsonArray = JSONArray.fromObject(userlist, jsonConfig);
				userInfo = jsonArray.getJSONObject(0);
			}


			ZjkRyjbxxUpdate zjkRyjbxxUpdate = this.findById(isExpert);
			Util.ApplyObject(zjkRyjbxxUpdate,userInfo);
			this.merge(zjkRyjbxxUpdate);

			return true;
		}


	}


	/**
	 * 同步用户单位信息到专家库--流程节点用
	 * @param userid
	 * @return
	 */
	public Boolean synchronizeZcxxToZjk(String userid) {
		String certificateno = this.dbHelper.getOnlyStringValue("select certificateno from sys_user where id = ?",new Object[]{userid});
		String isExpert = this.dbHelper.getOnlyStringValue("select id from zjk_ryjbxx_update where id_number = ?",new Object[]{certificateno});

		//判断是否是专家
		if(Util.isEoN(isExpert)){
			return true;
		}else{
			String unitid = this.dbHelper.getOnlyStringValue("select purvieworganizeid from sys_identity where userid = ? and rownum = 1",new Object[]{userid});
			PmsEnterprise pmsEnterprise = this.pmsEnterpriseService.findById(unitid);

			//判断是否是二级学院
			String selflevel = this.dbHelper.getOnlyStringValue("select selflevel from pms_enterprise where id = ?",new Object[]{unitid});

			if("1".equals(selflevel)){
				List<Map> list = this.dbHelper.getRows("select jtgzdw_creditcode,jtgzdw " +
						"from bi_talent_we where mainid = ? and type = 'work_current'",new Object[]{userid});

				if(list.size()>0){
					ZjkRyjbxxUpdate zjkRyjbxxUpdate = this.findById(isExpert);
					zjkRyjbxxUpdate.setUnitid(unitid);
					zjkRyjbxxUpdate.setOrganization(pmsEnterprise.getName());
					zjkRyjbxxUpdate.setJtgzdw(list.get(0).get("jtgzdw")+"");
					zjkRyjbxxUpdate.setUniformcode(pmsEnterprise.getUniformcode());
					zjkRyjbxxUpdate.setJtgzdw_uniformcode(list.get(0).get("jtgzdw_creditcode")+"");
					this.merge(zjkRyjbxxUpdate);
				}else{
					ZjkRyjbxxUpdate zjkRyjbxxUpdate = this.findById(isExpert);
					zjkRyjbxxUpdate.setUnitid(unitid);
					zjkRyjbxxUpdate.setOrganization(pmsEnterprise.getName());
					zjkRyjbxxUpdate.setJtgzdw(null);
					zjkRyjbxxUpdate.setUniformcode(pmsEnterprise.getUniformcode());
					zjkRyjbxxUpdate.setJtgzdw_uniformcode(pmsEnterprise.getUniformcode());
					this.merge(zjkRyjbxxUpdate);
				}
			}else{
				ZjkRyjbxxUpdate zjkRyjbxxUpdate = this.findById(isExpert);
				zjkRyjbxxUpdate.setUnitid(unitid);
				zjkRyjbxxUpdate.setOrganization(pmsEnterprise.getName());
				zjkRyjbxxUpdate.setJtgzdw(null);
				zjkRyjbxxUpdate.setUniformcode(pmsEnterprise.getUniformcode());
				zjkRyjbxxUpdate.setJtgzdw_uniformcode(pmsEnterprise.getUniformcode());
				this.merge(zjkRyjbxxUpdate);
			}

			return true;
		}
	}



	/**
	 * 专家异议--保存意见+标识
	 * @param jsonObject
	 * @return
	 */
	public Boolean updateDissent(JSONObject jsonObject) {
		String expertid = jsonObject.get("expertid")+"";
		String dissentContent = jsonObject.get("dissentContent")+"";

		ZjkRyjbxxUpdate zjkRyjbxxUpdate = this.findById(expertid);

		if(zjkRyjbxxUpdate!=null){
			zjkRyjbxxUpdate.setGsdissent("1");
			zjkRyjbxxUpdate.setDissentdate(new Date());
			zjkRyjbxxUpdate.setDissentcontent(dissentContent);
			this.merge(zjkRyjbxxUpdate);
		}

		return true;
	}


	/**
	 * 专家异议--获取异议中的专家
	 * @param jsonObject
	 * @return
	 */
	public List<Map> initDissentExpert(JSONObject jsonObject) {
		JSONArray paramsList = new JSONArray();
		String insql1 = " and 1=1 ";
		String insql2 = " and 1=1 ";

		//专家姓名
		if(!Util.isEoN(jsonObject.get("name")+"")){
			insql1 = " and z.name like ? ";
			paramsList.add("%"+jsonObject.get("name")+"%");
		}

		//工作单位
		if(!Util.isEoN(jsonObject.get("organization")+"")){
			insql2 = " and z.organization like ? ";
			paramsList.add("%"+jsonObject.get("organization")+"%");
		}


		String sql = "select z.name as name,z.position_title,e.name as organization,to_char(z.gsdate,'yyyy-MM-dd') as gsdate," +
				"'异议中' as status,z.dissentcontent " +
				"from zjk_ryjbxx_update z " +
				"left join pms_enterprise e on z.unitid = e.id " +
				"where 1=1 and z.gsdate is not null and z.gsdissent is not null and z.rkflag is null " +insql1+insql2+
				"order by z.gsdate desc";

		List<Map> list = this.dbHelper.getRows(sql,paramsList.toArray());

		return list;
	}



	public JSONObject judgeIsExistExpert(String certificateno,String userid){
		JSONObject resObj = new JSONObject();
		//先判断是否是省厅的专家
		String isExist = this.dbHelper.getOnlyStringValue("select id from zjk_ryjbxx_update " +
				"where id_number = ? and drbj = '2' ",new Object[]{certificateno});

		if(Util.isEoN(isExist)){
			//再判断个人信息是否完善
			String sql = "select t.id from bi_talent_bi t " +
					"left join bi_talent_we e on e.mainid = t.mainid and e.type = 'learn_current' " +
					"left join bi_talent_we w on w.mainid = t.mainid and w.type = 'work_current' " +
					"where t.mainid = ? " +
					"and t.name is not null and t.certificatename is not null and t.certificatenumber is not null " +
					"and t.mobile is not null and t.email is not null and e.degree is not null and w.unitname is not null ";
			String isOk = this.dbHelper.getOnlyStringValue(sql,new Object[]{userid});

			if(!Util.isEoN(isOk)){
				//再判断是否在变更流程中
				String isInFlow = this.dbHelper.getOnlyStringValue("select id from register_temp " +
						"where sourceid = ? and status = '审核中'",new Object[]{userid});
				if(Util.isEoN(isInFlow)){
					//职务是否为空
					String post = this.dbHelper.getOnlyStringValue("select post from bi_talent_we " +
							"where type = 'work_current' and mainid = ?",new Object[]{userid});

					if(Util.isEoN(post)||"无".equals(post)){
						resObj.put("success",false);
						resObj.put("type","D");
					}else{
						//年龄是否>65
						String ageStr = this.dbHelper.getOnlyStringValue("select trunc(months_between(sysdate,birthday)/12) as age " +
								"from bi_talent_bi where mainid = ?",new Object[]{userid});

						if(!Util.isEoN(ageStr)){
							if(Integer.parseInt(ageStr)>=65){
								resObj.put("success",false);
								resObj.put("type","F");
							}else{
								String rkdate = this.dbHelper.getOnlyStringValue("select to_char(gsdate+7,'yyyy-MM-dd HH24:mi:ss') as rkdate " +
										"from zjk_ryjbxx_update where id_number = ? ",new Object[]{certificateno});
								resObj.put("success",true);
								resObj.put("rkdate",rkdate);
							}
						}else{
							resObj.put("success",false);
							resObj.put("type","E");
						}
					}
				}else{
					resObj.put("success",false);
					resObj.put("type","C");
				}
			}else{
				resObj.put("success",false);
				resObj.put("type","B");
			}
		}else{
			resObj.put("success",false);
			resObj.put("type","A");
		}

		return resObj;
	}



	/**
	 * 【专家注册】 -- 执行流程
	 */
	public JSONObject executeFlow(JSONObject json){
		JSONObject resJson = new JSONObject();
		String id = json.get("id")+"";
		String result = json.get("result")+"";//上报 or 通过 or 退回 or 不通过
		String operationcontent = json.get("operationcontent")+"";
		if(Util.isEoN(id)||Util.isEoN(result)||Util.isEoN(operationcontent)){
			return Util.dealResJson(resJson,false,"前台参数不完整");
		}
		ZjkRyjbxxUpdate main = this.findById(id);
		String nowStateid = main.getMinicurrentstateid();//当前流程状态id
		Date nowDate = new Date();
		SysUser expertUser = this.sysUserService.findById(id);
		//判断操作结果是不是退回，是的话都是退回给用户
		if("退回".equals(result)){
			SysUser sysUser = this.sysUserService.findById(id);
			String msgContent = sysUser.getName()+"专家：您好！经审核您的入库申请未通过，已被退回，具体原因您可以登录“贵州省科技计划项目管理平台”-“专家信息维护”处查看。（贵州省科学技术厅）";

			//审核人退回
			main.setApprovestate("2");
			main.setMinicurrentstate("专家注册:专家填报");
			main.setMinicurrentstateid("EXPERT_ZC_1");

			//发短信通知
			JSONObject msgJson = new JSONObject();
			msgJson.put("cellphones", sysUser.getMobile());
			msgJson.put("content", msgContent);
			msgJson.put("bussiness", "专家注册退回");
			// msgUtil.sendMsg(msgJson);
		}else if("不通过".equals(result)){

			//审核人不通过
			main.setMinicurrentstate("专家注册:不通过");
			main.setMinicurrentstateid("EXPERT_ZC_9");
		}else if("上报".equals(result)){
			//上报
			main.setSubmitdate(nowDate);
			main.setApprovestate("0");
			main.setMinicurrentstateid("EXPERT_ZC_2");
			main.setMinicurrentstate("专家注册:所在单位审核");
		}else if("通过".equals(result)){
			//审核人通过
			switch (nowStateid){
				case "EXPERT_ZC_2":
					//专家注册:所在单位审核
					main.setMinicurrentstateid("EXPERT_ZC_3");
					main.setMinicurrentstate("专家注册:推荐单位审核");
					break;
				case "EXPERT_ZC_3":
					//专家注册:推荐单位审核
					main.setMinicurrentstateid("EXPERT_ZC_4");
					main.setMinicurrentstate("专家注册:科技厅审核");
					break;
//				case "EXPERT_ZC_4":
//					//专家注册:湾区初审
//					main.setMinicurrentstateid("EXPERT_ZC_5");
//					main.setMinicurrentstate("专家注册:湾区复审");
//					break;
				case "EXPERT_ZC_4":
					//专家注册:科技厅审核
					main.setMinicurrentstateid("EXPERT_ZC_100");
					main.setMinicurrentstate("专家注册:完成");
//					main.setRkflag("1");
					main.setApprovestate("1");
					main.setGsdate(new Date());
					main.setZero_reason("专家入库公示中");
					this.runSql("update sys_user set eid = ? where id = ?",new Object[]{id,id});

					SysIdentity sysIdentity = new SysIdentity();
					sysIdentity.setId(Util.NewGuid());
					SysRole sysRole = this.sysRoleService.findById("5A595EE9-8F1B-4318-92E5-56A2D50C34D4");//专家角色
					if (sysRole != null) {
						sysIdentity.setSysRole(sysRole);//赋予专家角色
					}
					sysIdentity.setSysUser(expertUser);//用户信息关联
					sysIdentity.setPmsEnterprise(pmsEnterpriseService.findById(expertUser.getSysIdentitys().get(0).getPmsEnterprise().getId()));//单位信息关联
					sysIdentity.setCreatedate(new Date());//创建时间
					sysIdentity.setEnabled(1);//0:表示申请中，1:表示通过
					sysIdentity.setIdentityType("sb");//参考之前的注册，暂时先保留
					this.sysIdentityService.merge(sysIdentity);
					break;
//				case "EXPERT_ZC_6":
//					//专家注册:资管处经办人审核
//					main.setMinicurrentstateid("EXPERT_ZC_7");
//					main.setMinicurrentstate("专家注册:资管处处长审核");
//					break;
//				case "EXPERT_ZC_7":
//					//专家注册:资管处处长审核
//					main.setMinicurrentstateid("EXPERT_ZC_8");
//					main.setMinicurrentstate("专家注册:完成");
////					main.setRkflag("1");
//					main.setApprovestate("1");
//					main.setGsdate(new Date());
//					this.runSql("update sys_user set eid = ? where id = ?",new Object[]{id,id});
//
//					SysIdentity sysIdentity = new SysIdentity();
//					sysIdentity.setId(Util.NewGuid());
//					SysRole sysRole = this.sysRoleService.findById("5A595EE9-8F1B-4318-92E5-56A2D50C34D4");//专家角色
//					if (sysRole != null) {
//						sysIdentity.setSysRole(sysRole);//赋予专家角色
//					}
//					sysIdentity.setSysUser(expertUser);//用户信息关联
//					sysIdentity.setPmsEnterprise(pmsEnterpriseService.findById(expertUser.getSysIdentitys().get(0).getPmsEnterprise().getId()));//单位信息关联
//					sysIdentity.setCreatedate(new Date());//创建时间
//					sysIdentity.setEnabled(1);//0:表示申请中，1:表示通过
//					sysIdentity.setIdentityType("sb");//参考之前的注册，暂时先保留
//					this.sysIdentityService.merge(sysIdentity);
//
//					break;
				default:
					break;
			}
		}
		//人工造流转记录
		FlowRecord f = new FlowRecord();
		f.setId(Util.NewGuid());
		f.setCurrentflowpointname(main.getMinicurrentstate());
		f.setCurrentflowpointid(main.getMinicurrentstateid());
		f.setCurrentoperationname(result);
		f.setCurrentoperationresultname(result);
		if("上报".equals(result)){
			f.setOperationcontent("上报成功，目前处于所在单位审核中");
		}else{
			f.setOperationcontent(operationcontent);
		}

		//各个层获取用户
		SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
		f.setCurrentoperatorid(user.getId());
		f.setCurrentoperatorname(user.getName());
		f.setOperationtime(nowDate);
		f.setSourceid(id+"ZJXX");
		this.flowRecordService.merge(f);
		//保存主表
		main.setUpdatelasttime(new Date());
		this.merge(main);

		return Util.dealResJson(resJson,true);
	}



	/**
	 * 【专家变更】 -- 执行流程--变更
	 */
	public JSONObject executeFlow_BG(JSONObject json){
		JSONObject resJson = new JSONObject();
		String id = json.get("id")+"";
		String result = json.get("result")+"";//上报 or 通过 or 退回 or 不通过
		String operationcontent = json.get("operationcontent")+"";
		if(Util.isEoN(id)||Util.isEoN(result)||Util.isEoN(operationcontent)){
			return Util.dealResJson(resJson,false,"前台参数不完整");
		}
		ZjkRyjbxxUpdate main = this.findById(id);
		String nowStateid = main.getMinicurrentstateid();//当前流程状态id
		Date nowDate = new Date();
		//判断操作结果是不是退回，是的话都是退回给用户
		if("退回".equals(result)){
			SysUser sysUser = this.sysUserService.findById(id);
			String msgContent  = sysUser.getName()+"专家：您好！经审核您的变更申请未通过，已被退回，具体原因您可以登录“贵州省科技计划项目管理平台”-“专家信息维护”处查看。（贵州省科学技术厅）";

			//审核人退回
			main.setApprovestate("2");
			main.setIscanpick("1");
			main.setMinicurrentstate("专家变更:专家填报");
			main.setMinicurrentstateid("EXPERT_BG_1");
			// updateInfoFromTemp(id, "退回");

			//发短信通知
			JSONObject msgJson = new JSONObject();
			msgJson.put("cellphones", sysUser.getMobile());
			msgJson.put("content", msgContent);
			msgJson.put("bussiness", "专家变更退回");
			// msgUtil.sendMsg(msgJson);
		}else if("不通过".equals(result)){
			//审核人不通过
			main.setMinicurrentstate("专家变更:不通过");
			main.setMinicurrentstateid("EXPERT_BG_9");
			// updateInfoFromTemp(id, "不通过");
		}else if("上报".equals(result)){
			//上报
			main.setSubmitdate(nowDate);
			main.setApprovestate("0");
			main.setIscanpick("0");
			main.setZero_reason("专家变更流程中");
			main.setMinicurrentstateid("EXPERT_BG_2");
			main.setMinicurrentstate("专家变更:所在单位审核");
		}else if("通过".equals(result)){
			//审核人通过
			switch (nowStateid){
				case "EXPERT_BG_2":
					//专家变更:所在单位审核
					main.setMinicurrentstateid("EXPERT_BG_3");
					main.setMinicurrentstate("专家变更:推荐单位审核");
					break;
				case "EXPERT_BG_3":
					//专家变更:推荐单位审核
					main.setMinicurrentstateid("EXPERT_BG_4");
					main.setMinicurrentstate("专家变更:科技厅审核");
					break;
//				case "EXPERT_BG_4":
//					//专家变更:湾区初审
//					main.setMinicurrentstateid("EXPERT_BG_5");
//					main.setMinicurrentstate("专家变更:湾区复审");
//					break;
				case "EXPERT_BG_4":
					//专家变更:科技厅审核
					main.setMinicurrentstateid("EXPERT_BG_100");
					main.setMinicurrentstate("专家变更:完成");
					main.setApprovestate("1");
					main.setIscanpick("1");
					main.setOne_reason("专家变更审核通过");

					//推荐单位相关信息覆盖
					String tempid = main.getChangecasemanagementid();
					String tempname = main.getChangecasemanagement();
					main.setCasemanagementid(tempid);
					main.setCasemanagement(tempname);
					main.setChangecasemanagementid(null);
					main.setChangecasemanagement(null);

					this.runSql("update sys_user set eid = ? where id = ?",new Object[]{id,id});
					// updateInfoFromTemp(id, "通过");
					break;
//				case "EXPERT_BG_6":
//					//专家变更:资管处经办人审核
//					main.setMinicurrentstateid("EXPERT_BG_7");
//					main.setMinicurrentstate("专家变更:资管处处长审核");
//					break;
//				case "EXPERT_BG_7":
//					//专家变更:资管处处长审核
//					main.setMinicurrentstateid("EXPERT_BG_8");
//					main.setMinicurrentstate("专家变更:完成");
//					main.setRkflag("1");
//					main.setApprovestate("1");
//					this.runSql("update sys_user set eid = ? where id = ?",new Object[]{id,id});
//					updateInfoFromTemp(id, "通过");
//					break;
				default:
					break;
			}
		}
		//人工造流转记录
		FlowRecord f = new FlowRecord();
		f.setId(Util.NewGuid());
		f.setCurrentflowpointname(main.getMinicurrentstate());
		f.setCurrentflowpointid(main.getMinicurrentstateid());
		f.setCurrentoperationname(result);
		f.setCurrentoperationresultname(result);
		if("上报".equals(result)){
			f.setOperationcontent("上报成功，目前处于所在单位审核中");
		}else{
			f.setOperationcontent(operationcontent);
		}

		//各个层获取用户
		SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
		f.setCurrentoperatorid(user.getId());
		f.setCurrentoperatorname(user.getName());
		f.setOperationtime(nowDate);
		f.setSourceid(id+"ZJXX");
		this.flowRecordService.merge(f);
		//保存主表
		main.setUpdatelasttime(new Date());
		this.merge(main);

		return Util.dealResJson(resJson,true);
	}


	public Boolean judgeExpertIsInFlow(JSONObject jsonObject) {
		String zjid = jsonObject.get("zjid") + "";

		String isExist = this.dbHelper.getOnlyStringValue("select id " +
				"from zjk_ryjbxx_update where id = ? " +
				"and minicurrentstate not in ('专家注册:完成','专家注册:专家填报','专家变更:完成','专家变更:专家填报') ", new Object[]{zjid});

		if (!Util.isEoN(isExist)) {
			return false;
		} else {
			return true;
		}

	}


	public JSONObject getFlowInfo(JSONObject jsonObject) {
		JSONObject resbj = new JSONObject();
		String sql = "";
		String zjid = jsonObject.get("zjid") + "";
		String roleid = "";

		String minicurrnetstate = this.dbHelper.getOnlyStringValue("select minicurrentstate " +
				"from zjk_ryjbxx_update where id = ? " +
				"and minicurrentstate not in ('专家注册:完成','专家注册:专家填报','专家变更:完成','专家变更:专家填报','省外专家注册:完成','省外专家注册:专家填报','省外专家变更:完成','省外专家变更:专家填报') ", new Object[]{zjid});

			if("专家注册:科技厅审核".equals(minicurrnetstate)||"专家变更:科技厅审核".equals(minicurrnetstate)||"省外专家注册:科技厅审核".equals(minicurrnetstate)||"省外专家变更:科技厅审核".equals(minicurrnetstate)){
				resbj.put("unitname","贵州省科学技术厅");
				resbj.put("username","科技厅审核人员");
				resbj.put("mobile","027-xxxxxxxx");

				return resbj;
			}else if("专家注册:资管处处长审核".equals(minicurrnetstate)||"专家变更:资管处处长审核".equals(minicurrnetstate)){
				resbj.put("unitname","广州市科技局");
				resbj.put("username","资管处处长");
				resbj.put("mobile","027-xxxxxxxx");

				return resbj;
//			}else if("专家注册:湾区初审".equals(minicurrnetstate)||"专家变更:湾区初审".equals(minicurrnetstate) || "省外专家注册:湾区初审".equals(minicurrnetstate)||"省外专家变更:湾区初审".equals(minicurrnetstate)){
//				resbj.put("unitname","大湾区科技创新服务中心（广州）有限公司");
//				resbj.put("username","湾区初审经办人");
//				resbj.put("mobile","027-xxxxxxxx");
//
//				return resbj;
//			}else if("专家注册:湾区复审".equals(minicurrnetstate)||"专家变更:湾区复审".equals(minicurrnetstate) || "省外专家注册:湾区复审".equals(minicurrnetstate)||"省外专家变更:湾区复审".equals(minicurrnetstate)){
//				resbj.put("unitname","大湾区科技创新服务中心（广州）有限公司");
//				resbj.put("username","湾区复审经办人");
//				resbj.put("mobile","027-xxxxxxxx");
//
//				return resbj;
			}else if("专家注册:所在单位审核".equals(minicurrnetstate)||"专家变更:所在单位审核".equals(minicurrnetstate)){
				sql = "select purvieworganizeid from sys_identity where userid = ? " +
						"and roleid = '129947C6-94DC-4A7D-84D2-E78A80E518A3' ";

				roleid = "C7004168-4E0C-4F1F-B341-A225B5644DC5";
			}else if("专家注册:推荐单位审核".equals(minicurrnetstate)||"专家变更:推荐单位审核".equals(minicurrnetstate)){
				sql = "select casemanagementid from zjk_ryjbxx_update where userid = ? ";

				roleid = "2A6D2039-4D9F-4B6A-ACBA-D4BEF7577487";
			}


		String unitid = this.dbHelper.getOnlyStringValue(sql, new Object[]{zjid});

		List<Map> list = this.dbHelper.getRows("select t.name as unitname,w.name as username,w.mobile " +
				"from pms_enterprise t " +
				"left join sys_identity e on e.purvieworganizeid = t.id " +
				"and e.roleid = ? " +
				"left join sys_user w on w.id = e.userid " +
				"where t.id = ? ", new Object[]{roleid,unitid});

		if (list.size() > 0) {
			resbj.put("unitname", list.get(0).get("unitname") + "");
			resbj.put("username", list.get(0).get("username") + "");
			resbj.put("mobile", list.get(0).get("mobile") + "");
		}


		return resbj;
	}


	public Boolean judgeExpertIsInGS(String expertid) {
		String isExist = this.dbHelper.getOnlyStringValue("select id " +
				"from zjk_ryjbxx_update where id = ? " +
				"and rkflag = '1' and gsdissent is null ", new Object[]{expertid});

		if (!Util.isEoN(isExist)) { //公示结束
			return true;
		} else { //公示中或异议中
			return false;
		}

	}


	public JSONObject changeInfoToTemp(JSONObject jsonObject) {
		JSONObject resObj = new JSONObject();

		String type = jsonObject.get("type") + "";
		String sourceid = jsonObject.get("sourceid") + "";
		String content = jsonObject.get("content") + "";

		String isExist = this.dbHelper.getOnlyStringValue("select id from expert_temp where type = ? and sourceid = ? and status = '审核中' ", new Object[]{type, sourceid});

		if (!Util.isEoN(isExist)) {
			resObj.put("success", false);
			resObj.put("errMsg", "已有在流程中的变更信息，无法再次变更。");
		} else {
			try {
				isExist = this.dbHelper.getOnlyStringValue("select id from expert_temp where type = ? and sourceid = ? and status = '填写中' ", new Object[]{type, sourceid});
				if(!Util.isEoN(isExist)) {
					this.dbHelper.runSql("update expert_temp set content = ?, changedate = sysdate, status = '审核中' where sourceid = ? and status = '填写中' ", new Object[]{content, sourceid});
				} else {
					this.dbHelper.runSql("insert into expert_temp (id,sourceid,type,status,content,changedate) values (?,?,?,?,?,sysdate)", new Object[]{Util.NewGuid(), sourceid, type, "审核中", content});
				}
				resObj.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return resObj;
	}


	// public Boolean updateInfoFromTemp(String sourceid, String result) {
	// 	String id = "";
	// 	JSONObject json = new JSONObject();
	//
	// 	List<Map> list = this.dbHelper.getRows("select * from expert_temp " +
	// 			"where sourceid = ? and status = '审核中' ", new Object[]{sourceid});
	// 	if (list.size() > 0) {
	// 		id = list.get(0).get("id") + "";
	// 		json = JSONObject.fromObject(list.get(0).get("content") + "");
	// 	} else {
	// 		return null;
	// 	}
	//
	// 	try {
	// 		if("通过".equals(result)) {
	// 			// 专家基本信息
	// 			JSONArray ryjbxxChangeArr = json.getJSONArray("ryjbxx");
	// 			JSONObject ryjbxxChangeObj = new JSONObject();
	// 			for(int i = 0; i < ryjbxxChangeArr.size(); i++) {
	// 				JSONObject ryjbxxTempChangeJSONObject = ryjbxxChangeArr.getJSONObject(i);
	// 				ryjbxxChangeObj.put(ryjbxxTempChangeJSONObject.get("field"), ryjbxxTempChangeJSONObject.get("newvalue"));
	// 			}
	// 			ZjkRyjbxxUpdate zjkRyjbxxUpdate = this.findById(sourceid);
	// 			Util.ApplyObject(zjkRyjbxxUpdate, ryjbxxChangeObj);
	// 			this.merge(zjkRyjbxxUpdate);
	// 			//删除学科标签
	// 			String deleteBqZjkLinkbqHql = "select t from ZjkLinkbq t " +
	// 					" where t.zjkryjbxxupdateid = ? and t.type = ? " +
	// 					" and t.endzjkbqid in ( " +
	// 					" '9282ace8-d12f-463d-d6be-2758eb534c22',\n" + // 技术专家
	// 					" 'dd276ca5-4b30-4ec9-d20a-b3f39a3b50c0',\n" + // 管理专家
	// 					" '5c5345d1-1656-445c-ff92-a21624061c2e',\n" + // 经济专家
	// 					" '6617384b-7d89-478b-c97b-bc52f50d7d1a',\n" + // 其他专家
	// 					" 'bed44cfd-7d25-473c-d93e-09a9412a7871',\n" + // 战略专家
	// 					" 'c5111578-d836-429d-f51e-18211541183b',\n" + // 顶尖高端专家
	// 					" '992026d3-2f66-42c2-8f27-bd787858614d',\n" + // 国家级高端专家
	// 					" 'fd9e98de-be52-4f42-c28e-f69611f0212c',\n" + // 省级高端专家
	// 					" '7ac3dda2-b124-4afe-f29e-c9c8d59eb5b5',\n" + // 外国专家
	// 					" 'c1fb7eb7-e48e-47ec-f96a-3469747d3d4b',\n" + // 国际合作专家
	// 					" '26b9e3c4-89e7-4e9a-ec3d-483652213606' \n" + // 企业专家
	// 					")";
	// 			List<ZjkLinkbq> zjkLinkbqList = zjkLinkbqService.findByHql(deleteBqZjkLinkbqHql, sourceid, "bq");
	// 			zjkLinkbqService.deleteList(zjkLinkbqList);
	// 			zjkRegisterService.saveZjBq(zjkRyjbxxUpdate);
	//
	// 			// 专家头衔相关内容
	// 			JSONArray ryjbxxinfoChangeArr = json.getJSONArray("ryjbxxinfo");
	// 			JSONObject ryjbxxinfoChangeObj = new JSONObject();
	// 			for(int i = 0; i < ryjbxxinfoChangeArr.size(); i++) {
	// 				JSONObject ryjbxxinfoTempChangeJSONObject = ryjbxxinfoChangeArr.getJSONObject(i);
	// 				ryjbxxinfoChangeObj.put(ryjbxxinfoTempChangeJSONObject.get("field"), ryjbxxinfoTempChangeJSONObject.get("newvalue"));
	// 			}
	// 			List<ZjkRyjbxxInfoUpdate> zjkRyjbxxInfoUpdateList = this.zjkRyjbxxInfoUpdateService.findByHql("select t from ZjkRyjbxxInfoUpdate t where t.zjkryjbxxupdateid = ? ", sourceid);
	// 			ZjkRyjbxxInfoUpdate zjkRyjbxxInfoUpdate = zjkRyjbxxInfoUpdateList.size() > 0 ? zjkRyjbxxInfoUpdateList.get(0) : new ZjkRyjbxxInfoUpdate();
	// 			Util.ApplyObject(zjkRyjbxxInfoUpdate, ryjbxxinfoChangeObj);
	// 			if(Util.isEoN(zjkRyjbxxInfoUpdate.getId())) {
	// 				zjkRyjbxxInfoUpdate.setId(Util.NewGuid());
	// 			}
	// 			zjkRyjbxxInfoUpdate.setZjkryjbxxupdateid(sourceid);
	// 			zjkRyjbxxInfoUpdateService.merge(zjkRyjbxxInfoUpdate);
	// 			// 专家学科
	// 			JSONArray subjectArrTempChangeArr = json.getJSONArray("subjectArr");
	// 			for(int i = 0; i < subjectArrTempChangeArr.size(); i++) {
	// 				JSONObject jsonObject = subjectArrTempChangeArr.getJSONObject(i);
	// 				String tempSubjecttype = jsonObject.get("type") + "";
	// 				JSONArray newvalue = jsonObject.getJSONArray("newvalue");
	// 				String subjecttype = "";
	// 				if("zj".equals(tempSubjecttype) || "jjxk".equals(tempSubjecttype)) {
	// 					subjecttype = "jjxk";
	// 				} else if("xm".equals(tempSubjecttype) || "gbxk".equals(tempSubjecttype)) {
	// 					subjecttype = "gbxk";
	// 				} else if("scly".equals(tempSubjecttype) || "scly".equals(tempSubjecttype)) {
	// 					subjecttype = "scly";
	// 				} else if("jsly".equals(tempSubjecttype) || "hyly".equals(tempSubjecttype)) {
	// 					subjecttype = tempSubjecttype;
	// 				}
	// 				List<ZjkRyjbxxxkflUpdate> zjkRyjbxxxkflUpdateList = this.zjkRyjbxxxkflUpdateService.findByHql("select t from ZjkRyjbxxxkflUpdate t where t.person_id = ? and t.subjecttype = ? ", new Object[]{sourceid, subjecttype});
	// 				// 修改后的学科ids，通过“，”分割
	// 				String ids = "";
	// 				//删除学科标签
	// 				String deleteSubjectZjkLinkbqHql = "select t from ZjkLinkbq t where t.zjkryjbxxupdateid = ? and t.type = ? ";
	// 				List<ZjkLinkbq> zjkLinkbqList1 = zjkLinkbqService.findByHql(deleteSubjectZjkLinkbqHql, sourceid, subjecttype);
	// 				zjkLinkbqService.deleteList(zjkLinkbqList1);
	// 				for(int j = 0; j < newvalue.size(); j++) {
	// 					ZjkRyjbxxxkflUpdate zjkRyjbxxxkflUpdate = new ZjkRyjbxxxkflUpdate();
	// 					Util.ApplyObject(zjkRyjbxxxkflUpdate, newvalue.getJSONObject(j));
	// 					if(Util.isEoN(zjkRyjbxxxkflUpdate.getId())) {
	// 						zjkRyjbxxxkflUpdate.setId(Util.NewGuid());
	// 					}
	// 					zjkRyjbxxxkflUpdate.setSeq(j);
	// 					zjkRyjbxxxkflUpdate.setPerson_id(sourceid);
	// 					zjkRyjbxxxkflUpdate.setXmzlfl(subjecttype);
	// 					ids += "," + zjkRyjbxxxkflUpdate.getId();
	// 					zjkRyjbxxxkflUpdateService.merge(zjkRyjbxxxkflUpdate);
	// 					// 新增学科对应标签
	// 					ZjkLinkbq zjkLinkbq = new ZjkLinkbq();
	// 					String bqid = zjkBqService.fetchBqidByTypeAndCode(subjecttype, zjkRyjbxxxkflUpdate.getSubjectendcode());
	// 					JSONObject bqInfo = zjkBqService.fetchCompleteZjkBqInfo(bqid);
	// 					Util.ApplyObject(zjkLinkbq, bqInfo);
	// 					zjkLinkbq.setId(zjkRyjbxxxkflUpdate.getId());
	// 					zjkLinkbq.setSeq(i);
	// 					zjkLinkbq.setCreatetime(new Date());
	// 					zjkLinkbq.setZjkryjbxxupdateid(zjkRyjbxxxkflUpdate.getPerson_id());
	// 					zjkLinkbqService.merge(zjkLinkbq);
	// 				}
	// 				for(int j = 0; j < zjkRyjbxxxkflUpdateList.size(); j++) {
	// 					if(ids.contains(zjkRyjbxxxkflUpdateList.get(j).getId())) {
	// 						continue;
	// 					} else {
	// 						zjkRyjbxxxkflUpdateService.delete(zjkRyjbxxxkflUpdateList.get(j));
	// 					}
	// 				}
	// 			}
	// 			dbHelper.runSql("update expert_temp set status = '审核完成，通过' where id = ? and status = '审核中'", new Object[]{id});
	// 		} else if("退回".equals(result)) {
	// 			//更新register_temp表
	// 			dbHelper.runSql("update expert_temp set status = '填写中' where id = ? and status = '审核中'", new Object[]{id});
	// 		} else {
	// 			//更新register_temp表
	// 			dbHelper.runSql("update expert_temp set status = '审核完成，不通过' where id = ? and status = '审核中'", new Object[]{id});
	// 		}
	// 	} catch (Exception e) {
	// 		throw new BusinessException(ErrorCodeEnum.UPDATE_ERROR, e);
	// 	}
	// 	return true;
	// }

	public JSONObject fetchExpertStatus(JSONObject jsonObject) {
		JSONObject result = new JSONObject();
		String zjid = jsonObject.get("zjid") + "";
		ZjkRyjbxxUpdate zjkRyjbxxUpdate = this.findById(zjid);
		if(Util.isEoN(zjkRyjbxxUpdate)) {
			// 异常
			result.put("flag", "9");
			// throw new BusinessException("未获取到专家信息!!!");
		}
		String minicurrentstate = zjkRyjbxxUpdate.getMinicurrentstate();
		String gsdissent = zjkRyjbxxUpdate.getGsdissent();
		String rkflag = zjkRyjbxxUpdate.getRkflag();
		if(Util.isEoN(minicurrentstate)
				|| "专家注册:完成".equals(minicurrentstate)
				|| "专家注册:专家填报".equals(minicurrentstate)
				|| "专家变更:完成".equals(minicurrentstate)
				|| "专家变更:专家填报".equals(minicurrentstate)
				|| "省外专家注册:完成".equals(minicurrentstate)
				|| "省外专家注册:专家填报".equals(minicurrentstate)
				|| "省外专家变更:完成".equals(minicurrentstate)
				|| "省外专家变更:专家填报".equals(minicurrentstate)) {
			if(Util.isEoN(rkflag) && Util.isEoN(gsdissent) && ("专家注册:完成".equals(minicurrentstate) || "省外专家注册:完成".equals(minicurrentstate))) {
				// 公示中
				result.put("flag", "1");
				result.put("message", "公示中");
			} else if(Util.isEoN(rkflag) && Util.isEoN(gsdissent) && (Util.isEoN(minicurrentstate) || !minicurrentstate.contains("省外"))) {
				// 注册中
				result.put("flag", "0");
				result.put("message", "注册中");
			} else if(Util.isEoN(rkflag) && Util.isEoN(gsdissent) && !Util.isEoN(minicurrentstate) && minicurrentstate.contains("省外")) {
				// 注册中
				List<Map> sysOperList = this.dbHelper.getRows("select f.* from FL_FLOWRECORD f where  f.sourceid = ? order by f.operationtime desc",new Object[]{zjid + "ZJXX"});
				if(sysOperList.size() > 0) {
					JSONObject return_info = new JSONObject();
					return_info.put("return_result", sysOperList.get(0).get("currentoperationresultname"));
					return_info.put("return_message", sysOperList.get(0).get("operationcontent"));
					result.put("message", return_info);
					result.put("flag", "6");
				} else {
					result.put("flag", "0");
					result.put("message", "注册中");
				}
			} else if("1".equals(rkflag) && Util.isEoN(gsdissent) && ("专家注册:完成".equals(minicurrentstate) || "专家变更:完成".equals(minicurrentstate)|| "省外专家注册:完成".equals(minicurrentstate)|| "省外专家变更:完成".equals(minicurrentstate))) {
				// 已入库
				String rkdate = this.dbHelper.getOnlyStringValue("select to_char(gsdate+7,'yyyy-MM-dd HH24:mi:ss') as rkdate from zjk_ryjbxx_update " +
						"where id = ? ",new Object[]{zjid});
				result.put("rkdate", rkdate);
				result.put("flag", "3");
				result.put("drbj", zjkRyjbxxUpdate.getDrbj());
				result.put("message", "已入库");
			} else if("1".equals(rkflag) && Util.isEoN(gsdissent) && ("专家变更:专家填报".equals(minicurrentstate) || "省外专家变更:专家填报".equals(minicurrentstate))) {
				// 已入库，变更退回
				result.put("flag", "5");
				result.put("changeinfo", fetchChangeInfo(jsonObject));
				List<Map> sysOperList = this.dbHelper.getRows("select f.* from FL_FLOWRECORD f where  f.sourceid = ? order by f.operationtime desc",new Object[]{zjid + "ZJXX"});
				if(sysOperList.size() > 0) {
					JSONObject return_info = new JSONObject();
					return_info.put("return_result", sysOperList.get(0).get("currentoperationresultname"));
					return_info.put("return_message", sysOperList.get(0).get("operationcontent"));
					result.put("message", return_info);
				} else {
					JSONObject return_info = new JSONObject();
					return_info.put("return_result", "后台程序异常，请联系管理员!!!");
					return_info.put("return_message", "后台程序异常，请联系管理员!!!");
					result.put("message", return_info);
				}
			} else if(!Util.isEoN(gsdissent)){
				// 有异议
				result.put("flag", "2");
				result.put("message", "有异议");
			} else {
				// 异常
				result.put("flag", "9");
				// throw new BusinessException("后台异常，请联系管理员!!!");
			}
		} else {
			result.put("flag", "4");
			result.put("message", "流程中");
			result.put("flowinfo", this.getFlowInfo(jsonObject));
		}
		return result;
	}

	public Map fetchChangeInfo(JSONObject jsonObject) {
		String zjid = jsonObject.get("zjid") + "";
		List<Map> list = this.dbHelper.getRows("select * from expert_temp t where sourceid = ? and (status = '审核中' or status = '填写中') ", new Object[]{zjid});
		return list.size() > 0 ? list.get(0) : new JSONObject();
	}

	public List<Map> fetchAllChangeInfo(JSONObject jsonObject) {
		String zjid = jsonObject.get("zjid") + "";
		List<Map> list = this.dbHelper.getRows("select * from expert_temp t where sourceid = ? and status != '审核完成，不通过' order by t.changedate desc ", new Object[]{zjid});
		return list;
	}

	/**
	 * 删除变更信息
	 * @param jsonObject
	 */
	public void deleteChangeInfo(JSONObject jsonObject) {

		Map map = fetchChangeInfo(jsonObject);
		if(map.containsKey("id") && !Util.isEoN(map.get("id"))) {
			try {
				this.dbHelper.runSql("delete expert_temp where id = ?", new Object[]{map.get("id")});
			} catch (Exception e) {
				// throw new BusinessException(ErrorCodeEnum.DELETE_ERROR, e);
			}
		}
	}




	public JSONObject getExpertStatisticsForEchart(){
		Calendar cal = Calendar.getInstance();
		JSONObject resObj = new JSONObject();
		String sql = "select t.id," +
				"(case when t.position_title = '正高级' then 'zgj' when t.position_title = '副高级' then 'fgj' when t.position_title = '中级' then 'zj' " +
				"when (t.position_title = '初级助理级' or t.position_title = '初级员级') then 'cj' when t.position_title = '无' then 'wu' else 'qt' end) as title," +
				"(case when t.gender = '男' then 'male' else 'female' end) as gender," +
				"t.organization_province as province,t.organization_city as city,substr(pp.id,0,6) as citycode," +
				"trunc(months_between(to_date(?,'yyyy-MM-dd'),to_date(t.birthday,'yyyy-MM-dd')) / 12) as age " +
				"from zjk_ryjbxx_update t " +
				"left join (select id,name from sys_params_provinces where parentid = '440000000000') pp on pp.name = organization_city " +
				"where length(t.birthday) = 10 and t.rkflag = '1' and t.organization_province = '广东省' ";
		List<Map> list = this.dbHelper.getRows(sql,new Object[]{(cal.get(Calendar.YEAR)+1)+"01-01"});

		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				String citycode = list.get(i).get("citycode")+"";
				String age = list.get(i).get("age")+"";
				String title = list.get(i).get("title")+"";
				String gender = list.get(i).get("gender")+"";

				String keyName = "age"+age+"_"+title+"_"+gender;

				if(resObj.has(citycode)){
					JSONObject cityObj = resObj.getJSONObject(citycode);

					if(cityObj.has(keyName)){
						cityObj.put(keyName,cityObj.getInt(keyName)+1);
					}else{
						cityObj.put(keyName,1);
					}

					resObj.put(citycode,cityObj);
				}else{
					JSONObject cityObj = new JSONObject();

					if(cityObj.has(keyName)){
						cityObj.put(keyName,cityObj.getInt(keyName)+1);
					}else{
						cityObj.put(keyName,1);
					}

					resObj.put(citycode,cityObj);
				}
			}
		}

		return resObj;
	}



	/**
	 * 【专家注册】 -- 执行流程--人工录入
	 */
	public JSONObject executeFlow_RGLR(JSONObject json){
		JSONObject resJson = new JSONObject();
		String id = json.get("id")+"";
		String result = json.get("result")+"";//上报 or 通过 or 退回 or 不通过
		String operationcontent = json.get("operationcontent")+"";
		if(Util.isEoN(id)||Util.isEoN(result)||Util.isEoN(operationcontent)){
			return Util.dealResJson(resJson,false,"前台参数不完整");
		}
		ZjkRyjbxxUpdate main = this.findById(id);
		String nowStateid = main.getMinicurrentstateid();//当前流程状态id
		Date nowDate = new Date();
		SysUser expertUser = this.sysUserService.findById(id);
		//判断操作结果是不是退回，是的话都是退回给用户
		if("退回".equals(result)){
			//审核人退回
			main.setApprovestate("2");
			main.setMinicurrentstate("专家录入:湾区初审录入");
			main.setMinicurrentstateid("EXPERT_RGLR_1");
		}else if("不通过".equals(result)){
			//审核人不通过
			main.setMinicurrentstate("专家录入:不通过");
			main.setMinicurrentstateid("EXPERT_RGLR_9");
		}else if("上报".equals(result)){
			//上报
			main.setSubmitdate(nowDate);
			main.setApprovestate("0");
			main.setMinicurrentstateid("EXPERT_RGLR_2");
			main.setMinicurrentstate("专家录入:湾区复审");
		}else if("通过".equals(result)){
			//审核人通过
			switch (nowStateid){
				case "EXPERT_RGLR_2":
					//专家录入:湾区复审
					main.setMinicurrentstateid("EXPERT_RGLR_5");
					main.setMinicurrentstate("专家录入:完成");
					main.setApprovestate("1");
					main.setGsdate(new Date());

					this.runSql("update sys_user set eid = ? where id = ?",new Object[]{id,id});

					SysIdentity sysIdentity = new SysIdentity();
					sysIdentity.setId(Util.NewGuid());
					SysRole sysRole = this.sysRoleService.findById("5A595EE9-8F1B-4318-92E5-56A2D50C34D4");//专家角色
					if (sysRole != null) {
						sysIdentity.setSysRole(sysRole);//赋予专家角色
					}
					sysIdentity.setSysUser(expertUser);//用户信息关联
					sysIdentity.setPmsEnterprise(pmsEnterpriseService.findById(expertUser.getSysIdentitys().get(0).getPmsEnterprise().getId()));//单位信息关联
					sysIdentity.setCreatedate(new Date());//创建时间
					sysIdentity.setEnabled(1);//0:表示申请中，1:表示通过
					sysIdentity.setIdentityType("sb");//参考之前的注册，暂时先保留
					this.sysIdentityService.merge(sysIdentity);

					break;
				default:
					break;
			}
		}
		//人工造流转记录
		FlowRecord f = new FlowRecord();
		f.setId(Util.NewGuid());
		f.setCurrentflowpointname(main.getMinicurrentstate());
		f.setCurrentflowpointid(main.getMinicurrentstateid());
		f.setCurrentoperationname(result);
		f.setCurrentoperationresultname(result);
		if("上报".equals(result)){
			f.setOperationcontent("上报成功，目前处于湾区复审中");
		}else{
			f.setOperationcontent(operationcontent);
		}

		//各个层获取用户
		SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
		f.setCurrentoperatorid(user.getId());
		f.setCurrentoperatorname(user.getName());
		f.setOperationtime(nowDate);
		f.setSourceid(id);
		this.flowRecordService.merge(f);
		//保存主表
		main.setUpdatelasttime(new Date());
		this.merge(main);

		return Util.dealResJson(resJson,true);
	}



	//导入专家--页面新增
	public JSONObject saveExpertByInvite(JSONObject jsonObject){
		JSONObject resObj = new JSONObject();
		String certificatename = jsonObject.get("certificatename")+"";
		String certificateno = jsonObject.get("certificateno")+"";
		String mobile = jsonObject.get("mobile")+"";
		String unitid = jsonObject.get("unitid")+"";
		String organization = jsonObject.get("organization")+"";
		String uniformcode = jsonObject.get("uniformcode")+"";
		String email = jsonObject.get("email")+"";
		String name = jsonObject.get("name")+"";
		String title = jsonObject.get("title")+"";
		String province = jsonObject.get("province")+"";
		String city = jsonObject.get("city")+"";
		String id = Util.NewGuid();

		//判断证件号码有没有重复
		String isExist_cert = this.dbHelper.getOnlyStringValue("select id from zjk_ryjbxx_update " +
				"where id_number = ? union select id from sys_user where certificateno = ? ",new Object[]{certificateno,certificateno});

		if(!Util.isEoN(isExist_cert)){
			resObj.put("result",false);
			resObj.put("errMsg","证件号码重复，无法录入。");
			return resObj;
		}

		//判断是否在黑名单、有诚信问题
		// Boolean isInRecord = this.scienceRulerecordService.judgeIsInRecord(certificateno,"user");

		// if(!isInRecord){
		// 	resObj.put("result",false);
		// 	resObj.put("errMsg","该专家处在黑名单列表，无法录入。");
		// 	return resObj;
		// }

		//判断手机号有没有重复
		String isExist_mobile = this.dbHelper.getOnlyStringValue("select id from zjk_ryjbxx_update " +
				"where mobile_telephone = ? union select id from sys_user where mobile = ? ",new Object[]{mobile,mobile});

		if(!Util.isEoN(isExist_mobile)){
			resObj.put("result",false);
			resObj.put("errMsg","手机号重复，无法录入。");
			return resObj;
		}


		//判断邮箱有没有重复
		String isExist_email = this.dbHelper.getOnlyStringValue("select id from zjk_ryjbxx_update " +
				"where email = ? union select id from sys_user where email = ? ",new Object[]{email,email});

		if(!Util.isEoN(isExist_email)){
			resObj.put("result",false);
			resObj.put("errMsg","邮箱重复，无法录入。");
			return resObj;
		}

		//用户选择的单位
		PmsEnterprise pmsEnterprise = this.pmsEnterpriseService.findById(unitid);

		//sys_user表
		SysUser user = new SysUser();
		user.setId(id);
		user.setUserid(mobile+"ZJ");
		user.setName(name);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setUserstate(1);// 0:不启用；1:启用
		user.setApprovestate("1");// 空：审核中，1:审核成功，0:审核不成功
		user.setDefaultSystem("sb");//这个字段要赋值，不然新首页登录会失败
		// DESEncrypt des = new DESEncrypt();
		// user.setPassword(des.encrypt("Gz@123abc"));//加密密码
		// user.setPassword(SmUtilAll.encryptAll("Gz@123abc"));//加密密码
		user.setRegisterdate(new Date());//注册时间
		this.sysUserService.merge(user);

		//sys_identity表
		SysIdentity sysIdentity = new SysIdentity();
		sysIdentity.setId(Util.NewGuid());
		SysRole sysRole = this.sysRoleService.findById("5A595EE9-8F1B-4318-92E5-56A2D50C34D4");//专家角色
		if (sysRole != null) {
			sysIdentity.setSysRole(sysRole);//赋予普通用户角色
		}
		sysIdentity.setSysUser(user);//用户信息关联
		sysIdentity.setPmsEnterprise(pmsEnterprise);//单位信息关联
		sysIdentity.setCreatedate(new Date());//创建时间
		sysIdentity.setEnabled(1);//0:表示申请中，1:表示通过
		sysIdentity.setIdentityType("sb");//参考之前的注册，暂时先保留
		this.sysIdentityService.merge(sysIdentity);

		//zjk_ryjbxx_update表
		ZjkRyjbxxUpdate zjkRyjbxxUpdate = new ZjkRyjbxxUpdate();
		zjkRyjbxxUpdate.setId(id);
		zjkRyjbxxUpdate.setName(name);
		zjkRyjbxxUpdate.setMobile_telephone(mobile);
		zjkRyjbxxUpdate.setId_type(certificatename);
		zjkRyjbxxUpdate.setId_number(certificateno);
		zjkRyjbxxUpdate.setDrbj("5");
		zjkRyjbxxUpdate.setUserid(id);
		zjkRyjbxxUpdate.setUnitid(unitid);
		zjkRyjbxxUpdate.setOrganization_province(province);
		zjkRyjbxxUpdate.setOrganization_city(city);
		zjkRyjbxxUpdate.setPosition_title(title);
		zjkRyjbxxUpdate.setEmail(email);


		if("广州市".equals(city)){ //市内
			String schoolname = this.dbHelper.getOnlyStringValue("select schoolname from pms_enterprise where id = ?",
					new Object[]{unitid});
			if(!Util.isEoN(schoolname)){
				zjkRyjbxxUpdate.setOrganization(schoolname);
				zjkRyjbxxUpdate.setJtgzdw(this.dbHelper.getOnlyStringValue("select collegename from pms_enterprise where id = ?",
						new Object[]{unitid}));
				zjkRyjbxxUpdate.setUniformcode(this.dbHelper.getOnlyStringValue("select substr(uniformcode,0,18) from pms_enterprise where id = ?",
						new Object[]{unitid}));
				zjkRyjbxxUpdate.setJtgzdw_uniformcode(this.dbHelper.getOnlyStringValue("select uniformcode from pms_enterprise where id = ?",
						new Object[]{unitid}));
			}else{
				zjkRyjbxxUpdate.setOrganization(pmsEnterprise.getName());
				zjkRyjbxxUpdate.setUniformcode(pmsEnterprise.getUniformcode());
			}
		}else{ //省外
			zjkRyjbxxUpdate.setOrganization(organization);
			zjkRyjbxxUpdate.setUniformcode(uniformcode);
		}

		this.merge(zjkRyjbxxUpdate);

		resObj.put("result",true);
		return resObj;
	}


	//导入专家--批量导入
	public JSONObject saveExpertByInviteByExcel(File excelFile, HttpServletRequest request) throws BiffException, IOException {
		JSONObject resObj = new JSONObject();
		List<Map> outputlist = new ArrayList<>();
		// TODO Auto-generated method stub
		Workbook rwb = Workbook.getWorkbook(excelFile);
		// 得到工作簿中的第一个表索引即为excel下的sheet1,sheet2,sheet3...
		Sheet sheet = rwb.getSheets()[0];
		int rsColumns = sheet.getColumns();// 列数
		int rsRows = sheet.getRows();// 行数
		// 第一列为列头 检查列头是否为模版excel提供的一致，如果有差别，则不能插入
		String [] srcColumnTable = new String[]{"姓名","手机","邮箱","证件类型","证件号码","单位名称","统一社会信用代码","职称","所在省","所在市"};
		for (int i = 0; i < rsColumns; i++) {
			Cell cell = sheet.getCell(i, 0);
			if (!srcColumnTable[i].equals(cell.getContents())) {
				Logger.getLogger("导入的列数不够");
				resObj.put("success",false);
				resObj.put("data","导入的列数不够");
				return resObj;
			}
		}


		for (int i = 1; i < rsRows; i++) {
			JSONObject json = new JSONObject();
			json.put("name",sheet.getCell(0, i).getContents().trim());
			json.put("mobile",sheet.getCell(1, i).getContents().trim());
			json.put("email",sheet.getCell(2, i).getContents().trim());
			json.put("certificatename",sheet.getCell(3, i).getContents().trim());
			json.put("certificateno",sheet.getCell(4, i).getContents().trim());
			json.put("unitname",sheet.getCell(5, i).getContents().trim());
			json.put("organization",sheet.getCell(5, i).getContents().trim());
			json.put("uniformcode",sheet.getCell(6, i).getContents().trim());
			json.put("title",sheet.getCell(7, i).getContents().trim());
			json.put("province",sheet.getCell(8, i).getContents().trim());
			json.put("city",sheet.getCell(9, i).getContents().trim());

			if(Util.isEoN(sheet.getCell(0, i).getContents().trim())){
				json.put("errMsg","专家姓名不得为空。");
				outputlist.add(json);
				continue;
			}

			if(Util.isEoN(sheet.getCell(1, i).getContents().trim())){
				json.put("errMsg","专家手机号不得为空。");
				outputlist.add(json);
				continue;
			}else{
				if((sheet.getCell(1, i).getContents().trim()).length()!=11){
					json.put("errMsg","专家手机号位数错误。");
					outputlist.add(json);
					continue;
				}
			}

			if(Util.isEoN(sheet.getCell(2, i).getContents().trim())){
				json.put("errMsg","专家邮箱不得为空。");
				outputlist.add(json);
				continue;
			}else{
				String str = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?.)+[a-z]{2,}$";
				if(!sheet.getCell(2, i).getContents().trim().matches(str)){
					json.put("errMsg","专家邮箱格式不正确。");
					outputlist.add(json);
					continue;
				}
			}

			if(Util.isEoN(sheet.getCell(3, i).getContents().trim())){
				json.put("errMsg","专家证件类型不得为空。");
				outputlist.add(json);
				continue;
			}else{
				String certificatename = this.dbHelper.getOnlyStringValue("select wm_concat(name) from sys_params " +
						"where parentid = 'd0edad41-4d66-4f54-968f-67e2e6962428'",null);
				if(certificatename.indexOf(sheet.getCell(3, i).getContents().trim())==-1){
					json.put("errMsg","专家证件类型有误。");
					outputlist.add(json);
					continue;
				}
			}

			if(Util.isEoN(sheet.getCell(4, i).getContents().trim())){
				json.put("errMsg","专家证件号码不得为空。");
				outputlist.add(json);
				continue;
			}

			if(Util.isEoN(sheet.getCell(5, i).getContents().trim())){
				json.put("errMsg","专家单位不得为空。");
				outputlist.add(json);
				continue;
			}

			if(Util.isEoN(sheet.getCell(6, i).getContents().trim())){
				json.put("errMsg","专家单位统一社会信用代码不得为空。");
				outputlist.add(json);
				continue;
			}

			if(Util.isEoN(sheet.getCell(7, i).getContents().trim())){
//				json.put("errMsg","专家职称不得为空。");
//				outputlist.add(json);
//				continue;
			}else{
				String title = this.dbHelper.getOnlyStringValue("select wm_concat(name) from sys_params " +
						"where parentid = '9285'",null);
				if(title.indexOf(sheet.getCell(7, i).getContents().trim())==-1){
					json.put("errMsg","专家职称有误。");
					outputlist.add(json);
					continue;
				}
			}

			if(Util.isEoN(sheet.getCell(8, i).getContents().trim())){
				json.put("errMsg","专家所在省不得为空。");
				outputlist.add(json);
				continue;
			}

			if(Util.isEoN(sheet.getCell(9, i).getContents().trim())){
				json.put("errMsg","专家所在市不得为空。");
				outputlist.add(json);
				continue;
			}else{
				if("广州市".equals(sheet.getCell(9, i).getContents().trim())){
					json.put("errMsg","广州市内专家请按正常专家注册流程进行。");
					outputlist.add(json);
					continue;
				}
			}

			//判断单位是否在系统
			String unitid = this.dbHelper.getOnlyStringValue("select id from pms_enterprise " +
					"where uniformcode = ? ",new Object[]{sheet.getCell(6, i).getContents().trim()});
			if(Util.isEoN(unitid)){
				unitid = "SWUNIT-123SADSA13-1231ASD";
			}

			json.put("unitid",unitid);

			JSONObject resultObj = this.saveExpertByInvite(json);

			if(resultObj.getBoolean("result")){
				continue;
			}else{
				json.put("errMsg",resultObj.get("errMsg")+"");
				outputlist.add(json);
			}
		}

		if(outputlist.size()>0){
			Map resMap = this.importExpertResult(outputlist,request);
			if("true".equals(resMap.get("success"))){
				resObj.put("success",true);
				resObj.put("data",resMap.get("data")+"");
			}else{
				resObj.put("success",false);
				resObj.put("data","导出失败");
			}
		}else{
			resObj.put("success",true);
		}

		return resObj;
	}


	public Map importExpertResult(List<Map> list, HttpServletRequest request){
		Map resMap = new HashMap<>();
		try {
			// 创建文档对象(其他对象都是通过文档对象创建)
			HSSFWorkbook wb = new HSSFWorkbook();
			// 创建HSSFSheet对象(excel的表单对象)
			HSSFSheet sheet = wb.createSheet("导出清单");
			// 创建样式对象（HSSFCellStyle ）
			HSSFCellStyle cellStyle = wb.createCellStyle();
			// 创建字体对象
			HSSFFont font = wb.createFont();
			// 将字体对象赋给单元格样式对象
			cellStyle.setFont(font);

			//获取数据源
			String[] key1 = new String[] {};
			String[] key = new String[] {};

			key1 = new String[] {"姓名","手机","邮箱","证件类型","证件号码",
					"单位名称","统一社会信用代码","职称","所在省","所在市","错误原因"};
			key = new String[] {"name","mobile","email","certificatename","certificateno",
					"unitname","uniformcode","title","province","city","errMsg"};

			JSONArray jsonArray = JSONArray.fromObject(list);

			// 在sheet里创建第一行，参数为行索引
			HSSFRow row1 = sheet.createRow(0);
			HSSFRow row;
			for (int i = 0; i < key1.length; i++) {
				HSSFCell cell = row1.createCell(i);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(key1[i]);
			}
			for (int j = 1; j < jsonArray.size() + 1; j++) {
				row = sheet.createRow(j);
				JSONObject job = jsonArray.getJSONObject(j - 1);
				for (int s = 0; s < key.length; s++) {
					String mycell = key[s];
					if (job.containsKey(mycell)) {
						row.createCell(s).setCellValue(job.getString(mycell));
					}
				}
			}
			String publishPath = request.getRealPath("/");
			long time = new Date().getTime();
			String excelName = "ExpertFailList"+time+".xls";
			File file = new File(Util.GetFileRealPath("D://helpdocs//importExpertResult//"));
			if(!file.exists()&&!file.isDirectory()){
				//说明文件夹不存在
				file.mkdir();
			}
//			FileOutputStream fout = new FileOutputStream(publishPath+"/helpdocs/importExpertResult/"+excelName);
			FileOutputStream fout = new FileOutputStream(Util.GetFileRealPath("D://helpdocs//importExpertResult//"+excelName));
			String recordid = this.sysDownRecordService.commonSaveOperation(request, String.valueOf(time),"定向邀请专家导入结果导出", excelName,Util.GetFileRealPath("D://helpdocs//importExpertResult//"+excelName));
			wb.write(fout);
			fout.close();
			resMap.put("success","true");
			resMap.put("data",recordid);

		}catch (Exception e){
			resMap.put("success","false");
            // log.error("importExpertResult报错"+e);
			// throw new BusinessException(ErrorCodeEnum.UPDATE_ERROR, e);
		}

		return resMap;
	}




	/**
	 * 【专家注册】 -- 执行流程--省外专家
	 */
	public JSONObject executeFlow_SW(JSONObject json){
		JSONObject resJson = new JSONObject();
		String id = json.get("id")+"";
		String result = json.get("result")+"";//上报 or 通过 or 退回 or 不通过
		String operationcontent = json.get("operationcontent")+"";
		if(Util.isEoN(id)||Util.isEoN(result)||Util.isEoN(operationcontent)){
			return Util.dealResJson(resJson,false,"前台参数不完整");
		}
		ZjkRyjbxxUpdate main = this.findById(id);
		String nowStateid = main.getMinicurrentstateid();//当前流程状态id
		Date nowDate = new Date();
		SysUser expertUser = this.sysUserService.findById(id);
		//判断操作结果是不是退回，是的话都是退回给用户
		if("退回".equals(result)){
			//审核人退回
			main.setApprovestate("2");
			main.setMinicurrentstateid("SWEXPERT_ZC_1");
			main.setMinicurrentstate("省外专家注册:专家填报");

			//发短信通知
			SysUser sysUser = this.sysUserService.findById(id);
			JSONObject msgJson = new JSONObject();
			msgJson.put("cellphones", sysUser.getMobile());
			msgJson.put("content", sysUser.getName()+"专家：您好！经审核您的入库申请未通过，具体原因您可以登录“贵州省科技计划项目管理平台”-“专家信息维护”处查看。（贵州省科学技术厅）");
			msgJson.put("bussiness", "省外专家注册退回");
			// msgUtil.sendMsg(msgJson);
		}else if("不通过".equals(result)){
			//审核人不通过
			main.setMinicurrentstateid("SWEXPERT_ZC_9");
			main.setMinicurrentstate("省外专家注册:不通过");
		}else if("上报".equals(result)){
			//上报
			main.setSubmitdate(nowDate);
			main.setRegisterdate(Util.isEoN(main.getRegisterdate())?nowDate:main.getRegisterdate());
			main.setApprovestate("0");
			main.setMinicurrentstateid("SWEXPERT_ZC_2");
			main.setMinicurrentstate("省外专家注册:科技厅审核");
		}else if("通过".equals(result)){
			//审核人通过
			switch (nowStateid){
//				case "SWEXPERT_ZC_2":
//					//省外专家注册:湾区初审
//					main.setMinicurrentstateid("SWEXPERT_ZC_3");
//					main.setMinicurrentstate("省外专家注册:湾区复审");
//					break;
				case "SWEXPERT_ZC_2":
					//省外专家注册:科技厅审核
					main.setMinicurrentstateid("SWEXPERT_ZC_100");
					main.setMinicurrentstate("省外专家注册:完成");
					main.setApprovestate("1");
					main.setIscanpick("1");
//					main.setRkflag("1");
					main.setZero_reason("");
					main.setGsdate(new Date());

					SysUser user = this.sysUserService.findById(id);
					user.setUserstate(1);
					user.setApprovestate("1");
					// user.setEid(id);

					SysRole sysRole = this.sysRoleService.findbyRoleCode("zhuanjiark");//专家入库角色
					if (sysRole != null) {
						user.getSysIdentitys().get(0).setSysRole(sysRole);
						user.getSysIdentitys().get(0).setEnabled(1);
					}

					break;
				default:
					break;
			}
		}
		//人工造流转记录
		FlowRecord f = new FlowRecord();
		f.setId(Util.NewGuid());
		f.setCurrentflowpointname(main.getMinicurrentstate());
		f.setCurrentflowpointid(main.getMinicurrentstateid());
		f.setCurrentoperationname(result);
		f.setCurrentoperationresultname(result);
		if("上报".equals(result)){
			f.setOperationcontent("上报成功，目前处于科技厅审核中");
		}else{
			f.setOperationcontent(operationcontent);
		}

		//各个层获取用户
		SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
		f.setCurrentoperatorid(user.getId());
		f.setCurrentoperatorname(user.getName());
		f.setOperationtime(nowDate);
		f.setSourceid(id+"ZJXX");
		this.flowRecordService.merge(f);
		//保存主表
		main.setUpdatelasttime(new Date());
		this.merge(main);

		return Util.dealResJson(resJson,true);
	}



	/**
	 * 【专家变更】 -- 执行流程--变更--省外专家
	 */
	public JSONObject executeFlow_BG_SW(JSONObject json){
		JSONObject resJson = new JSONObject();
		String id = json.get("id")+"";
		String result = json.get("result")+"";//上报 or 通过 or 退回 or 不通过
		String operationcontent = json.get("operationcontent")+"";
		if(Util.isEoN(id)||Util.isEoN(result)||Util.isEoN(operationcontent)){
		return Util.dealResJson(resJson,false,"前台参数不完整");
		}
		ZjkRyjbxxUpdate main = this.findById(id);
		String nowStateid = main.getMinicurrentstateid();//当前流程状态id
		Date nowDate = new Date();
		//判断操作结果是不是退回，是的话都是退回给用户
		if("退回".equals(result)){
			//审核人退回
			main.setApprovestate("2");
			main.setMinicurrentstateid("SWEXPERT_BG_1");
			main.setMinicurrentstate("省外专家变更:专家填报");
			main.setIscanpick("1");
			// updateInfoFromTemp(id, "退回");

			//发短信通知
			SysUser sysUser = this.sysUserService.findById(id);
			JSONObject msgJson = new JSONObject();
			msgJson.put("cellphones", sysUser.getMobile());
			msgJson.put("content", sysUser.getName()+"专家：您好！经审核您的入库申请未通过，具体原因您可以登录“贵州省科技计划项目管理平台”-“专家信息维护”处查看。（贵州省科学技术厅）");
			msgJson.put("bussiness", "省外专家变更退回");
			// msgUtil.sendMsg(msgJson);
		}else if("不通过".equals(result)){
			//审核人不通过
			main.setMinicurrentstateid("SWEXPERT_BG_9");
			main.setMinicurrentstate("省外专家变更:不通过");
			// updateInfoFromTemp(id, "不通过");
		}else if("上报".equals(result)){
			//上报
			main.setSubmitdate(nowDate);
			main.setApprovestate("0");
			main.setIscanpick("0");
			main.setZero_reason("专家变更流程中");
			main.setMinicurrentstateid("SWEXPERT_BG_2");
			main.setMinicurrentstate("省外专家变更:科技厅审核");
		}else if("通过".equals(result)){
			//审核人通过
			switch (nowStateid){
//				case "SWEXPERT_BG_2":
//					//专家变更:湾区初审
//					main.setMinicurrentstateid("SWEXPERT_BG_3");
//					main.setMinicurrentstate("省外专家变更:湾区复审");
//					break;
				case "SWEXPERT_BG_2":
					//专家变更:科技厅审核
					main.setMinicurrentstateid("SWEXPERT_BG_100");
					main.setMinicurrentstate("省外专家变更:完成");
					main.setApprovestate("1");
					main.setIscanpick("1");
					main.setZero_reason("");
					this.runSql("update sys_user set eid = ? where id = ?",new Object[]{id,id});
					// updateInfoFromTemp(id, "通过");

					break;
				default:
					break;
			}
		}
		//人工造流转记录
		FlowRecord f = new FlowRecord();
		f.setId(Util.NewGuid());
		f.setCurrentflowpointname(main.getMinicurrentstate());
		f.setCurrentflowpointid(main.getMinicurrentstateid());
		f.setCurrentoperationname(result);
		f.setCurrentoperationresultname(result);
		if("上报".equals(result)){
			f.setOperationcontent("上报成功，目前处于科技厅审核中");
		}else{
			f.setOperationcontent(operationcontent);
		}

		//各个层获取用户
		SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
		f.setCurrentoperatorid(user.getId());
		f.setCurrentoperatorname(user.getName());
		f.setOperationtime(nowDate);
		f.setSourceid(id+"ZJXX");
		this.flowRecordService.merge(f);
		//保存主表
		main.setUpdatelasttime(new Date());
		this.merge(main);

		return Util.dealResJson(resJson,true);
	}


	/**
	 * 【专家】 -- 根据所在省查找专家
	 */
	public JSONObject findExpertByProvince(JSONObject json){
		JSONObject resObj = new JSONObject();
		JSONArray paramList = new JSONArray();

		String insql = " and 1=1 ";

		if(!Util.isEoN(json.get("province")+"")){
			if(!"全国".equals(json.get("province")+"")){
				insql = " and t.organization_province = ? ";
				paramList.add(json.get("province")+"");
			}else{
				insql = " and 1=1 ";
			}
		}

		String sql = "select t.name,t.organization,t.jtgzdw,t.id_type,t.id_number," +
				"t.mobile_telephone,t.position_title,t.gender,t.birthday " +
				"from zjk_ryjbxx_update t " +
				"where t.rkflag = '1' and t.organization_province is not null " +
				"and length(t.birthday) = 10 " +insql;

		//获取总数
		String totalCount = this.dbHelper.getOnlyStringValue("select count(as_count.id) from ( " + sql + " ) as_count", paramList.toArray());
		//分页处理
		JSONObject page = json.getJSONObject("page");

		sql = "select levelone.* from(select leveltwo.*,rownum rn from (" + sql + ") leveltwo ) levelone where levelone.rn < ? and  levelone.rn > ?";

		paramList.add(Integer.parseInt(page.get("currentPage").toString()) * Integer.parseInt(page.get("pageSize").toString()) + 1);
		paramList.add((Integer.parseInt(page.get("currentPage").toString()) - 1) * Integer.parseInt(page.get("pageSize").toString()));

		//返回
		resObj.put("data", this.dbHelper.getRows(sql, paramList.toArray()));
		resObj.put("totalCount", totalCount);

		return resObj;
	}


	/**
	 * 【信用问题专家出库】 -- 执行流程
	 */
	public JSONObject executeFlow_CK(JSONObject json,SysUser sysUser){
		JSONObject resJson = new JSONObject();
		String id = json.get("id")+"";
		String result = json.get("result")+"";//提交 or 通过 or 退回 or 不通过
		String operationcontent = json.get("operationcontent")+"";
		if(Util.isEoN(id)||Util.isEoN(result)||Util.isEoN(operationcontent)){
			return Util.dealResJson(resJson,false,"前台参数不完整");
		}
		ZjkRyjbxxUpdate main = this.findById(id);
		String nowStateid = main.getMinicurrentstateid();//当前流程状态id
		Date nowDate = new Date();
		//判断操作结果是不是退回，是的话都是退回给用户
		if("退回".equals(result)){
			//退回
			main.setMinicurrentstate("信用问题出库:待上报");
			main.setMinicurrentstateid("EXPERT_CK_1");
		}else if("不通过".equals(result)){
			//审核人不通过
			main.setMinicurrentstate("信用问题出库:不通过");
			main.setMinicurrentstateid("EXPERT_CK_5");
		}else if("上报".equals(result)){
			//资管处经办人提交
			main.setMinicurrentstate("信用问题出库:资管处处长审核");
			main.setMinicurrentstateid("EXPERT_CK_2");
		}else if("通过".equals(result)){
			//审核人通过
			switch (nowStateid) {
				case "EXPERT_CK_2":
					main.setMinicurrentstate("信用问题出库:已出库");
					main.setMinicurrentstateid("EXPERT_CK_3");

					JSONObject params = new JSONObject();
					params.put("zjid",id);
					params.put("reason","列入科研失信名单");
					params.put("outtype","zgc");
					// this.zjkOutService.zjOutFunc(params);
					break;
				default:
					break;
			}
		}
		//人工造流转记录
		FlowRecord f = new FlowRecord();
		f.setId(Util.NewGuid());
		f.setCurrentflowpointname(main.getMinicurrentstate());
		f.setCurrentflowpointid(main.getMinicurrentstateid());
		f.setCurrentoperationname(result);
		f.setCurrentoperationresultname(result);
		f.setOperationcontent(operationcontent);


		//各个层获取用户
		SysUser user = this.sysUserService.findById(AuthUtil.getUserId());
		f.setCurrentoperatorid(user.getId());
		f.setCurrentoperatorname(user.getName());
		f.setOperationtime(nowDate);
		f.setSourceid(id+"ZJXX");
		this.flowRecordService.merge(f);
		//保存主表
		main.setUpdatelasttime(new Date());
		this.merge(main);

		return Util.dealResJson(resJson,true);
	}



	/**
	 * 【定向邀请专家】 -- 录入后发短信通知
	 */
	public Boolean sendMessage(String id) {
		// DESEncrypt des = new DESEncrypt();
		ZjkRyjbxxUpdate main = this.findById(id);
		SysUser sysUser = this.sysUserService.findById(id);

		JSONObject msgJson = new JSONObject();
		// msgJson.put("cellphones", main.getMobile_telephone());
		// msgJson.put("content", main.getName()+"专家：您好！诚邀您加入广州市科技专家库。请登陆广州科技大脑【https://gzsti.gzsi.gov.cn】（点击右上角“登录”按钮进行登录）" +
		// 		// "您的账号："+sysUser.getUserid()+"，密码："+des.decrypt(sysUser.getPassword())+" 。系统将在您补充完善个人信息后结束本次入库流程。感谢您对广州科技事业的支持！（广州市科技局）");
		// "您的账号："+sysUser.getUserid()+"，密码："+SmUtilAll.decryptAll(sysUser.getPassword())+" 。系统将在您补充完善个人信息后结束本次入库流程。感谢您对广州科技事业的支持！（广州市科技局）");
		// msgJson.put("bussiness", "定向邀请专家通知短信");
		// // JSONObject msgResJson = msgUtil.sendMsg(msgJson);
		//
		// if(msgResJson != null && msgResJson.has("flag") && msgResJson.getBoolean("flag") == true) {
		// 	main.setMessageflag("1");
		// }

		this.merge(main);

		return true;
	}


	public Boolean saveCnsFlag(String zjkryjbxxupdateid) {
		if(Util.isEoN(zjkryjbxxupdateid)) {
			// throw new BusinessException("未获取到专家信息");
		}
		ZjkRyjbxxUpdate zjkRyjbxxUpdate = this.findById(zjkryjbxxupdateid);
		if(Util.isEoN(zjkRyjbxxUpdate)) {
			// throw new BusinessException("未获取到专家信息");
		}
		zjkRyjbxxUpdate.setCnsflag("同意");
		zjkRyjbxxUpdate.setCnsdate(new Date());
		this.merge(zjkRyjbxxUpdate);
		return true;
	}

	/**
	 * 个人数据仓变更时，专家的iscanpick标记为0
	 * @param sourceid
	 * @param flag
	 */
	public void updateIscanpickForFlow(String sourceid, String flag){
		ZjkRyjbxxUpdate zjkRyjbxxUpdate = this.findByPid(sourceid);
		if (flag.equals("null")){
			zjkRyjbxxUpdate.setIscanpick("0");
			zjkRyjbxxUpdate.setZero_reason("个人数据仓变更流程中");
		} else {
			zjkRyjbxxUpdate.setIscanpick(flag);
		}
		this.merge(zjkRyjbxxUpdate);
	}


	public List findRandomExpert(JSONObject obj) {
		JSONArray paramsList = new JSONArray();
		String insql1 = " and 1=1 ";
		String insql2 = " and 1=1 ";
		String insql3 = " and 1=1 ";
		String insql4 = " and 1=1 ";
		String insql5 = " and 1=1 ";
		String insql6 = " and 1=1 ";
		String insql7 = " and 1=1 ";
		String insql8 = " and 1=1 ";
		String insql9 = " and 1=1 ";
		String insql10 = " and 1=1 ";
		String insql11 = " and 1=1 ";
		String insql_xkfl = " and 1=1 ";

		if(Util.isEoN(obj.get("cqnum")+"")) {
			// throw new BusinessException("未获取到抽取数量");
		}

		if(!Util.isEoN(obj.get("subjecttype")+"")){
			insql_xkfl = " and xkfl.subjecttype = ? ";
			paramsList.add(obj.get("subjecttype")+"");
		}

		if(!Util.isEoN(obj.get("experttype")+"")) {
			insql1 = " and t.expert_type like ? ";
			paramsList.add("%"+obj.get("experttype")+"%");
		}
		if(!Util.isEoN(obj.get("tzfx")+"")) {
			insql11 = " and info.investment like ? ";
			paramsList.add("%"+obj.get("tzfx")+"%");
		}

		if(!Util.isEoN(obj.get("region")+"")) {
			if("市内".equals(obj.get("region")+"")) {
				insql2 = " and t.organization_city = '广州市' ";
			} else {
				insql2 = " and (t.organization_city <> '广州市' or t.organization_city is null) ";
			}
		}

		if(!Util.isEoN(obj.get("position_title"))) {
			if("正高级".equals(obj.get("position_title")+"")) {
				insql3 = " and t.position_title = '正高级' ";
			} else if("副高级及以上".equals(obj.get("position_title"))) {
				insql3 = " and (t.position_title = '正高级' or t.position_title = '副高级') ";
			} else {
				insql3 = " and 1=1 ";
			}

		}

		if(!Util.isEoN(obj.get("startdate")+"")){
			insql4 = " and t.gsdate >= ? ";
			paramsList.add(obj.get("startdate")+"");
		}

		if(!Util.isEoN(obj.get("enddate")+"")){
			insql5 = " and t.gsdate <= ? ";
			paramsList.add(obj.get("enddate")+"");
		}

		if(!Util.isEoN(obj.get("expertname")+"")) {
			insql6 = " and t.name like ? ";
			paramsList.add("%"+obj.get("expertname")+"%");
		}

		if(!Util.isEoN(obj.get("id_number")+"")) {
			insql7 = " and t.id_number = ? ";
			paramsList.add(obj.get("id_number"));
		}

		if(!Util.isEoN(obj.get("creditcode")+"")) {
			insql8 = " and (t.uniformcode = ? or t.jtgzdw_uniformcode = ?) ";
			paramsList.add(obj.get("creditcode"));
			paramsList.add(obj.get("creditcode"));
		}

		if(!Util.isEoN(obj.get("rktype")+"")) {
			if("共享".equals(obj.get("rktype")+"")){
				insql9 = " and t.drbj = '2' ";
			}else if("邀请".equals(obj.get("rktype")+"")){
				insql9 = " and t.drbj = '5' ";
			}else{
				insql9 = " and t.drbj is null ";
			}
		}

		List<String> code_arr = new ArrayList<>();
		if(!Util.isEoN(obj.get("subjecttype"))) {
			code_arr = obj.getJSONArray("code_arr");
		}

		if(code_arr.size() != 0 && !Util.isEoN(obj.get("subjecttype"))) {
			String subjectonecodeStr = Util.sqlSplicingForInStatement(JSONArray.fromObject(code_arr), "tt.subjectonecode");
			String subjecttwocodeStr = Util.sqlSplicingForInStatement(JSONArray.fromObject(code_arr), "tt.subjecttwocode");
			String subjectthreecodeStr = Util.sqlSplicingForInStatement(JSONArray.fromObject(code_arr), "tt.subjectthreecode");
			String subjectfourcodeStr = Util.sqlSplicingForInStatement(JSONArray.fromObject(code_arr), "tt.subjectfourcode");
			if(!Util.isEoN(subjectonecodeStr)) {
				insql10 = "   and exists (select person_id zjkryjbxxupdateid from ZJK_RYJBXXXKFL_UPDATE tt where tt.person_id = t.id and tt.subjecttype = ? and (( " + subjectonecodeStr + " ) or ( " + subjecttwocodeStr + " ) or ( " + subjectthreecodeStr + " ) or ( " + subjectfourcodeStr + " )) ) ";
				paramsList.add(obj.get("subjecttype"));
				paramsList.addAll(JSONArray.fromObject(code_arr));
				paramsList.addAll(JSONArray.fromObject(code_arr));
				paramsList.addAll(JSONArray.fromObject(code_arr));
				paramsList.addAll(JSONArray.fromObject(code_arr));
			}
		}


		//抽取数量
		paramsList.add(Integer.parseInt(obj.get("cqnum")+""));

		String sql = "select * from ( " +
				"select distinct (t.id),t.userid,t.unitid,t.drbj,t.jtgzdw,t.expert_type,t.name,t.organization," +
				"to_char(t.gsdate,'yyyy-MM-dd HH24:mi:ss') as gsdate,e1.csdate,e2.fsdate,t.mobile_telephone,t.position_title, " +
				"(case when t.organization_city = '广州市' then '市内' else '市外' end) as region, " +
				"wm_concat(xkfl.subjectendname) as subjectname,wm_concat(xkfl.subjectendcode) as subjectcode " +
				"from zjk_ryjbxx_update t " +
				"left join zjk_ryjbxx_info_update info on t.id = info.zjkryjbxxupdateid "+
				"left join zjk_ryjbxxxkfl_update xkfl on xkfl.person_id = t.id " + insql_xkfl +
				"left join (select sourceid,max(operationtime) as csdate from fl_flowrecord " +
				"where currentflowpointname = '专家注册:湾区初审' group by sourceid) e1 on e1.sourceid = t.id||'ZJXX' " +
				"left join (select sourceid,max(operationtime) as fsdate from fl_flowrecord " +
				"where currentflowpointname = '专家注册:湾区复审' group by sourceid) e2 on e2.sourceid = t.id||'ZJXX' " +
				"where 1=1 " + insql1 + insql2 + insql3 + insql4 + insql5 + insql6 + insql7 + insql8 + insql9 + insql10 +insql11+
				"and t.rkflag = '1' and t.iscanpick = '1' " +
				"group by t.id,t.userid,t.unitid,t.drbj,t.jtgzdw,t.expert_type,t.name,t.organization, " +
				"to_char(t.gsdate,'yyyy-MM-dd HH24:mi:ss'),e1.csdate,e2.fsdate,t.mobile_telephone,t.position_title, " +
				"(case when t.organization_city = '广州市' then '市内' else '市外' end) " +
				"order by dbms_random.value) " +
				"where rownum <= ?";

		List<Map> list = this.dbHelper.getRows(sql, paramsList.toArray());

		return list;
	}


	public JSONObject getRandomCondition(String batchid) {
		String sql = "select t.cqcontent from zjk_cqbatchparams t where t.batchid = ?";
		String json_str = this.dbHelper.getOnlyStringValue(sql,new Object[]{batchid});

		if(Util.isEoN(json_str)){
			return null;
		}else{
			return JSONObject.fromObject(json_str);
		}
	}



	// public void saveRandomCondition(JSONObject jsonObject){
	// 	String batchid = jsonObject.get("batchid")+"";
	// 	ZjkCqbatchParams zjkCqbatchParams = this.zjkCqbatchParamsService.findById(batchid);
	//
	// 	if(zjkCqbatchParams==null){
	// 		zjkCqbatchParams = new ZjkCqbatchParams();
	// 		zjkCqbatchParams.setId(batchid);
	// 	}
	//
	// 	zjkCqbatchParams.setBatchid(batchid);
	// 	zjkCqbatchParams.setCqcontent(jsonObject.toString());
	// 	this.zjkCqbatchParamsService.merge(zjkCqbatchParams);
	//
	// }


	public List<Map> getRandomExpert(String batchid) {
		String sql = "select t.id,t.userid,t.unitid,t.drbj,t.jtgzdw,t.expert_type,t.name,t.organization, " +
				"to_char(t.gsdate,'yyyy-MM-dd HH24:mi:ss') as gsdate,e1.csdate,e2.fsdate,t.mobile_telephone,t.position_title, " +
				"(case when t.organization_city = '广州市' then '市内' else '市外' end) as region, " +
				"wm_concat(xkfl.subjectendname) as subjectname,wm_concat(xkfl.subjectendcode) as subjectcode " +
				"from zjk_expertinbatch w " +
				"left join zjk_ryjbxx_update t on t.id = w.eid " +
				"left join zjk_ryjbxxxkfl_update xkfl on xkfl.person_id = t.id and xkfl.subjecttype = 'jsly'  " +
				"left join (select sourceid,max(operationtime) as csdate from fl_flowrecord " +
				"where currentflowpointname = '专家注册:湾区初审' group by sourceid) e1 on e1.sourceid = t.id||'ZJXX' " +
				"left join (select sourceid,max(operationtime) as fsdate from fl_flowrecord " +
				"where currentflowpointname = '专家注册:湾区复审' group by sourceid) e2 on e2.sourceid = t.id||'ZJXX' " +
				"where w.bid = ? " +
				"group by t.id,t.userid,t.unitid,t.drbj,t.jtgzdw,t.expert_type,t.name,t.organization, " +
				"to_char(t.gsdate,'yyyy-MM-dd HH24:mi:ss'),e1.csdate,e2.fsdate,t.mobile_telephone,t.position_title, " +
				"(case when t.organization_city = '广州市' then '市内' else '市外' end) ";
		List<Map> list = this.dbHelper.getRows(sql,new Object[]{batchid});

		return list;
	}

	//
	// public void saveRandomExpert(JSONObject jsonObject) {
	// 	String batchid = jsonObject.get("batchid")+"";
	// 	JSONArray expertArr = jsonObject.getJSONArray("expertArr");
	//
	// 	try {
	// 		dbHelper.runSql("delete from zjk_expertinbatch where bid = ?", new Object[]{batchid});
	// 	} catch (SQLException e) {
	//
	// 	}
	//
	// 	if(expertArr.size()>0){
	// 		for (int i = 0; i < expertArr.size(); i++) {
	// 			ZjkExpertinbatch zjkExpertinbatch = new ZjkExpertinbatch();
	// 			zjkExpertinbatch.setId(Util.NewGuid());
	// 			zjkExpertinbatch.setEid(expertArr.get(i)+"");
	// 			zjkExpertinbatch.setBid(batchid);
	// 			this.zjkExpertinbatchService.merge(zjkExpertinbatch);
	// 		}
	// 	}
	// }



	/**
	 * 【专家抽取】 -- 执行流程
	 */
	// public JSONObject executeFlow_ZJJC(JSONObject json){
	// 	JSONObject resJson = new JSONObject();
	// 	String id = json.get("id")+"";
	// 	String result = json.get("result")+"";//提交 or 通过 or 退回 or 不通过
	// 	String operationcontent = json.get("operationcontent")+"";
	// 	if(Util.isEoN(id)||Util.isEoN(result)||Util.isEoN(operationcontent)){
	// 		return Util.dealResJson(resJson,false,"前台参数不完整");
	// 	}
	// 	ZjkCqbatch main = this.zjkCqbatchService.findById(id);
	// 	String nowStateid = main.getMinicurrentstateid();//当前流程状态id
	//
	// 	Date nowDate = new Date();
	// 	//判断操作结果是不是退回，是的话都是退回给用户
	// 	if("退回".equals(result)){
	// 		//退回
	// 		main.setMinicurrentstate("专家抽取批次:待上报");
	// 		main.setMinicurrentstateid("EXPERT_JC_1");
	// 	}else if("不通过".equals(result)){
	// 		//审核人不通过
	// 		main.setMinicurrentstate("专家抽取批次:不通过");
	// 		main.setMinicurrentstateid("EXPERT_JC_4");
	// 	}else if("上报".equals(result)){
	// 		//资管处经办人提交
	// 		main.setMinicurrentstate("专家抽取批次:资管处处长审核");
	// 		main.setMinicurrentstateid("EXPERT_JC_2");
	// 	}else if("通过".equals(result)){
	// 		//审核人通过
	// 		switch (nowStateid) {
	// 			case "EXPERT_JC_2":
	// 				main.setMinicurrentstate("专家抽取批次:审核通过");
	// 				main.setMinicurrentstateid("EXPERT_JC_3");
	// 				break;
	// 			default:
	// 				break;
	// 		}
	// 	}
	// 	//人工造流转记录
	// 	FlowRecord f = new FlowRecord();
	// 	f.setId(Util.NewGuid());
	// 	f.setCurrentflowpointname(main.getMinicurrentstate());
	// 	f.setCurrentflowpointid(main.getMinicurrentstateid());
	// 	f.setCurrentoperationname(result);
	// 	f.setCurrentoperationresultname(result);
	// 	f.setOperationcontent(operationcontent);
	//
	//
	// 	//各个层获取用户
	// 	SecurityUser securityUser = SecurityUserLocal.getSecurityUser();
	// 	SysUser user = securityUser.getUser();
	// 	f.setCurrentoperatorid(user.getId());
	// 	f.setCurrentoperatorname(user.getName());
	// 	f.setOperationtime(nowDate);
	// 	f.setSourceid(id);
	// 	this.flowRecordService.merge(f);
	// 	//保存主表
	// 	main.setUpdatelasttime(new Date());
	// 	this.zjkCqbatchService.merge(main);
	//
	// 	return Util.dealResJson(resJson,true);
	// }


	public Boolean judgeExpertIsReviewing(JSONObject jsonObject) {

		String expertid = jsonObject.get("expertid") + "";
		if(Util.isEoN(expertid)) {
			// throw new BusinessException("专家信息有误");
		}
		String sql = "select distinct e.id  " +
				"from ZJK_BATCH_PLAN p " +
				"    inner join ZJK_BATCH_EXPERT e on e.PLANID = p.ID " +
				"    left join REV_COMMON_TEAM t on t.PLANID = p.ID and t.id = e.groupid " +
				"    left join REV_COMMON_EXPERTINTEAM ei on ei.PLANID = p.ID and ei.TID = e.GROUPID and e.EXPERTID = ei.EID " +
				"where e.EXPERTID = ? " +
				"  and (ei.STATUS not in ('不参加','已替换') or ei.STATUS is null ) " +
				"  AND (t.STATUS != '评审完成' or t.STATUS is NULL)";
		List<Map> rows = dbHelper.getRows(sql, new Object[]{expertid});
		if(rows.size() > 0) {
			// throw new BusinessException("当前专家处于评审过程中，无法审核");
		}
		return true;
	}

	//流程脚本 用户信息变更 如果是有效专家，临时可抽标记为0 审核通过后 标记为1
	public void updateIscanpickForegisterbgR(String bimainid, String iscanpickflag){
		ZjkRyjbxxUpdate zjkRyjbxxUpdate = this.findById(bimainid);
        if (zjkRyjbxxUpdate != null) {
            if ("0".equals(iscanpickflag)) {
                if (Optional.ofNullable(zjkRyjbxxUpdate.getIscanpick()).filter(m -> m.equals("1")).isPresent()) {
                    zjkRyjbxxUpdate.setIscanpick("0");
                    zjkRyjbxxUpdate.setZero_reason("用户信息变更可能涉及单位");
                    this.merge(zjkRyjbxxUpdate);
                    this.sysOperationrecordService.commonSaveOperation(bimainid, "用户信息变更", "用户信息变更涉及，且他是专家，临时iscanpick标0");
                }
            }

            if ("1".equals(iscanpickflag)) {
                if (Optional.ofNullable(zjkRyjbxxUpdate.getIscanpick()).filter(m -> m.equals("1")).isPresent() &&
                        Optional.ofNullable(zjkRyjbxxUpdate.getZero_reason()).filter(m -> m.equals("用户信息变更可能涉及单位")).isPresent()) {
                    zjkRyjbxxUpdate.setIscanpick("1");
                    this.merge(zjkRyjbxxUpdate);
                    this.sysOperationrecordService.commonSaveOperation(bimainid, "用户信息变更", "用户信息变更通过，涉及，且他是专家，临时iscanpick标1");
                }
            }
        }
	}

	public static void main(String[] args) {
		ZjkRyjbxxUpdate zjkRyjbxxUpdate = null;
		ZjkRyjbxxUpdate zjkRyjbxxUpdate2 = new ZjkRyjbxxUpdate();
		zjkRyjbxxUpdate2.setIscanpick(null);
		zjkRyjbxxUpdate2.setId("111");
		ZjkRyjbxxUpdate zjkRyjbxxUpdate3 = new ZjkRyjbxxUpdate();
		zjkRyjbxxUpdate3.setIscanpick("1");

		boolean present = Optional.ofNullable(zjkRyjbxxUpdate).map(m -> m.getIscanpick()).filter(m -> m.equals("1")).isPresent();
		boolean present2 = Optional.ofNullable(zjkRyjbxxUpdate2).map(m -> m.getIscanpick()).filter(m -> m.equals("1")).isPresent();
		boolean present3 = Optional.ofNullable(zjkRyjbxxUpdate3).map(m -> m.getIscanpick()).filter(m -> m.equals("1")).isPresent();


		if (Optional.ofNullable(zjkRyjbxxUpdate).map(m -> m.getIscanpick()).isPresent()) {
			System.out.println(111);
		}
		if (Optional.ofNullable(zjkRyjbxxUpdate3).map(m -> m.getIscanpick()).isPresent()) {
			System.out.println(333);
		}
		//if (Optional.ofNullable(zjkRyjbxxUpdate.getIscanpick()).isPresent()) {
		//	System.out.println(111);
		//}
		//if (Optional.ofNullable(zjkRyjbxxUpdate3.getIscanpick()).isPresent()) {
		//	System.out.println(222);
		//}
	}


	//统计人才称号
	public List<Map> findExpertRcch(){
		String sql = "select sum(case when e.topexpert is not null then 1 else 0 end) as topexpert, " +
				"sum(case when e.nationalexpert is not null then 1 else 0 end) as nationalexpert, " +
				"sum(case when e.provincialexpert is not null then 1 else 0 end) as provincialexpert, " +
				"sum(case when e.strategyexpert is not null then 1 else 0 end) as strategyexpert, " +
				"sum(case when e.foreignexpert is not null then 1 else 0 end) as foreignexpert, " +
				"sum(case when e.gjhz is not null then 1 else 0 end) as gjhzexpert, " +
				"sum(case when t.isqyzj is not null then 1 else 0 end) as qyexpert, " +
				"sum(case when (t.expert_type = '技术类专家' or t.expert_type like '%技术类专家%') then 1 else 0 end) as techexpert, " +
				"sum(case when (t.expert_type = '管理类专家' or t.expert_type like '%管理类专家%') then 1 else 0 end) as manageexpert, " +
				"sum(case when t.expert_type = '经济类专家' then 1 else 0 end) as economyexpert, " +
				"sum(case when t.expert_type = '其他类专家' then 1 else 0 end) as otherexpert " +
				"from zjk_ryjbxx_update t " +
				"left join zjk_ryjbxx_info_update e on e.zjkryjbxxupdateid = t.id " +
				"where t.rkflag = '1' ";

		List<Map> list = this.dbHelper.getRows(sql,null);
		return list;
	}


	public List<Map> findExpertRcchDetail(String type){
		String sql = "";
		if("topexpert".equals(type)){
			sql = "select sum(case when e.expert_nbej_hb is not null then 1 else 0 end) expert_nbej_hb, " +
					"sum(case when e.expert_tlj_hb is not null then 1 else 0 end) expert_tlj_hb, " +
					"sum(case when e.expert_fezj_hb is not null then 1 else 0 end) expert_fezj_hb, " +
					"sum(case when e.expert_plzkj_hb is not null then 1 else 0 end) expert_plzkj_hb, " +
					"sum(case when e.expert_zgkxjsj_hb is not null then 1 else 0 end) expert_zgkxjsj_hb, " +
					"sum(case when e.expert_kxyys_hb is not null then 1 else 0 end) expert_kxyys_hb, " +
					"sum(case when e.expert_gcyys_hb is not null then 1 else 0 end) expert_gcyys_hb, " +
					"sum(case when e.expert_wjys_hb is not null then 1 else 0 end) expert_wjys_hb, " +
					"sum(case when e.expert_wgys_hb is not null then 1 else 0 end) expert_wgys_hb, " +
					"sum(case when e.expert_djrc_leader_hb is not null then 1 else 0 end) expert_djrc_leader_hb, " +
					"sum(case when e.expert_jcrc_hb is not null then 1 else 0 end) expert_jcrc_hb, " +
					"sum(case when e.expert_sxjjg_hb is not null then 1 else 0 end) expert_sxjjg_hb " +
					"from zjk_ryjbxx_update t " +
					"left join zjk_ryjbxx_info_update e on e.zjkryjbxxupdateid = t.id " +
					"where e.topexpert is not null and t.rkflag = '1' ";
		}else if("nationalexpert".equals(type)){
			sql = "select sum(case when e.national_rcyj_hb is not null then 1 else 0 end) national_rcyj_hb, " +
					"sum(case when e.national_ljrc_hb is not null then 1 else 0 end) national_ljrc_hb, " +
					"sum(case when e.national_cjxz_hb is not null then 1 else 0 end) national_cjxz_hb, " +
					"sum(case when e.national_cxjh_hb is not null then 1 else 0 end) national_cxjh_hb, " +
					"sum(case when e.national_bwrcrxz_hb is not null then 1 else 0 end) national_bwrcrxz_hb, " +
					"sum(case when e.national_ljrcrxz_hb is not null then 1 else 0 end) national_ljrcrxz_hb, " +
					"sum(case when e.national_tcgxzj_hb is not null then 1 else 0 end) national_tcgxzj_hb, " +
					"sum(case when e.national_qnkj_hb is not null then 1 else 0 end) national_qnkj_hb, " +
					"sum(case when e.national_brjhrxz_hb is not null then 1 else 0 end) national_brjhrxz_hb, " +
					"sum(case when e.national_gwyjtzj_hb is not null then 1 else 0 end) national_gwyjtzj_hb, " +
					"sum(case when e.national_zfyyj_hb is not null then 1 else 0 end) national_zfyyj_hb, " +
					"sum(case when e.national_kxjshzj_hb is not null then 1 else 0 end) national_kxjshzj_hb, " +
					"sum(case when e.national_zrkxwcr_hb is not null then 1 else 0 end) national_zrkxwcr_hb, " +
					"sum(case when e.national_gyds_hb is not null then 1 else 0 end) national_gyds_hb, " +
					"sum(case when e.national_qhmzy_hb is not null then 1 else 0 end) national_qhmzy_hb, " +
					"sum(case when e.national_hlhljj_hb is not null then 1 else 0 end) national_hlhljj_hb, " +
					"sum(case when e.national_zdzxfzr_hb is not null then 1 else 0 end) national_zdzxfzr_hb, " +
					"sum(case when e.national_zdyffzr_hb is not null then 1 else 0 end) national_zdyffzr_hb, " +
					"sum(case when e.national_zrkxfzr_hb is not null then 1 else 0 end) national_zrkxfzr_hb, " +
					"sum(case when e.national_bsgxxz_hb is not null then 1 else 0 end) national_bsgxxz_hb, " +
					"sum(case when e.national_zdxkdtr_hb is not null then 1 else 0 end) national_zdxkdtr_hb, " +
					"sum(case when e.national_sysyz_hb is not null then 1 else 0 end) national_sysyz_hb, " +
					"sum(case when e.national_ssgsfzr_hb is not null then 1 else 0 end) national_ssgsfzr_hb " +
					"from zjk_ryjbxx_update t " +
					"left join zjk_ryjbxx_info_update e on e.zjkryjbxxupdateid = t.id " +
					"where e.nationalexpert is not null and t.rkflag = '1' ";
		}else if("provincialexpert".equals(type)){
			sql = "select sum(case when e.provincial_brjhrxz_hb is not null then 1 else 0 end) provincial_brjhrxz_hb, " +
					"sum(case when e.provincial_ctxzjs_hb is not null then 1 else 0 end) provincial_ctxzjs_hb, " +
					"sum(case when e.provincial_tcgxzj_hb is not null then 1 else 0 end) provincial_tcgxzj_hb, " +
					"sum(case when e.provincial_jczyrc_hb is not null then 1 else 0 end) provincial_jczyrc_hb, " +
					"sum(case when e.provincial_xsjgccrxz_hb is not null then 1 else 0 end) provincial_xsjgccrxz_hb, " +
					"sum(case when e.provincial_qnbjrcrxz_hb is not null then 1 else 0 end) provincial_qnbjrcrxz_hb, " +
					"sum(case when e.provincial_tsjt_hb is not null then 1 else 0 end) provincial_tsjt_hb, " +
					"sum(case when e.provincial_zxjt_hb is not null then 1 else 0 end) provincial_zxjt_hb, " +
					"sum(case when e.provincial_bzj_hb is not null then 1 else 0 end) provincial_bzj_hb, " +
					"sum(case when e.provincial_tcgxj_hb is not null then 1 else 0 end) provincial_tcgxj_hb, " +
					"sum(case when e.provincial_zrkxwcr_hb is not null then 1 else 0 end) provincial_zrkxwcr_hb, " +
					"sum(case when e.provincial_zdzxfzr_hb is not null then 1 else 0 end) provincial_zdzxfzr_hb, " +
					"sum(case when e.provincial_cxqtfzr_hb is not null then 1 else 0 end) provincial_cxqtfzr_hb, " +
					"sum(case when e.provincial_sczldtr_hb is not null then 1 else 0 end) provincial_sczldtr_hb " +
					"from zjk_ryjbxx_update t " +
					"left join zjk_ryjbxx_info_update e on e.zjkryjbxxupdateid = t.id " +
					"where e.provincialexpert is not null and t.rkflag = '1' ";
		}else if("foreignexpert".equals(type)){
			sql = "select sum(case when e.foreign_rxgnrcyjjh_hb is not null then 1 else 0 end) foreign_rxgnrcyjjh_hb, " +
					"sum(case when e.foreign_gjgrzycj_hb is not null then 1 else 0 end) foreign_gjgrzycj_hb, " +
					"sum(case when e.foreign_scdxgwxq_hb is not null then 1 else 0 end) foreign_scdxgwxq_hb, " +
					"sum(case when e.foreign_cxcyrc_hb is not null then 1 else 0 end) foreign_cxcyrc_hb, " +
					"sum(case when e.foreign_yxqnrc_hb is not null then 1 else 0 end) foreign_yxqnrc_hb " +
					"from zjk_ryjbxx_update t " +
					"left join zjk_ryjbxx_info_update e on e.zjkryjbxxupdateid = t.id " +
					"where e.foreignexpert is not null and t.rkflag = '1' ";
		}else if("gjhzexpert".equals(type)){
			sql = "select sum(case when e.gjhz_organization_senior is not null then 1 else 0 end) gjhz_organization_senior, " +
					"sum(case when e.gjhz_administration_senior is not null then 1 else 0 end) gjhz_administration_senior, " +
					"sum(case when e.gjhz_enterprise_senior is not null then 1 else 0 end) gjhz_enterprise_senior " +
					"from zjk_ryjbxx_update t " +
					"left join zjk_ryjbxx_info_update e on e.zjkryjbxxupdateid = t.id " +
					"where e.gjhz is not null and t.rkflag = '1' ";
		}

		List<Map> list = this.dbHelper.getRows(sql,null);

		List<Map> resList = new ArrayList<>();

		Iterator iter = list.get(0).entrySet().iterator();
		while (iter.hasNext()) {
			String name = "";
			Map.Entry entry = (Map.Entry) iter.next();
			Map m = new HashMap();
			m.put("type",entry.getKey().toString());
			m.put("value",entry.getValue().toString());

			Class<?> clazz = ZjkRyjbxxInfoUpdate.class;
			Field[] fields = clazz.getDeclaredFields();
			for(Field field : fields){
				if(field.isAnnotationPresent(FieldDes.class)){
					FieldDes fieldDes = field.getAnnotation(FieldDes.class);
					if(fieldDes.name().equals(entry.getKey().toString())){
						name = fieldDes.memo();
						break;
					}
				}
			}

			m.put("name",name);

			resList.add(m);
		}

		return resList;
	}



	public Page<Map> findExpertRcchList(JSONObject jsonObject) {
		String type = jsonObject.get("type")+"";
		JSONObject pageConfig = jsonObject.getJSONObject("pageConfig");
		String insql = "";
		List paramlist = new ArrayList<>();
		if(!Util.isEoN(jsonObject.get("searchContent"))){
			String param = jsonObject.get("searchContent").toString();
			insql += " and (t.name like ? or t.organization like ? or t.position_title like ?)";
			paramlist.add("%" + param + "%");
			paramlist.add("%" + param + "%");
			paramlist.add("%" + param + "%");
		}
		List params = new ArrayList<>();
		String sql = "select t.name,t.organization,t.position_title " +
				"from zjk_ryjbxx_update t " +
				"left join zjk_ryjbxx_info_update e on e.zjkryjbxxupdateid = t.id " +
				"where e."+type+" is not null and t.rkflag = '1' ";
		Page<Map> page = this.pageService.findPageBySql(sql + insql, paramlist, pageConfig);
		return page;
	}

	public Page<Map> findExpertTypeList(JSONObject jsonObject) {
		String type = jsonObject.get("type")+"";
		JSONObject pageConfig = jsonObject.getJSONObject("pageConfig");
		String insql = "";
		List paramlist = new ArrayList<>();
		if(!Util.isEoN(jsonObject.get("searchContent"))){
			String param = jsonObject.get("searchContent").toString();
			insql += " and (t.name like ? or t.organization like ? or t.position_title like ?)";
			paramlist.add("%" + param + "%");
			paramlist.add("%" + param + "%");
			paramlist.add("%" + param + "%");
		}
		List params = new ArrayList<>();
		String sql = "";
		if("techexpert".equals(type)){//应该就技术和管理有交叉吧
			sql = "select t.name,t.organization,t.position_title " +
					"from zjk_ryjbxx_update t " +
					"where t.expert_type like '%技术类专家%'  and t.rkflag = '1' ";
		}else if("manageexpert".equals(type)){
			sql = "select t.name,t.organization,t.position_title " +
					"from zjk_ryjbxx_update t " +
					"where t.expert_type like '%管理类专家%'  and t.rkflag = '1' ";
		}else if("economyexpert".equals(type)){
			sql = "select t.name,t.organization,t.position_title " +
					"from zjk_ryjbxx_update t " +
					"where t.expert_type = '经济类专家'  and t.rkflag = '1' ";
		}else if("otherexpert".equals(type)){
			sql = "select t.name,t.organization,t.position_title " +
					"from zjk_ryjbxx_update t " +
					"where t.expert_type = '其他类专家'  and t.rkflag = '1' ";
		}else if("strategyexpert".equals(type)){
			sql = "select t.name,t.organization,t.position_title " +
					"from zjk_ryjbxx_update t " +
					"left join zjk_ryjbxx_info_update e on e.zjkryjbxxupdateid = t.id " +
					"where e.strategyexpert is not null  and t.rkflag = '1' ";
		}else if("qyexpert".equals(type)){
			sql = "select t.name,t.organization,t.position_title " +
					"from zjk_ryjbxx_update t " +
					"where t.isqyzj is not null  and t.rkflag = '1' ";
		}
		Page<Map> page = this.pageService.findPageBySql(sql + insql, paramlist, pageConfig);
		return page;
	}



	public ZjkRyjbxxUpdate findByIdNew(String id) {
		return   this.findById(id);
	}



	/**
	 * 【专家注册保存】---保存专家信息Update
	 * @param json
	 */
	public void saveRyjbxxUpdate(JSONObject json){
		String id = json.get("id")+"";
		ZjkRyjbxxUpdate obj = this.findById(id);
		if(obj!=null){
			String cnsflag = obj.getCnsflag();
			Util.ApplyObject(obj,json);
			obj.setId(id);
			if(Util.isEoN(cnsflag) && !Util.isEoN(obj.getCnsflag())) {
				obj.setCnsdate(new Date());
			}
		}else{
			obj = new ZjkRyjbxxUpdate();
			Util.ApplyObject(obj,json);
			obj.setId(id);
			obj.setRegisterdate(new Date());
			if(!Util.isEoN(obj.getCnsflag())) {
				obj.setCnsdate(new Date());
			}
			if(json.containsKey("dxyqzj") && "true".equals(json.get("dxyqzj"))) {
				obj.setMinicurrentstate("省外专家注册:专家填报");
				obj.setMinicurrentstateid("SWEXPERT_ZC_1");
			} else {
				obj.setMinicurrentstate("专家注册:专家填报");
				obj.setMinicurrentstateid("EXPERT_ZC_1");
			}
			obj.setIscanpick("0");
			obj.setZero_reason("专家注册流程中");
		}



		List<BiTalentWe> biTalentWes = this.biTalentWeService.findByHql("select t from BiTalentWe t " +
				"where t.mainid = ?0 and t.type = 'currentInfo_work' ",new Object[]{json.get("userid")+""});
		if(biTalentWes.size()>0){
			obj.setOrganization(biTalentWes.get(0).getUnitname());
			obj.setUniformcode(biTalentWes.get(0).getCreditcode());
			obj.setJtgzdw(biTalentWes.get(0).getJtgzdw());
			obj.setJtgzdw_uniformcode(Optional.ofNullable(biTalentWes.get(0).getJtgzdw_creditcode()).orElse(biTalentWes.get(0).getCreditcode()));
			obj.setMain_unitname(biTalentWes.get(0).getMain_unitname());
			obj.setMain_uniformcode(biTalentWes.get(0).getMain_uniformcode());
		}else{
			PmsEnterprise pmsEnterprise = this.pmsEnterpriseService.findById(json.get("unitid")+"");

			obj.setOrganization(pmsEnterprise.getName());
			obj.setUniformcode(pmsEnterprise.getUniformcode());
		}


//        //判断中文名称大于三个字 或 全英文名称
//        if(this.judgeIscanpick(obj.getName())){
//            obj.setIscanpick("");
//        }else{
//            obj.setIscanpick("0");
//        }
		this.merge(obj);
//		String deleteZjkLinkbqHql = "select t from ZjkLinkbq t " +
//				" where t.zjkryjbxxupdateid = ? and t.type = ? " +
//				" and t.endzjkbqid in ( " +
//				" '9282ace8-d12f-463d-d6be-2758eb534c22',\n" + // 技术专家
//				" 'dd276ca5-4b30-4ec9-d20a-b3f39a3b50c0',\n" + // 管理专家
//				" '5c5345d1-1656-445c-ff92-a21624061c2e',\n" + // 经济专家
//				" '6617384b-7d89-478b-c97b-bc52f50d7d1a',\n" + // 其他专家
//				" 'bed44cfd-7d25-473c-d93e-09a9412a7871',\n" + // 战略专家
//				" 'c5111578-d836-429d-f51e-18211541183b',\n" + // 顶尖高端专家
//				" '992026d3-2f66-42c2-8f27-bd787858614d',\n" + // 国家级高端专家
//				" 'fd9e98de-be52-4f42-c28e-f69611f0212c',\n" + // 省级高端专家
//				" '7ac3dda2-b124-4afe-f29e-c9c8d59eb5b5',\n" + // 外国专家
//				" 'c1fb7eb7-e48e-47ec-f96a-3469747d3d4b',\n" + // 国际合作专家
//				" '26b9e3c4-89e7-4e9a-ec3d-483652213606' \n" + // 企业专家
//				")";
//		List<ZjkLinkbq> zjkLinkbqList = zjkLinkbqService.findByHql(deleteZjkLinkbqHql, obj.getId(), "bq");
//		zjkLinkbqService.deleteList(zjkLinkbqList);
//		saveZjBq(obj);
	}


	/**
	 * 【专家注册获取】---判断该用户是否有专家主表信息
	 * @param json
	 * @return
	 */
	public boolean judgeExpertInfoByUserid(JSONObject json){
		String userid = json.get("userid")+"";
		List<ZjkRyjbxxUpdate> zjkRyjbxxList = this.findByHql("select t from ZjkRyjbxxUpdate t " +
				"where t.userid = ?0 ",new Object[]{userid});
		if(zjkRyjbxxList!=null&&zjkRyjbxxList.size()>0){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * 【专家注册获取】---获取专家主表信息
	 * @param json
	 * @return
	 */
	public JSONObject getRyjbxx(JSONObject json){
		String userid = json.get("userid")+"";
		JSONObject resJson = new JSONObject();
		List<ZjkRyjbxxUpdate> zjkRyjbxxList = this.findByHql("select t from ZjkRyjbxxUpdate t " +
				"where t.userid = ?0 ",new Object[]{userid});
		if(zjkRyjbxxList!=null&&zjkRyjbxxList.size()>0){
			ZjkRyjbxxUpdate zjkRyjbxx = zjkRyjbxxList.get(0);
			JsonConfig jsonConfig = JsonConfigUtil.getJsonConfig();
			resJson = JSONObject.fromObject(zjkRyjbxx,jsonConfig);
		}
		return resJson;
	}






}
