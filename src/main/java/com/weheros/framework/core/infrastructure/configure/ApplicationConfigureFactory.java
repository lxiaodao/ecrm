/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.configure;

import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.weheros.framework.core.log.LogService;
import com.weheros.framework.core.utils.PropertiesService;

/**
 * Factory for AppConfigure,Such as <code>FastDFSConfigure</code>
 * @ClassName: ApplicationConfigure 
 * @author Yang
 * @date 2014年12月2日 下午5:45:57
 */
public class ApplicationConfigureFactory {
	private static Map<AppConfigureType,AppConfigure> appconfigures=new HashMap<AppConfigureType,AppConfigure>();
	static{
		try {
			loadApplicationConfigures();
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			
			LogService.fatal(ApplicationConfigureFactory.class, "FATAL: Load app.properties and initialize configure with error. ",e);
		}
	}
	
	enum AppConfigureType{
		FDFS,SOLR,MONGO,PUSH,UPLOAD
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T takeConfigure(Class<T> aclass){
		AppConfigureType type=AppConfigureType.FDFS;
		if(aclass == FastDFSConfigure.class ){
			type=AppConfigureType.FDFS;
		}else if(aclass == SolrConfigure.class){
			type=AppConfigureType.SOLR;
		}else if(aclass == PushConfigure.class){
			type=AppConfigureType.PUSH;
		}else if (aclass == UploadConfigure.class){
			type=AppConfigureType.UPLOAD;
		}
		return (T) ApplicationConfigureFactory.buildAppConfigure(type);		
	}
		
	public static AppConfigure buildAppConfigure(AppConfigureType key){		
		return appconfigures.get(key);
	}
	/**
	 *  Load the inftrastruction/app.peoperties file and initialize the configure for three object.<p/>
	 *  There are two configure,one is FastDFS and other is Solr.
	  * @author Yang
	  * @return  Map<AppConfigureType,AppConfigure>
	  * @throws NoSuchFieldException
	  * @throws SecurityException
	  * @throws IllegalArgumentException
	  * @throws IllegalAccessException 
	  *
	  *
	 */
	private static Map<AppConfigureType,AppConfigure> loadApplicationConfigures() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		//Read configure parameters from properties.
		//TODO:Configure parameters should be load from database,ie mongodb.
		Enumeration<?> keyNames=PropertiesService.takeProperties(PropertiesService.PROPS_NAME_APP).propertyNames();	
			
		while(keyNames.hasMoreElements()){
			Object key=keyNames.nextElement();
			String strkey=key.toString();
			if (strkey.startsWith("fdfs")) {
				// this is fastdfs configure
				if(!appconfigures.containsKey(AppConfigureType.FDFS)){
					AppConfigure configure=new FastDFSConfigure();
					Field field = configure.getClass().getDeclaredField(strkey.substring(5));
					field.setAccessible(true);					
					Object value=getExactTypeValue(field,PropertiesService.takeProperties(PropertiesService.PROPS_NAME_APP).get(key));
					field.set(configure,value);
					appconfigures.put(AppConfigureType.FDFS, configure);
				}else{
					AppConfigure configure=appconfigures.get(AppConfigureType.FDFS);
					Field field = configure.getClass().getDeclaredField(strkey.substring(5));
					field.setAccessible(true);
					Object value=getExactTypeValue(field,PropertiesService.takeProperties(PropertiesService.PROPS_NAME_APP).get(key));
					field.set(configure,value);
				}
				
			
			}else if(strkey.startsWith("mongo")){
				//this is mongodb configure which is already in context xml file.
				//Do nothing.
				
				
			}else if(strkey.startsWith("solr")){
				//this is solr
				if(!appconfigures.containsKey(AppConfigureType.SOLR)){
					AppConfigure configure=new SolrConfigure();
					Field field=configure.getClass().getDeclaredField(strkey.substring(5));
					field.setAccessible(true);
					Object value=getExactTypeValue(field,PropertiesService.takeProperties(PropertiesService.PROPS_NAME_APP).get(key));
					field.set(configure,value);
					appconfigures.put(AppConfigureType.SOLR, configure);
				}else{
					AppConfigure configure=appconfigures.get(AppConfigureType.SOLR);
					Field field=configure.getClass().getDeclaredField(strkey.substring(5));
					field.setAccessible(true);
					Object value=getExactTypeValue(field,PropertiesService.takeProperties(PropertiesService.PROPS_NAME_APP).get(key));
					field.set(configure,value);
					
					appconfigures.put(AppConfigureType.SOLR, configure);
				}
				
			   
				
			}else if(strkey.startsWith("redis")){
		        // Redis is already configured in spring context xml file.
				// Do nothing here.
				
			}else if(strkey.startsWith("push")){
				//this is push configure
				if(!appconfigures.containsKey(AppConfigureType.PUSH)){
					AppConfigure configure=new PushConfigure();
					Field field=configure.getClass().getDeclaredField(strkey.substring(5));
					field.setAccessible(true);
					Object value=getExactTypeValue(field,PropertiesService.takeProperties(PropertiesService.PROPS_NAME_APP).get(key));
					field.set(configure,value);
					appconfigures.put(AppConfigureType.PUSH, configure);
				}else{
					AppConfigure configure=appconfigures.get(AppConfigureType.PUSH);
					Field field=configure.getClass().getDeclaredField(strkey.substring(5));
					field.setAccessible(true);
					Object value=getExactTypeValue(field,PropertiesService.takeProperties(PropertiesService.PROPS_NAME_APP).get(key));
					field.set(configure,value);
					
					appconfigures.put(AppConfigureType.PUSH, configure);
				}
				
			}else if(strkey.startsWith("uplo")){
				if(!appconfigures.containsKey(AppConfigureType.UPLOAD)){
					AppConfigure configure=new UploadConfigure();
					Field field=configure.getClass().getDeclaredField(strkey.substring(5));
					field.setAccessible(true);
					Object value=getExactTypeValue(field,PropertiesService.takeProperties(PropertiesService.PROPS_NAME_APP).get(key));
					field.set(configure,value);
					appconfigures.put(AppConfigureType.UPLOAD, configure);
				}else{
					AppConfigure configure=appconfigures.get(AppConfigureType.UPLOAD);
					Field field=configure.getClass().getDeclaredField(strkey.substring(5));
					field.setAccessible(true);
					Object value=getExactTypeValue(field,PropertiesService.takeProperties(PropertiesService.PROPS_NAME_APP).get(key));
					field.set(configure,value);
					
					appconfigures.put(AppConfigureType.UPLOAD, configure);
				}
				
			}else{
		
				// It is wrong.
				throw new IllegalArgumentException("The key is "+strkey+" Can not recognize the begin of the key in properties.");
			}
			
		}
		
		return appconfigures;
	}
    /**
     * 
      * 得到某个对象属性字段对应类型的值，目前支持Integer,Double,Long.<p/>
      * @author Administrator
      * @param field
      * @param value
      * @return 
      * Object
      *
     */
	private static Object getExactTypeValue(Field field, Object value) {
		Class<?> needclass=field.getType();
		
		if(needclass == Integer.class){
			value=Integer.valueOf(value.toString());
		}else if(needclass == Double.class){
			value=Double.valueOf(value.toString());
		}else if(needclass == Long.class){
			value=Long.valueOf(value.toString());
		}else if(needclass == Boolean.class){
			value="false".equals(value.toString())?false:true;
		}
		return value;
	}
	

}
