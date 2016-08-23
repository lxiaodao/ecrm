/**
 * 
 */
package com.weheros.framework.core.utils;

import javax.annotation.PostConstruct;


/**
 * 在Spring ApplicationContext中初始化Slf4对Java.util.logging的拦截.
 * 在配置文件中加入，spring容器管理它的初始化。
 * @author calvin
 */

public class JulOverSlf4jProcessor {

	//Spring在所有属性注入后自动执行的函数.
	@PostConstruct
	public void init() {
		//SLF4JBridgeHandler.removeHandlersForRootLogger();
		//SLF4JBridgeHandler.install();
	}
}
