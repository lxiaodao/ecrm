/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.filesystem;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.weheros.framework.core.infrastructure.filesystem.FileSystemAccessFactory;
import com.weheros.framework.core.infrastructure.filesystem.IFileSystemAccess;
import com.weheros.framework.core.infrastructure.filesystem.LocalFileSystemAccess;
import com.weheros.test.GenericJunitTestCase;

/**
 * @ClassName: FileSystemAccessFactoryTest
 * 
 * @author Administrator
 * @date 2013年11月6日 下午3:41:00
 */
public class FileSystemAccessFactoryTest extends GenericJunitTestCase {
    
	@Autowired
	FileSystemAccessFactory factory;
	
	@Value("${fdfs.tracker_server}")
	String fdfsUrl;
	
	@Test
	public void testBuild(){
		IFileSystemAccess systemaccess=factory.buildFileSystemAccess();
		Assert.assertTrue(systemaccess!=null);
		Assert.assertTrue(systemaccess instanceof LocalFileSystemAccess);
	}
	@Test
	public void testGetFdfsServer(){
		Assert.assertNotNull(fdfsUrl);
		this.logger.info("----this is fastdfs server---"+fdfsUrl);
	}
}
