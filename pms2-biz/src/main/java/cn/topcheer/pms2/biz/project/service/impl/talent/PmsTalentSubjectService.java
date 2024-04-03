/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2022-10-26 16:26:50
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.talent;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.project.entity.projectBase.PmsProjectbaseSubject;
import cn.topcheer.pms2.api.project.entity.talent.PmsTalentSubject;
import cn.topcheer.pms2.dao.project.projectBase.PmsProjectbaseSubjectDao;
import cn.topcheer.pms2.dao.project.talent.PmsTalentSubjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PmsTalentSubject 服务类
 */
@Service(value="PmsTalentSubjectService")
public class PmsTalentSubjectService extends GenericService<PmsTalentSubject> {

	public PmsTalentSubjectDao getPmsTalentSubjectDao() {
		return (PmsTalentSubjectDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsTalentSubjectDao(PmsTalentSubjectDao pmsTalentSubjectDao) {

		this.setGenericDao(pmsTalentSubjectDao);
	}

	public List<PmsTalentSubject> findSubByPidandType(String talentid,String type){
		List<PmsTalentSubject> list = this.getPmsTalentSubjectDao().findByHql("select t from PmsTalentSubject t where t.mainid = ? and type = ? order by t.seq",new Object[]{talentid,type});
		return list;
	}
	
}
