package com.weheros.framework.core.utils;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ToJsonTest {
	
	
	String json="[{\"bg\":\"240\",\"ed\":\"1420\",\"onebest\":\"早上不是打擂台。\",\"speaker\":\"1\"},"
+"{\"bg\":\"2150\",\"ed\":\"4380\",\"onebest\":\"你现在着急在身上鲜血四溅\",\"speaker\":\"2\"}]";
//{"bg":"5670","ed":"7540","onebest":"，你听过清脆的骨裂声吗","speaker":"2"},
//{"bg":"8890","ed":"9830","onebest":"你知道。","speaker":"2"},
//{"bg":"10160","ed":"11550","onebest":"你的同伴第一","speaker":"1"},
//{"bg":"11730","ed":"13550","onebest":"，一个个时代，你身边是什么感觉吧","speaker":"1"},
//{"bg":"15530","ed":"16110","onebest":"？马上","speaker":"1"},
//{"bg":"16740","ed":"17830","onebest":"你根本什么都不懂","speaker":"1"}
//,{"bg":"22290","ed":"24140","onebest":"。他认为，根本不懂调兵遣将","speaker":"1"},
//{"bg":"24930","ed":"25820","onebest":"指挥哈尔滨。","speaker":"1"}]";
	
	@Test
	public void test_convertJsonArrayToObject(){
		
		List<XfResult> results=ToJson.convertJsonArrayToObject(json, XfResult.class);
		Assert.assertTrue(results.size()>0);
		for(XfResult value:results){
			System.out.println(value.getOnebest());
		}
		
	}
	@Test
	public void test_convertJsonToObject(){
		String thisjson="{\"bg\":\"240\",\"ed\":\"1420\",\"onebest\":\"早上不是打擂台。\",\"speaker\":\"1\"}";
		XfResult result=ToJson.convertJsonToObject(thisjson, XfResult.class);
		System.out.println(result.getOnebest());
		
	}

}
class XfResult{
	String bg;
	String ed;
	String onebest;
	String speaker;
	public String getBg() {
		return bg;
	}
	public void setBg(String bg) {
		this.bg = bg;
	}
	public String getEd() {
		return ed;
	}
	public void setEd(String ed) {
		this.ed = ed;
	}
	public String getOnebest() {
		return onebest;
	}
	public void setOnebest(String onebest) {
		this.onebest = onebest;
	}
	public String getSpeaker() {
		return speaker;
	}
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
	
}
