/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.searchengine;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName: SolrSearchEngineAccess
 * @author Yang
 * @date 2013年10月31日 下午4:25:15
 */
@Service("solrSearchEngineAccess")
class SolrSearchEngineAccess implements ISearchEngineAccess {
	protected Logger log = LoggerFactory.getLogger(getClass());
	private final SolrServer server=SolrServerFactory.buildSolrServer();

	
	@Override
	public UpdateResponse insert(SolrInputDocument document) throws SolrServerException, IOException {	
		
		server.add(document);		
		return server.commit();
	}


	@Override
	public UpdateResponse delete(String id) throws SolrServerException, IOException {
		return server.deleteById(id);		
	}

	@Override
	public SolrDocumentList query(SolrQuery query) throws SolrServerException {
		QueryRequest request = new QueryRequest(query);
		request.setMethod(METHOD.GET);
		QueryResponse response = request.process(server);
		return response.getResults();

	}
    private SolrDocumentList excuteQuery(SolrQuery query) throws SolrServerException{
    	QueryRequest request = new QueryRequest(query);
		request.setMethod(METHOD.GET);
		QueryResponse response = request.process(server);
		return response.getResults();
    }

	@Override
	public <T> List<T> query(SolrQuery query, Class<T> entityClass) throws SolrServerException {
		List<T> returnList=new ArrayList<T>();
		try {
			
			Field[] fields=entityClass.getDeclaredFields();
			SolrDocumentList list=this.excuteQuery(query);
			 for (SolrDocument doc : list) { 
				 T newVo=entityClass.newInstance();//invoke default constructor to initialize
				 for(Field afield:fields){
						//  String idResult = (String) doc.getFieldValue("id");
					    //set value to the properties
						afield.set(newVo, doc.getFieldValue(afield.getName()));
					}
				 returnList.add(newVo);
			 }
			
		} catch (InstantiationException | IllegalAccessException e) {
			this.log.error("newInstance "+entityClass.getName()+" with error.", e);
		}	
		
		return returnList;
	}
	
	@Override
	public UpdateResponse insertDocuments(Collection<SolrInputDocument> docs) throws SolrServerException, IOException {
		server.add(docs);		
		return server.commit();
	}


	@Override
	public UpdateResponse deleteByQuery(String conditions)
			throws SolrServerException, IOException {		
		return this.server.deleteByQuery(conditions);
	}

}
