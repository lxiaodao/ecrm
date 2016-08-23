/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.filesystem;


import java.io.InputStream;

/**
 * 文件系统访问通用接口
 * @ClassName: IFileSystemAccess 
 * @author Administrator
 * @date 2013年11月4日 下午2:14:52
 */
public interface IFileSystemAccess {

	/**
	 *  传入上传文件的迭代集合，保存后返回文件的存放地址。
     *  <p>典型文件就是图片</p>
	  * @author Yang
	  * @param  inputStream 
	  * @param  suffix such as jpg,bmp,gif
	  * @return 
	  *
	 */
	public String uploadFile(InputStream inputStream, String suffix);
	public String uploadFile(InputStream inputStream);
	/**
	 *  Delete file by file id.
	  * @author Yang
	  * @param fileid
	  * @return boolean true,delete sucess,otherwise fail.
	  *
	 */
	public boolean deleteByFileid(String fileid);
	
	
}
