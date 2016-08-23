/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.configure;


/**
 * @ClassName: FastDFSConfigure 
 * @author Administrator
 * @date 2014年12月2日 下午5:57:08
 */
public class FastDFSConfigure extends AppConfigure {
	//@Value("${fdfs.connect_timeout}")
	private Integer connect_timeout;
	
	private Integer network_timeout;
	
	private String charset;
	
	private Integer tracker_http_port;
	
	private Boolean anti_steal_token;
	
	private String secret_key;
	/**
	 * 格式：115.28.21.131:22122
	 */
	private String tracker_server;
	

	public int getConnect_timeout() {
		return connect_timeout;
	}


	public int getNetwork_timeout() {
		return network_timeout;
	}


	public String getCharset() {
		return charset;
	}


	public int getTracker_http_port() {
		return tracker_http_port;
	}


	public boolean isAnti_steal_token() {
		return anti_steal_token;
	}


	public String getSecret_key() {
		return secret_key;
	}


	public String getTracker_server() {
		return tracker_server;
	}
	
	//----------------------------------------------//
	public String takeTrackerIp(){
		return this.tracker_server.split(":")[0];
	}
	public int takeTrackerPort(){
		return Integer.valueOf(this.tracker_server.split(":")[1]);
	}

}
