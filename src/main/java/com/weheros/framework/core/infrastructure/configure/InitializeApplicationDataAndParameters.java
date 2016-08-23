/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.configure;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.weheros.framework.core.infrastructure.datasystem.INoSQLDataAccess;

/**
 * 
 * @ClassName: InitializeApplicationDataAndParameters
 * @Description: 初始化数据或者参数。
 * 存入MongoDB
 * @author Yang
 * @date 2013年11月21日 下午4:42:47
 */
@Service
public class InitializeApplicationDataAndParameters {
	protected Logger log = Logger.getLogger(getClass());
	
//	@Autowired
//	@Qualifier("mongoDataAccessService")
//	INoSQLDataAccess noSQLDataAccess;
    
	private static final Lock lock = new ReentrantLock();
	

	@PostConstruct
	public void initializeAppConfigure(){
		
	}

	
}
