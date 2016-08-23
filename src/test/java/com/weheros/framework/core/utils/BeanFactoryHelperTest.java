/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.utils;

import org.junit.Assert;
import org.junit.Test;

import com.weheros.framework.core.log.LogService;
import com.weheros.framework.core.utils.BeanFactoryHelper;
import com.weheros.test.GenericJunitTestCase;

/**
 * @ClassName: BeanFactoryHelperTest 
 * @author Administrator
 * @date 2014年12月16日 下午5:37:44
 */
public class BeanFactoryHelperTest extends GenericJunitTestCase {
	
	
	@Test
	public void testgetbean(){
		Object object=BeanFactoryHelper.getTargetBean("mongoDataAccessService");
		Assert.assertNotNull(object);
		LogService.info(BeanFactoryHelperTest.class,"---class name is---"+object.getClass().getName());
	}

}
