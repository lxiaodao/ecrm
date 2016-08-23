/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.upload.format;

import java.nio.file.Path;
import java.nio.file.Paths;

import magick.MagickException;

import org.junit.Test;

import com.weheros.framework.core.upload.format.JMagickFormat;

/**
 * 没有解决加载dll包的问题
 * @ClassName: MagickImageFormatTest 
 * @author Administrator
 * @date 2014年12月18日 下午3:33:39
 */
public class MagickImageFormatTest {
	
	
	public static void main(String[] args)throws MagickException{
		//Path currentRelativePath = Paths.get("");
		//String s = currentRelativePath.toAbsolutePath().toString();
		String origin="E:/company/technology/magick/small.jpg";
		//InputStream smallIs = FastDFSClientTest.class.getClassLoader().getResourceAsStream(small);
		String localStorePath=origin.replace("small.jpg", "big_small.jpg");
		System.out.println("--- absolute new path---"+localStorePath);
		new JMagickFormat().format(origin, localStorePath, 1200, 1200);
	}

}
