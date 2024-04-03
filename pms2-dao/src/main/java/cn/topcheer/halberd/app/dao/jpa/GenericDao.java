package cn.topcheer.halberd.app.dao.jpa;

import cn.hutool.db.sql.Direction;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.metamodel.model.domain.internal.EntityTypeImpl;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springblade.core.mp.base.BaseCommonEntity;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.ManagedType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.hibernate.criterion.Example.create;

@Slf4j
public abstract class GenericDao<T> extends HibernateDaoSupport {

    private final Class<T> t;

    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory=entityManagerFactory;
        this.setSessionFactory(entityManagerFactory.unwrap(SessionFactory.class));
    }


    public Class getEntityClassByName(String className)
    {
        List<ManagedType> list=  this.entityManagerFactory.getMetamodel()
                .getEntities().stream().filter((c)->c.getName().equals(className))
                .collect(Collectors.toList());
        if(list.size()>0)
        {
           return list.get(0).getJavaType();
        }
        else
        {
            return  null;
        }
    }

    /** 获取泛型的类型  */
    @SuppressWarnings("rawtypes")
    private static Class getSuperClassGenricType(final Class clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class<?>) params[index];
    }


    public Session getCurrentSession()
    {
        return this.currentSession();
    }

    /** HibernateBaseDao的构造方法 */
    @SuppressWarnings("unchecked")
    public GenericDao() {
        this.t = getSuperClassGenricType(this.getClass(), 0);
    }

    public void persist(T transientInstance)
    {
        log.debug("persisting " + t.getName() + " instance");
        try
        {
            this.currentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re)
        {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void attachDirty(T instance)
    {
        log.debug("attaching dirty " + t.getName() + " instance");
        try
        {
            this.currentSession().saveOrUpdate(instance);
            log.debug("attachdirty successful");
        }
        catch (RuntimeException re)
        {
            log.error("attach dirty failed", re);
            throw re;
        }
    }

    public void attachClean(T instance)
    {
        log.debug("attaching clean " + t.getName() + " instance");
        try
        {
            this.currentSession().lock(instance, LockMode.NONE);
            log.debug("attach clean successful");
        }
        catch (RuntimeException re)
        {
            log.error("attach clean failed", re);
            throw re;
        }
    }

    public boolean delete(T persistentInstance)
    {
        log.debug("deleting " + t.getName() + " instance");
        try
        {
            this.currentSession().delete(persistentInstance);
            log.debug("delete successful");
            return true;
        }
        catch (RuntimeException re)
        {
            log.error("delete failed", re);
            throw re;
        }
    }

    public boolean deleteAll()
    {
        log.debug("deleting all " + t.getName() + " instance");
        try
        {
            String queryString = "delete from " + t.getName();
            this.currentSession().createQuery(queryString).executeUpdate();
            log.debug("delete all successful");
            return true;
        }
        catch (RuntimeException re)
        {
            log.error("delete all failed", re);
            throw re;
        }
    }

    public void save(T instance)
    {
        log.debug("saving " + t.getName() + " instance");
        try
        {
            if(instance instanceof BaseCommonEntity)
            {
                BaseCommonEntity commonEntity= (BaseCommonEntity) instance;
                if(commonEntity.getCreateTime()==null)
                {
                    commonEntity.setCreateTime(new Date());
                    commonEntity.setCreateUser(AuthUtil.getUserId());
                }
                commonEntity.setUpdateTime(new Date());
                commonEntity.setUpdateUser(AuthUtil.getUserId());
            }
            this.currentSession().save(instance.getClass().getName(), instance);
            log.debug("save successful");
        }
        catch (RuntimeException re)
        {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(T instance)
    {
        log.debug("updating " + t.getName() + " instance");
        try
        {
            if(instance instanceof BaseCommonEntity)
            {
                BaseCommonEntity commonEntity= (BaseCommonEntity) instance;

                commonEntity.setUpdateTime(new Date());
                commonEntity.setUpdateUser(AuthUtil.getUserId());
            }
            this.currentSession().update(instance);
            log.debug("update successful");
        }
        catch (RuntimeException re)
        {
            log.error("update failed", re);
            throw re;
        }
    }

    public T saveOrUpdate(T instance)
    {
        log.debug("save or update " + t.getName() + " instance");
        try
        {
            if(instance instanceof BaseCommonEntity)
            {
                BaseCommonEntity commonEntity= (BaseCommonEntity) instance;
                if(commonEntity.getCreateTime()==null)
                {
                    commonEntity.setCreateTime(new Date());
                    commonEntity.setCreateUser(AuthUtil.getUserId());
                }
                commonEntity.setUpdateTime(new Date());
                commonEntity.setUpdateUser(AuthUtil.getUserId());
            }
            this.currentSession().saveOrUpdate(instance);
            log.debug("save or update successful");
            return instance;
        }
        catch (RuntimeException re)
        {
            log.error("save or update failed", re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public T merge(T detachedInstance)
    {
        log.debug("merging " + t.getName() + " instance");
        try
        {
            T result = (T) this.currentSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public T findById(java.lang.String id)
    {
        log.debug("getting " + t.getName() + " instance with id: " + id);
        try
        {

            T instance = (T) this.getHibernateTemplate().get(t.getName(), id);
            if (instance == null)
            {
                log.debug("get successful, no instance found");
            } else
            {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }



    public List<T> find(HqlBuilder<T> hqlBuilder){
        log.debug("finding " + t.getName() + " instance by example");
        try
        {
            Query query = this.getQuery(hqlBuilder.getHql(t));
            for(int i=0;i< hqlBuilder.getParamsNames().size();i++)
            {
                Object paramsValue=hqlBuilder.getParamsValues().get(i);
                if(paramsValue instanceof Collection)
                {
                    query.setParameterList(hqlBuilder.getParamsNames().get(i),(Collection) paramsValue);
                }
                else {
                    query.setParameter(hqlBuilder.getParamsNames().get(i), paramsValue);
                }
            }
            List<T> results = query.getResultList();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re)
        {
            log.error("find by example failed", re);
            throw re;
        }
    }


    public List<Map> find(SqlBuilder sqlBuilder){
        log.debug("finding " + t.getName() + " instance by example");
        try
        {
            Query query = this.getQuery(sqlBuilder.getSql());
            for(int i=0;i< sqlBuilder.getParamsNames().size();i++)
            {
                Object paramsValue=sqlBuilder.getParamsValues().get(i);
                if(paramsValue instanceof Collection)
                {
                    query.setParameterList(sqlBuilder.getParamsNames().get(i),(Collection) paramsValue);
                }
                else {
                    query.setParameter(sqlBuilder.getParamsNames().get(i), paramsValue);
                }
            }
            List<Map> results = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re)
        {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public T findOne(HqlBuilder  hqlBuilder){
        log.debug("finding " + t.getName() + " instance by example");
        try
        {
            Query query = this.getQuery(hqlBuilder.getHql(t));
            for(int i=0;i< hqlBuilder.getParamsNames().size();i++)
            {
                Object paramsValue=hqlBuilder.getParamsValues().get(i);
                if(paramsValue instanceof Collection)
                {
                    query.setParameterList((String) hqlBuilder.getParamsNames().get(i),(Collection) paramsValue);
                }
                else {
                    query.setParameter((String) hqlBuilder.getParamsNames().get(i), paramsValue);
                }
            }
            query.setMaxResults(1);
            List<T> results = query.getResultList();

            if(results.size()>0)
                return results.get(0);
            else
                return null;
        }
        catch (RuntimeException re)
        {
            log.error("find by example failed", re);
            throw re;
        }
    }


    public Long findCount(HqlBuilder<T> hqlBuilder){
        log.debug("finding " + t.getName() + " instance by example");
        try
        {
            Query query = this.getQuery("select count(1) " + hqlBuilder.getCountHql(t));
            for(int i=0;i< hqlBuilder.getParamsNames().size();i++)
            {
                Object paramsValue=hqlBuilder.getParamsValues().get(i);
                if(paramsValue instanceof Collection)
                {
                    query.setParameterList(hqlBuilder.getParamsNames().get(i),(Collection) paramsValue);
                }
                else {
                    query.setParameter(hqlBuilder.getParamsNames().get(i), paramsValue);
                }
            }
            long result =(Long) query.uniqueResult();
            log.debug("find by findCount successful, result size: " + result);
            return result;
        }
        catch (RuntimeException re)
        {
            log.error("find by example failed", re);
            throw re;
        }
    }
    public List<T> findByIds(List<String> ids)
    {
        log.debug("finding " + t.getName() + " instance by ids");
        try
        {
            if(ids.size()==0)
            {return new ArrayList<T>();}
            String hql = "from " + t.getName() + " where id in (:ids)";
            List<T> results = (List<T>) this.currentSession().createQuery(hql).setParameterList("ids", ids).list();
            log.debug("find by ids successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re)
        {
            log.error("find by ids failed", re);
            throw re;
        }
    }
    public List<T> findByIds(List<String> ids,boolean flag)
    {
        log.debug("finding " + t.getName() + " instance by ids");
        try
        {
            List<T> tempList = new ArrayList<T>();
            if(ids.size()==0)
            {return new ArrayList<T>();}
            if(ids.size()<1000)
                return findByIds(ids);
            int rows = ids.size()/1000+1;
            for(int i=0;i<rows;i++){
                List<String> tempids = ids.subList(i*1000,(i+1)*1000>=ids.size()?ids.size():(i+1)*1000);
                List<T> results = findByIds(tempids);
                tempList.addAll(results);
                log.debug("find by ids successful, result size: " + results.size());
            }
            return tempList;
        }
        catch (RuntimeException re)
        {
            log.error("find by ids failed", re);
            throw re;
        }
    }
    public List<T> findByExample(T instance)
    {
        log.debug("finding " + t.getName() + " instance by example");
        try
        {
            List<T> results = (List<T>) this.currentSession().createCriteria(t.getName()).add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re)
        {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public boolean deleteById(java.lang.String id)
    {
        log.debug("deleting " + t.getName() + " instance with id: " + id);
        try
        {
            T instance = this.findById(id);
            if (instance != null)
            {
                this.delete(instance);
                log.debug("delete by id successful");
            } else
                log.warn("delete by id ignored,instance with id:" + id + " not found");
            return true;
        }
        catch (RuntimeException re)
        {
            log.error("delete by id failed", re);
            throw re;
        }
    }

    public boolean deleteByIds(List<java.lang.String> ids)
    {
        log.debug("deleting " + t.getName() + " instance with ids");
        try
        {

            String  hql=  "delete "+t.getName() +" where id  in  (:ids)";
            Query query = this.getSessionFactory().getCurrentSession() .createQuery(hql);
            query.setParameterList ("ids", ids.toArray()).executeUpdate();
            return true;
        }
        catch (RuntimeException re)
        {
            log.error("delete by id failed", re);
            throw re;
        }

    }


    public List<T> find(String filter)
    {
        log.debug("finding " + t.getName() + " instance with filter: " + filter);
        return this.find("", filter);
    }

    /**
     * 通过参数方式查询
     *
     * @param query
     * @param objects
     * @return
     */
    public List<T> findParams(String query, Object... objects)
    {
        return (List<T>) this.getHibernateTemplate().find(query, objects);
    }

    public List<T> findAll()
    {
        return this.find("", "");
    }

    public List<T> findSort(String sortField)
    {
        return this.find(sortField, "");
    }

    public List<T> findFilter(String filter)
    {
        return this.find("", filter);
    }

    public List<T> find(String sortField, String filter)
    {
        log.debug("finding " + t.getName() + " instance with sortField: " + sortField + ", filter: " + filter);
        try
        {
            String queryString = "";
            if (filter != null && filter.length() > 0)
                queryString = "from " + t.getName() + " as model where " + filter;
            else
                queryString = "from " + t.getName() + " as model";
            if (sortField != null && sortField.length() > 0)
                queryString += " order by " + sortField;
            List<T> results = this.currentSession().createQuery(queryString).list();
            log.debug("find successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re)
        {
            log.error("find failed", re);
            throw re;
        }
    }

    public List findByHql(String hql, Object... params)
    {
        log.debug("findByHql " + t.getName() + " instance with Hql: " + hql);
        try
        {

            List results = this.getHibernateTemplate().find(hql, params);

            return results;
        }
        catch (RuntimeException re)
        {
            log.error("findByHql failed", re);
            throw re;
        }
    }

    public List<T> findByProperty(String propertyName, Object value)
    {
        log.debug("finding " + t.getName() + " instance with property: " + propertyName + ", value: " + value);
        try
        {
            String queryString = null;
            List<T> results = null;
            if (value == null)
            {
                queryString = "from " + t.getName() + " as model where model." + propertyName + " is null";
                results = (List<T>) this.getHibernateTemplate().find(queryString);
            } else
            {
                queryString = "from " + t.getName() + " as model where model." + propertyName + " = ?0";
                results = (List<T>) this.getHibernateTemplate().find(queryString, value);
            }
            log.debug("find by property successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re)
        {
            log.error("find by property failed", re);
            throw re;
        }
    }

    public List<T> findByPropertyASC(String propertyName, Object value)
    {
        log.debug("finding " + t.getName() + " instance with property: " + propertyName + ", value: " + value);
        try
        {
            String queryString = null;
            List<T> results = null;
            if (value == null)
            {
                queryString = "from " + t.getName() + " as model where model." + propertyName + " is null order by model.seq";
                results = (List<T>) this.getHibernateTemplate().find(queryString);
            } else
            {
                queryString = "from " + t.getName() + " as model where model." + propertyName + " = ? order by model.seq";
                results = (List<T>) this.getHibernateTemplate().find(queryString, value);
            }
            log.debug("find by property successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re)
        {
            log.error("find by property failed", re);
            throw re;
        }
    }


    public List<T> findByPropertyLike(String propertyName, Object value)
    {
        log.debug("finding Like " + t.getName() + " instance with property: " + propertyName + ", value: " + value);
        try
        {
            String queryString = null;
            List<T> results = null;
            if (value == null)
            {
                queryString = "from " + t.getName() + " as model where model." + propertyName + " is null";
                results = (List<T>) this.getHibernateTemplate().find(queryString);
            } else
            {
                queryString = "from " + t.getName() + " as model where model." + propertyName + " like "+" '%"+value+"%'";
                results = this.currentSession().createQuery(queryString).list();
            }
            log.debug("find Like by property successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re)
        {
            log.error("find Like by property failed", re);
            throw re;
        }
    }

    public List<T> getPaginationDao(int pageNum, int pageSize, String orderby, String filterString)
    {
        log.debug("getPaginationDao " + t.getName());
        try
        {
            String whereString = "";
            if (filterString != null && filterString.length() > 0)
                whereString = " where " + filterString;
            String hql = "select t from " + t.getName() + " t " + whereString;
            if (orderby != null && orderby.length() > 0)
                hql = hql + " order by t." + orderby;
            Query query = this.currentSession().createQuery(hql).setFirstResult(pageNum).setMaxResults(pageSize);
            List<T> results = query.list();
            log.debug("getPaginationDao successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re)
        {
            log.error("getPaginationDao failed", re);
            throw re;
        }
    }



    public List<T> getPaginationDao(Page page , T obj)
    {
        log.debug("getPaginationDao " + t.getName());
        try
        {
            Example example= Example.create(obj);
            DetachedCriteria dc =  DetachedCriteria.forClass(t);
            dc.add(example);
            if(page.getOrders()!=null)
            {
                Arrays.stream(page.getOrders()).forEach(o->{
                    if(o.getDirection().equals(Direction.ASC))
                    {
                        dc.addOrder(Order.asc(o.getField()));
                    }
                    else {
                        dc.addOrder(Order.desc(o.getField()));
                    }
                    });
            }

            return this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<T>>() {
                @Override
                public List<T> doInHibernate(Session session) throws HibernateException {
                    Criteria criteria=dc.getExecutableCriteria(session.getSession());
                    criteria.setFirstResult(page.getStartIndex()).setMaxResults(page.getPageSize());
                    List<T> list = criteria.list();
                    return list;
                }
            });
             // return (List<T>)this.getHibernateTemplate().findByCriteria(dc,page.getStartIndex(),page.getPageSize());

        }
        catch (RuntimeException re)
        {
            log.error("getPaginationDao failed", re);
            throw re;
        }
    }


    public List<T> getPaginationDao(Page page , HqlBuilder hqlBuilder)
    {
        log.debug("getPaginationDao " + t.getName());
        try
        {
            Query query = this.getQuery(hqlBuilder.getHql(t));
            for(int i=0;i< hqlBuilder.getParamsNames().size();i++)
            {
                Object paramsValue=hqlBuilder.getParamsValues().get(i);
                if(paramsValue instanceof Collection)
                {
                    query.setParameterList((String) hqlBuilder.getParamsNames().get(i),(Collection) paramsValue);
                }
                else {
                    query.setParameter((String) hqlBuilder.getParamsNames().get(i), paramsValue);
                }
            }
            query.setFirstResult(page.getStartIndex()).setMaxResults(page.getPageSize());
            return (List<T>)query.list();

        }
        catch (RuntimeException re)
        {
            log.error("getPaginationDao failed", re);
            throw re;
        }
    }


    public List getPaginationBySql(int pageNum, int pageSize, String sql, boolean ishql)
    {
        log.debug("getPaginationBySql " + t.getName());
        try
        {
            Query query;
            if (ishql)
                query = this.currentSession().createQuery(sql).setFirstResult(pageNum).setMaxResults(pageSize);
            else
                query = this.currentSession().createSQLQuery(sql).setFirstResult(pageNum).setMaxResults(pageSize)
                        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List results = query.list();
            log.debug("getPaginationBySql successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re)
        {
            log.error("getPaginationBySql failed", re);
            throw re;
        }
    }

    public Map getPaginationMap(int pageNum, int pageSize, String orderby, String filterString)
    {
        log.debug("getPaginationMap " + t.getName());
        try
        {
            List list = this.getPaginationDao(pageNum, pageSize, orderby, filterString);
            HashMap rvMap = new HashMap();
            rvMap.put("root", list);
            long total = this.getDaoCount(filterString, false);
            rvMap.put("totalProperty", total);
            log.debug("getPaginationMap successful");
            return rvMap;
        }
        catch (RuntimeException re)
        {
            log.error("getPaginationMap failed", re);
            throw re;
        }
    }


    public PageResult<List<T>> getPagination(Page page, T o)
    {
        log.debug("getPaginationMap " + t.getName());
        try
        {


            List<T> list = this.getPaginationDao(page,o);
            long total=this.getDaoCount(page,o);
            return PageResult.data(list,total);
        }
        catch (RuntimeException re)
        {
            log.error("getPaginationMap failed", re);
            throw re;
        }
    }

    public PageResult<List<T>> getPagination(Page page, HqlBuilder  hqlBuilder)
    {
        log.debug("getPaginationMap " + t.getName());
        try
        {

            List<T> list = this.getPaginationDao(page,hqlBuilder);
            long total=this.getDaoCount(page,hqlBuilder);
            return PageResult.data(list,total);
        }
        catch (RuntimeException re)
        {
            log.error("getPaginationMap failed", re);
            throw re;
        }
    }

    public PageResult<List<T>> getPagination(Page page, String filterString)
    {
        log.debug("getPaginationMap " + t.getName());
        try
        {
            List<T> list = this.getPaginationDao(page.getPageNumber(),page.getPageSize(), page.getOrderStr(), filterString);
            HashMap rvMap = new HashMap();
            rvMap.put("root", list);
            long total = this.getDaoCount(filterString, false);
            rvMap.put("totalProperty", total);
            log.debug("getPaginationMap successful");
            return PageResult.data(list,total);
        }
        catch (RuntimeException re)
        {
            log.error("getPaginationMap failed", re);
            throw re;
        }
    }

    public Query getQuery(String hsql)
    {
        //this.currentSession().createNamedQuery(hsql,t);
        Query query = this.currentSession().createQuery(hsql);
        return query;
    }

    public Query getSqlQueryNotEntity(String sql)
    {
        Query query = this.currentSession().createSQLQuery(sql);
        return query;
    }
    public Query getSqlQuery(String sql)
    {
        Query query = this.currentSession().createSQLQuery(sql).addEntity(t);
        return query;
    }

    public long getDaoCount()
    {
        log.debug("getDaoCount " + t.getName());
        try
        {
            String hql = "select count(*) from " + t.getName();
            long l = (Long) this.getHibernateTemplate().find(hql).get(0);
            log.debug("getDaoCount successful, result size: " + String.valueOf(l));
            return l;
        }
        catch (RuntimeException re)
        {
            log.error("getDaoCount failed", re);
            throw re;
        }
    }

    public long getDaoCount(Page page,T obj)
    {
        log.debug("getDaoCount " + t.getName());
        try
        {
            Example example= Example.create(obj);
            DetachedCriteria dc = DetachedCriteria.forClass(t);
            dc.add(example);
            if(page.getOrders()!=null)
            {
                Arrays.stream(page.getOrders()).forEach(o->{
                    if(o.getDirection().equals(Direction.ASC))
                    {
                        dc.addOrder(Order.asc(o.getField()));
                    }
                    else {
                        dc.addOrder(Order.desc(o.getField()));
                    }
                });
            }
            dc.setProjection(Projections.rowCount());

            long totalCount =this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Long>() {
                @Override
                public Long doInHibernate(Session session) throws HibernateException {
                    return  (Long)   dc.getExecutableCriteria(session.getSession()).uniqueResult();
                }
            });



            return totalCount;
        }
        catch (RuntimeException re)
        {
            log.error("getDaoCount failed", re);
            throw re;
        }
    }
    public long getDaoCount(Page page,HqlBuilder hqlBuilder)
    {
        log.debug("getDaoCount " + t.getName());
        long totalCount=0L;
        try
        {
            Query query = this.getQuery("select count(1) " + hqlBuilder.getCountHql(t));
            for(int i=0;i< hqlBuilder.getParamsNames().size();i++)
            {
                Object paramsValue=hqlBuilder.getParamsValues().get(i);
                if(paramsValue instanceof Collection)
                {
                    query.setParameterList((String) hqlBuilder.getParamsNames().get(i),(Collection) paramsValue);
                }
                else {
                    query.setParameter((String) hqlBuilder.getParamsNames().get(i), paramsValue);
                }
            }
            totalCount = (long)query.uniqueResult();

            return totalCount;
        }
        catch (RuntimeException re)
        {
            log.error("getDaoCount failed", re);
            throw re;
        }
    }
    public long getDaoCount(String filterString, boolean initsearchHql)
    {
        log.debug("getDaoCount " + t.getName());
        try
        {
            String whereString = "";
            long l;
            if (initsearchHql)
            {
                whereString = initSearchHql_whereString(filterString);
                String hql = "select count(*) from " + t.getName() + " " + whereString;
                Map map = initSearchValues(filterString);
                Object[] perchs = map.keySet().toArray(new String[0]);
                l = (Long) this.getHibernateTemplate().find(hql, perchs).get(0);
            } else
            {
                if (filterString != null && filterString.length() > 0)
                    whereString = " where " + filterString;
                String hql = "select count(*) from " + t.getName() + " " + whereString;
                l = (Long) this.getHibernateTemplate().find(hql).get(0);
            }
            log.debug("getDaoCount successful, result size: " + String.valueOf(l));
            return l;
        }
        catch (RuntimeException re)
        {
            log.error("getDaoCount failed", re);
            throw re;
        }
    }

    public String initSearchHql_whereString(String filters)
    {
        log.debug("initSearchHql_whereString " + t.getName());
        try
        {
            String hqlFilter = " where  1=1 ";
            HashSet perchSet = new HashSet(0);
            if (filters == null || filters.equals(""))
            {
                return hqlFilter;
            }
            String filterString = filters.replaceAll("[\"]", "");
            filterString = filterString.substring(1, filterString.length() - 1);
            String[] arrFilter = filterString.split(",");
            for (String filter : arrFilter)
            {
                if (filter.startsWith("except"))
                    continue;
                if (filter.startsWith("null"))
                {
                    String[] optionString = filter.split(":", 2);
                    String[] arrOption = optionString[0].split("\\$");
                    if (optionString[1].equals("true"))
                    {
                        hqlFilter += arrOption[1] + " " + arrOption[2].replaceAll("[-]", ".").replaceAll("&", "") + " is not null ";
                    }
                    continue;
                }
                String optionString = filter.split(":", 2)[0];
                String filterValue = filter.split(":", 2)[1];
                String[] arrOption = optionString.split("\\$");
                if (!(filterValue.equals("")) || arrOption[2].equals("!="))
                {
                    String perchString = arrOption[1].replaceAll("[-]", "");
                    if (perchSet.contains(perchString))
                    {
                        perchString += "_1";
                    }
                    perchSet.add(perchString);
                    hqlFilter += arrOption[0] + " " + arrOption[1].replaceAll("[-]", ".").replaceAll("&", "") + " " + arrOption[2] + " " + arrOption[3]
                            .replace(arrOption[3], ":" + perchString) + " ";
                }
            }
            log.debug("function-initSearchHql success:" + hqlFilter);
            return hqlFilter;
        }
        catch (RuntimeException re)
        {
            log.error("function-initSearchHql error", re);
            return null;
        }
    }

    public Map initSearchValues(String filters)
    {
        log.debug("initSearchValues " + t.getName());
        try
        {
            Map<String, Object> valuesMap = new HashMap<String, Object>();
            if (filters == null || filters.equals(""))
            {
                return valuesMap;
            }
            String filterString = filters.replaceAll("[\"]", "");
            filterString = filterString.substring(1, filterString.length() - 1);
            String[] arrFilter = filterString.split(",");
            for (String filter : arrFilter)
            {
                if (filter.startsWith("except"))
                    continue;
                if (filter.startsWith("null"))
                    continue;
                String optionString = filter.split(":", 2)[0];
                String valueString = filter.split(":", 2)[1];
                String[] arrOption = optionString.split("\\$");
                if (!(valueString.equals("")) || arrOption[2].equals("!="))
                {
                    String perchString = arrOption[1].replaceAll("[-]", "");
                    String valueType = arrOption.length > 4 ? arrOption[4] : "String";
                    valueString = arrOption[3].replace("value", valueString);
                    Object value = valueTransform(valueString, valueType);
                    if (valuesMap.containsKey(perchString))
                    {
                        perchString += "_1";
                    }
                    valuesMap.put(perchString, value);
                }
            }
            return valuesMap;
        }
        catch (RuntimeException re)
        {
            log.error("initSearchValues error", re);
            return null;
        }
    }

    public Object valueTransform(String valueString, String valueType)
    {
        if (valueType.equals("String"))
        {
            return valueString;
        } else if (valueType.equals("Short"))
        {
            return Short.parseShort(valueString);
        } else if (valueType.equals("Integer"))
        {
            return Integer.parseInt(valueString);
        } else if (valueType.equals("Double"))
        {
            return Double.parseDouble(valueString);
        } else if (valueType.equals("Long"))
        {
            return Long.parseLong(valueString);
        } else if (valueType.equals("Boolean"))
        {
            return Boolean.parseBoolean(valueString);
        } else if (valueType.equals("Date"))
        {
            try
            {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valueString.replace("T", " "));
            }
            catch (ParseException re)
            {
                log.error("error while transfer to date from " + valueString, re);
                return null;
            }
        } else
            return valueString;
    }






    /**
     * 判断用于过滤的值是否有效
     * @return
     */
    private  boolean isValidValue(Object value){
        if(value==null) return false;
        if(value instanceof String){
            if (((String)value).trim().equals("")) return false;
            if (((String)value).trim().toLowerCase().equals("null")) return false;
        }
        return true;
    }


    /**
     * 用hibernate 的sql 语句执行
     * 由于之前用了 dbhelper 两个数据源可能导致数据不同步
     * @param sql
     * @return
     */
    public int changeStateBySessionSql(String sql){
        log.debug("findByExample(json) " + t.getName() + " instance with Hql: " + sql);
        int res = -1;
        try {
            res = this.currentSession().createSQLQuery(sql).executeUpdate();
        }catch (RuntimeException re){
            log.error("hibernate Sql 执行,sql=>"+sql, re);
        }
        return res;
    }


}
