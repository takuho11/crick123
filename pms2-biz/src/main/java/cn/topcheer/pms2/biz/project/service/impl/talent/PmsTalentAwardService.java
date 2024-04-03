/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024-1-4 9:30:13
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.talent;

import java.util.List;
import java.util.ArrayList;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.project.entity.talent.*;
import cn.topcheer.pms2.dao.project.talent.*;

/**
 * PmsTalentAward 服务类
 */
@Service(value="PmsTalentAwardService")
public class PmsTalentAwardService extends GenericService<PmsTalentAward> {

	public PmsTalentAwardDao getPmsTalentAwardDao() {
		return (PmsTalentAwardDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsTalentAwardDao(PmsTalentAwardDao pmsTalentAwardDao) {

		this.setGenericDao(pmsTalentAwardDao);
	}

	
}
