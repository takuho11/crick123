package cn.topcheer.halberd.app.dao.jpa;

import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.lang.func.LambdaUtil;
import lombok.Data;
import org.springblade.core.tool.utils.Func;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
public class HqlBuilder<T> {
    private List<String> fields = new ArrayList<>();
    private List<String> paramsNames = new ArrayList<>();
    private List<Object> paramsValues = new ArrayList<>();

    private List<String> selectFields = new ArrayList<>();
    private List<String> joinList = new ArrayList<>();
    private StringBuffer orderStr = new StringBuffer();

    private String alias = " t ";

    private String concatStr = " and ";


    private StringBuilder sqlList = new StringBuilder();
    private int index = 0;

    public static <T> HqlBuilder<T> builder() {
        return new HqlBuilder<T>();
    }

    public static <T> HqlBuilder<T> builder(Class<T> tClass) {
        return new HqlBuilder<T>();
    }

    public static <T> HqlBuilder<T> builder(String alias) {

        HqlBuilder hqlBuilder = new HqlBuilder<T>();
        hqlBuilder.setAlias(alias);
        return hqlBuilder;
    }


    public HqlBuilder<T> and() {
        this.sqlList.append(this.concatStr).append(" (");
        this.concatStr = " and ";
        return this;
    }

    public HqlBuilder<T> endAnd() {
        this.concatStr = " and ";
        this.sqlList.append(")");
        return this;
    }

    public HqlBuilder<T> or() {
        this.sqlList.append(this.concatStr).append(" (");
        this.concatStr = " or ";
        return this;
    }

    public HqlBuilder<T> endOr() {
        this.sqlList.append(")");
        return this;
    }


    public HqlBuilder<T> addOr() {
        this.concatStr = " or ";
        return this;
    }

    public HqlBuilder<T> addLeftBracket() {
        this.sqlList.append("(");
        this.concatStr = " ";
        return this;
    }

    public HqlBuilder<T> addRightBracket() {
        this.sqlList.append(")");
        this.concatStr = " and ";
        return this;
    }

    public HqlBuilder<T> addParam(String paramName, Object paramValue) {
        this.paramsNames.add(paramName);
        this.paramsValues.add(paramValue);
        return this;
    }

    public HqlBuilder<T> addParam(Func1<T, Object> func1, Object paramValue) {
        this.paramsNames.add(LambdaUtil.getFieldName(func1));
        this.paramsValues.add(paramValue);
        return this;
    }

    public HqlBuilder<T> neq(boolean condition, String paramName, Object paramValue) {
        if (condition)
            return op("<>", paramName, paramValue);
        else
            return this;
    }

    public HqlBuilder<T> neq(boolean condition, Func1<T, Object> func1, Object paramValue) {
        if (condition)
            return op("<>", LambdaUtil.getFieldName(func1), paramValue);
        else
            return this;
    }

    public HqlBuilder<T> neq(String paramName, Object paramValue) {
        return neq(true, paramName, paramValue);
    }


    public HqlBuilder<T> neq(Func1<T, Object> func1, Object paramValue) {
        return neq(true, LambdaUtil.getFieldName(func1), paramValue);
    }


    public HqlBuilder<T> eq(boolean condition, String paramName, Object paramValue) {
        if (condition)
            return op("=", paramName, paramValue);
        else
            return this;
    }

    public HqlBuilder<T> eq(boolean condition, Func1<T, Object> func1, Object paramValue) {
        if (condition)
            return op("=", LambdaUtil.getFieldName(func1), paramValue);
        else
            return this;
    }

    public HqlBuilder<T> eq(String paramName, Object paramValue) {
        return eq(true, paramName, paramValue);
    }


    public HqlBuilder<T> eq(Func1<T, Object> func1, Object paramValue) {
        return eq(true, LambdaUtil.getFieldName(func1), paramValue);
    }


    public HqlBuilder<T> like(boolean condition, String paramName, Object paramValue) {
        if (condition)
            return op(" like ", paramName, paramValue);
        else
            return this;
    }

    public HqlBuilder<T> like(boolean condition, Func1<T, Object> func1, Object paramValue) {
        if (condition)
            return op(" like ", LambdaUtil.getFieldName(func1), paramValue);
        else
            return this;
    }


    public HqlBuilder<T> like(String paramName, Object paramValue) {
        return like(true, paramName, paramValue);
    }

    public HqlBuilder<T> like(Func1<T, Object> func1, Object paramValue) {
        return like(true, LambdaUtil.getFieldName(func1), paramValue);
    }

    public HqlBuilder<T> in(boolean condition, String paramName, Collection paramValue) {
        if (condition)
            return op(" in ", paramName, paramValue);
        else
            return this;
    }

    public HqlBuilder<T> in(boolean condition, Func1<T, Object> func1, Collection paramValue) {
        if (condition)
            return op(" in ", LambdaUtil.getFieldName(func1), paramValue);
        else
            return this;
    }

    public HqlBuilder<T> in(String paramName, Collection paramValue) {
        return in(true, paramName, paramValue);
    }

    public HqlBuilder<T> in(Func1<T, Object> func1, Collection paramValue) {
        return in(true, func1, paramValue);
    }

    public HqlBuilder<T> notIn(boolean condition, String paramName, Collection paramValue) {
        if (condition)
            return op("  not in ", paramName, paramValue);
        else
            return this;
    }

    public HqlBuilder<T> notIn(boolean condition, Func1<T, Object> func1, Collection paramValue) {
        if (condition)
            return op("  not in ", LambdaUtil.getFieldName(func1), paramValue);
        else
            return this;
    }

    public HqlBuilder<T> notIn(String paramName, Collection paramValue) {
        return notIn(true, paramName, paramValue);
    }

    public HqlBuilder<T> notIn(Func1<T, Object> func1, Collection paramValue) {
        return notIn(true, LambdaUtil.getFieldName(func1), paramValue);
    }

    public HqlBuilder<T> gt(boolean condition, String paramName, Object paramValue) {
        if (condition)
            return op(" > ", paramName, paramValue);
        else
            return this;
    }

    public HqlBuilder<T> gt(boolean condition, Func1<T, Object> func1, Object paramValue) {
        if (condition)
            return op(" > ", LambdaUtil.getFieldName(func1), paramValue);
        else
            return this;
    }

    public HqlBuilder<T> gt(String paramName, Object paramValue) {
        return gt(true, paramName, paramValue);
    }

    public HqlBuilder<T> gt(Func1<T, Object> func1, Object paramValue) {
        return gt(true, LambdaUtil.getFieldName(func1), paramValue);
    }

    public HqlBuilder<T> ge(boolean condition, String paramName, Object paramValue) {
        if (condition)
            return op(" >= ", paramName, paramValue);
        else
            return this;
    }

    public HqlBuilder<T> ge(boolean condition, Func1<T, Object> func1, Object paramValue) {
        if (condition)
            return op(" >= ", LambdaUtil.getFieldName(func1), paramValue);
        else
            return this;
    }

    public HqlBuilder<T> ge(String paramName, Object paramValue) {
        return ge(true, paramName, paramValue);
    }

    public HqlBuilder<T> ge(Func1<T, Object> func1, Object paramValue) {
        return ge(true, LambdaUtil.getFieldName(func1), paramValue);
    }

    public HqlBuilder<T> le(boolean condition, String paramName, Object paramValue) {
        if (condition)
            return op(" <= ", paramName, paramValue);
        else
            return this;
    }

    public HqlBuilder<T> le(boolean condition, Func1<T, Object> func1, Object paramValue) {
        if (condition)
            return op(" <= ", LambdaUtil.getFieldName(func1), paramValue);
        else
            return this;
    }

    public HqlBuilder<T> le(String paramName, Object paramValue) {
        return le(true, paramName, paramValue);
    }

    public HqlBuilder<T> le(Func1<T, Object> func1, Object paramValue) {
        return le(true, LambdaUtil.getFieldName(func1), paramValue);
    }

    public HqlBuilder<T> lt(boolean condition, String paramName, Object paramValue) {
        if (condition) {
            return op("  < ", paramName, paramValue);
        } else {
            return this;
        }
    }

    public HqlBuilder<T> lt(boolean condition, Func1<T, Object> func1, Object paramValue) {
        return lt(condition, LambdaUtil.getFieldName(func1), paramValue);
    }

    public HqlBuilder<T> lt(String paramName, Object paramValue) {
        return lt(true, paramName, paramValue);
    }

    public HqlBuilder<T> lt(Func1<T, Object> func1, Object paramValue) {
        return lt(true, LambdaUtil.getFieldName(func1), paramValue);
    }

    public HqlBuilder<T> between(boolean condition, String paramName, Object paramValue1, Object paramValue2) {
        if (!condition)
            return this;
        String key1 = "PB1" + paramsNames.size();
        String key2 = "PB2" + paramsNames.size();
        if (this.sqlList.length() > 0) {
            this.sqlList.append(this.concatStr);
        }
        this.sqlList.append(paramName).append(" between ").append(":").append(key1).append(" and :").append(key2).append(" ");
        this.fields.add(paramName);
        this.paramsNames.add(key1);
        this.paramsValues.add(paramValue1);
        this.paramsNames.add(key2);
        this.paramsValues.add(paramValue2);
        return this;
    }

    public HqlBuilder<T> between(boolean condition, Func1<T, Object> func1, Object paramValue1, Object paramValue2) {
        return between(condition, LambdaUtil.getFieldName(func1), paramValue1, paramValue2);
    }

    public HqlBuilder<T> between(String paramName, Object paramValue1, Object paramValue2) {
        return between(true, paramName, paramValue1, paramValue2);
    }

    public HqlBuilder<T> between(Func1<T, Object> func1, Object paramValue1, Object paramValue2) {
        return between(true, LambdaUtil.getFieldName(func1), paramValue1, paramValue2);
    }

    private HqlBuilder<T> op(String opStr, String paramName, Object paramValue) {
        String key = "P" + paramsNames.size();

        if (this.sqlList.length() > 0) {
            this.sqlList.append(this.concatStr);
        }
        this.sqlList.append(paramName).append(opStr).append(":").append(key).append(" ");
        this.fields.add(paramName);
        this.paramsNames.add(key);
        this.paramsValues.add(paramValue);
        return this;
    }

    private HqlBuilder<T> opNull(String opStr, String paramName) {
        if (this.sqlList.length() > 0) {
            this.sqlList.append(this.concatStr);
        }

        this.sqlList.append(paramName).append(opStr);
        return this;
    }

    public String getCountHql(Class className) {

        if (this.sqlList.length() > 0) {
            return " from " + className.getName() + " " + this.alias + " " + Func.join(this.joinList, " ") + "   where " + this.sqlList.toString() + this.orderStr;
        } else {
            return " from " + className.getName() + " " + this.alias + " " + Func.join(this.joinList, " ") + "   " + this.orderStr;
        }
    }


    public String getHql(Class className) {

        String select = "";
        if (this.selectFields.size() > 0) {
            select = " select new map(" + Func.join(this.selectFields, ",") + ") ";
        }

        if (this.sqlList.length() > 0) {
            return select + " from " + className.getName() + " " + this.alias + " " + Func.join(this.joinList, " ") + "   where " + this.sqlList.toString() + this.orderStr;
        } else {
            return select + " from " + className.getName() + " " + this.alias + " " + Func.join(this.joinList, " ") + "   " + this.orderStr;
        }
    }

    /**
     * 添加排序处理
     *
     * @param orderField
     * @param direction
     * @return
     */
    public HqlBuilder<T> addOrder(String orderField, String direction) {
        if (this.orderStr.length() == 0) {
            this.orderStr.append(" order by ").append(orderField).append(" ").append(direction);
        } else {
            this.orderStr.append(",").append(orderField).append(" ").append(direction);
        }
        return this;
    }

    public HqlBuilder<T> addOrder(Func1<T, Object> func1, String direction) {
        return addOrder(LambdaUtil.getFieldName(func1), direction);
    }

    /**
     * 增加选择字段  默认： 选择全部
     *
     * @param selectFields
     * @return
     */
    public HqlBuilder<T> addSelectList(String... selectFields) {
        if (selectFields != null) {
            this.selectFields = Arrays.asList(selectFields);
        } else {
            this.selectFields = new ArrayList<>();
        }
        return this;
    }

    /**
     * 增加字段
     *
     * @param fieldExpress 例如： _name as name
     * @return
     */
    public HqlBuilder addSelect(String fieldExpress) {
        this.selectFields.add(fieldExpress);
        return this;
    }


//    /**
//     * 增加选择字段  默认： 选择全部
//     * @param selectFields
//     * @return
//     */
//    public HqlBuilder<T> addSelect(List<Func1<T,Object>> selectFields)
//    {
//        if(selectFields!=null)
//        {
//            this.selectFields=selectFields.stream().map(f->LambdaUtil.getFieldName(f)).collect(Collectors.toList());
//        }
//        else
//        {
//            this.selectFields=new ArrayList<>();
//        }
//        return this;
//    }

    /**
     * 增加关联   例如：  addJoin("t.sysUser","u"," inner` join"
     *
     * @param joinName 例如： t.sysUser
     * @param alias    u
     * @param joinOp   inner join,left join , right join ,outer join 等
     * @return
     */
    public HqlBuilder<T> addJoin(String joinName, String alias, String joinOp) {
        this.joinList.add((Func.isEmpty(joinOp) ? "inner join" : joinOp) + " " + joinName + " " + alias + " ");
        return this;
    }


    public HqlBuilder<T> isNull(boolean condition, String paramName) {
        if (condition) {
            return opNull(" is null", paramName);
        } else {
            return this;
        }
    }

    public HqlBuilder<T> isNull(boolean condition, Func1<T, Object> func1) {
        if (condition) {
            return opNull(" is null", LambdaUtil.getFieldName(func1));
        } else {
            return this;
        }
    }

    public HqlBuilder<T> isNull(String paramName) {
        return isNull(true, paramName);
    }


    public HqlBuilder<T> isNull(Func1<T, Object> func1) {
        return isNull(true, LambdaUtil.getFieldName(func1));
    }


    public HqlBuilder<T> isNotNull(boolean condition, String paramName) {
        if (condition) {
            return opNull(" is not null", paramName);
        } else {
            return this;
        }
    }

    public HqlBuilder<T> isNotNull(boolean condition, Func1<T, Object> func1) {
        if (condition) {
            return opNull(" is not null", LambdaUtil.getFieldName(func1));
        } else {
            return this;
        }
    }

    public HqlBuilder<T> isNotNull(String paramName) {
        return isNotNull(true, paramName);
    }


    public HqlBuilder<T> isNotNull(Func1<T, Object> func1) {
        return isNotNull(true, LambdaUtil.getFieldName(func1));
    }

}
