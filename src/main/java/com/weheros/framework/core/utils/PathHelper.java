/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: PathHelper
 * @Description: 路径获取辅助类
 * @author Yang
 * @date 2013年11月4日 下午4:28:09
 */
public final class PathHelper {
     private PathHelper(){
    	 
     }
     
     public static String getAbsoluteServletPath(HttpServletRequest request){
    	 return request.getServletContext().getRealPath("/");  
     }
}
