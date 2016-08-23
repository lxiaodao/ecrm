/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.utils;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @ClassName: ToJson
 * @Description: TODO
 * @author Administrator
 * @date 2013年11月20日 下午2:29:43
 */
public final class ToJson {
	private static final Logger LOG = Logger.getLogger(ToJson.class);
	public static String toJson(Object o){
		String json="";
		try {
			json= new ObjectMapper().writeValueAsString(o);
		} catch (Exception e) {
			LOG.error("convert object to json string fail.",e);
		}
		return json;
	}
}
