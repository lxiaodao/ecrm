/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.sms;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


/**
 * @ClassName: SendMessage
 * @Description: TODO
 * @author wb
 * @date 2013-12-9 下午3:43:39
 */
public class SendMessage {
	
	
	//短信接口地址
	private static final String SMS_URL = "http://106.ihuyi.com/webservice/sms.php?method=Submit";
	//短信接口账户
	private static final String SMS_USER = "cf_ate";
	//短信接口密码
	private static final String SMS_PWD = "4zycW4";

	
	
	/**
	 * 
	  * <描述>
	  * @author wb
	  * @param phone
	  * @param content
	  * @return
	  * @throws HttpException
	  * @throws IOException 
	  * String
	  *
	 */
	public static String send(String phone, String content) 
			throws HttpException, IOException {
		//创建HTTP请求实例
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("UTF-8");
		
		//创建请求方式
		PostMethod method = new PostMethod(SMS_URL);
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		
		//设置请求参数
		NameValuePair[] data = {
			    new NameValuePair("account", SMS_USER), 
			    new NameValuePair("password", SMS_PWD), 			    
			    new NameValuePair("mobile", phone), 
			    new NameValuePair("content", content)
		};
		method.setRequestBody(data);
		
		//执行请求，并接口返回信息
		client.executeMethod(method);
		return method.getResponseBodyAsString();
	}
	
	/**
	 * 
	  * <描述>
	  * @author wb
	  * @param phone
	  * @param randCode
	  * @return
	  * @throws HttpException
	  * @throws IOException 
	  * String
	  *
	 */
	public static String sendRandCode(String phone, String randCode) 
			throws HttpException, IOException {
		String content = "您的验证码为" + randCode + ",有效时间为10分钟，工作人员不会向您索取，请勿泄露。";
		return send(phone, content);
	}
	/**
	 * <描述>
	 * @author Administrator
	 * @param args 
	 * void
	 * 
	 */

	public static void main(String[] args) {
		String xmlStr;
		try {
			xmlStr = send("13658337004", "你好!");
					//SendMessage.sendRandCode("13658337004", RandUtil.getRandCode());
			Document doc = DocumentHelper.parseText(xmlStr);
			Element root = doc.getRootElement();
	        String code = root.elementText("code");
	        System.out.println(code);
	        if (!"2".equals(code)) {
	        	System.out.println(root.elementText("msg"));
	        }
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
