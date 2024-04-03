package cn.topcheer.halberd.app.biz.framework.service.impl;

import cn.topcheer.halberd.app.api.framework.constant.DataStoreType;
import cn.topcheer.halberd.app.api.framework.dto.api.AmDataSourceDTO;
import cn.topcheer.halberd.app.api.framework.entity.api.AmDataSource;
import cn.topcheer.halberd.app.api.framework.service.api.AmDataSourceService;
import cn.topcheer.halberd.app.api.framework.vo.DBResult;
import cn.topcheer.halberd.app.api.framework.vo.DBTree;
import cn.topcheer.halberd.app.api.framework.vo.TreeNode;
import cn.topcheer.halberd.app.biz.config.DataSourceConfig;
import cn.topcheer.halberd.app.biz.framework.service.DataSourceManagementService;
import cn.topcheer.halberd.app.dao.db.DataSourceAndType;
import cn.topcheer.halberd.app.dao.db.dialect.SqlDialectFactory;
import cn.topcheer.halberd.app.dao.db.util.DorisUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DataSourceManagementServiceImpl implements DataSourceManagementService {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Autowired(required = false)
    private DataSource dataSource;

    @Autowired
    private AmDataSourceService amDataSourceService;

    @Autowired
    @Qualifier("dataCenterEncryptor")
    private StringEncryptor dataCenterEncryptor;


    @Autowired
    @Qualifier("dataSourceStringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    private final Map<String, DataSourceCache> dataSourceMap = new ConcurrentHashMap<>();


    @Override
    public AmDataSource detail(AmDataSourceDTO amDataSourceDTO) {
        return amDataSourceService.getOne(Condition.getQueryWrapper(amDataSourceDTO));
    }

    @Override
    public IPage<AmDataSource> page(AmDataSourceDTO amDataSourceDTO, Query query) {
        QueryWrapper<AmDataSource> qw = Condition.getQueryWrapper(amDataSourceDTO);
        qw.ge(amDataSourceDTO.getStartDate() != null, "create_time", amDataSourceDTO.getStartDate());
        if (amDataSourceDTO.getEndDate() != null) {
            qw.lt("create_time", amDataSourceDTO.getEndDate().plusDays(1));
        }
        qw.eq("is_deleted", 0);
        qw.orderByDesc(" create_time ");

        return amDataSourceService.page(Condition.getPage(query), qw);
    }

    @Override
    public List<AmDataSource> findAllEnabled(AmDataSourceDTO amDataSourceDTO) {
        amDataSourceDTO.setEnabled(true);
        if (StringUtils.isNotBlank(amDataSourceDTO.getType())) {
            amDataSourceDTO.setType(amDataSourceDTO.getType().toUpperCase());
        }
        return amDataSourceService.list(Condition.getQueryWrapper(amDataSourceDTO))
                .stream()
                .sorted(Comparator.comparing(AmDataSource::getId))
                .collect(Collectors.toList());
    }

    private String getDataSourceType(String url, String userName, String password) {
        DbType dbType = JdbcUtils.getDbType(url);
        switch (dbType) {
            case ORACLE:
                return DataStoreType.Type.ORACLE.getName();
            case POSTGRE_SQL:
                return DataStoreType.Type.POSTGRESQL.getName();
            case SQL_SERVER2005:
            case SQL_SERVER:
                return DataStoreType.Type.SQLSERVER.getName();
            case MYSQL: {
                try (Connection conn = DriverManager.getConnection(url, userName, password)) {
                    return DorisUtil.isDorisDataSource(conn) ? DataStoreType.Type.DORIS.getName() : DataStoreType.Type.MYSQL.getName();
                } catch (Exception e) {
                    return DataStoreType.Type.MYSQL.getName();
                }
            }
            case DM :
                return DataStoreType.Type.DM.getName();
            default:
                throw new NotImplementedException("未实现相应类型数据源！");
        }
    }

    @Override
    @Transactional
    public boolean create(AmDataSourceDTO amDataSourceDTO) {
        testConnection(amDataSourceDTO.getUrl(), amDataSourceDTO.getUserName(), amDataSourceDTO.getUserPwd());
        amDataSourceDTO.setEnabled(true);
        amDataSourceDTO.setType(getDataSourceType(amDataSourceDTO.getUrl(), amDataSourceDTO.getUserName(), amDataSourceDTO.getUserPwd()));
        AmDataSource encryptedAmDataSource = encrypt(amDataSourceDTO);
        return amDataSourceService.save(encryptedAmDataSource);
    }

    @Override
    @Transactional
    public boolean updateById(AmDataSourceDTO newAmDataSource) {
        Long id = newAmDataSource.getId();
        AmDataSource oldAmDataSource = amDataSourceService.getOne(
                Wrappers.lambdaQuery(AmDataSource.class)
                        .select(AmDataSource::getId, AmDataSource::getUserPwd, AmDataSource::getName)
                        .eq(AmDataSource::getId, id)
        );
        if (oldAmDataSource == null) {
            throw new ServiceException("数据源" + id + "不存在");
        }
        String password = StringUtils.isBlank(newAmDataSource.getUserPwd()) ? decrypt(oldAmDataSource.getUserPwd()) : newAmDataSource.getUserPwd();
        testConnection(newAmDataSource.getUrl(), newAmDataSource.getUserName(), password);
        newAmDataSource.setType(getDataSourceType(newAmDataSource.getUrl(), newAmDataSource.getUserName(), password));
        removeRedisRecord(newAmDataSource.getId().toString());
        return amDataSourceService.updateById(encrypt(newAmDataSource));
    }

    @Override
    public boolean removeById(Long id) {
        AmDataSource amDataSource = amDataSourceService.getById(id);
        if (amDataSource == null) {
            throw new ServiceException("数据源" + id + "不存在");
        }
        if (Boolean.TRUE.equals(amDataSource.getEnabled())) {
            throw new ServiceException("删除前请先禁用！");
        }
        removeRedisRecord(id.toString());
        return amDataSourceService.removeById(id);
    }


    private DataSourceRecord getRedisRecord(String idStr) {
        return DataSourceRecord.parse(redisTemplate.opsForValue().get("datacenter:datasource:" + idStr));
    }


    private void setRedisRecord(String idStr, DataSourceRecord dataSourceRecord) {
        long minutes = RandomUtils.nextInt(24 * 60 - 30, 24 * 60 + 30);
        redisTemplate.opsForValue().set("datacenter:datasource:" + idStr, dataSourceRecord.toString(), Duration.ofMinutes(minutes));
    }

    private void removeRedisRecord(String idStr) {
        redisTemplate.delete("datacenter:datasource:" + idStr);
    }

    private static final Map<String, Object> lock = new ConcurrentHashMap<>();

    private static Object getInitLock(String idStr) {
        return lock.computeIfAbsent(idStr, s -> new Object());
    }


    private boolean needToFetchDataSourceInfo(String idStr, DataSourceRecord redisRecord) {
        return redisRecord == null
                ||
                (redisRecord.enabled && !dataSourceMap.containsKey(idStr))
                ||
                (redisRecord.enabled && redisRecord.getVersion() > dataSourceMap.get(idStr).getVersion());
    }

    @Override
    public DataSource getDataSource(String idStr) {
        return getDataSourceAndType(idStr).getDruidDataSource();
    }

    @Override
    public DataSource getDataSourceByName(String name) {
        return getDataSourceAndTypeByName(name).getDruidDataSource();
    }

    @Override
    public DataSourceAndType getDataSourceAndType(String idStr) {
        if (StringUtils.isBlank(idStr)) {
            throw new ServiceException("数据源" + idStr + "不存在");
        }
        DataSourceRecord redisRecord = getRedisRecord(idStr);
        if (needToFetchDataSourceInfo(idStr, redisRecord)) {
            synchronized (getInitLock(idStr)) {
                redisRecord = getRedisRecord(idStr);
                if (needToFetchDataSourceInfo(idStr, redisRecord)) {
                    log.info("begin to fetch datasource info from database");
                    AmDataSource amDataSource = amDataSourceService.getById(Long.parseLong(idStr));
                    if (amDataSource == null) {
                        Optional.ofNullable(redisRecord).ifPresent(r -> removeRedisRecord(idStr));
                        Optional.ofNullable(dataSourceMap.remove(idStr)).ifPresent(d -> stop(d.getDataSource()));
                        throw new ServiceException("数据源" + idStr + "不存在");
                    }

                    if (amDataSource.getApproveStatus() != 2) {
                        throw new ServiceException("数据源" + idStr + "不是审批通过的");
                    }

                    DataSourceRecord newRedisRecord = DataSourceRecord.fromEntity(amDataSource);
                    setRedisRecord(idStr, newRedisRecord);
                    if (Boolean.FALSE.equals(amDataSource.getEnabled())) {
                        Optional.ofNullable(dataSourceMap.remove(idStr)).ifPresent(d -> stop(d.getDataSource()));
                        throw new ServiceException("数据源" + idStr + "禁用中");
                    }

                    DruidDataSource newDataSource = init(decrypt(amDataSource));
                    Optional.ofNullable(dataSourceMap.put(idStr, new DataSourceCache(newDataSource, amDataSource.getType(), amDataSource.getVersion(), amDataSource)))
                            .ifPresent(d -> stop(d.getDataSource()));
                    return DataSourceAndType.newInstance(amDataSource.getId(), newDataSource, amDataSource.getType());
                }
            }
        }

        if (!redisRecord.isEnabled()) {
            Optional.ofNullable(dataSourceMap.remove(idStr)).ifPresent(d -> stop(d.getDataSource()));
            throw new ServiceException("数据源" + idStr + "禁用中");
        }

        return dataSourceMap.get(idStr).getDataSourceAndType();
    }


    @Override
    public DataSourceAndType getDataSourceAndTypeByName(String name) {
        List<AmDataSource> list = amDataSourceService.list(
                Wrappers.lambdaQuery(AmDataSource.class)
                        .select(AmDataSource::getId)
                        .eq(AmDataSource::getName, name)
        );
        if (list == null || list.isEmpty()) {
            throw new ServiceException("未找到数据源：" + name);
        }
        return getDataSourceAndType(list.get(0).getId().toString());
    }


    @Override
    @Transactional
    public boolean changeDataSourceStatus(Long id) {
        AmDataSource amDataSource = amDataSourceService.getOne(
                Wrappers.lambdaQuery(AmDataSource.class)
                        .select(AmDataSource::getId, AmDataSource::getEnabled)
                        .eq(AmDataSource::getId, id)
        );
        if (amDataSource == null) {
            throw new ServiceException("数据源" + id + "不存在");
        }
        removeRedisRecord(id.toString());
        return amDataSourceService.update(
                Wrappers.lambdaUpdate(AmDataSource.class)
                        .set(AmDataSource::getEnabled, !amDataSource.getEnabled())
                        .set(AmDataSource::getUpdateTime, LocalDateTime.now())
                        .set(AmDataSource::getUpdateUser, Optional.ofNullable(AuthUtil.getUser()).map(BladeUser::getUserId).orElse(null))
                        .eq(AmDataSource::getId, id)
        );
    }


    private DruidDataSource init(AmDataSource amDataSource) {
        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setUsername(amDataSource.getUserName());
        druidDataSource.setPassword(amDataSource.getUserPwd());
        druidDataSource.setUrl(amDataSource.getUrl());
        druidDataSource.setName(amDataSource.getName());
        //临时添加，避免连接失败无限次重试，后面结合实际情况做调整
        druidDataSource.setConnectionErrorRetryAttempts(3);
        druidDataSource.setBreakAfterAcquireFailure(true);
        druidDataSource.setValidationQuery(SqlDialectFactory.getDialectFromType(amDataSource.getType()).validationQuery());

        String driverClassName = amDataSource.getDriverClassName();
        if (!StringUtils.isBlank(driverClassName)) {
            druidDataSource.setDriverClassName(driverClassName);
        }
        this.setPropertiesFromConfig(druidDataSource);

        if(Boolean.TRUE.equals(dataSourceConfig.isGlobalReadonly()) || amDataSource.getReadonly() == null || Boolean.TRUE.equals(amDataSource.getReadonly())){
            this.setReadOnly(druidDataSource);
        }

        // 清除过滤，兼容达梦数据库
        druidDataSource.clearFilters();

        try {
            druidDataSource.clearFilters();
            druidDataSource.init();
        } catch (SQLException e) {
            throw new IllegalStateException("druid create error", e);
        }
        return druidDataSource;
    }


    private void setPropertiesFromConfig(DruidDataSource druidDataSource) {
        Optional.ofNullable(dataSourceConfig.getMaxActive()).ifPresent(druidDataSource::setMaxActive);
        Optional.ofNullable(dataSourceConfig.getInitialSize()).ifPresent(druidDataSource::setInitialSize);
        Optional.ofNullable(dataSourceConfig.getMaxWait()).ifPresent(druidDataSource::setMaxWait);
        Optional.ofNullable(dataSourceConfig.getMinIdle()).ifPresent(druidDataSource::setMinIdle);
        Optional.ofNullable(dataSourceConfig.getTimeBetweenEvictionRunsMillis()).ifPresent(druidDataSource::setTimeBetweenEvictionRunsMillis);
        Optional.ofNullable(dataSourceConfig.getMinEvictableIdleTimeMillis()).ifPresent(druidDataSource::setMinEvictableIdleTimeMillis);
        Optional.ofNullable(dataSourceConfig.getTestWhileIdle()).ifPresent(druidDataSource::setTestWhileIdle);
        Optional.ofNullable(dataSourceConfig.getTestOnBorrow()).ifPresent(druidDataSource::setTestOnBorrow);
        Optional.ofNullable(dataSourceConfig.getTestOnReturn()).ifPresent(druidDataSource::setTestOnReturn);
        Optional.ofNullable(dataSourceConfig.getPoolPreparedStatements()).ifPresent(druidDataSource::setPoolPreparedStatements);
        Optional.ofNullable(dataSourceConfig.getMaxPoolPreparedStatementPerConnectionSize()).ifPresent(druidDataSource::setMaxPoolPreparedStatementPerConnectionSize);
    }

    /**
     * https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE-wallfilter
     * @param druidDataSource
     */
    public void setReadOnly(DruidDataSource druidDataSource){
        WallConfig wallConfig = new WallConfig();

        wallConfig.setSelectIntoAllow(false);
        wallConfig.setDeleteAllow(false);
        wallConfig.setUpdateAllow(false);
        wallConfig.setInsertAllow(false);
        wallConfig.setReplaceAllow(false);
        wallConfig.setMergeAllow(false);
        wallConfig.setCallAllow(false);
        wallConfig.setSetAllow(false);
        wallConfig.setTruncateAllow(false);
        wallConfig.setCreateTableAllow(false);
        wallConfig.setAlterTableAllow(false);
        wallConfig.setDropTableAllow(false);

        wallConfig.setSelectWhereAlwayTrueCheck(false);
        wallConfig.setSelectHavingAlwayTrueCheck(false);
        wallConfig.setConditionAndAlwayTrueAllow(true);
        wallConfig.setConditionAndAlwayFalseAllow(true);

        wallConfig.setStrictSyntaxCheck(false);
        wallConfig.setConditionOpXorAllow(true);
        wallConfig.setConditionDoubleConstAllow(true);
        wallConfig.setLimitZeroAllow(true);

        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        wallFilter.setLogViolation(true);

        wallFilter.setThrowException(dataSourceConfig.isThrowException());

        druidDataSource.getProxyFilters().add(wallFilter);

    }

    private void stop(DruidDataSource druidDataSource) {
        druidDataSource.close();
    }

    @Override
    public boolean testConnection(String url, String user, String password) {
        try (Connection conn = DriverManager.getConnection(url, user, password)){
            return true;
        } catch (SQLInvalidAuthorizationSpecException e) {
            e.printStackTrace();
            throw new ServiceException("用户名或密码错误");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException("数据源连接失败！");
        }
    }


    @Override
    public DBResult getDbInfoByPath(List<String> path, String type) {
        if (path == null || path.isEmpty()) {
            List<AmDataSource> dataSources = findAllEnabled(new AmDataSourceDTO())
                    .stream()
                    .sorted(Comparator.comparing(AmDataSource::getCreateTime))
                    .collect(Collectors.toList());

            return DBTree.builder().nodes(dataSources.stream().map(
                    ds -> TreeNode.builder()
                            .path(ds.getId().toString())
                            .label(ds.getName())
                            .type(ds.getType())
                            .leaf(false)
                            .queryDetail(false)
                            .build()
            ).collect(Collectors.toList())).build();
        }
        return this.getDataSourceAndType(path.get(0)).getMetaData().getDbInfoByPath(path, type);
    }

    @Override
    public List<String> getDataSourceType() {
        return amDataSourceService.list(
                Wrappers.lambdaQuery(AmDataSource.class)
                        .select(AmDataSource::getType)
                        .groupBy(AmDataSource::getType)
                        .orderByAsc(AmDataSource::getType)
        ).stream().map(AmDataSource::getType).collect(Collectors.toList());
    }


    private AmDataSource encrypt(AmDataSource amDataSource) {
        AmDataSource encryptedAmDataSource = SerializationUtils.clone(amDataSource);
        encryptedAmDataSource.setUserPwd(this.encrypt(encryptedAmDataSource.getUserPwd()));
        return encryptedAmDataSource;
    }

    private AmDataSource decrypt(AmDataSource amDataSource) {
        AmDataSource decryptedAmDataSource = SerializationUtils.clone(amDataSource);
        decryptedAmDataSource.setUserPwd(this.decrypt(decryptedAmDataSource.getUserPwd()));
        return decryptedAmDataSource;
    }

    public String encrypt(String message) {
        if (StringUtils.isBlank(message)) {
            return message;
        }
        return PropertyValueEncryptionUtils.encrypt(message, this.dataCenterEncryptor);
    }

    private String decrypt(String encryptedMessage) {
        if (StringUtils.isBlank(encryptedMessage)) {
            return encryptedMessage;
        }
        return PropertyValueEncryptionUtils.decrypt(encryptedMessage, this.dataCenterEncryptor);
    }

    /**
     * 启动服务时先初始化连接池
     */
    @PostConstruct
    private void init() {
        amDataSourceService.list(
                Wrappers.lambdaQuery(AmDataSource.class)
                        .select(AmDataSource::getId)
                        .eq(AmDataSource::getEnabled, true)
        ).forEach(
                ds -> {
                    try {
                        this.getDataSource(ds.getId().toString());
                    } catch (Exception e) {
                        log.error("init datasource error,datasource.id=" + ds.getId().toString()
                                        + ";name=" + ds.getName()
                                ,
                                e);
                    }
                }
        );
    }


    @Getter
    private static class DataSourceRecord {
        private Long version;
        private boolean enabled;

        static DataSourceRecord fromEntity(AmDataSource amDataSource) {
            DataSourceRecord result = new DataSourceRecord();
            result.version = amDataSource.getVersion();
            result.enabled = amDataSource.getEnabled();
            return result;
        }

        static DataSourceRecord parse(String recordStr) {
            if (StringUtils.isBlank(recordStr) || !recordStr.contains(";")) {
                return null;
            }
            try {
                String[] arr = recordStr.split(";");
                DataSourceRecord result = new DataSourceRecord();
                result.version = Long.parseLong(arr[0]);
                result.enabled = "1".equals(arr[1]);
                return result;
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        public String toString() {
            return String.format("%s;%s", version, enabled ? "1" : "0");
        }
    }

    @Getter
    private static class DataSourceCache {
        private DruidDataSource dataSource;

        private long version;

        private String type;

        private AmDataSource amDataSource;

        DataSourceCache(DruidDataSource dataSource, String type, long version, AmDataSource amDataSource) {
            this.dataSource = dataSource;
            this.type = type;
            this.version = version;
            this.amDataSource = amDataSource;
        }

        public DataSourceAndType getDataSourceAndType() {
            return DataSourceAndType.newInstance(amDataSource.getId(), this.dataSource, this.type);
        }
    }

    private enum ListenEventType {
        DATASOURCE_CREATE,
        DATASOURCE_UPDATE,
        DATASOURCE_DELETE
    }

    public class ListenEvent {
        private ListenEventType type;
        private Map data;

        public Map getData() {
            return data;
        }

        public ListenEvent(ListenEventType type, Map data) {
            this.type = type;
            this.data = data;
        }

        public ListenEventType getType() {
            return type;
        }
    }

}
