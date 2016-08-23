package com.weheros.framework.core.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Yang
 * @version 1.0
 * @created 27-??-2014 12:11:09
 */
public class CommonThreadPool {
	
	public static ExecutorService executor = Executors.newCachedThreadPool();
   
	
}