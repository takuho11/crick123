package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.topcheer.halberd.app.api.framework.entity.api.AmApi;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiRequest;
import cn.topcheer.halberd.app.api.framework.enumerate.CompareType;
import cn.topcheer.halberd.app.api.framework.enumerate.DataSourceType;
import cn.topcheer.halberd.app.api.framework.enumerate.FieldType;
import cn.topcheer.halberd.app.api.framework.service.api.ApiRequestService;
import cn.topcheer.halberd.app.api.framework.service.api.ApiService;
import cn.topcheer.halberd.app.api.minidev.constant.PropertyTypeEnum;
import cn.topcheer.halberd.app.api.minidev.dto.ModelDTO;
import cn.topcheer.halberd.app.api.minidev.entity.Model;
import cn.topcheer.halberd.app.api.minidev.entity.ModelPrototype;
import cn.topcheer.halberd.app.api.minidev.service.ModelConverter;
import cn.topcheer.halberd.app.biz.framework.service.DataSourceManagementService;
import cn.topcheer.halberd.app.dao.db.DataSourceAndType;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Lazy
@Service
public class ExchangApiConverter implements ModelConverter {


    private static final Map<String, FieldType> fieldTypeMapping = new HashMap<>();

    /**
     * 这个类型是查询条件：ModelPrototype<-->AmApiRequest
     */
    private static final Map<String, CompareType> compareTypeMapping = new HashMap<>();

    static {
        fieldTypeMapping.put(PropertyTypeEnum.STRING.getValue(), FieldType.String);
        fieldTypeMapping.put(PropertyTypeEnum.BOOLEAN.getValue(), FieldType.Integer);
        fieldTypeMapping.put(PropertyTypeEnum.INTEGER.getValue(), FieldType.Integer);
        fieldTypeMapping.put(PropertyTypeEnum.DATETIME.getValue(), FieldType.LocalDateTime);
        fieldTypeMapping.put(PropertyTypeEnum.NUMBER.getValue(), FieldType.Double);


        compareTypeMapping.put("equal", CompareType.eq);
        compareTypeMapping.put("notequal", CompareType.ne);
        compareTypeMapping.put("gt", CompareType.gt);
        compareTypeMapping.put("ge", CompareType.ge);
        compareTypeMapping.put("lt", CompareType.lt);
        compareTypeMapping.put("le", CompareType.le);
        compareTypeMapping.put("like", CompareType.like);
        compareTypeMapping.put("likeleft", CompareType.likeLeft);
        compareTypeMapping.put("likeright", CompareType.likeRight);
        compareTypeMapping.put("none", CompareType.eq);

    }


    @Autowired
    DataSourceManagementService dataSourceManagementService;

    //@Autowired
    // ModelService modelService;

    @Autowired
    ApiService apiService;

    @Autowired
    ApiRequestService apiRequestService;


    private final static String apiPrefix = "/qsm/";

    public static String getInsertApiPath(Model entity) {
        return getApiModelPrefix(entity) + "/insert";
    }

    public static String getUpdateApiPath(Model entity) {
        return getApiModelPrefix(entity) + "/update";
    }

    public static String getDeleteApiPath(Model entity) {
        return getApiModelPrefix(entity) + "/delete";
    }

    public static String getSelectApiPath(Model entity) {
        return getApiModelPrefix(entity) + "/select";
    }


    public static String getApiModelPrefix(Model entity) {
        int endidx;
        if (entity.getId().length() > 4) {
            endidx = entity.getId().length() / 4;
        } else {
            endidx = entity.getId().length();
        }
        return apiPrefix + entity.getModelCode() + "-" + entity.getId().substring(0, endidx);
    }


    @Override
    public void convert(ModelDTO model) throws Exception {
        if (!canHandle(model)) {
            throw new Exception("can't handle this miniform");
        }
        convertApi(model);
    }

    @Override
    public boolean canHandle(ModelDTO model) {

        if (model.getApiType() != null
                && (model.getApiType() == 1 || model.getApiType() == 2)
        ) {
            return true;
        }

        return false;
    }


    private void convertApi(ModelDTO model) throws Exception {

        AmApi parentApi = saveOrUpdateParentApi(model);
        saveOrUpdateChildApis(parentApi, model);


    }


    private AmApi saveOrUpdateParentApi(ModelDTO model) {

        DataSourceAndType dataSourceAndType = dataSourceManagementService.getDataSourceAndType(model.getDatasourceId());

        String path = getApiModelPrefix(model);
        AmApi parentApi = apiService.getOne(Wrappers.lambdaQuery(AmApi.class).eq(AmApi::getPath, path));
        if (parentApi == null) {
            parentApi = new AmApi();
            parentApi.setName(model.getModelName());
            parentApi.setPath(path);
            parentApi.setType("exchange");

            if (!StringUtil.hasText(model.getSchemaName())) {
                String currentSchema = dataSourceAndType.getMetaData().getCurrentSchema();
                if (StringUtil.hasText(currentSchema)) {
                    model.setSchemaName(currentSchema);
                }

            }
            parentApi.setSchemaName(model.getSchemaName());
            parentApi.setTableName(model.getModelTable());
            parentApi.setSourceType(DataSourceType.compatibleValueOf(dataSourceAndType.getType()));
//            parentApi.setDevState(ApiState.undev);
//            parentApi.setProdState(ApiState.undev);
            parentApi.setDatasourceId(model.getDatasourceId());
            // TODO:这里临时写成这样，以后感觉要从建model时选择
            parentApi.setServiceId(1645981268779732994L);
            parentApi.setPaginationBit(true);

            // TODO：通过模型管理生成的API默认已发布，默认审批通过
            parentApi.setStatus(1);
            parentApi.setApproveStatus(2);
            parentApi.setIsAccessAuthorize(false);
            parentApi.setIsPublic(true);

            apiService.save(parentApi);

        }
        return parentApi;

    }


    private void saveOrUpdateChildApis(AmApi parentApi, ModelDTO model) {

        List<AmApi> subApis = apiService.list(Wrappers.lambdaQuery(AmApi.class).eq(AmApi::getApiId, parentApi.getId()));

        // insert
        String insertPath = model.getInsertApi();
        String updatePath = model.getUpdateApi();
        String deletePath = model.getDeleteApi();
        String selectPath = model.getSelectApi();
        AmApi insertApi, deleteApi, updateApi, selectApi;

        // get or create insertApi
        Optional<AmApi> optApi = subApis.stream().filter(a -> (insertPath).equals(a.getPath())).findFirst();
        insertApi = optApi.orElseGet(() -> createChildApi("新增", insertPath, "insert", parentApi));

        // get or create deleteApi
        optApi = subApis.stream().filter(a -> (deletePath).equals(a.getPath())).findFirst();
        deleteApi = optApi.orElseGet(() -> createChildApi("删除", deletePath, "delete", parentApi));

        // get or create updateApi
        optApi = subApis.stream().filter(a -> (updatePath).equals(a.getPath())).findFirst();
        updateApi = optApi.orElseGet(() -> createChildApi("更新", updatePath, "update", parentApi));

        // get or create selectApi
        optApi = subApis.stream().filter(a -> (selectPath).equals(a.getPath())).findFirst();
        selectApi = optApi.orElseGet(() -> createChildApi("查询", selectPath, "select", parentApi));

        List<ModelPrototype> properties = model.getPrototypes();
        AmApiRequest apirequest;
        for (ModelPrototype pro : properties) {

            // 新增API
            apirequest = saveOrUpdateAmApiRequest(insertApi, pro, null);

            // 编辑API
            apirequest = saveOrUpdateAmApiRequest(updateApi, pro, a -> {
                a.setWhereby(pro.getIsPrimarykey() != null && pro.getIsPrimarykey());
                a.setCompared(compareTypeMapping.getOrDefault(pro.getQueryType(), CompareType.eq));
                return true;
            });

            // 删除API
            apirequest = saveOrUpdateAmApiRequest(deleteApi, pro, a -> {
                a.setWhereby(pro.getIsPrimarykey() != null && pro.getIsPrimarykey());
                a.setCompared(compareTypeMapping.getOrDefault(pro.getQueryType(), CompareType.eq));
                return true;
            });

            // 查询API
            apirequest = saveOrUpdateAmApiRequest(selectApi, pro, a -> {
                a.setWhereby(pro.getIsQuery() != null && pro.getIsQuery());
                a.setCompared(compareTypeMapping.getOrDefault(pro.getQueryType(), CompareType.eq));
                return true;
            });

        }

    }

    private AmApi createChildApi(String name, String path, String type, AmApi parentApi) {
        AmApi newApi = new AmApi();
        newApi.setName(name);
        newApi.setPath(path);
        newApi.setType(type);
        newApi.setApiId(parentApi.getId());
        newApi.setSchemaName(parentApi.getSchemaName());
        newApi.setTableName(parentApi.getTableName());
        newApi.setSourceType(parentApi.getSourceType());
//        newApi.setDevState(ApiState.undev);
//        newApi.setProdState(ApiState.undev);
        newApi.setPaginationBit(true);
        newApi.setDatasourceId(parentApi.getDatasourceId());
        newApi.setServiceId(parentApi.getServiceId());
        apiService.save(newApi);
        return newApi;
    }


    private AmApiRequest saveOrUpdateAmApiRequest(AmApi api, ModelPrototype pro, Function<AmApiRequest, Boolean> beforeSave) {

        AmApiRequest apirequest = apiRequestService
                .getOne(Wrappers.lambdaQuery(AmApiRequest.class).eq(AmApiRequest::getApiId, api.getId())
                        .eq(AmApiRequest::getName, pro.getJdbcName()));
        if (apirequest == null) {
            apirequest = new AmApiRequest();
        }

        apirequest.setApiId(api.getId());
        apirequest.setName(pro.getJdbcName());
        apirequest.setMemo(pro.getComment());
        apirequest.setFilled(false);
        apirequest.setWhereby(false);
        apirequest.setSource("param");
        apirequest.setSortNumber(pro.getSortNumber());
        apirequest.setPrimaryKey(pro.getIsPrimarykey());

        if (fieldTypeMapping.containsKey(pro.getPropertyType())) {
            apirequest.setType(fieldTypeMapping.get(pro.getPropertyType()));
        } else {
            apirequest.setType(fieldTypeMapping.get("string"));
        }

        if (beforeSave != null) {
            if (!beforeSave.apply(apirequest)) {
                return apirequest;
            }
        }

        apiRequestService.saveOrUpdate(apirequest);

        return apirequest;
    }


}
