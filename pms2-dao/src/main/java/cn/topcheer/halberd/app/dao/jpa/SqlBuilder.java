package cn.topcheer.halberd.app.dao.jpa;

import lombok.Data;
import org.springblade.core.tool.utils.Func;

import java.util.*;

@Data
public class SqlBuilder {
    private List<String> fields=new ArrayList<>();
    private List<String> paramsNames=new ArrayList<>();
    private List<Object> paramsValues=new ArrayList<>();

    private List<String> selectFields=new ArrayList<>();
    private List<String> joinList=new ArrayList<>();
    private StringBuffer orderStr=new StringBuffer();

    private String tableName;
    private String alias=" t ";

    private String concatStr =" and ";


    private StringBuilder sqlList=new StringBuilder();
    private int index=0;


    public static  <T> SqlBuilder builder(String tableName,String alias){
        SqlBuilder hqlBuilder=  new SqlBuilder();
        hqlBuilder.setTableName(tableName);
        hqlBuilder.setAlias(alias);
        return hqlBuilder;
    }


    public SqlBuilder and()
    {
        this.sqlList.append(this.concatStr).append(" (");
        this.concatStr =" and ";
        return this;
    }

    public SqlBuilder endAnd(){
        this.concatStr =" and ";
        this.sqlList.append(")");
        return this;
    }

    public SqlBuilder or()
    {
        this.sqlList.append(this.concatStr).append(" (");
        this.concatStr =" or ";
        return this;
    }

    public SqlBuilder endOr(){
        this.sqlList.append(")");
        return this;
    }

    public SqlBuilder addParam(String paramName, Object paramValue)
    {
        this.paramsNames.add(paramName);
        this.paramsValues.add(paramValue);
        return this;
    }

    public SqlBuilder neq(boolean condition, String paramName, Object paramValue)
    {
        if(condition)
            return op("<>",paramName,paramValue);
        else
            return this;
    }
    public SqlBuilder neq(String paramName, Object paramValue)
    {
        return op("<>",paramName,paramValue);
    }

    public SqlBuilder eq(boolean condition, String paramName, Object paramValue)
    {
        if(condition)
            return op("=",paramName,paramValue);
        else
            return this;
    }

    public SqlBuilder eq(String paramName, Object paramValue)
    {
        return op("=",paramName,paramValue);
    }



    public SqlBuilder like(boolean condition, String paramName, Object paramValue)
    {
        if(condition)
            return op(" like ",paramName,paramValue);
        else
            return this;
    }

    public SqlBuilder like(String paramName, Object paramValue)
    {
        return op(" like ",paramName,paramValue);
    }

    public SqlBuilder inSql(boolean condition, String paramName,String sql )
    {
        if(condition)
            return opValueSql(" in ",paramName,sql);
        else
            return this;
    }

    public SqlBuilder in(boolean condition, String paramName, Collection paramValue)
    {
        if(condition)
            return op(" in ",paramName,paramValue);
        else
            return this;
    }

    public SqlBuilder in(String paramName, Collection paramValue)
    {
        return op(" in ",paramName,paramValue);
    }

    public SqlBuilder notIn(boolean condition, String paramName, Collection paramValue)
    {
        if(condition)
            return op("  not in ",paramName,paramValue);
        else
            return this;
    }

    public SqlBuilder notIn(String paramName, Collection paramValue)
    {
        return op(" not in ",paramName,paramValue);
    }


    public SqlBuilder gt(boolean condition, String paramName, Object paramValue)
    {
        if(condition)
            return op(" > ",paramName,paramValue);
        else
            return this;
    }

    public SqlBuilder gt(String paramName, Object paramValue)
    {
        return op(" > ",paramName,paramValue);
    }


    public SqlBuilder ge(boolean condition, String paramName, Object paramValue)
    {
        if(condition)
            return op(" >= ",paramName,paramValue);
        else
            return this;
    }
    public SqlBuilder ge(String paramName, Object paramValue)
    {
        return op(" >= ",paramName,paramValue);
    }

    public SqlBuilder le(boolean condition, String paramName, Object paramValue)
    {
        if(condition)
            return op(" <= ",paramName,paramValue);
        else
            return this;
    }
    public SqlBuilder le(String paramName, Object paramValue)
    {
        return op("  <= ",paramName,paramValue);
    }

    public SqlBuilder lt(String paramName, Object paramValue)
    {
        return op("  < ",paramName,paramValue);
    }

    public SqlBuilder between(boolean condition, String paramName, Object paramValue1, Object paramValue2)
    {
        if(!condition)
            return this;
        String key1= "PB1"+paramsNames.size();
        String key2= "PB2"+paramsNames.size();
        if(this.sqlList.length()>0)
        {
            this.sqlList.append(this.concatStr);
        }
        this.sqlList.append( paramName).append(" between ").append(":").append(key1).append(" and :").append(key2).append(" ");
        this.fields.add(paramName);
        this.paramsNames.add(key1);
        this.paramsValues.add(paramValue1);
        this.paramsNames.add(key2);
        this.paramsValues.add(paramValue2);
        return this;
    }
    public SqlBuilder between(String paramName, Object paramValue1, Object paramValue2)
    {
        String key1= "PB1"+paramsNames.size();
        String key2= "PB2"+paramsNames.size();
        if(this.sqlList.length()>0)
        {
            this.sqlList.append(this.concatStr);
        }
        this.sqlList.append( paramName).append(" between ").append(":").append(key1).append(" and :").append(key2).append(" ");
        this.fields.add(paramName);
        this.paramsNames.add(key1);
        this.paramsValues.add(paramValue1);
        this.paramsNames.add(key2);
        this.paramsValues.add(paramValue2);
        return this;
    }

    private SqlBuilder op(String opStr, String paramName, Object paramValue)
    {
        String key= "P"+paramsNames.size();

        if(this.sqlList.length()>0)
        {
            this.sqlList.append(this.concatStr);
        }
        this.sqlList.append( paramName).append(opStr).append(":").append(key).append(" ");
        this.fields.add(paramName);
        this.paramsNames.add(key);
        this.paramsValues.add(paramValue);
        return this;
    }
    private SqlBuilder opValueSql(String opStr, String paramName, String sqlValue)
    {
        String key= "P"+paramsNames.size();

        if(this.sqlList.length()>0)
        {
            this.sqlList.append(this.concatStr);
        }
        this.sqlList.append( paramName).append(opStr).append("(").append(sqlValue).append(") ");
        return this;
    }

    public String getSql(){

        String select="";
        if(this.selectFields.size()>0)
        {
            select=" select "+ Func.join(this.selectFields,",")+" ";
        }
        else
        {
            select= " select " + this.alias + ".* ";
        }

        if(this.sqlList.length()>0)
        {
            return select+ " from "+this.tableName+" "+this.alias + " "+  Func.join(this.joinList," ") +"   where "+this.sqlList.toString() + this.orderStr;
        }
        else
        {
            return select+ " from " + this.tableName + " "+this.alias+ " "+  Func.join(this.joinList," ")+"   " + this.orderStr;
        }
    }

    /**
     * 添加排序处理
     * @param orderField
     * @param direction
     * @return
     */
    public SqlBuilder addOrder(String orderField, String direction)
    {
        if(this.orderStr.length()==0)
        {
            this.orderStr.append(" order by ").append(orderField).append(" ").append(direction);
        }
        else
        {
            this.orderStr.append(",").append(orderField).append(" ").append(direction);
        }
        return this;
    }

    /**
     * 增加选择字段  默认： 选择全部
     * @param selectFields
     * @return
     */
    public SqlBuilder addSelectList(String... selectFields)
    {
        if(selectFields!=null)
        {
            this.selectFields=Arrays.asList(selectFields);// .list( Arrays.stream(selectFields).iterator()); // Arrays.stream(selectFields).map((str));
        }
        else
        {
            this.selectFields=new ArrayList<>();
        }
        return this;
    }

    /**
     * 增加字段
     * @param fieldExpress  例如： _name as name
     * @return
     */
    public SqlBuilder addSelect(String fieldExpress)
    {
        this.selectFields.add(fieldExpress);
        return this;
    }


    /**
     * 增加关联   例如：  addJoin("t.sysUser","u"," inner` join"
     * @param joinExpress    inner join table1 t2 on t1.AId=t2.id   包括了表别名和等条件
     * @return
     */
    public SqlBuilder addJoin( String joinExpress)
    {

        return addJoin(true,joinExpress);
    }

    /**
     * 增加关联   例如：  addJoin("t.sysUser","u"," inner` join"
     * @param condition  例如：是否增加的条件
     * @param joinExpress    inner join table1 t2 on t1.AId=t2.id   包括了表别名和等条件
     * @return
     */
    public SqlBuilder addJoin(Boolean condition,String joinExpress)
    {
        if(condition) {
            this.joinList.add(joinExpress);
        }
        return this;
    }


}
