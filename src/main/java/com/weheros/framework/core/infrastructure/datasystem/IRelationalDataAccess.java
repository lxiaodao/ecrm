/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.datasystem;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * @ClassName: IRelationalDataAccess
 * @Description: TODO
 * @author Yang
 * @param <T>
 * @date 2013年10月28日 下午5:23:52
 *
 */

public interface IRelationalDataAccess extends IDataSystemAccess {
	
	public JdbcTemplate getJdbcTemplate();
	
   /**
     * 新增
     * 
     * @param sql 语句（如需传入参数，则必须以占位符的方式传入）
     * @param values 参数值
     * @return 影响行数
     */
    public int insert(final String sql, Object... values);
  /**
   * 
    * @Description: 插入数据并返回主键
    * @author Administrator
    * @param sql
    * @param params
    * @return Long
    * @throws
   */
    public Number insertAndReturnKey(final String sql, Object... values);
    /**
     * 通用的增删改方法。
     * 
     * @param sql 语句（如需传入参数，则必须以占位符的方式传入）
     * @param values 参数值
     * @return 影响行数
     */
    public int update(final String sql, Object... values);
    /**
     * 根据sql和参数值删除数据 
     * @param sql 语句（如需传入参数，则必须以占位符的方式传入）
     * @param values 参数值
     * @return 影响行数
     */
    public int delete(final String sql, Object... values);
    /**
     * @param <T>
     * 
      * 通用查询，可对多表进行查询，需要传入RowMapper
      * @author Yang
      * @param sql 以占位符的方式书写的语句
      * @param values 必须是同参数一一对应的值数组
      * @param rowMapper 封装需要的对象或者其他数据结构的工具
      * @return List
      * @throws
     */
    public <T> List<T> queryByMapper(final String sql, final Object[] values, final RowMapper<T> rowMapper);
    
    public <T> T queryOne(final String sql, final Object[] values, final RowMapper<T> rowMapper);
    /**
     * 
      * 根据查询条件，查询数据记录数量
      * <详细描述>
      * @author Administrator
      * @param sql 以占位符的方式书写的语句
      * @param values 必须是同参数一一对应的值数组
      * @return int
      * @throws
     */
    public int queryCount(final String sql,final Object...values);
    /**
     * 
      * 
      * 批量插入或者更新
      * @author Yang
      * @param valuesList 是一个包含多个Object数组的集合，其中数组里面存放需要插入或更新的数据。
      * @param sql  
      * @return int[]
      * @throws
     */
    public int[] insertOrUpdateAll(final String sql,final List<Object[]> valuesList);
    
    
}
