/**
 * 
 */
package com.weheros.framework.core.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * @author liuy
 *
 */
public class ConcurrentTreadTest {
	
	
	@Test
	public void testFuture() throws InterruptedException, ExecutionException{
		
	    //
		List<Future<String> > list=new ArrayList<Future<String> >();
		for(int i=0;i<10;i++){
			Future<String> future=CommonThreadPool.executor.submit(new ElementTask(i+"tk"));
			
			list.add(future);
		}
		
		for(Future<String> fu:list){
			System.out.println(fu.get());
		}
	}

}



class ElementTask implements Callable<String> {
	
	private String name;
	public ElementTask(String name){
		this.name=name;
	}

	@Override
	public String call() throws Exception {
		
		System.out.println("------begin excutoe--------"+name);
		Thread.sleep(name.length()*1000*10);
		System.out.println("------excutoe--------"+name);
		
		return name;
	}
	
	
}
