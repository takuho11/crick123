package cn.topcheer.pms2.biz.pml.service.impl;


import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.halberd.flow.entity.BatchTaskJob;
import cn.topcheer.halberd.flow.service.FlowProcessService;
import cn.topcheer.pms2.api.pml.entity.PmlGrid;
import cn.topcheer.pms2.dao.pml.PmlGridDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by XL on 2019/3/12.
 */
@Service(value = "PmlCommonGridService")
public class PmlCommonGridService {
    @Autowired
    private DBHelper dbHelper;

    @Autowired
    private PmlGridDao pmlGridDao;
    @Autowired
    @Lazy
    private PmlGridService pmsGridService;
    @Autowired
    private FlAuthorityGridService flAuthorityGridService;

    @Autowired
    private FlowProcessService processService;

//    @Autowired
//    private PmlVerifyService pmsVerifyService;

//    @Autowired
//    private PmlProjectbaseService pmsProjectbaseService;
//
//    @Autowired
//    PmlOutInterfaceService pmsOutInterfaceService;
//    @Autowired
//    private SysParameterService sysParameterService;


    /**
     * 【通用列表】--通用获取数据源--通过sql
     *
     * @param json
     * @return
     */
    public PageResult<List<Map>> getDataBySql(JSONObject json, PmlGrid pmsGrid) {
        json.put("userid", AuthUtil.getUserId());
        //处理Where语句
        JSONObject whereJson = this.handleWhereConditions(json, pmsGrid.getWhereconditions());
        String whereConditons = whereJson.get("whereConditions") + "";//where条件语句
        List paramList = (List) whereJson.get("paramList");//条件参数
        //处理sql语句
        String sql = this.handleSqlHql(pmsGrid.getScript(), whereConditons);
        //分页配置
        JSONObject pageConfig = json.getJSONObject("pageConfig");
        pageConfig.put("pageSize", (int) pageConfig.get("pageSize") + 1);
        //分页处理
        PageResult<List<Map>> page = this.pmsGridService.findPageBySql(sql, paramList, Page.of(pageConfig));
        return page;
    }


    /**
     * 【通用列表】--通用获取数据源--通过hql
     *
     * @param json
     * @return
     */
    public PageResult<List<Map>> getDataByHql(JSONObject json, PmlGrid pmsGrid) {
        //处理Where语句
        JSONObject whereJson = this.handleWhereConditions(json, pmsGrid.getWhereconditions());
        String whereConditons = whereJson.get("whereConditions") + "";//where条件语句
        List paramList = (List) whereJson.get("paramList");//条件参数
        //处理hql语句
        String hql = this.handleSqlHql(pmsGrid.getScript(), whereConditons);
        //分页配置
        JSONObject pageConfig = json.getJSONObject("pageConfig");
        PageResult<List<Map>> page = this.pmsGridService.findPageBySql(hql, paramList, Page.of(pageConfig));
        return page;
    }

//    /**
//     * 【通用列表】--通用获取数据源，根据角色，列表url等配置信息获取，默认是sql语句
//     * @param json
//     * @return
//     */
//    public PageResult<List<Map>> getDataByConfig(JSONObject json){
//        PmlGrid pmsGrid = this.pmsGridService.findById(json.getString("gridid"));
//        //1、处理条件语句--列表前台配置的条件语句
//        JSONObject whereJson = this.handleWhereConditions(json,pmsGrid.getWhereconditions());
//        String whereConditons = whereJson.get("whereConditions")+"";//where条件语句
//        List paramList = (List)whereJson.get("paramList");//条件参数
//        //2、通过接口获取一个特殊的条件语句
//        String specialSql = this.flAuthorityGridService.getAuthorityGridSql(pmsGrid.getBusinesstype(),pmsGrid.getGridtype(),json.getString("userRoleid"),json.getString("userid"));
//        //3、重新拼接条件语句
//        whereConditons = whereConditons + " " +specialSql+" ";
//        //4、根据特殊条件语句，重新添加参数
//        paramList = this.handleSqlForParams(paramList,specialSql,json.getString("userid"));
//        //5、处理sql语句
//        String sql = this.handleSqlHql(pmsGrid.getScript(),whereConditons);
//        //6、分页配置
//        JSONObject pageConfig = json.getJSONObject("pageConfig");
//        //7、分页处理
//        PageResult<List<Map>> page = this.genericPageService.findPageBySql(sql, paramList, Page.of(pageConfig));
//        return page;
//
//    }

    /**
     * 【通用列表】--获取列表配置数据
     *
     * @param type
     * @return
     */
    public Map getConfigData(String type) {
        BladeUser securityUser = AuthUtil.getUser();
        if (Util.isEoN(securityUser)) {
            throw new ServiceException("未获取到当前登录人信息");
        }
        Map resMap = new HashMap();
        List<Map> resList = this.dbHelper.getRows("select e.* from pml_grid e where e.gridtype = ?", new Object[]{type});
        if (!Util.isEoN(resList) && resList.size() > 0) {
            //列表主表配置数据
            resMap = resList.get(0);
            //获取搜索框的主表配置数据
            String widthConfig = this.dbHelper.getOnlyStringValue("select e.widthconfig from pml_collection e where e.id = ?", new Object[]{resMap.get("collectionid")});
            resMap.put("widthconfig", Util.isEoN(widthConfig) ? "" : widthConfig);
            //获取搜索框的配置数据
            List<Map> searchboxList = this.dbHelper.getRows("select s.* from pml_searchbox_collection sc " +
                    "left join pml_searchbox s on s.id = sc.searchboxid " +
                    "where sc.collectionid = ? order by sc.seq", new Object[]{resMap.get("collectionid")});
            resMap.put("searchboxData", searchboxList);
            //获取列名的配置数据
            List<Map> columnList = this.dbHelper.getRows("select e.* from pml_column e " +
                    "left join pml_grid_column gc on e.id = gc.columnid " +
                    "where gc.gridid = ? order by gc.seq", new Object[]{resMap.get("id")});
            resMap.put("columnData", columnList);

            //获取全部按钮的配置数据
            List<Map> btnList = this.dbHelper.getRows("select e.* from pml_button e " +
                    "left join pml_grid_button gb on e.id = gb.buttonid " +
                    "where gb.gridid = ? order by gb.seq", new Object[]{resMap.get("id")});
            //获取其他按钮的配置数据
            resMap.put("otherBtnData", btnList.stream().filter(t -> "otherbtn".equals(t.get("buttontype"))).collect(Collectors.toList()));
            //获取操作列按钮的配置数据
            resMap.put("gridBtnData", btnList.stream().filter(t -> "gridbtn".equals(t.get("buttontype"))).collect(Collectors.toList()));
//            resMap.put("otherBtnData",otherBtnList);
//            //获取其他按钮的配置数据
//            List<Map> otherBtnList = this.dbHelper.getRows("select e.* from pml_button e " +
//                    "left join pml_grid_button gb on e.id = gb.buttonid " +
//                    "where e.buttontype = 'otherbtn' and gb.gridid = ? order by gb.seq",new Object[]{resMap.get("id")});
//            resMap.put("otherBtnData",otherBtnList);
//            //获取操作列按钮的配置数据
//            List<Map> gridBtnList = this.dbHelper.getRows("select e.* from pml_button e " +
//                    "left join pml_grid_button gb on e.id = gb.buttonid " +
//                    "where e.buttontype = 'gridbtn' and gb.gridid = ? order by gb.seq",new Object[]{resMap.get("id")});
//            resMap.put("gridBtnData",gridBtnList);
        }
        return resMap;
    }

    /**
     * 【通用列表】---处理where语句
     *
     * @param json
     * @param grid_whereConditions
     * @return
     */
    public JSONObject handleWhereConditions(JSONObject json, String grid_whereConditions) {
        JSONObject resJson = new JSONObject();//返回结果json
        List paramList = new ArrayList();//参数集合
        String res_whereConditions = "";//最终拼接的where条件语句
        if (!Util.isEoN(grid_whereConditions)) {
            JSONArray grid_whereConditionArr = JSONArray.fromObject(grid_whereConditions);
            if (!Util.isEoN(grid_whereConditionArr) && grid_whereConditionArr.size() > 0) {
                for (int i = 0; i < grid_whereConditionArr.size(); i++) {
                    JSONObject conditionJson = grid_whereConditionArr.getJSONObject(i);
                    String condition = conditionJson.get("condition") + "";//where条件语句
                    String key = conditionJson.get("jsonAttribute") + "";//对应前台参数key
                    //判断where条件语句是否有带?
                    if (condition.contains("?")) {
                        //说明有?  需要条件参数
                        //先判断前有没有传参数来，参数有没有值，都没有的话，就不拼接条件语句
                        if (json.has(key)) {
                            if (!Util.isEoN(json.get(key))) {
                                //判断值是否为: 空的字符串
                                if ("空的字符串".equals(json.get(key))) {
                                    condition = condition.replace("=", "").replace("like", "").replace("?", "") + " is null ";
                                    res_whereConditions = res_whereConditions + " " + condition + " ";
                                    continue;
                                }
                                String[] conditionArr = condition.split("[?]");//按照?进行分割
                                if (conditionArr.length > 0) {
                                    for (int j = 0; j < conditionArr.length; j++) {
                                        String condition_part = conditionArr[j];
                                        //判断语句里是否有like 或者 =
                                        if (condition_part.contains("like") || condition_part.contains("=")) {
                                            //判断?前是 = 还是 like
                                            String condition_part_sub = condition_part.substring(condition_part.length() - 6, condition_part.length());
                                            if (condition_part_sub.contains("like")) {
                                                paramList.add("%" + json.get(key) + "%");
                                            } else if (condition_part_sub.contains("=")) {
                                                paramList.add(json.get(key));
                                            }
                                        }
                                    }
                                }
                                res_whereConditions = res_whereConditions + " " + condition + " ";
                            }
                        }
                    } else if (condition.contains("in brackets")) {
                        //特殊处理 in 语句
                        if (json.has(key)) {
                            if (!Util.isEoN(json.get(key))) {
                                String keyValue = json.get(key) + "";
                                String dealKeyValue = " in ('" + keyValue.replaceAll(",", "','") + "')";
                                res_whereConditions = res_whereConditions + " " + condition.replaceAll("in brackets", dealKeyValue) + " ";
                            }
                        }
                    } else {
                        //说明没有?，直接拼接语句
                        res_whereConditions = res_whereConditions + " " + condition + " ";
                    }
                }
            }
        }
        resJson.put("whereConditions", res_whereConditions);
        resJson.put("paramList", paramList);
        return resJson;
    }

    /**
     * 【通用列表】---处理sqlHql语句
     *
     * @param select
     * @param whereConditons
     * @return
     */
    public String handleSqlHql(String select, String whereConditons) {
        int num = select.indexOf("1=1");
        String sql_one = select.substring(0, num + 3);
        String sql_two = select.substring(num + 3, select.length());
        return sql_one + " " + whereConditons + " " + sql_two;
    }

    /**
     * 【通用列表】--- 根据特殊条件语句，原来参数集合里新添加参数，目前参数只有userid
     *
     * @param paramList
     * @param condition
     * @return
     */
    public List handleSqlForParams(List paramList, String condition, String userid) {
        if (condition.contains("?")) {
            String[] conditionArr = condition.split("[?]");//按照?进行分割
            if (conditionArr.length > 0) {
                for (int j = 0; j < conditionArr.length; j++) {
                    String condition_part = conditionArr[j];
                    //判断语句里是否有like 或者 =
                    if (condition_part.contains("like") || condition_part.contains("=")) {
                        //判断?前是 = 还是 like
                        String condition_part_sub = condition_part.substring(condition_part.length() - 6, condition_part.length());
                        if (condition_part_sub.contains("like")) {
                            paramList.add("%" + userid + "%");
                        } else if (condition_part_sub.contains("=")) {
                            paramList.add(userid);
                        }
                    }
                }
            }
        }
        return paramList;
    }


    /**
     * 【通用列表】---获取总数通过类对象---列表类PmlGrid
     *
     * @param pmsGrid
     */
    public String getCountByClass(SysUser user, PmlGrid pmsGrid, JSONObject json) {
        //先判断列表是否用通用的获取方法
        if (pmsGrid.isReadscript()) {
            //说明是用通用的获取方法
            //判断是用sql还是用hql
            if ("sql".equals(pmsGrid.getSqlorhql())) {
                // 说明是用sql
                return this.getCountByClass_sqlOrHql(user, pmsGrid, "sql");
            } else {
                // 说明是用hql
                return this.getCountByClass_sqlOrHql(user, pmsGrid, "hql");
            }
        } else {
            if (StringUtils.isBlank(pmsGrid.getGetcountmethod())) {
                return "0";
            }

            // 服务名称
            String serviceName = "PmlCommonGridService";
            // 方法名称
            String methodName = pmsGrid.getGetcountmethod();

            // 如果有设置Servce，那就分割字符串，PmlCommonGridService/listMyBatchTasksCount
            String[] arr = pmsGrid.getGetcountmethod().split("\\/");
            if (arr.length == 2) {
                serviceName = arr[0];
                methodName = arr[1];
            }

            try {
                // 动态执行Service方法，获取总数
                Object object = Util.springInvokeMethod(serviceName, methodName, new Object[]{json});

                // 如果返回时list,那就取size
                if (object instanceof List) {
                    return String.valueOf(((List) object).size());
                }

                // 直接转String
                return String.valueOf(object);
            } catch (Exception e) {
                e.printStackTrace();
                return "0";
            }
        }

    }


    /**
     * 获取自己的批量流转任务列表总数
     *
     * @param json 请求参数
     * @return 总数
     * @author szs
     * @date 2024-01-02
     */
    public Long listMyBatchTasksCount(JSONObject json) {
        // 请求参数
        BatchTaskJob job = new BatchTaskJob();
        job.setBusinessType(json.getString("gridType"));

        // 获取自己的批量流转任务列表
        return processService.listMyBatchTasks(1, 1, job).getTotal();
    }


    /**
     * 【通用列表】---获取总数通过类对象---列表类PmlGrid---通过sql或者Hql语句获取
     *
     * @param pmsGrid
     */
    public String getCountByClass_sqlOrHql(SysUser user, PmlGrid pmsGrid, String sqlOrHql) {
        JSONArray jsonattributes = JSONArray.fromObject(pmsGrid.getJsonattributes());
        JSONObject resJson = new JSONObject();
        if (jsonattributes.size() > 0) {
            for (int i = 0; i < jsonattributes.size(); i++) {
                JSONObject nowJson = jsonattributes.getJSONObject(i);
                if ("userid".equals(nowJson.get("attribute"))) {
                    resJson.put("userid", user.getId());
                }
                if ("userid_comma".equals(nowJson.get("attribute"))) {
                    resJson.put("userid_comma", "," + user.getId() + " ,");
                }
                if ("userid_qm".equals(nowJson.get("attribute"))) {
                    resJson.put("userid_qm", ":\"" + user.getId() + "\",");
                }
            }
        }
        //处理Where语句
        JSONObject whereJson = this.handleWhereConditions(resJson, pmsGrid.getWhereconditions());
        String whereConditons = whereJson.get("whereConditions") + "";//where条件语句
        List paramList = (List) whereJson.get("paramList");//条件参数
        if ("sql".equals(sqlOrHql)) {
            //判断是权限版本配置，还是老版本配置
            if (pmsGrid.getGetdatamethod().contains("/PmlCommonGrid/getDataByConfig.do")) {
                //1、通过接口获取一个特殊的条件语句
                String roleid = this.pmsGridService.getOnlyValueBySqlForGrid("select replace(listagg(e.roleid),',','@') as roleids from sys_identity e where e.userid = ? " +
                        " group by e.userid", new Object[]{user.getId()});
                String specialSql = this.flAuthorityGridService.getAuthorityGridSql(pmsGrid.getBusinesstype(), pmsGrid.getGridtype(), roleid, user.getId());
                //2、重新拼接条件语句
                whereConditons = whereConditons + " " + specialSql + " ";
                //3、根据特殊条件语句，重新添加参数
                paramList = this.handleSqlForParams(paramList, specialSql, user.getId());
            }
            //处理sql语句
            String sql = this.handleSqlHql(pmsGrid.getScript(), whereConditons);
            //获取数据的总数
            String countSql = "select count(*) from (" + sql + ")";
            String totalCount = this.dbHelper.getOnlyStringValue(countSql, paramList.toArray());
            if (Util.isEoN(totalCount)) {
                totalCount = "0";
            }
            return totalCount;
        } else {
            //处理hql语句
            String hql = this.handleSqlHql(pmsGrid.getScript(), whereConditons);
            //获取数据的总数
            String countHql = "select count(*) " + hql.substring(hql.indexOf("from"), hql.length());
            Query countQuery = this.pmlGridDao.getQuery(countHql);
            if (paramList.size() > 0) {
                for (int i = 0; i < paramList.size(); i++) {
                    countQuery.setParameter(i, paramList.get(i) + "");
                }
            }
            String totalCount = countQuery.uniqueResult().toString();
            if (Util.isEoN(totalCount)) {
                totalCount = "0";
            }
            return totalCount;
        }
    }


    /**
     * 【通用列表】---获取所有数据通过类对象---列表类PmlGrid
     *
     * @param pmsGrid
     */
    public JSONArray getAllDataByClass(SysUser user, PmlGrid pmsGrid, JSONObject json) {
        JSONArray jsonArray = new JSONArray();
        //如果不是通用的获取方法，并且是导出全部数据，需要特殊处理
        if (!pmsGrid.isReadscript()) {
            //特殊处理方式
            return new JSONArray();
//            return this.getAllDataBySpecialDo(user,pmsGrid,json);
        } else {
            //原来的处理方式
            if ("sql".equals(pmsGrid.getSqlorhql()) || Util.isEoN(pmsGrid.getSqlorhql())) {
                //说明是用sql
                return this.getAllDataByClass_sqlOrHql(user, pmsGrid, "sql", json);
            } else {
                //说明是用hql
                return this.getAllDataByClass_sqlOrHql(user, pmsGrid, "hql", json);
            }
        }

//        }
//        return jsonArray;
    }


    /**
     * 【通用列表】---获取所有数据通过类对象---列表类PmlGrid---通过sql或者Hql语句获取
     *
     * @param pmsGrid
     */
    public JSONArray getAllDataByClass_sqlOrHql(SysUser user, PmlGrid pmsGrid, String sqlOrHql, JSONObject json) {
        //判断是否有配置自定义语句
        String script = "";
        if (!Util.isEoN(json.get("customSql"))) {
            script = json.get("customSql") + "";
        } else {
            script = pmsGrid.getScript();
        }
        JSONArray jsonattributes = JSONArray.fromObject(pmsGrid.getJsonattributes());
        JSONObject resJson = new JSONObject();
        if ("select".equals(json.get("printType") + "")) {
            //说明是选中导出
            if (jsonattributes.size() > 0) {
                for (int i = 0; i < jsonattributes.size(); i++) {
                    JSONObject nowJson = jsonattributes.getJSONObject(i);
                    if ("userid".equals(nowJson.get("attribute"))) {
                        resJson.put("userid", user.getId());
                    }
                    break;
                    //userRoleid  和  userEnterpriseid  暂时先不赋值
                }
            }
        } else if ("all".equals(json.get("printType") + "")) {
            //说明是全部导出
            resJson = json;
        }
        //处理Where语句
        JSONObject whereJson = this.handleWhereConditions(resJson, pmsGrid.getWhereconditions());
        String whereConditons = whereJson.get("whereConditions") + "";//where条件语句
        List paramList = (List) whereJson.get("paramList");//条件参数
        if ("sql".equals(sqlOrHql)) {
            //处理sql语句
            String sql = "";
            //判断是选择打印还是全部打印
            if ("select".equals(json.get("printType") + "")) {
                //说明是选中导出
                String whereSql = "";
                sql = this.handleSqlHql(script, whereConditons);
                JSONArray idArr = json.getJSONArray("seletedData");
                if (idArr.size() > 0) {
                    for (int i = 0; i < idArr.size(); i++) {
                        whereSql = whereSql + "'" + idArr.get(i) + "',";
                    }
                    whereSql = whereSql.substring(0, whereSql.length() - 1);
                    sql = "select tt.*,rownum from (" + sql + ") tt where tt.id in (" + whereSql + ")";
                }
            } else {
                //说明是全部导出
                //判断获取方法是getData.do 还是 getDataByConfig.do
                String method = pmsGrid.getGetdatamethod();
                if (method.contains("getDataByConfig")) {
                    //2、通过接口获取一个特殊的条件语句
                    String specialSql = this.flAuthorityGridService.getAuthorityGridSql(pmsGrid.getBusinesstype(), pmsGrid.getGridtype(), json.getString("userRoleid"), json.getString("userid"));
                    //3、重新拼接条件语句
                    whereConditons = whereConditons + " " + specialSql + " ";
                    //4、根据特殊条件语句，重新添加参数
                    paramList = this.handleSqlForParams(paramList, specialSql, json.getString("userid"));
                    //5、处理sql语句
                    sql = this.handleSqlHql(script, whereConditons);
                } else {
                    sql = this.handleSqlHql(script, whereConditons);
                }
            }
            //获取所有数据
            List<Map> list = this.dbHelper.getRows(sql, paramList.toArray());
            return JSONArray.fromObject(list);
        } else {
            //处理hql语句
            String hql = this.handleSqlHql(script, whereConditons);
            //获取所有数据
            Query hqlQuery = this.pmlGridDao.getQuery(hql);
            if (paramList.size() > 0) {
                for (int i = 0; i < paramList.size(); i++) {
                    hqlQuery.setParameter(i, paramList.get(i) + "");
                }
            }
            //hql 选中打印方法还未处理！hql 选中打印方法还未处理！hql 选中打印方法还未处理！hql 选中打印方法还未处理！
            List list = hqlQuery.list();
            return JSONArray.fromObject(list);
        }
    }

//    /**
//     * 当导出全部数据，并且列表不是通用获取方法
//     * @param pmsGrid
//     * @param json
//     * @return
//     */
//    public JSONArray getAllDataBySpecialDo( SysUser user,PmlGrid pmsGrid,  JSONObject json){
//
//        JSONArray result = new JSONArray();
//        List<Map> tempResult = new ArrayList<>();
//        JSONArray resArr = new JSONArray();
//        JSONObject pageConfig = new JSONObject();
//        List<Integer> pageSizes = new ArrayList<>();
//        pageSizes.add(10000);
//        pageConfig.put("pageSizes",pageSizes);
//        pageConfig.put("pageSize",10000);
//        pageConfig.put("currentPage",1);
//        json.put("pageConfig",pageConfig);
//        String getDataDo = pmsGrid.getGetdatamethod();
//        if(getDataDo.contains("ndbg_wsb_getData")){
//            tempResult = this.ndbg_wsb_getData(json,user).getData();
//        }else if(getDataDo.contains("jb_wsb_getData")){
//            tempResult = this.jb_wsb_getData(json,user).getData();
//        }else if(getDataDo.contains("ysb_yhsy_getData")){
//            tempResult = this.ysb_yhsy_getData(json).getData();
//        }else if(getDataDo.contains("getHistoryProject")){
//            tempResult = this.pmsProjectbaseService.getHistoryProject(pmsGrid, json).getData();
//        }else if(getDataDo.contains("findZrjjLimitBatchList.do")){
//            tempResult = this.sysParameterService.findZrjjLimitBatchList(json).getData();
//        }
//
//        if("select".equals(json.get("printType")+"")){
//            //说明是选中导出
//            JSONArray idArr = json.getJSONArray("seletedData");
//            result = tempResult.stream().filter(map -> idArr.contains(map.get("id"))).collect(Collectors.toCollection(JSONArray::new));
//        }else if("all".equals(json.get("printType")+"") || Util.isEoN(json.get("printType"))){
//            //说明是全部导出
//            result = JSONArray.fromObject(tempResult);
//        }
//        return result;
//    }
//
//
//    /**
//     * 【黑龙江系统合同验收】 --- 待验收合同列表获取方法
//     * @param json
//     */
//    public Page<Map> htys_dysht_getData(JSONObject json){
//        //处理参数
//        List paramList = new ArrayList<>();
////        paramList.add(json.get("userid")+"");
//        //判断有无搜索条件
////        String insql1 = "";
////        if(json.has("searchContent")&&!Util.isEoN(json.get("searchContent")+"")){
////            insql1 = " and (jbxx.contractno like ? or jbxx.showprojectbasename like ? )";
////            paramList.add("%"+json.get("searchContent")+"%");
////            paramList.add("%"+json.get("searchContent")+"%");
////        }
////        String insql2 = "";
////        if(json.has("year")&&!Util.isEoN(json.get("year")+"")){
////            insql2 = " and batch.annually = ?";
////            paramList.add(json.get("year")+"");
////        }
////        String insql3 = "";
////        if(json.has("batchname")&&!Util.isEoN(json.get("batchname")+"")){
////            insql3 = " and batch.name = ?";
////            paramList.add(json.get("batchname")+"");
////        }
////        String insql4 = "";
////        if(json.has("bigBatchname")&&!Util.isEoN(json.get("bigBatchname")+"")){
////            insql4 = " and bigbatch.projectname like ?";
////            paramList.add("%"+json.get("bigBatchname")+"%");
////        }
//
//        paramList.add(json.get("userid")+"");
//
//        String insql5 = "";
//        if(json.has("searchContent")&&!Util.isEoN(json.get("searchContent")+"")){
//            insql5 = " and (jbxx.contractno like ? or jbxx.projectbasename like ? )";
//            paramList.add("%"+json.get("searchContent")+"%");
//            paramList.add("%"+json.get("searchContent")+"%");
//        }
//        String insql6 = "";
//        if(json.has("year")&&!Util.isEoN(json.get("year")+"")){
//            insql6 = " and batch.annually = ?";
//            paramList.add(json.get("year")+"");
//        }
//        String insql7 = "";
//        if(json.has("batchname")&&!Util.isEoN(json.get("batchname")+"")){
//            insql7 = " and batch.name = ?";
//            paramList.add(json.get("batchname")+"");
//        }
//        String insql8 = "";
//        if(json.has("bigBatchname")&&!Util.isEoN(json.get("bigBatchname")+"")){
//            insql8 = " and bigbatch.projectname like ?";
//            paramList.add("%"+json.get("bigBatchname")+"%");
//        }
//
//        String sql =
//                " select ys.id as conclusionid,ys.contractno,jbxx.id as contractid,ys.projectbasename, " +
//                " (case when jbxx.projectleader is not null then jbxx.projectleader else ojbxx.projectleader end) as fzrname ,ys.contracttype,ys.conclusionway ,batch.name as batchname ,batch.xtwys_hrefname,batch.xtwys_href,batch.id as batchid " +
//                " from pms_conclusion ys " +
//                " left join crt_contract_jbxx jbxx on ys.contractid = jbxx.id " +
//                " left join crt_othersystem ojbxx on ys.othercontractid = ojbxx.id " +
//                " left join pms_planprojectbatch batch on ys.planprojectbatchid = batch.id " +
//                " left join pms_planproject bigbatch on ys.planprojectid = bigbatch.id " +
//                " where ys.declarantid = ? and (ys.conclusionway = '用户发起系统内验收' or ys.conclusionway = '用户发起系统外验收') and (ys.minicurrentstate is null or ys.minicurrentstate = '项目验收:用户填报' or ys.minicurrentstate = '项目验收:需修改' or ys.minicurrentstate = '项目验收:需整改' or ys.minicurrentstate = '项目验收:主管官员退回')   " +
//                insql5+insql6+insql7+insql8;
//        //分页配置
//        JSONObject pageConfig = json.getJSONObject("pageConfig");
//        //分页处理
//        Page<Map> page = this.pageService.findPageBySql(sql,paramList,pageConfig);
//        return page;
//    }
//
//    /**
//     * 【黑龙江系统合同验收】 --- 待验收合同列表获取方法 --- 获取总数
//     * @param json
//     */
//    public String htys_dysht_getData_count(JSONObject json){
//        //处理参数
//        List paramList = new ArrayList<>();
////        paramList.add(json.get("userid")+"");
//        //判断有无搜索条件
////        String insql1 = "";
////        if(json.has("searchContent")&&!Util.isEoN(json.get("searchContent")+"")){
////            insql1 = " and (jbxx.contractno like ? or jbxx.showprojectbasename like ? )";
////            paramList.add("%"+json.get("searchContent")+"%");
////            paramList.add("%"+json.get("searchContent")+"%");
////        }
////        String insql2 = "";
////        if(json.has("year")&&!Util.isEoN(json.get("year")+"")){
////            insql2 = " and batch.annually = ?";
////            paramList.add(json.get("year")+"");
////        }
////        String insql3 = "";
////        if(json.has("batchname")&&!Util.isEoN(json.get("batchname")+"")){
////            insql3 = " and batch.name = ?";
////            paramList.add(json.get("batchname")+"");
////        }
////        String insql4 = "";
////        if(json.has("bigBatchname")&&!Util.isEoN(json.get("bigBatchname")+"")){
////            insql4 = " and bigbatch.projectname like ?";
////            paramList.add("%"+json.get("bigBatchname")+"%");
////        }
//
//        paramList.add(json.get("userid")+"");
//
//        String insql5 = "";
//        if(json.has("searchContent")&&!Util.isEoN(json.get("searchContent")+"")){
//            insql5 = " and (jbxx.contractno like ? or jbxx.showprojectbasename like ? )";
//            paramList.add("%"+json.get("searchContent")+"%");
//            paramList.add("%"+json.get("searchContent")+"%");
//        }
//        String insql6 = "";
//        if(json.has("year")&&!Util.isEoN(json.get("year")+"")){
//            insql6 = " and batch.annually = ?";
//            paramList.add(json.get("year")+"");
//        }
//        String insql7 = "";
//        if(json.has("batchname")&&!Util.isEoN(json.get("batchname")+"")){
//            insql7 = " and batch.name = ?";
//            paramList.add(json.get("batchname")+"");
//        }
//        String insql8 = "";
//        if(json.has("bigBatchname")&&!Util.isEoN(json.get("bigBatchname")+"")){
//            insql8 = " and bigbatch.projectname like ?";
//            paramList.add("%"+json.get("bigBatchname")+"%");
//        }
//
//        String sql =
//                " select ys.id as conclusionid,ys.contractno,ys.projectbasename, " +
//                " (case when jbxx.projectleader is not null then jbxx.projectleader else ojbxx.projectleader end) as fzrname,ys.contracttype,ys.conclusionway,batch.name as batchname,batch.xtwys_hrefname,batch.xtwys_href ,batch.id as batchid" +
//                " from pms_conclusion ys " +
//                " left join crt_contract_jbxx jbxx on ys.contractid = jbxx.id " +
//                " left join crt_othersystem ojbxx on ys.othercontractid = ojbxx.id " +
//                " left join pms_planprojectbatch batch on ys.planprojectbatchid = batch.id " +
//                " left join pms_planproject bigbatch on ys.planprojectid = bigbatch.id " +
//                " where ys.declarantid = ? and (ys.conclusionway = '用户发起系统内验收' or ys.conclusionway = '用户发起系统外验收') and (ys.minicurrentstate is null or ys.minicurrentstate = '项目验收:需修改' or ys.minicurrentstate = '项目验收:用户填报' or ys.minicurrentstate = '项目验收:需整改'  or ys.minicurrentstate = '项目验收:主管官员退回') " +
//                insql5+insql6+insql7+insql8;
//        //获取数据的总数
//        String countSql = "select count(*) from ("+sql+")";
//        String totalCount = this.dbHelper.getOnlyStringValue(countSql,paramList.toArray());
//        if(Util.isEoN(totalCount)){
//            totalCount = "0";
//        }
//        return totalCount;
//    }
//
//    /**
//     * 【黑龙江系统合同】 --- 随机抽取合同
//     * @param json
//     */
//    public Page<Map> ht_random_getData(JSONObject json){
//        //处理参数
//        List paramList = new ArrayList<>();
//        //判断有无搜索条件
//        String insql1 = "";
//        if(json.has("searchContent")&&!Util.isEoN(json.get("searchContent")+"")){
//            insql1 = " and (e.contractno like ? or e.showprojectbasename like ? or e.projectleader like ? or e.mainorganizers like ?)";
//            paramList.add("%"+json.get("searchContent")+"%");
//            paramList.add("%"+json.get("searchContent")+"%");
//            paramList.add("%"+json.get("searchContent")+"%");
//            paramList.add("%"+json.get("searchContent")+"%");
//        }
//        String insql2 = "";
//        if(json.has("year")&&!Util.isEoN(json.get("year")+"")){
//            insql2 = " and batch.annually = ?";
//            paramList.add(json.get("year")+"");
//        }
//        String insql3 = "";
//        if(json.has("batchname")&&!Util.isEoN(json.get("batchname")+"")){
//            insql3 = " and batch.name = ?";
//            paramList.add(json.get("batchname")+"");
//        }
//        String insql4 = "";
//        if(json.has("bigBatchname")&&!Util.isEoN(json.get("bigBatchname")+"")){
//            insql4 = " and bigbatch.projectname like ?";
//            paramList.add("%"+json.get("bigBatchname")+"%");
//        }
//        String insql5 = "";
//        if(json.has("minicurrentstate")&&!Util.isEoN(json.get("minicurrentstate")+"")){
//            insql5 = " and e.minicurrentstate like ? ";
//            paramList.add("%"+json.get("minicurrentstate")+"%");
//        }
//        String insql6 = "";
//        if(json.has("randomNum")&&!Util.isEoN(json.get("randomNum")+"")){
//            insql6 = "where rownum <= "+json.get("randomNum");
//        }
//        String sql = " select ttt.* from (" +
//                " select e.id,e.id as contractid,e.projectbaseid, " +
//                " batch.name as batchname, " +
//                " batch.id as batchid, " +
//                " e.contractno, " +
//                " e.showprojectbasename, " +
//                " e.mainorganizers as cddwname, " +
//                " xmry.name as fzrname, " +
//                " xmry.mobilephone as fzrmobilephone, " +
//                " e.minicurrentstate as ht_minicurrentstate, " +
//                " u.userid as user_userid, " +
//                " (case when e.isys is not null then '结题' when e.isendcontract is not null then '终止' else '合同签订'  end) as contractstate " +
//                " from crt_contract_jbxx e  " +
//                " left join crt_contract_xmry xmry on e.id = xmry.contractid and xmry.rytype = 'true' " +
//                " left join fl_flowrelationdetail fl on e.id = fl.sourceid " +
//                " left join pms_planproject bigbatch on e.planprojectid = bigbatch.id " +
//                " left join pms_planprojectbatch batch on e.planprojectbatchid = batch.id " +
//                " left join sys_user u on e.declarantid = u.id " +
//                " where 1=1 " +insql1+insql2+insql3+insql4+insql5+
//                " order by dbms_random.value ) ttt " + insql6;
//        //分页配置
//        JSONObject pageConfig = json.getJSONObject("pageConfig");
//        //分页处理
//        Page<Map> page = this.pageService.findPageBySql(sql,paramList,pageConfig);
//        return page;
//    }
//
//    /**
//     * 【黑龙江系统合同】 --- 随机抽取合同 -- 总数
//     * @param json
//     */
//    public String ht_random_getData_count(JSONObject json){
//        //处理参数
//        List paramList = new ArrayList<>();
//        //判断有无搜索条件
//        String insql1 = "";
//        if(json.has("searchContent")&&!Util.isEoN(json.get("searchContent")+"")){
//            insql1 = " and (e.contractno like ? or e.showprojectbasename like ? or e.projectleader like ? or e.mainorganizers like ?)";
//            paramList.add("%"+json.get("searchContent")+"%");
//            paramList.add("%"+json.get("searchContent")+"%");
//            paramList.add("%"+json.get("searchContent")+"%");
//            paramList.add("%"+json.get("searchContent")+"%");
//        }
//        String insql2 = "";
//        if(json.has("year")&&!Util.isEoN(json.get("year")+"")){
//            insql2 = " and batch.annually = ?";
//            paramList.add(json.get("year")+"");
//        }
//        String insql3 = "";
//        if(json.has("batchname")&&!Util.isEoN(json.get("batchname")+"")){
//            insql3 = " and batch.name = ?";
//            paramList.add(json.get("batchname")+"");
//        }
//        String insql4 = "";
//        if(json.has("bigBatchname")&&!Util.isEoN(json.get("bigBatchname")+"")){
//            insql4 = " and bigbatch.projectname like ?";
//            paramList.add("%"+json.get("bigBatchname")+"%");
//        }
//        String insql5 = "";
//        if(json.has("minicurrentstate")&&!Util.isEoN(json.get("minicurrentstate")+"")){
//            insql5 = " and e.minicurrentstate like ? ";
//            paramList.add("%"+json.get("minicurrentstate")+"%");
//        }
//        String insql6 = "";
//        if(json.has("randomNum")&&!Util.isEoN(json.get("randomNum")+"")){
//            insql6 = "where rownum <= "+json.get("randomNum");
//        }
//        String sql = " select ttt.* from (" +
//                " select e.id,e.id as contractid,e.projectbaseid, " +
//                " batch.name as batchname, " +
//                " batch.id as batchid, " +
//                " e.contractno, " +
//                " e.showprojectbasename, " +
//                " e.mainorganizers as cddwname, " +
//                " xmry.name as fzrname, " +
//                " xmry.mobilephone as fzrmobilephone, " +
//                " e.minicurrentstate as ht_minicurrentstate, " +
//                " u.userid as user_userid, " +
//                " (case when e.isys is not null then '结题' when e.isendcontract is not null then '终止' else '合同签订'  end) as contractstate " +
//                " from crt_contract_jbxx e  " +
//                " left join crt_contract_xmry xmry on e.id = xmry.contractid and xmry.rytype = 'true' " +
//                " left join fl_flowrelationdetail fl on e.id = fl.sourceid " +
//                " left join pms_planproject bigbatch on e.planprojectid = bigbatch.id " +
//                " left join pms_planprojectbatch batch on e.planprojectbatchid = batch.id " +
//                " left join sys_user u on e.declarantid = u.id " +
//                " where 1=1 " +insql1+insql2+insql3+insql4+insql5+
//                " order by dbms_random.value ) ttt " + insql6;
//        //获取数据的总数
//        String countSql = "select count(*) from ("+sql+")";
//        String totalCount = this.dbHelper.getOnlyStringValue(countSql,paramList.toArray());
//        if(Util.isEoN(totalCount)){
//            totalCount = "0";
//        }
//        return totalCount;
//    }
//
//
//
//    /**
//     * 【黑龙江系统季报】 --- 未上报
//     * @param json
//     */
//    public Page<Map> jb_wsb_getData(JSONObject json,SysUser user){
//        //获取当前角色
//        String roleids = this.dbHelper.getOnlyStringValue("select listagg(e.roleid) from sys_identity e  " +
//                " where e.userid = ?  group by e.userid",new Object[]{user.getId()});
//        //当前处室id
//        String departmentid = "";
//        if(user.getSysIdentitys().size()>0){
//            if(!Util.isEoN(user.getSysIdentitys().get(0).getPmlDepartment())){
//                departmentid = user.getSysIdentitys().get(0).getPmlDepartment().getId();
//            }
//        }
//        //处理参数
//        List paramList = new ArrayList<>();
//        //判断有无搜索条件
//        String insql1 = "";
//        if(json.has("searchContent")&&!Util.isEoN(json.get("searchContent")+"")){
//            insql1 = " and (e.contractno like ? or e.showprojectbasename like ? or e.projectleader like ? or e.mainorganizers like ?)";
//            paramList.add("%"+json.get("searchContent")+"%");
//            paramList.add("%"+json.get("searchContent")+"%");
//            paramList.add("%"+json.get("searchContent")+"%");
//            paramList.add("%"+json.get("searchContent")+"%");
//        }
//        String insql2 = "";
//        if(json.has("year")&&!Util.isEoN(json.get("year")+"")){
//            insql2 = " and batch.annually = ?";
//            paramList.add(json.get("year")+"");
//        }
//        //判断季报表
//        String insql3 = "";
//        if(json.has("jbyear")&&!Util.isEoN(json.get("jbyear")+"")&&json.has("season")&&!Util.isEoN(json.get("season")+"")){
//            insql3 = " and e.id not in ( select jb.contractid from pms_seasonreport jb   " +
//                    " where jb.year = ? and jb.season = ?   " +
//                    " and ( jb.MINICURRENTSTATE='季报:完成季报' or jb.MINICURRENTSTATE ='季报:业务官员审核'))";
//            paramList.add(json.get("jbyear")+"");
//            paramList.add(json.get("season")+"");
//        }
//        String insql4 = "";
//        if(json.has("jbyear")&&!Util.isEoN(json.get("jbyear")+"")){
//            insql4 = "and to_char(e.STARTDATE,'yyyy') <= ? and to_char(e.Enddate,'yyyy') >= ? ";
//            paramList.add(json.get("jbyear")+"");
//            paramList.add(json.get("jbyear")+"");
//        }
//        //判断角色：
//        //1、高级管理员、二级高级管理员
//        //2、高新处、农村处、社发处、省院士办
//        //3、战略处、资配处、监督处、科技厅统计人员
//        String insql5 = "";
//        if(roleids.contains("erji6D2039-4D9F-4B6A-ACBA-D4BEF757erji")){
//            //二级高级管理员
//            insql5 = " and e.CASEMANAGEMENTID in (select i.purvieworganizeid from sys_identity i where i.userid = ? and rownum<2) " +
//                    " and e.planprojectid in (select ent.planprojectid from sys_enterpriseadmin ent where ent.userid = ?) ";
//            paramList.add(user.getId());
//            paramList.add(user.getId());
//        }
//        if(roleids.contains("2A6D2039-4D9F-4B6A-ACBA-D4BEF7577487")){
//            //高级管理员
//            insql5 = " and e.CASEMANAGEMENTID in (select i.purvieworganizeid from sys_identity i where i.userid = ? and rownum<2) ";
//            paramList.add(user.getId());
//        }
//        if("gxjsc38A1B7A9".equals(departmentid)||"nckjc38A1B7A9".equals(departmentid)||"shfzkjc38A1B7A9".equals(departmentid)||"sysgzbgs38A1B7A9".equals(departmentid)){
//            //高新处、农村处、社发处、省院士办
//            insql5 = " and e.belonglabid in (select i.departmentid from sys_user u left join sys_identity i on u.id = i.userid where u.id = ? and rownum <2) ";
//            paramList.add(user.getId());
//        }
//
//        String sql = " select distinct e.id,e.id as contractid,e.projectbaseid, " +
//                " e.contractno, " +
//                " e.showprojectbasename, " +
//                " xmry.name as fzrname, " +
//                " xmry.mobilephone as fzrmobilephone, " +
//                " e.mainorganizers as cddwname, " +
//                " to_char(e.STARTDATE,'yyyy-mm-dd') as startdate, " +
//                " to_char(e.ENDDATE,'yyyy-mm-dd') as enddate, " +
//                " batch.name as batchname, " +
//                " batch.id as batchid " +
//                " from crt_contract_jbxx e " +
//                " left join pms_planproject bigbatch on e.planprojectid = bigbatch.id " +
//                " left join pms_planprojectbatch batch on e.planprojectbatchid = batch.id " +
//                " left join crt_contract_xmry xmry on e.id = xmry.contractid and xmry.rytype = 'true' " +
//                " where 1=1 " +
//                " and e.isys is NULL " +
//                " and e.ISENDCONTRACT is null " +
//                " and batch.ishavejdbg is not null" +
//                " and e.MINICURRENTSTATE = '合同签订:完成合同签订' "+insql1+insql2+insql3+insql4+insql5;
//        //分页配置
//        JSONObject pageConfig = json.getJSONObject("pageConfig");
//        //分页处理
//        Page<Map> page = this.pageService.findPageBySql(sql,paramList,pageConfig);
//        return page;
//    }
//
//    /**
//     * 【黑龙江系统年报】 --- 未上报
//     * @param json
//     */
//    public Page<Map> ndbg_wsb_getData(JSONObject json,SysUser user){
//        //获取当前角色
////        String roleids = this.dbHelper.getOnlyStringValue("select listagg(e.roleid) from sys_identity e  " +
////                " where e.userid = ?  group by e.userid",new Object[]{user.getId()});
//        //当前处室id
//        String departmentid = "";
//        if(user.getSysIdentitys().size()>0){
//            if(!Util.isEoN(user.getSysIdentitys().get(0).getPmlDepartment())){
//                departmentid = user.getSysIdentitys().get(0).getPmlDepartment().getId();
//            }
//        }
//        //处理参数
//        List paramList = new ArrayList<>();
//        //判断有无搜索条件
//        String insql1 = "";
//        if(json.has("searchContent")&&!Util.isEoN(json.get("searchContent")+"")){
//            insql1 = " and (e.contractno like ? or e.showprojectbasename like ? or e.projectleader like ? or e.mainorganizers like ?)";
//            paramList.add("%"+json.get("searchContent")+"%");
//            paramList.add("%"+json.get("searchContent")+"%");
//            paramList.add("%"+json.get("searchContent")+"%");
//            paramList.add("%"+json.get("searchContent")+"%");
//        }
//        String insql2 = "";
//        if(json.has("year")&&!Util.isEoN(json.get("year")+"")){
//            insql2 = " and batch.annually = ?";
//            paramList.add(json.get("year")+"");
//        }
//        String insql3 = "";
//        if(json.has("batchname")&&!Util.isEoN(json.get("batchname")+"")){
//            insql3 = " and batch.name = ?";
//            paramList.add(json.get("batchname")+"");
//        }
//
//        //判断年报表
//        String insql4 = "";
//        if(json.has("nbyear")&&!Util.isEoN(json.get("nbyear")+"")){
//            insql4 = " and e.id not in ( select jb.contractid from pms_annualreport jb  " +
//                    " where jb.year = ?   " +
//                    " and ( jb.MINICURRENTSTATE='年报:业务官员审核' or jb.MINICURRENTSTATE ='年报:完成年报'))" +
//                    " and to_char(e.STARTDATE,'yyyy') <= ? " +
//                    " and to_char(e.Enddate,'yyyy') >= ?";
//            paramList.add(json.get("nbyear")+"");
//            paramList.add(json.get("nbyear")+"");
//            paramList.add(json.get("nbyear")+"");
//        }
//        //判断角色：
//        //1、高新处、农村处、社发处、省院士办
//        //2、其他处室
//        //3、科技厅统计人员
//        String insql5 = "";
//        if(Util.isEoN(departmentid)){
//
//        }else  if("gxjsc38A1B7A9".equals(departmentid)||"nckjc38A1B7A9".equals(departmentid)||"shfzkjc38A1B7A9".equals(departmentid)||"sysgzbgs38A1B7A9".equals(departmentid)){
//            //高新处、农村处、社发处、省院士办
//            insql5 = " and e.belonglabid in (select i.departmentid from sys_user u left join sys_identity i on u.id = i.userid where u.id = ? and rownum <2) ";
//            paramList.add(user.getId());
//        }else{
//            //其他处室
//            insql5 = " and bigbatch.departmentid in (select i.departmentid from sys_user u left join sys_identity i on u.id = i.userid where u.id = ? and rownum <2) ";
//            paramList.add(user.getId());
//        }
//        String sql = " select distinct e.id,e.id as contractid,e.projectbaseid, " +
//                " e.contractno, " +
//                " e.showprojectbasename, " +
//                " xmry.name as fzrname, " +
//                " xmry.mobilephone as fzrmobilephone, " +
//                " e.mainorganizers as cddwname, " +
//                " to_char(e.STARTDATE,'yyyy-mm-dd') as startdate, " +
//                " to_char(e.ENDDATE,'yyyy-mm-dd') as enddate, " +
//                " batch.name as batchname, " +
//                " batch.id as batchid " +
//                " from crt_contract_jbxx e " +
//                " left join pms_planproject bigbatch on e.planprojectid = bigbatch.id " +
//                " left join pms_planprojectbatch batch on e.planprojectbatchid = batch.id " +
//                " left join crt_contract_xmry xmry on e.id = xmry.contractid and xmry.rytype = 'true' " +
//                " where 1=1 " +
//                " and batch.versionndbgid is not null " +
//                " and e.MINICURRENTSTATE = '合同签订:完成合同签订' " +
//                " and e.isys is NULL " +
//                " and e.ISENDCONTRACT is null "+insql1+insql2+insql3+insql4+insql5;
//        //分页配置
//        JSONObject pageConfig = json.getJSONObject("pageConfig");
//        //分页处理
//        Page<Map> page = this.pageService.findPageBySql(sql,paramList,pageConfig);
//        return page;
//    }
//
//    /**
//     * 【科研诚信系统】 --- 所有的用户、单位、专家
//     * @param json
//     */
//    public Page<Map> kycx_all(JSONObject json){
//        //处理参数
//        List paramList = new ArrayList<>();
//        //判断有无搜索条件
//        String insql = "";
//        if(json.has("searchContent")&&!Util.isEoN(json.get("searchContent")+"")) {
//            insql = " and (aa.onlyname like ? or aa.onlyvalue like ?)";
//            paramList.add("%" + json.get("searchContent") + "%");
//            paramList.add("%" + json.get("searchContent") + "%");
//        }
//        String insql2 = "";
//        if(json.has("type")&&!Util.isEoN(json.get("type")+"")){
//            insql2 = " and aa.type = ?";
//            paramList.add(json.get("type")+"");
//        }
//        String sql = "select * from ( " +
//                " select * from ( " +
//                " select u.id,'个人信用' as type_ch,'user' as type,u.name as onlyname ,u.certificateno as onlyvalue,u.sciencescore " +
//                " from sys_user u " +
//                " left join sys_identity i on u.id = i.userid " +
//                " where u.userstate = 1 and u.sciencescore is not null and i.roleid = '129947C6-94DC-4A7D-84D2-E78A80E518A3' order by u.sciencescore desc) " +
//                " union all " +
//                " select * from ( " +
//                " select e.id,'法人信用' as type_ch,'unit' as type,e.name as onlyname ,e.uniformcode as onlyvalue,e.sciencescore " +
//                " from pms_enterprise e " +
//                " where e.state = 0 and e.sciencescore is not null order by e.sciencescore desc) " +
//                " union all " +
//                " select * from ( " +
//                " select zj.userid as id ,'专家信用' as type_ch,'expert' as type,zj.name as onlyname ,zj.id_number as onlyvalue,zj.sciencescore " +
//                " from zjk_ryjbxx_update zj " +
//                " where zj.sciencescore is not null and zj.rkflag = '1' and zj.userid is not null order by zj.sciencescore desc) " +
//                ") aa where 1=1 "+insql+insql2 ;
//        //分页配置
//        JSONObject pageConfig = json.getJSONObject("pageConfig");
//        //分页处理
//        Page<Map> page = this.pageService.findPageBySqlForRownum(sql,paramList,pageConfig);
//        return page;
//    }
//
//    /**
//     * 【黑龙江系统科技奖励】 --- 所有的用户、单位、专家  -- 获取总数
//     * @param json
//     */
//    public String kycx_all_count(JSONObject json){
//        //处理参数
//        List paramList = new ArrayList<>();
//        String sql = "select * from ( " +
//                " select * from ( " +
//                " select u.id,'个人信用' as type_ch,'user' as type,u.name as onlyname ,u.certificateno as onlyvalue,u.sciencescore " +
//                " from sys_user u " +
//                " left join sys_identity i on u.id = i.userid " +
//                " where u.userstate = 1 and u.sciencescore is not null and i.roleid = '129947C6-94DC-4A7D-84D2-E78A80E518A3' order by u.sciencescore desc) " +
//                " union all " +
//                " select * from ( " +
//                " select e.id,'法人信用' as type_ch,'unit' as type,e.name as onlyname,e.uniformcode as onlyvalue,e.sciencescore " +
//                " from pms_enterprise e " +
//                " where e.state = 0 and e.sciencescore is not null order by e.sciencescore desc) " +
//                " union all " +
//                " select * from ( " +
//                " select zj.userid as id ,'专家信用' as type_ch,'expert' as type,zj.name as onlyname,zj.id_number as onlyvalue,zj.sciencescore " +
//                " from zjk_ryjbxx_update zj " +
//                " where zj.sciencescore is not null and zj.rkflag = '1' and zj.userid is not null order by zj.sciencescore desc) " +
//                ") aa  " ;
//        //获取数据的总数
//        String countSql = "select count(*) from ("+sql+")";
//        String totalCount = this.dbHelper.getOnlyStringValue(countSql,paramList.toArray());
//        if(Util.isEoN(totalCount)){
//            totalCount = "0";
//        }
//        return totalCount;
//    }
//
//    /**
//     * 【预申报】 --- 获取当前用户所有预申报数据
//     * @param json
//     */
//    public Page<Map> ysb_yhsy_getData(JSONObject json){
//        //判断是否有传参数：certificatenumber，tyshxydm
//        if(!json.has("certificatenumber")){
//            return null;
//        }
//        //处理参数
//        List paramList = new ArrayList<>();
//        DESEncrypt desypt = new DESEncrypt();
//        //证件号码
//        String insql1 = " and e.carrierid = ? ";
//        paramList.add(desypt.decrypt(json.get("certificatenumber")+""));
//        //统一社会信用代码（可能为空）
//        String insql2 = "";
//        if(Util.isEoN(json.get("tyshxydm"))){
//            insql2 = " and e.unid is null ";
//        }else{
//            insql2 = " and e.unid = ? ";
//            paramList.add(desypt.decrypt(json.get("tyshxydm")+""));
//        }
//        //判断有无年份
//        String insql3 = "";
//        if(json.has("year")&&!Util.isEoN(json.get("year")+"")){
//            insql3 = " and batch.annually = ? ";
//            paramList.add(json.get("year")+"");
//        }
//        //判断有无大批次名称
//        String insql4 = "";
//        if(json.has("bigBatchname")&&!Util.isEoN(json.get("bigBatchname")+"")){
//            insql4 = " and bigbatch.projectname like ? ";
//            paramList.add("%"+json.get("bigBatchname")+"%");
//        }
//        //判断有无小批次名称
//        String insql5 = "";
//        if(json.has("batchname")&&!Util.isEoN(json.get("batchname")+"")){
//            insql5 = " and batch.name like ? ";
//            paramList.add("%"+json.get("batchname")+"%");
//        }
//        //判断有无搜索内容
//        String insql6 = "";
//        if(json.has("searchContent")&&!Util.isEoN(json.get("searchContent")+"")){
//            insql6 = " and batch.name like ? ";
//            paramList.add("%"+json.get("searchContent")+"%");
//        }
//        //判断路由有无大批次id
//        String insql7 = "";
//        if(json.has("bigbatchid")&&!Util.isEoN(json.get("bigbatchid")+"")){
//            insql7 = " and bigbatch.id = ? ";
//            paramList.add(json.get("bigbatchid")+"");
//        }
//        //语句
//        String sql = "select e.*, " +
//                " to_char(e.savedate,'yyyy-MM-dd HH24:mi:ss') as as_savedate," +
//                " (case when e.isysb = '1' then '是' else '否' end) as as_isysb, " +
//                " batch.name as batchname " +
//                " from bi_predeclare_data e " +
//                " left join pms_planprojectbatch batch on e.batchid = batch.id " +
//                " left join pms_planproject bigbatch on batch.planprojectid = bigbatch.id " +
//                " where 1=1  " +insql1+insql2+insql3+insql4+insql5+insql6+insql7+
//                " order by e.savedate desc";
//        //分页配置
//        JSONObject pageConfig = json.getJSONObject("pageConfig");
//        //分页处理
//        Page<Map> page = this.pageService.findPageBySqlForRownum(sql,paramList,pageConfig);
//        return page;
//    }
//
//    /**
//     * 获取专家列表--用于打标签
//     * @param jsonObject
//     * @return
//     */
//    public Page<Map> expertlist_tjbq_getData(JSONObject jsonObject) {
//        List<Map> result = new ArrayList<>();
//        Map<String, JSONArray> subjectcodeArrMap = new ArrayMap<>();
//        String []subject_scope = SubjecttypeEnum.fetchSubjecttypeStrArr();
//        String xkSelectInsql = "";
//        String bqnameSelectInsql = "";
//        String bqidSelectInsql = "";
//        String commonSelectInsql = "";
//        for(String tempSubjecttype : subject_scope) {
//            xkSelectInsql += " \n ,(select " + tempSubjecttype + "\n" +
//                    " from (\n" +
//                    "     select xk.PERSON_ID,listagg(rtrim(xk.SUBJECTONENAME || '-' || xk.SUBJECTTWONAME || '-' || xk.SUBJECTTHREENAME || '-' || xk.SUBJECTFOURNAME, '-') || '(' || xk.SUBJECTENDCODE || ')') over ( partition by xk.PERSON_ID order by xk.SEQ) " + tempSubjecttype + "\n" +
//                    "          , row_number() over (partition by xk.PERSON_ID order by xk.SEQ desc) rn\n" +
//                    "     from ZJK_RYJBXXXKFL_UPDATE xk\n" +
//                    "     where xk.SUBJECTTYPE = '" + tempSubjecttype + "'\n" +
//                    " ) tt where tt.rn = 1 and tt.PERSON_ID = t.ID ) " + tempSubjecttype+ "";
//        }
//        bqnameSelectInsql += " \n ,(select bqname \n" +
//                " from (\n" +
//                "     select l.ZJKRYJBXXUPDATEID,listagg(b.NAME) over ( partition by l.ZJKRYJBXXUPDATEID order by l.ID) bqname \n" +
//                "          , listagg(b.id) over ( partition by l.ZJKRYJBXXUPDATEID order by l.ID) bqid \n" +
//                "          , row_number() over (partition by l.ZJKRYJBXXUPDATEID order by l.ID desc) rn\n" +
//                "     from ZJK_LINKBQ l \n" +
//                "     left join ZJK_BQ b on b.ID = l.ENDZJKBQID \n" +
//                "     where l.type = 'bq' \n" +
//                " ) tt where tt.rn = 1 and tt.ZJKRYJBXXUPDATEID = t.ID ) bqname ";
//        bqidSelectInsql += " \n ,(select bqid \n" +
//                " from (\n" +
//                "     select l.ZJKRYJBXXUPDATEID, listagg(l.ENDZJKBQID) over ( partition by l.ZJKRYJBXXUPDATEID order by l.ID) bqid \n" +
//                "          , row_number() over (partition by l.ZJKRYJBXXUPDATEID order by l.ID desc) rn\n" +
//                "     from ZJK_LINKBQ l \n" +
//                "     where l.type = 'bq' \n" +
//                " ) tt where tt.rn = 1 and tt.ZJKRYJBXXUPDATEID = t.ID ) bqid ";
//        commonSelectInsql = " \n , t.expert_type, t.province_region as region, t.position_title, t.organization_province, " +
//                "t.organization_city, t.organization_county, t.organization_province || t.organization_city || t.organization_county organization_location ";
//
//        for(String tempSubjecttype : subject_scope) {
//            String subjectcodeStr = jsonObject.get(tempSubjecttype + "subjectcode") + "";
//            if(!Util.isEoN(subjectcodeStr)) {
//                subjectcodeArrMap.put(tempSubjecttype, JSONArray.fromObject(subjectcodeStr.split(",")));
//            }
//        }
//
//        JSONArray paramArr = new JSONArray();
//        String commonFilterInsql = "";
//        //专家姓名
//        if(!Util.isEoN(jsonObject.get("name"))) {
//            commonFilterInsql += " and t.name like ? ";
//            paramArr.add("%" + jsonObject.get("name") + "%");
//        }
//        //专家单位
//        if(!Util.isEoN(jsonObject.get("organization"))) {
//            commonFilterInsql += " and t.organization like ? ";
//            paramArr.add("%" + jsonObject.get("organization") + "%");
//        }
//        //省内外
//        if(!Util.isEoN(jsonObject.get("region")) && !"2".equals(jsonObject.get("region"))) {
//            if("0".equals(jsonObject.get("region"))) {
//                commonFilterInsql += " and t.province_region = '省外' ";
//                //省外 省名称
//                if(!Util.isEoN(jsonObject.get("organization_province"))) {
//                    //省名称
//                    commonFilterInsql += " \n and t.organization_province = ? ";
//                    paramArr.add(jsonObject.get("organization_province"));
//                }
//            }else{
//                commonFilterInsql += " and t.province_region = '省内' ";
//            }
//            if(!Util.isEoN(jsonObject.get("city"))) {
//                //市名称
//                commonFilterInsql += " \n and t.organization_city = ? ";
//                paramArr.add(jsonObject.get("city"));
//            }
//            if(!Util.isEoN(jsonObject.get("county"))) {
//                //县区
//                commonFilterInsql += " \n and t.organization_county = ? ";
//                paramArr.add(jsonObject.get("county"));
//            }
//        } else {
//            if(!Util.isEoN(jsonObject.get("organization_province"))) {
//                //省名称
//                commonFilterInsql += " \n and t.organization_province = ? ";
//                paramArr.add(jsonObject.get("organization_province"));
//            }
//            if(!Util.isEoN(jsonObject.get("city"))) {
//                //市名称
//                commonFilterInsql += " \n and t.organization_city = ? ";
//                paramArr.add(jsonObject.get("city"));
//            }
//            if(!Util.isEoN(jsonObject.get("county"))) {
//                //县区
//                commonFilterInsql += " \n and t.organization_county = ? ";
//                paramArr.add(jsonObject.get("county"));
//            }
//        }
//
//        //专家类型
//        if(!Util.isEoN(jsonObject.get("expert_type"))) {
//            commonFilterInsql += " \n and t.expert_type like ? ";
//            paramArr.add("%" + jsonObject.get("expert_type") + "%");
//        }
//
//        //专家职称
//        if(!Util.isEoN(jsonObject.get("position_title"))) {
//            commonFilterInsql += " \n and t.position_title = ? ";
//            paramArr.add(jsonObject.get("position_title"));
//        }
//
//        //专家年龄区间
//        if(Util.isEoN(jsonObject.get("ageGroup"))) {
//            jsonObject.put("ageGroup", "0");
//        }
//        if(Util.isEoN(jsonObject.get("ageGroups"))) {
//            jsonObject.put("ageGroups", "0");
//        }
//        if(!"0".equals(jsonObject.get("ageGroup") + "") && !"0".equals(jsonObject.get("ageGroups") + "")) {
//            commonFilterInsql += " \n and length(t.birthday)=10 and (trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd')) / 12) between ? and ? )";
//            paramArr.add(jsonObject.get("ageGroup"));
//            paramArr.add(jsonObject.get("ageGroups"));
//        } else if(!"0".equals(jsonObject.get("ageGroup") + "") && "0".equals(jsonObject.get("ageGroups") + "")) {
//            commonFilterInsql += " \n and length(t.birthday)=10 and (trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd')) / 12) >= ? )";
//            paramArr.add(jsonObject.get("ageGroup"));
//        } else if("0".equals(jsonObject.get("ageGroup") + "") && !"0".equals(jsonObject.get("ageGroups") + "")) {
//            commonFilterInsql += " \n and length(t.birthday)=10 and (trunc(months_between(sysdate,to_date(t.birthday,'yyyy-mm-dd')) / 12) <= ? )";
//            paramArr.add(jsonObject.get("ageGroups"));
//        }
//
//        //关键字
//        if(!Util.isEoN(jsonObject.get("gjz"))){
//            String tempSql = "";
//            String[] keywords = jsonObject.get("gjz").toString().split(",");
//            if(!Util.isEoN(keywords) && keywords.length > 0) {
//                tempSql = " \n and ( ";
//                for(int i = 0; i < keywords.length; i++){
//                    if(i == 0) {
//                        tempSql += " t.research_direction like ? ";
//                    } else {
//                        tempSql += " or t.research_direction like ? ";
//                    }
//                    paramArr.add("%" + keywords[i] + "%");
//                }
//                tempSql += " and 1 = 1 )";
//            }
//            commonFilterInsql += tempSql;
//        }
//
////        //核心专家
////        if(!Util.isEoN(jsonObject.get("iscoreexpert"))) {
////            if("1".equals(jsonObject.get("iscoreexpert"))) {
////                commonFilterInsql += " \n and t.iscoreexpert = '1' ";
////            } else {
////                commonFilterInsql += " \n and (t.iscoreexpert = '0' or t.iscoreexpert is null) ";
////            }
////        }
////
////        //核心专家
////        if(!Util.isEoN(jsonObject.get("isjjcoreexpert"))) {
////            if("1".equals(jsonObject.get("isjjcoreexpert"))) {
////                commonFilterInsql += " \n and t.isjjcoreexpert = '1' ";
////            } else {
////                commonFilterInsql += " \n and (t.isjjcoreexpert = '0' or t.isjjcoreexpert is null) ";
////            }
////        }
//
//        //专家来源
//        if(!Util.isEoN(jsonObject.get("drbj"))) {
//            if("省厅".equals(jsonObject.get("drbj"))) {
//                commonFilterInsql += " \n and t.drbj = '2' ";
//            } else {
//                commonFilterInsql += " \n and (t.drbj != '2' or t.drbj is null) ";
//            }
//        }
//
//        //市级专家
//        if(!Util.isEoN(jsonObject.get("sjzj"))) {
//            if("1".equals(jsonObject.get("sjzj"))) {
//                commonFilterInsql += " \n and t.sjzj = 'true' ";
//            } else {
//                commonFilterInsql += " \n and (t.sjzj = 'false' or t.sjzj is null) ";
//            }
//        }
//
//        if(!Util.isEoN(jsonObject.get("qwzj")) || !Util.isEoN(jsonObject.get("rktj"))) {
//            if("1".equals(jsonObject.get("qwzj")) || "1".equals(jsonObject.get("rktj"))) {
//                commonFilterInsql += " \n and t.rktj like '%;%' ";
//            } else {
//                commonFilterInsql += " \n and t.rktj is null ";
//            }
//        }
//
//        //标签信息
//        if(!Util.isEoN(jsonObject.get("bqid")+"")){
//            String tempbqinsql = "";
//            String[] bqids = jsonObject.get("bqid").toString().split(",");
//            for(int i = 0; i < bqids.length; i++) {
//                if(i != 0) {
//                    tempbqinsql += " or tt.endzjkbqid = ? ";
//                } else {
//                    tempbqinsql += " tt.endzjkbqid = ? ";
//                }
//                paramArr.add(bqids[i]);
//            }
//            if(bqids.length > 0) {
//                commonFilterInsql += " \n and exists (select tt.zjkryjbxxupdateid from zjk_linkbq tt where ( " + tempbqinsql + " ) and tt.zjkryjbxxupdateid = t.id) ";
//            }
//        }
//
//        for(String tempSubjecttype : subject_scope) {
//            //学科信息
//            JSONArray subjectcodeArr = subjectcodeArrMap.get(tempSubjecttype);
//            if(!Util.isEoN(subjectcodeArr) && subjectcodeArr.size() > 0) {
//                String subjectonecodeStr = Util.sqlSplicingForInStatement(subjectcodeArr, "xkfl.subjectonecode");
//                String subjecttwocodeStr = Util.sqlSplicingForInStatement(subjectcodeArr, "xkfl.subjecttwocode");
//                String subjectthreecodeStr = Util.sqlSplicingForInStatement(subjectcodeArr, "xkfl.subjectthreecode");
//                String subjectfourcodeStr = Util.sqlSplicingForInStatement(subjectcodeArr, "xkfl.subjectfourcode");
//                if(!Util.isEoN(subjectonecodeStr)) {
//                    commonFilterInsql += " \n and EXISTS (select 1 from ZJK_RYJBXXXKFL_UPDATE xkfl where  xkfl.person_id = t.id ";
//                    commonFilterInsql += " \n and ( ";
//                    commonFilterInsql += " (( " + subjectonecodeStr + " ) or ( " + subjecttwocodeStr + " ) or ( " + subjectthreecodeStr + " ) or ( " + subjectfourcodeStr + " ))  ";
//                    commonFilterInsql += " \n and ";
//                    commonFilterInsql += " (xkfl.subjecttype = '" + tempSubjecttype + "') ";
//                    commonFilterInsql += " )) ";
//                    paramArr.addAll(subjectcodeArr);
//                    paramArr.addAll(subjectcodeArr);
//                    paramArr.addAll(subjectcodeArr);
//                    paramArr.addAll(subjectcodeArr);
//                }
//            }
//        }
//        String sql = " select distinct t.ID, t.NAME, t.MOBILE_TELEPHONE, t.ID_NUMBER, t.ORGANIZATION, t.JTGZDW, " +
//                "(case when t.drbj = '1' then '老系统' else '新系统' end) as drbj,ide.purvieworganizeid as unitid " +
//                commonSelectInsql +
//                xkSelectInsql +
//                bqnameSelectInsql +
//                bqidSelectInsql +
//                " from ZJK_RYJBXX_UPDATE t\n" +
//                " left join sys_identity ide on ide.userid = t.id and ide.roleid = '5A595EE9-8F1B-4318-92E5-56A2D50C34D4' " +
//                //" left join ZJK_RYJBXXXKFL_UPDATE xkfl on xkfl.PERSON_ID = t.ID\n" +
//                //" where t.ISCANPICK = '1' and t.RKFLAG = '1' \n" +
//                " where t.RKFLAG = '1' \n" +
//                commonFilterInsql +
//                //"   and t.MOBILE_TELEPHONE is not null and t.organization is not null \n";
//                "  \n";
//        //分页配置
//        JSONObject pageConfig = jsonObject.getJSONObject("pageConfig");
//        //分页处理
//        Page<Map> page = this.pageService.findPageBySql(sql, paramArr, pageConfig);
//        return page;
//    }
//
//    /**
//     * 获取静态 专家统计数据
//     */
//    public JSONObject expert_tj_static() {
//        JSONObject result = new JSONObject();
//
//        //专家来源
//        JSONObject zjly = new JSONObject();
//        String sql1 = "select 'stzj' as property ,sum (case when DRBJ ='2' then 1 else 0 end) total  from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1'\n" +
//                "union\n" +
//                "select 'gzzj' as property ,sum (case when DRBJ !='2' or DRBJ is null then 1 else 0 end) total  from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1'\n";
//        List<Map> rows1 = dbHelper.getRows(sql1, null);
//        if (null!=rows1 && rows1.size()>0){
//            for (Map map : rows1) {
//                zjly.put(map.get("property"),map.get("total")+"");
//            }
//        }else {
//            zjly.put("stzj","0");
//            zjly.put("gzzj","0");
//        }
//        result.put("zjly",zjly);
//
//
//        //市内外
//        JSONObject snwzj = new JSONObject();
//        String sql2 = "select 'sn' as property ,sum (case when organization_city='广州市' then 1 else 0 end) total  from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1'\n" +
//                "union\n" +
//                "select 'sw' as property ,sum (case when organization_city!='广州市' or organization_city is null then 1 else 0 end) total  from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1'\n";
//        List<Map> rows2 = dbHelper.getRows(sql2, null);
//        if (null!=rows2 && rows2.size()>0){
//            for (Map map : rows2) {
//                snwzj.put(map.get("property"),map.get("total")+"");
//            }
//        }else {
//            snwzj.put("sn","0");
//            snwzj.put("sw","0");
//        }
//        result.put("snwzj",snwzj);
//
//
//
//        //职称
//        JSONObject title = new JSONObject();
//        String sql3 = "select 'zgj' as property ,sum(case when POSITION_TITLE ='正高级' then 1 else 0 end) total from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1'\n" +
//                "union\n" +
//                "select 'fgj' as property ,sum(case when POSITION_TITLE ='副高级' then 1 else 0 end) total from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1'\n" +
//                "union\n" +
//                "select 'zj' as property ,sum(case when POSITION_TITLE ='中级' then 1 else 0 end) total from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1'\n" +
//                "union\n" +
//                "select 'wu' as property ,sum(case when POSITION_TITLE ='无' then 1 else 0 end) total from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1'\n" +
//                "union\n" +
//                "select 'cjzlj' as property ,sum(case when POSITION_TITLE ='初级助理级' then 1 else 0 end) total from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1'\n" +
//                "union\n" +
//                "select 'cjyj' as property ,sum(case when POSITION_TITLE ='初级员级' then 1 else 0 end) total from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1'\n";
//        List<Map> rows3 = dbHelper.getRows(sql3, null);
//        if (null!=rows3 && rows3.size()>0){
//            for (Map map : rows3) {
//                title.put(map.get("property"),map.get("total")+"");
//            }
//        }else {
//            title.put("zgj","0");
//            title.put("fgj","0");
//            title.put("zj","0");
//            title.put("wu","0");
//            title.put("cjzlj","0");
//            title.put("cjyj","0");
//        }
//        result.put("title",title);
//
//
//        //专家类型
//        JSONObject expert_type = new JSONObject();
//        String sql4 = "select 'cwlzj' as property ,count(*) total from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1' and EXPERT_TYPE like '%财务类专家%'\n" +
//                "union\n" +
//                "select 'gllzj' as property ,count(*) total from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1' and EXPERT_TYPE like '%管理类专家%'\n" +
//                "union\n" +
//                "select 'jslzj' as property ,count(*) total from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1' and EXPERT_TYPE like '%技术类专家%'\n" +
//                "union\n" +
//                "select 'fllzj' as property ,count(*) total from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1' and EXPERT_TYPE like '%f法律类专家%'\n" +
//                "union\n" +
//                "select 'kjjrlzj' as property ,count(*) total from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1' and EXPERT_TYPE like '%科技金融类专家%'\n";
//        List<Map> rows4 = dbHelper.getRows(sql4, null);
//        if (null!=rows4 && rows4.size()>0){
//            for (Map map : rows4) {
//                expert_type.put(map.get("property"),map.get("total")+"");
//            }
//        }else {
//            expert_type.put("cwlzj","0");
//            expert_type.put("gllzj","0");
//            expert_type.put("jslzj","0");
//            expert_type.put("fllzj","0");
//            expert_type.put("kjjrlzj","0");
//        }
//        result.put("expert_type",expert_type);
//
//
///*        //所属省份
//        JSONObject belong_procince = new JSONObject();
//        String sql5 = "select ORGANIZATION_PROVINCE property,count(ORGANIZATION_PROVINCE) total from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1' group by ORGANIZATION_PROVINCE\n";
//        List<Map> rows5 = dbHelper.getRows(sql5, null);
//        if (null!=rows5 && rows5.size()>0){
//            for (Map map : rows5) {
//                belong_procince.put(map.get("property"),map.get("total")+"");
//            }
//        }
//        result.put("belong_province",belong_procince);*/
//
//        return result;
//    }
//    /**
//     * 获取动态 专家统计数据
//     */
//    public JSONObject expert_tj_dynamic(JSONObject param) {
//        JSONObject result = new JSONObject();
//
//        String basesql = "select count(*) total from ZJK_RYJBXX_UPDATE a " +
//                " left join ZJK_RYJBXXXKFL_UPDATE b on b.person_id = a.id " +
//                " where a.RKFLAG = '1' and a.ISCANPICK = '1' ";
//
//        String insql = "";
//
//        List paramList = new ArrayList();
//
//        String snwzj = param.get("snwzj")+"";
//        if ("市内".equals(snwzj)){
//            insql += " and a.organization_city = '广州市' ";
//        }else if ("市外".equals(snwzj)){
//            insql += " and (a.organization_city != '广州市' or a.organization_city is null ) ";
//        }
//
//        String zjly = param.get("zjly")+"";
//        if ("省厅".equals(zjly)){
//            insql += " and a.drbj = '2' ";
//        }else if ("广州".equals(zjly)){
//            insql += " and (a.drbj != '2' or a.drbj is null) ";
//        }
//
//        String title = param.get("title")+"";
//        if (!Util.isEoN(title)&&!"全部".equals(title)){
//            insql += " and a.position_title = ?";
//            paramList.add(title);
//        }
//
//        String belong_province = param.get("belong_province")+"";
//        if (!Util.isEoN(belong_province)&&!"全部".equals(belong_province)){
//            insql += " and a.ORGANIZATION_PROVINCE = ?";
//            paramList.add(belong_province);
//        }
//
//        String expert_type = param.get("expert_type")+"";
//        if (!Util.isEoN(title)&&!"全部".equals(expert_type)){
//            insql += " and a.EXPERT_TYPE like ? ";
//            paramList.add("%"+expert_type+"%");
//        }
//
//        //学科领域之间应该用or连接  ， 学科领域和其他之间用and连接
//        int xklycount = 0;//前台传参学科和领域的种类数
//        String gbxksubjectcode = param.get("gbxksubjectcode")+"";
//        if (!Util.isEoN(gbxksubjectcode)){
//            xklycount ++;
//        }
//        String jjxksubjectcode = param.get("jjxksubjectcode")+"";
//        if (!Util.isEoN(jjxksubjectcode)){
//            xklycount++;
//        }
//        String sclysubjectcode = param.get("sclysubjectcode")+"";
//        if (!Util.isEoN(sclysubjectcode)){
//            xklycount++;
//        }
//        String hylysubjectcode = param.get("hylysubjectcode")+"";
//        if (!Util.isEoN(hylysubjectcode)){
//            xklycount++;
//        }
//        if (xklycount>=1){
//            insql += " and ( ";
//        }
//
//
//        //国标学科
//        String[] gbxkcodelist = new String []{};
//        if (!Util.isEoN(gbxksubjectcode)){
//            gbxkcodelist = gbxksubjectcode.split(",");
//        }
//        if (gbxkcodelist.length>0){
//            String gbxksql = "b.XMZLFL='gbxk' ";
//            gbxksql += " and ( ";
//            for (String s : gbxkcodelist) {
//                gbxksql += " b.SUBJECTENDCODE like ? or ";
//                paramList.add(s+"%");
//            }
//            gbxksql = gbxksql.substring(0,gbxksql.length()-3);
//            gbxksql += " ) or ";
//            insql += gbxksql;
//        }
//
//        //基金学科
//        String[] jjxkcodelist = new String []{};
//        if (!Util.isEoN(jjxksubjectcode)){
//            jjxkcodelist = jjxksubjectcode.split(",");
//        }
//        if (jjxkcodelist.length>0){
//            String jjxksql = " b.XMZLFL='jjxk' ";
//            jjxksql += " and ( ";
//            for (String s : jjxkcodelist) {
//                jjxksql += " b.SUBJECTENDCODE like ? or ";
//                paramList.add(s+"%");
//            }
//            jjxksql = jjxksql.substring(0,jjxksql.length()-3);
//            jjxksql += " ) or ";
//            insql += jjxksql;
//        }
//
//        //擅长领域
//        String[] sclycodelist = new String []{};
//        if (!Util.isEoN(sclysubjectcode)){
//            sclycodelist = sclysubjectcode.split(",");
//        }
//        if (sclycodelist.length>0){
//            String sclysql = " and b.XMZLFL='scly' ";
//            sclysql += " and ( ";
//            for (String s : sclycodelist) {
//                sclysql += " b.SUBJECTENDCODE like ? or ";
//                paramList.add(s+"%");
//            }
//            sclysql = sclysql.substring(0,sclysql.length()-3);
//            sclysql += " ) or ";
//            insql += sclysql;
//        }
//
//        //行业领域
//        String[] hylycodelist = new String []{};
//        if (!Util.isEoN(hylysubjectcode)){
//            hylycodelist = hylysubjectcode.split(",");
//        }
//        if (hylycodelist.length>0){
//            String hylysql = " and b.XMZLFL='hyly' ";
//            hylysql += " and ( ";
//            for (String s : hylycodelist) {
//                hylysql += " b.SUBJECTENDCODE like ? or ";
//                paramList.add(s+"%");
//            }
//            hylysql = hylysql.substring(0,hylysql.length()-3);
//            hylysql += " ) or ";
//            insql += hylysql;
//        }
//        if (xklycount>=1){
//            //不管学科或者领域传了几种，只要有一个 最后就是 or 结尾 ，去掉并加上 )
//            insql = insql.substring(0,insql.length()-3);
//            insql +=" )";
//        }
//        String total = dbHelper.getOnlyStringValue(basesql + insql, paramList.toArray());
//        result.put("count",total);
//
//        return result;
//    }
//
//    /**
//     * 专家入库情况统计
//     */
//    public JSONObject expert_rk(JSONObject param){
//        JSONObject result = new JSONObject();
//
//        String tocharsql = "";
//        List paramList = new ArrayList();
//        String year = param.get("year")+"";
//        if (Util.isEoN(year)){
//            throw new BusinessException("未获取到年份信息");
//        }
//        String month = param.get("month")+"";
//        if (Util.isEoN(month)){//仅传年份
//            tocharsql = "'yyyy'";
//            paramList.add(year);
//        }else {//年月都有
//            tocharsql = "'yyyy-MM'";
//            paramList.add(year+"-"+month);
//        }
//
//        //总数
//        JSONObject total = new JSONObject();
//        List<Map> rows = dbHelper.getRows("" +
//                "select 'stzj' as property ,sum (case when DRBJ ='2' then 1 else 0 end) total  from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1'\n" +
//                "union\n" +
//                "select 'gzzj' as property ,sum (case when DRBJ !='2' or DRBJ is null then 1 else 0 end) total  from ZJK_RYJBXX_UPDATE where RKFLAG = '1' and ISCANPICK = '1'", null);
//        if (null!=rows && rows.size()>0){
//            for (Map map : rows) {
//                total.put(map.get("property"),Util.isEoN(map.get("total")+"")?"0":map.get("total")+"");
//            }
//        }else {
//            total.put("stzj","0");
//            total.put("gzzj","0");
//        }
//        result.put("total",total);
//
//        //指定时间段的专家入库 来源、职称、类型
//        JSONObject lyzclx = new JSONObject();
//        List<Map> rows1 = dbHelper.getRows("" +
//                        "with temp as (select distinct (u.id) id from ZJK_RYJBXX_UPDATE u\n" +
//                        "        INNER JOIN FL_FLOWRECORD f ON f.SOURCEID = u.ID || 'ZJXX'\n" +
//                        "    where to_char(f.OPERATIONTIME," + tocharsql + ") = ?\n" +
//                        "      and (f.CURRENTFLOWPOINTID = 'EXPERT_ZC_2' OR f.CURRENTFLOWPOINTID = 'SWEXPERT_ZC_2') )\n" +
//                        "select 'stzj' as property ,sum (case when DRBJ='2' then 1 else 0 end) total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE  b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1'\n" +
//                        "union\n" +
//                        "select 'gzszj' as property ,sum (case when DRBJ!='2' or DRBJ is null then 1 else 0 end) total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE  b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1'\n" +
//                        "union\n" +
//                        "select 'zgj' as property ,sum (case when POSITION_TITLE ='正高级' then 1 else 0 end) total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE  b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1'\n" +
//                        "union\n" +
//                        "select 'fgj' as property ,sum (case when POSITION_TITLE ='副高级' then 1 else 0 end) total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE  b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1'\n" +
//                        "union\n" +
//                        "select 'zj' as property ,sum (case when POSITION_TITLE ='中级' then 1 else 0 end) total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE  b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1'\n" +
//                        "union\n" +
//                        "select 'wu' as property ,sum (case when POSITION_TITLE ='无' then 1 else 0 end) total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE  b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1'\n" +
//                        "union\n" +
//                        "select 'cjzlj' as property ,sum (case when POSITION_TITLE ='初级助理级' then 1 else 0 end) total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE  b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1'\n" +
//                        "union\n" +
//                        "select 'cjyj' as property ,sum (case when POSITION_TITLE ='初级员级' then 1 else 0 end) total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE  b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1'\n" +
//                        "union\n" +
//                        "select 'cwl' as property ,sum (case when EXPERT_TYPE like '%财务类%' then 1 else 0 end) total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE  b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1'\n" +
//                        "union\n" +
//                        "select 'cwl' as property ,sum (case when EXPERT_TYPE like '%财务类%' then 1 else 0 end) total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE  b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1'\n" +
//                        "union\n" +
//                        "select 'gll' as property ,sum (case when EXPERT_TYPE like '%管理类%' then 1 else 0 end) total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE  b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1'\n" +
//                        "union\n" +
//                        "select 'jsl' as property ,sum (case when EXPERT_TYPE like '%技术类%' then 1 else 0 end) total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE  b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1'\n" +
//                        "union\n" +
//                        "select 'fll' as property ,sum (case when EXPERT_TYPE like '%法律类%' then 1 else 0 end) total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE  b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1'\n" +
//                        "union\n" +
//                        "select 'kjjrl' as property ,sum (case when EXPERT_TYPE like '%科技金融类%' then 1 else 0 end) total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE  b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1'"
//                , paramList.toArray());
//        if (null!=rows1 && rows1.size()>0){
//            for (Map map : rows1) {
//                lyzclx.put(map.get("property"),Util.isEoN(map.get("total")+"")?"0":map.get("total")+"");
//            }
//        }else {
//            lyzclx.put("stzj","0");
//            lyzclx.put("gzszj","0");
//            lyzclx.put("zgj","0");
//            lyzclx.put("fgj","0");
//            lyzclx.put("zj","0");
//            lyzclx.put("wu","0");
//            lyzclx.put("cjzlj","0");
//            lyzclx.put("cjyj","0");
//            lyzclx.put("cwl","0");
//            lyzclx.put("gll","0");
//            lyzclx.put("jsl","0");
//            lyzclx.put("fll","0");
//            lyzclx.put("kjjrl","0");
//        }
//        result.put("lyzclx",lyzclx);
//
//        //某一时间段专家入库 的省份
//        List province = new ArrayList();
//        List<Map> rows2 = dbHelper.getRows("" +
//                        "with temp as (select distinct (u.id) id from ZJK_RYJBXX_UPDATE u\n" +
//                        "        INNER JOIN FL_FLOWRECORD f ON f.SOURCEID = u.ID || 'ZJXX'\n" +
//                        "    where to_char(f.OPERATIONTIME," + tocharsql + ") = ?\n" +
//                        "      and (f.CURRENTFLOWPOINTID = 'EXPERT_ZC_2' OR f.CURRENTFLOWPOINTID = 'SWEXPERT_ZC_2') )\n" +
//                        "select b.ORGANIZATION_PROVINCE as province,count(ORGANIZATION_PROVINCE) as total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1' group by b.ORGANIZATION_PROVINCE"
//                , paramList.toArray());
//        if (null!=rows2 && rows2.size()>0){
//            province = rows2;
//        }
//        result.put("province",province);
//
//        //某一时间段专家入库 的各市分部
//        List city = new ArrayList();
//        List<Map> rows3 = dbHelper.getRows("" +
//                        "with temp as (select distinct (u.id) id from ZJK_RYJBXX_UPDATE u\n" +
//                        "        INNER JOIN FL_FLOWRECORD f ON f.SOURCEID = u.ID || 'ZJXX'\n" +
//                        "    where to_char(f.OPERATIONTIME," + tocharsql + ") = ?\n" +
//                        "      and (f.CURRENTFLOWPOINTID = 'EXPERT_ZC_2' OR f.CURRENTFLOWPOINTID = 'SWEXPERT_ZC_2') )\n" +
//                        "select b.ORGANIZATION_CITY as city ,count(ORGANIZATION_CITY) as total from temp\n" +
//                        "    inner join ZJK_RYJBXX_UPDATE b on b.ID = temp.id\n" +
//                        "where b.RKFLAG = '1' and b.ISCANPICK = '1' and ORGANIZATION_PROVINCE='广东省' group by b.ORGANIZATION_CITY"
//                , paramList.toArray());
//        if (null!=rows3 && rows3.size()>0){
//            city=rows3;
//        }
//        result.put("city",city);
//
//        //专家注册审核 和变更审核
//        JSONObject audit = new JSONObject();
//        List<Map> rows4 = dbHelper.getRows("" +
//                "with temp as (select f.* from ZJK_RYJBXX_UPDATE u\n" +
//                "        INNER JOIN FL_FLOWRECORD f ON f.SOURCEID = u.ID || 'ZJXX'\n" +
//                "    where to_char(f.OPERATIONTIME,"+tocharsql+") = ?)\n" +
//                "select 'shzc' as property ,count(id) as total from temp\n" +
//                "where CURRENTFLOWPOINTID = 'EXPERT_ZC_2' OR CURRENTFLOWPOINTID = 'SWEXPERT_ZC_2'\n" +
//                "union\n" +
//                "select 'shbg' as property ,count(id) as total from temp\n" +
//                "where CURRENTFLOWPOINTID = 'EXPERT_BG_2' OR CURRENTFLOWPOINTID = 'SWEXPERT_BG_2'", paramList.toArray());
//        if (null!=rows4 && rows4.size()>0){
//            for (Map map : rows4) {
//                audit.put(map.get("property"),Util.isEoN(map.get("total")+"")?"0":map.get("total")+"");
//            }
//        }else {
//            audit.put("stzj","0");
//            audit.put("gzzj","0");
//        }
//        result.put("audit",audit);
//
//        return result;
//    }
//
//    /**
//     * 获取专家列表--用于打标签（条目数）
//     * @param jsonObject
//     * @return
//     */
//    public String expertlist_tjbq_getData_count(JSONObject jsonObject) {
//
//        String sql = " select distinct t.ID \n" +
//                " from ZJK_RYJBXX_UPDATE t\n" +
//                //" where t.ISCANPICK = '1' and t.RKFLAG = '1' \n" +
//                " where t.RKFLAG = '1' \n" +
//                //"   and t.MOBILE_TELEPHONE is not null and t.organization is not null \n";
//                "  \n";
//        //获取数据的总数
//        String countSql = "select count(*) from ("+sql+")";
//        String totalCount = this.dbHelper.getOnlyStringValue(countSql, null);
//        if(Util.isEoN(totalCount)){
//            totalCount = "0";
//        }
//        return totalCount;
//    }
//
//
//    /**
//     * 【经办人评审】 --- 获取当前经办人所有项目
//     * @param json
//     */
//    public Page<Map> jbrReview(JSONObject json){
//        if(!json.has("planid")){
//            return new Page<>();
//        }
//
//        String jbrid = json.get("userid")+"";
//        String planid = json.get("planid")+"";
//
//        //处理参数
//        List paramList = new ArrayList<>();
//        paramList.add(jbrid);
//
//
//        //评审年度
//        String insql1 = " and p.year = ? ";
//        paramList.add(json.get("year")+"");
//
//        //评审批次id
//        String insql2 = " and p.id = ? ";
//        paramList.add(planid);
//
//        //分组名称
//        String insql3 = "";
//        if(json.has("teamname")&&!Util.isEoN(json.get("teamname")+"")){
//            insql3 = " and g.groupname = ? ";
//            paramList.add(json.get("teamname")+"");
//        }
//
//        //受理编号
//        String insql4 = "";
//        if(json.has("applicationno")&&!Util.isEoN(json.get("applicationno")+"")){
//            insql4 = " and z.applicationno like ? ";
//            paramList.add("%"+json.get("applicationno")+"%");
//        }
//
//        //项目名称
//        String insql5 = "";
//        if(json.has("projectbasename")&&!Util.isEoN(json.get("projectbasename")+"")){
//            insql5 = " and z.projectbasename like ? ";
//            paramList.add("%"+json.get("projectbasename")+"%");
//        }
//
//        //申报单位
//        String insql6 = "";
//        if(json.has("mainorganizers")&&!Util.isEoN(json.get("mainorganizers")+"")){
//            insql6 = " and z.mainorganizers like ? ";
//            paramList.add("%"+json.get("mainorganizers")+"%");
//        }
//
//        //业务处室
//        String insql7 = "";
//        if(json.has("belonglabid")&&!Util.isEoN(json.get("belonglabid")+"")){
//            insql7 = " and z.belonglabid = ? ";
//            paramList.add(json.get("belonglabid")+"");
//        }
//
//
//        String sql = "select r.assessment,z.projectbaseid as baseid,g.id as tid,g.groupname as teamname,z.planprojectbatchid,z.id,  " +
//                "z.mainorganizers,z.applicationno,z.projectleader,z.projectplantype,u.name as batchname," +
//                "z.projectbasename,z.citycasemanagement,z.projectsumforgov, " +
//                "r.status,r.jbrid,r.pid, '' fund,r.id as scoreid,r.sheetid,r.totalscore,r.totalscore_weight," +
//                "w.type as business_type,w.experttype,w.unittype,w.name as sheetname,w.sheettype,p.id as planid," +
//                "p.type as pstype,r.tjjg " +
//                "from rev_common_jbrscore r  " +
//                "inner join zjk_batch_plan p on p.id = r.planid " +
//                "inner join zjk_batch_group g on g.planid = p.id  and r.TID = g.ID " +
//                "inner join zjk_batch_project z on z.projectbaseid = r.pid and z.groupid = g.id " +
//                "inner join pms_planprojectbatch u on u.id = .planprojectbatchid  " +
//                "inner join rev_common_scoresheet w on w.id = r.sheetid " +
//                "where r.jbrid = ? " +insql1+insql2+insql3+insql4+insql5+insql6+insql7+
//                "order by z.applicationno ";
//
//
//        Page<Map> page = new Page<>();
//        //分页配置
//        if (json.containsKey("pageConfig")) {
//            JSONObject pageConfig = json.getJSONObject("pageConfig");
//            //分页处理
//            page = this.pageService.findPageBySql(sql,paramList,pageConfig);
//        } else {
//            List<Map> list = dbHelper.getRows(sql,paramList.toArray());
//            page.setTotal(list.size());
//        }
//
//        return page;
//    }
//


}
