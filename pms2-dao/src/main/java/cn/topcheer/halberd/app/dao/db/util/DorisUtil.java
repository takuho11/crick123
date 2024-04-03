package cn.topcheer.halberd.app.dao.db.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class DorisUtil {

    private DorisUtil(){}

    public static boolean isDorisDataSource(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT @@VERSION_COMMENT")) {
                return rs.next() &&
                        Optional.ofNullable(rs.getString(1))
                                .map(s -> s.toLowerCase().contains("doris"))
                                .orElse(false);
            }
        }
    }


    public static boolean isDorisDataSource(DataSource dataSource) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            return isDorisDataSource(connection);
        }
    }
}
