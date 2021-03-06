package com.weheros.framework.core.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanFactoryHelper {

	@Autowired
	private ApplicationContext applicationContext;
	
	private static ApplicationContext singleContext;
	
	public static Object getTargetBean(String beanName){		
		return singleContext.getBean(beanName);
	}
	public static <T> T getTargetBean(String beanName,Class<T> aclass){		
		return singleContext.getBean(beanName,aclass);
	}
	
	@PostConstruct
	private void afterSet(){
		singleContext=applicationContext;
	}
    
}
