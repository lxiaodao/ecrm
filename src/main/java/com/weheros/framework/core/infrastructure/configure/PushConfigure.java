/**
 * Copyright (c) 2011-2015 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.configure;

/**
 * @ClassName: PushConfigure 
 * @author WB
 * @date 2015-2-4 上午11:07:12
 */
public class PushConfigure extends AppConfigure{

	private String ak;
	private String sk;
	private String keypath;
	private String keypassword;
	private Boolean type;
	
	public String getAk() {
		return ak;
	}
	public void setAk(String ak) {
		this.ak = ak;
	}
	public String getSk() {
		return sk;
	}
	public void setSk(String sk) {
		this.sk = sk;
	}
	public String getKeypath() {
		return keypath;
	}
	public void setKeypath(String keypath) {
		this.keypath = keypath;
	}
	public String getKeypassword() {
		return keypassword;
	}
	public void setKeypassword(String keypassword) {
		this.keypassword = keypassword;
	}
	public Boolean getType() {
		return type;
	}
	public void setType(Boolean type) {
		this.type = type;
	}
	 
	
}

