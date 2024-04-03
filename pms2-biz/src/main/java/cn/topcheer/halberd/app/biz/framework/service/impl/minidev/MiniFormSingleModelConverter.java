package cn.topcheer.halberd.app.biz.framework.service.impl.minidev;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.template.TemplateEngine;
import cn.topcheer.halberd.app.api.framework.dto.api.ApiDTO;
import cn.topcheer.halberd.app.api.minidev.dto.ConnectorDTO;
import cn.topcheer.halberd.app.api.minidev.dto.ModelAndTemplateDTO;
import cn.topcheer.halberd.app.api.minidev.dto.ModelDTO;
import cn.topcheer.halberd.app.api.minidev.dto.TemplateDTO;
import cn.topcheer.halberd.app.api.minidev.entity.MiniForm;
import cn.topcheer.halberd.app.api.minidev.entity.Model;
import cn.topcheer.halberd.app.api.minidev.entity.TemplateFile;
import cn.topcheer.halberd.app.api.minidev.service.MiniFormConverter;
import cn.topcheer.halberd.app.api.minidev.service.MiniFormService;
import cn.topcheer.halberd.app.api.minidev.service.ModelService;
import cn.topcheer.halberd.app.api.minidev.service.TemplateService;
import cn.topcheer.halberd.app.common.utils.TemplateEngineFactory;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MiniFormSingleModelConverter implements MiniFormConverter {
    

    @Autowired
    TemplateService templateService;

    @Autowired

    MiniFormService miniFormService;

    @Autowired
    ModelService modelService;

    // @Autowired
    // ApiService apiService;

    // @Autowired
    // ApiRequestService apiRequestService;

    // @Autowired
    // DataSourceManagementService dataSourceManagementService;

    @Override
    public void convert(MiniForm miniform) throws Exception {

        if (!canHandle(miniform))
            throw new Exception("can't handle this miniform");

        ModelAndTemplateDTO modelAndTemplate = getModelAndTemplate(miniform);
        updateMiniForm(miniform, modelAndTemplate);

        //这块代码移到模型管理里去了，因为很有可能模型要调整，form不需要调整
        // if (modelAndTemplate.getModel().getApiType() != null 
        // && (modelAndTemplate.getModel().getApiType() == 1 || modelAndTemplate.getModel().getApiType() == 2)
        // ) {
        //     convertApi(modelAndTemplate.getModel(),modelAndTemplate.getModel().getApiType());
        // }

       
        // ModelDTO model = (ModelDTO)source;

        // for (TemplateFile templateFile : template.getFiles()) {
        // if(templateFile.getFileCategory().equals("table") &&
        // templateFile.getFileCode().equals("mini_form") &&
        // templateFile.getFilePath().equals("content"))
        // {
        // String script =getScript(templateFile);
        // templateFile.setFileContent(model.getTables().stream().map(t->t.getTableName()).collect(Collectors.joining(",")));
        // }
        // }

        // 生成miniform.content值

        // 生成连接器数据
        // 生成api

    }


    private void updateMiniForm(MiniForm miniform, ModelAndTemplateDTO data) {

            for (TemplateFile file : data.getTemplate().getFiles()) {
                if("table".equals(file.getFileCategory()) && "mini_form".equals(file.getFileCode())&& "content".equals(file.getFilePath())){
                    String script =getScript(file);
                    TemplateEngine templateEngine = TemplateEngineFactory.getTemplateEngine(file.getTemplateEngine());
                    Map<String,Object> map = new HashMap<>();
                    map.put("model",  data.getModel() );
                    map.put("form",miniform);
                    map.put("modelListProtypes",data.getModel().getPrototypes().stream().filter(item->item.getIsList()||item.getIsForm()).collect(Collectors.toList()));
                    map.put("modelQueryProtypes",data.getModel().getPrototypes().stream().filter(item->item.getIsQuery()).collect(Collectors.toList()));

                    List<ConnectorDTO> connectorDTOList=  data.getModel().getPrototypes().stream().
                            filter(item->(item.getIsList()||item.getIsForm())&& ObjectUtil.isNotEmpty(item.getDictCode()))
                            .map(item->  item.getDictCode())
                            .distinct()
                            .map(item->{
                                ConnectorDTO connectorDTO=new ConnectorDTO();
                                connectorDTO.setId("mini-"+miniform.getFormKey()+"-dictbiz-"+item);
                                connectorDTO.setCode("dictbiz-"+item);
                                connectorDTO.setName(item);
                                connectorDTO.setMethod("get");
                                connectorDTO.setType("3");
                                connectorDTO.setAddToRuntime(true);
                                connectorDTO.setDictCode(item);
                                return connectorDTO;
                            }).collect(Collectors.toList());
                    map.put("connectors",connectorDTOList);
                    //System.out.println("map: "+ JSON.toJSONString(map));
                    String result = templateEngine.getTemplate(script).render(map);;

                    miniform.setContent(result);
                    miniFormService.updateById(miniform);
                }
            }

    }

    private void convertConnector(ModelDTO model, List<ApiDTO> apis) throws Exception {

    }

    //api 生成移到模型管理里去了

    /*
    private void convertApi(ModelDTO model, int type) throws Exception {

        Api parentApi = saveOrUpdateParentApi(model);
        saveOrUpdateChildApis(parentApi,model);

        
    }

    private void saveOrUpdateChildApis(Api parentApi, ModelDTO model) {

        List<Api> subApis = apiService.list(Wrappers.lambdaQuery(Api.class).eq(Api::getApiId, parentApi.getId()));

        // insert
        String insertPath = model.getInsertApi();
        String updatePath = model.getUpdateApi();  
        String deletePath = model.getDeleteApi();
        String selectPath =  model.getSelectApi();
        Api insertApi,deleteApi,updateApi,selectApi;

        // get or create insertApi
        Optional<Api> optApi = subApis.stream().filter(a -> (insertPath).equals(a.getPath())).findFirst();
        if (!optApi.isPresent()) {
            insertApi = createChildApi("新增", insertPath, "insert", parentApi);
        } else {
            insertApi = optApi.get();
        }

        // get or create deleteApi
        optApi = subApis.stream().filter(a -> (deletePath).equals(a.getPath())).findFirst();
        if (!optApi.isPresent()) {
            deleteApi = createChildApi("删除", deletePath, "delete", parentApi);
        } else {
            deleteApi = optApi.get();
        }

        // get or create updateApi
        optApi = subApis.stream().filter(a -> (updatePath).equals(a.getPath())).findFirst();
        if (!optApi.isPresent()) {
            updateApi = createChildApi("更新", updatePath, "update", parentApi);
        } else {
            updateApi = optApi.get();
        }

        // get or create selectApi
        optApi = subApis.stream().filter(a -> (selectPath).equals(a.getPath())).findFirst();
        if (!optApi.isPresent()) {
            selectApi = createChildApi("查询", selectPath, "select", parentApi);
        } else {
            selectApi = optApi.get();
        }

        List<ModelPrototype> properties = model.getPrototypes();
        ApiRequest apirequest;
        for (ModelPrototype pro : properties) {
            if(pro.getIsReadonly()==null||!pro.getIsReadonly()){
                apirequest=saveOrUpdateApiRequest(insertApi,pro,null);


                apirequest=saveOrUpdateApiRequest(updateApi,pro,a->{
                    if(pro.getIsPrimarykey()!=null&& pro.getIsPrimarykey()){
                        a.setWhereby(true);
                        if(compareTypeMapping.containsKey(pro.getQueryType())){
                            a.setCompared(compareTypeMapping.get(pro.getQueryType()));
                        }else{
                            a.setCompared(CompareType.eq);
                        }
                    }
                    return true;
                });
            }
            if(pro.getIsPrimarykey()!=null&& pro.getIsPrimarykey()){
                apirequest=saveOrUpdateApiRequest(deleteApi,pro,a->{
                        a.setWhereby(true);
                        if(compareTypeMapping.containsKey(pro.getQueryType())){
                            a.setCompared(compareTypeMapping.get(pro.getQueryType()));
                        }else{
                            a.setCompared(CompareType.eq);
                        }
                    return true;
                });


            }

            if(pro.getIsQuery()!=null&& pro.getIsQuery()){
                apirequest=saveOrUpdateApiRequest(selectApi,pro,a->{
                        a.setWhereby(true);
                        if(compareTypeMapping.containsKey(pro.getQueryType())){
                            a.setCompared(compareTypeMapping.get(pro.getQueryType()));
                        }else{
                            a.setCompared(CompareType.eq);
                        }
                    return true;
                });

            }


        }

    }
 */
    /**
     * 根据Api与ModelPrototype，新增或修改 ApiRequest，并保存到数据库中。
     * @param api
     * @param pro
     * @param beforeSave，保存前的回调函数，可以对ApiRequest做进一步处理，如果该回调函数返回值为false，则不保存。如果该参数传入null,则ApiRequest还是保存到数据库中。
     * @return
     */
    // private ApiRequest saveOrUpdateApiRequest(Api api, ModelPrototype pro,Function<ApiRequest,Boolean> beforeSave) {

    //     ApiRequest apirequest = apiRequestService
    //             .getOne(Wrappers.lambdaQuery(ApiRequest.class).eq(ApiRequest::getApiId, api.getId())
    //                     .eq(ApiRequest::getName, pro.getJdbcName()));
    //     if (apirequest == null) {
    //         apirequest = new ApiRequest();
    //         apirequest.setApiId(api.getId());
    //         apirequest.setName(pro.getJdbcName());
    //         apirequest.setFilled(false);
    //         apirequest.setWhereby(false);
    //         apirequest.setSource("param");
            
    //         if (typeMapping.containsKey(pro.getPropertyType())) {
    //             apirequest.setType(typeMapping.get(pro.getPropertyType()));
    //         } else {
    //             apirequest.setType(typeMapping.get("string"));
    //         }

    //     }
    //     if(beforeSave!=null){
    //         if(!beforeSave.apply(apirequest)){
    //             return apirequest;
    //         }
    //     }
    //     apiRequestService.saveOrUpdate(apirequest);
    //     return apirequest;

    // }

//     private Api createChildApi(String name, String path, String type, Api parentApi) {
//         Api newApi = new Api();
//         newApi.setName(name);
//         newApi.setPath(path);
//         newApi.setType(type);
//         newApi.setApiId(parentApi.getId());
//         newApi.setSchemaName(parentApi.getSchemaName());
//         newApi.setTableName(parentApi.getTableName());
//         newApi.setSourceType(parentApi.getSourceType());
// //        newApi.setDevState(ApiState.undev);
// //        newApi.setProdState(ApiState.undev);
//         newApi.setPaginationBit(true);
//         newApi.setDatasourceId(parentApi.getDatasourceId());
//         newApi.setServiceId(parentApi.getServiceId());
//         apiService.save(newApi);
//         return newApi;
//     }

//     private Api saveOrUpdateParentApi(ModelDTO model) {

//         DataSourceAndType dataSourceAndType = dataSourceManagementService.getDataSourceAndType(model.getDatasourceId());

//         String path = modelService.getApiModelPrefix(model);
//         Api parentApi = apiService.getOne(Wrappers.lambdaQuery(Api.class).eq(Api::getPath, path));
//         if (parentApi == null) {
//             parentApi = new Api();
//             parentApi.setName(model.getModelName());
//             parentApi.setPath(path);
//             parentApi.setType("exchange");

//             if (!StringUtil.hasText(model.getSchemaName())) {
//                 String currentSchema = dataSourceAndType.getMetaData().getCurrentSchema();
//                 if (StringUtil.hasText(currentSchema)) {
//                     model.setSchemaName(currentSchema);
//                 }

//             }
//             parentApi.setSchemaName(model.getSchemaName());
//             parentApi.setTableName(model.getModelTable());
//             parentApi.setSourceType(DataSourceType.compatibleValueOf(dataSourceAndType.getType()));
// //            parentApi.setDevState(ApiState.undev);
// //            parentApi.setProdState(ApiState.undev);
//             parentApi.setDatasourceId(model.getDatasourceId());
//             // TODO:这里临时写成这样，以后感觉要从建model时选择
//             parentApi.setServiceId(1645981268779732994L);
//             parentApi.setPaginationBit(true);
//             apiService.save(parentApi);

//         }
//         return parentApi;

//     }

    private ModelAndTemplateDTO getModelAndTemplate(MiniForm miniform) {
        Model model = modelService.getById(miniform.getModelId());
        ModelDTO modelDTO = modelService.getDTO(model);

        TemplateDTO templateDTO = templateService.getTemplateDTO(miniform.getTemplateId());
        ModelAndTemplateDTO mt = new ModelAndTemplateDTO(modelDTO, templateDTO);
        return mt;
    }

    @Override
    public boolean canHandle(MiniForm miniform) {

        if (!StringUtil.hasText(miniform.getModelId()) || !StringUtil.hasText(miniform.getTemplateId())) {
            return false;
        }

        return true;
    }

    private String getScript(TemplateFile templateFile) {
        return new String(Base64.getDecoder().decode(templateFile.getTemplateContent()), StandardCharsets.UTF_8);
    }

    // private static final Map<String, FieldType> typeMapping = new HashMap<>();
    // private static final Map<String, CompareType> compareTypeMapping = new HashMap<>();
    // static {
    //     typeMapping.put(PropertyTypeEnum.STRING.getValue(), FieldType.String);
    //     typeMapping.put(PropertyTypeEnum.BOOLEAN.getValue(), FieldType.Integer);
    //     typeMapping.put(PropertyTypeEnum.INTEGER.getValue(), FieldType.Integer);
    //     typeMapping.put(PropertyTypeEnum.DATETIME.getValue(), FieldType.LocalDateTime);
    //     typeMapping.put(PropertyTypeEnum.NUMBER.getValue(), FieldType.Double);


    //     compareTypeMapping.put("equal", CompareType.eq);
    //     compareTypeMapping.put("notequal", CompareType.ne);
    //     compareTypeMapping.put("gt", CompareType.gt);
    //     compareTypeMapping.put("ge", CompareType.ge);
    //     compareTypeMapping.put("lt", CompareType.lt);
    //     compareTypeMapping.put("le", CompareType.le);
    //     compareTypeMapping.put("like", CompareType.like);
    //     compareTypeMapping.put("likeleft", CompareType.likeLeft);
    //     compareTypeMapping.put("likeright", CompareType.likeRight);
    //     compareTypeMapping.put("none", CompareType.eq);

    // }



}
