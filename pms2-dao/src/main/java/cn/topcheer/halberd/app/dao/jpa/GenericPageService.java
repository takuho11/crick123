package cn.topcheer.halberd.app.dao.jpa;

import cn.topcheer.halberd.app.api.utils.Util;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.halberd.core.result.Page;
import cn.topcheer.halberd.core.result.PageResult;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public abstract class GenericPageService<T> extends GenericService<T> {
    @Autowired
    DBHelper dbHelper;


    /**
     * 【分页】---通过sql语句来进行分页处理
     *
     * @param sql			组合好的sql语句（针对只有一个from的sql，多个from会报错）
     * @param paramList		where后跟的条件集合
     * @param page	分页的配置：第几页，一页几条数据
     * @return
     */
    public PageResult<List<Map>> findPageBySql(String sql, List paramList, Page page){
        //通过前台传来的currentPage：当前第几页;pageSize：每页多少数据，
        //计算出获取所有数据当中改的范围值，bigNum:范围最大值，smallNum:范围最小值
        int bigNum = page.getEndIndex();  // Integer.parseInt(pageConfig.get("currentPage").toString())*Integer.parseInt(pageConfig.get("pageSize").toString())+1;
        int smallNum =page.getStartIndex(); // (Integer.parseInt(pageConfig.get("currentPage").toString())-1)*Integer.parseInt(pageConfig.get("pageSize").toString());
        //获取数据的总数
        String countSql = "select count(*) from ("+sql+")";
        String totalCount = this.dbHelper.getOnlyStringValue(countSql,paramList.toArray());
        if(Util.isEoN(totalCount)){
            totalCount = "0";
        }
        //判断前台是否有传多级排序
        if(page.getOrders() != null) {
            if (page.getOrders().length > 0) {
//            if(!Util.isEoN(pageConfig.get("sorts"))){
//                JSONArray sorts = pageConfig.getJSONArray("sorts");
//                String orderBySql = " order by ";
//                if(sorts.size()>0){
//                    for (int i = 0; i < sorts.size(); i++) {
//                        JSONObject sort = sorts.getJSONObject(i);
//                        switch (sort.get("sorttype")+""){
//                            case "数字":
//                                orderBySql = orderBySql +" to_number(" +sort.get("field")+") ";
//                                break;
//                            default:
//                                //默认都先按照拼音排序
//                                orderBySql = orderBySql +" NLSSORT(" +sort.get("field")+" ,'NLS_SORT = SCHINESE_PINYIN_M')  ";
//                                break;
//                        }
//                        orderBySql = orderBySql + sort.get("direction")+" nulls last ,";
//                    }
                String orderBySql = page.getOrderStr();  //orderBySql.substring(0,orderBySql.length()-1);
                sql = "select * from (" + sql + ") where 1=1 order by " + orderBySql;
//                }
//            }
            }
        }
        //获取当前分页下的数据
        sql = "select levelone.* from(select leveltwo.*,rownum rn from (" +sql+") leveltwo ) levelone where levelone.rn <= ? and  levelone.rn > ?";
        paramList.add(bigNum);
        paramList.add(smallNum);
        //得出数据结果
        List<Map> list  = this.dbHelper.getRows(sql, paramList.toArray(new Object[]{}));
        return PageResult.data(list, Long.parseLong(totalCount));
    }


    /**
     * 【分页】---通过hql语句来进行分页处理
     *
     * @param hql
     * @param paramList
     * @param pageConfig
     * @return
     */
    public PageResult<List<Map>> findPageByHql(String hql, List paramList, Page pageConfig){
        //通过前台传来的currentPage：当前第几页;pageSize：每页多少数据，
        //计算出获取所有数据当中改的范围值，bigNum:范围最大值，smallNum:范围最小值
        int pageSizes = pageConfig.getPageSize();// Integer.parseInt(pageConfig.get("pageSize").toString());//每页多少数据
        int currentPage = pageConfig.getPageNumber(); // Integer.parseInt(pageConfig.get("currentPage").toString());//当前第几页
        int startNum = pageConfig.getStartIndex();//  (currentPage-1)*pageSizes;//从多少数据开始
        //获取数据的总数
        String countHql = "select count(*) "+hql.substring(hql.indexOf("from"),hql.length());
        Query countQuery = this.getQuery(countHql);
        if(paramList.size()>0){
            for (int i = 0; i <paramList.size() ; i++) {
                countQuery.setParameter(i,paramList.get(i)+"");
            }
        }
        String totalCount = countQuery.uniqueResult().toString();
        //获取当前分页下的数据
        Query hqlQuery = this.getQuery(hql);
        if(paramList.size()>0){
            for (int i = 0; i <paramList.size() ; i++) {
                hqlQuery.setParameter(i,paramList.get(i)+"");
            }
        }
        hqlQuery.setFirstResult(startNum);
        hqlQuery.setMaxResults(pageSizes);
        List list = hqlQuery.list();
        return PageResult.data(list,Long.parseLong(totalCount));// new Page<Map>(list, Integer.parseInt(totalCount));
    }



    /**
     * 【分页】---通过sql语句来进行分页处理 for 序号
     *
     * @param sql			组合好的sql语句（针对只有一个from的sql，多个from会报错）
     * @param paramList		where后跟的条件集合
     * @param pageConfig	分页的配置：第几页，一页几条数据
     * @return
     */
    public PageResult<List<Map>> findPageBySqlForRownum(String sql, List paramList,Page pageConfig){
        //通过前台传来的currentPage：当前第几页;pageSize：每页多少数据，
        //计算出获取所有数据当中改的范围值，bigNum:范围最大值，smallNum:范围最小值
//        int bigNum = Integer.parseInt(pageConfig.get("currentPage").toString())*Integer.parseInt(pageConfig.get("pageSize").toString())+1;
//        int smallNum = (Integer.parseInt(pageConfig.get("currentPage").toString())-1)*Integer.parseInt(pageConfig.get("pageSize").toString());
        //获取数据的总数
        String countSql = "select count(*) from ("+sql+")";
        String totalCount = this.getOnlyValueBySql(countSql,paramList.toArray());
        if(Util.isEoN(totalCount)){
            totalCount = "0";
        }
        //判断前台是否有传多级排序
        if(pageConfig.getOrders().length>0){
//            JSONArray sorts = pageConfig.getJSONArray("sorts");
//            String orderBySql = " order by ";
//            if(sorts.size()>0){
//                for (int i = 0; i < sorts.size(); i++) {
//                    JSONObject sort = sorts.getJSONObject(i);
//                    orderBySql = orderBySql +" " +sort.get("field")+" "+sort.get("direction")+",";
//                }
//            }
            //orderBySql = orderBySql.substring(0,orderBySql.length()-1);
            sql = "select * from ("+ sql +") where 1=1 order by " + pageConfig.getOrderStr();
        }
        //获取当前分页下的数据
        sql = "select rownum,levelone.* from(select leveltwo.*,rownum rn from (" +sql+") leveltwo where rownum < ?) levelone where levelone.rn > ?";
        paramList.add(pageConfig.getEndIndex());
        paramList.add(pageConfig.getStartIndex());
        //得出数据结果
        List<Map> list  = this.dbHelper.getRows(sql, paramList.toArray());
        return  PageResult.data(list,Long.parseLong(totalCount));// new Page<Map>(list, Integer.parseInt(totalCount));
    }



    /**
     * 【分页】---通过sql语句来进行分页处理 for 序号
     *
     * @param sql			组合好的sql语句（针对只有一个from的sql，多个from会报错）
     * @param paramList		where后跟的条件集合
     * @param pageConfig	分页的配置：第几页，一页几条数据
     * @return
     */
    public PageResult<List<Map>> findPageBySqlForRownumArs(String sql, List paramList, Page pageConfig){
        //通过前台传来的currentPage：当前第几页;pageSize：每页多少数据，
        //计算出获取所有数据当中改的范围值，bigNum:范围最大值，smallNum:范围最小值
//        int bigNum = Integer.parseInt(pageConfig.get("currentPage").toString())*Integer.parseInt(pageConfig.get("pageSize").toString())+1;
//        int smallNum = (Integer.parseInt(pageConfig.get("currentPage").toString())-1)*Integer.parseInt(pageConfig.get("pageSize").toString());
        //获取数据的总数
        String countSql = "select count(*) from ("+sql+")";
        String totalCount = this.dbHelper.getOnlyStringValue(countSql,paramList.toArray());
        if(Util.isEoN(totalCount)){
            totalCount = "0";
        }
        //判断前台是否有传多级排序
        if(pageConfig.getOrders().length>0){
//            JSONArray sorts = pageConfig.getJSONArray("sorts");
//            String orderBySql = " order by ";
//            if(sorts.size()>0){
//                for (int i = 0; i < sorts.size(); i++) {
//                    JSONObject sort = sorts.getJSONObject(i);
//                    orderBySql = orderBySql +" " +sort.get("field")+" "+sort.get("direction")+",";
//                }
//            }
//            orderBySql = orderBySql.substring(0,orderBySql.length()-1);
            sql = "select * from ("+ sql +") where 1=1 order by" + pageConfig.getOrderStr();
        }
        //获取当前分页下的数据
        sql = "select rownum,levelone.* from(select leveltwo.*,rownum rn from (" +sql+") leveltwo where rownum < ?) levelone where levelone.rn > ?";
        paramList.add(pageConfig.getEndIndex());
        paramList.add(pageConfig.getStartIndex());
        //得出数据结果
        List<Map> list  = this.dbHelper.getRows(sql, paramList.toArray());
        return  PageResult.data(list,Long.parseLong(totalCount)); //new Page<Map>(list, Integer.parseInt(totalCount));
    }

}
