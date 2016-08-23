/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.upload.domain;

import com.weheros.framework.core.infrastructure.configure.ApplicationConfigureFactory;
import com.weheros.framework.core.infrastructure.configure.FastDFSConfigure;

/**
 * 文件定义对象，包括图片大小格式。<p>
 * thumbIMG-220px*220px （列表使用）
 * thumbIMG-480px*480px   （产品详情）
 * thumbIMG-1200px*1200px  （查看大图）
 * thumbIMG-原图
 * @ClassName: SourceFile 
 * @author Yang
 * @date 2014年12月15日 下午4:48:18
 */
public class SourceFile {
	
	public enum PictureSize{
		BIG(1200,1200),MEDIUM(480,480),THUMB(220,220);
		
		private PictureSize(Integer wide, Integer high) {
			this.wide=wide;
			this.high=high;
		}
		private Integer wide;		
		private Integer high;
		public Integer getWide() {
			return wide;
		}
		public Integer getHigh() {
			return high;
		}
		
	}
	
	
	private String name;//origin name	
	private String origin; //url of origin file
	private String contentType; //content type of file.
	private String big;
	private String medium;	
	private String thumb;
	
	public SourceFile(String name, String origin,
			String contentType) {
		
		this.name = name;	
		this.origin = origin;
		this.contentType = contentType;
	}
	
	
	public SourceFile(String name, String origin, String contentType,
			String big, String medium, String thumb) {
		super();
		this.name = name;
		this.origin = origin;
		this.contentType = contentType;
		this.big = big;
		this.medium = medium;
		this.thumb = thumb;
	}


	public String getName() {
		return name;
	}
	public String getBig() {
		return this.big;
	}
	public String getThumb() {
		return this.thumb;
	}
	public String getOrigin() {
		return this.origin;
	}
	public String getContentType() {
		return contentType;
	}

	public String getMedium() {
		return this.medium;
	}

}
