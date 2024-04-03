package cn.topcheer.halberd.app.dao.jpa;

import cn.topcheer.halberd.app.api.framework.entity.sys.PmsArea;
import cn.topcheer.halberd.app.api.framework.entity.sys.PmsDepartment;
import cn.topcheer.halberd.app.api.framework.entity.sys.PmsEnterprise;
import cn.topcheer.halberd.app.api.framework.entity.sys.SysUser;
import cn.topcheer.halberd.app.api.framework.service.sys.ISysUserService;
import cn.topcheer.halberd.app.api.utils.JsonConfigUtil;
import cn.topcheer.halberd.app.api.utils.PreSaveResult;
import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import cn.topcheer.halberd.flow.entity.FlowBusiness;
import cn.topcheer.halberd.flow.entity.FlowBusinessTaskLog;
import cn.topcheer.halberd.flow.service.FlowBusinessService;
import cn.topcheer.halberd.flow.service.FlowBusinessTaskLogService;
import cn.topcheer.pms2.api.annotation.*;
import cn.topcheer.pms2.api.plan.entity.PmsPlancategory;
import cn.topcheer.pms2.api.project.entity.prize.PmsPrize;
import cn.topcheer.pms2.api.sys.PmsAcceptanceSave;
import cn.topcheer.pms2.api.sys.PmsProcessSave;
import cn.topcheer.pms2.api.sys.PmsResultSave;
import cn.topcheer.pms2.api.sys.SysOperationrecord;
import cn.topcheer.pms2.api.plan.entity.PmsPlanproject;
import cn.topcheer.pms2.api.plan.entity.PmsPlanprojectbatch;
import cn.topcheer.pms2.api.sys.enums.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.*;

@Transactional
@Slf4j
public abstract class GenericService<T> implements IGenericService<T> {

    @Autowired
    private DBHelper dbHelper;

    @Autowired
    private FlowBusinessTaskLogService flowBusinessTaskLogService;

    @Autowired
    private FlowBusinessService flowBusinessService;


    private Class<T> t;

    public GenericService() {
        try {
            Class cls = getClass();
            while (!(cls.getGenericSuperclass() instanceof ParameterizedType)) {
                cls = cls.getSuperclass();
            }
            if (cls != null) {
                t = (Class<T>) ((ParameterizedType) cls.getGenericSuperclass())
                        .getActualTypeArguments()[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;

    private GenericDao<T> genericDao;

    public void setGenericDao(GenericDao<T> genericDao) {
        this.genericDao = genericDao;
    }

    public GenericDao<T> getGenericDao() {
        return genericDao;
    }


    @Override
    public T getById(String id) {
        return this.getGenericDao().findById(id);
    }


    @Override
    public void persist(T t) {
        try {
            this.getGenericDao().persist(t);
        } catch (Exception re) {
            log.error("GenericService persist error", re);
        }
    }

    @Override
    public void attachDirty(T t) {
        try {
            this.getGenericDao().attachDirty(t);
        } catch (Exception re) {
            log.error("GenericService attachDirty error", re);
        }
    }

    @Override
    public void attachClean(T t) {
        try {
            this.getGenericDao().attachClean(t);
        } catch (Exception re) {
            log.error("GenericService attachClean error", re);
        }
    }

    @Override
    public boolean delete(T t) {
        try {
            this.getGenericDao().delete(t);
            return true;
        } catch (Exception re) {
            log.error("GenericService delete error", re);
            return false;
        }
    }

    @Override
    public boolean deleteList(List<T> ts) {
        try {
            for (T t : ts) {
                this.delete(t);
            }
            return true;
        } catch (Exception re) {
            log.error("GenericService deleteList error", re);
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        try {
            return this.getGenericDao().deleteAll();
        } catch (Exception re) {
            log.error("GenericService deleteAll error", re);
            return false;
        }
    }

    @Override
    public boolean save(T t) {
        try {
            this.getGenericDao().save(t);
            return true;
        } catch (Exception re) {
            log.error("GenericService save error", re);
            return false;
        }
    }

    @Override
    public void update(T t) {
        try {
            this.getGenericDao().update(t);
        } catch (Exception re) {
            log.error("GenericService update error", re);
        }
    }

    @Override
    public T saveOrUpdate(T t) {
        try {
            return this.getGenericDao().saveOrUpdate(t);
        } catch (Exception re) {
            log.error("GenericService saveOrUpdate error", re);
            return null;
        }
    }

    @Override
    public void saveOrUpdateEntity(Object entity) {
        try {
            this.getGenericDao().getSessionFactory().getCurrentSession()
                    .saveOrUpdate(entity);
        } catch (Exception re) {
            log.error("GenericService saveOrUpdateEntity error", re);
        }
    }

    @Override
    public T merge(T t) {
        try {
            return this.getGenericDao().merge(t);
        } catch (Exception re) {
            log.error("GenericService merge error", re);
            return null;
        }
    }

    @Override
    public List<T> findParams(String filter, Object... values) {
        return this.getGenericDao().findParams(filter, values);
    }

    @Override
    public List findByHql(String hql, Object... values) {
        return this.getGenericDao().findByHql(hql, values);
    }


    @Override
    public T findById(String id) {
        try {
            return this.getGenericDao().findById(id);
        } catch (Exception re) {
            log.error("GenericService findById error", re);
            return null;
        }
    }

    @Override
    public List<T> findByIds(List<String> ids) {
        try {
            return this.getGenericDao().findByIds(ids);
        } catch (Exception re) {
            log.error("GenericService findByIds error", re);
            return null;
        }
    }

    @Override
    public List<T> findByIds(List<String> ids, boolean flag) {
        try {
            return this.getGenericDao().findByIds(ids, true);
        } catch (Exception re) {
            log.error("GenericService findByIds error", re);
            return null;
        }
    }

    @Override
    public List<T> findByExample(T t) {
        try {
            return this.getGenericDao().findByExample(t);
        } catch (Exception re) {
            log.error("GenericService findByExample error", re);
            return null;
        }
    }


    @Override
    public boolean deleteById(String id) {
        try {
            return this.getGenericDao().deleteById(id);
        } catch (Exception re) {
            log.error("GenericService deleteById error", re);
            return false;
        }
    }

    @Override
    public boolean deleteByIds(List<String> ids) {
        try {

            for (int i = ids.size() - 1; i >= 0; i--) {
                if (ids.get(i) == null || ids.get(i).trim().equals("")) {
                    ids.remove(i);
                }
            }
            return this.getGenericDao().deleteByIds(ids);
        } catch (Exception re) {
            log.error("GenericService deleteByIds error", re);
            return false;
        }
    }

    @Override
    public boolean deleteByIds(Object[] ids) {
        try {
            ArrayList<String> aids = new ArrayList<String>();
            for (int i = 0; i < ids.length; i++) {
                aids.add(ids[i].toString());
            }

            return this.deleteByIds(aids);
        } catch (Exception re) {
            log.error("GenericService deleteByIds error", re);
            return false;
        }
    }

    public Class getEntityClassByName(String className) {
        return this.genericDao.getEntityClassByName(className);
    }

    @Override
    public List<T> findAll() {
        return this.find("", "");
    }

    @Override
    public List<T> findSort(String sortField) {
        return this.find(sortField, "");
    }

    @Override
    public List<T> findFilter(String filter) {
        return this.find("", filter);
    }

    @Override
    public List<T> find(String orderby, String filter) {
        try {
            return this.getGenericDao().find(orderby, filter);
        } catch (Exception re) {
            log.error("GenericService find error", re);
            return null;
        }
    }

    @Override
    public List<T> findByProperty(String propertyName, Object value) {
        try {
            return this.getGenericDao().findByProperty(propertyName, value);
        } catch (Exception re) {
            log.error("GenericService findByProperty error", re);
            return null;
        }
    }

    @Override
    public List<T> findByPropertyASC(String propertyName, Object value) {
        try {
            return this.getGenericDao().findByPropertyASC(propertyName, value);
        } catch (Exception re) {
            log.error("GenericService findByProperty error", re);
            return null;
        }
    }

    /**
     * 模糊查询
     */
    @Override
    public List<T> findByPropertyLike(String propertyName, Object value) {
        try {
            return this.getGenericDao().findByPropertyLike(propertyName, value);
        } catch (Exception re) {
            log.error("GenericService findByPropertyLike error", re);
            return null;
        }
    }


    @Override
    public void save(T rt, Map<String, Object> params) {
        this.save(rt);
    }

    @Override
    public Query getQuery(String hsql) {
        Query query = this.getGenericDao().getQuery(hsql);
        return query;
    }

    @Override
    public Query getSqlQueryNotEntity(String sql) {
        Query query = this.getGenericDao().getSqlQueryNotEntity(sql);
        return query;
    }

    @Override
    public Query getSqlQuery(String sql) {
        Query query = this.getGenericDao().getSqlQuery(sql);
        return query;
    }

    @Override
    public List<T> getPaginationDao(int pageNum, int pageSize, String orderby,
                                    String filterString) {
        try {
            return this.getGenericDao().getPaginationDao(pageNum, pageSize,
                    orderby, filterString);
        } catch (Exception re) {
            log.error("GenericService getPaginationDao error", re);
            return null;
        }
    }

    @Override
    public List getPaginationBySql(int pageNum, int pageSize, String sql,
                                   boolean ishql) {
        try {
            return this.getGenericDao().getPaginationBySql(pageNum, pageSize,
                    sql, ishql);
        } catch (Exception re) {
            log.error("GenericService getPaginationBySql error", re);
            return null;
        }
    }

    @Override
    public Map getPaginationMap(int pageNum, int pageSize, String orderby,
                                String filterString) {
        try {
            return this.getGenericDao().getPaginationMap(pageNum, pageSize,
                    orderby, filterString);
        } catch (Exception re) {
            log.error("GenericService getPaginationMap error", re);
            return null;
        }
    }

    @Override
    public long getDaoCount() {
        try {
            return this.getGenericDao().getDaoCount();
        } catch (Exception re) {
            log.error("GenericService getDaoCount error", re);
            return 0;
        }
    }

    @Override
    public long getDaoCount(String filterString, boolean initsearchHql) {
        try {
            return this.getGenericDao()
                    .getDaoCount(filterString, initsearchHql);
        } catch (Exception re) {
            log.error("GenericService getDaoCount error", re);
            return 0;
        }
    }


    @Override
    public T findOne(HqlBuilder hqlBuilder) {
        return this.getGenericDao().findOne(hqlBuilder);
    }

    @Override
    public List<T> find(HqlBuilder<T> hqlBuilder) {
        return this.getGenericDao().find(hqlBuilder);
    }

    @Override
    public Long findCount(HqlBuilder<T> hqlBuilder) {
        return this.getGenericDao().findCount(hqlBuilder);
    }

    @Override
    public PageResult<List<T>> getPagination(Page page, T o) {
        return this.getGenericDao().getPagination(page, o);
    }


    @Override
    public PageResult<List<T>> getPagination(Page page, HqlBuilder hqlBuilder) {
        return this.getGenericDao().getPagination(page, hqlBuilder);
    }

    @Override
    public PageResult<List<T>> getPagination(Page page, String filterString) {
        return this.getGenericDao().getPagination(page, filterString);
    }


    /*******************************************************************    完美的分割线     *******************************************************************
     */


    /******************************************************************************************************
     *                                         Start:  Sql相关方法集合             						  *
     *******************************************************************************************************/

    /**
     * 【通用方法】--通过sql语句查询集合
     *
     * @param sql
     * @param params
     * @return
     */
    public List<Map> getListBySql(String sql, Object[] params) {
        try {
            Query query = this.getGenericDao().getCurrentSession().createSQLQuery(sql);
            if (params != null) {
                if (params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                }
            }
            //设定结果结果集中的每个对象为Map类型
            query.setResultTransformer(Util.ALIAS_TO_LOWER_TO_ENTITY_MAP);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("GenericService getListBySql error", e);
            System.out.println("getListBySql方法报错，通过sql获取集合失败。sql语句：" + sql);
            return null;
        }
    }

    /**
     * 【通用方法】--执行update delete 的sql语句
     *
     * @param sql
     * @param params
     * @return
     */
    public String getOnlyValueBySql(String sql, Object[] params) {
        try {
            Query query = this.getGenericDao().getCurrentSession().createSQLQuery(sql);
            if (params != null) {
                if (params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                }
            }
            //设定结果结果集中的每个对象为Map类型
            query.setResultTransformer(Util.ALIAS_TO_LOWER_TO_ENTITY_MAP);
            List<Map> list = query.list();
            if (list.size() > 0) {
                Map map = list.get(0);
                Set<String> keySet = map.keySet();
                Iterator<String> iterator = keySet.iterator();
                String key = iterator.next();
                return map.get(key) + "";
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("GenericService runSql error", e);
            System.out.println("getOnlyValueBySql方法报错，执行sql语句失败。");
            return null;
        }
    }

    /**
     * 【通用方法】--执行update delete 的sql语句
     *
     * @param sql
     * @param params
     */
    public void runSql(String sql, Object[] params) {
        try {
            Query query = this.getGenericDao().getSessionFactory().getCurrentSession().createSQLQuery(sql);
            if (params != null) {
                if (params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                }
            }
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("GenericService runSql error", e);
            System.out.println("runSql方法报错，执行sql语句失败。sql语句：" + sql);
        }
    }


    //------------------------------------------End:  Sql相关方法集合---------------------------------------//


    /******************************************************* 申报通用方法---数据读取----开始 *************************************/
    /**
     * 通用数据读取接口
     *
     * @param mainid       关联id
     * @param type         类型
     * @param saveFlag     是否已保存过（true：已保存，flase：未保存）
     * @param batchid      批次表id
     * @param tableObject  完整前台数据（解决特殊传参使用）
     * @param initParamArr 初始化数据所需id
     * @return
     */
    public List<Map> genericFetchFun(String mainid, String type, Boolean saveFlag, String batchid, JSONObject tableObject, String[] initParamArr) {

        List<Map> result = new ArrayList<>();
        tableObject = preGenericFetchFun(mainid, type, saveFlag, batchid, tableObject, initParamArr);
        result = coreGenericFetchFun(result, mainid, type, saveFlag, batchid, tableObject, initParamArr);
        result = afterGenericFetchFun(result, mainid, type, saveFlag, batchid, tableObject, initParamArr);
        return result;
    }

    /**
     * 预处理前台前台传输的tableObject
     *
     * @param mainid       关联id
     * @param type         类型
     * @param saveFlag     是否已保存过（true：已保存，flase：未保存）
     * @param batchid      批次表id
     * @param tableObject  完整前台数据（解决特殊传参使用）
     * @param initParamArr 初始化数据所需id
     * @return
     */
    protected JSONObject preGenericFetchFun(String mainid, String type, Boolean saveFlag, String batchid, JSONObject tableObject, String[] initParamArr) {
        return tableObject;
    }

    /**
     * 获取数据核心方法
     *
     * @param result       结果列表
     * @param mainid       关联id
     * @param type         类型
     * @param saveFlag     是否已保存过（true：已保存，flase：未保存）
     * @param batchid      批次表id
     * @param tableObject  完整前台数据（解决特殊传参使用）
     * @param initParamArr 初始化数据所需id
     * @return
     */
    private List<Map> coreGenericFetchFun(List<Map> result, String mainid, String type, Boolean saveFlag, String batchid, JSONObject tableObject, String[] initParamArr) {

        //判断是否保存过
        if (saveFlag) {
            result = coreGenericFetchSaveFun(result, mainid, type, batchid, tableObject, initParamArr);
        } else {
            result = coreGenericFetchNotSaveFun(result, mainid, type, batchid, tableObject, initParamArr);
        }
        return result;
    }

    /**
     * 处理获取到的结果列表
     *
     * @param result       结果列表
     * @param mainid       关联id
     * @param type         类型
     * @param saveFlag     是否已保存过（true：已保存，flase：未保存）
     * @param batchid      批次表id
     * @param tableObject  完整前台数据（解决特殊传参使用）
     * @param initParamArr 初始化数据所需id
     * @return
     */
    protected List<Map> afterGenericFetchFun(List<Map> result, String mainid, String type, Boolean saveFlag, String batchid, JSONObject tableObject, String[] initParamArr) {
        return result;
    }


    /**
     * 保存后核心初始化
     *
     * @param result       结果列表
     * @param mainid       关联id
     * @param type         类型
     * @param batchid      批次表id
     * @param tableObject  完整前台数据（解决特殊传参使用）
     * @param initParamArr 初始化数据所需id
     * @return
     */
    protected List<Map> coreGenericFetchSaveFun(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {

        if (t.isAnnotationPresent(MainTable.class)) {
            //主表初始化方式
            result = coreGenericFetchSaveMaintableFun(result, mainid, type, batchid, tableObject, initParamArr);
        } else if (t.isAnnotationPresent(ClobTable.class)) {
            // 大字段初始化方式
            result = coreGenericFetchSaveClobFun(result, mainid, type, batchid, tableObject, initParamArr);
        } else {
            // 普通业务表初始化方式
            result = coreGenericFetchSaveCommonFun(result, mainid, type, batchid, tableObject, initParamArr);
        }
        return result;
    }

    /**
     * 未保存核心初始化
     *
     * @param result       结果列表
     * @param mainid       关联id
     * @param type         类型
     * @param batchid      批次表id
     * @param tableObject  完整前台数据（解决特殊传参使用）
     * @param initParamArr 初始化数据所需id
     * @return
     */
    protected List<Map> coreGenericFetchNotSaveFun(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {

        JSONArray params = JSONArray.fromObject(tableObject.get("hisdatasource"));
        if (Util.isEoN(tableObject.get("isloadhis")) || "normal".equals(tableObject.get("isloadhis"))) {
            // 默认初始化方式
            result = coreGenericFetchNotSaveNormalFun(result, mainid, type, batchid, tableObject, initParamArr);
        } else if (params.size() > 0 && "original".equals(tableObject.get("isloadhis"))) {
            // 某张表获取，从人员表获取数据
            result = coreGenericFetchNotSaveOriginalFun(result, mainid, type, batchid, tableObject, initParamArr);
        } else if (params.size() > 0 && "history".equals(tableObject.get("isloadhis"))) {
            // 历史获取，有 平台年报 从 平台年报 获取，没有从 平台备案 获取（需要子类定制化实现）
            result = coreGenericFetchNotSaveHistoryFun(result, mainid, type, batchid, tableObject, initParamArr);
        }
        // 去除初始化获取到的无用字段（流程状态，流程id，保存时间等）
        result = removeInitNotUseKey(result, mainid, type, batchid, tableObject, initParamArr);
        return result;
    }


    /**
     * 通用主表初始化（已保存）
     *
     * @param result
     * @param mainid
     * @param type
     * @param batchid
     * @param tableObject
     * @param initParamArr
     * @return
     */
    protected List<Map> coreGenericFetchSaveMaintableFun(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {
        // 主表初始化方式
        List<T> list = new ArrayList<>();
        T thisObj = this.findById(mainid);
        if (!Util.isEoN(thisObj)) {
            list.add(thisObj);
        }
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig = JsonConfigUtil.getJsonConfig();
        result = new ArrayList(JSONArray.fromObject(list, jsonConfig));
        return result;
    }

    /**
     * 通用大字段初始化（已保存）
     *
     * @param mainid
     * @param type
     * @param batchid
     * @param tableObject
     * @param initParamArr
     * @return
     */
    protected List<Map> coreGenericFetchSaveClobFun(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {
        // 大字段初始化方式
        JSONObject data = new JSONObject();
        List<T> tempList = this.findByMainidAndTypeForClob(mainid, type);
        for (int i = 0; i < tempList.size(); i++) {
            try {
                Field columnnameField = tempList.get(i).getClass().getDeclaredField("columnname");
                columnnameField.setAccessible(true);
                String columnname = Util.isEoN(columnnameField.get(tempList.get(i))) == true ? "" : columnnameField.get(tempList.get(i)).toString();
                Field sourceField = tempList.get(i).getClass().getDeclaredField("source");
                sourceField.setAccessible(true);
                String source = Util.isEoN(sourceField.get(tempList.get(i))) == true ? "" : sourceField.get(tempList.get(i)).toString();
                data.put(columnname, source);
            } catch (Exception e) {
                continue;
            }

        }
        result.add(data);
        return result;
    }

    /**
     * 通用数组表初始化（已保存）
     *
     * @param mainid
     * @param type
     * @param batchid
     * @param tableObject
     * @param initParamArr
     * @return
     */
    protected List<Map> coreGenericFetchSaveCommonFun(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {
        //正常初始化
        List<T> list = new ArrayList<>();
        list = this.findByMainidAndType(mainid, type);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig = JsonConfigUtil.getJsonConfig();
        result = new ArrayList(JSONArray.fromObject(list, jsonConfig));
        return result;
    }


    /**
     * 未保存初始化（默认模式）
     *
     * @param result       结果列表
     * @param mainid       关联id
     * @param type         类型
     * @param batchid      批次表id
     * @param tableObject  完整前台数据（解决特殊传参使用）
     * @param initParamArr 初始化数据所需id
     * @return
     */
    protected List<Map> coreGenericFetchNotSaveNormalFun(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {
        if (t.isAnnotationPresent(MainTable.class)) {
            // 主表初始化方式
            result = coreGenericFetchNotSaveNormalMaintableFun(result, mainid, type, batchid, tableObject, initParamArr);
        } else if (t.isAnnotationPresent(ClobTable.class)) {
            // 大字段初始化方式
            result = coreGenericFetchNotSaveNormalClobFun(result, mainid, type, batchid, tableObject, initParamArr);
        } else {
            // 通用数据统计表
            result = coreGenericFetchNotSaveNormalCommonFun(result, mainid, type, batchid, tableObject, initParamArr);
        }
        return result;
    }

    /**
     * 未保存初始化（指定某张表模式）
     *
     * @param mainid
     * @param type
     * @param batchid
     * @param tableObject
     * @param initParamArr
     * @return
     */
    protected List<Map> coreGenericFetchNotSaveOriginalFun(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {

        Class clazz = null;
        String newtype = "";
        String temSourceid = "";
        // 针对平台年报来说，每次初始化都读取备案的信息
        JSONArray params = JSONArray.fromObject(tableObject.get("hisdatasource"));
        temSourceid = initParamArr[0];
        try {
            clazz = (Class) Util.springInvokeMethod("BizUtil", "getClassByTableObject", new Object[]{params.getJSONObject(0).get("database").toString()});
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Util.isEoN(clazz)) {
            return result;
        }

        //service Bean对象的对象名称
        String serviceName = "";
        //获取中定义的spring的service注解的名称
        serviceName = ((Service) (clazz.getAnnotation(Service.class))).value();
        //判断是否已为service名称是否存在
        if (Util.isEoN(serviceName)) {
            return result;
        }
        //保存类型
        newtype = params.getJSONObject(0).get("paramtype").toString();
        try {
            //备案数据
            String[] tempInitParamArr = new String[0];
            result = (List<Map>) Util.springInvokeMethod(serviceName, "genericFetchFun", new Object[]{temSourceid, newtype, true, batchid, tableObject, tempInitParamArr});
        } catch (Exception e) {
            e.printStackTrace();
            result = new ArrayList<>();
        }

        return result;
    }


    /**
     * 未保存初始化（历史数据模式）
     *
     * @param result       结果列表
     * @param mainid       关联id
     * @param type         类型
     * @param batchid      批次表id
     * @param tableObject  完整前台数据（解决特殊传参使用）
     * @param initParamArr 初始化数据所需id
     * @return
     */
    protected List<Map> coreGenericFetchNotSaveHistoryFun(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {
        return result;
    }

    /**
     * 通用主表默认初始化（未保存）
     *
     * @param result
     * @param mainid
     * @param type
     * @param batchid
     * @param tableObject
     * @param initParamArr
     * @return
     */
    protected List<Map> coreGenericFetchNotSaveNormalMaintableFun(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {
        return result;
    }

    /**
     * 通用大字段默认初始化（未保存）
     *
     * @param mainid
     * @param type
     * @param batchid
     * @param tableObject
     * @param initParamArr
     * @return
     */
    protected List<Map> coreGenericFetchNotSaveNormalClobFun(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {
        return result;
    }

    /**
     * 通用数组默认初始化（未保存）
     *
     * @param mainid
     * @param type
     * @param batchid
     * @param tableObject
     * @param initParamArr
     * @return
     */
    protected List<Map> coreGenericFetchNotSaveNormalCommonFun(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {

        if (t.isAnnotationPresent(PersonTable.class)) {
            result = coreGenericFetchNotSaveNormalCommonPersonFun(result, mainid, type, batchid, tableObject, initParamArr);
        } else if (t.isAnnotationPresent(EnterpriseTable.class)) {
            result = coreGenericFetchNotSaveNormalCommonEnterpriseFun(result, mainid, type, batchid, tableObject, initParamArr);
        } else if (t.isAnnotationPresent(ChangeTable.class)) {
            result = coreGenericFetchNotSaveNormalCommonChangeFun(result, mainid, type, batchid, tableObject, initParamArr);
        } else {
            result = new ArrayList<>();
        }
        return result;
    }

    /**
     * 通用数组人员初始化（未保存）
     *
     * @param result
     * @param mainid
     * @param type
     * @param batchid
     * @param tableObject
     * @param initParamArr
     * @return
     */
    protected List<Map> coreGenericFetchNotSaveNormalCommonPersonFun(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {

        String datatype = tableObject.getString("datatype");
        if ("normal".equals(datatype) && ("true".equals(type) || "项目负责人".equals(type))) {
            //如果是对象并且是项目负责人并且没保存
            try {
                JSONObject obj = (JSONObject) Util.springInvokeMethod("GenericInitData", "getUserInfoForSb", new Object[]{});
                obj.put("id", Util.NewGuid());
                result.add(obj);
            } catch (Exception e) {
                result = new ArrayList<>();
                e.printStackTrace();
            }
        } else if ("normal".equals(datatype) && "fund".equals(type)) {
            //如果是对象并且是财务负责人并且没保存
            try {
                JSONObject obj = (JSONObject) Util.springInvokeMethod("GenericInitData", "getUserEnterCwForSb", new Object[]{});
                obj.put("id", Util.NewGuid());
                result.add(obj);
            } catch (Exception e) {
                result = new ArrayList<>();
                e.printStackTrace();
            }
        } else {
            result = new ArrayList<>();
        }
        return result;
    }

    /**
     * 通用数组单位初始化（未保存）
     *
     * @param result
     * @param mainid
     * @param type
     * @param batchid
     * @param tableObject
     * @param initParamArr
     * @return
     */
    protected List<Map> coreGenericFetchNotSaveNormalCommonEnterpriseFun(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {
        String datatype = tableObject.getString("datatype");
        //如果是对象并且是承担单位并且没保存
        if ("normal".equals(datatype) && ("true".equals(type) || "sub".equals(type))) {
            try {
                JSONObject obj = (JSONObject) Util.springInvokeMethod("GenericInitData", "getUserEnterForSb", new Object[]{});
                if ("true".equals(type)) {
                    // 处理高校或普通企业
                    obj.put("id", Util.NewGuid());
                    result.add(obj);
                } else if ("sub".equals(type)) {
                    // 处理二级学院
                    JSONObject collegeObj = obj.getJSONObject("collegeObj");
                    collegeObj.put("id", Util.NewGuid());
                    result.add(collegeObj);
                }
            } catch (Exception e) {
                result = new ArrayList<>();
                e.printStackTrace();
            }
        } else {
            result = new ArrayList<>();
        }
        return result;
    }

    /**
     * 通用数组变更记录初始化（未保存）
     *
     * @param result
     * @param mainid
     * @param type
     * @param batchid
     * @param tableObject
     * @param initParamArr
     * @return
     */
    protected List<Map> coreGenericFetchNotSaveNormalCommonChangeFun(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {
        String datatype = tableObject.getString("datatype");
        //如果是对象并且是承担单位并且没保存
        if ("normal".equals(datatype) && "true".equals(type)) {
            try {
                JSONObject obj = new JSONObject();
                JSONArray jsonArray = (JSONArray) Util.springInvokeMethod("GenericInitData", "getChangeData", new Object[]{});
                obj.put("id", Util.NewGuid());
                obj.put("changecontent", jsonArray.toString());
                result.add(obj);
            } catch (Exception e) {
                result = new ArrayList<>();
                e.printStackTrace();
            }
        } else {
            result = new ArrayList<>();
        }
        return result;
    }


    /**
     * 去除无用的字段
     *
     * @param result
     * @return
     */
    protected List<Map> removeInitNotUseKey(List<Map> result, String mainid, String type, String batchid, JSONObject tableObject, String[] initParamArr) {
        for (int i = 0; i < result.size(); i++) {
            result.get(i).remove("minicurrentstate");
            result.get(i).remove("minicurrentstateid");
            result.get(i).remove("submitdate");
            result.get(i).remove("updatelasttime");
            result.get(i).remove("savedate");
            result.get(i).remove("isAddwatermark");
            result.get(i).remove("isaddwatermark");
            result.get(i).remove("downloaddate");
        }
        return result;
    }


    /**
     * 通过主表id和type获取泛型List（针对大字段）
     *
     * @param mainid 主表id
     * @param type   类型
     * @return
     */
    public List<T> findByMainidAndTypeForClob(String mainid, String type) {

        // HQL
        String hql = " select t from " + t.getSimpleName() + " t where t.mainid = ?0 and t.type = ?1 ";

        return this.findByHql(hql, new Object[]{mainid, type});
    }


    /**
     * 通过主表id和type获取泛型List
     *
     * @param mainid 主表id
     * @param type   类型
     * @return
     */
    public List<T> findByMainidAndType(String mainid, String type) {

        // HQL
        String hql = " select t from " + t.getSimpleName() + " t where t.mainid = ?0 and t.type = ?1 order by t.seq ";

        return this.findByHql(hql, mainid, type);
    }


    /******************************************************* 申报通用方法---数据读取---结束 *************************************/


    /******************************************************* 申报通用方法---数据保存---开始 *************************************/

    /**
     * 通用数据保存接口
     *
     * @param mainid      关联id
     * @param type        类型
     * @param data        数据
     * @param batchid     批次id
     * @param flowtype    流程类型
     * @param mold        流程模式
     * @param tableObject 完整前台数据（解决特殊传参使用）
     * @return
     */
    public JSONObject genericSaveFun(String mainid, String type, JSONArray data, String batchid, String flowtype, String mold, JSONObject tableObject) {

        JSONObject result = new JSONObject();
        PreSaveResult preSaveResult = preGenericSaveFun(mainid, type, data, batchid, flowtype, mold, tableObject);
        data = preSaveResult.getData();
        tableObject = preSaveResult.getTableObject();
        result = coreGenericSaveFun(result, mainid, type, data, batchid, flowtype, mold, tableObject);
        result = afterGenericSaveFun(result, mainid, type, data, batchid, flowtype, mold, tableObject);
        return result;
    }

    /**
     * 预处理前台前台传输的tableObject
     *
     * @param mainid      关联id
     * @param type        类型
     * @param data        数据
     * @param batchid     批次id
     * @param flowtype    流程类型
     * @param mold        流程模式
     * @param tableObject 完整前台数据（解决特殊传参使用）
     * @return
     */
    protected PreSaveResult preGenericSaveFun(String mainid, String type, JSONArray data, String batchid, String flowtype, String mold, JSONObject tableObject) {
        PreSaveResult preSaveResult = new PreSaveResult(data, tableObject);
        return preSaveResult;
    }

    /**
     * 核心保存方法
     *
     * @param result      结果
     * @param mainid      关联id
     * @param type        类型
     * @param data        数据
     * @param batchid     批次id
     * @param flowtype    流程类型
     * @param mold        流程模式
     * @param tableObject 完整前台数据（解决特殊传参使用）
     * @return
     */
    private JSONObject coreGenericSaveFun(JSONObject result, String mainid, String type, JSONArray data, String batchid, String flowtype, String mold, JSONObject tableObject) {
        //正常保存
        if (t.isAnnotationPresent(MainTable.class)) {
            // 主表保存方法
            result = coreGenericMainTableSaveFun(mainid, type, data, batchid, flowtype, mold, tableObject);
        } else if (t.isAnnotationPresent(ClobTable.class)) {
            // 大字段保存方式
            result = coreGenericClobSaveFun(mainid, type, data, batchid, flowtype, mold, tableObject);
        } else {
            // 普通业务表保存方法
            result = coreGenericCommonSaveFun(mainid, type, data, batchid, flowtype, mold, tableObject);
        }
        return result;
    }

    /**
     * 处理结果
     *
     * @param result      结果
     * @param mainid      关联id
     * @param type        类型
     * @param data        数据
     * @param batchid     批次id
     * @param flowtype    流程类型
     * @param mold        流程模式
     * @param tableObject 完整前台数据（解决特殊传参使用）
     * @return
     */
    protected JSONObject afterGenericSaveFun(JSONObject result, String mainid, String type, JSONArray data, String batchid, String flowtype, String mold, JSONObject tableObject) {
        return result;
    }


    /**
     * 业务主表保存
     *
     * @param mainid      关联id
     * @param type        类型
     * @param data        数据
     * @param batchid     批次id
     * @param flowtype    流程类型
     * @param mold        流程模式
     * @param tableObject 完整前台数据（解决特殊传参使用）
     * @return
     */
    private JSONObject coreGenericMainTableSaveFun(String mainid, String type, JSONArray data, String batchid, String flowtype, String mold, JSONObject tableObject) {

        JSONObject result = new JSONObject();
        try {
            if (data.size() != 1) {
                result.put("success", false);
                return result;
            }
            //临时储存前台需需要保存数据的id
            PmsEnterprise pmsEnterprise = new PmsEnterprise();
            //如果前台有传单位id就根据这个id来，不然就根据当前用户
            if (Util.isEoN(data.getJSONObject(0).get("choiceeid"))) {
                String findEidSql = "select e.purvieworganizeid from sys_identity e where e.userid = ? ";
                String eid = dbHelper.getOnlyStringValue(findEidSql, new Object[]{AuthUtil.getUserId()});
                pmsEnterprise = getObjectById(eid, PmsEnterprise.class);
            } else {
                pmsEnterprise = getObjectById(data.getJSONObject(0).get("choiceeid").toString(), PmsEnterprise.class);
            }
            PmsPlanprojectbatch planprojectbatch = getObjectById(batchid, PmsPlanprojectbatch.class);
            PmsPlanproject planproject = planprojectbatch.getPmsPlanproject();
            // 操作记录
            SysOperationrecord sysOpera = new SysOperationrecord();
            sysOpera.setNote("保存项目，该项目ID为--" + mainid);
            sysOpera.setType("保存");
            //通过反射执行保存操作记录
            Util.springInvokeMethod("SysOperationrecordService", "saveCurrentUserOperation", new Object[]{sysOpera});
            //获取处室id
            String belonglabid = planprojectbatch.getDepartmentid();
            PmsDepartment pmsDepartment = null;
            if (!Util.isEoN(belonglabid)) {
                pmsDepartment = getObjectById(belonglabid, PmsDepartment.class);
            }
            T myInstance = this.findById(mainid);
            if (Util.isEoN(myInstance)) {
                myInstance = t.newInstance();
                //设置业务主表id字段
                fieldSetValue(mainid, "id", myInstance);
                fieldSetValue(new Date(), "savedate", myInstance);
            }
            if (Util.isEoN(myInstance)) {
                //创建对象失败
                result.put("success", false);
                return result;
            }
            //解决注册类型的问题，如果没有当前登录用户，那么使用前台传的用户id
            if (Util.isEoN(AuthUtil.getUserId())) {
                fieldSetValue(data.getJSONObject(0).get("declarantid").toString(), "declarantid", myInstance);
            } else {
                fieldSetValue(AuthUtil.getUserId(), "declarantid", myInstance);
            }
            //变更上报以后 同时又要保存 屏蔽下面的字段
            data.getJSONObject(0).remove("minicurrentstate");
            data.getJSONObject(0).remove("minicurrentstateid");
            data.getJSONObject(0).remove("changecitycasemanagement");
            data.getJSONObject(0).remove("changecitycasemanagementid");
            data.getJSONObject(0).remove("changeunitname");
            data.getJSONObject(0).remove("changeunitid");


            Util.ApplyObject(myInstance, data.getJSONObject(0));
            fieldSetValue(planprojectbatch.getId(), "planprojectbatchid", myInstance);
            //fieldSetValue(tableObject.getString("planprojectType"), "planprojecttype", myInstance);
            fieldSetValue(0, "isDeleted", myInstance);

            fieldSetValue(planprojectbatch.getId(), "planprojectbatchid", myInstance);
            //fieldSetValue(Util.isEoN(pmsDepartment) ? null : pmsDepartment.getId(), "belonglabid", myInstance);
            //fieldSetValue(Util.isEoN(pmsDepartment) ? null : pmsDepartment.getName(), "belonglab", myInstance);
            fieldSetValue(planproject.getId(), "planprojectid", myInstance);
            String enterpriseid = Util.fetchFieldName(t, "enterpriseid");
            fieldSetValue(pmsEnterprise == null ? null : pmsEnterprise.getId(), enterpriseid, myInstance);
            // fieldSetValue(new Date(), "updatelasttime", myInstance);

//            Field minicurrentstateField = myInstance.getClass().getDeclaredField("minicurrentstate");
//            minicurrentstateField.setAccessible(true);
//            String minicurrentstate = Util.isEoN(minicurrentstateField.get(myInstance)) ? "" : minicurrentstateField.get(myInstance).toString();
//            Field minicurrentstateidField = myInstance.getClass().getDeclaredField("minicurrentstateid");
//            minicurrentstateidField.setAccessible(true);
//            String minicurrentstateid = Util.isEoN(minicurrentstateidField.get(myInstance)) ? "" : minicurrentstateidField.get(myInstance).toString();
//            if (Util.isEoN(minicurrentstate) || Util.isEoN(minicurrentstateid)) {
//                //获取流程结点信息
//                FlowPoint flow = (FlowPoint) Util.springInvokeMethod("flowPointService", "getFirstFlowPointByBatchidAndTypeAndMold", new Object[]{batchid, flowtype, mold});
//                if (!Util.isEoN(flow)) {
//                    fieldSetValue(flow.getId(), "minicurrentstateid", myInstance);
//                    fieldSetValue(flow.getName(), "minicurrentstate", myInstance);
//                }
//            }
            this.merge(myInstance);
            //将保保存记录添加到txt文件当中
            JSONObject saveData = new JSONObject();
            saveData.put("data", data);
            Util.springInvokeMethod("pmsTxtSave", "saveTxt", new Object[]{mainid, saveData, t.getSimpleName() + "Service", "coreGenericMainTableSaveFun"});

            //创建返回对象
            result.put("success", true);
            Field idField = myInstance.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            result.put("data", idField.get(myInstance).toString());

        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            System.out.println("/" + t.getSimpleName() + "Service/coreGenericMainTableSaveFun 方法报错。");
            log.error("/" + t.getSimpleName() + "Service/coreGenericMainTableSaveFun 方法报错,错误信息：" + e);
        }

        return result;
    }

    /**
     * 大字段表保存
     *
     * @param mainid      关联id
     * @param type        类型
     * @param data        数据
     * @param batchid     批次id
     * @param flowtype    流程类型
     * @param mold        流程模式
     * @param tableObject 完整前台数据（解决特殊传参使用）
     * @return
     */
    private JSONObject coreGenericClobSaveFun(String mainid, String type, JSONArray data, String batchid, String flowtype, String mold, JSONObject tableObject) {
        JSONObject result = new JSONObject();
        //处理大字段原始数据，将前台对象的属性拆分为多个对象，例如：{xxx:"XXX", yyy:"YYY"} => [{xxx:"XXX"},{yyy:"YYY"}]
        JSONArray newData = dealOriginalColbData(data, type, mainid);
        try {
            //获取数据库中已有的信息
            List<T> list = this.findByMainidAndTypeForClob(mainid, type);
            //原始数组的ids字符串，例如：id1,id2,id3...
            String originalListIds = fetchListIds(list);
            //临时储存前台需需要保存数据的id
            StringBuilder currentIds = new StringBuilder("");
            //校验数据是否需要保存
            if (!Util.isEoN(type) && !Util.isEoN(newData) && newData.size() > 0) {
                T myInstance = null;
                for (int i = 0; i < newData.size(); i++) {
                    //获取数据库中存储的数据
                    List<T> temp = this.findByMainidTypeAndColumname(mainid, newData.getJSONObject(i).get("columnname").toString(), type);
                    if (!Util.isEoN(temp) && temp.size() > 0) {
                        myInstance = temp.get(0);
                    } else {
                        myInstance = t.newInstance();
                    }
                    if (Util.isEoN(myInstance)) {
                        //创建对象失败
                        continue;
                    }
                    Field sourceField = t.getDeclaredField("source");
                    sourceField.setAccessible(true);
                    Object originalObjFieldValue = sourceField.get(myInstance);
                    Util.ApplyObject(myInstance, newData.getJSONObject(i));
                    //获取id字段
                    Field idField = t.getDeclaredField("id");
                    //设置对象的访问权限，保证对private的属性的访问
                    idField.setAccessible(true);
                    //判断对象中是否包含主键id
                    if (Util.isEoN(idField.get(myInstance))) {
                        idField.set(myInstance, Util.NewGuid());
                    }
                    //将id添加的需保存id字符串中
                    currentIds.append(idField.get(myInstance) + ";");
                    //设置业务主表id字段
                    fieldSetValue(mainid, "mainid", myInstance);
                    //设置类型字段
                    fieldSetValue(type, "type", myInstance);
                    Boolean addFlag = compareObjectIsAdd(originalListIds, myInstance);
                    Boolean changeFlag;
                    if (addFlag) {
                        //设置保存字段
                        fieldSetValue(new Date(), "savedate", myInstance);
                        //设置最后更新时间字段
                        fieldSetValue(new Date(), "updatelasttime", myInstance);
                    } else {
                        Object currentObjFieldValue = sourceField.get(myInstance);
                        changeFlag = compareObjectHasChangeForClob(originalObjFieldValue, currentObjFieldValue);
                        if (changeFlag) {
                            //设置最后更新时间字段
                            fieldSetValue(new Date(), "updatelasttime", myInstance);
                        }
                    }
                    //保存数据
                    this.merge(myInstance);
                }
            }
            //处理数据库中需删除的数据
            for (T value : list) {
                Field idField = value.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                if (currentIds.indexOf(idField.get(value).toString()) > -1) {
                    //被修改的数据
                } else {
                    //已删除的数据
                    this.deleteById(idField.get(value).toString());
                }
            }
            //将保保存记录添加到txt文件当中
            JSONObject saveData = new JSONObject();
            saveData.put("data", data);
            Util.springInvokeMethod("pmsTxtSave", "saveTxt", new Object[]{mainid, saveData, t.getSimpleName() + "Service", "coreGenericClobSaveFun"});

            //创建返回对象
            result.put("success", true);
            result.put("data", currentIds.toString());
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            System.out.println("/" + t.getSimpleName() + "Service/coreGenericClobSaveFun 方法报错。");
            log.error("/" + t.getSimpleName() + "Service/coreGenericClobSaveFun 方法报错,错误信息：" + e);
        } finally {
            return result;
        }
    }


    /**
     * 普通业务表保存
     *
     * @param mainid      关联id
     * @param type        类型
     * @param data        数据
     * @param batchid     批次id
     * @param flowtype    流程类型
     * @param mold        流程模式
     * @param tableObject 完整前台数据（解决特殊传参使用）
     * @return
     */
    private JSONObject coreGenericCommonSaveFun(String mainid, String type, JSONArray data, String batchid, String flowtype, String mold, JSONObject tableObject) {
        JSONObject result = new JSONObject();
        try {
            //获取数据库中已有的信息
            List<T> list = this.findByMainidAndType(mainid, type);
            //原始数组的ids字符串，例如：id1,id2,id3...
            String originalListIds = fetchListIds(list);
            //临时储存前台需需要保存数据的id
            StringBuilder currentIds = new StringBuilder("");
            //校验数据是否需要保存
            if (!Util.isEoN(type) && !Util.isEoN(data) && data.size() > 0) {
                //前台传的需保存的对象
                T myInstance = null;
                //数据库中获取的对象
                T originalInstance = null;
                for (int i = 0; i < data.size(); i++) {
                    myInstance = t.newInstance();
                    if (Util.isEoN(myInstance)) {
                        //创建对象失败
                        continue;
                    }
                    //获取id字段
                    String id = data.getJSONObject(i).get("id") + "";
                    //判断前台传输的数据是否包含主键id
                    if (Util.isEoN(id)) {
                        id = Util.NewGuid();
                        data.getJSONObject(i).put("id", id);
                    }
                    Util.ApplyObject(myInstance, data.getJSONObject(i));
                    //将id添加的需保存id字符串中
                    currentIds.append(id + ";");
                    //设置业务主表id字段
                    fieldSetValue(mainid, "mainid", myInstance);
                    //设置类型字段
                    fieldSetValue(type, "type", myInstance);
                    //设置顺序字段
                    fieldSetValue(i, "seq", myInstance);
                    //处理学科表中的subjecttype字段
                    if (t.isAnnotationPresent(SubjectTable.class)) {
                        myInstance = dealSubjecttpe(myInstance);
                    }
                    Boolean addFlag = compareObjectIsAdd(originalListIds, myInstance);
                    Boolean changeFlag;
                    if (addFlag) {
                        //设置保存字段
                        fieldSetValue(new Date(), "savedate", myInstance);
                        //设置最后更新时间字段
                        fieldSetValue(new Date(), "updatelasttime", myInstance);
                    } else {
                        originalInstance = this.findById(id);
                        try {
                            Field savedateField = t.getDeclaredField("savedate");
                            Field updatelasttimeField = t.getDeclaredField("updatelasttime");
                            savedateField.setAccessible(true);
                            updatelasttimeField.setAccessible(true);
                            fieldSetValue(savedateField.get(originalInstance), "savedate", myInstance);
                            fieldSetValue(updatelasttimeField.get(originalInstance), "updatelasttime", myInstance);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("/" + t.getSimpleName() + "Service/coreGenericCommonSaveFun 方法报错。");
                            log.error("/" + t.getSimpleName() + "Service/coreGenericCommonSaveFun 方法报错,错误信息：" + e);
                        }
                        changeFlag = compareObjectHasChange(list, myInstance);
                        if (changeFlag) {
                            //设置最后更新时间字段
                            fieldSetValue(new Date(), "updatelasttime", myInstance);
                        }
                    }
                    //保存数据
                    this.merge(myInstance);
                }
            }
            //处理数据库中需删除的数据
            for (int i = 0; i < list.size(); i++) {
                Field idField = list.get(i).getClass().getDeclaredField("id");
                idField.setAccessible(true);
                if (currentIds.indexOf(idField.get(list.get(i)).toString()) > -1) {
                    //被修改的数据
                } else {
                    //已删除的数据
                    this.deleteById(idField.get(list.get(i)).toString());
                }
            }
            //将保保存记录添加到txt文件当中
            JSONObject saveData = new JSONObject();
            saveData.put("data", data);
            Util.springInvokeMethod("pmsTxtSave", "saveTxt", new Object[]{mainid, saveData, t.getSimpleName() + "Service", "coreGenericCommonSaveFun"});
            //创建返回对象
            result.put("success", true);
            result.put("data", currentIds.toString());
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            System.out.println("/" + t.getSimpleName() + "Service/coreGenericCommonSaveFun 方法报错。");
            log.error("/" + t.getSimpleName() + "Service/coreGenericCommonSaveFun 方法报错,错误信息：" + e);
        } finally {
            return result;
        }
    }


    /**
     * 获取泛型数租对象的Ids，通过","逗号进行分割
     *
     * @param originalList
     * @param <T>
     * @return
     */
    private <T> String fetchListIds(List<T> originalList) {
        StringBuilder stringBuilder = new StringBuilder("");
        Field idField = null;
        try {
            idField = t.getDeclaredField("id");
            idField.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (idField == null) {
            return "";
        }
        for (int i = 0; i < originalList.size(); i++) {
            try {
                if (i != 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(idField.get(originalList.get(i)).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }


    /**
     * 处理大字段原始数据，将前台对象的属性拆分为多个对象，例如：{xxx:"XXX", yyy:"YYY"} => [{xxx:"XXX"},{yyy:"YYY"}]
     *
     * @param originalData 原始数据
     * @param type         类型
     * @param mainid       主表id
     * @return
     */
    private static JSONArray dealOriginalColbData(JSONArray originalData, String type, String mainid) {
        JSONArray newData = new JSONArray();
        JSONObject newObject = null;
        for (int i = 0; i < originalData.size(); i++) {
            Map<String, Object> map = originalData.getJSONObject(i);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                newObject = new JSONObject();
                newObject.put("columnname", entry.getKey());
                newObject.put("source", entry.getValue().toString());
                newObject.put("type", type);
                newObject.put("mainid", mainid);
                newData.add(newObject);
            }
        }
        return newData;
    }


    /**
     * 按业务主表id、属性名称及类型获取大字段数据（针对大字段其他类勿用）
     *
     * @param mainid
     * @param columnname
     * @param type
     * @return
     */
    public List<T> findByMainidTypeAndColumname(String mainid, String columnname, String type) {
        List<T> list = new ArrayList<>();
        String hql = "select t from " + t.getSimpleName() + " t where t.mainid = ?0 and t.type = ?1 and t.columnname = ?2 ";
        list = this.findByHql(hql, new Object[]{mainid, type, columnname});
        return list;
    }


    /**
     * 设置属性字段的值
     *
     * @param value      属性的值
     * @param fieldName  属性名
     * @param myInstance 实例
     * @return
     */
    private Boolean fieldSetValue(Object value, String fieldName, T myInstance) {
        try {
            //获取字段
            Field mainidField = t.getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            mainidField.setAccessible(true);
            //设置属性的值
            mainidField.set(myInstance, value);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取属性字段的值
     *
     * @param fieldName  属性名
     * @param myInstance 实例
     * @return Object
     */
    private Object fieldGetValue(String fieldName, T myInstance) {
        try {
            //获取字段
            Field mainidField = t.getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            mainidField.setAccessible(true);
            //设置属性的值
            return mainidField.get(myInstance);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 判断对象值是否发生修改
     *
     * @param originalList
     * @param currentObj
     * @param <T>
     * @return 发生修改：true，否则： false
     */
    private <T> Boolean compareObjectHasChange(List<T> originalList, T currentObj) {
        Boolean result = false;
        try {
            Field idField = t.getDeclaredField("id");
            idField.setAccessible(true);
            String currentId = idField.get(currentObj).toString();
            for (int i = 0; i < originalList.size(); i++) {
                String originalObjId = idField.get(originalList.get(i)).toString();
                if (originalObjId.equals(currentId)) {
                    Field[] declaredFields = t.getDeclaredFields();
                    for (Field field : declaredFields) {
                        if ("updatelasttime".equals(field.getName()) || "savedate".equals(field.getName())) {
                            continue;
                        }
                        field.setAccessible(true);
                        Object originalObjFieldValue = field.get(originalList.get(i));
                        Object currentObjFieldValue = field.get(currentObj);
                        if (originalObjFieldValue instanceof Timestamp) {
                            originalObjFieldValue = new Date(((Timestamp) field.get(originalList.get(i))).getTime());
                        }
                        if (!Util.isEoN(originalObjFieldValue) && !originalObjFieldValue.equals(currentObjFieldValue)) {
                            return true;
                        }
                        if (Util.isEoN(originalObjFieldValue) && !Util.isEoN(currentObjFieldValue)) {
                            return true;
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断大字段的值是否发生修改
     *
     * @param originalObjFieldValue 原始值
     * @param currentObjFieldValue  新值
     * @param <T>
     * @return 发生修改：true，否则： false
     */
    private <T> Boolean compareObjectHasChangeForClob(Object originalObjFieldValue, Object currentObjFieldValue) {
        Boolean result = false;
        if (!Util.isEoN(originalObjFieldValue) && !originalObjFieldValue.equals(currentObjFieldValue)) {
            return true;
        }
        if (Util.isEoN(originalObjFieldValue) && !Util.isEoN(currentObjFieldValue)) {
            return true;
        }
        return false;
    }


    /**
     * 判断对象值是否为新增
     *
     * @param originalListIds
     * @param currentObj
     * @param <T>
     * @return 发生修改、新增：true，否则： false
     */
    private <T> Boolean compareObjectIsAdd(String originalListIds, T currentObj) {
        Boolean result = false;
        try {
            Field idField = t.getDeclaredField("id");
            idField.setAccessible(true);
            String currentId = idField.get(currentObj).toString();
            String tempCurrentId = "," + currentId + ",";
            String tempOriginalListIds = "," + originalListIds + ",";
            if (!tempOriginalListIds.contains(tempCurrentId)) {
                result = true;
            }
        } catch (Exception e) {
            result = true;
            log.error("originalListIds：" + originalListIds, e);
        } finally {
            return result;
        }
    }


    /**
     * 获取id为给定值的该泛型类的对象
     *
     * @param id     主键
     * @param kClass 泛型类
     * @param <K>    泛型
     * @return
     */
    private <K> K getObjectById(String id, Class<K> kClass) {
        String hql = "select t from " + kClass.getSimpleName() + " t where t.id = ?0 ";
        List<K> list = this.findByHql(hql, id);
        K myInstance = null;
        try {
            myInstance = list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            myInstance = null;
        } finally {
            return myInstance;
        }
    }


    /**
     * 处理学科表中的subjecttype字段
     *
     * @param myInstance
     * @return
     */
    private T dealSubjecttpe(T myInstance) {

        try {
            Field subtypeField = t.getDeclaredField("subtype");
            Field subjecttypeField = t.getDeclaredField("subjecttype");
            subtypeField.setAccessible(true);
            subjecttypeField.setAccessible(true);
            String subtype = Util.isEoN(subtypeField.get(myInstance)) == true ? "" : subtypeField.get(myInstance) + "";
            String subjecttype = "";
            switch (subtype) {
                case "xm":
                case "gbxk": {
                    subjecttype = "gbxk";
                    break;
                }
                case "zj":
                case "jjxk": {
                    subjecttype = "jjxk";
                    break;
                }
                case "jyb":
                case "true":
                case "jybxk": {
                    subjecttype = "jybxk";
                    break;
                }
                default: {
                    subjecttype = subtype;
                }
            }
            fieldSetValue(subjecttype, "subjecttype", myInstance);
        } catch (Exception e) {

        } finally {
            return myInstance;
        }
    }


    /******************************************************* 申报通用方法---数据保存---结束 *************************************/


    /**
     * 判断当前业务是否属于等钱登录用户
     *
     * @param mainid
     * @return
     */
    public Boolean judgetBelongToCurrentUser(String mainid) {
        String userid = AuthUtil.getUserId();
        T myInstance = findById(mainid);
        if (Util.isEoN(myInstance)) {
            return false;
        }
        try {
            Field declarantidField = t.getDeclaredField("declarantid");
            declarantidField.setAccessible(true);
            String declarantid = Util.isEoN(declarantidField.get(myInstance)) ? "" : (declarantidField.get(myInstance)).toString();
            return !Util.isEoN(userid) && userid.equals(declarantid);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 按主表id删除当前业务表的所有数据
     *
     * @param mainid
     * @return
     */
    public Boolean deleteDataByMainid(String mainid) {
        boolean result;
        List<T> allList = findAllByMainidNotOrderBy(mainid);
        try {
            Field idField = t.getDeclaredField("id");
            idField.setAccessible(true);
            for (T value : allList) {
                //删除数组嵌套附件
                String id = idField.get(value) + "";
                Util.springInvokeMethod("PmsAttachmentService", "deletePmsAttachmentBySourceid", new Object[]{id});
                Util.springInvokeMethod("PmsAllattachmentsService", "deletePmsAllattachmentsBySourceid", new Object[]{id});
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("删除失败");
        }
        result = this.deleteList(allList);
        return result;
    }


    /**
     * 通过主表id获取泛型List
     *
     * @param mainid 主表id
     * @return
     */
    public List<T> findAllByMainidNotOrderBy(String mainid) {

        List<T> list = new ArrayList<>();
        String hql = " select t from " + t.getSimpleName() + " t where t.mainid = ?0 ";
        list = this.findByHql(hql, new Object[]{mainid});
        return list;
    }


    /**
     * 逻辑删除
     *
     * @param mainid 主表id
     */
    public void deleteLogicById(String mainid) {
        // 查询
        T myInstance = this.findById(mainid);
        if (myInstance == null) {
            throw new ServiceException("暂无数据");
        }

        fieldSetValue(1, "isDeleted", myInstance);
        fieldSetValue(new Date(), "savedate", myInstance);

        // 保存
        this.merge(myInstance);

    }


    /**
     * 获取申报人id
     *
     * @param mainid 主表id
     * @return 申报人id
     * @author szs
     * @date 2023-11-22
     */
    public Object getDeclarantid(String mainid) {
        // 查询
        T myInstance = this.findById(mainid);
        if (myInstance == null) {
            return null;
        }

        return fieldGetValue("declarantid", myInstance);
    }


    /**
     * 通过流转记录状态判断是否可以编辑
     *
     * @param mainid 主表id
     * @return JSONObject
     * @author szs
     * @date 2023-11-22
     */
    public JSONObject judgeMinicurrentStateSatify(String mainid) {
        JSONObject result = new JSONObject();

        // 查询
        T myInstance = this.findById(mainid);
        if (myInstance == null) {
            result.put("success", true);
            return result;
        }

        // 获取流程状态
        String minicurrentstate = (String) fieldGetValue("minicurrentstate", myInstance);

        if (StringUtils.isNotBlank(minicurrentstate) && !minicurrentstate.contains("用户填报")) {
            result.put("success", false);
            result.put("errMsg", "该项目当前的状态为：" + minicurrentstate + "，未在用户填报阶段，无法进行保存！！！");
            return result;
        }

        result.put("success", true);
        return result;
    }


    /**
     * 修改流程状态
     *
     * @param param 参数
     * @author szs
     * @date 2024-03-01
     */
    public void updateMinicurrentState(JSONObject param) {
        String mainId = param.getString("mainId");
        String minicurrentstate = param.getString("minicurrentstate");
        String minicurrentstateid = param.getString("minicurrentstateid");
        String miniCurrentTaskDefKey = param.getString("miniCurrentTaskDefKey");
        String miniCurrentProcessDefKey = param.getString("miniCurrentProcessDefKey");

        // 查询
        T myInstance = this.findById(mainId);
        if (myInstance == null) {
            return;
        }

        // 统一状态，如果状态有差异，请在自己主表service中重写该方法
        if (StringUtils.isNotBlank(minicurrentstate)) {
            switch (minicurrentstate) {
                case "完成":
                    minicurrentstate = "申报完成";
                    break;
                case "终止":
                    minicurrentstate = "申报终止";
                    break;
                case "撤销":
                    minicurrentstate = "用户填报";
                    break;
                default:
                    break;
            }
        }

        fieldSetValue(minicurrentstate, "minicurrentstate", myInstance);
        fieldSetValue(minicurrentstateid, "minicurrentstateid", myInstance);
        fieldSetValue(miniCurrentTaskDefKey, "miniCurrentTaskDefKey", myInstance);
        fieldSetValue(miniCurrentProcessDefKey, "miniCurrentProcessDefKey", myInstance);

        // 保存
        this.merge(myInstance);

    }


    /**
     * 修改主表流程状态
     *
     * @param mainId 主表id
     * @param status 状态
     * @author szs
     * @date 2024-03-26
     */
    public void updateMainState(String mainId, String status) {
        // 查询
        T myInstance = this.findById(mainId);
        if (myInstance == null) {
            return;
        }


        fieldSetValue(status, "minicurrentstate", myInstance);
        fieldSetValue(null, "minicurrentstateid", myInstance);
        fieldSetValue(null, "miniCurrentTaskDefKey", myInstance);
        fieldSetValue(null, "miniCurrentProcessDefKey", myInstance);

        // 保存
        this.merge(myInstance);

    }


    /**
     * 当前流程任务完成
     *
     * @param param 参数
     * @author szs
     * @date 2024-03-13
     */
    public void minicurrentTaskCompleted(JSONObject param) {
        String mainId = param.getString("mainId");
        String minicurrentstate = param.getString("minicurrentstate");
        String minicurrentstateid = param.getString("minicurrentstateid");
        String miniCurrentTaskDefKey = param.getString("miniCurrentTaskDefKey");
        String miniCurrentProcessDefKey = param.getString("miniCurrentProcessDefKey");


        // TODO 在各自service中重写自己的逻辑

    }


    /**
     * 当前流程任务创建
     *
     * @param param 参数
     * @author szs
     * @date 2024-03-19
     */
    public void minicurrentTaskCreated(JSONObject param) {
        String mainId = param.getString("mainId");
        String minicurrentstate = param.getString("minicurrentstate");
        String minicurrentstateid = param.getString("minicurrentstateid");
        String miniCurrentTaskDefKey = param.getString("miniCurrentTaskDefKey");
        String miniCurrentProcessDefKey = param.getString("miniCurrentProcessDefKey");

        // TODO 在各自service中重写自己的逻辑

    }


    /**
     * 获取项目名称
     *
     * @param mainId 主表id
     * @return Object
     * @author szs
     * @date 2023-12-26
     */
    public Object getProjectName(String mainId) {
        // 查询
        T myInstance = this.findById(mainId);
        if (myInstance == null) {
            return null;
        }

        return fieldGetValue("projectname", myInstance);
    }


    /**
     * 获取推荐单位
     *
     * @param mainId 主表id
     * @return JSONObject
     * @author szs
     * @date 2024-03-08
     */
    public JSONObject getCasemanagement(String mainId) {
        JSONObject object = new JSONObject();

        // 查询
        T myInstance = this.findById(mainId);
        if (myInstance != null) {
            object.put("citycasemanagementid", fieldGetValue("citycasemanagementid", myInstance));
            object.put("citycasemanagement", fieldGetValue("citycasemanagement", myInstance));
            object.put("countycasemanagementid", fieldGetValue("countycasemanagementid", myInstance));
            object.put("countycasemanagement", fieldGetValue("countycasemanagement", myInstance));
        }

        return object;
    }


    /**
     * 分页获取业务数据
     *
     * @param minicurrentstate         当前流程节点状态
     * @param miniCurrentProcessDefKey 流程定义KEY
     * @param pageNumber               第几页
     * @param pageSize                 每页数量
     * @return PageResult
     * @author szs
     * @date 2024-03-13
     */
    public PageResult<List<T>> getBusinessDataPageList(String minicurrentstate, String miniCurrentProcessDefKey, int pageNumber, int pageSize) {

        // 分页
        Page page = Page.of(pageNumber, pageSize);

        // 查询数据
        HqlBuilder<T> builder = new HqlBuilder<>();
        builder.eq("minicurrentstate", minicurrentstate);
        builder.eq("mini_current_process_def_key", miniCurrentProcessDefKey);
        builder.eq("is_deleted", 0);

        return this.getPagination(page, builder);
    }


    /**
     * 获取业务数据
     *
     * @param minicurrentstate         当前流程节点状态
     * @param miniCurrentProcessDefKey 流程定义KEY
     * @return PageResult
     * @author szs
     * @date 2024-03-13
     */
    public List<T> getBusinessDataList(String minicurrentstate, String miniCurrentProcessDefKey) {

        // 查询数据
        HqlBuilder<T> builder = new HqlBuilder<>();
        builder.eq("minicurrentstate", minicurrentstate);
        builder.eq("mini_current_process_def_key", miniCurrentProcessDefKey);
        builder.eq("is_deleted", 0);

        return this.find(builder);
    }

    /**
     * 政务网 受理保存 调用
     *
     * @param param
     * @return
     */
    public PmsAcceptanceSave acceptanceSave(JSONObject param) {
        String mainId = param.getString("mainId");
//        String minicurrentstate = param.getString("minicurrentstate");
//        String minicurrentstateid = param.getString("minicurrentstateid");
//        String miniCurrentTaskDefKey = param.getString("miniCurrentTaskDefKey");
//        String miniCurrentProcessDefKey = param.getString("miniCurrentProcessDefKey");

        PmsAcceptanceSave acceptanceSave = new PmsAcceptanceSave();
        acceptanceSave.setId(Util.NewGuid());
        acceptanceSave.setMainid(mainId);
        acceptanceSave.setSavedate(new Date());
        acceptanceSave.setRowGuid(mainId);
        //主表对象
        T myInstance = this.findById(mainId);
        if (myInstance == null) {
            return null;
        }
        String batchId = String.valueOf(fieldGetValue("planprojectbatchid", myInstance));
        PmsPlanprojectbatch batch = getObjectById(batchId, PmsPlanprojectbatch.class);
        //查找对应节点创建日志
        QueryWrapper<FlowBusinessTaskLog> wrapper = new QueryWrapper<>();
        wrapper.eq("business_id", mainId)
                .eq("task_name", "申报受理")
                .eq("change_type", "taskCreated")
                .orderByDesc("change_time")
                .last("limit 1");
        FlowBusinessTaskLog taskLog = flowBusinessTaskLogService.getOne(wrapper);
        if (taskLog == null) {
            log.error(mainId+":获取 FlowBusinessTaskLog 的 taskCreated 申报受理记录失败");
            return null;
        }
        //查找对应节点记录
        QueryWrapper<FlowBusiness> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("task_id", taskLog.getTaskId());
        FlowBusiness business = flowBusinessService.getOne(wrapper1);
        if (business == null) {
            log.error(taskLog.getTaskId()+":获取 FlowBusiness 记录失败");
            return null;
        }
        //流程创建人
        SysUser createUser = getObjectById(business.getStartUser(), SysUser.class);
        String roleId = createUser.getRoleId();
        String applyType = "";
        if (roleId.contains("")) {
            //自然人
            applyType = ServiceObjectTypeEnum.NATURAL_PERSON.getCode();
        } else if (roleId.contains("")) {
            //法人
            applyType = ServiceObjectTypeEnum.ENTERPRISE_LEGAL.getCode();
        }
        //受理人
        String taskCandidateUser = business.getTaskCandidateUser();
        String[] split = taskCandidateUser.split(",");
        SysUser handleUser = getObjectById(split[0], SysUser.class);
        //填充字段
        acceptanceSave.setProjectNo(mainId);
        acceptanceSave.setTaskName(batch.getName());
        acceptanceSave.setItemRegionCode(batch.getItemRegionCode());
        acceptanceSave.setItemRegionName(batch.getItemRegionName());
        acceptanceSave.setItemOrganCode(batch.getItemOrganCode());
        acceptanceSave.setItemOrganName(batch.getItemOrganName());
        acceptanceSave.setApplyerName(createUser.getId());
        acceptanceSave.setApplyType(createUser.getRoleName());
        acceptanceSave.setApplyerPageType(CertificateTypeEnum.getCodeByName(createUser.getCertificatetype()));
        acceptanceSave.setApplyerPageCode(createUser.getCertificateno());
        acceptanceSave.setApplyDate(business.getStartTime());
        acceptanceSave.setApplyType(applyType);//申请类型(申请对象类型)
        acceptanceSave.setProjectType(HandleDocumentEnum.COMMITMENT_HANDLE.getCode());
        acceptanceSave.setPromisetimeLimit(batch.getPromisetimeLimit());
        acceptanceSave.setPromisetimeUnit(batch.getPromisetimeUnit());
        acceptanceSave.setTimeLimit(batch.getTimeLimit());
        acceptanceSave.setTimeUnit(batch.getTimeUnit());
        acceptanceSave.setApplyChannel(HandleTypeEnum.ONLINE_HANDLE.getCode());
        acceptanceSave.setAcceptDate(business.getStartTime());
        acceptanceSave.setAcceptName(handleUser.getRoleName());
        acceptanceSave.setAcceptPersonId(handleUser.getId());
        acceptanceSave.setDataSource("贵州省科技业务综合管理信息系统");
        acceptanceSave.setDataFrom("52000001901");

        return acceptanceSave;
    }

    /**
     * 政务网 过程保存 调用
     *
     * @param param
     * @param map
     * @return
     */
    public PmsProcessSave processSave(JSONObject param, Map<String, String> map) {
        String mainId = param.getString("mainId");
        String minicurrentstate = param.getString("minicurrentstate");

        PmsProcessSave processSave = new PmsProcessSave();
        processSave.setId(Util.NewGuid());
        processSave.setMainid(mainId);
        processSave.setSavedate(new Date());
        processSave.setRowGuid(mainId);

        //主表对象
        T myInstance = this.findById(mainId);
        if (myInstance == null) {
            return null;
        }
        String batchId = String.valueOf(fieldGetValue("planprojectbatchid", myInstance));
        PmsPlanprojectbatch batch = getObjectById(batchId, PmsPlanprojectbatch.class);
        //查找对应节点完成日志
        QueryWrapper<FlowBusinessTaskLog> wrapper = new QueryWrapper<>();
        wrapper.eq("business_id", mainId)
                .eq("change_type", "taskCompleted")
                .orderByDesc("change_time")
                .last("limit 1");
        FlowBusinessTaskLog taskLog = flowBusinessTaskLogService.getOne(wrapper);
        if (taskLog == null) {
            log.error(mainId+":获取 FlowBusinessTaskLog 的 taskCompleted 记录失败");
            return null;
        }
        //环节开始日志
        QueryWrapper<FlowBusinessTaskLog> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("business_id", mainId)
                .eq("TASK_ID", taskLog.getTaskId())
                .eq("change_type", "taskCreated")
                .orderByDesc("change_time")
                .last("limit 1");
        FlowBusinessTaskLog createLog = flowBusinessTaskLogService.getOne(wrapper1);
        if (createLog == null) {
            log.error(mainId+";TASK_ID : "+taskLog.getTaskId() +"获取 FlowBusinessTaskLog 的 taskCreated 记录失败");
            return null;
        }
        //审核人
        SysUser changeUser = getObjectById(taskLog.getChangeUser(), SysUser.class);
        //审核人所在单位
        PmsEnterprise enterprise = getObjectById(changeUser.getEnterPriseId(), PmsEnterprise.class);
        //单位区划
        PmsArea pmsArea = enterprise.getPmsArea();
        //填充字段
        processSave.setProjectNo(mainId);
        processSave.setBusinessState(map.get("State"));
        processSave.setBusinessStage(map.get("Stage"));
        String changeComment = taskLog.getChangeComment();
        String resultName = "通过";
        String comment = "";
        if (changeComment != null) {
            JSONObject jsonObject = JSONObject.fromObject(changeComment);
            resultName = String.valueOf(jsonObject.get("taskResultName"));
            comment = String.valueOf(jsonObject.get("taskComment"));
        }
        processSave.setEventName(resultName);
        processSave.setProcessName(taskLog.getTaskName());
        processSave.setHandleUserName(changeUser.getName());
        processSave.setHandleUserCode(changeUser.getCertificateno());
        processSave.setHandleExplain(comment);
        processSave.setEventStartTime(createLog.getChangeTime());
        processSave.setEventEndTime(taskLog.getChangeTime());
        processSave.setOrgCode(enterprise.getUniformcode());
        processSave.setOrgName(enterprise.getName());
        processSave.setRegionCode(pmsArea == null ? "" : pmsArea.getCode());
        processSave.setRegionName(pmsArea == null ? "" : pmsArea.getName());

        return processSave;
    }

    /**
     * 政务网 过程保存 调用
     *
     * @param param
     * @param map
     * @return
     */
    public PmsResultSave resultSave(JSONObject param, Map map) {
        String mainId = param.getString("mainId");
        String minicurrentstate = param.getString("minicurrentstate");

        PmsResultSave resultSave = new PmsResultSave();
        resultSave.setId(Util.NewGuid());
        resultSave.setMainid(mainId);
        resultSave.setSavedate(new Date());
        resultSave.setRowGuid(mainId);
        //主表对象
        T myInstance = this.findById(mainId);
        if (myInstance == null) {
            return null;
        }
        String batchId = String.valueOf(fieldGetValue("planprojectbatchid", myInstance));
        PmsPlanprojectbatch batch = getObjectById(batchId, PmsPlanprojectbatch.class);
        //查找对应节点完成日志
        QueryWrapper<FlowBusinessTaskLog> wrapper = new QueryWrapper<>();
        wrapper.eq("business_id", mainId)
                .eq("change_type", "taskCompleted")
                .orderByDesc("change_time")
                .last("limit 1");
        FlowBusinessTaskLog compeleteLog = flowBusinessTaskLogService.getOne(wrapper);
        if (compeleteLog == null) {
            log.error(mainId+":获取 FlowBusinessTaskLog 的 taskCompleted 记录失败");
            return null;
        }
        //环节开始日志
//        QueryWrapper<FlowBusinessTaskLog> wrapper1 = new QueryWrapper<>();
//        wrapper1.eq("business_id", mainId)
//                .eq("TASK_ID", compeleteLog.getTaskId())
//                .eq("change_type", "taskCreated")
//                .orderByDesc("change_time")
//                .last("limit 1");
//        FlowBusinessTaskLog createLog = flowBusinessTaskLogService.getOne(wrapper1);
//        if (createLog == null) {
//            log.error(mainId+";TASK_ID : "+createLog.getTaskId() +"获取 FlowBusinessTaskLog 的 taskCreated 记录失败");
//            return null;
//        }
        //审核人
        SysUser changeUser = getObjectById(compeleteLog.getChangeUser(), SysUser.class);
        //审核人所在单位
        PmsEnterprise enterprise = getObjectById(changeUser.getEnterPriseId(), PmsEnterprise.class);
        //单位区划
        PmsArea pmsArea = enterprise.getPmsArea();
        //填充字段
        resultSave.setProjectNo(mainId);
        resultSave.setHandleUserCode(changeUser.getCertificateno());
        resultSave.setHandleUserName(changeUser.getName());
        resultSave.setOrgCode(enterprise.getUniformcode());
        resultSave.setOrgName(enterprise.getName());
        resultSave.setRegionCode(pmsArea.getCode());
        resultSave.setRegionName(pmsArea.getName());
        resultSave.setResultDate(compeleteLog.getChangeTime());
        resultSave.setResultType(String.valueOf(map.get("resultType")));
        return resultSave;
    }


    public HqlBuilder<T> getHqlBuilder() {
        String userId = AuthUtil.getUserId();
        if (userId == null) {
            return null;
        }
        SysUser user = getObjectById(userId, SysUser.class);
        String enterPriseId = user.getEnterPriseId();
        String deptId = user.getDeptId();
        String roleId = user.getRoleId();
        HqlBuilder<T> hqlBuilder = new HqlBuilder<>();
        hqlBuilder.eq(!Util.isEoN(enterPriseId), "ORGANIZE_ID", enterPriseId)
                .eq(!Util.isEoN(deptId), "ITEM_ORGAN_CODE", deptId);
        String[] idSplit = roleId.split(",");
        if (idSplit.length > 0) {
            for (int i = 0; i < idSplit.length; i++) {
                hqlBuilder.eq(!Util.isEoN(idSplit[i]), "ROLE_IDS", ";" + idSplit[i] + ";");
            }
        }
        return hqlBuilder;
    }

    //获取角色权限过滤条件
    public String getRoleSql() {
        String userId = AuthUtil.getUserId();
        if (userId == null) {
            return null;
        }
        SysUser user = getObjectById(userId, SysUser.class);
        StringBuilder sql = new StringBuilder(" and e.ITEM_ORGAN_CODE like '%" + user.getDeptId() + "%' " +
                " and e.ORGANIZE_ID = '" + user.getEnterPriseId() + "' ");
        String roleId = user.getRoleId();
        if (Util.isEoN(roleId)) {
            return sql.toString();
        }
        String[] idSplit = roleId.split(",");
        if (idSplit.length > 0) {
            for (int i = 0; i < idSplit.length; i++) {
                if (i == 0) {
                    sql.append(" and ( ");
                } else {
                    sql.append(" or ");
                }
                sql.append("e.role_ids like '%;" + idSplit[i] + ";%' ");
            }
        }
        sql.append(") ");
        return sql.toString();
    }
}
