/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.upload.format;

import com.weheros.framework.core.exception.InfrastructureException;

/**
 * @ClassName: FormatException 
 * @author Administrator
 * @date 2014年12月18日 下午6:29:58
 */
public class FormatException extends InfrastructureException{

	public FormatException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public FormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public FormatException(Throwable cause) {
		super(cause);
	}

	/**
	  * @Fields serialVersionUID 
	  */
		
	private static final long serialVersionUID = 6576412971056200631L;

}
