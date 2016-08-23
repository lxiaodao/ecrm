package com.weheros.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * 请使用junit4注解方式。<p/>
 * 测试使用的基础代码 需要数据源以及SpringContent时请继承本类。<p/>
 * 如果不需要数据库源，直接使用<code>@Test</code>注解标识测试方法.
 *  * <p />
 * 请注意下面三类测试基类的区别：<p/>
 * <code>GenericJunitDataInDBTest</code>
 * <code>GenericJunitTestCase</code>
 * <code>AnnotationJUnitTest</code>
 * @author Yang
 * 
 */
@ContextConfiguration(locations = { "classpath*:*/applicationContext.xml" })
public class GenericJunitTestCase extends AbstractTransactionalJUnit4SpringContextTests{
	
	

}
