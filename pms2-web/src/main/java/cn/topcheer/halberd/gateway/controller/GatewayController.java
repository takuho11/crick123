package cn.topcheer.halberd.gateway.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import org.springblade.core.secure.provider.ResponseProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.topcheer.halberd.gateway.api.dto.ForwardInfo;
import cn.topcheer.halberd.gateway.service.impl.GatewayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import sun.security.action.GetPropertyAction;

@RestController
@Slf4j
@Api(value = "网关", tags = "系统-网关")
public class GatewayController {


    @Autowired
    GatewayServiceImpl gatewayService;

    @Value("${api.basePath:sysapi}")
    private String basePath;


    // @RequestMapping("/sysapi/**")
    @RequestMapping("/" + "${sysapi.basePath:sysapi}" + "/**")
    public void dispatch(HttpServletRequest request, HttpServletResponse response) throws HttpException, IOException {

        String urlPath = getUrlPath(request);

        ForwardInfo forwardInfo = gatewayService.getForwardInfo(urlPath);
        ;

        if (forwardInfo.isCanForward()) {
            if (request.getMethod().equals("GET")) {
                doGet(request, response, forwardInfo);
            } else if (request.getMethod().equals("POST")) {
                doPost(request, response, forwardInfo);
            }
        } else {
            log.warn(forwardInfo.getErrorMsg());
            ResponseProvider.write(response);
        }


    }


    private String extractUrl(String url) {
        String basePathIncludeSlash = "/" + basePath + "/";
        int startIndex = url.indexOf(basePathIncludeSlash); // 起始索引位置
        if (startIndex != -1) {
            startIndex += basePathIncludeSlash.length(); // 跳过 "/sysapi/" 部分
            return url.substring(startIndex); // URL 没有后续路径的情况
        }

        return null; // 没有以 "/sysapi/" 开始的 URL
    }


    private String getUrlPath(HttpServletRequest request) {
        // 获取完整的请求 URL
        StringBuffer requestURL = request.getRequestURL();

        // 获取请求参数部分
        String queryString = request.getQueryString();

        // 构建带参数的 URL
        String fullURL = requestURL.toString();
        if (queryString != null) {
            fullURL += "?" + queryString;
        }
        return extractUrl(fullURL);
    }

    private void doGet(HttpServletRequest request, HttpServletResponse response, ForwardInfo forwardInfo) throws HttpException, IOException {


        HttpRequest targetRequest = HttpUtil.createGet(forwardInfo.getUrl(), true);
        Map<String, Object> params = getParams(request);
        if (forwardInfo.getParams() != null) {
            params.putAll(forwardInfo.getParams());
        }

        Map<String, String> httpHeaders = getHttpHeaders(request);
        if (forwardInfo.getHeaders() != null) {
            httpHeaders.putAll(forwardInfo.getHeaders());
        }

        HttpResponse targetResponse = targetRequest.form(params).addHeaders(httpHeaders).execute();
        addHeaders(response, targetResponse.headers());
        response.setStatus(targetResponse.getStatus());
        //response.getWriter().append(targetResponse.body());
        response.getOutputStream().write(targetResponse.bodyBytes());
        response.getOutputStream().flush();


    }

    private void doPost(HttpServletRequest request, HttpServletResponse response, ForwardInfo forwardInfo) throws IOException {


        Map<String, String> httpHeaders = getHttpHeaders(request);
        if (forwardInfo.getHeaders() != null) {
            httpHeaders.putAll(forwardInfo.getHeaders());
        }
        HttpRequest targetRequest = HttpUtil.createPost(forwardInfo.getUrl());
        targetRequest.addHeaders(getHttpHeaders(request));


        String contentType = request.getContentType();
        MediaType reqMediaType;
        if (contentType == null) {
            reqMediaType = MediaType.APPLICATION_FORM_URLENCODED;
        } else {
            reqMediaType = MediaType.parseMediaType(request.getContentType());
        }

        if (reqMediaType.isCompatibleWith(MediaType.APPLICATION_JSON)) {

            BufferedReader reader = request.getReader();
            // 读取请求体内容
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
            targetRequest.body(requestBody.toString());

        } else if (reqMediaType.isCompatibleWith(MediaType.APPLICATION_FORM_URLENCODED)) {
            targetRequest.form(getParams(request));
        } else if (reqMediaType.isCompatibleWith(MediaType.MULTIPART_FORM_DATA)) {
            targetRequest.form(getParams(request));
        }

        HttpResponse targetResponse = targetRequest.execute();

        response.setStatus(targetResponse.getStatus());
        addHeaders(response, targetResponse.headers());
        //response.getWriter().append(targetResponse.body());
        response.getOutputStream().write(targetResponse.bodyBytes());
        response.getOutputStream().flush();
        System.out.println("完成");
    }



    private Map<String, Object> getParams(HttpServletRequest request) throws IOException {

        Map<String, Object> values = new HashMap<>();

        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            values.put(name, request.getParameterValues(name));
        }
        if (request.getContentType() != null && MediaType.parseMediaType(request.getContentType()).isCompatibleWith(MediaType.MULTIPART_FORM_DATA)){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            File file = null;
            MultipartFile multipartFile = multipartRequest.getFile("file");
            if (multipartFile == null) {
                log.error("上传文件为空, multipartFile is null");
                return values;
            }

            String originalFilename = multipartFile.getOriginalFilename();
            if(originalFilename == null){
                log.warn("上传文件名为空, originalFilename is null");
                originalFilename="null-originalFilename";
            }
           // String[] filename = originalFilename.split("\\.");
            file = File.createTempFile(originalFilename,"");
            file=new File(new File(AccessController
                    .doPrivileged(new GetPropertyAction("java.io.tmpdir"))),originalFilename);
            multipartFile.transferTo(file);
            file.deleteOnExit();
            values.put("file", file);
        }
        return values;

    }

    private Map<String, String> getHttpHeaders(HttpServletRequest request) {
        List<String> filters = new ArrayList<String>();
        filters.add("authorization");  //Authorization 会导致spring security 框架用这个header去登录

        Map<String, String> headers = new HashMap<String, String>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (!filters.contains(headerName.toLowerCase(Locale.ROOT))) {
                String header = request.getHeader(headerName);
                headers.put(headerName, header);
            }
        }
        return headers;

    }

    private void addHeaders(HttpServletResponse response, Map<String, List<String>> headers) {
        if (headers == null) return;
        for (String key : headers.keySet()) {

            if (key != null) {  //可能是hutool 的问题，他把 HTTP/1.1 200 OK
                if (key.equals("Transfer-Encoding")
                        || key.equals("ETag")
                        || key.equals("Content-Encoding")
                        || key.equals("Last-Modified")
                        || key.equals("Connection")
                ) {
                    continue;
                }

                // 获取对应值列表
                List<String> values = headers.get(key);

                // 枚举对应每个值列表中的每个值
                for (String value : values) {
                    System.out.println(key + ":" + value);
                    response.addHeader(key, value);
                }
            }

        }
    }


}
