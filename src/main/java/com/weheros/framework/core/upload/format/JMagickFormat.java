/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.upload.format;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

/**
 * @ClassName: ImageFormat 
 * @author Administrator
 * @date 2014年12月18日 上午11:17:32
 */
class JMagickFormat implements IPictureFormat{
	/**
	 * 
	  * 格式化本地图片成希望的分辨率
	  * @author Administrator
	  * @param origin
	  * @param localStorePath
	  * @param wide
	  * @param high
	  * @return
	  * @throws MagickException 
	  * String
	  *
	 */
	public String format(String origin,String localStorePath,Integer wide,Integer high){
		//String storepath=System.getProperty("java.io.tmpdir");
		ImageInfo origInfo;
		try {
			origInfo = new ImageInfo(origin);
			MagickImage image = new MagickImage(origInfo); //load image
			image = image.scaleImage(wide, high); //to Scale image
			image.setFileName(localStorePath); //give new location
			image.writeImage(origInfo); 
		} catch (MagickException e) {
			throw new FormatException("jmagick_format_picture_error",e);
		} //load image info
		
		return localStorePath;
	}

}
