package cn.topcheer.pms2.biz.message;

import cn.topcheer.halberd.app.dao.jpa.GenericService;
import cn.topcheer.pms2.api.message.PmsMessagerecord;
import cn.topcheer.pms2.api.pml.vo.ReturnToJs;
import net.sf.json.JSONObject;
import org.apache.http.client.HttpClient;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service(value="MessageService")
public class MessageService extends GenericService<PmsMessagerecord> {

    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

    public ReturnToJs<Object> sendMessage(String mobile,String content) {
        ReturnToJs<Object> returnToJs = new ReturnToJs<>();
        Map<String, String> map = new HashMap<>();
        try {
            String paramStr = transParamStr(mobile, content);
            URL url = new URL(Url+paramStr);
            map.put("accept", "*/*");
            map.put("UserAgent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022)");
            map.put("connection", "Keep-Alive");  //维持长链接
            map.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type","text/html; charset=UTF-8");
//            conn.setRequestProperty("Authorization",authorization);
            conn.connect();
            StringBuilder result =new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                String line ;
                while( (line =br.readLine()) != null ){
                    result.append(line+"\r\n");
                }
                br.close();
            }catch (Exception e){
                returnToJs.setErrMsg("发送失败");
                return returnToJs;
            }
            String resStr = result.toString();
            String[] split = resStr.split("\r\n");
            transResultMess(split);
            returnToJs.setData(result.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        returnToJs.setMsg("发送成功");
        return returnToJs;
    }

    private Map transResultMess(String[] split) {
        PmsMessagerecord record = new PmsMessagerecord();
        //todo
        return null;
    }

    private String transParamStr(String mobile, String content) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("&account=")
                .append("cf_gzkj")
                .append("&password=")
                .append("7ff0011de57c90b04c1bfe22ae7c8a10")
                .append("&mobile=" + mobile)
                .append("&content=" + content);
        return stringBuilder.toString();
    }
}
