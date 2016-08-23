/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.upload.format;

/**
 * 图片格式化，
 * @see <code>SourceFile.PictureSize</code>
 * @ClassName: IPictureFormat 
 * @author Administrator
 * @date 2014年12月18日 下午6:26:42
 */
public interface IPictureFormat {
	
	public String format(String originPath,String targetPath,Integer wide,Integer high);

}
