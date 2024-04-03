package cn.topcheer.pms2.common.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JdbcUrlUtil {

    /**
     * todo:完善url的解析
     * @param url
     */
    public static UrlInfo resolveUrl(String url){
        Pattern p = Pattern.compile("jdbc:(?<db>\\w+):.*((//)|@)(?<host>.+):(?<port>\\d+)(/|(;DatabaseName=)|:)(?<dbName>\\w+)\\??.*");
        Matcher m = p.matcher(url);
        if(m.find()) {
            return UrlInfo.builder()
                    .db(m.group("db"))
                    .host(m.group("host"))
                    .port(m.group("port"))
                    .dbName(m.group("dbName"))
                    .build();
        }else {
            throw new IllegalStateException("未能解析url:"+url);
        }
    }


    @Getter
    @Setter
    @Builder
    public static class UrlInfo{
        private String db;
        private String host;
        private String port;
        private String dbName;
    }


    public static void main(String[] args){
        UrlInfo info1 = resolveUrl("jdbc:mysql://127.0.0.1:3306/databaseName");
        UrlInfo info2 = resolveUrl("jdbc:microsoft:sqlserver://127.0.0.1:1433;DatabaseName=databaseName");
        UrlInfo info3 = resolveUrl("jdbc:oracle:thin:@localhost:1521:dbName");
        UrlInfo info4 = resolveUrl("jdbc:mysql://10.10.218.198:3306/jqzjk");
        info4.getDb();

    }
}
