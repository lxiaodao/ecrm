/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.front;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.weheros.test.AnnotationJUnitTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * @ClassName: SimpleControllerTest
 * @Description: 
 *   Headers = {Accept=[application/json;charset=UTF-8]}
 *   Content-Type=[application/json;charset=UTF-8]
 * Show how to write a controller test.
 * @author Administrator
 * @date 2013年11月6日 下午4:09:41
 */

public class SimpleControllerTest extends AnnotationJUnitTest {
	
	
	  @Test
	  public void testwelcome() throws Exception {

	    this.mockMvc.perform(get("/simple/saywel.json")
	    		.accept(MediaType.APPLICATION_JSON))
	    		.andDo(print())
	        .andExpect(status().isOk())
	        .andExpect(content().contentType("application/json"));
       }
	  public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	  @Test	  
	  public void testPostJson() throws Exception{
		
		  this.mockMvc.perform(post("/simple/saveMessage.json")				
				 .content("{\"code\":\"rt\",\"msg\":\"This is a test to post json\"}")
				 .contentType(APPLICATION_JSON_UTF8)				  
		    	  .accept(APPLICATION_JSON_UTF8)
				  )  		
		    		.andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("code").value("9999"));
		
	  }
	  
	  @Test
	  public void testpostByParam() throws Exception{
		  this.mockMvc.perform(post("/simple/postByParam.json")
				 .param("jsonString", "{\"code\":\"rt\",\"msg\":\"This is a test to post json\"}")
				 .content("{\"code\":\"rt\",\"msg\":\"ONLYJSON\"}")
				 .contentType(APPLICATION_JSON_UTF8)				  
		    	  .accept(APPLICATION_JSON_UTF8)
				  )  		
		    		.andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("message.code").value("99990"));
		  //org.springframework.http.converter.HttpMessageNotReadableException hh;
		  //org.springframework.web.HttpMediaTypeNotSupportedException aa;
	  }
	 
	  @Test
	  public void testGetJson() throws Exception{
		 
		  this.mockMvc.perform(get("/simple/getme"))		    		
		    		.andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("code").value("8888"));
		
	  }
    
}
