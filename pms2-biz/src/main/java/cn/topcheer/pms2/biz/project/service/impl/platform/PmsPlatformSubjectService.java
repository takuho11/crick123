/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024-1-4 9:33:52
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.platform;

import java.util.List;
import java.util.ArrayList;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.project.entity.platform.*;
import cn.topcheer.pms2.dao.project.platform.*;

/**
 * PmsPlatformSubject 服务类
 */
@Service(value="PmsPlatformSubjectService")
public class PmsPlatformSubjectService extends GenericService<PmsPlatformSubject> {

	public PmsPlatformSubjectDao getPmsPlatformSubjectDao() {
		return (PmsPlatformSubjectDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsPlatformSubjectDao(PmsPlatformSubjectDao pmsPlatformSubjectDao) {

		this.setGenericDao(pmsPlatformSubjectDao);
	}

	
}
