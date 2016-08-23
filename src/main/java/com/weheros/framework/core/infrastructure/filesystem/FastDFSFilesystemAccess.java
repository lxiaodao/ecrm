/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.filesystem;

import java.io.InputStream;

/**
 * @ClassName: FastDFSFilesystemAccess
 * @Description: TODO
 * @author Yang
 * @date 2013年11月4日 下午3:33:04
 */

class FastDFSFilesystemAccess implements IFileSystemAccess {


	@Override
	public String uploadFile(InputStream inputStream, String suffix) {		
		return FastDFSClient.uploadFile(inputStream, suffix);
	}

	@Override
	public boolean deleteByFileid(String fileid) {		
		return FastDFSClient.deleteByfileId(fileid)==0;
	}

	@Override
	public String uploadFile(InputStream inputStream) {
		return FastDFSClient.uploadFile(inputStream, "");
	}

}
