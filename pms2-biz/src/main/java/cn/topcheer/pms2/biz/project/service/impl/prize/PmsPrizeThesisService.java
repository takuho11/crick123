/**
 *  本代码由代码生成工具自动生成
 *  创建时间 : 2024-1-4 9:32:16
 *
 */
package cn.topcheer.pms2.biz.project.service.impl.prize;

import java.util.List;
import java.util.ArrayList;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.project.entity.prize.*;
import cn.topcheer.pms2.dao.project.prize.*;

/**
 * PmsPrizeThesis 服务类
 */
@Service(value="PmsPrizeThesisService")
public class PmsPrizeThesisService extends GenericService<PmsPrizeThesis> {

	public PmsPrizeThesisDao getPmsPrizeThesisDao() {
		return (PmsPrizeThesisDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsPrizeThesisDao(PmsPrizeThesisDao pmsPrizeThesisDao) {

		this.setGenericDao(pmsPrizeThesisDao);
	}

	
}
