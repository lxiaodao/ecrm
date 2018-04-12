/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */

package com.weheros.framework.core.infrastructure.datasystem;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @ClassName: INoSQLDataAccess
 * @Description: NoSQL访问接口
 * @author Yang
 * @date 2013年10月30日 上午11:40:30
 *
 */

public interface INoSQLDataAccess extends IDataSystemAccess
{
    /**
     * 
      * 插入数据
      * @author Yang
      * @param objectToSave json格式的数据对象
      * @return Object
      * 
     */
    public Object insert(final Object objectToSave);
    
    /**
     * 
      * 只是更新一条记录
      * @author Yang
      * @param id 主键
      * @param keyValues key和value的集合
      * @param entityClass
      * @return int 更新的数量
      * @throws
     */
    public int updateOnlyOne(final String id, Map<String, Object> keyValues, Class<?> entityClass);
    
    /**
     *  更新符合条件的所有数据记录
      * @author Administrator
      * @param id
      * @param keyValues
      * @param entityClass
      * @return int
      * @throws
     */
    public int update(final String id, Map<String, Object> keyValues, Class<?> entityClass);
    
    /**
     *  删除
      * @author Administrator
      * @param id
      * @param entityClass 
      * @throws
     */
    public void delete(final Object id, Class<?> entityClass);
    
    /**
     * @param <T>
     *  根据查询条件查询一条记录。
      * @author Administrator
      * @param query
      * @param entityClass
      * @return Object
      * @throws
     */
    public <T> T query(Query query, Class<T> entityClass);
    
    public <T> List<T> queryList(Query query, Class<T> entityClass);
    
    /**
     * 
      * 统计数量查询
      * @author Administrator
      * @param query
      * @param entityClass
      * @return long
      *
     */
    public long count(Query query, Class<?> entityClass);
    
    /**
     *  get the mongoTemplate to obtain more operations.
     *  @see MongoDataAccessServiceTest
      * @author Administrator
      * @return 
      * MongoOperations
      *
     */
    public MongoOperations getMongoOps();
    
}
