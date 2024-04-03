package cn.topcheer.pms2.biz.pdfDownload.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.halberd.biz.common.db.DBHelper;
import cn.topcheer.pms2.api.pdfDownload.PmsDownloadtype;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import cn.topcheer.pms2.biz.utils.Util;
import cn.topcheer.pms2.dao.jpa.pdf.PmsDownloadtypeDao;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PmsDownloadtype 服务类
 */
@Slf4j
@Service(value="PmsDownloadtypeService")
public class PmsDownloadtypeService extends GenericService<PmsDownloadtype> {
    @Autowired
    DBHelper dbHelper;
    public PmsDownloadtypeDao getPmsDownloadtypeDao() {
        return (PmsDownloadtypeDao) this.getGenericDao();
    }

    @Autowired
    public void setPmsDownloadtypeDao(PmsDownloadtypeDao pmsDownloadtypeDao) {

        this.setGenericDao(pmsDownloadtypeDao);
    }
    public  void  doPost2(String url, JSONObject jsonParam) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
        HttpPost httpPost = new HttpPost(url);

        // 对JSONObject格式的数据进行转型
        String string = jsonParam.toString();
        StringEntity entity = new StringEntity(string, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        // 设置Post参数
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

        CloseableHttpResponse response = null;
        String content = null;
        // 开始链接
        try {
            response = httpClient.execute(httpPost);
            if (Objects.isNull(response)){
                //throw new ServiceException("调用接口返回数据出错");
                log.error("调用接口返回数据出错:response is null");
                httpClient.close();
                return;
            }

            System.out.println(response.getStatusLine());

            // 判断调用状态
            if(response.getStatusLine().getStatusCode() == 200){
                // 获取返回结果
                HttpEntity httpEntity = response.getEntity();
                // 结果转型为String
                content = EntityUtils.toString(httpEntity, "UTF-8");
                if (Objects.isNull(content)){
                }else{
                    
                    if("true".equals(content)){

                        int i=0;
                        try{
                            while ( i<6){
                                Thread.sleep(1000);
                                i++;
                            }
                        } catch (InterruptedException e) { // Compliant; the interrupted state is restored
                            log.warn("Interrupted!", e);
                            /* Clean up whatever needs to be handled before interrupting  */
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }catch (Exception e) {
            log.error("调用接口返回数据出错",e);
            
        }finally {
            if (response != null){
                response.close();
            }
        }
        // 链接关闭======================================================
        httpClient.close();
        /*return "";*/
    }

    //修改单独下载配置信息
    public ReturnToJs pmsDownloadtypeSave(JSONObject json, ReturnToJs returnToJs){
        String id ="";
        String type = json.get("type")+"".trim();
        String planprojectid = json.get("planprojectid")+"".trim();
        if(Util.isEoN(json.get("id"))){
            String sql = "select t.* from pms_downloadtype t ";
            List<Map> listMap = dbHelper.getRows(sql,new Object[]{});
            if (listMap.size() > 0) {
                for (int i = 0; i < listMap.size(); i++) {
                    if(type.equals(listMap.get(i).get("type"))&&planprojectid.equals(listMap.get(i).get("planprojectid"))){
                        returnToJs.setSuccess(false);
                        returnToJs.setErrMsg("已经配置了该项目，请重新命名");
                        return  returnToJs;
                    }
                }
            }
            PmsDownloadtype pdtype = new PmsDownloadtype();
            id=Util.NewGuid();
            Util.ApplyObject(pdtype,json);
            pdtype.setId(id);
            Date aa=new Date();
            pdtype.setUpdatetime(aa);
            this.merge(pdtype);
            returnToJs.setSuccess(true);
        }else{
            id=json.get("id")+"";
            PmsDownloadtype pdtype = this.findById(id);
            Util.ApplyObject(pdtype,json);
            Date aa=new Date();
            pdtype.setUpdatetime(aa);
            this.merge(pdtype);
            returnToJs.setSuccess(true);
        }
        return  returnToJs;
    }

    //查询单独下载配置信息

    public JSONArray pmsDownloadtypeFind(JSONObject json) {
        String type="";
        if(!Util.isEoN(json.get("searchContent"))){
            type=json.get("searchContent")+"".trim();
        }
        String sql = "select t.* from pms_downloadtype t where t.type like '"+"%"+type+"%'";
        List<Map> listMap = dbHelper.getRows(sql,new Object[]{});
        if (listMap != null && listMap.size() > 0)
            return JSONArray.fromObject(listMap);
        else
            return null;
    }

    //初始单独下载配置信息

    public JSONArray pmsdownInit() {
        String sql = "select t.* from pms_downloadtype t ";
        List<Map> listMap = dbHelper.getRows(sql,new Object[]{});
        if (listMap != null && listMap.size() > 0)
            return JSONArray.fromObject(listMap);
        else
            return null;
    }

    //删除单独下载配置信息

    public ReturnToJs pmsdownDelete(JSONObject json,ReturnToJs returnToJs) {
        String id=json.get("id")+"".trim();
        try {
            this.deleteById(id);
            returnToJs.setSuccess(true);
        } catch (Exception exception) {
            exception.printStackTrace();
            returnToJs.setSuccess(false);
        }
        return  returnToJs;

    }

    //pdf查看配置方法

    public JSONArray pmsDownPdfInit(String type) {
        String sql = "select t.* from pms_downconfigwater t where type=?";
        List<Map> listMap = dbHelper.getRows(sql,new Object[]{type});
        if (listMap != null && listMap.size() > 0)
            return JSONArray.fromObject(listMap);
        else
            return null;
    }

    //pdf查看配置方法

    public ReturnToJs deletePdfPdfPz(String id,ReturnToJs returnToJs) {
        String sql = "delete pms_downconfigwater t where id=?";
        try{
            dbHelper.runSql(sql,new Object[]{id}) ;
            returnToJs.setSuccess(true);
        }catch (SQLException e){
            returnToJs.setSuccess(false);
        }
        return  returnToJs;
    }

    //单独下载配置参数保存修改方法
    public ReturnToJs saveMyPdfPz(JSONObject json,ReturnToJs returnToJs,int number){
        if(Util.isEoN(json.get("id"))){
            String num="xm"+json.get("type")+String.valueOf(number);
            try{
                String sql="insert into pms_downconfigwater(id,name,watermarkname,fillopacity,strokeopacity,fontsize,sybj,textx,texty,rotation,seq,type)" +
                        "values(?,?,?,?,?,?,?,?,?,?,?,?)";
                dbHelper.runSql(sql,new Object[]{num,json.get("name"),json.get("watermarkname"),json.get("fillopacity"),json.get("strokeopacity")
                        ,json.get("fontsize"),json.get("sybj"),json.get("textx"),json.get("texty"),json.get("rotation"),json.get("seq"),json.get("type")});
                returnToJs.setSuccess(true);
            }catch (SQLException e){
                returnToJs.setSuccess(false);
            }
        }else{
            try{
                String sql="update pms_downconfigwater set name=?,watermarkname=?,fillopacity=?,strokeopacity=?,fontsize=?," +
                        "sybj=?,textx=?,texty=?,rotation=?,seq=? where id=? ";
                dbHelper.runSql(sql,new Object[]{json.get("name"),json.get("watermarkname"),json.get("fillopacity"),json.get("strokeopacity")
                        ,json.get("fontsize"),json.get("sybj"),json.get("textx"),json.get("texty"),json.get("rotation"),json.get("seq"),json.get("id")});
                returnToJs.setSuccess(true);
            }catch (SQLException e){
                returnToJs.setSuccess(false);
            }
        }
        return  returnToJs;
    }
}
