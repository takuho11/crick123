package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.hutool.core.bean.BeanUtil;
import cn.topcheer.halberd.app.api.framework.vo.DbColumn;
import cn.topcheer.halberd.app.api.framework.vo.DbTableOrView;
import cn.topcheer.halberd.app.api.minidev.constant.PropertyTypeEnum;
import cn.topcheer.halberd.app.api.minidev.dto.BatchAddModelDTO;
import cn.topcheer.halberd.app.api.minidev.dto.ModelDTO;
import cn.topcheer.halberd.app.api.minidev.entity.GenModelType;
import cn.topcheer.halberd.app.api.minidev.entity.Model;
import cn.topcheer.halberd.app.api.minidev.entity.ModelPrototype;
import cn.topcheer.halberd.app.api.minidev.entity.TableInfo;
import cn.topcheer.halberd.app.api.minidev.result.ModelTreeResult;
import cn.topcheer.halberd.app.api.minidev.service.GenModelTypeService;
import cn.topcheer.halberd.app.api.minidev.service.ModelPrototypeService;
import cn.topcheer.halberd.app.api.minidev.service.ModelService;
import cn.topcheer.halberd.app.biz.framework.service.DataSourceManagementService;
import cn.topcheer.halberd.app.biz.framework.service.impl.sys.SysUserServiceImpl;
import cn.topcheer.halberd.app.dao.db.metadata.IDbMetaData;
import cn.topcheer.halberd.app.dao.mapper.minidev.ModelMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 代码模型表 服务实现类
 *
 * @author Chill
 */
@Service
public class ModelServiceImpl extends BaseServiceImpl<ModelMapper, Model> implements ModelService {

    @Resource
    private ModelPrototypeService modelPrototypeService;

    @Resource
    private DataSourceManagementService dataSourceManagementService;

    @Resource
    private ExchangApiConverter exchangApiConverter;

    @Resource
    private GenModelTypeService genModelTypeService;

    @Autowired
    private SysUserServiceImpl sysUserService;


    @Override
    public ModelDTO getDTO(Model model) {

        ModelDTO modelDTO = BeanUtil.copyProperties(model, ModelDTO.class);

        List<ModelPrototype> prototypes = modelPrototypeService
                .list(Wrappers.<ModelPrototype>query().lambda().eq(ModelPrototype::getModelId, model.getId()));
        modelDTO.setPrototypes(prototypes);
        return modelDTO;

    }

    @Override
    public boolean saveBatch(Collection<Model> entityList, int batchSize) {

        super.saveBatch(entityList, batchSize);
        entityList.forEach(this::addApiPath);

        return super.saveBatch(entityList, batchSize);
    }


    @Override
    public boolean saveOrUpdate(Model entity) {
        super.saveOrUpdate(entity);
        addApiPath(entity);
        return super.saveOrUpdate(entity);
    }

    private void addApiPath(Model entity) {
        if (entity.getApiType() != null && (entity.getApiType() == 1 || entity.getApiType() == 2)) {
            if (!StringUtil.hasText(entity.getInsertApi())) {
                entity.setInsertApi(ExchangApiConverter.getInsertApiPath(entity));
            }

            if (!StringUtil.hasText(entity.getUpdateApi())) {
                entity.setUpdateApi(ExchangApiConverter.getUpdateApiPath(entity));
            }

            if (!StringUtil.hasText(entity.getDeleteApi())) {
                entity.setDeleteApi(ExchangApiConverter.getDeleteApiPath(entity));
            }

            if (!StringUtil.hasText(entity.getSelectApi())) {
                entity.setSelectApi(ExchangApiConverter.getSelectApiPath(entity));
            }
        }
    }


    @Override
    public boolean saveOrUpdateBatch(Collection<Model> entityList, int batchSize) {
        super.saveOrUpdateBatch(entityList, batchSize);
        entityList.forEach(this::addApiPath);

        return super.saveOrUpdateBatch(entityList, batchSize);
    }

    @Override
    public TableInfo getTableInfo(String datasourceId, String schema, String tableName) {

        IDbMetaData metaData = dataSourceManagementService.getDataSourceAndType(datasourceId).getMetaData();
        DbTableOrView tableOrView;
        if (StringUtil.hasText(schema)) {
            tableOrView = metaData.getTableOrView(schema, tableName);

        } else {
            tableOrView = metaData.getTableOrView(tableName);
        }
        if (tableOrView == null) {
            return null;
        } else {
            TableInfo tableInfo = new TableInfo();
            tableInfo.setName(tableOrView.getName());
            tableInfo.setComment(tableOrView.getComment());
            tableInfo.setSchema(tableOrView.getSchema());
            tableInfo.setFullName(tableOrView.getFullName());
            tableInfo.setDbType(tableOrView.getDbType());
            tableInfo.setView(tableOrView.isView());
            tableInfo.setEntityName(toCamelCase(tableOrView.getName(), true));

            return tableInfo;
        }

    }

    @Override
    public void convertExchangApi(Model model) throws Exception {

        ModelDTO modelDTO = getDTO(model);
        if (model.getApiType() != null
                && (model.getApiType() == 1 || model.getApiType() == 2)) {
            exchangApiConverter.convert(modelDTO);
        }

    }

    private static final Map<String, PropertyTypeEnum> typeMapping = new HashMap<>();

    static {
        typeMapping.put("varchar", PropertyTypeEnum.STRING);
        typeMapping.put("char", PropertyTypeEnum.STRING);
        typeMapping.put("text", PropertyTypeEnum.STRING);
        typeMapping.put("longtext", PropertyTypeEnum.STRING);
        typeMapping.put("tinytext", PropertyTypeEnum.STRING);
        typeMapping.put("mediumtext", PropertyTypeEnum.STRING);
        typeMapping.put("longvarchar", PropertyTypeEnum.STRING);
        typeMapping.put("nvarchar", PropertyTypeEnum.STRING);
        typeMapping.put("nchar", PropertyTypeEnum.STRING);
        typeMapping.put("bit", PropertyTypeEnum.BOOLEAN);
        typeMapping.put("boolean", PropertyTypeEnum.BOOLEAN);
        typeMapping.put("datetime", PropertyTypeEnum.DATETIME);
        typeMapping.put("date", PropertyTypeEnum.DATETIME);
        typeMapping.put("time", PropertyTypeEnum.DATETIME);
        typeMapping.put("timestamp", PropertyTypeEnum.DATETIME);
        typeMapping.put("smalldatetime", PropertyTypeEnum.DATETIME);
        typeMapping.put("int", PropertyTypeEnum.INTEGER);
        typeMapping.put("integer", PropertyTypeEnum.INTEGER);
        typeMapping.put("tinyint", PropertyTypeEnum.INTEGER);
        typeMapping.put("smallint", PropertyTypeEnum.INTEGER);
        typeMapping.put("mediumint", PropertyTypeEnum.INTEGER);
        typeMapping.put("bigint", PropertyTypeEnum.INTEGER);
        typeMapping.put("float", PropertyTypeEnum.NUMBER);
        typeMapping.put("double", PropertyTypeEnum.NUMBER);
        typeMapping.put("decimal", PropertyTypeEnum.NUMBER);
        typeMapping.put("numeric", PropertyTypeEnum.NUMBER);
        typeMapping.put("number", PropertyTypeEnum.NUMBER);
        typeMapping.put("real", PropertyTypeEnum.NUMBER);
        typeMapping.put("money", PropertyTypeEnum.NUMBER);
        typeMapping.put("smallmoney", PropertyTypeEnum.NUMBER);
        typeMapping.put("binary", PropertyTypeEnum.BLOB);
        typeMapping.put("varbinary", PropertyTypeEnum.BLOB);
        typeMapping.put("longvarbinary", PropertyTypeEnum.BLOB);
        typeMapping.put("blob", PropertyTypeEnum.BLOB);
        typeMapping.put("clob", PropertyTypeEnum.BLOB);
        typeMapping.put("nclob", PropertyTypeEnum.BLOB);

    }

    @Override
    public List<ModelPrototype> createPrototypesByTable(String modelId) {
        Model model = getById(modelId);
        if (model == null) {
            return null;
        }

        IDbMetaData metaData = dataSourceManagementService.getDataSourceAndType(model.getDatasourceId()).getMetaData();
        String fullTablename = null;
        List<DbColumn> columns;
        if (StringUtil.hasText(model.getSchemaName())) {
            columns = metaData.getColumns(model.getSchemaName(), model.getModelTable());
        } else {
            columns = metaData.getColumns(fullTablename);
        }

        List<ModelPrototype> result = new ArrayList<>();
        ModelPrototype prototype;
        int seq = 1;
        for (DbColumn column : columns) {
            prototype = new ModelPrototype();
            prototype.setJdbcName(column.getColumnName().toLowerCase());
            prototype.setJdbcType(column.getColumnType());
            prototype.setModelId(model.getId());
            prototype.setComment(column.getComment());
            prototype.setPropertyName(toCamelCase(column.getColumnName().toLowerCase(), false));
            prototype.setIsPrimarykey(column.isPrimaryKey());
            prototype.setPropertyType(typeMapping.getOrDefault(column.getDataType().toLowerCase(), PropertyTypeEnum.STRING).getValue());
            prototype.setIsForm(true);
            prototype.setIsRow(false);
            prototype.setIsRequired(true);
            prototype.setIsList(true);
            prototype.setIsQuery(false);
            prototype.setIsReadonly(false);
            prototype.setListSeq(seq);
            prototype.setSortNumber(seq);
            prototype.setComponentType("cov-el-input");
            prototype.setCharLength(column.getCharLength());
            result.add(prototype);
            seq++;
        }

        return result;

    }

    // 实现一个方法，传入字段名或表名，返回驼峰命名，不要用第三方库
    public static String toCamelCase(String input, boolean capitalizeFirstLetter) {
        StringBuilder result = new StringBuilder();
        String[] words = input.split("_");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0 && !capitalizeFirstLetter) {
                result.append(word);
            } else {
                result.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    result.append(word.substring(1));
                }
            }
        }
        return result.toString();
    }


    /**
     * 获取模型树
     *
     * @return List
     * @author szs
     * @date 2023-08-21
     */
    @Override
    public List<ModelTreeResult> getModelTree() {
        List<ModelTreeResult> list = new ArrayList<>();

        // 查询模型类型
        List<GenModelType> modelTypes = this.genModelTypeList();
        for (GenModelType modelType : modelTypes) {
            // 查询模型列表
            List<Model> models = this.genModelList(modelType.getId());
            if (models.size() == 0) {
                continue;
            }

            ModelTreeResult result = new ModelTreeResult(1, modelType.getId().toString(), modelType.getModelTypeName(), new ArrayList<>());
            for (Model model : models) {
                result.getChildren().add(new ModelTreeResult(2, model.getId(), model.getModelName(), null));
            }

            list.add(result);
        }

        return list;
    }


    /**
     * 获取模型类型列表
     *
     * @return List
     * @author szs
     * @date 2023-08-21
     */
    private List<GenModelType> genModelTypeList() {
        QueryWrapper<GenModelType> qw = new QueryWrapper<>();
        qw.eq("enterprise_id", sysUserService.getEnterPriseId());
        qw.eq("is_deleted", 0);
        qw.orderByAsc("id");

        return genModelTypeService.list(qw);
    }


    /**
     * 获取模型列表
     *
     * @param modelTypeId 模型类型id
     * @return List
     * @author szs
     * @date 2023-08-21
     */
    private List<Model> genModelList(Long modelTypeId) {
        QueryWrapper<Model> qw = new QueryWrapper<>();
        qw.eq("model_type_id", modelTypeId);
        qw.eq("is_deleted", 0);
        qw.orderByAsc("create_time");

        return this.list(qw);
    }


    /**
     * 批次添加
     *
     * @param dto BatchAddModelDTO
     * @author szs
     * @date 2024-01-08
     */
    @Override
    public void batchAdd(BatchAddModelDTO dto) {
        List<Model> list = new ArrayList<>();

        // 单位id
        String enterPriseId = sysUserService.getEnterPriseId();


        // 组装数据
        for (String modelTable : dto.getModelTables()) {
            Model data = new Model();
            data.setDatasourceId(dto.getDatasourceId());
            data.setSchemaName(dto.getSchemaName());
            data.setModelTable(modelTable);
            // 获取表信息
            TableInfo tableInfo = this.getTableInfo(dto.getDatasourceId(), dto.getSchemaName(), modelTable);
            if (tableInfo != null) {
                data.setModelName(tableInfo.getComment());
                data.setModelCode(tableInfo.getName());
                data.setModelClass(tableInfo.getEntityName());
            }

            data.setModelTypeId(dto.getModelTypeId());
            data.setApiType(0);
            data.setCreateTime(new Date());
            data.setCreateUser(AuthUtil.getUserId());
            data.setIsDeleted(0);
            data.setEnterpriseId(enterPriseId);
            list.add(data);
        }

        // 批量新增
        if (list.size() > 0) {
            this.saveOrUpdateBatch(list);
        }

        // 导入表字段
        List<ModelPrototype> modelPrototypeList = new ArrayList<>();
        for (Model data : list) {
            // 获取表字段
            modelPrototypeList.addAll(this.createPrototypesByTable(data.getId()));
        }

        // 批量保存
        if (modelPrototypeList.size() > 0) {
            modelPrototypeService.saveBatch(modelPrototypeList);
        }

    }


}
