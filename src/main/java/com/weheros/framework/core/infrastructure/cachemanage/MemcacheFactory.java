/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.infrastructure.cachemanage;


/**
 * Do not use memcached since 2014.12.02. <p/>
 * @ClassName: MemcacheFactory 
 * @author Administrator
 * @date 2014年1月2日 上午11:04:08
 */
/*@Service("MemcacheFactory")
public class MemcacheFactory {
	private final static Logger log = Logger.getLogger(MemcacheFactory.class);
	private static final Lock lock = new ReentrantLock();
	private static MemcachedClientBuilder builder=null;

	
	@Autowired
	private InitializeApplicationDataAndParameters initialization;
	
	public static MemcachedClient getMemcachedClient(){
		MemcachedClient client=null;
		try {
			client= builder.build();
		} catch (IOException e) {
			log.error("---------build memcache client error--------------",e);
			throw new InfrastructureException("-build memcache client error",e);
		}
		return client;
	}
    
	@PostConstruct
	private void initializeMemcache(){
		//10.0.0.40:11211
		lock.lock();
		if(null!=builder){
			log.info("==================ONLY initialize memcache one time!============");
			String ips=initialization.getAppconfig().getMemcachedIps();
			builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ips),new int[] { 1, 1});
			builder.setCommandFactory(new BinaryCommandFactory());// use binary protocol
			builder.setConnectionPoolSize(200);
		}
		
		lock.unlock();
	}
}*/
