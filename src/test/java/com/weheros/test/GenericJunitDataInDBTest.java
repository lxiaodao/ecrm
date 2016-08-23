package com.weheros.test;

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


/**
 * 继承本测试方法，测试数据保存入数据库，不会删除。<p/>
 * <p />
 * 请注意下面三类测试基类的区别：<p/>
 * <code>GenericJunitDataInDBTest</code>
 * <code>GenericJunitTestCase</code>
 * <code>AnnotationJUnitTest</code>
 * 
 * @author Yang
 *
 */
//@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-aop.xml" })
@ContextConfiguration(locations = { "classpath*:*/applicationContext.xml" })
public abstract class GenericJunitDataInDBTest extends AbstractJUnit4SpringContextTests{
	
}
