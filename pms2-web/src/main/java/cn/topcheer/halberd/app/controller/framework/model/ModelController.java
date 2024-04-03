package cn.topcheer.halberd.app.controller.framework.model;

import cn.topcheer.halberd.app.api.minidev.constant.PropertyTypeEnum;
import cn.topcheer.halberd.app.api.minidev.dto.BatchAddModelDTO;
import cn.topcheer.halberd.app.api.minidev.entity.Model;
import cn.topcheer.halberd.app.api.minidev.entity.ModelPrototype;
import cn.topcheer.halberd.app.api.minidev.entity.TableInfo;
import cn.topcheer.halberd.app.api.minidev.result.ModelTreeResult;
import cn.topcheer.halberd.app.api.minidev.service.ModelPrototypeService;
import cn.topcheer.halberd.app.api.minidev.service.ModelService;
import cn.topcheer.halberd.app.biz.framework.service.DataSourceManagementService;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.app.dao.db.metadata.IDbMetaData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * 代码模型表 控制器
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("/model")
@Api(value = "代码模型表", tags = "模型-代码模型表接口")
public class ModelController extends BladeController {

    private final ModelService modelService;

    private final ModelPrototypeService modelPrototypeService;

    private final DataSourceManagementService dataSourceManagementService;

    private final SysUserServiceImpl sysUserService;

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入model")
    public R<Model> detail(Model model) {

        Model detail = modelService.getById(model.getId());
        return R.data(detail);
    }

    /**
     * 分页 代码模型表
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入model")
    public R<IPage<Model>> list(Model model, Query query) {
        QueryWrapper<Model> qw = new QueryWrapper<>();
        qw.eq(StringUtils.isNotBlank(model.getDatasourceId()), "datasource_id", model.getDatasourceId());
        qw.eq(model.getModelTypeId() != null, "model_type_id", model.getModelTypeId());
        qw.like(StringUtils.isNotBlank(model.getModelCode()), "model_code", model.getModelCode());
        qw.like(StringUtils.isNotBlank(model.getModelName()), "model_name", model.getModelName());
        qw.eq("enterprise_id", sysUserService.getEnterPriseId());
        qw.orderByDesc("create_time");

        IPage<Model> pages = modelService.page(Condition.getPage(query), qw);
        return R.data(pages);
    }

    /**
     * 新增 代码模型表
     */
    @PostMapping("/save")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "新增", notes = "传入model")
    public R save(@Valid @RequestBody Model model) {
        if (!StringUtil.hasText(model.getDatasourceId())) {
            return R.fail("datasource 不能为空");
        }
        if (!StringUtil.hasText(model.getSchemaName())) {
            String currentSchema = dataSourceManagementService.getDataSourceAndType(model.getDatasourceId()).getMetaData().getCurrentSchema();
            if (StringUtil.hasText(currentSchema)) {
                model.setSchemaName(currentSchema);
            }
        }
        return R.status(modelService.save(model));
    }

    /**
     * 修改 代码模型表
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "修改", notes = "传入model")
    public R update(@Valid @RequestBody Model model) {
        return R.status(modelService.updateById(model));
    }


    /**
     * 新增或修改 代码模型表
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "新增或修改", notes = "传入model")
    public R submit(@Valid @RequestBody Model model) {

        if (model.getId() == null) {
            // 新增
            model.setIsDeleted(0);
            model.setCreateTime(new Date());
            model.setCreateUser(AuthUtil.getUserId());
            model.setEnterpriseId(sysUserService.getEnterPriseId());

        } else {
            // 编辑
            model.setUpdateTime(new Date());
            model.setUpdateUser(AuthUtil.getUserId());
        }


        return R.status(modelService.saveOrUpdate(model));
    }


    /**
     * 批量新增
     */
    @PostMapping("/batchAdd")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "批量新增", notes = "批量新增")
    public R<String> batchAdd(@Valid @RequestBody BatchAddModelDTO dto) {

        // 批量添加
        modelService.batchAdd(dto);

        return R.data("操作成功");
    }



    /**
     * 生成api
     */
    @PostMapping("/convert_api")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "新增或修改", notes = "传入model")
    public R convertApi(@Valid @RequestBody Model model) {
        try {
            modelService.convertExchangApi(model);
            return R.success("成功");
        } catch (Exception ex) {
            return R.fail("发生错误，请告之开发人员");
        }

    }


    /**
     * 删除 代码模型表
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(modelService.removeByIds(Func.toStrList(ids)));
    }

    /**
     * 模型列表
     */
    @GetMapping("/select")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "模型列表", notes = "模型列表")
    public R<List<Model>> select() {
        List<Model> list = modelService.list();
        list.forEach(model -> model.setModelName(
                model.getModelName() + StringPool.LEFT_BRACKET + model.getModelCode() + StringPool.RIGHT_BRACKET));
        return R.data(list);
    }

    /**
     * 获取物理表列表
     */
    @GetMapping("/table-list")
    @ApiOperationSupport(order = 8)
    @ApiOperation(value = "物理表列表", notes = "传入model")
    public R<List> tableList(String datasourceId, String schema) {
        IDbMetaData metaData = dataSourceManagementService.getDataSourceAndType(datasourceId).getMetaData();
        List allTableOrViews;
        if (schema == null) {
            allTableOrViews = metaData.getAllTableOrViews();

        } else {
            allTableOrViews = metaData.getAllTableOrViews(schema);
        }
        return R.data(allTableOrViews);
    }

    /**
     * 获取物理表信息
     */
    @GetMapping("/table-info")
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "物理表信息", notes = "传入model")
    public R<TableInfo> tableInfo(String modelId, String tableName, String schema, String datasourceId) {
        Model model = null;
        if ((!StringUtil.hasText(tableName) || !StringUtil.hasText(schema)) && StringUtil.hasText(modelId)) {
            model = modelService.getById(modelId);
        }

        if (!StringUtil.hasText(tableName) && model != null) {

            tableName = model.getModelTable();
        }
        if (!StringUtil.hasText(schema) && model != null) {

            schema = model.getSchemaName();

        }

        TableInfo tableInfo = modelService.getTableInfo(datasourceId, schema, tableName);
        //创建一个实现了DbTableOrView 的类

        return R.data(tableInfo);

    }

    /**
     * 获取字段信息
     */
    @GetMapping("/model-prototype")
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "物理表信息", notes = "传入model")
    public R<List<ModelPrototype>> modelPrototype(String modelId) {
        // 从模型中获取字段
        QueryWrapper<ModelPrototype> qw = new QueryWrapper<>();
        qw.eq("model_id", modelId);
        qw.eq("is_deleted", 0);
        qw.orderByAsc("sort_number");
        List<ModelPrototype> modelPrototypeList = modelPrototypeService.list(qw);
        if (modelPrototypeList.size() > 0) {
            modelPrototypeList.forEach(modelPrototype -> modelPrototype.setComment(StringUtils.isBlank(modelPrototype.getComment()) ? modelPrototype.getPropertyName() : modelPrototype.getComment()));
            return R.data(modelPrototypeList);
        }

        // 从表结构中获取字段
        return R.data(modelService.createPrototypesByTable(modelId));
    }


    /**
     * 获取字段信息-从表结构中
     */
    @GetMapping("/model-prototypeByTable")
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "物理表信息", notes = "传入model")
    public R<List<ModelPrototype>> modelPrototypeByTable(String modelId) {

        // 从表结构中获取字段
        return R.data(modelService.createPrototypesByTable(modelId));
    }


    /**
     * 获取属性类型枚举集合
     */
    @GetMapping("/property-types")
    public R getPropertyTypes() {
        List<Map> result = new ArrayList<>();
        for (PropertyTypeEnum propertyTypeEnum : PropertyTypeEnum.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("value", propertyTypeEnum.getValue());
            map.put("name", propertyTypeEnum.name());
            result.add(map);
        }
        return R.data(result);

    }


    // /**
    // * 获取表信息
    // *
    // * @param tableName 表名
    // * @param datasourceId 数据源主键
    // */
    // private TableInfo getTableInfo(String tableName, Long datasourceId) {
    // Datasource datasource = datasourceService.getById(datasourceId);
    // //ConfigBuilder config = getConfigBuilder(datasource);
    // ConfigBuilderImpl config = (ConfigBuilderImpl) getConfigBuilder(datasource);
    // List<TableInfo> tableInfoList = config.getTableList(tableName);
    // TableInfo tableInfo = null;
    // Iterator<TableInfo> iterator = tableInfoList.stream().filter(table ->
    // table.getName().equals(tableName)).collect(Collectors.toList()).iterator();
    // if (iterator.hasNext()) {
    // tableInfo = iterator.next();
    // //tableInfo.getName()
    // //tableInfo.setName(tableName.replace(tableName.split(StringPool.UNDERSCORE)[0]
    // + StringPool.UNDERSCORE, StringPool.EMPTY));
    // tableInfo.setEntityName(tableInfo.getEntityName().replace(StringUtil.firstCharToUpper(tableName.split(StringPool.UNDERSCORE)[0]),
    // StringPool.EMPTY));
    // }
    // return tableInfo;
    // }

    // /**
    // * 获取表配置信息
    // *
    // * @param datasource 数据源信息
    // */
    // private ConfigBuilder getConfigBuilder(DataSource datasource) {
    // //StrategyConfig strategy = new StrategyConfig();
    // // strategy.setNaming(NamingStrategy.underline_to_camel);
    // // strategy.setColumnNaming(NamingStrategy.underline_to_camel);

    // StrategyConfig.Builder strategyBuild = new StrategyConfig.Builder();
    // StrategyConfig strategy = strategyBuild.build();
    // strategy.entityBuilder().naming(NamingStrategy.underline_to_camel)
    // .columnNaming(NamingStrategy.underline_to_camel);

    // DataSourceConfig datasourceConfig = new
    // DataSourceConfig.Builder(datasource).build();
    // // String driverName = datasource.getDriverClass();
    // // if (StringUtil.containsAny(driverName, DbType.MYSQL.getDb())) {
    // // datasourceConfig.setDbType(DbType.MYSQL);
    // // datasourceConfig.setTypeConvert(new MySqlTypeConvert());
    // // } else if (StringUtil.containsAny(driverName, DbType.POSTGRE_SQL.getDb()))
    // {
    // // datasourceConfig.setDbType(DbType.POSTGRE_SQL);
    // // datasourceConfig.setTypeConvert(new PostgreSqlTypeConvert());
    // // } else if (StringUtil.containsAny(driverName, DbType.SQL_SERVER.getDb()))
    // {
    // // datasourceConfig.setDbType(DbType.SQL_SERVER);
    // // datasourceConfig.setTypeConvert(new SqlServerTypeConvert());
    // // } else {
    // // datasourceConfig.setDbType(DbType.ORACLE);
    // // datasourceConfig.setTypeConvert(new OracleTypeConvert());
    // // }
    // // datasourceConfig.setDriverName(driverName);
    // // datasourceConfig.setUrl(datasource.getUrl());
    // // datasourceConfig.setUsername(datasource.getUsername());
    // // datasourceConfig.setPassword(datasource.getPassword());
    // return new ConfigBuilderImpl(null, datasourceConfig, strategy, null,
    // null,null);
    // }


    /**
     * 获取模型树
     *
     * @author szs
     * @date 2023-08-22
     */
    @GetMapping("/getModelTree")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取模型树", notes = "获取模型树")
    public R<List<ModelTreeResult>> getModelTree() {

        return R.data(modelService.getModelTree());
    }


}
