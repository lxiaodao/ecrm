/**
 * 
 */
package com.weheros.framework.core.infrastructure.datasystem;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author liuy
 *
 */
public class DeleteCollectionToolTest extends com.weheros.test.GenericJunitTestCase {
	
	protected static Logger LOG = Logger.getLogger(DeleteCollectionToolTest.class);
	
	@Autowired
	@Qualifier("mongoDataAccessService")
	INoSQLDataAccess noSQLDataAccess;
	
	@Test
	public void testDeleteTheCollection(){
		//Query.query(Criteria.where("keyWord").is("张强").and("year").is(21)
		//平民英雄
		
		/*String[] arrs=new String[]{
				"平民英雄","快乐大本营",
				"新闻大求真","刑警队长","甄嬛传","爱情公寓","铁在烧","喜剧总动员","雪狼谷","奔跑吧兄弟","老婆大人是80后",
				"金星秀"
		};*/
		String[] arrs=new String[]{
				"食不可挡","热线188"
		};
		//noSQLDataAccess.getMongoOps().findAllAndRemove(Query.query(Criteria.where("keyWord").is("平民英雄")), "programToVideo");
		for(String name:arrs){
			noSQLDataAccess.getMongoOps().remove(Query.query(Criteria.where("keyWord").regex(name)), "programToVideo");
			noSQLDataAccess.getMongoOps().remove(Query.query(Criteria.where("videoName").regex(name)), "videoRecord");
			noSQLDataAccess.getMongoOps().remove(Query.query(Criteria.where("videoName").regex(name)), "theme");
			noSQLDataAccess.getMongoOps().remove(Query.query(Criteria.where("videoName").regex(name)), "video");
		}

	}

}
