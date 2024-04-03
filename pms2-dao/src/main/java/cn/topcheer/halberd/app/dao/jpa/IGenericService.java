package cn.topcheer.halberd.app.dao.jpa;

import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import org.hibernate.Query;

import java.util.List;
import java.util.Map;

public interface IGenericService<T> {
    T getById(String id);

    void persist(T t);

    void attachDirty(T t);

    void attachClean(T t);

    boolean delete(T t);

    boolean deleteList(List<T> ts);

    boolean deleteAll();

    boolean save(T t);

    void update(T t);

    T saveOrUpdate(T t);

    void saveOrUpdateEntity(Object entity);

    T merge(T t);

    List<T> findParams(String filter, Object... values);

    List findByHql(String hql, Object... values);

    T findById(String id);

    List<T> findByIds(List<String> ids);

    List<T> findByIds(List<String> ids, boolean flag);

    List<T> findByExample(T t);

    boolean deleteById(String id);

    boolean deleteByIds(List<String> ids);

    boolean deleteByIds(Object[] ids);

    List<T> findAll();

    List<T> findSort(String sortField);

    List<T> findFilter(String filter);

    List<T> find(String orderby, String filter);

    List<T> findByProperty(String propertyName, Object value);

    List<T> findByPropertyASC(String propertyName, Object value);

    List<T> findByPropertyLike(String propertyName, Object value);

    void save(T rt, Map<String, Object> params);

    Query getQuery(String hsql);

    Query getSqlQueryNotEntity(String sql);

    Query getSqlQuery(String sql);

    List<T> getPaginationDao(int pageNum, int pageSize, String orderby,
                             String filterString);

    List getPaginationBySql(int pageNum, int pageSize, String sql,
                            boolean ishql);

    Map getPaginationMap(int pageNum, int pageSize, String orderby,
                         String filterString);

    long getDaoCount();

    long getDaoCount(String filterString, boolean initsearchHql);

    T findOne(HqlBuilder hqlBuilder);

    List<T> find(HqlBuilder<T> hqlBuilder);

    Long findCount(HqlBuilder<T> hqlBuilder);

    PageResult<List<T>> getPagination(Page page, T o);

    PageResult<List<T>> getPagination(Page page, HqlBuilder hqlBuilder);

    PageResult<List<T>> getPagination(Page page, String filterString);
}
