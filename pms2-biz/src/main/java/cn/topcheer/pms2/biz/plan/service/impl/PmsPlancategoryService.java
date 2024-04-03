package cn.topcheer.pms2.biz.plan.service.impl;

import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.pms2.api.plan.dto.PmsPlancategoryDTO;
import cn.topcheer.pms2.api.plan.entity.PmsPlancategory;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatchInBiz;
import cn.topcheer.pms2.api.plan.entity.PmsPlantype;
import cn.topcheer.pms2.api.plan.vo.PmsPlancategoryVO;
import cn.topcheer.pms2.biz.sys.SysUserService;
import cn.topcheer.pms2.dao.plan.PmsPlancategoryDao;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;

/**
 * PmsPlanCategoryService 服务类
 * @author whq
 * @date 2023-11-09
 */
@Service(value = "PmsPlanCategoryService")
public class PmsPlancategoryService extends GenericService<PmsPlancategory> {

    public final PmsPlantypeService pmsPlantypeService;

    private final PmsPlanprojectbatchInBizService planprojectbatchInBizService;
    @Autowired
    private SysUserService sysUserService;

    public PmsPlancategoryService(PmsPlantypeService pmsPlantypeService, PmsPlanprojectbatchInBizService planprojectbatchInBizService) {
        this.pmsPlantypeService = pmsPlantypeService;
        this.planprojectbatchInBizService = planprojectbatchInBizService;
    }

    public PmsPlancategoryDao getPmsPlanCategoryDao() {
        return (PmsPlancategoryDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsPlanCategoryDao(PmsPlancategoryDao pmsPlanCategoryDao) {

        this.setGenericDao(pmsPlanCategoryDao);
    }

    /**
     * 查询事项类别详情
     * @param plancategory
     * @return
     *
     */
    public R<PmsPlancategoryVO> getDetail(PmsPlancategory plancategory) {
        //拼接角色权限过滤
//        HqlBuilder<PmsPlancategory> hqlBuilder = super.getHqlBuilder();
        HqlBuilder<PmsPlancategory> hqlBuilder = new HqlBuilder<>();
        if (hqlBuilder == null) {
            return R.fail("请检查传参");
        }
        hqlBuilder.eq("is_delete", 0)
                .like(!Util.isEoN(plancategory.getName()),PmsPlancategory::getName,plancategory.getName())
                .like(!Util.isEoN(plancategory.getCode()),PmsPlancategory::getCode,plancategory.getCode());
        PmsPlancategory pbc = this.findOne(hqlBuilder);
        PmsPlancategoryVO plancategoryVO = new PmsPlancategoryVO();
        BeanUtil.copyProperties(pbc,plancategoryVO);
        String[] split = pbc.getDefaultType().split(";");
        List<PmsPlantype> list = new ArrayList<>();
        for (String s : split) {
            PmsPlantype pmsPlantype = pmsPlantypeService.getPmsPlantype(s);
            list.add(pmsPlantype);
        }
        plancategoryVO.setPmsPlantypeList(list);
        return R.data(plancategoryVO,"查询成功");
    }

    /**
     * 新增或保存
     * @param plancategoryDTO
     * @return
     */
    public R savePmsPlancategory(PmsPlancategoryDTO plancategoryDTO) {
        PmsPlancategory ppc = null;
        try {
            R r = pmsPlantypeService.saveTypes(plancategoryDTO.getPmsPlantypeList());
            if (!r.isSuccess()){
                return r;
            }
            String defaultType = makeDefaultType(plancategoryDTO.getPmsPlantypeList());
            if (!StringUtils.isEmpty(plancategoryDTO.getId())){
                //获取新增的planType对象
                Object data = r.getData();
                JSONArray jsonArray = JSONArray.fromObject(data);
                List<PmsPlantype> typeList = new ArrayList();
                jsonArray.forEach(json->{
                    PmsPlantype pmsPlantype = new PmsPlantype();
                    JSONObject typeJson = (JSONObject) json;
                    pmsPlantype.setId(typeJson.getString("id"));
                    pmsPlantype.setName(typeJson.getString("name"));
                    pmsPlantype.setCode(typeJson.getString("code"));
                    pmsPlantype.setSeq(Integer.parseInt(typeJson.getString("seq")));
                    typeList.add(pmsPlantype);
                });
                //修改
                ppc = this.findById(plancategoryDTO.getId());
                String[] split = defaultType.split(";");
                String ppcDefaultType = ppc.getDefaultType();
                List<String> addList = new ArrayList<>();
                //提取要新增的业务
                for (String s : split) {
                    s = s.replaceAll(";", "");
                    if (!ppcDefaultType.contains(s)){
//                        ppcDefaultType = ppcDefaultType + ";" + s;
                        addList.add(s);
                    }
                }
//                plancategoryDTO.setDefaultType(ppcDefaultType);
                plancategoryDTO.setDefaultType(defaultType);
                plancategoryDTO.setCreateUser(ppc.getCreateUser());
                plancategoryDTO.setCreateTime(ppc.getCreateTime());
                plancategoryDTO.setCode(ppc.getCode());
                plancategoryDTO.setName(ppc.getName());
                BeanUtil.copyProperties(plancategoryDTO, ppc);
                //获取该category对应的小批次集合
                String code = ppc.getCode();
                List<Map> groupByCode = planprojectbatchInBizService.getGroupByCode(code);
                Set<String> idSet = new HashSet<>();
                //todo: zrx
                groupByCode.forEach(biz->{
                    String id = biz.get("pms_planprojectbatch_id").toString();
                    typeList.forEach(type ->{
                        //获取该小批次所有业务记录
                        List<PmsPlanprojectbatchInBiz> allTypeByBatchId = planprojectbatchInBizService.getAllTypeByBatchId(id);
                        //删除新业务串中不包含的业务
                        allTypeByBatchId.forEach(batchInBiz->{
                            if (!defaultType.contains(batchInBiz.getPlanprojectType())){
                                planprojectbatchInBizService.delete(batchInBiz);
                            }
                        });

                        //查询该小批次是否有该新增业务类型
                        PmsPlanprojectbatchInBiz pmsPlanprojectbatchInBiz = planprojectbatchInBizService.getPmsPlanprojectbatchInBiz(id, type.getCode());
                        if (pmsPlanprojectbatchInBiz == null){
                            //没有，获取该小批次最新一条为模板创建新业务类型记录
                            PmsPlanprojectbatchInBiz last = planprojectbatchInBizService.getLastByBatchidAndCode(id, code);
                            if (last != null) {
                                PmsPlanprojectbatchInBiz newBiz = new PmsPlanprojectbatchInBiz();
                                BeanUtil.copyProperties(last, newBiz);
                                newBiz.setCategoryCode(code);
                                newBiz.setId(UUID.randomUUID().toString());
                                newBiz.setSeq(last.getSeq()+1);
                                //根据当前业务类型id查询对象
                                PmsPlantype pmsPlantype = pmsPlantypeService.getById(type.getId());
                                newBiz.setPmsPlantype(pmsPlantype);
                                newBiz.setMiniBizDefId(null);
                                newBiz.setMiniBizVersionId(null);
                                planprojectbatchInBizService.saveOrUpdate(newBiz);
                            }
                        }
                    });
                });
            }else {
                //新增
                ppc = new PmsPlancategory();
                List<PmsPlantype> pmsPlantypeList = plancategoryDTO.getPmsPlantypeList();
                pmsPlantypeService.saveTypes(pmsPlantypeList);
                BeanUtil.copyProperties(plancategoryDTO, ppc);
                ppc.setCreateTime(new Date());
                ppc.setCreateUser(AuthUtil.getUserId());
                ppc.setDefaultType(defaultType);
                ppc.setRoleIds(plancategoryDTO.getRoleIds());
                ppc.setItemOrganCode(plancategoryDTO.getItemOrganCode());
                ppc.setOrganizeId(plancategoryDTO.getOrganizeId());
                ppc.setId(Util.NewGuid());
                ppc.setIsDelete(0);
            }
            ppc.setUpdateTime(new Date());
            ppc.setUpdateUser(AuthUtil.getUserId());
            this.saveOrUpdate(ppc);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail("保存失败");
        }
        return R.success("保存成功");
    }

    /**
     * 拼接category的默认业务串
     * @param pmsPlantypeList
     * @return
     */
    private String makeDefaultType(List<PmsPlantype> pmsPlantypeList) {
        StringBuilder builder = new StringBuilder();
        Set<String> codeSet = new HashSet<>();
        pmsPlantypeList.forEach(pmsPlantype -> {
            if (!codeSet.contains(pmsPlantype.getCode())){
                codeSet.add(pmsPlantype.getCode());
                builder.append(pmsPlantype.getCode()).append(";");
            }
        });
        return String.valueOf(builder);
    }

    /**
     * 传入平台类型编码，根据defaultType创建biz记录
     * @param code
     * @param id planprojectbatchid
     * @return
     */
    public R createBizByTypes(String code,String id){
        HqlBuilder<PmsPlancategory> categoryHql = new HqlBuilder<>();
        categoryHql.eq("code",code);
        List<PmsPlancategory> plancategories = getPmsPlanCategoryDao().find(categoryHql);
        if (plancategories.size()>0){
            PmsPlancategory pmsPlancategory = plancategories.get(0);
            if (!StringUtils.isEmpty(pmsPlancategory.getDefaultType())){
                String[] split = pmsPlancategory.getDefaultType().split(";");
                int seq = 1;
                for (String type : split) {
                    PmsPlantype pmsPlantype = pmsPlantypeService.getPmsPlantype(type);
                    PmsPlanprojectbatchInBiz inBiz = new PmsPlanprojectbatchInBiz();
                    inBiz.setId(Util.NewGuid());
                    inBiz.setPmsPlantype(pmsPlantype);
                    inBiz.setSeq(seq);
                    inBiz.setCategoryCode(code);
                    inBiz.setPmsPlanprojectbatchId(id);
                    planprojectbatchInBizService.merge(inBiz);
                    seq++;
                }
            }else {
                return R.success("无默认业务类型");
            }
        }else {
            return R.fail("未找到对应平台");
        }
        return R.success("创建成功");
    }

    public R<List<PmsPlancategoryVO>> getAll(PmsPlancategory plancategory) {
        List<PmsPlancategoryVO> voList = new ArrayList<>();
        //拼接角色条件过滤
//        HqlBuilder<PmsPlancategory> hqlBuilder = super.getHqlBuilder();
        HqlBuilder<PmsPlancategory> hqlBuilder = new HqlBuilder<>();
//        if (hqlBuilder == null) {
//            return R.fail("请检查传参");
//        }
        hqlBuilder.eq("is_delete", 0)
                .like(!Util.isEoN(plancategory.getName()),PmsPlancategory::getName,plancategory.getName())
                .like(!Util.isEoN(plancategory.getCode()),PmsPlancategory::getCode,plancategory.getCode());

        List<PmsPlancategory> plancategories = getPmsPlanCategoryDao().find(hqlBuilder);
//        List<PmsPlancategory> plancategories = this.findAll();
        plancategories.forEach(category->{
            PmsPlancategoryVO plancategoryVO = new PmsPlancategoryVO();
            BeanUtil.copyProperties(category,plancategoryVO);
            String[] split = category.getDefaultType().split(";");
            List<PmsPlantype> list = new ArrayList<>();
            for (String s : split) {
                PmsPlantype pmsPlantype = pmsPlantypeService.getPmsPlantype(s);
                if (pmsPlantype != null) {
                    list.add(pmsPlantype);
                }
            }
            plancategoryVO.setPmsPlantypeList(list);
            voList.add(plancategoryVO);
        });
        return R.data(voList, "查询成功");
    }
}
