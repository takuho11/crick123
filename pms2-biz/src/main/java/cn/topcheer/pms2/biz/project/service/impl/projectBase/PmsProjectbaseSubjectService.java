/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2022-10-26 16:26:50
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.projectBase;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbaseSubject;
import cn.topcheer.pms2.dao.project.*;
import cn.topcheer.pms2.api.project.entity.*;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbaseSubjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PmsProjectbaseSubject 服务类
 */
@Service(value="PmsProjectbaseSubjectService")
public class PmsProjectbaseSubjectService extends GenericService<PmsProjectbaseSubject> {

	public PmsProjectbaseSubjectDao getPmsProjectbaseSubjectDao() {
		return (PmsProjectbaseSubjectDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsProjectbaseSubjectDao(PmsProjectbaseSubjectDao pmsProjectbaseSubjectDao) {

		this.setGenericDao(pmsProjectbaseSubjectDao);
	}

	public List<PmsProjectbaseSubject> findSubByPidandType(String projectbaseid,String type){
		List<PmsProjectbaseSubject> list = this.getPmsProjectbaseSubjectDao().findByHql("select t from PmsProjectbaseSubject t where t.projectbaseid = ? and type = ? order by t.seq",new Object[]{projectbaseid,type});
		return list;
	}
	
}
