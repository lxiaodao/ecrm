/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.searchengine;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

/**
 * @ClassName: ISearchEngineAccess
 * @Description: define the common interface to access search engin. 
 * @author Yang
 * @date 2013年10月31日 下午4:14:08
 * @TODO:
 *    编写通用的封装查询条件的类 SolrInputDocument <p>
 *    把查询条件封装成 SolrQuery
 */
public interface ISearchEngineAccess {
	
	/**
	  * Save data and create index.
	  * @author Yang
	  * @param document
	  * @return UpdateResponse Result object,see test code in <code>SolrSearchEngineAccessTest</code>
	  * @throws SolrServerException
	  * @throws IOException 
	 */
    public UpdateResponse insert(SolrInputDocument document) throws SolrServerException, IOException; 
   /**
     * Add mutiple documents in solr.
     * @author Yang
     * @param docs
     * @return UpdateResponse
     * @throws SolrServerException
     * @throws IOException 
     * 
     *
    */
   public UpdateResponse insertDocuments( Collection<SolrInputDocument> docs) throws SolrServerException, IOException;
    /**
     *  根据id删除索引数据
      * @author Administrator
      * @param id
      * @return
      * @throws SolrServerException
      * @throws IOException UpdateResponse
      * @throws
     */
    public UpdateResponse delete(String id) throws SolrServerException, IOException;
    /**
     *  根据查询条件删除索引数据
      * @author Administrator
      * @param conditions  Query string of solr,such as nodevalue_ss:12.04, <p/>
      *                   _d:[10 TO 11]
      * @return
      * @throws SolrServerException
      * @throws IOException UpdateResponse
      * @throws
     */
    public UpdateResponse deleteByQuery(String conditions) throws SolrServerException, IOException;
    /**
     * 
      * 根据查询条件查询需要的数据
      * @author Administrator
      * @param query
      * @return SolrDocumentList
      * @throws SolrServerException 
      * 
      *
     */
    public SolrDocumentList query(SolrQuery query) throws SolrServerException;
    /**
     *  根据查询条件，查询需要的数据。
     *  把查询结果分析，封装成需要的对象。
      * @author Administrator
      * @param query
      * @param entityClass
      * @return List<T>
      * @throws SolrServerException 
      * 
      *
     */
    public <T> List<T> query(SolrQuery query,Class<T> entityClass) throws SolrServerException;
}
