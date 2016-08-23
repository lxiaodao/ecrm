/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.cachemanage;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.weheros.framework.core.log.LogService;
import com.weheros.test.GenericJunitTestCase;

/**
 * @ClassName: RedisCacheManagerTest 
 * @author Administrator
 * @date 2014年12月5日 下午3:56:35
 */
public class RedisCacheManagerTest extends GenericJunitTestCase {
	
	@Autowired
	QuerySimple querySimple;
	
	@Test
	public void testSimpleCache(){
		
		for(int i=0;i<10;i++){			
			
			LogService.info(QuerySimple.class,"---query back---"+querySimple.querySomething(""));
		}
	}
	

}

@Service
class QuerySimple{
	
	@Cacheable(value="simpleQueryCache")
	public String querySomething(String para){
		LogService.info(QuerySimple.class, "-----excute the querySomething----");
		
		return "this is querysomething.";
	}
	
}
