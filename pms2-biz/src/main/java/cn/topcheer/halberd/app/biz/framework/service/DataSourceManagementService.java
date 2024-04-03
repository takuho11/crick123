package cn.topcheer.halberd.app.biz.framework.service;

import cn.topcheer.halberd.app.api.framework.dto.api.AmDataSourceDTO;
import cn.topcheer.halberd.app.api.framework.entity.api.AmDataSource;
import cn.topcheer.halberd.app.api.framework.vo.DBResult;
import cn.topcheer.halberd.app.dao.db.DataSourceAndType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.support.Query;

import javax.sql.DataSource;
import java.util.List;

public interface DataSourceManagementService {

    AmDataSource detail(AmDataSourceDTO amDataSourceDTO);

    IPage<AmDataSource> page(AmDataSourceDTO amDataSourceDTO, Query query);

    List<AmDataSource> findAllEnabled(AmDataSourceDTO amDataSourceDTO);

    boolean create(AmDataSourceDTO amDataSourceDTO);

    boolean updateById(AmDataSourceDTO amDataSourceDTO);

    boolean removeById(Long id);

    DataSource getDataSource(String id);

    DataSource getDataSourceByName(String name);

    DataSourceAndType getDataSourceAndType(String id);

    DataSourceAndType getDataSourceAndTypeByName(String name);

    boolean changeDataSourceStatus(Long id);

    boolean testConnection(String url, String user, String password);

    List<String>  getDataSourceType();

    DBResult getDbInfoByPath(List<String> path, String type);
}
