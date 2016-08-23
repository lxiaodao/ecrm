/*
* Copyright HZCW (He Zhong Chuang Wei) Technologies Co.,Ltd. 2013-2015. All rights reserved.
*
*
*/
package com.weheros.framework.core.infrastructure.datasystem;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: MongoDataAccessService
 * @Description: 操作MongoDB的接口
 * @author Administrator
 * @date 2013年10月30日 上午11:41:53
 *
 */
@Repository("mongoDataAccessService")
public class MongoDataAccess implements INoSQLDataAccess
{
    
    @Autowired
    @Qualifier("mongoTemplate")
    MongoOperations mongoOps;
    
    public MongoOperations getMongoOps()
    {
        return mongoOps;
    }
    
    @Override
    public Object insert(Object object)
    {
        mongoOps.insert(object);
        return object;
    }
    
    @Override
    public void delete(Object id, Class<?> entityClass)
    {
        this.mongoOps.remove(Query.query(Criteria.where("id").is(id)), entityClass);
    }
    
    @Override
    public int update(String id, Map<String, Object> keyValues, Class<?> entityClass)
    {
        return mongoOps.updateMulti(constructQuery("id", id), constructUpdate(keyValues), entityClass).getN();
    }
    
    @Override
    public int updateOnlyOne(String id, Map<String, Object> keyValues, Class<?> entityClass)
    {
        return mongoOps.updateFirst(constructQuery("id", id), constructUpdate(keyValues), entityClass).getN();
    }
    
    private Query constructQuery(final String key, final Object value)
    {
        return Query.query(Criteria.where(key).is(value));
    }
    
    private Update constructUpdate(final Map<String, Object> keyValues)
    {
        Iterator<Entry<String, Object>> iterator = keyValues.entrySet().iterator();
        Update update = new Update();
        while (iterator.hasNext())
        {
            Entry<String, Object> entry = iterator.next();
            // Update.update(entry.getKey(), entry.getValue());
            update.set(entry.getKey(), entry.getValue());
        }
        return update;
    }
    
    @Override
    public <T> List<T> queryList(Query query, Class<T> entityClass)
    {
        return mongoOps.find(query, entityClass);
    }
    
    @Override
    public <T> T query(Query query, Class<T> entityClass)
    {
        return mongoOps.findOne(query, entityClass);
    }
    
    @Override
    public long count(Query query, Class<?> entityClass)
    {
        return this.mongoOps.count(query, entityClass);
    }
    
}
