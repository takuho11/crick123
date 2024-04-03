/**
 * 本代码由代码生成工具自动生成
 * 创建时间 : 2016-2-22 14:53:22
 */
package cn.topcheer.pms2.biz.sys;

import cn.topcheer.halberd.app.api.framework.entity.sys.PmsEnterprise;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.app.dao.jpa.HqlBuilder;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.plan.entity.PmsPlanproject;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatch;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.sys.PmsEnterpriselimit;
import cn.topcheer.pms2.api.sys.PmsEnterpriselimitdetail;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectService;
import cn.topcheer.pms2.biz.plan.service.impl.PmsPlanprojectbatchService;
import cn.topcheer.pms2.biz.pml.service.impl.enumUtil.TableEnum;
import cn.topcheer.pms2.biz.project.service.impl.enumUtil.TableMappingEnum;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.common.enumerate.ErrorCodeEnum;
import cn.topcheer.pms2.common.exception.BusinessException;
import cn.topcheer.pms2.dao.sys.PmsEnterpriselimitDao;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.SpringUtil;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * PmsEnterpriselimit 服务类
 */
@Service(value = "PmsEnterpriselimitService")
public class PmsEnterpriselimitService extends GenericService<PmsEnterpriselimit> {

    public PmsEnterpriselimitDao getPmsEnterpriselimitDao() {
        return (PmsEnterpriselimitDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsEnterpriselimitDao(PmsEnterpriselimitDao pmsEnterpriselimitDao) {

        this.setGenericDao(pmsEnterpriselimitDao);
    }

    @Autowired
    private DBHelper dbHelper;
    @Autowired
    private SysOperationrecordService sysOperationrecordService;
    @Autowired
    private PmsEnterpriselimitdetailService pmsEnterpriselimitdetailService;
    @Autowired
    private PmsPlanprojectbatchService pmsPlanprojectbatchService;
    @Autowired
    private PmsEnterpriseService pmsEnterpriseService;
    @Autowired
    private SysGuideService sysGuideService;
    @Autowired
    private PmsPlanprojectService pmsPlanprojectService;

    /**
     * 【获取单位指标】
     */
    public List<Map> initEnterpriselimit(JSONObject json) {
        String sql = "select k.*,(case when k.isable = 1 then '启动' else '未启动' end) as state from pms_enterpriselimit k where 1=1 ";
        JSONArray array = new JSONArray();
        if (!Util.isEoN(json.get("batchid") + "")) {
            sql += " and k.batchid = ?";
            array.add(json.get("batchid") + "");
        }
        if (!Util.isEoN(json.get("filter") + "")) {
            sql += " and (k.enterprisename like ? or k.excelenterprisename like ?)";
            array.add("%" + json.get("filter") + "%");
            array.add("%" + json.get("filter") + "%");
        }
        if (!Util.isEoN(json.get("state") + "")) {
            sql += " and k.importstate = ? ";
            array.add(json.get("state") + "");
        }
        sql += " order by k.batchname,k.limitnum";
        List<Map> list = this.dbHelper.getRows(sql, array.toArray());
        return list;
    }


    /**
     * 审核的时候调用
     * 方向指标
     *
     * @return JSONObject
     * @author j
     */
    public JSONObject updateDirectionLimit(String projectbaseid, String type, Integer count, String result) throws Exception {
        JSONObject obj = new JSONObject();
        String planbatchid = "";
        String directionid = "";
        String childdirectionid = "";
        String direction = "";
        String directionchild = "";
        String serviceName = TableMappingEnum.getEnumByClassname(TableEnum.valueOf(type).getClassname()).getServiceclassname().getSimpleName();
        Object object = Util.springInvokeMethod(serviceName, "findById", new Object[]{projectbaseid});

        // 一级推荐单位，归口单位
        if (!Util.isExistFieldName("citycasemanagementid", object)) {
            throw new ServiceException("主表中缺少字段：citycasemanagementid");
        }

        // 归口单位id
        PropertyDescriptor pd = new PropertyDescriptor("citycasemanagementid", object.getClass());
        Method rm = pd.getReadMethod();
        String enterpriseid = (String) rm.invoke(object);

        //支持方向id
        if (Util.isExistFieldName("supportdirectionid", object)) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("supportdirectionid", object.getClass());
            Method readMethod = propertyDescriptor.getReadMethod();
            directionid = (String) readMethod.invoke(object);
        }
        //支持子方向id
        if (Util.isExistFieldName("supportdirectionchildid", object)) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("supportdirectionchildid", object.getClass());
            Method readMethod = propertyDescriptor.getReadMethod();
            childdirectionid = (String) readMethod.invoke(object);
        }
        //支持方向
        if (Util.isExistFieldName("supportdirection", object)) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("supportdirection", object.getClass());
            Method readMethod = propertyDescriptor.getReadMethod();
            direction = (String) readMethod.invoke(object);
        }
        //支持子方向
        if (Util.isExistFieldName("supportdirectionchild", object)) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("supportdirectionchild", object.getClass());
            Method readMethod = propertyDescriptor.getReadMethod();
            directionchild = (String) readMethod.invoke(object);
        }
        if (Util.isExistFieldName("planprojectbatchid", object)) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("planprojectbatchid", object.getClass());
            Method readMethod = propertyDescriptor.getReadMethod();
            planbatchid = (String) readMethod.invoke(object);
        }
        String batchname = this.pmsPlanprojectbatchService.findById(planbatchid).getName();

        if ("pass".equals(result)) {
            PmsEnterpriselimit pmsEnterpriselimit = ((PmsEnterpriselimitService) AopContext.currentProxy()).getPmsEnterpriseDirectionLimitIfNull(planbatchid, projectbaseid, type, count);
            if (pmsEnterpriselimit != null) {
                Integer limitnum = pmsEnterpriselimit.getLimitnum();
//				int recommendnum = this.getListBySql("select * " +
//								" from PMS_ENTERPRISELIMITDETAIL " +
//								" where batchid = ? and enterpriseid = ? and directionid = ? ",
//						new Object[]{planbatchid, enterpriseid, directionid}).size();
                int recommendnum = pmsEnterpriselimit.getRecommendnum();
                //如果已推荐数量小于指标，则可以推荐
                if (recommendnum < limitnum) {
                    //二级单位审核 占用指标
                    pmsEnterpriselimit.setRecommendnum(pmsEnterpriselimit.getRecommendnum() + 1);
                    this.merge(pmsEnterpriselimit);

                    PmsEnterpriselimitdetail pps = new PmsEnterpriselimitdetail();
                    String limitdetailid = UUID.randomUUID().toString();
                    pps.setId(limitdetailid);
                    pps.setProjectbaseid(projectbaseid);
                    //某存在不同批次共用同一指标
                    pps.setBatchid(planbatchid);
                    pps.setEnterpriseid(enterpriseid);
                    pps.setCreatedate(new Date());
                    pps.setState("推荐");
                    pps.setDirectionid(directionid);
                    pps.setChilddirectionid(childdirectionid);
                    this.pmsEnterpriselimitdetailService.merge(pps);
                    sysOperationrecordService.commonSaveOperation(pmsEnterpriselimit.getId(), "审核更新指标--推荐", "批次id：" + planbatchid + ",项目id：" + projectbaseid + ",单位id：" + enterpriseid + "指标id：" + pmsEnterpriselimit.getId() + "，指标详细表id：" + limitdetailid + "，方向id：" + directionid + "，子方向id：" + childdirectionid);


                    obj.put("result", true);
                    obj.put("note", "");
                } else {
                    String res = "";
                    res = !Util.isEoN(directionchild) ? directionchild : direction;
                    res = !Util.isEoN(direction) ? direction : batchname;
                    obj.put("result", false);
                    obj.put("note", "申报单位关于" + res + "方向的指标已用完");
                }
            } else {
                obj.put("result", true);
                obj.put("note", "该方向未设置指标");
            }

        } else if ("back".equals(result)) {
            //组织单位退回，退回指标
            List<PmsEnterpriselimitdetail> pp = pmsEnterpriselimitdetailService.findByProperty("projectbaseid", projectbaseid);
            String limitdetailid;
            if (pp.size() > 0) {
                limitdetailid = pp.get(0).getId();
                PmsEnterpriselimit pmsEnterpriselimit = ((PmsEnterpriselimitService) AopContext.currentProxy()).getPmsEnterpriseDirectionLimitIfNull(planbatchid, projectbaseid, type, count);
                if (pmsEnterpriselimit != null) {
                    /*把原来的也删除*/
                    pmsEnterpriselimitdetailService.deleteById(limitdetailid);
                    sysOperationrecordService.commonSaveOperation(pmsEnterpriselimit.getId(), "审核更新指标--退回", "批次id：" + planbatchid + ",项目id：" + projectbaseid + ",单位id：" + enterpriseid + "指标id：" + pmsEnterpriselimit.getId() + "，指标详细表id：" + limitdetailid + "，type：" + type + "，方向id：" + directionid + "，子方向id：" + childdirectionid);
                    pmsEnterpriselimit.setRecommendnum(pmsEnterpriselimit.getRecommendnum() - 1);
                    this.merge(pmsEnterpriselimit);
                    obj.put("result", true);
                    obj.put("note", "");
                } else {
                    obj.put("result", false);
                    obj.put("note", "本单位未设置指标");
                }
            } else {
                obj.put("result", true);
                obj.put("note", "无法退回，未找到对应的审核通过记录");
            }

        } else {
            obj.put("result", false);
            obj.put("note", "审核结果参数不对");
        }
        return obj;
    }

    /**
     * 获取单位方向指标()  有限判断子方向指标，再判断方向指标，最后判断批次指标
     */
    public PmsEnterpriselimit getPmsEnterpriseDirectionLimitIfNull(String planbatchid, String mainid, String type, Integer count) throws Exception {
        //通过类型找到service
        String serviceName = TableMappingEnum.getEnumByClassname(TableEnum.valueOf(type).getClassname()).getServiceclassname().getSimpleName();
        //找到对应 类对象
        Object object = Util.springInvokeMethod(serviceName, "findById", new Object[]{mainid});
        String directionid = "";
        String childdirectionid = "";
        String insql = " and 1 = 1 ";

        // 一级推荐单位，归口单位
        if (!Util.isExistFieldName("citycasemanagementid", object)) {
            throw new ServiceException("主表中缺少字段：citycasemanagementid");
        }

        // 归口单位id
        PropertyDescriptor pd = new PropertyDescriptor("citycasemanagementid", object.getClass());
        Method rm = pd.getReadMethod();
        String enterpriseid = (String) rm.invoke(object);

        if (Util.isExistFieldName("supportdirectionid", object)) {
            //有方向id
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("supportdirectionid", object.getClass());
            Method readMethod = propertyDescriptor.getReadMethod();
            //拿到对象的方向id
            directionid = (String) readMethod.invoke(object);
            if (!Util.isEoN(directionid)) {
                if (!Util.isEoN(sysGuideService.findById(directionid).getLimitnum())) {
                    //拿到 公共的 关于该方向的指标限额（优先级低）
                    count = sysGuideService.findById(directionid).getLimitnum();
                    insql = insql + " and directionid ='" + directionid + "'";
                }
            }
        }
        if (Util.isExistFieldName("supportdirectionchildid", object)) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("supportdirectionchildid", object.getClass());
            Method readMethod = propertyDescriptor.getReadMethod();
            childdirectionid = (String) readMethod.invoke(object);
            if (!Util.isEoN(childdirectionid)) {
                if (!Util.isEoN(sysGuideService.findById(childdirectionid).getLimitnum())) {
                    //拿到 公共的 关于该子方向的指标限额（优先级高）
                    count = sysGuideService.findById(childdirectionid).getLimitnum();
                    insql = insql + " and childdirectionid ='" + childdirectionid + "'";
                }
            }

        }
        String hql = "select t from PmsEnterpriselimit t where batchid= ?0 and enterpriseid=?1 and type = ?2 " + insql;
        PmsEnterpriselimit pmsEnterpriselimit = null;
        Query query2 = this.getPmsEnterpriselimitDao().getQuery(hql);
        query2.setParameter(0, planbatchid);
        query2.setParameter(1, enterpriseid);
        query2.setParameter(2, type);
        PmsEnterpriselimit o = (PmsEnterpriselimit) query2.uniqueResult();
        if (!Util.isEoN(o)) {
            pmsEnterpriselimit = o;
        } else {
            pmsEnterpriselimit = new PmsEnterpriselimit();
            pmsEnterpriselimit.setId(UUID.randomUUID().toString());
            pmsEnterpriselimit.setIsable(1);
            pmsEnterpriselimit.setEnterpriseid(enterpriseid);
            PmsEnterprise pmsEnterprise = this.pmsEnterpriseService.findById(enterpriseid);
            pmsEnterpriselimit.setEnterprisename(pmsEnterprise.getName());
            pmsEnterpriselimit.setBatchid(planbatchid);
            pmsEnterpriselimit.setBatchname(Optional.ofNullable(this.pmsPlanprojectbatchService.findById(planbatchid))
                    .map(m -> m.getName()).orElse(""));
            pmsEnterpriselimit.setLimitnum(count);
            pmsEnterpriselimit.setRecommendnum(0);
            pmsEnterpriselimit.setType(type);
            pmsEnterpriselimit.setDirectionid(directionid);
            pmsEnterpriselimit.setChilddirectionid(childdirectionid);
            this.getPmsEnterpriselimitDao().merge(pmsEnterpriselimit);
        }
        return pmsEnterpriselimit;
    }


    /**
     * 获取单位方向指标()  有限判断子方向指标，再判断方向指标，最后判断批次指标
     */
    public ReturnToJs getPmsEnterpriseDirectionLimitIfNullMul(String planbatchid, List<String> mainids, String type, Integer count) throws Exception {

        String serviceName = TableMappingEnum.getEnumByClassname(TableEnum.valueOf(type).getClassname()).getServiceclassname().getSimpleName();

        // 查询列表
        Object service = SpringUtil.getBean(serviceName);
        Method method_findByIds = null;
        for (Method method : service.getClass().getMethods()) {
            if (method.getParameterCount() == 1 && "findByIds".equals(method.getName())) {
                method_findByIds = method;
            }
        }
        List<Object> objList = (List<Object>) method_findByIds.invoke(service, mainids);
        //List<Object> objList = (List) Util.springInvokeMethod(serviceName, "findByIds", new Object[]{mainids});
        if (objList == null) {
            throw new BusinessException("getPmsEnterpriseDirectionLimitIfNullMul,入参有误");
        }


        Object newInstance = TableEnum.valueOf(type).getClassname().newInstance();
        Class newClass = TableEnum.valueOf(type).getClassname();


        // 一级推荐单位，归口单位
        if (!Util.isExistFieldName("citycasemanagementid", newInstance)) {
            throw new ServiceException("主表中缺少字段：citycasemanagementid");
        }

        // 归口单位id
        PropertyDescriptor propertyDescriptor_dw = new PropertyDescriptor("citycasemanagementid", newClass);
        Method readMethod_dw = propertyDescriptor_dw.getReadMethod();


        // 业务id 方向id 子方向id 反射获取
        Boolean hasSupportdirectionid = Util.isExistFieldName("supportdirectionid", newInstance);
        Boolean hasSupportdirectionchildid = Util.isExistFieldName("supportdirectionchildid", newInstance);

        Method readMethod_fx = null;
        if (hasSupportdirectionid) {
            PropertyDescriptor propertyDescriptor_fx = new PropertyDescriptor("supportdirectionid", newClass);
            readMethod_fx = propertyDescriptor_fx.getReadMethod();
        }

        Method readMethod_zfx = null;
        if (hasSupportdirectionchildid) {
            PropertyDescriptor propertyDescriptor_zfx = new PropertyDescriptor("supportdirectionchildid", newClass);
            readMethod_zfx = propertyDescriptor_zfx.getReadMethod();
        }

        PropertyDescriptor propertyDescriptor_id = new PropertyDescriptor("id", newClass);
        Method readMethod_id = propertyDescriptor_id.getReadMethod();

        Method finalReadMethod_fx = readMethod_fx;
        Method finalReadMethod_zfx = readMethod_zfx;
        Map<String, List<HashMap<Object, Object>>> collect = objList.stream().map(m -> {
            HashMap<Object, Object> hashMap = new HashMap<>(3);
            try {
                hashMap.put("id", readMethod_id.invoke(m));
                if (hasSupportdirectionid) {
                    hashMap.put("directionid", finalReadMethod_fx.invoke(m) != null ? finalReadMethod_fx.invoke(m) : "");
                } else {
                    hashMap.put("directionid", "");
                }
                if (hasSupportdirectionchildid) {
                    hashMap.put("childdirectionid", finalReadMethod_zfx.invoke(m) != null ? finalReadMethod_fx.invoke(m) : "");
                } else {
                    hashMap.put("childdirectionid", "");
                }
                hashMap.put("enterpriseid", readMethod_dw.invoke(m));
                hashMap.put("keys", planbatchid + hashMap.get("enterpriseid") + type + hashMap.get("directionid") + hashMap.get("childdirectionid"));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new BusinessException(ErrorCodeEnum.PARAM_FORMAT_ERROR, e);
            } finally {
                return hashMap;
            }
        }).collect(Collectors.groupingBy(m -> m.get("keys").toString(), Collectors.toList()));

        List<HashMap> list = new ArrayList<>();
        for (Map.Entry<String, List<HashMap<Object, Object>>> entry : collect.entrySet()) {
            // 归口单位id
            String enterpriseid = (String) entry.getValue().get(0).get("enterpriseid");

            String insql = " and 1 = 1 ";
            if (!Util.isEoN(entry.getValue().get(0).get("directionid"))) {
                if (!Util.isEoN(sysGuideService.findById(entry.getValue().get(0).get("directionid").toString()).getLimitnum())) {
                    count = sysGuideService.findById(entry.getValue().get(0).get("directionid").toString()).getLimitnum();
                    insql = insql + " and directionid ='" + entry.getValue().get(0).get("directionid").toString() + "'";
                }
            }
            if (!Util.isEoN(entry.getValue().get(0).get("childdirectionid"))) {
                if (!Util.isEoN(sysGuideService.findById(entry.getValue().get(0).get("childdirectionid").toString()).getLimitnum())) {
                    count = sysGuideService.findById(entry.getValue().get(0).get("childdirectionid").toString()).getLimitnum();
                    insql = insql + " and childdirectionid ='" + entry.getValue().get(0).get("childdirectionid") + "'";
                }
            }
            PmsEnterpriselimit pmsEnterpriselimit = null;
            String hql = "select t from PmsEnterpriselimit t where batchid= ?0 and enterpriseid= ?1 and type = ?2 " + insql;
            Query query2 = this.getPmsEnterpriselimitDao().getQuery(hql);
            //基金项目特殊处理
            String limitBatchid = "";
            PmsPlanprojectbatch pmsPlanprojectbatch = this.pmsPlanprojectbatchService.findById(planbatchid);
            if (!Util.isEoN(pmsPlanprojectbatch.getLimitbatchid())) {
                limitBatchid = pmsPlanprojectbatch.getLimitbatchid();
                query2.setParameter(0, limitBatchid);
            } else {
                query2.setParameter(0, planbatchid);
            }
            query2.setParameter(1, enterpriseid);
            query2.setParameter(2, type);
            PmsEnterpriselimit o = (PmsEnterpriselimit) query2.uniqueResult();
            if (!Util.isEoN(o)) {
                pmsEnterpriselimit = o;
            } else {
                pmsEnterpriselimit = new PmsEnterpriselimit();
                pmsEnterpriselimit.setId(UUID.randomUUID().toString());
                pmsEnterpriselimit.setIsable(1);
                pmsEnterpriselimit.setEnterpriseid(enterpriseid);
                PmsEnterprise pmsEnterprise = this.pmsEnterpriseService.findById(enterpriseid);
                pmsEnterpriselimit.setEnterprisename(pmsEnterprise.getName());
                pmsEnterpriselimit.setBatchid(planbatchid);
                pmsEnterpriselimit.setBatchname(this.pmsPlanprojectbatchService.findById(planbatchid).getName());
                pmsEnterpriselimit.setLimitnum(count);
                pmsEnterpriselimit.setRecommendnum(0);
                pmsEnterpriselimit.setType(type);
                pmsEnterpriselimit.setDirectionid(entry.getValue().get(0).get("directionid").toString());
                pmsEnterpriselimit.setChilddirectionid(entry.getValue().get(0).get("childdirectionid").toString());
                this.getPmsEnterpriselimitDao().merge(pmsEnterpriselimit);
            }
            PmsEnterpriselimit finalPmsEnterpriselimit = pmsEnterpriselimit;
            List<HashMap<Object, Object>> mapList = entry.getValue().stream().map(m -> {
                m.put("limitnum", finalPmsEnterpriselimit.getLimitnum());
                m.put("recommendnum", finalPmsEnterpriselimit.getRecommendnum());
                return m;
            }).collect(Collectors.toList());
            list.addAll(mapList);
        }

        return ReturnToJs.success(list);
    }


    /**
     * 根据 id 修改指标限额
     */
    public void editLimitNum(String id, String newlimitnum, HttpServletRequest request) {
        PmsEnterpriselimit limit = this.findById(id);
        String content = "指标限额数量由" + limit.getLimitnum() + "个修改为" + newlimitnum + "个。";
        limit.setLimitnum(Integer.parseInt(newlimitnum));
        this.merge(limit);
        //操作记录
        this.sysOperationrecordService.commonSaveOperation(request, id, "指标限额修改", content);
    }

    /**
     * 根据 id 修改指标限额
     */
    public JSONObject editEndtime(String id, String endtime, HttpServletRequest request) {
        JSONObject resJson = new JSONObject();
        PmsEnterpriselimit limit = this.findById(id);
        Date time = limit.getEndtime();
        String content;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (Util.isEoN(endtime)) {
            content = "审核截止时间由" + sdf.format(time) + "修改为不设置。";
            limit.setEndtime(null);
            this.merge(limit);
            //操作记录
            this.sysOperationrecordService.commonSaveOperation(request, id, "指标限额修改", content);

            return Util.dealResJson(resJson, true);
        }
        String batchid = limit.getBatchid();
        if (Util.isEoN(batchid)) {
            return Util.dealResJson(resJson, false, "未获取到批次ID");
        }
        PmsPlanprojectbatch batch = pmsPlanprojectbatchService.findById(batchid);
        //合同结束时间
        Date batchEndtime;
        Date recommendedunitendtime = batch.getRecommendedunitendtime();
        if (recommendedunitendtime == null) {
            Date endtime1 = batch.getEndtime();
            if (endtime1 == null) {
                return Util.dealResJson(resJson, false, "未获取到批次结束时间");
            } else {
                batchEndtime = endtime1;
            }
        } else {
            batchEndtime = recommendedunitendtime;
        }

        //准备设置的结束时间
        Date date;
        try {
            date = sdf.parse(endtime);
        } catch (Exception e) {
            e.printStackTrace();
            return Util.dealResJson(resJson, false, "前台参数错误");
        }
        if (date.compareTo(batchEndtime) == 1) {
            return Util.dealResJson(resJson, false, "设置的时间必须早于批次的截止时间(" + simpleDateFormat.format(batchEndtime) + ")");
        }

        if (time == null) {
            content = "审核截止时间由" + "未设置修改为" + endtime + "。";
        } else {
            content = "审核截止时间由" + sdf.format(time) + "修改为" + endtime + "。";
        }

        limit.setEndtime(date);
        this.merge(limit);
        //操作记录
        this.sysOperationrecordService.commonSaveOperation(request, id, "指标限额修改", content);

        return Util.dealResJson(resJson, true);
    }


    /**
     * 新增新单位指标
     */
    public JSONObject addLimitNum(JSONObject json, HttpServletRequest request) {
        JSONObject resJson = new JSONObject();
        String batchid = json.get("batchid") + "";
        String enterprisename = json.get("enterprisename") + "";
        String limitnum = json.get("limitnum") + "";
        String type = json.get("type") + "";
        //判断批次
//		String batchid = this.getOnlyValueBySql("select id from pms_planprojectbatch where name = ?",new Object[]{batchname});
//		if(Util.isEoN(batchid)){
//			resJson.put("success",false);
//			resJson.put("reason","当前批次名称在后台不存在。");
//			return resJson;
//		}
        //判断单位名称
        String enterpriseid = this.dbHelper.getOnlyStringValue("select id from pms_enterprise where name = ? and state = 0", new Object[]{enterprisename});
        if (Util.isEoN(enterpriseid)) {
            resJson.put("success", false);
            resJson.put("reason", "当前单位名称在系统中未搜索到，请联系后台人员查询，或等待法人注册。");
            return resJson;
        }
        //判断是否存在
        String limitid = this.dbHelper.getOnlyStringValue("select id from pms_enterpriselimit where enterpriseid = ? and batchid = ?", new Object[]{enterpriseid, batchid});
        if (!Util.isEoN(limitid)) {
            resJson.put("success", false);
            resJson.put("reason", "当前批次下，该单位名称已设置指标限额，可在列表搜索后进行修改。");
            return resJson;
        }

        String batchname = this.dbHelper.getOnlyStringValue("select name from sys_parameter where value = ?", new Object[]{batchid});

        PmsEnterpriselimit limit = new PmsEnterpriselimit();
        limit.setId(Util.NewGuid());
        limit.setIsable(1);
        limit.setEnterpriseid(enterpriseid);
        limit.setEnterprisename(enterprisename);
        limit.setBatchid(batchid);
        limit.setBatchname(batchname);
        limit.setLimitnum(Integer.parseInt(limitnum));
        limit.setRecommendnum(0);
        limit.setImportstate("成功");
        limit.setExcelenterprisename(enterprisename);
        limit.setImportdate(new Date());
        limit.setType(type);
        this.merge(limit);
        //操作记录
        this.sysOperationrecordService.commonSaveOperation(request, limit.getId(), "指标限额修改", "新增指标限额数量" + limit.getLimitnum() + "个");
        resJson.put("success", true);
        return resJson;
    }


    /**
     * 根据 id 删除指标限额
     */
    public void deleteData(String id, HttpServletRequest request) {
        PmsEnterpriselimit limit = this.findById(id);
        //操作记录
        this.sysOperationrecordService.commonSaveOperation(request, id, "指标限额删除", "删除指标限额，批次名称：" + limit.getBatchname()
                + "，单位名称：" + limit.getEnterprisename());
        this.delete(limit);
    }

    public String importByExcelFile(HttpServletRequest request, List<Object> read) {
        Map<Integer, String> row0 = (Map<Integer, String>) read.get(0);
        int rsColumns = row0.size();// 列数
        int rsRows = read.size();// 行数
        String simNumber = "";// 每个单元格中的数据
        String str = "";// 拼接要插入的列
        String[] srcColumnTable = new String[rsColumns];
        srcColumnTable[0] = "单位名称";
        srcColumnTable[1] = "指标";
        srcColumnTable[2] = "批次名称";
        srcColumnTable[3] = "截止时间";
        srcColumnTable[4] = "类型";
        srcColumnTable[5] = "支持方向";
            /*for (int i = 0; i < (rsColumns - 10); i++) {
                srcColumnTable[10 + i] = "$" + (i + 2) + "期";
            }*/
        for (int j = 0; j < rsColumns; j++) {
            if (!srcColumnTable[j].equals(row0.get(j))) {
                return "导入的列与提供的模版不一致";
            }
        }
        List<PmsEnterpriselimit> list = null;
        PmsEnterpriselimit PmsEnterpriselimit = null;

        String sql = "";
        String enterpriseid = "";
        Date date = new Date();

        for (int i = 1; i < rsRows; i++) {
            String errorStr = "";
            // 根据单位名称查找该单位，更新该批次该单位的指标表
            Map<Integer, String> rowI = (Map<Integer, String>) read.get(i);
            simNumber = rowI.get(0).trim();//获得单位名称
            String batchname = rowI.get(2).trim();//获得批次名称
            String batchHql = "from PmsPlanprojectbatch where name = ?0";
            List<PmsPlanprojectbatch> batch = this.pmsPlanprojectbatchService.findByHql(batchHql, batchname);
            if(batch == null || batch.size() == 0){
                return "未找到该批次";
            }
            String batchid = batch.get(0).getId();
            if (!Util.isEoN(simNumber)) {
                sql = "from PmsEnterprise where name = ?0 ";
                List<PmsEnterprise> listenter = this.findByHql(sql, new Object[]{simNumber});
                if (listenter.size() == 1) { //如果找到该单位并且该单位为1个就插入该单位id
                    sql = "from PmsEnterpriselimit where batchid = ?0 and enterpriseid = ?1 ";
                    enterpriseid = listenter.get(0).getId();
                    list = this.findByHql(sql, new Object[]{batchid, enterpriseid});
                    if (list.size() > 0) {
                        PmsEnterpriselimit = list.get(0);
                    } else {
                        PmsEnterpriselimit = new PmsEnterpriselimit();
                        PmsEnterpriselimit.setId(UUID.randomUUID().toString());
                        PmsEnterpriselimit.setBatchid(batchid);
                        PmsEnterpriselimit.setEnterpriseid(enterpriseid);
                        PmsEnterpriselimit.setEnterprisename(simNumber);
                        PmsEnterpriselimit.setExcelenterprisename(simNumber);
                    }
                } else if (listenter.size() >= 1) {//如果单位大于1个
                    sql = "from PmsEnterpriselimit where batchid = ?0 and excelenterprisename = ?1 ";
                    list = this.findByHql(sql, new Object[]{batchid, simNumber});
                    if (list.size() > 0) {
                        PmsEnterpriselimit = list.get(0);
                        errorStr += "单位大于一个、";
                    } else {
                        PmsEnterpriselimit = new PmsEnterpriselimit();
                        PmsEnterpriselimit.setId(UUID.randomUUID().toString());
                        PmsEnterpriselimit.setBatchid(batchid);
                        PmsEnterpriselimit.setExcelenterprisename(simNumber);
                        errorStr += "单位大于一个、";
                    }
                } else {//未找到单位
                    sql = "from PmsEnterpriselimit where batchid = ?0 and excelenterprisename = ?1 ";
                    list = this.findByHql(sql, new Object[]{batchid, simNumber});
                    if (list.size() > 0) {
                        PmsEnterpriselimit = list.get(0);
                        errorStr += "未找到单位、";
                    } else {
                        PmsEnterpriselimit = new PmsEnterpriselimit();
                        PmsEnterpriselimit.setId(UUID.randomUUID().toString());
                        PmsEnterpriselimit.setBatchid(batchid);
                        PmsEnterpriselimit.setExcelenterprisename(simNumber);
                        errorStr += "未找到单位、";
                    }
                }
            } else {
                errorStr += "单位名称有问题、";
            }
            if (!Util.isEoN(rowI.get(1))) {
                int limitnum = Integer.parseInt(rowI.get(1).replaceAll(" ", ""));//获得单位指标
                PmsEnterpriselimit.setLimitnum(limitnum);
            } else {
                errorStr += "单位指标导入失败、";
            }
            if (!Util.isEoN(rowI.get(2))) {
                simNumber = rowI.get(2).replaceAll(" ", "");//获得批次名称
                PmsEnterpriselimit.setBatchname(simNumber);
            } else {
                errorStr += "批次名称导入失败、";
            }
            if (!Util.isEoN(rowI.get(3))) {
                simNumber = rowI.get(3);//获得截止时间
                try {
                    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date endtime = sim.parse(simNumber);
                    if (endtime.after(batch.get(0).getEndtime())) {
                        errorStr += "截止时间导入失败,截止时间不能晚于该批次截止时间、";
                    } else {
                        PmsEnterpriselimit.setEndtime(endtime);
                    }
                } catch (ParseException e) {
                    errorStr += "截止时间导入失败,请检查时间格式、";
                    e.printStackTrace();
                }
            }
            if (!Util.isEoN(rowI.get(4))) {
                simNumber = rowI.get(4).replaceAll(" ", "");
                PmsEnterpriselimit.setType(simNumber);
            } else {
                errorStr += "类型导入失败、";
            }
            if (!Util.isEoN(rowI.get(5))) {
                simNumber = rowI.get(5).replaceAll(" ", "");
                PmsEnterpriselimit.setDirectionid(simNumber);
            } else {
                errorStr += "支持方向导入失败、";
            }

            PmsEnterpriselimit.setFailreason(errorStr);
            PmsEnterpriselimit.setRecommendnum(0);
            PmsEnterpriselimit.setIsable(0);

            if (Util.isEoN(errorStr)) {
                PmsEnterpriselimit.setImportstate("成功");
                PmsEnterpriselimit.setIsable(1);
            } else {
                PmsEnterpriselimit.setImportstate("失败");
                PmsEnterpriselimit.setIsable(0);
            }
            PmsEnterpriselimit.setImportdate(date);
            this.merge(PmsEnterpriselimit);
            // this.sysOperationrecordService.commonSaveOperation(request, batchid, "导入Excel单位名字", "导入单位指标记录，单位：" + enterprisename);
        }
        return null;
    }

    /**
     * 获取当前单位可选批次
     */
    public R getAllBigPlan() {
        String userId = AuthUtil.getUserId();
        HqlBuilder<PmsPlanproject> hqlBuilder = new HqlBuilder<>();
        hqlBuilder.addSelect("id as id").addSelect("projectname as name").eq("create_user", userId);
        List<PmsPlanproject> pmsPlanprojects = pmsPlanprojectService.find(hqlBuilder);
        return R.data(pmsPlanprojects);
    }

    /**
     * 获取批次分配指标的单位数据
     */
    public R getEnterpriseByBatch(String batchid, String serachStr, String type) {
        StringBuilder sql = new StringBuilder("select ent.id,ent.name ,ent.address,ent.linkman,ent.mobile," +
                "ent.unittype,ent.uniformcode ,mit.limitnum, mit.recommendnum " +
                "from Pms_EnterpriseLimit mit " +
                "inner join Pms_enterprise ent on mit.enterpriseid = ent.id " +
                "where mit.batchid = ? and mit.enterprisename like ? ");
        if (!Util.isEoN(type)) {
            sql.append("and ent.unittype = '"+type+"' ");
        }
        List<Map> rows = dbHelper.getRows(sql.toString(), new Object[]{batchid, "%" + serachStr + "%"});
        return R.data(rows);
    }

    /**
     * 获取一级单位数据(已完善信息)
     *
     * @param batchid
     * @param serachStr
     * @param type
     * @return
     */
    public R limitedOrganization(String batchid, String serachStr, String type) {
        serachStr = serachStr.trim();
        type = type.trim();
        if (Util.isEoN(batchid)) {
            //查单位信息
            StringBuilder sql = new StringBuilder("select ent.id,ent.name,ent.address," +
                    "ent.linkman,ent.mobile,ent.unittype,ent.uniformcode " +
                    "FROM Pms_Enterprise ent " +
                    "INNER JOIN SYS_IDENTITY iden ON ent.id = iden.PURVIEWORGANIZEID  " +
                    "AND iden.ROLEID  = '2A6D2039-4D9F-4B6A-ACBA-D4BEF7577487' " +
                    "WHERE (ent.name LIKE ? OR ent.UNIFORMCODE  LIKE ?) " );
            if (!Util.isEoN(type)) {
                sql.append("and ent.unittype = '" + type + "' ");
            }
            List<Map> rows = dbHelper.getRows(sql.toString(), new Object[]{"%" + serachStr + "%", "%" + serachStr + "%"});
            return R.data(rows);
        }else {
            return getEnterpriseByBatch(batchid,serachStr,type);
        }
    }
}
