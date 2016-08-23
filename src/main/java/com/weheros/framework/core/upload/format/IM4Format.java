/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.upload.format;

import java.io.IOException;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

import com.weheros.framework.core.log.LogService;

/**
 * @ClassName: IM4Format 
 * @author Administrator
 * @date 2014年12月19日 上午10:51:27
 */
public class IM4Format implements IPictureFormat {
	private static IM4Format instance=new IM4Format();
	private IM4Format(){
		
	}
	
	public static IM4Format getInstance(){
		return instance;
	}

	@Override
	public String format(String origin, String targetPath, Integer wide,
			Integer high) {
		ConvertCmd cmd = new ConvertCmd();
		IMOperation op = new IMOperation();
		op.addImage(origin);
		op.resize(wide, high);
		op.addImage(targetPath);
		
		try {
			cmd.run(op);
		} catch (IOException | InterruptedException | IM4JavaException e) {
			LogService.error(IM4Format.class, "im4_format_picture_error",e);
			throw new FormatException("im4_format_picture_error",e);
		}
		return targetPath;
	}

}
