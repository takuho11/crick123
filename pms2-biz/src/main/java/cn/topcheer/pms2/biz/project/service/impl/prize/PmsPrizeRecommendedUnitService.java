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
 * PmsPrizeRecommendedUnit 服务类
 */
@Service(value="PmsPrizeRecommendedUnitService")
public class PmsPrizeRecommendedUnitService extends GenericService<PmsPrizeRecommendedUnit> {

	public PmsPrizeRecommendedUnitDao getPmsPrizeRecommendedUnitDao() {
		return (PmsPrizeRecommendedUnitDao) this.getGenericDao();
	}

	@Autowired
	public void setPmsPrizeRecommendedUnitDao(PmsPrizeRecommendedUnitDao pmsPrizeRecommendedUnitDao) {

		this.setGenericDao(pmsPrizeRecommendedUnitDao);
	}

	
}
