package cn.topcheer.halberd.app.biz.sql;

import cn.hutool.core.map.CaseInsensitiveMap;
import cn.topcheer.halberd.app.api.framework.dto.api.PageDTO;
import cn.topcheer.halberd.app.api.framework.dto.api.Result;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiResponse;
import cn.topcheer.halberd.app.api.framework.enumerate.FieldType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlHelper {

    private static final Map<String, FieldType> TYPE_MAPPER = new HashMap<>();
    static {
        TYPE_MAPPER.put("BigDecimal", FieldType.Double);
    }

    private static Logger log = LoggerFactory.getLogger(SqlHelper.class);

    public static Result<List<AmApiResponse>> getResponseInfos(String selectSql, List<Object> params,
                                                               DataSource dataSource) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(selectSql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            if (params != null && params.size() > 0) {
                int idx = 1;
                for (Object aParam : params) {

                    statement.setObject(idx, aParam);
                    idx++;

                }
            }

            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();

            List<AmApiResponse> responses = new ArrayList<>();
            if (resultSet.next()) {
                for (int i = 1; i <= count; i++) {
                    String typeName = metaData.getColumnClassName(i);
                    typeName = typeName.substring(typeName.lastIndexOf(".") + 1);
                    AmApiResponse response = new AmApiResponse();
                    response.setName(metaData.getColumnLabel(i));
                    response.setBindField(metaData.getColumnLabel(i));
                    response.setSample(resultSet.getObject(i) == null ? "" : resultSet.getObject(i).toString());
                    FieldType type = FieldType.String;

                    try {
                        if (TYPE_MAPPER.containsKey(typeName)) {
                            type = TYPE_MAPPER.get(typeName);
                        } else {
                            type = FieldType.valueOf(typeName);
                        }
                    } catch (IllegalArgumentException e) {
                        log.warn("type no match:"+typeName, e);
                    }
                    response.setType(type);
                    responses.add(response);
                }
            }
            return Result.successOf(responses);

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Result.failOf(
                    "AmApiService selectSQL方法错误:" + e.getSQLState() + "," + e.getErrorCode() + ":" + e.getMessage());
        } finally {
            try {
                if (statement != null && !statement.isClosed())
                    statement.close();

                if (connection != null && !connection.isClosed())
                    connection.close();

            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }

    }

    public static Result<PageDTO<Map<String, Object>>> selectSQL(String select, List<Object> params,
                                                                 DataSource dataSource, PageDTO<Map<String, Object>> page, BiPredicate<ResultSet, Integer> filter) {
        Connection connection = null;
        PreparedStatement statement = null;
        int start = (page.getCurrentPage() - 1) * page.getPageSize();
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(select, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            if (params != null && params.size() > 0) {
                int idx = 1;
                for (Object aParam : params) {

                    statement.setObject(idx, aParam);
                    idx++;

                }
            }

            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();

            List<Map<String, Object>> list = new ArrayList<>();
            if (start > 0) {
                resultSet.absolute(start);
            }

            int r = 1;
            while ((r <= page.getPageSize()) && resultSet.next()) {
                Map<String, Object> data = new CaseInsensitiveMap<>();
                for (int i = 1; i <= count; i++) {
                    if (filter == null || !filter.test(resultSet, i)) {
                        data.put(metaData.getColumnLabel(i),
                                resultSet.getObject(i) == null ? "" : resultSet.getObject(i).toString());
                    }

                }
                list.add(data);
            }

            if (!resultSet.isLast()) {
                resultSet.last();
            }
            int size = resultSet.getRow();
            PageDTO<Map<String, Object>> pageDTO = PageDTO.of(start, page.getPageSize(), size);
            pageDTO.setRows(list);
            return Result.successOf(pageDTO);

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return Result.failOf(
                    "AmApiService selectSQL方法错误:" + e.getSQLState() + "," + e.getErrorCode() + ":" + e.getMessage());
        } finally {
            try {
                if (statement != null && !statement.isClosed())
                    statement.close();

                if (connection != null && !connection.isClosed())
                    connection.close();

            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    // 默认最大行数
    private final static int DEFAULT_MAX_ROWS = 10000;

    private final static String NEW_PARAM_REGEX = "\\{\\:([0-9a-zA-Z_]+)\\}";

    public static Result<PageDTO<Map<String, Object>>> selectSQL(String select, List<Object> params,
            DataSource dataSource) {

        PageDTO<Map<String, Object>> page = PageDTO.of(1, DEFAULT_MAX_ROWS);
        return selectSQL(select, params, dataSource, page, null);
    }

    /**
     * 将sql语句中的{:参数}替换成?,并返回与之对应的List<Object> 的参数集合
     */
    public static ParameterizationSql getParameterizationSql(String sql, Map<String, Object> params) {
        return getParameterizationSql(sql, NEW_PARAM_REGEX, 1, params);
    }

    /**
     * 将sql语句中的占位参数替换成?,并将Map<String, Object>参数集转换成List<Object> 的参数集
     */
    public static ParameterizationSql getParameterizationSql(String sql, String paramRegex, int paramNameGroupNum,
            Map<String, Object> params) {

        List<Object> sqlparams = new ArrayList<Object>();
        String retSql = replaceEx(sql, paramRegex, (t, i) -> {
            String key = t.group(paramNameGroupNum);
            sqlparams.add(params.get(key));
            return "?";
        });

        return new ParameterizationSql(retSql, sqlparams);

    }

    /**
     * 将原字符串中匹配正则表达式的值替换成新值,同时，每次匹配时，还可以做一些其他事情
     * 
     * @param str                                             原字符
     * @param regex                                           用来匹配的正则表达式
     * @param replace，替换函数，传入匹配的Matcher和当前匹配次数(第一次值为1),返回替换结果
     * @return
     */
    public static String replaceEx(String str, String regex, @NotNull BiFunction<Matcher, Integer, String> replace) {

        if (str == null) {
            return str;
        }

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (m.find()) {
            i++;
            m.appendReplacement(sb, replace.apply(m, i));

        }
        m.appendTail(sb);
        return sb.toString().replaceAll(regex, regex);

    }

    @Data
    @AllArgsConstructor
    public static class ParameterizationSql {

        private String sql;
        private List<Object> params;

    }

}
