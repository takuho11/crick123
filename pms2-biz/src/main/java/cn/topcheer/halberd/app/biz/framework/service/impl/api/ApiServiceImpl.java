package cn.topcheer.halberd.app.biz.framework.service.impl.api;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.template.TemplateUtil;

import cn.topcheer.halberd.app.api.framework.dto.api.*;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApi;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiRequest;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiResponse;
import cn.topcheer.halberd.app.api.framework.entity.client.BladeClient;
import cn.topcheer.halberd.app.api.framework.enumerate.*;
import cn.topcheer.halberd.app.api.framework.service.api.ApiRequestService;
import cn.topcheer.halberd.app.api.framework.service.api.ApiResponseService;
import cn.topcheer.halberd.app.api.framework.service.api.ApiService;
import cn.topcheer.halberd.app.api.framework.service.client.BladeClientService;
import cn.topcheer.halberd.app.api.framework.vo.DbColumn;
import cn.topcheer.halberd.app.api.framework.wrapper.AmApiWrapper;
import cn.topcheer.halberd.app.biz.framework.service.DataSourceManagementService;
import cn.topcheer.halberd.app.biz.sql.SqlHelper;
import cn.topcheer.halberd.app.common.utils.TemplateEngineFactory;
import cn.topcheer.halberd.app.dao.db.DataSourceAndType;
import cn.topcheer.halberd.app.dao.db.metadata.IDbMetaData;
import cn.topcheer.halberd.app.dao.mapper.framework.api.ApiMapper;
import cn.topcheer.halberd.tool.DomesticUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.net.URI;
import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Slf4j
@Service
public class ApiServiceImpl extends BaseServiceImpl<ApiMapper, AmApi> implements ApiService {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private ApiRequestService requestService;
    @Resource
    private ApiResponseService responseService;
    @Resource
    private DataSourceManagementService dataSourceService;
    @Resource
    private BladeClientService bladeClientService;

    @Override
    public R saveOrUpdate(ApiDTO dto) {
        // 根据路径查询此路径的Api是否已经存在
        LambdaQueryWrapper apiWrapper = new LambdaQueryWrapper<AmApi>()
                .eq(AmApi::getPath, dto.getPath()).eq(AmApi::getIsDeleted, 0);
        AmApi api = super.getOne(apiWrapper);
        if (api != null && !api.getId().equals(dto.getId())) {
            return R.fail("当前Api路径已经存在！");
        }
        // 保存API
        boolean success;
        if (StringUtils.isEmpty(dto.getId())) {
            dto.setId(null);
            dto.setStatus(0);
            // 管理员新增直接审批通过
            dto.setApproveStatus(AuthUtil.isAdmin() ? 2 : 0);
        }

        success = super.saveOrUpdate(dto);
        // 删除API的所有下级
        success = success && removeApiChild(dto);
        // 保存ApiRequest及响应
        success = success && saveApiChild(dto);
        // 保存子Api请求
        if (Objects.nonNull(dto.getChildren())) {
            if (dto.getChildren().size() > 0) {
                dto.getChildren().forEach(childDto -> {
                    childDto.setId(null);
                    childDto.setServiceId(dto.getServiceId());
                    childDto.setPaginationBit(dto.getPaginationBit());
                    childDto.setSchemaName(dto.getSchemaName());
                    childDto.setTableName(dto.getTableName());
                    childDto.setSourceType(dto.getSourceType());
                    childDto.setDatasourceId(dto.getDatasourceId());
                    childDto.setApiId(dto.getId());
                    this.saveOrUpdate(childDto);
                });
            }
        }
        return R.status(success);
    }

    private boolean removeApiChild(ApiDTO dto) {
        boolean success = true;

        // 交互类型删除子类API
        if ("exchange".equals(dto.getType())) {
            LambdaQueryWrapper childWrapper = new LambdaQueryWrapper<AmApi>()
                    .eq(AmApi::getApiId, dto.getId());
            List<AmApi> childList = this.list(childWrapper);
            if (childList.size() > 0) {
                childList.forEach(child -> {
                    Map<String, Object> columnMap = new HashMap<>();
                    columnMap.put("api_id", child.getId());
                    // 移除子Api下的请求和响应
                    requestService.removeByMap(columnMap);
                    responseService.removeByMap(columnMap);
                });
                success = success && this.removeBatchByIds(childList);
            }
        } else {
            // 其他类型就删除原有的请求和响应
            LambdaQueryWrapper requestWrapper = new LambdaQueryWrapper<AmApiRequest>()
                    .eq(AmApiRequest::getApiId, dto.getId());
            LambdaQueryWrapper responseWrapper = new LambdaQueryWrapper<AmApiResponse>()
                    .eq(AmApiResponse::getApiId, dto.getId());
            List<AmApiRequest> requestList = requestService.list(requestWrapper);
            List<AmApiResponse> responseList = responseService.list(responseWrapper);
            if (requestList.size() > 0) {
                success = success && requestService.removeBatchByIds(requestList);
            }

            if (responseList.size() > 0) {
                success = success && responseService.removeBatchByIds(responseList);
            }
        }
        return success;
    }

    private boolean saveApiChild(ApiDTO dto) {
        boolean success = true;
        if (Objects.nonNull(dto.getRequests())) {
            if (dto.getRequests().size() > 0) {
                int sortNumber = 0;
                for (AmApiRequest request : dto.getRequests()) {
                    request.setId(UUID.randomUUID().toString());
                    request.setApiId(dto.getId());
                    request.setSortNumber(sortNumber);
                    sortNumber++;
                }
                success = success && requestService.saveBatch(dto.getRequests());
            }
        }
        // 保存ApiResponse
        if (Objects.nonNull(dto.getResponses())) {
            if (dto.getResponses().size() > 0) {
                int sortNumber = 0;
                for (AmApiResponse response : dto.getResponses()) {
                    response.setId(UUID.randomUUID().toString());
                    response.setApiId(dto.getId());
                    response.setSortNumber(sortNumber);
                    sortNumber++;
                }

                success = success && responseService.saveBatch(dto.getResponses());
            }
        }
        return success;
    }

    @Override
    public ApiDTO getApiDTO(AmApi api) {
        api = this.getById(api.getId());
        ApiDTO dto = AmApiWrapper.build().entityVO(api);

        if ("exchange".equals(dto.getType())) {
            LambdaQueryWrapper childWrapper = new LambdaQueryWrapper<AmApi>()
                    .eq(AmApi::getApiId, dto.getId());
            List<AmApi> childList = this.list(childWrapper);
            List<ApiDTO> dtos = new ArrayList<>();
            childList.forEach(child -> {
                ApiDTO apiDTO = this.getApiDTO(child);
                dtos.add(apiDTO);
            });
            dto.setChildren(dtos);
        } else {
            LambdaQueryWrapper<AmApiRequest> requestWrapper = new LambdaQueryWrapper<AmApiRequest>()
                    .eq(AmApiRequest::getApiId, dto.getId())
                    // 查询接口，仅仅返回查询配置的字段
                    .eq("select".equals(dto.getType()), AmApiRequest::getWhereby, 1)
                    .orderByAsc(AmApiRequest::getSortNumber);
            LambdaQueryWrapper<AmApiResponse> responseWrapper = new LambdaQueryWrapper<AmApiResponse>()
                    .eq(AmApiResponse::getApiId, dto.getId())
                    .orderByAsc(AmApiResponse::getSortNumber);
            List<AmApiRequest> requestList = requestService.list(requestWrapper);
            List<AmApiResponse> responses = responseService.list(responseWrapper);
            dto.setRequests(requestList);
            dto.setResponses(responses);
        }
        return dto;
    }

    @Override
    public Object debug(ApiDTO apiDTO, List<AmApiRequest> requests, HttpServletRequest req, HttpServletResponse res) {
        if ("templateScript".equals(apiDTO.getType())) {
            JSONObject params = new JSONObject();
            if (apiDTO.getRequests() != null && apiDTO.getRequests().size() > 0) {
                apiDTO.getRequests().forEach(r -> {
                    params.put(r.getName(), r.getSample());
                });
            }
            return processTemplateScript(apiDTO, params);
        }

        if (requests == null) {
            return this.process(apiDTO, null, req, res);
        } else {
            // 参数
            Map<String, Object> params = requests.stream().filter(request -> StringUtils.isNotEmpty(request.getSample())).collect(Collectors.toMap(AmApiRequest::getName, AmApiRequest::getSample));

            // 排序
            params.put("orders", apiDTO.getOrders());

            // 自定义查询语句
            params.put("customQuerySql", apiDTO.getCustomQuerySql());

            return this.process(apiDTO, new JSONObject(params), req, res);
        }
    }

    @Override
    public Object process(@NotNull String apiPath, JSONObject params, HttpServletRequest req, HttpServletResponse res) {
        // 根据api路径查询是否存在该Api
        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper<AmApi>()
                .eq(AmApi::getPath, "/" + apiPath)
                .eq(AmApi::getIsDeleted, 0);
        AmApi api = this.getOne(queryWrapper);
        if (api == null)
            return Result.failOf("访问不存在的Api！" + apiPath);
        return this.process(api, params, req, res);
    }

    @Override
    public Object process(@NotNull AmApi api, JSONObject params, HttpServletRequest req, HttpServletResponse res) {
        Object result = null;
        ApiType apiType;
        try {
            apiType = ApiType.valueOf(api.getType());
        } catch (IllegalArgumentException e) {
            apiType = ApiType.exchange;
        }
        switch (apiType) {
            case script:
                result = this.process(api, params);
                break;
            case service:
                result = this.proxy(api, params, req, res);
                break;
            case templateScript:
                result = this.processTemplateScript(api, params);
                break;
            default:
                // exchange
                R reqResult = this.fillParams(api, params);
                if (!reqResult.isSuccess()) {
                    return Result.failOf(reqResult.getMsg());
                }

                List<AmApiRequest> requests = (List) reqResult.getData();
                result = this.processExchange(api, requests, params);
        }

        return result;
    }

    private Result processExchange(AmApi api, List<AmApiRequest> requests, JSONObject params) {
        Result result = null;

        ArrayList<Object> values = new ArrayList<>();
        ApiChildType childType = ApiChildType.valueOf(api.getType());
        DataSource dataSource = dataSourceService.getDataSource(api.getDatasourceId());

        // 数据库名和表名前后都拼接上"`"，如果是达梦数据库，需要拼接双引号",避免冲突关键字
        String tableName;
        if (api.getSourceType().equals(DataSourceType.DM)) {
            tableName = "\"" + api.getSchemaName() + "\".\"" + api.getTableName() + "\"";
        } else {
            tableName = "`" + api.getSchemaName() + "`.`" + api.getTableName() + "`";
        }

        switch (childType) {
            case select:
                // sql语句
                String sql = this.createSelectSQL(requests, tableName, values);

                // 自定义查询条件功能先注释掉，这个风险比较大
//                // 自定义查询条件
//                String customQuerySql = params.getString("customQuerySql");
//                if (StringUtils.isNotBlank(customQuerySql)) {
//                    if (!sql.contains("where")) {
//                        sql += " where 1=1 ";
//                    }
//
//                    sql += " " + customQuerySql + " ";
//                }


                // 排序参数
                List<OrderItem> orderItems = null;
                JSONArray orders = params.getJSONArray("orders");
                if (orders != null && orders.size() > 0) {
                    orderItems = orders.toJavaList(OrderItem.class);
                }

                // 分页参数
                Object current = Objects.nonNull(params.get("current")) ? params.get("current") : params.get("pageNum");
                Object pageSize = Objects.nonNull(params.get("size")) ? params.get("size") : params.get("pageSize");
                PageDTO page = null;
                if (Objects.nonNull(current) && Objects.nonNull(pageSize)) {
                    page = PageDTO.of(Integer.parseInt(current.toString()), Integer.parseInt(pageSize.toString()));
                }

                // 查询
                result = this.readDB(dataSource, sql, values, page, orderItems);
                break;
            case insert:
                sql = this.createInsertSQL(requests, tableName, values);
                result = this.writeDB(dataSource, sql, values);
                break;
            case update:
                sql = this.createUpdateSQL(requests, tableName, values);
                result = this.writeDB(dataSource, sql, values);
                break;
            case delete:
                sql = this.createDeleteSQL(requests, tableName, values);
                result = this.writeDB(dataSource, sql, values);
                break;
        }
        return result;
    }

    @Override
    public Result process(@NotNull AmApi api, JSONObject params) {
        R reqResult = this.fillParams(api, params);
        if (!reqResult.isSuccess())
            return Result.failOf(reqResult.getMsg());
        List<AmApiRequest> requests = (List) reqResult.getData();

        Map<String, AmApiRequest> requestMap = requests.stream()
                .collect(Collectors.toMap(AmApiRequest::getName, e -> e, (oldData, newData) -> oldData));
        String script = api.getScript();
        if (StringUtils.isEmpty(script))
            return Result.failOf("接口无脚本");

        String regex = "\\$\\{([0-9a-zA-Z_]+)\\}";
        Pattern pt = Pattern.compile(regex);
        Matcher matcher = pt.matcher(script);
        int i = 1;
        Map<Integer, AmApiRequest> paramMap = new HashMap<>();
        while (matcher.find()) {
            String key = matcher.group(1);
            matcher.start();
            AmApiRequest request = requestMap.get(key);
            paramMap.put(i++, request);
        }
        Map<String, String> map = new HashMap<>();
        for (String key : requestMap.keySet()) {
            map.put(key, "?");
        }
        script = TemplateUtil.createEngine().getTemplate(script).render(map);

        // 动态替换数据库用户
        DataSource dataSource = dataSourceService.getDataSource(api.getDatasourceId().toString());
        // 注意，这里有个比较坑的地方，params == null 代表他是从debug过来的，这时候取的内容是不一样的，
        // 下面的selectSQL方法取的是debug的一条数据,而不是指取不分页的所有数据
        if (params == null) {
            return this.selectSQL(script, paramMap, dataSource);
        } else {
            Integer pageNum = params.getInteger("pageNum");
            Integer pageSize = params.getInteger("pageSize");
            if (pageSize == null) {
                pageNum = params.getInteger("current");
                pageSize = params.getInteger("size");
            }
            if (api.getPaginationBit()) {
                if (pageNum == null)
                    pageNum = 1;
                if (pageSize == null)
                    pageSize = 100;
            } else {
                pageNum = 1;
                pageSize = 10000; // 暂定部分也 最多返回一万条
            }
            PageDTO page = PageDTO.of(pageNum, pageSize);
            return pageSQL(script, paramMap, dataSource, page);
        }
    }

    private Result selectSQL(String select, Map<Integer, AmApiRequest> paramMap, DataSource dataSource) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(select, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            if (paramMap.size() > 0) {
                for (Integer key : paramMap.keySet()) {
                    try {
                        statement.setObject(key, paramMap.get(key).getParamValue());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();

            List<AmApiResponse> responses = new ArrayList<>();
            if (resultSet.next()) {
                for (int i = 1; i <= count; i++) {
                    String fullTypeName = metaData.getColumnClassName(i);

                    AmApiResponse response = new AmApiResponse();
                    response.setName(metaData.getColumnLabel(i));
                    response.setBindField(metaData.getColumnLabel(i));
                    response.setSample(resultSet.getObject(i) == null ? "" : resultSet.getObject(i).toString());
                    FieldType type = FieldType.String;
                    try {
                        type = FieldType.valueOf(fullTypeName.substring(fullTypeName.lastIndexOf(".") + 1).toString());
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                    response.setType(type);
                    responses.add(response);
                }
            }
            return Result.successOf(responses);
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.failOf(
                    "AmApiService selectSQL方法错误:" + e.getSQLState() + "," + e.getErrorCode() + ":" + e.getMessage());
        } finally {
            try {
                if (connection != null && !connection.isClosed())
                    connection.close();
                if (statement != null && !statement.isClosed())
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Result pageSQL(String select, Map<Integer, AmApiRequest> paramMap, DataSource dataSource, PageDTO page) {
        Connection connection = null;
        PreparedStatement statement = null;
        int start = (page.getCurrentPage() - 1) * page.getPageSize();
        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(select, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            if (paramMap.size() > 0) {
                for (Integer key : paramMap.keySet()) {
                    try {
                        statement.setObject(key, paramMap.get(key).getParamValue());

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();

            List<Map> list = new ArrayList<>();
            if (start > 0) {
                resultSet.absolute(start);
            }
            int r = 1;
            while ((r <= page.getPageSize()) && resultSet.next()) {
                Map<String, Object> rtnMap = new HashMap<>();
                for (int i = 1; i <= count; i++) {
                    rtnMap.put(metaData.getColumnLabel(i), resultSet.getObject(i));
                }
                list.add(rtnMap);
                r++;
            }
            if (!resultSet.isLast()) {
                resultSet.last();
            }
            int size = resultSet.getRow();
            PageDTO pageDTO = PageDTO.of(start, page.getPageSize(), size);
            pageDTO.setRows(list);
            return Result.successOf(pageDTO);
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.failOf(
                    "AmApiService pageSQL方法错误:" + e.getSQLState() + "," + e.getErrorCode() + ":" + e.getMessage());
        } finally {
            try {
                if (connection != null && !connection.isClosed())
                    connection.close();
                if (statement != null && !statement.isClosed())
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Object proxyGet(AmApi api, JSONObject params, HttpServletRequest request, HttpServletResponse res) {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
        if (Objects.nonNull(params) && params.size() > 0) {
            params.keySet().forEach(key -> values.add(key, params.getString(key)));
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(api.getTargetUrl());
        URI uri = builder.queryParams(values).build().encode().toUri();

        if (api.getAuthorize()) {
            // 请求头
            proxyheaders(headers);
        }
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(values, headers);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
            return JSON.parse(response.getBody());
        } catch (RestClientException e) {
            //接口地址不存在
            e.printStackTrace();
            return e.getMessage();
        }
    }

    private Object proxyPost(AmApi api, JSONObject params, HttpServletRequest request, HttpServletResponse res) {
        String target = api.getTargetUrl();
        // 拿到header信息,处理JSON字符串请求
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String header = request.getHeader(headerName);
            headers.add(headerName, header);
        }
        if (api.getAuthorize()) {
            // 请求头
            proxyheaders(headers);
        }
        HttpEntity<JSONObject> entity = new HttpEntity(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(target, entity, String.class);
        Object list = JSON.parse(response.getBody());

        // 当需要分页时，返回分页信息
        // res.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
        // res.setHeader("Content-Range", String.valueOf(list.size()));
        // res.setHeader("Access-Control-Expose-Headers", "Content-Range");

        return list;
    }

    /**
     * 请求头
     *
     * @param headers HttpHeaders
     * @author szs
     * @date 2023-07-25
     */
    private void proxyheaders(HttpHeaders headers) {
        // 客户端id
        String clientId = AuthUtil.getClientId();
        if (StringUtils.isNotBlank(clientId)) {
            // 获取客户端应用
            QueryWrapper<BladeClient> qw = new QueryWrapper<>();
            qw.eq("client_id", clientId);
            qw.eq("is_deleted", 0);
            qw.last(" LIMIT 1 ");
            BladeClient bladeClient = bladeClientService.getOne(qw);
            if (bladeClient == null) {
                throw new IllegalStateException("应用不存在");
            }

            String requestTime = String.valueOf(System.currentTimeMillis());
            headers.add("appKey", bladeClient.getClientId());
            headers.add("sign", DomesticUtil.sm3(bladeClient.getClientId() + bladeClient.getClientSecret() + requestTime));
            headers.add("requestTime", requestTime);
        }

    }

    public Object proxy(AmApi api, JSONObject params, HttpServletRequest req, HttpServletResponse res) {
        if (api instanceof ApiDTO) {
            ApiDTO dto = (ApiDTO) api;
            if (Objects.nonNull(dto.getRequests())) {
                JSONObject innerParams = new JSONObject();
                dto.getRequests().forEach(request -> innerParams.put(request.getName(), request.getSample()));
                params = innerParams;
            }
        }
        if ("GET".equals(api.getMethod().toUpperCase())) {
            return this.proxyGet(api, params, req, res);
        } else
            return this.proxyPost(api, params, req, res);
    }

    // 根据数据库用户查询所有可访问用户
    @Override
    public R getAllUsersByDataSource(String dataSourceId) {
        DataSource dataSource = dataSourceService.getDataSource(dataSourceId);
        String exeSql = "SELECT schema_name \"schemaName\" from information_schema.schemata order by schema_name";
        List list = this.selectByDataSource(dataSource, exeSql);
        return R.data(list);
    }

    // 根据数据库用户查询表结构
    @Override
    public R getAllTablesByDataSource(String dataSourceId, String dataBase) {
        DataSource dataSource = dataSourceService.getDataSource(dataSourceId);
        String exeSql = "select table_name \"tableName\",table_comment \"tableComment\" " +
                "from information_schema.tables where table_schema= '" + dataBase + "'";
        return R.data(this.selectByDataSource(dataSource, exeSql));
    }

    // 根据数据库用户查询表结构
    @Override
    public R getTableStructByUser(AmApi api) {
        DataSourceAndType dataSource = dataSourceService.getDataSourceAndType(api.getDatasourceId().toString());
        IDbMetaData metaData = dataSource.getMetaData();
        List<DbColumn> dbColumns = metaData.getColumns(api.getSchemaName(), api.getTableName());

        List<AmApiRequest> selectReqs = new ArrayList<>();
        dbColumns.stream().forEach(column -> {
            AmApiRequest request = new AmApiRequest();
            request.setName(column.getColumnName());
            request.setType(FieldType.String);
            request.setSource("param");
            request.setMemo(column.getComment());

            request.setPrimaryKey(column.isPrimaryKey());
            request.setFilled(false);
            request.setUpdated(true);
            request.setReturned(true);
            request.setWhereby(true);
            request.setCompared(CompareType.eq);
            selectReqs.add(request);
        });

        return R.data(selectReqs);
    }

    // 填充参数
    private R fillParams(AmApi api, JSONObject params) {
        List<AmApiRequest> requests;
        if (api instanceof ApiDTO) {
            ApiDTO dto = (ApiDTO) api;
            requests = dto.getRequests();

        } else {
            LambdaQueryWrapper requestWrapper = new LambdaQueryWrapper<AmApiRequest>().eq(AmApiRequest::getApiId, api.getId());
            requests = requestService.list(requestWrapper);

        }

        if (requests.size() > 0) {
            for (AmApiRequest request : requests) {
                if (api instanceof ApiDTO) {
                    request.setParamValue(params.get(request.getName()));
                } else {
                    // 如果为空，先赋值为 param
                    if (StringUtils.isEmpty(request.getSource()))
                        request.setSource("param");
                    // 接口参数、当前时间、uuid、longid
                    switch (request.getSource()) {
                        case "now":
                            request.setParamValue(new Date());
                            break;
                        case "userid":
                            BladeUser user = AuthUtil.getUser();
                            request.setParamValue(Objects.nonNull(user.getUserId()) ? user.getUserId() : user.getClientId());
                            break;
                        case "uuid":
                            request.setParamValue(UUID.randomUUID().toString());
                            break;
                        case "longid":
                            request.setParamValue(ThreadLocalRandom.current().nextLong(100000000000L, 999999999999L + 1));
                            break;
                        default:
                            request.setParamValue(params.get(request.getName()));
                    }

                    if (request.getFilled() && request.getParamValue() == null) {
                        return R.fail("必填参数：" + request.getName() + " 为空！");
                    }
                }
            }
        }

        return R.data(requests);
    }

    // 填充参数
    private R fillParamsByList(AmApi api, JSONObject params) {
        List<AmApiRequest> requests;
        List<Object> values;
        if (api instanceof ApiDTO) {
            ApiDTO dto = (ApiDTO) api;
            requests = dto.getRequests();
        } else {
            LambdaQueryWrapper requestWrapper = new LambdaQueryWrapper<AmApiRequest>().eq(AmApiRequest::getApiId,
                    api.getId());
            requests = requestService.list(requestWrapper);
        }
        if (requests.size() > 0) {

            String jsonStr = params.toJSONString();
            JSONValidator validator = JSONValidator.from(jsonStr);
            if (validator.getType() == JSONValidator.Type.Array) {

                JSONArray jsonArray = JSON.parseArray(jsonStr);
                jsonArray.forEach(json -> {
                    // do something
                    JSONObject singleJson = (JSONObject) json;

                    requests.forEach(request -> {
                        if (api instanceof ApiDTO) {
                            request.setParamValue(request.getSample());
                        } else {
                            Object paramObj = params.get(request.getName());
                            request.setParamValue(params.get(request.getName()));
                        }
                    });

                });

                for (AmApiRequest request : requests) {
                    if (api instanceof ApiDTO) {
                        request.setParamValue(request.getSample());
                    } else {
                        Object paramObj = params.get(request.getName());
                        if (request.getFilled() && paramObj == null)
                            return R.fail("必填参数：" + request.getName() + " 为空！");
                        else
                            request.setParamValue(params.get(request.getName()));
                    }
                }
            }
        }
        return R.data(requests);
    }

    // 创建select语句
    private String createSelectSQL(List<AmApiRequest> requests, String tableName, ArrayList<Object> values) {
        String sql = "select * from " + tableName;
        List<String> fields = new ArrayList<>();
        requests.forEach(request -> {
            // 只有where的字段加入判断
            if (request.getWhereby() == null)
                request.setWhereby(false);
            if (request.getWhereby()) {
                if (ObjectUtil.isNotNull(request.getParamValue()))
                    fields.add(symbolValue(request, values));
                else if (request.getCompared() == CompareType.isNull || request.getCompared() == CompareType.notNull) {
                    fields.add(symbolValue(request, values));
                }
            }
        });
        if (fields.size() > 0) {
            return sql + " where " + StringUtil.join(fields, " and ");
        } else {
            return sql;
        }
    }

    // 创建insert语句
    private String createInsertSQL(List<AmApiRequest> requests, String tableName, ArrayList<Object> values) {
        String sql = "insert into " + tableName + " (";
        List<String> fields = new ArrayList<>();
        List<String> questions = new ArrayList<>();
        requests.forEach(request -> {
            if (!request.getWhereby()) {
                fields.add(request.getName());
                questions.add("?");
                values.add(request.getParamValue());
            }
        });
        return sql + StringUtil.join(fields) + ") values (" + StringUtil.join(questions) + ")";
    }

    // 创建update语句
    private String createUpdateSQL(List<AmApiRequest> requests, String tableName, ArrayList<Object> values) {
        String sql = "update " + tableName + " set ";
        List<String> fields = new ArrayList<>();
        List<String> whereFields = new ArrayList<>();
        ArrayList<Object> whereValues = new ArrayList<>();
        requests.forEach(request -> {
            if (!request.getWhereby()) {
                fields.add(request.getName() + "=?");
                values.add(request.getParamValue());
            } else {
                if (ObjectUtil.isNotNull(request.getParamValue()))
                    whereFields.add(symbolValue(request, whereValues));
                else if (request.getCompared() == CompareType.isNull || request.getCompared() == CompareType.notNull) {
                    whereFields.add(symbolValue(request, whereValues));
                }
            }
        });
        values.addAll(whereValues);
        return sql + StringUtil.join(fields, " , ") + " where " + StringUtil.join(whereFields, " and ");
    }

    // 创建delete语句
    private String createDeleteSQL(List<AmApiRequest> requests, String tableName, ArrayList<Object> values) {
        String sql = "delete from " + tableName + " where ";
        List<String> fields = new ArrayList<>();
        requests.forEach(request -> {
            // 只有where的字段加入判断
            if (request.getWhereby()) {
                if (ObjectUtil.isNotNull(request.getParamValue()))
                    fields.add(symbolValue(request, values));
                else if (request.getCompared() == CompareType.isNull || request.getCompared() == CompareType.notNull) {
                    fields.add(symbolValue(request, values));
                }
            }
        });
        return sql + StringUtil.join(fields, " and ");
    }

    private Result readDB(DataSource dataSource, String readSQL, ArrayList<Object> values, PageDTO page, List<OrderItem> orderItems) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Map> list = new ArrayList<>();
        try {
            if (orderItems != null && orderItems.size() > 0) {
                readSQL = appendOrderItems(readSQL, orderItems);
            }

            System.out.println("查询语句：" + readSQL);

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(readSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            int index = 1;
            for (Object param : values) {
                statement.setObject(index, param);
                index++;
            }

            // 执行查询
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();

            // 如果传入分页参数，则执行分页查询，如果未传入则查询全部
            if (page != null) {
                // 执行分页查询
                int start = (page.getCurrentPage() - 1) * page.getPageSize();
                if (start > 0) {
                    resultSet.absolute(start);
                }

                int r = 1;
                while ((r <= page.getPageSize()) && resultSet.next()) {
                    Map<String, Object> rtnMap = new HashMap<>();
                    for (int i = 1; i <= count; i++) {
                        rtnMap.put(metaData.getColumnLabel(i), resultSet.getObject(i));
                    }
                    list.add(rtnMap);
                    r++;
                }

                if (!resultSet.isLast()) {
                    resultSet.last();
                }

                int size = resultSet.getRow();
                PageDTO pageDTO = PageDTO.of(start, page.getPageSize(), size);
                pageDTO.setRows(list);
                return Result.successOf(pageDTO);
            } else {
                while (resultSet.next()) {
                    Map<String, Object> rtnMap = new HashMap<>();
                    for (int i = 1; i <= count; i++) {
                        rtnMap.put(metaData.getColumnLabel(i), resultSet.getObject(i));
                    }
                    list.add(rtnMap);
                }
                return Result.successOf(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Result
                    .failOf("ApiService readDB方法错误:" + e.getSQLState() + "," + e.getErrorCode() + ":" + e.getMessage());
        } finally {
            try {
                if (connection != null && !connection.isClosed())
                    connection.close();
                if (statement != null && !statement.isClosed())
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String appendOrderItems(String sql, List<OrderItem> orderItems) {
        if (orderItems != null && orderItems.size() > 0) {
            List<String> items = new ArrayList<>();
            orderItems.forEach(orderItem -> {
                // 判断orderItem中没有非法字符
                if (!orderItem.getColumn().matches("^[a-zA-Z0-9_]+$")) {
                    log.error("orderItem中的column字段含有非法字符:" + orderItem.getColumn());
                } else {
                    items.add(orderItem.getColumn() + " " + (orderItem.isAsc() ? "asc" : "desc"));
                }
            });

            if (items.size() > 0) {
                sql += " order by " + StringUtil.join(items, " , ");
            }
        }

        return sql;
    }

    private Result writeDB(DataSource dataSource, String writeSQL, List<Object> values) {
        log.info("语句：" + writeSQL);
        log.info("参数：" + values);
        if (values.size() == 0) {
            return Result.failOf("参数为空！");
        }

        Connection connection = null;
        PreparedStatement statement = null;
        int count;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(writeSQL);

            int index = 1;
            for (Object param : values) {
                statement.setObject(index, param);
                index++;
            }
            // 执行更新
            count = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.failOf(
                    "ApiService writeDB方法错误:" + e.getSQLState() + "," + e.getErrorCode() + ":" + e.getMessage());
        } finally {
            try {
                if (connection != null || !connection.isClosed())
                    connection.close();
                if (statement != null || !statement.isClosed())
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Result.successOf(count);
    }

    private String symbolValue(AmApiRequest request, ArrayList<Object> values) {
        String fieldCompare;
        switch (request.getCompared()) {
            case ge:
                fieldCompare = " >= ?";
                values.add(request.getParamValue());
                break;
            case gt:
                fieldCompare = " > ?";
                values.add(request.getParamValue());
                break;
            case le:
                fieldCompare = " <= ?";
                values.add(request.getParamValue());
                break;
            case lt:
                fieldCompare = " < ?";
                values.add(request.getParamValue());
                break;
            case in:
                String[] arrases = request.getParamValue().toString().split(",");
                String pattern = String.join(",", Collections.nCopies(arrases.length, "?"));
                fieldCompare = " in (" + pattern + ")";
                values.addAll(Arrays.asList(arrases));
                break;
            case between:
                arrases = request.getParamValue().toString().split(",");
                values.addAll(Arrays.asList(arrases));
                fieldCompare = " between ? and ? ";
                break;
            case like:
                fieldCompare = " like ?";
                request.setParamValue("%" + request.getParamValue() + "%");
                values.add(request.getParamValue());
                break;
            case likeLeft:
                fieldCompare = " like ?";
                request.setParamValue("%" + request.getParamValue());
                values.add(request.getParamValue());
                break;
            case likeRight:
                fieldCompare = " like ?";
                request.setParamValue(request.getParamValue() + "%");
                values.add(request.getParamValue());
                break;
            case ne:
                fieldCompare = " != ?";
                values.add(request.getParamValue());
                break;
            case isNull:
                fieldCompare = " is null";
                break;
            case notNull:
                fieldCompare = " is not null";
                break;
            default:
                fieldCompare = " = ?";
                values.add(request.getParamValue());
        }

        // 字段增加兼容关键字，前后拼接上"`"
        return "`" + request.getName() + "`" + fieldCompare;
    }

    private List selectByDataSource(DataSource dataSource, String exeSql) {
        List<Map> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(exeSql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> rtnMap = new HashMap<>();
                for (int i = 1; i <= count; i++) {
                    rtnMap.put(metaData.getColumnLabel(i), resultSet.getObject(i));
                }
                list.add(rtnMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }

                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 调试基于模板引擎的sql,主要目的是为了返回集合结构
     *
     * @param apiDTO
     * @return
     */
    private Result getResponsesInfo(ApiDTO apiDTO) {

        List<AmApiRequest> requests = apiDTO.getRequests();
        requests.forEach(t -> t.setParamValue(t.getSample()));

        // Map<String, ApiRequest> requestMap = requests.stream()
        // .collect(Collectors.toMap(ApiRequest::getName, e -> e, (oldData, newData) ->
        // oldData));

        Map<String, Object> params = requests.stream()
                .collect(Collectors.toMap(AmApiRequest::getName, AmApiRequest::getParamValue));

        String script = apiDTO.getScript();
        if (StringUtils.isEmpty(script))
            return Result.failOf("接口无脚本");

        Result<Object> result = preprocessTemplateScript(apiDTO, params);
        if (!result.isSuccess()) {
            return result;
        }
        script = (String) result.getData();

        SqlHelper.ParameterizationSql psql = SqlHelper.getParameterizationSql(script, params);

        DataSource dataSource = dataSourceService.getDataSource(apiDTO.getDatasourceId().toString());

        Result<List<AmApiResponse>> respInfos = SqlHelper.getResponseInfos(psql.getSql(), psql.getParams(), dataSource);

        LambdaQueryWrapper<AmApiResponse> responseWrapper = new LambdaQueryWrapper<AmApiResponse>()
                .eq(AmApiResponse::getApiId, apiDTO.getId());

        Map<String, AmApiResponse> oldResponses = responseService.list(responseWrapper).stream().collect(
                Collectors.toMap(AmApiResponse::getBindField, Function.identity()));

        respInfos.getData().forEach(n -> {
            if (oldResponses.containsKey(n.getBindField())) {
                AmApiResponse o = oldResponses.get(n.getBindField());
                n.setApiId(o.getApiId());
                n.setMemo(o.getMemo());
            }
        });

        return respInfos;
    }

    /**
     * 执行基于模板引擎的sql语句，
     *
     * @param api
     * @param params
     * @return
     */
    private Result processTemplateScript(@NotNull AmApi api, JSONObject params) {

        R reqResult = this.fillParams(api, params);
        if (!reqResult.isSuccess())
            return Result.failOf(reqResult.getMsg());
        // List<ApiRequest> requests = (List) reqResult.getData();

        // Map<String, ApiRequest> requestMap = requests.stream()
        //         .collect(Collectors.toMap(ApiRequest::getName, e -> e, (oldData, newData) -> oldData));

        Result<Object> result = preprocessTemplateScript(api, params);
        if (!result.isSuccess()) {
            return result;
        }

        String script = (String) result.getData();

        SqlHelper.ParameterizationSql psql = SqlHelper.getParameterizationSql(script, params);

        // 动态替换数据库用户
        DataSource dataSource = dataSourceService.getDataSource(api.getDatasourceId().toString());
        return SqlHelper.selectSQL(psql.getSql(), psql.getParams(), dataSource);

    }

    private static final String OLD_PARAM_REGEX = "\\$\\{([0-9a-zA-Z_]+)\\}";

    /**
     * 预处理脚本
     * 先把sql参数${...},而是换成{:...},是为了避免脚本引擎执行时把这部分值直接写入，这样就会引入注入风险了
     */
    private Result<Object> preprocessTemplateScript(@NotNull AmApi api, Map params) {

        String script = api.getScript();
        if (StringUtils.isEmpty(script))
            return Result.failOf("接口无脚本");

        script = script.replaceAll(OLD_PARAM_REGEX, "{:$1}");

        // todo: 2023/05/22 ly: 这里用freemarker,因为beetl+hutool不会甩出异常，这个是比较麻烦的事情
        try {
            script = TemplateEngineFactory.getTemplateEngine("freemarker").getTemplate(script).render(params);
            return Result.successOf(script);
        } catch (Exception ex) {
            log.error("apiservice-791", ex);
            return Result.failOf("sql语句解析出错，详细参考后台出错信息(日志定位：apiservice-791)");
        }

    }

    @Override
    public IPage listAndAuth(IPage page, Map params) {
        QueryWrapper wrapper = new QueryWrapper<>();
        // 部门ID
        wrapper.eq(Objects.nonNull(params.get("deptId")), "d.id", params.get("deptId"));
        // 所属ID
        wrapper.eq(Objects.nonNull(params.get("service_id")), "a.service_id", params.get("service_id"));
        // Api名称
        wrapper.like(Objects.nonNull(params.get("name")), "a.name", params.get("name"));
        // 创建人
        wrapper.like(Objects.nonNull(params.get("create_user_name")), "s.name", params.get("create_user_name"));
        // Api类型
        wrapper.eq(Objects.nonNull(params.get("type")), "a.type", params.get("type"));
        // Api路径
        wrapper.like(Objects.nonNull(params.get("path")), "a.path", params.get("path"));
        wrapper.eq(true, "a.is_deleted", 0).isNull("api_id");
        wrapper.orderByDesc("create_time");


        page = this.getBaseMapper().listAndAuth(page, AuthUtil.getUserId(), wrapper);
        return page;
    }


    // /**
    // * 将原字符串中匹配正则表达式的值替换成新值
    // * @param oldString 原字符
    // * @param regex
    // * @param groupNum
    // * @param placeholderStart
    // * @param placeholderEnd
    // * @param func
    // * @return
    // */
    // private String replaceViablePlaceholder(String oldString,String regex,int
    // groupNum,String placeholderStart,String placeholderEnd){

    // if(oldString == null){
    // return oldString;
    // }

    // Pattern p = Pattern.compile(regex);
    // Matcher m = p.matcher(oldString);
    // StringBuffer sb = new StringBuffer();
    // while (m.find()) {
    // if(func!=null){
    // m.appendReplacement(sb,func.apply(m));
    // }else{
    // String key = m.group(groupNum);

    // m.appendReplacement(sb, placeholderStart+key+placeholderEnd);
    // }

    // }
    // m.appendTail(sb);
    // return sb.toString();
    // }


    /**
     * 分页获取API列表
     *
     * @param dto   AmApiDTO
     * @param query Query
     * @return IPage
     * @author szs
     * @date 2023-07-26
     */
    @Override
    public IPage<AmApiDTO> getApiList(AmApiDTO dto, Query query) {
        QueryWrapper<AmApi> qw = new QueryWrapper<>();
        qw.eq(dto.getId() != null, "a.id", dto.getId());
        qw.eq(dto.getIsPublic() != null, "a.is_public", dto.getIsPublic());
        qw.eq(dto.getServiceId() != null, "a.service_id", dto.getServiceId());
        qw.like(StringUtils.isNotBlank(dto.getName()), "a.name", dto.getName());
        qw.eq(StringUtils.isNotBlank(dto.getType()), "a.type", dto.getType());
        qw.like(StringUtils.isNotBlank(dto.getCreateUserName()), "b.name", dto.getCreateUserName());
        qw.eq(dto.getStatus() != null, "a.status", dto.getStatus());
        qw.eq(dto.getApproveStatus() != null, "a.approve_status", dto.getApproveStatus());
        qw.eq(dto.getDataResourceId() != null, "a.data_resource_id", dto.getDataResourceId());
        qw.eq(dto.getTableName() != null, "a.table_name", dto.getTableName());
        qw.eq(dto.getIsMyCreate() != null && dto.getIsMyCreate() == 1, "a.create_user", AuthUtil.getUserId());
        qw.isNull("a.api_id");
        qw.eq("a.is_deleted", 0);
        qw.orderByDesc("a.create_time");

        return baseMapper.getApiList(new Page<>(query.getCurrent(), query.getSize()), qw);
    }

    /**
     * 查询api路径是否存在
     *
     * @param dataResourceId 数据资源ID
     * @param tableName      数据资源表名
     * @return 是否存在
     * @author zuowentao
     */
    @Override
    public Boolean verifyApiRoute(Long dataResourceId, String tableName) {
        // 根据路径查询此路径的Api是否已经存在
        QueryWrapper<AmApi> qw = new QueryWrapper<>();
        qw.lambda().eq(AmApi::getDataResourceId, dataResourceId).eq(AmApi::getTableName, tableName).eq(AmApi::getIsDeleted, 0);
        return this.count(qw) > 0;
    }


    /**
     * 根据API查询表结构
     *
     * @param apiId APIID
     * @return ApiDTO
     * @author szs
     * @date 2023-08-01
     */
    @Override
    public ApiDTO getTableStructByApi(String apiId) {
        ApiDTO apiDTO = new ApiDTO();

        // 获取API
        AmApi api = this.getById(apiId);
        if (api == null) {
            throw new ServiceException("Api不存在：" + apiId);
        }

        BeanUtils.copyProperties(api, apiDTO);

        // 获取子API
        QueryWrapper<AmApi> qw = new QueryWrapper<>();
        qw.eq("api_id", apiId);
        qw.eq("is_deleted", 0);
        List<AmApi> childList = this.list(qw);

        List<ApiDTO> children = new ArrayList<>();
        for (AmApi child : childList) {
            // 获取子API
            ApiDTO dto = new ApiDTO();
            BeanUtils.copyProperties(child, dto);

            // 获取请求
            QueryWrapper<AmApiRequest> qw2 = new QueryWrapper<>();
            qw2.eq("api_id", child.getId());
            qw2.eq("is_deleted", 0);
            qw2.orderByAsc("sort_number");
            dto.setRequests(requestService.list(qw2));

            // 获取返回
            QueryWrapper<AmApiResponse> qw3 = new QueryWrapper<>();
            qw3.eq("api_id", child.getId());
            qw3.eq("is_deleted", 0);
            qw3.orderByAsc("sort_number");
            dto.setResponses(responseService.list(qw3));

            children.add(dto);
        }

        apiDTO.setChildren(children);

        return apiDTO;
    }


    /**
     * 查询api路径是否是本人创建
     *
     * @param dataResourceId 数据资源ID
     * @param tableName      数据资源表名
     * @return 是否存在
     * @author zuowentao
     */
    @Override
    public Boolean verifyMyApiRoute(Long dataResourceId, String tableName) {
        // 根据路径查询此路径的Api是否已经存在
        QueryWrapper<AmApi> qw = new QueryWrapper<>();
        qw.lambda().eq(AmApi::getDataResourceId, dataResourceId)
                .eq(AmApi::getTableName, tableName)
                .eq(AuthUtil.getUserId() != null, AmApi::getCreateUser, AuthUtil.getUserId())
                .eq(AmApi::getIsDeleted, 0);
        return this.count(qw) > 0;
    }


    /**
     * 保存API
     *
     * @param dto ApiDTO
     * @author szs
     * @date 2023-09-14
     */
    @Override
    public void submit(ApiDTO dto) {
        // 1. 检查路径是否存在
        checkPathIsExist(dto.getId(), dto.getPath());

        // 2. 保存API
        AmApi api = new AmApi();
        BeanUtils.copyProperties(dto, api);
        if (StringUtils.isEmpty(api.getId())) {
            api.setId(null);
            api.setStatus(0);

            // 管理员新增直接审批通过
            api.setApproveStatus(AuthUtil.isAdmin() ? 2 : 0);
        }

        boolean bo = this.saveOrUpdate(api);
        if (!bo) {
            throw new ServiceException("保存Api失败");
        }

        // 3. 保存request
        batchSaveRequest(dto.getRequests(), api.getId());

        // 4. 保存response
        batchSaveResponse(dto.getResponses(), api.getId());

        // 5. 保存api子集
        batchSaveApi(dto.getChildren(), api);

    }


    /**
     * 批量保存请求
     *
     * @param requestList 请求字段集合
     * @param apiId       APIID
     * @author szs
     * @date 2023-09-14
     */
    void batchSaveRequest(List<AmApiRequest> requestList, String apiId) {
        if (requestList == null) {
            requestList = new ArrayList<>();
        }

        // 查询原数据
        QueryWrapper<AmApiRequest> qw = new QueryWrapper<>();
        qw.eq("api_id", apiId);
        qw.eq("is_deleted", 0);
        List<AmApiRequest> list = requestService.list(qw);

        // 待删除ids
        Set<String> toDelIds = list.stream().map(AmApiRequest::getId).collect(Collectors.toSet());

        // 遍历处理数据
        int sortNumber = 0;
        for (AmApiRequest data : requestList) {
            if (StringUtils.isNotBlank(data.getId())) {
                toDelIds.remove(data.getId());
            }

            data.setApiId(apiId);
            data.setIsDeleted(0);
            data.setSortNumber(sortNumber);
            sortNumber++;
        }

        // 批量保存
        if (requestList.size() > 0) {
            requestService.saveOrUpdateBatch(requestList);
        }

        // 批量删除
        if (toDelIds.size() > 0) {
            requestService.removeByIds(toDelIds);
        }

    }


    /**
     * 批量保存返回
     *
     * @param responseList 返回字段集合
     * @param apiId        APIID
     * @author szs
     * @date 2023-09-14
     */
    void batchSaveResponse(List<AmApiResponse> responseList, String apiId) {
        if (responseList == null) {
            responseList = new ArrayList<>();
        }

        // 查询原数据
        QueryWrapper<AmApiResponse> qw = new QueryWrapper<>();
        qw.eq("api_id", apiId);
        qw.eq("is_deleted", 0);
        List<AmApiResponse> list = responseService.list(qw);

        // 待删除ids
        Set<String> toDelIds = list.stream().map(AmApiResponse::getId).collect(Collectors.toSet());

        // 遍历处理数据
        int sortNumber = 0;
        for (AmApiResponse data : responseList) {
            if (StringUtils.isNotBlank(data.getId())) {
                toDelIds.remove(data.getId());
            }

            data.setApiId(apiId);
            data.setIsDeleted(0);
            data.setSortNumber(sortNumber);
            sortNumber++;
        }

        // 批量保存
        if (responseList.size() > 0) {
            responseService.saveOrUpdateBatch(responseList);
        }

        // 批量删除
        if (toDelIds.size() > 0) {
            responseService.removeByIds(toDelIds);
        }

    }


    /**
     * 批量保存子API
     *
     * @param apiList Api子集
     * @param api     Api
     * @author szs
     * @date 2023-09-14
     */
    void batchSaveApi(List<ApiDTO> apiList, AmApi api) {
        if (apiList == null) {
            apiList = new ArrayList<>();
        }

        // 查询原数据
        QueryWrapper<AmApi> qw = new QueryWrapper<>();
        qw.eq("api_id", api.getId());
        qw.eq("is_deleted", 0);
        List<AmApi> list = this.list(qw);

        // 待删除ids
        Set<String> toDelIds = list.stream().map(AmApi::getId).collect(Collectors.toSet());

        // 遍历处理数据，递归保存API
        for (ApiDTO data : apiList) {
            if (StringUtils.isNotBlank(data.getId())) {
                toDelIds.remove(data.getId());
            }

            data.setApiId(api.getId());
            data.setServiceId(api.getServiceId());
            data.setPaginationBit(api.getPaginationBit());
            data.setSchemaName(api.getSchemaName());
            data.setTableName(api.getTableName());
            data.setSourceType(api.getSourceType());
            data.setDatasourceId(api.getDatasourceId());
            data.setIsAccessAuthorize(api.getIsAccessAuthorize());
            data.setIsPublic(api.getIsPublic());
            data.setIsDeleted(0);
            this.submit(data);
        }

        // 批量删除，删除状态
        if (toDelIds.size() > 0) {
            // 删除API
            this.removeByIds(toDelIds);

            // 删除API 请求
            QueryWrapper<AmApiRequest> qw3 = new QueryWrapper<>();
            qw3.in("api_id", toDelIds);
            qw3.eq("is_deleted", 0);
            requestService.remove(qw3);

            // 删除API 返回
            QueryWrapper<AmApiResponse> qw4 = new QueryWrapper<>();
            qw4.in("api_id", toDelIds);
            qw4.eq("is_deleted", 0);
            responseService.remove(qw4);
        }

    }


    /**
     * 检查路径是否存在
     *
     * @param id   主键ID
     * @param path 路径
     * @author szs
     * @date 2023-09-14
     */
    void checkPathIsExist(String id, String path) {
        QueryWrapper<AmApi> qw = new QueryWrapper<>();
        qw.eq("path", path);
        qw.ne(StringUtils.isNotBlank(id), "id", id);
        qw.eq("is_deleted", 0);

        if (this.count(qw) > 0) {
            throw new ServiceException("Api路径已经存在：" + path);
        }
    }


}
