/**
 * 
 */
package com.weheros.framework.core.infrastructure.datasystem;

import junit.framework.Assert;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.weheros.framework.core.infrastructure.datasystem.IRelationalDataAccess;
import com.weheros.framework.core.log.LogService;
import com.weheros.framework.core.utils.ToJson;
import com.weheros.test.GenericJunitDataInDBTest;
import com.weheros.test.GenericJunitTestCase;

/**
 * @author AA
 *
 */
public class RelationalDataAccessTest extends GenericJunitDataInDBTest {
 
	@Autowired
	@Qualifier("relationalDataAccessService")
	IRelationalDataAccess relationalDataAccess;
	
	@Test
	public void testInsert(){
		String sql="insert into test (name,sex) values (?,?)";
		Object[] values=new Object[]{"东方一哥2",1};
		int num=relationalDataAccess.insert(sql, values);
		Assert.assertEquals(num, 1);
		select();
	}
	
	
	public void select(){
		String sql="select * from test";
		
		List<TestModel> list=relationalDataAccess.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<TestModel>(TestModel.class));
		for(TestModel model:list){
			
			LogService.info(RelationalDataAccessTest.class, ToJson.toJson(model));
		}
		
	
	}
}

