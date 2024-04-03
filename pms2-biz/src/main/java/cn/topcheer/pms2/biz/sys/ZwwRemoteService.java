package cn.topcheer.pms2.biz.sys;

import cn.hutool.crypto.digest.MD5;
import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.modules.system.entity.UserInfo;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.api.sys.PmsAcceptanceSave;
import cn.topcheer.pms2.api.sys.PmsProcessSave;
import cn.topcheer.pms2.api.sys.PmsResultSave;
import cn.topcheer.pms2.api.sys.PmsZwwRecord;
import cn.topcheer.pms2.biz.utils.Util;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springblade.core.tool.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service(value = "ZwwRemoteService")
public class ZwwRemoteService extends GenericService<PmsZwwRecord> {


    public JSONObject trainsParams(Object obj,String type){
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject;
    }

    public ReturnToJs saveAcceptance(String type, String json) {
        return this.remoteZWW(type,JSONObject.fromObject(json),Util.NewGuid());
    }

    /**
     * 调用政务网接口
     * @param type
     * @param json
     * @param id 对应受理、过程、结果报存的id
     * @return
     */
    public ReturnToJs remoteZWW(String type, JSONObject json,String id) {
        ReturnToJs<Object> returnToJs = new ReturnToJs<>();
        Map property = getParams(type, json);
        if (property == null) {
            returnToJs.setMsg("没有找到对应参数！");
            returnToJs.setCode(500);
            return returnToJs;
        }
        String params = String.valueOf(property.get("params"));

        try {
            URL url = new URL(String.valueOf(property.get("url")));
            HashMap<String, Object> map = new HashMap<>();
            map.put("accept", "*/*");
            map.put("UserAgent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022)");
            map.put("connection", "Keep-Alive");  //维持长链接
            map.put("Content-Type", "application/json;charset=utf-8");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("sign", String.valueOf(property.get("sign")));
            conn.setRequestProperty("appId", String.valueOf(property.get("appId")));
            conn.setRequestProperty("apiCode", String.valueOf(property.get("apiCode")));
            conn.setRequestProperty("requestNo", "7b364ad113d63c901276c2ec95c482ab");
            conn.setRequestProperty("apiKey", String.valueOf(property.get("apiKey")));
//            System.out.println("--------connection start----------");
            conn.connect();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            writer.write(params);
            writer.close();
//            System.out.println("--------connection writer close----------");
            int responseCode = conn.getResponseCode();
            InputStream inputStream = null;
            System.out.println(responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = conn.getInputStream();
            }else {
                inputStream = conn.getErrorStream();
            }
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[4096];
            for (int i; (i = inputStream.read(b)) != -1; ) {
                out.append(new String(b, 0, i));
            }
            //获取返回数据
            JSONObject jsonObject = JSONObject.fromObject(out.toString());
//            System.out.println(jsonObject);
            JSONObject head = JSONObject.fromObject(jsonObject.get("head"));
            JSONObject result = JSONObject.fromObject(jsonObject.get("result"));
            if (head.get("code").equals("200")){
                PmsZwwRecord record = getRecord(result, id, type);
                return getReturnToJs(record);
            }else {
                returnToJs.setMsg(String.valueOf(head.get("result")));
                returnToJs.setCode(Integer.valueOf(String.valueOf(head.get("code"))));
            }
//            System.out.println("--------connection end----------");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        returnToJs.setErrMsg("发送失败");
        return returnToJs;
    }


    /**
     * 返回调用结果
     * @param record
     * @return
     */
    private ReturnToJs getReturnToJs(PmsZwwRecord record) {
        ReturnToJs<Object> returnToJs = new ReturnToJs<>();
        returnToJs.setCode(Integer.valueOf(record.getState()));
        switch (record.getState()){
            case "401" :
                returnToJs.setMsg("可能未填写授权认证信息（或者授权认证信息填写错误）");
                break;
            case "402" :
                returnToJs.setMsg("可能未获取资源授权（未申请接口或申请未通过审批）");
                break;
            case "404" :
                returnToJs.setMsg("可能接口地址不正确（或者是请求方式填写错误）");
                break;
            case "502" :
                returnToJs.setMsg("可能从上游服务器接收到无效的响应（源接口地址不通）");
                break;
            case "504" :
                returnToJs.setMsg("可能未能及时从上游服务器接收到响应（源接口地址不通）");
                break;
            case "500" :
                returnToJs.setMsg("请联系技术人员进行排查");
                break;
            case "200" :
                returnToJs.setMsg("请求成功");
                break;
            case "403" :
                returnToJs.setMsg("禁止访问");
                break;
        }
        return returnToJs;
    }

    /**
     * 创建调用记录
     *
     * @param result
     * @param id
     * @return
     */
    private PmsZwwRecord getRecord(JSONObject result, String id, String type) {
        PmsZwwRecord record = new PmsZwwRecord();
        record.setId(Util.NewGuid());
        record.setMainid(id);
        record.setState(type);
        record.setSavedate(new Date());
        record.setUpdatelasttime(new Date());
        record.setProjectNo(String.valueOf(result.get("projectNo")));
        record.setState(String.valueOf(result.get("state")));
        this.merge(record);
        return record;
    }

    /**
     * 获取调用参数
     * @param type
     * @param acceptanceSave
     * @param processSave
     * @param resultSave
     * @return
     */
    private Map getParams(String type,PmsAcceptanceSave acceptanceSave, PmsProcessSave processSave, PmsResultSave resultSave) {
        Map<String, String> map = new HashMap<>();
        JSONObject jsonObject;
        JSONObject baseInfo= new JSONObject();
        JSONObject param = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        map.put("appId","ZW1540461311446");
        map.put("requestNo", "7b364ad113d63c901276c2ec95c482ab");
        map.put("apiKey", "ptGuBM6iuiff4Yqb39I2jLfzpFqrGELd");
        switch (type) {
            case "acceptance":
//                map.put("url","https://data.gz.cegn.cn:1443/wg/dldz/5f98c25b-f5b1-4acd-abd8-7b7eb765921c");
//                map.put("url","http://10.248.2.127:80/zwfuhc1");//测试库
                map.put("url","http://61.243.3.47:8081/zwfuhc1");//本地测试
                map.put("apiCode","100W1469");
                jsonObject = acceptanceSave != null ? JSONObject.fromObject(acceptanceSave) : getAcceptanceSave();
                map.put("id", jsonObject.getString("id"));
                jsonArray.add(jsonObject);
                baseInfo.put("baseInfo",jsonArray);
                param.put("param", baseInfo);
                map.put("params", String.valueOf(param));
                break;
            case "process":
//                map.put("url","https://data.gz.cegn.cn:1443/wg/dldz/768f743f-7066-44ef-9e35-0fd1713c3b92");
//                map.put("url","http://10.248.2.127:80/zwfuhc2");//测试库
                map.put("url","http://61.243.3.47:8081/zwfuhc2");//本地测试
                map.put("apiCode","100W1468");
                jsonObject = processSave != null ? JSONObject.fromObject(processSave) : getProcessSave();
                map.put("id", jsonObject.getString("id"));
                jsonArray.add(jsonObject);
                baseInfo.put("postdata",jsonArray);
                param.put("param", baseInfo);
                map.put("params", String.valueOf(param));
                break;
            case "result":
//                map.put("url","https://data.gz.cegn.cn:1443/wg/dldz/e9c07708-16b9-45c0-b83a-5189592cde5");
//                map.put("url","http://10.248.2.127:80/zwfuhc3");//测试库
                map.put("url","http://61.243.3.47:8081/zwfuhc3");//本地测试
                map.put("apiCode","100W1467");
                jsonObject = resultSave != null ? JSONObject.fromObject(resultSave) : getResultSave();
                map.put("id", jsonObject.getString("id"));
                jsonArray.add(jsonObject);
                baseInfo.put("postdata",jsonArray);
                param.put("param", baseInfo);
                map.put("params", String.valueOf(param));
                break;
            default:
                return null;
        }
//        String md532Lower = map.get("appid") + "f22e822efd68f8d3f52e23c5dd6cc778" + System.currentTimeMillis() + map.get("apiCode");
        map.put("sign", getSign(map.get("appId"),"f22e822efd68f8d3f52e23c5dd6cc778",map.get("apiCode")));
        return map;
    }



    /**
     * 签名计算
     * @param appId     应用ID
     * @param appSecret 应用密钥
     * @param apiCode  接口代码
     * @return
     * */
    private String getSign(String appId, String appSecret, String apiCode) {
        //时间戳
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String timestamp = dateFormat.format(new Date());
        System.out.println(timestamp);
        //加密令牌
        String tokenKey = appId + appSecret + timestamp;
        //算出本地签名
        return DigestUtils.md5DigestAsHex((tokenKey + apiCode).getBytes());}

    public Map getMD5(String type) {
        Map<String, String> map = new HashMap<>();
        map.put("appid","ZW1540461311446");
        map.put("requestNo", "7b364ad113d63c901276c2ec95c482ab");
        switch (type) {
            case "acceptance":
                map.put("url","https://data.gz.cegn.cn:1443/wg/dldz/5f98c25b-f5b1-4acd-abd8-7b7eb765921c");
                map.put("apiCode","100W1469");
                break;
            case "process":
                map.put("url","https://data.gz.cegn.cn:1443/wg/dldz/768f743f-7066-44ef-9e35-0fd1713c3b92");
                map.put("apiCode","100W1468");
                break;
            case "result":
                map.put("url","https://data.gz.cegn.cn:1443/wg/dldz/e9c07708-16b9-45c0-b83a-5189592cde5");
                map.put("apiCode","100W1467");
                break;
            default:
                return null;
        }
        map.put("sign", getSign(map.get("appid"),"f22e822efd68f8d3f52e23c5dd6cc778",map.get("apiCode")));
        return map;
    }



    public JSONObject getAcceptanceSave() {
        JSONObject str = JSONObject.fromObject("{\"address\":\"政务中心\",\"applyDate\":\"2018-09-13 13:33:27\"," +
                "\"applyType\":\"1\",\"applyerName\":\"亚非\",\"applyerPageCode\":\"650204197006240750\"," +
                "\"applyerPageType\":\"51\",\"applyerType\":\"1\",\"contactCode\":\"650204197006240750\"," +
                "\"contactMobile\":\"17785848013\",\"contactName\":\"亚非\",\"contactType\":\"51\"," +
                "\"dataFrom\":\"test\",\"dataSource\":\"152\",\"lerep\":\"亚非\"," +
                "\"projectNo\":\"d05529f9-6986-41b9-ae99-470bc9d80dad\",\"projectType\":2," +
                "\"rowGuid\":\"2719625d-8ace-45e0-b527-5e4c291627a3\",\"taskName\":\"测试数据\"," +
                "\"taskItemCode\":\"24456\",\"itemOrganCode\":\"429280359\",\"itemOrganName\":\"云岩区城管局\"," +
                "\"itemRegionCode\":\"520103\",\"itemRegionName\":\"云岩区\",\"projectCode\":\"123456\"," +
                "\"promisetimeLimit\":\"15\",\"promisetimeUnit\":\"G\",\"provinceRegion\":\"520500\"," +
                "\"timeLimit\":\"15\",\"timeUnit\":\"G\",\"acceptDate\":\"2018-09-13 13:33:27\"," +
                "\"acceptName\":\"张三\",\"acceptPersonID\":\"dfgacd\",\"id\":\"123456123456\"}");
//        JSONObject json = JSONObject.fromObject(str);
//        PmsAcceptanceSave acceptanceSave = new PmsAcceptanceSave();
//        BeanUtil.copyProperties(json, acceptanceSave);
//        acceptanceSave.setAddress(json.getString("address"));
//        acceptanceSave.setApplyDate(new Date());
//        acceptanceSave.setApplyType(json.getString("applyType"));
//        acceptanceSave.setApplyerName(json.getString("applyerName"));
//        acceptanceSave.setApplyerPageCode(json.getString("applyerPageCode"));
//        acceptanceSave.setApplyerPageType(json.getString("applyerPageType"));
//        acceptanceSave.setApplyType(json.getString("applyerType"));
//        acceptanceSave.setContactCode(json.getString("contactCode"));
//        acceptanceSave.setContactMobile(json.getString("contactMobile"));
//        acceptanceSave.setContactName(json.getString("contactName"));
//        acceptanceSave.setContactType(json.getString("contactType"));
//        acceptanceSave.setDataFrom(json.getString("dataFrom"));
//        acceptanceSave.setDataSource(json.getString("dataSource"));
//        acceptanceSave.setLerep(json.getString("lerep"));
//        acceptanceSave.setProjectNo(json.getString("projectNo"));
//        acceptanceSave.setProjectType(json.getString("projectType"));
//        acceptanceSave.setRowGuid(json.getString("rowGuid"));
//        acceptanceSave.setTaskName(json.getString("taskName"));
//        acceptanceSave.setTaskItemCode(json.getString("taskItemCode"));
//        acceptanceSave.setItemOrganCode(json.getString("itemOrganCode"));
//        acceptanceSave.setItemOrganName(json.getString("itemOrganName"));
//        acceptanceSave.setItemRegionCode(json.getString("itemRegionCode"));
//        acceptanceSave.setItemRegionName(json.getString("itemRegionName"));
////        acceptanceSave.setproject
//        acceptanceSave.setPromisetimeLimit(json.getInt("promisetimeLimit"));
//        acceptanceSave.setPromisetimeUnit(json.getString("promisetimeUnit"));
//        acceptanceSave.setTimeLimit(json.getInt("timeLimit"));
//        acceptanceSave.setTimeUnit(json.getString("timeUnit"));
//        try {
//            acceptanceSave.setAcceptDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(json.getString("acceptDate")));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        acceptanceSave.setAcceptName(json.getString("acceptName"));
//        acceptanceSave.setAcceptName(json.getString("acceptPersonID"));
//
//        acceptanceSave.setId(Util.NewGuid());
//        acceptanceSave.setRowGuid(acceptanceSave.getId());
//        acceptanceSave.setSeq(1);
//        acceptanceSave.setSavedate(new Date());
//        acceptanceSave.setUpdatelasttime(new Date());
        return str;
    }


    private JSONObject getProcessSave() {
        JSONObject str = JSONObject.fromObject("{\"eventEndTime\":\"2018-09-12 09:11:57\",\"eventName\":\"1\"," +
                "\"eventStartTime\":\"2018-09-11 09:11:57\",\"handleExplain\":\"办理意见\"," +
                "\"handleUserCode\":\"drx\",\"handleUserName\":\"丁仁鑫\",\"businessStage\":\"2\"," +
                "\"businessState\":\"03\",\"eventLimit\":\"10\",\"eventUnit\":\"2\",\"processName\":\"办理环节名称\"," +
                "\"projectNo\":\"d05529f9-6986-41b9-ae99-470bc9d80dae\",\"remark\":\"备注\",\"rowGuid\":\"drx123\"," +
                "\"orgCode\":\"19951111\",\"orgName\":\"贵阳市质量技术监督局\",\"regionCode\":\"520100\"," +
                "\"regionName\":\"贵阳市\",\"id\":\"123456123456\"}");

        return str;
    }


    private JSONObject getResultSave() {
        JSONObject jsonObject = JSONObject.fromObject("{\"handleUserCode\":\"a1b2c3\",\"handleUserName\":\"张三\"," +
                "\"orgCode\":\"123456789\",\"orgName\":\"公安部\",\"projectNo\":\"d05529f9-6986-41b9-ae99-470bc9d80d11\"," +
                "\"resultDate\":\"2018-09-15 09:11:57\",\"resultExplain\":\"没通过\",\"resultType\":\"1\"," +
                "\"rowGuid\":\"180910346457568679 \",\"regionCode\":\"520000\",\"regionName\":\"贵州省\",\"id\":\"123456123456\"}");
        return jsonObject;
    }

    /**
     * httpcilent
     * @param type
     */
    public ReturnToJs zwwRemote(String type, String json, String id){
        if (Util.isEoN(type)) {
//            type = "acceptance";//受理
//            json = String.valueOf(getAcceptanceSave());
//            id = "2719625d-8ace-45e0-b527-5e4c291627a3";
//            type = "process";//过程
//            json = String.valueOf(getProcessSave());
//            id = "drx123";
            type = "result";//结果
//            json = String.valueOf(getResultSave());
            id = "180910346457568679";
        }
        ReturnToJs<Object> returnToJs = new ReturnToJs<>();
        String body = "";
        try {
            Map property = getParams(type,JSONObject.fromObject(json));
            if (property == null) {
                returnToJs.setMsg("没有找到对应参数！");
                returnToJs.setCode(500);
                return returnToJs;
            }
            SSLContext sslContext = SSLContexts.custom()
                    .loadTrustMaterial(null, (x509Certificates, s) -> true).build();
            //创建httpClient
            CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext).
                    setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
            //获取post请求
            HttpPost post = new HttpPost(String.valueOf(property.get("url")));
            //设置请求头
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            post.setHeader("appId", String.valueOf(property.get("appId")));
            post.setHeader("sign", String.valueOf(property.get("sign")));
            post.setHeader("apiCode", String.valueOf(property.get("apiCode")));
            post.setHeader("apiKey", String.valueOf(property.get("apiKey")));
            post.setHeader("requestNo", String.valueOf(property.get("requestNo")));
            //传参
            StringEntity entity = new StringEntity(String.valueOf(property.get("params")));
            entity.setContentType("application/json");
            post.setEntity(entity);
            //执行请求并获得响应
            CloseableHttpResponse response = client.execute(post);
            //获得响应报文中的报文主体
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("响应码为：" + statusCode);
            HttpEntity entity1 = response.getEntity();
            body = EntityUtils.toString(entity1);
            //获取返回数据
            JSONObject jsonObject = JSONObject.fromObject(body);
            System.out.println(jsonObject);
            JSONObject head = JSONObject.fromObject(jsonObject.get("head"));
            JSONObject result = JSONObject.fromObject(json);
            if (head.get("code").equals("200")){
                //响应成功，保存调用数据
                PmsZwwRecord record = getRecord(result, id, type);
                return getReturnToJs(record);
            }else {
                //响应失败，返回报错信息
                returnToJs.setMsg(String.valueOf(result.get("error")));
                returnToJs.setCode(Integer.valueOf(String.valueOf(result.get("state"))));
            }
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            //创建传参对象失败
            e.printStackTrace();
        } catch (IOException e) {
            //获取响应失败
            e.printStackTrace();
        }
        System.out.println("响应数据为：" + body);
        return returnToJs;
    }

    /**
     * 获取调用参数
     * @param type
     * @return
     */
    private Map getParams(String type, JSONObject json) {
        Map<String, String> map = new HashMap<>();

        JSONObject baseInfo= new JSONObject();
        JSONObject param = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        String paramName = "";

        map.put("appId","ZW1540461311446");
        map.put("requestNo", "7b364ad113d63c901276c2ec95c482ab");
        map.put("apiKey", "ptGuBM6iuiff4Yqb39I2jLfzpFqrGELd");
        switch (type) {
            case "acceptance":
                paramName = "baseInfo";
                if (Util.isEoN(json)) {
                    json = getAcceptanceSave();
                }
                map.put("url","http://61.243.3.47:8081/zwfuhc1");//本地测试
                map.put("apiCode","100W1469");
                break;
            case "process":
                paramName = "postdata";
                if (Util.isEoN(json)) {
                    json = getProcessSave();
                }
                map.put("url","http://61.243.3.47:8081/zwfuhc2");//本地测试
                map.put("apiCode","100W1468");
                break;
            case "result":
                paramName = "postdata";
                if (Util.isEoN(json)) {
                    json = getResultSave();
                }
                map.put("url","http://61.243.3.47:8081/zwfuhc3");//本地测试
                map.put("apiCode","100W1467");
                break;
            default:
                return null;
        }
        jsonArray.add(json);
        baseInfo.put(paramName,jsonArray);
        param.put("param", baseInfo);
        map.put("params", String.valueOf(param));
        map.put("sign", getSign(map.get("appId"),"f22e822efd68f8d3f52e23c5dd6cc778",map.get("apiCode")));
        return map;
    }
}
