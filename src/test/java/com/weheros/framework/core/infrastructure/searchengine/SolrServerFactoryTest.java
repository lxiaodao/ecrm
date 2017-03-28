/**
 * 
 */
package com.weheros.framework.core.infrastructure.searchengine;

import org.junit.Test;
import org.springframework.util.Assert;

import com.weheros.framework.core.infrastructure.configure.ApplicationConfigureFactory;
import com.weheros.framework.core.infrastructure.configure.SolrConfigure;

/**
 * @author liuy
 *
 */
public class SolrServerFactoryTest {
	
	@Test
	public void testLoadSolr(){
		
		SolrConfigure solr = ApplicationConfigureFactory.takeConfigure(SolrConfigure.class);
		Assert.notNull(solr);
		String url = solr.getSolrServerPath();
		
	}

}
