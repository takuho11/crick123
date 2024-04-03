package cn.topcheer.pms2.common.utils;

import net.sf.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;


/**
 * 这个Https协议工具类，采用HttpsURLConnection实现。
 * 提供get和post两种请求静态方法
 * 
 * @author marker
 * @date 2014年8月30日
 * @version 1.0
 */
public class PmlHttpUtil {
	
	private static TrustManager myX509TrustManager = new X509TrustManager() {

		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException { 

		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException { 

		}

		public X509Certificate[] getAcceptedIssuers() { 
			return null;
		}

	};











	public static String sendHttpsPOST(String url, String data) {
		String result = null;

		try {
			// 设置SSLContext
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { myX509TrustManager },
					null);

			// 打开连接
			// 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
			URL requestUrl = new URL(url);
			HttpsURLConnection httpsConn = (HttpsURLConnection) requestUrl
					.openConnection();

			// 设置套接工厂
			httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

			// 加入数据
			httpsConn.setRequestMethod("POST");
			httpsConn.setDoOutput(true);
			OutputStream out = httpsConn.getOutputStream() ;
			 
			if (data != null)
				out.write(data.getBytes("UTF-8")); 
			out.flush();
			out.close();

			// 获取输入流
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpsConn.getInputStream()));
			int code = httpsConn.getResponseCode();
			if (HttpsURLConnection.HTTP_OK == code) {
				String temp = in.readLine();
				/* 连接成一个字符串 */
				while (temp != null) {
					if (result != null)
						result += temp;
					else
						result = temp;
					temp = in.readLine();
				}
			}
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	
	public static String sendHttpsGET(String url) {
		String result = null;

		try {
			// 设置SSLContext
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { myX509TrustManager },
					null);

			// 打开连接
			// 要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式
			URL requestUrl = new URL(url);
			HttpsURLConnection httpsConn = (HttpsURLConnection) requestUrl
					.openConnection();

			// 设置套接工厂
			httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

			// 加入数据
			httpsConn.setRequestMethod("GET");
//			httpsConn.setDoOutput(true);
			  

			// 获取输入流
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpsConn.getInputStream()));
			int code = httpsConn.getResponseCode();
			if (HttpsURLConnection.HTTP_OK == code) {
				String temp = in.readLine();
				/* 连接成一个字符串 */
				while (temp != null) {
					if (result != null)
						result += temp;
					else
						result = temp;
					temp = in.readLine();
				}
			}
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static String getBody(HttpServletRequest request, String charsetname) throws IOException {
	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();   
	    BufferedReader bufferedReader = null;  
	    try {  
	        InputStream inputStream = request.getInputStream();  
	        if (inputStream != null) {  
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream,charsetname));  
	            char[] charBuffer = new char[128];  
	            int bytesRead = -1;  
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {  
	                stringBuilder.append(charBuffer, 0, bytesRead);  
	            }  
	        } else {  
	            stringBuilder.append("");  
	        }  
	    } catch (IOException ex) { 
	        throw ex;  
	    } finally {  
	        if (bufferedReader != null) {  
	            try {  
	                bufferedReader.close();  
	            } catch (IOException ex) {  
	                throw ex;  
	            }  
	        }  
	    }  
	    body = stringBuilder.toString();  
	    return body;  
	}
	
	public static JSONObject getBodyJSON(HttpServletRequest request, String charsetname)
	{
		String body;
		try {
			body = getBody(request,charsetname);
			//body=new String(body.getBytes("ISO8859-1"),"UTF-8");
			JSONObject json=JSONObject.fromObject(body);
            // 在这里加上？？？判断，如果内连续容包含3个问号以上 算不正常数据，不予保存
            if(json!=null&&(json.toString().indexOf("???")!=-1||json.toString().indexOf("？？？")!=-1)) {
                return null;
            }
			return json;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public static String doHttpPost(String URL,String jsonStr){
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		HttpURLConnection conn = null;
		try{
			URL url = new URL(URL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			//发送POST请求必须设置为true
			conn.setDoOutput(true);
			conn.setDoInput(true);
			//设置连接超时时间和读取超时时间
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(180000);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			//获取输出流
			out = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
			out.write(jsonStr);
			out.flush();
			out.close();
			//取得输入流，并使用Reader读取
			if (200 == conn.getResponseCode()){
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				String line;
				while ((line = in.readLine()) != null){
					result.append(line);
					System.out.println(line);
				}
			}else{
				System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try{
				if(out != null){
					out.close();
				}
				if(in != null){
					in.close();
				}
			}catch (IOException ioe){
				ioe.printStackTrace();
			}
		}
		return result.toString();
	}

	//
	public static JSONObject doHttpPost(String URL ,String app ,Integer time , String token , Integer offset ,
									Integer limit , ArrayList category , Integer status , Integer time_from , Integer time_to){
		OutputStreamWriter out = null;
		BufferedReader in = null;
		JSONObject request=new JSONObject();
		JSONObject result=new JSONObject();
		StringBuilder resultString = new StringBuilder();
		HttpURLConnection conn = null;
		try{
			URL url = new URL(URL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			//发送POST请求必须设置为true
			conn.setDoOutput(true);
			conn.setDoInput(true);
			//设置连接超时时间和读取超时时间
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(180000);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			//获取输出流
			request.put("app",app);
			request.put("time",time);
			request.put("token",token);
			request.put("offset",offset);
			request.put("limit",limit);
			request.put("category",category);
			request.put("status",status);
			request.put("time_from",time_from);
			request.put("time_to",time_to);
			out = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
			out.write(request.toString());
			out.flush();
			out.close();
			//取得输入流，并使用Reader读取
			if (200 == conn.getResponseCode()){
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				String line;
				System.out.println("开始获取输出流");
				while ((line = in.readLine()) != null){
					System.out.println(line);
					resultString.append(line);
					result = JSONObject.fromObject(resultString);
				}
			}else{
				System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try{
				if(out != null){
					out.close();
				}
				if(in != null){
					in.close();
				}
			}catch (IOException ioe){
				ioe.printStackTrace();
			}
		}
		return result;
	}

	public static String doHttpGet(String URL){
		HttpURLConnection conn = null;
		InputStream is = null;
		BufferedReader br = null;
		StringBuilder result = new StringBuilder();
		try{
			//创建远程url连接对象
			URL url = new URL(URL);
			//通过远程url连接对象打开一个连接，强转成HTTPURLConnection类
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			//设置连接超时时间和读取超时时间
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(180000);
			conn.setRequestProperty("Accept", "application/json");
			//发送请求
			conn.connect();
			//通过conn取得输入流，并使用Reader读取
			if (200 == conn.getResponseCode()){
				is = conn.getInputStream();
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String line;
				while ((line = br.readLine()) != null){
					result.append(line);
					System.out.println(line);
				}
			}else{
				System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try{
				if(br != null){
					br.close();
				}
				if(is != null){
					is.close();
				}
			}catch (IOException ioe){
				ioe.printStackTrace();
			}
			conn.disconnect();
		}
		return result.toString();
	}

	public static String doHttpPostFormData(String url , LinkedMultiValueMap<String,Object> map){
//		LinkedMultiValueMap <String,Object> map = new LinkedMultiValueMap<>();
//		map.add("1",new ArrayList<>());//放list对象
//		map.add("2",new String("111"));//放String对象
		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<LinkedMultiValueMap<String,Object>> request = new HttpEntity<>(map);
		ResponseEntity<String> responseEntity = template.postForEntity(url, request, String.class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		String body = responseEntity.getBody();
		return body;
	}
}

