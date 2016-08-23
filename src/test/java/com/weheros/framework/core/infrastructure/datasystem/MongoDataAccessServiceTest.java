/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.datasystem;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.weheros.framework.core.utils.ToJson;


/**
 * @ClassName: MongoDataAccessService

 * @author Administrator
 * @date 2013年10月30日 下午10:46:44
 *
 */

public class MongoDataAccessServiceTest extends com.weheros.test.GenericJunitTestCase {
	protected Logger log = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("mongoDataAccessService")
	INoSQLDataAccess noSQLDataAccess;
	
	@Before
	public void init(){
			
		MongoAccount account=new MongoAccount();
		account.setName("张强");
		account.setYear(21);
	
		MongoAccount account2=new MongoAccount();
		account2.setName("中国南海自古就是领土");
		account2.setYear(10);
		
		MongoAccount account3=new MongoAccount();
		account3.setName("张强");
		account3.setYear(97);
		
		
		noSQLDataAccess.getMongoOps().insert(account);
		noSQLDataAccess.getMongoOps().insert(account2);
		noSQLDataAccess.getMongoOps().insert(account3);
	}
	
	@After
	public void destory(){
		List<MongoAccount> all=this.noSQLDataAccess.getMongoOps().findAll(MongoAccount.class);
		for(MongoAccount one:all){
			this.noSQLDataAccess.getMongoOps().remove(one);
		}
	}

	@Test
	public void testContact(){
		
		//.and("year").in(new Object[]{18,30})
		
		List<MongoAccount> list=noSQLDataAccess.queryList(Query.query(Criteria.where("name").is("张强").and("year").is(21)), MongoAccount.class);
		for(MongoAccount contact:list){
			log.info("--------------contact from mongo-----------"+ToJson.toJson(contact));
		}
		Assert.assertSame(list.size(),1);
		
	}
	@Test
	public void testLikeQuery(){
		//can not support or in mongodb
		//.orOperator(Criteria.where("contact.phone")).regex(pattern)
		//Query.query(Criteria.where("alias").regex(pattern)
		Pattern pattern=Pattern.compile("南海");
		Query monquery=Query.query(Criteria.where("name").regex(pattern)).limit(20).skip(0);
		
		List<MongoAccount> list=noSQLDataAccess.queryList(monquery, MongoAccount.class);
		
		Assert.assertSame(list.size(),1);
	}
	

}
