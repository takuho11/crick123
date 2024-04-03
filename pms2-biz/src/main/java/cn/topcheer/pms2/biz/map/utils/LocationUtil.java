package cn.topcheer.pms2.biz.map.utils;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 工具类：地址经纬度转化
 * @author zk
 * @since 2024-03-11
 */
public class LocationUtil {

    /**
     * zk个人申请的高德开发key
     */
    private static final String KEY = "1e9ae68e3d026c8157accb8d65bc9612";



    /**
     * 应用方法：根据具体地址获取对应的经纬度
     * @param address 具体地址
     * @return 字符串数组，0：经度；1：纬度
     */
    public static String[] getLonAndLat(String address){
        String[] location = new String[]{};
        String url = "https://restapi.amap.com/v3/geocode/geo?key="+KEY+"&address="+address;
        //高德接口返回的是JSON格式的字符串
        JSONObject urlJson = JSONObject.fromObject(getResponse(url));
        String successFlagValue = "1";
        String statusKey = "status";
        if(successFlagValue.equals(urlJson.get(statusKey).toString())){
            //说明获取到地址信息
            String geocodesKey = "geocodes";
            JSONObject json = JSONObject.fromObject(urlJson.get(geocodesKey).toString()
                    .substring(1,urlJson.get(geocodesKey).toString().length()-1));
            String locationStr = json.get("location")+"";
            String splitValue = ",";
            if(locationStr.contains(splitValue)){
                return locationStr.split(splitValue);
            }
        }
        return location;
    }



    /**
     * 辅助方法：发送请求
     * @param serverUrl 请求地址
     * @return 字符串
     */
    private static String getResponse(String serverUrl){
        //用java发起http请求，并返回json格式的结果
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(serverUrl);
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while ((line = in.readLine())!=null){
                result.append(line);
            }
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }




}
