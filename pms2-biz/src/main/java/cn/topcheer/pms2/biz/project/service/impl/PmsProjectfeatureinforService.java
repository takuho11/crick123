/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2016-2-17 10:15:26
 *
 */
package cn.topcheer.pms2.biz.project.service.impl;

import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.PmsProjectfeatureinfor;
import cn.topcheer.pms2.api.project.entity.PmsProjectparty;
import cn.topcheer.pms2.dao.project.PmsProjectfeatureinforDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * PmsProjectfeatureinfor 服务类
 */
@Service(value="PmsProjectfeatureinforService")
public class PmsProjectfeatureinforService extends GenericService<PmsProjectfeatureinfor> {

	@Autowired
	DBHelper dbHelper;

	public PmsProjectfeatureinforDao getPmsProjectfeatureinforDao() {
		return (PmsProjectfeatureinforDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectfeatureinforDao(PmsProjectfeatureinforDao pmsProjectfeatureinforDao) {

		this.setGenericDao(pmsProjectfeatureinforDao);
	}



//	//根据项目ID查找位于enterpriseorganization的基本信息
//	public List<PmsEnterpriseorganization> findOtherBase(String projectbaseid) {
//		String hql = "select t from cn.topcheer.pms2.api.project.entity.PmsEnterpriseorganization t left join t.pmsProjectbase projectbase where projectbase.id=? and t.ismainenterprise='true'";
//		List<PmsEnterpriseorganization> listPpc = this.findByHql(hql,projectbaseid);
//		if (listPpc.size() > 0) {
//			return listPpc;
//		} else {
//			return null;
//		}
//	}
//
//	@Autowired
//	PmsDesensitizationdataService pmsDesensitizationdataService;
//
//	//根据项目ID查找位于enterpriseorganization的海外主要合作伙伴
//	public List<PmsEnterpriseorganization> findHwhzhb(String projectbaseid) {
//		String hql = "select t from cn.topcheer.pms2.api.project.entity.PmsEnterpriseorganization t left join t.pmsProjectbase projectbase where projectbase.id=? and t.ismainenterprise is null order by seq";
//		List<PmsEnterpriseorganization> listPpc = this.findByHql(hql,projectbaseid);
//		if (listPpc.size() > 0) {
//			return listPpc;
//		} else {
//			return null;
//		}
//	}

	//国际科技合作管理人员
	public List<PmsProjectparty> findQtry(String projectbaseid) {
		String hql = "select t from cn.topcheer.pms2.api.project.entity.PmsProjectparty t left join t.pmsProjectbase projectbase where projectbase.id=? and programrole='principal' order by seq";
		List<PmsProjectparty> listPpc = this.findByHql(hql,projectbaseid);
		if (listPpc.size() > 0) {
			return listPpc;
		} else {
			return null;
		}
	}

	//参加单位及人员情况2
	public List<PmsProjectfeatureinfor> findprojectinfor2(String projectbaseid) {
		String hql = "select t from cn.topcheer.pms2.api.project.entity.PmsProjectfeatureinfor t where t.projectbaseid=? and t.secret is null";
		List<PmsProjectfeatureinfor> listPpc = this.findByHql(hql,projectbaseid);
		if (listPpc.size() > 0) {
			return listPpc;
		} else {
			return null;
		}
	}

	//根据type和id查找
	public List<PmsProjectfeatureinfor> findprojecfeaturBytype(String projectbaseid,String type) {
		String hql = "select t from cn.topcheer.pms2.api.project.entity.PmsProjectfeatureinfor t where t.projectbaseid=? and t.secret=? order by seq";
		List<PmsProjectfeatureinfor> listPpc = this.findByHql(hql,new Object[]{projectbaseid,type});
		return listPpc;
	}
	
	/**
	 * @author ccb
	 * @param
	 * @param
	 * @return 参加单位及人员情况
	 * @throws IOException
	 */
	public List<Map> findprojectinfor(String pid) {
		String sql = "select t.*, t.rowid from PMS_PROJECTFEATUREINFOR t where t.projectbaseid=?";
		List<Map> listMap = dbHelper.getRows(sql, new Object[]{pid});

		return listMap;

	}


	public List<PmsProjectfeatureinfor> findBypid(String pid) {
		String sql = "select t from cn.topcheer.pms2.api.project.entity.PmsProjectfeatureinfor t where t.projectbaseid=?";
		List<PmsProjectfeatureinfor> listMap = this.findByHql(sql, new Object[]{pid});

		return listMap;

	}



    /**
     * @author j
     * @param
     * @param
     * @return 学科
     * @throws IOException
     */
    public String findxkname(String pid) {
        String xkname="";
        String sqlxk="select ty.name,ty.code,ty.sscs from pms_projectfeatureinfor pe inner join  pms_subject_type ty on ty.code=pe.subjecttwo" +
                " where pe.projectbaseid=? and  ty.type = 'xm' ";
        List<Map> listPishjqk = dbHelper.getRows(sqlxk,
                new Object[] { pid });
        if(listPishjqk.size()>0) {
            xkname=listPishjqk.get(0).get("name").toString();
        }else{
            String sqlxk2="select ty.name,ty.code,ty.sscs from pms_projectfeatureinfor pe inner join  pms_subject_type ty on ty.code=pe.subjectone" +
                    " where pe.projectbaseid=? and  ty.type = 'xm' ";
            List<Map> listPishjqk2 = dbHelper.getRows(sqlxk2,
                    new Object[] { pid });
            if(listPishjqk2.size()>0){
                xkname=listPishjqk2.get(0).get("name").toString();
            }else{
                xkname="";
            }
        }
        return  xkname;
    }


	public List Findyxzc(String pid) {
		String sql = "select t.enterprisetypes,t.researchinput,t.mainbusinessincome from pms_projectfeatureinfor t where t.projectbaseid = ?";
		List<Map> list = dbHelper.getRows(sql, new Object[]{pid});

		return list;
	}




}
