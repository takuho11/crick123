package cn.topcheer.halberd.app.api.framework.dto.db;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 通过加入spring容器,可以将此handler注入至{@link com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration}中,
 * 并在{@link com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration#sqlSessionFactory(javax.sql.DataSource)}方法中传入至{@link com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean}中,
 * 并在{@link com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean#buildSqlSessionFactory()}方法中
 * 通过调用{@link TypeHandlerRegistry#register(TypeHandler)}注册了此handler
 * @see com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration#MybatisPlusAutoConfiguration(com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties, org.springframework.beans.factory.ObjectProvider, org.springframework.beans.factory.ObjectProvider, org.springframework.beans.factory.ObjectProvider, org.springframework.core.io.ResourceLoader, org.springframework.beans.factory.ObjectProvider, org.springframework.beans.factory.ObjectProvider, org.springframework.beans.factory.ObjectProvider, org.springframework.beans.factory.ObjectProvider, org.springframework.context.ApplicationContext)
*/
@Component
@MappedTypes({DbMetadata.class})
public class DbMetadataTypeHandler extends BaseTypeHandler<DbMetadata> {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DbMetadata parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getJson(mapper));
    }

    @Override
    public DbMetadata getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new DbMetadata(rs.getString(columnName));
    }

    @Override
    public DbMetadata getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new DbMetadata(rs.getString(columnIndex));
    }

    @Override
    public DbMetadata getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new DbMetadata(cs.getString(columnIndex));
    }
}
