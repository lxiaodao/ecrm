/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.filesystem;


/**
 * @ClassName: FileSystemAccessFactory
 * @Description: 构建filesystemaccess
 * @author Administrator
 * @date 2013年11月6日 下午2:59:48
 */
public class FileSystemAccessFactory {
	/**
	  * 构建本地文件系统访问类 ，构建分布式文件系统(FastDFS)访问类。
	  * @author Administrator
	  * @param type
	  *        <p/>如果type为"local"，返回本地文件系统操作实例。
	  *        <p/>否则返回FastDFS的操作接口。
	  * @return IFileSystemAccess
	  *
	 */
	public static IFileSystemAccess buildFileSystemAccess(String type){
		if("local".equals(type)){
			return new LocalFileSystemAccess();
		}
		return new FastDFSFilesystemAccess();
	}
    /**
     *  构建分布式文件系统(FastDFS)访问类。
      * @author Yang
      * @return IFileSystemAccess
      *
     */
	public static IFileSystemAccess buildFileSystemAccess() {		
		return buildFileSystemAccess(null);
	}

}
