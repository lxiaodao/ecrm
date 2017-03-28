/**
 * 
 */
package com.weheros.framework.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.ExecutionContext;

/**
 * @author liuy
 * 
 */
public class HttpClientTest {

	public static void main(String[] arr) throws Exception {

		httpPost();

	}
	public static void doGet() throws Exception{
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("UTF-8");

		// 创建请求方式
		GetMethod method = new GetMethod(
				"https://s.taobao.com/search?q=%E9%A9%B4%E5%BE%97%E6%B0%B4+%2B%E8%A1%A3%E6%9C%8D&imgfile=&js=1&stats_click=search_radio_all%3A1&initiative_id=staobaoz_20161122&ie=utf8");

		method.setRequestHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		method.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
		method.setRequestHeader("Cache-Control", "no-cache");
		method.setRequestHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");

		client.executeMethod(method);

		System.out.println(retrieContent(method.getResponseBodyAsStream()));
	}

	public static String retrieContent(InputStream inputStream) throws Exception {
		BufferedReader in = null;

		String content = null;
		try {

			in = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			content = sb.toString();
		} finally {
			if (in != null) {
				try {
					in.close();// 最后要关闭BufferedReader
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return content;
		}
	}
	
	public static void executePost() throws HttpException, IOException{
		
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("UTF-8");
        
        PostMethod method=new PostMethod("http://localhost:8080/ecrm/login");
        method.addParameter("username", "rod");
        method.addParameter("password", "koala");
        
    	method.setRequestHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		method.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
		method.setRequestHeader("Cache-Control", "no-cache");
		method.setRequestHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
		
		
		client.executeMethod(method);
		Header[] hearders=method.getResponseHeaders();
		
		for(Header hea:hearders){
			System.out.println(hea.getName()+"---"+hea.getValue());
		}
		
	
	}
		
		public static void httpPost() throws IllegalStateException, Exception{
			HttpPost post = new HttpPost("http://localhost:8080/ecrm/login");
			 List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	            nameValuePairs.add(new BasicNameValuePair("username","rod"));
	            nameValuePairs.add(new BasicNameValuePair("password","koala"));
	            
	            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			DefaultHttpClient httpClient = new DefaultHttpClient();
			  httpClient.setRedirectStrategy(new LaxRedirectStrategy());
			 
			HttpResponse response = httpClient.execute(post);
			
			//if (response1.getStatusLine().getStatusCode() == 302) {
			//	  String redirectURL = response1.getFirstHeader("Location").getValue();
		    //}
			org.apache.http.Header[] hearders=response.getAllHeaders();
			System.out.println("Status code is:"+response.getStatusLine());
			for(org.apache.http.Header hea:hearders){
				System.out.println(hea.getName()+"---"+hea.getValue());
			}
			System.out.println(retrieContent(response.getEntity().getContent()));
			
    }

}
