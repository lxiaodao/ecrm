/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.upload.application;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.weheros.framework.core.exception.UploadFileException;
import com.weheros.framework.core.upload.domain.SourceFile;

/**
 * 目前没有限制上传文件大小，限制大小配置需要配置<code>CommonsMultipartResolver</code>
 * @ClassName: IMultipartFileUpload 
 * @author Administrator
 * @date 2014年12月17日 下午5:00:21
 */
public interface IMultipartFileUpload {
	/**
	 *  上传文件（包括图片），不限制大小。
	  * @author Yang
	  * @param files
	  * @return
	  * @throws IOException 
	  * List<SourceFile>
	  *
	 */
	public List<SourceFile> upload(MultipartFile[] files) throws UploadFileException;
	/**
	 *  分割图片成为集中格式后，上传到分布式文件服务器。
	  * @author Yang
	  * @param files
	  * @return List<SourceFile>
	  * @throws IOException 
	  * 
	  *
	 */
	public List<SourceFile> uploadAndFormatPictures(MultipartFile[] files) throws UploadFileException;
	/**
	 * 
	  * <描述>
	  * @author Yang
	  * @param basepath
	  * @param projectpath2
	  * @param files
	  * @return 
	  * List<SourceFile>
	  *
	 */
	public List<SourceFile> storeLocal(String basepath, String projectpath, MultipartFile[] files);

}
