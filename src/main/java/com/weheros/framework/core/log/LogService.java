/**
 * Copyright HZCW (He Zhong Chuang Wei) Technologies Co.,Ltd. 2013-2015. All rights reserved.
 */
	
package com.weheros.framework.core.log;

import org.apache.log4j.Logger;



/**
 * The unique log service.
 * 
 * @ClassName: LogService
 * @author Yang
 * @date 2014年4月2日 上午12:07:16
 */
public class LogService {
	private static final String PREFIX="WEHEROSPLATFORM";

	public static void debug(Class<?> aclass,String info){
    	Logger log4 = Logger.getLogger(aclass);
    	log4.debug(aclass.getName()+info);
    }
    public static void debug(Class<?> aclass,String info,Throwable e){
    	Logger log4 = Logger.getLogger(aclass);
    	log4.debug(aclass.getName()+info, e);
    }
    public static void info(Class<?> aclass,String info){
    	Logger log4 = Logger.getLogger(aclass);
    	log4.info(aclass.getName()+info);
    }
    public static void info(Class<?> aclass,String info,Throwable e){
    	Logger log4 = Logger.getLogger(aclass);
    	log4.info(aclass.getName()+info, e);
    }
    public static void error(Class<?> aclass,String info){
    	Logger log4 = Logger.getLogger(aclass);
    	log4.error(aclass.getName()+" ["+PREFIX+"] "+info);
    }
    public static void error(Class<?> aclass,String info,Throwable e){
    	Logger log4 = Logger.getLogger(aclass);
    	log4.error(aclass.getName()+" ["+PREFIX+"] "+info, e);
    }
    public static void warn(Class<?> aclass,String info,Throwable e){
    	Logger log4 = Logger.getLogger(aclass);
    	log4.warn(aclass.getName()+" ["+PREFIX+"] "+info, e);    	
    }
    
    public static void fatal(Class<?> aclass,String info){
    	Logger log4 = Logger.getLogger(aclass);
    	log4.fatal(aclass.getName()+" ["+PREFIX+"] "+" "+info);    	
    }
    public static void fatal(Class<?> aclass,String info,Throwable e){
    	Logger log4 = Logger.getLogger(aclass);
    	log4.fatal(aclass.getName()+" ["+PREFIX+"] "+info, e);    	
    }
    
    
}
