/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
package com.weheros.framework.core.infrastructure.searchengine;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.client.solrj.SolrServer;
//import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
//import org.apache.solr.core.CoreContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.weheros.framework.core.infrastructure.configure.ApplicationConfigureFactory;
import com.weheros.framework.core.infrastructure.configure.SolrConfigure;


/**
 * Factory to construct solr server instance.
 * @ClassName: SolrServerFactory
 * @Description: search engine construct factory.
 *               This is a spring bean configured in xml file.
 * @author Yang
 * @date 2013年10月31日 下午2:55:15
 * 
 */
@Component("solrServerFactory")
final class SolrServerFactory {
    
	protected static Logger log = LoggerFactory.getLogger(SolrServerFactory.class);
	  /**
     * 搜索引擎服务器,嵌入式
     */
    private static SolrServer solrServer = null;

    
    private static final Lock lock = new ReentrantLock();
  /**
   *  Construct solr server instance.
    * @author Yang
    * @return SolrServer
    *
   */
  public static SolrServer buildSolrServer(){   
        lock.lock();
        try{
		if (null == solrServer) {
			SolrConfigure solr = ApplicationConfigureFactory.takeConfigure(SolrConfigure.class);
			String url = solr.getSolrServerPath();
			if(!url.startsWith("http")){
				//
				throw new IllegalArgumentException("FATAL:The solr server url in app.properties is not correct!!");
			}
			solrServer = initHttpSolrServer(url);
		}
        }finally{
        	lock.unlock();
        }
	  return solrServer;
    }
    
  
    
    /**
     * 请注意，每次返回新的实例
     * @param url 
     * @param url 为空的时候，返回默认的相同的HttpSolrServer实例。
     * @return
     * @throws MalformedURLException 
     */
    private static SolrServer initHttpSolrServer(String url) {       
	    return new HttpSolrServer(url);
	
    }

   
}
