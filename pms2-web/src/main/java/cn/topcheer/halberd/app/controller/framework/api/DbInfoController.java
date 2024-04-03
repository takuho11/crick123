package cn.topcheer.halberd.app.controller.framework.api;

import cn.topcheer.halberd.app.api.framework.entity.api.AmDataSource;
import cn.topcheer.halberd.app.api.framework.service.DbInfoService;
import cn.topcheer.halberd.app.api.framework.vo.DBResult;
import cn.topcheer.halberd.app.api.framework.vo.DbColumn;
import cn.topcheer.halberd.app.api.framework.vo.DbTableOrView;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/db-info")
@Api(value = "数据库管理", tags = "API-数据库管理")
@Validated
public class DbInfoController{

    @Autowired
    private DbInfoService dbInfoService;

    @GetMapping("/datasources")
    public R<List<AmDataSource>> getEnableDataSources(){
        return R.data(dbInfoService.getEnableDataSources());
    }

    /**
     * 获取自己创建的数据源
     * @return 数据源列表
     */
    @GetMapping("/myDatasources")
    public R<List<AmDataSource>> getMyDataSources(){
        return R.data(dbInfoService.getMyDataSources());
    }


    @GetMapping("/schemas")
    public R<List<String>> getSchemas(Long dataSourceId){
        return R.data(dbInfoService.getSchemas(dataSourceId));
    }


    @GetMapping("/tables")
    public R<List<DbTableOrView>> getAllTableOrViews(Long dataSourceId, String schema){
        return R.data(dbInfoService.getAllTableOrViews(dataSourceId, schema));
    }

    @GetMapping("/tablesByName")
    public R<List<DbTableOrView>> getAllTableOrViews(Long dataSourceId, String schema, String name){
        if (name == null){
            return R.data(dbInfoService.getAllTableOrViews(dataSourceId, schema));
        }
        return R.data(dbInfoService.getAllTableOrViewsByName(dataSourceId, schema, name));
    }


    @GetMapping("/table-detail")
    public R<DbTableOrView> getTableOrView(Long dataSourceId, String schema, String tableOrViewName){
        return R.data(dbInfoService.getTableOrView(dataSourceId, schema, tableOrViewName));
    }


    @GetMapping("/columns")
    public R<List<DbColumn>> getColumns(Long dataSourceId, String schema, String tableOrViewName){
        return R.data(dbInfoService.getColumns(dataSourceId, schema, tableOrViewName));
    }


    @GetMapping("/db/**")
    public R<DBResult> findByPath(HttpServletRequest request, String type){
        String[] paths = request.getRequestURI().substring((request.getContextPath() + "/db-info/db").length()).split("/");
        List<String> pathList = new ArrayList<>();
        try{
            for(String path : paths){
                if(!StringUtils.isBlank(path)){
                    pathList.add(java.net.URLDecoder.decode(path, StandardCharsets.UTF_8.name()));
                }
            }
        }catch (java.io.UnsupportedEncodingException e){
            return R.fail(request.getRequestURI() + "解析失败");
        }
        return R.data(dbInfoService.getDbInfoByPath(pathList, type));
    }

}
