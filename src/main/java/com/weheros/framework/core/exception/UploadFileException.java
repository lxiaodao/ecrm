/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.exception;

/**
 * @ClassName: FastDFSClientException 
 * @author Yang
 * @date 2014年12月4日 下午2:05:23
 */
public class UploadFileException extends InfrastructureException {

	
	/**
	  * @Fields serialVersionUID 
	  */		
	private static final long serialVersionUID = 2343229706913331830L;
	public UploadFileException(String message) {
		super(message);
	}

	public UploadFileException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UploadFileException(Throwable cause) {
		super(cause);
	}

}
