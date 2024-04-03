package cn.topcheer.halberd.app.api.framework.constant;

import com.alibaba.druid.DbType;
import com.alibaba.druid.util.JdbcConstants;

public class DataStoreType {

    public enum Type{

        MYSQL("MYSQL",0),
        ORACLE("ORACLE",1),
        DORIS("DORIS", 2),
        SQLSERVER("SQLSERVER", 3),
        POSTGRESQL("POSTGRESQL", 4),
        DM("DM", 5),
        ;

        private String name;
        private int code;


        Type(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }


        public static Type getType(String typeString) {
            for (Type type :Type.values()){
                if(type.getName().equalsIgnoreCase(typeString)){
                    return type;
                }
            }
            return null;
        }

        public static DbType toDruidDbType(String dbType){
            if(Type.MYSQL.getName().equals(dbType) || Type.DORIS.getName().equals(dbType)){
                return JdbcConstants.MYSQL;
            } else if (Type.ORACLE.getName().equals(dbType)) {
                return JdbcConstants.ORACLE;
            } else if (Type.SQLSERVER.getName().equals(dbType)) {
                return JdbcConstants.SQL_SERVER;
            } else if (Type.POSTGRESQL.getName().equals(dbType)) {
                return JdbcConstants.POSTGRESQL;
            } else {
                throw new UnsupportedOperationException();
            }
        }






    }





}
