/**

 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.test;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * 需要测试controller的时候可以使用此基类.<p/>
 * 测试类可以覆写此测试基类的setUp和cleanUp方法。<p/>
 * <code>
 * 子类写法：<p/>
 *  @Before
	public void setUp() {	
        super.setUp();
        //
	} 
 * </code>
 *  * <p />
 * 请注意下面三类测试基类的区别：<p/>
 * <code>GenericJunitDataInDBTest</code>
 * <code>GenericJunitTestCase</code>
 * <code>AnnotationJUnitTest</code>
 * @ClassName: AnnotationJUnitTest
 * @Description: 测试基类，全部使用注解
 * @author Yang
 * @date 2013年11月11日 下午1:52:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/framework-servlet.xml","classpath*:/spring/applicationContext.xml" })
public abstract class AnnotationJUnitTest {
	protected Logger log = Logger.getLogger(getClass()); 
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Autowired
	protected WebApplicationContext wac;
	protected MockMvc mockMvc;

	private void initMock(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Before
	public void setUp() {	
        this.initMock();
	}

	@After
	public void cleanUp() {
		
	}
	
}
