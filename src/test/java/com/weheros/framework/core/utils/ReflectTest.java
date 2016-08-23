package com.weheros.framework.core.utils;

import java.lang.reflect.Field;

import org.junit.Test;

public class ReflectTest {
	
	@Test
	public void testReflect(){
		SupplyIndex entity=new SupplyIndex();
		entity.setId(2l);
		entity.setArea_name("aree");
		Field[] fields=entity.getClass().getDeclaredFields();
		
		for(Field field:fields){
			field.setAccessible(true);
			try {
				System.out.println("----------------"+field.getName()+":"+field.get(entity));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

}
