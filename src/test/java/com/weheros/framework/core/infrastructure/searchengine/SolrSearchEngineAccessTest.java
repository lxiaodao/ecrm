/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.searchengine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.weheros.framework.core.infrastructure.searchengine.ISearchEngineAccess;
import com.weheros.test.GenericJunitTestCase;

/**
 * @ClassName: SolrSearchEngineAccessTest 
 * @author Administrator
 * @date 2014年12月4日 下午8:41:21
 */
public class SolrSearchEngineAccessTest extends GenericJunitTestCase{
	
	@Autowired
	@Qualifier("solrSearchEngineAccess")
	ISearchEngineAccess searchEngineAccess;
	
	//@Test
	public void testBasic() throws SolrServerException, IOException{
		 
           SolrInputDocument doc1 = new SolrInputDocument();
           doc1.addField("id", UUID.randomUUID().toString());          
                    
           doc1.addField("nodevalue_ss", 12.04);
           doc1.addField("nodevalue_ss", "animal");
           doc1.addField("nodevalue_ss", new Date());
           doc1.addField("nodevalue_ss", true);
           
       
           //
           Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
           docs.add(doc1);
		   searchEngineAccess.insert(doc1);
		   
		  
	}
	@Test
	public void testQueryBasic() throws SolrServerException{
		 String sql="nodevalue_ss:12.04";
         SolrQuery q = new SolrQuery();
         q.setStart(0);
         q.setRows(10);
         
         q.setQuery(sql);
         SolrDocumentList results=searchEngineAccess.query(q);
       
         for (SolrDocument doc:results) {
             String idResult = (String) doc.getFieldValue("id");
             // String nameResult = (String) doc.getFieldValue("name");
             System.out.println("--------------id:" + idResult);

         }
		
	}

}
