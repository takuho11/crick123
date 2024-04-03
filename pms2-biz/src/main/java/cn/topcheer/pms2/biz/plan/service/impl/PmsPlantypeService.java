package cn.topcheer.pms2.biz.plan.service.impl;

import cn.topcheer.halberd.app.api.minidev.entity.MiniBizType;
import cn.topcheer.halberd.app.api.minidev.service.MiniBizTypeService;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.pms2.api.plan.entity.PmsPlantype;
import cn.topcheer.pms2.dao.plan.PmsPlantypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springblade.core.tool.api.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * PmsPlanCategoryService 服务类
 * @author whq
 * @date 2023-11-09
 */
@Service(value = "PmsPlanTypeService")
public class PmsPlantypeService extends GenericService<PmsPlantype> {
    @Autowired
    private MiniBizTypeService miniBizTypeService;
    public PmsPlantypeDao getPmsPlantypeDao() {
        return (PmsPlantypeDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsPlanTypeDao(PmsPlantypeDao pmsPlanTypeDao) {

        this.setGenericDao(pmsPlanTypeDao);
    }


    /**
     * 获取数据
     *
     * @param code 编码
     * @return PmsPlantype
     * @author szs
     * @date 2023-11-09
     */
    public PmsPlantype getPmsPlantype(String code) {
        HqlBuilder<PmsPlantype> builder = new HqlBuilder<>();
        builder.eq(PmsPlantype::getCode, code);
        List<PmsPlantype> list = this.find(builder);

        return list.size() > 0 ? list.get(0) : null;

    }



    /**
     * 查询所有业务类型编码名称
     */
    public List<PmsPlantype> queryAll(){
        List<PmsPlantype> list = this.findAll();
        return list;
    }

    public R saveTypes(List<PmsPlantype> pmsPlantypeList) {
        HqlBuilder hqlBuilder;
        List<PmsPlantype> typeList = new ArrayList<>();
        if(pmsPlantypeList != null){
            for (PmsPlantype pmsPlantype : pmsPlantypeList) {
                hqlBuilder = HqlBuilder.builder(PmsPlantype.class)
                        .eq(PmsPlantype::getCode, pmsPlantype.getCode());
                List<PmsPlantype> list = this.find(hqlBuilder);
                if (list.size()==0){
                    String id = miniBizTypeService.hasType(pmsPlantype);
                    pmsPlantype.setId(id);
                    typeList.add(pmsPlantype);
                    this.saveOrUpdate(pmsPlantype);
                }
            }
            R<Object> r = R.success("业务保存成功");
            r.setData(typeList);
            return r;
        }else {
            return R.success("无业务需保存");
        }
    }

}
