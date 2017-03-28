package com.weheros.framework.core.infrastructure.datasystem;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
/**
 * MongoDB Map-Reduce方式替代group方式统计查询数据
 * @author liuy
 *
 */
public class MongoQueryTest extends com.weheros.test.GenericJunitTestCase {
	
	protected static Logger LOG = Logger.getLogger(MongoQueryTest.class);
	
	@Autowired
	@Qualifier("mongoDataAccessService")
	INoSQLDataAccess noSQLDataAccess;
	
	//@Before
	public void init(){
			
		for(int i=0;i<100;i++){
		   MongoAccount account3=new MongoAccount();
		   String name=i%2==0?"test":(i%3==0?"for3":"default"+i);
		   
		   int year=i%2==0?1997:(i%3==0?1995:1992);
		   account3.setName(name);
		   account3.setYear(year);
		   noSQLDataAccess.getMongoOps().insert(account3);
		}
		
	
	}
	
	//@After
	public void destory(){
		List<MongoAccount> all=this.noSQLDataAccess.getMongoOps().findAll(MongoAccount.class);
		for(MongoAccount one:all){
			this.noSQLDataAccess.getMongoOps().remove(one);
		}
	}
	
	@Test
	public void testGroupby(){
		//没有测试通过
		//noSQLDataAccess.getMongoOps().m
		
		//GroupByResults result = this.noSQLDataAccess.group(criteria,CollectionName, groupBy, entityClass);
		//noSQLDataAccess.getMongoOps().group(arg0, arg1, arg2)

	    //result.getRawResults().get("retval");  
		// emit(this.name+''+this.year, 1); 
		String keyp="test";
		 String mapFunction = "function(){ emit(this.name, "+keyp+"); }";
		 String reduceFunction = "function(key,spec){ if(key==spec){ Array.push(spec); } }";
		MapReduceResults<Cu> results=noSQLDataAccess.getMongoOps().mapReduce("accountColl", mapFunction, reduceFunction, Cu.class);
		for (Cu valueObject : results) {
			System.out.println(valueObject.toString());
		}
		
		BasicDBObject obj = new BasicDBObject();
		obj.append( "$eval" , "stored_javascript_name()" );
		CommandResult cresult=noSQLDataAccess.getMongoOps().executeCommand(obj);
		cresult.get("retval");
		
		//CommandResult
		
	}
	//@Test
	public void testMap(){
		
		//noSQLDataAccess.getMongoOps().m
		
		//GroupByResults result = this.noSQLDataAccess.group(criteria,CollectionName, groupBy, entityClass);
		//noSQLDataAccess.getMongoOps().group(arg0, arg1, arg2)

	    //result.getRawResults().get("retval");  
		// emit(this.name+''+this.year, 1); 
		 String mapFunction = "function(){ emit(this.name, 1); }";
		 String reduceFunction = "function(key,values){ return Array.sum(values);}";
		MapReduceResults<Values> results=noSQLDataAccess.getMongoOps().mapReduce("accountColl", mapFunction, reduceFunction, Values.class);
		for (Values valueObject : results) {
			System.out.println(valueObject.toString());
		}
		
	}

}
class Cu{
	String _id;
	String value;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
class Values{
	String _id;
	Integer value;


	
	public String get_id() {
		return _id;
	}



	public void set_id(String _id) {
		this._id = _id;
	}



	public Integer getValue() {
		return value;
	}



	public void setValue(Integer value) {
		this.value = value;
	}



	@Override
	public String toString(){
		return this._id+","+this.value;
	}
	
}
