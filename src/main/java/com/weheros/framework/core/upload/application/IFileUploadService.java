/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.upload.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 编写代码简单处理上传的文件流stream。
 * @ClassName: IFileUploadService 
 * @author Administrator
 * @date 2014年12月15日 下午2:13:29
 */
public interface IFileUploadService {
	/**
	 *  Upload one or more files.Include pictures.
	 *  <p>The files uploaded is the same as the origin.
	  * @author Administrator
	  * @param request
	  * @return 
	  * List<String> The storeid of the files.
	  *
	 */
	public List<String> uploadFiles(HttpServletRequest request);
	/**
	 *  Upload one or more picture(s).<p/>
	 *  The pictures should be convert the size as need.<p>
	 *  TODO:These sizes are big,middle,small.Thumb
	  * @author Yang
	  * @param request
	  * @return List<String> The storeid of the pictures.
	  *
	 */
	public List<String> uploadPictures(HttpServletRequest request);
	/**
	 * 
	  * <描述>
	  * @author Administrator
	  * @param inputStream
	  * @param name Suffix of file,could be empty "".
	  * @return String
	 * @throws IOException 
	  *
	 */
	public String uploadStream(InputStream inputStream, String name) throws IOException;

}
