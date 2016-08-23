/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.filesystem;

import java.io.IOException;
import java.io.InputStream;

import org.csource.common.MyException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.weheros.framework.core.infrastructure.filesystem.FastDFSClient;
import com.weheros.framework.core.log.LogService;
import com.weheros.framework.core.utils.PropertiesService;
import com.weheros.test.GenericJunitTestCase;

/**
 * @ClassName: FastDFSClientTest 
 * @author Administrator
 * @date 2014年12月3日 下午5:27:31
 */
public class FastDFSClientTest extends GenericJunitTestCase{
	private InputStream[] streams=new InputStream[4];
	@Before
	public void setUp(){
		String small="fdfs/small.jpg";
		InputStream smallIs = FastDFSClientTest.class.getClassLoader().getResourceAsStream(small);
		String middle="fdfs/middle.bmp";
		InputStream middleIs = FastDFSClientTest.class.getClassLoader().getResourceAsStream(middle);
		streams[0]=smallIs;
		streams[1]=middleIs;
	}
	
	@After
	public void cleanUp(){
		for(InputStream stream:streams){
			if(stream!=null){
				try {
					stream.close();
				} catch (IOException e) {
					LogService.error(this.getClass(), "---------close stream error in TEST----------",e);
				}
			}
		}
	}
	@Test
	public void testUploadSmallFile() throws IOException, MyException{
		String url=FastDFSClient.uploadFile(streams[0], "jpg");
		LogService.info(this.getClass(), "-----------return url is-------------"+url);
		Assert.assertNotNull(url);
	}
	
	//@Test
	public void testUploadMiddleFile() throws IOException, MyException{
		String url=FastDFSClient.uploadFile(streams[1], "bmp");
		LogService.info(this.getClass(), "-----------return url is-------------"+url);
		Assert.assertNotNull(url);
	}
	//@Test
	public void deleteFile() throws IOException, MyException{
		int result=FastDFSClient.deleteByfileId("group1/M00/00/0F/eccdJVSAA1CAObacADhxvnMu0is18..bmp");
		LogService.info(this.getClass(), "-----------return result is-------------"+result);
		Assert.assertTrue(result==0);
	}

}
