/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.exception;

/**
 * @ClassName: FastDFSClientException 
 * @author Yang
 * @date 2014年12月4日 下午2:05:23
 */
public class FastDFSClientException extends InfrastructureException {

	
	/**
	  * @Fields serialVersionUID 
	  */		
	private static final long serialVersionUID = 2343229706913331830L;
	public FastDFSClientException(String message) {
		super(message);
	}

	public FastDFSClientException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FastDFSClientException(Throwable cause) {
		super(cause);
	}

}
