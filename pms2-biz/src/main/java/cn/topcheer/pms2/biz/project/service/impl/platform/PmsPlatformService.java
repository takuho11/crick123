/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2024-1-4 9:33:52
 */
package cn.topcheer.pms2.biz.project.service.impl.platform;


import cn.topcheer.halberd.app.api.framework.entity.sys.PmsDepartment;
import cn.topcheer.halberd.app.api.framework.entity.sys.PmsEnterprise;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsDepartmentServiceImpl;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.PmsEnterpriseServiceImpl;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.sys.SysGuide;
import cn.topcheer.pms2.biz.sys.SysGuideService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.topcheer.pms2.api.project.entity.platform.*;
import cn.topcheer.pms2.dao.project.platform.*;

/**
 * PmsPlatform 服务类
 */
@Service(value = "PmsPlatformService")
public class PmsPlatformService extends GenericService<PmsPlatform> {

    public PmsPlatformDao getPmsPlatformDao() {
        return (PmsPlatformDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsPlatformDao(PmsPlatformDao pmsPlatformDao) {

        this.setGenericDao(pmsPlatformDao);
    }


    @Autowired
    private PmsEnterpriseServiceImpl pmsEnterpriseService;

    @Autowired
    private PmsDepartmentServiceImpl pmsDepartmentService;

    @Autowired
    private SysGuideService sysGuideService;


    @Override
    protected JSONObject afterGenericSaveFun(JSONObject result, String mainid, String type, JSONArray data, String batchid, String flowtype, String mold, JSONObject tableObject) {
        PmsPlatform pmsPlatform = this.findById(mainid);

        // 推荐单位名称
        PmsEnterprise casemanagementEnter = pmsEnterpriseService.findById(pmsPlatform.getEnterpriseid());
        if (casemanagementEnter != null) {
            if (casemanagementEnter.getPmsParentEnterprise() == null || "98C57F262CB74E12AAC31C9D37533A17".equals(casemanagementEnter.getPmsParentEnterprise().getId())) {
                // 当前属于一级单位，无上级或上级等于省科技厅
                pmsPlatform.setCitycasemanagement(casemanagementEnter.getName());
                pmsPlatform.setCitycasemanagementid(casemanagementEnter.getId());

            } else {
                // 当前属于二级单位，有上级
                pmsPlatform.setCitycasemanagement(casemanagementEnter.getPmsParentEnterprise().getName());
                pmsPlatform.setCitycasemanagementid(casemanagementEnter.getPmsParentEnterprise().getId());
            }

            pmsPlatform.setCountycasemanagement(casemanagementEnter.getName());
            pmsPlatform.setCountycasemanagementid(casemanagementEnter.getId());

        }

        // 判断方向id是否有值
        if (!Util.isEoN(pmsPlatform.getSupportdirectionid())) {
            // 获取方向相关信息
            SysGuide sysGuide = sysGuideService.getById(pmsPlatform.getSupportdirectionid());
            if (!Util.isEoN(sysGuide) && !Util.isEoN(sysGuide.getBelonglabid())) {
                // 获取处室信息
                PmsDepartment pmsDepartment = pmsDepartmentService.findById(sysGuide.getBelonglabid());
                if (!Util.isEoN(pmsDepartment)) {
                    pmsPlatform.setBelonglabid(pmsDepartment.getId());
                    pmsPlatform.setBelonglab(pmsDepartment.getName());
                }
            }
        }

        this.merge(pmsPlatform);

        return result;
    }


}
