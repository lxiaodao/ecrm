/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.upload.front;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.weheros.framework.core.front.CodeDefinition;
import com.weheros.framework.core.front.Message;
import com.weheros.framework.core.upload.application.IMultipartFileUpload;
import com.weheros.framework.core.upload.domain.SourceFile;

/**
 * @ClassName: UploadFileController 
 * @author Administrator
 * @date 2014年12月15日 下午2:59:15
 */
@Controller
public class UploadFileController {
	@Autowired
	@Qualifier("multipartFileUploadService")
	IMultipartFileUpload uploadService;
	 @RequestMapping(value = "/upload/uploadMultiplePictures", method = RequestMethod.POST)
	    public @ResponseBody
	    Message uploadMultipleFilePictures(HttpServletRequest request, HttpServletResponse response,	
	    		
	    		@RequestParam("file") MultipartFile[] files) throws IOException {		
		 String projectpath=request.getSession().getServletContext().getRealPath("/");
		 String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() ;
		 List<SourceFile> sources= uploadService.storeLocal(basePath,projectpath,files);
		
		Message message = new Message(Message.VISIT_SUCCESS,
				CodeDefinition.LOGIN_SUCCESS.getCode(),
				"Congratulations,upload file sucess.");
		message.setData(sources);

		return message;
		
	 }
	
	 @RequestMapping(value = "/upload/uploadMultipleFiles", method = RequestMethod.POST)
	    public @ResponseBody
	    Message uploadMultipleFileHandler(HttpServletRequest request, HttpServletResponse response,	
	    		
	    		@RequestParam("file") MultipartFile[] files) throws IOException {	
		 
	
		 
		 List<SourceFile> sources= uploadService.upload(files);		
		Message message = new Message(Message.VISIT_SUCCESS,
				CodeDefinition.LOGIN_SUCCESS.getCode(),
				"Congratulations,upload file sucess.");
		message.setData(sources);

		return message;
		
	 }
	 @RequestMapping(value = "/upload/uploadAndFormatPictures", method = RequestMethod.POST)
	    public @ResponseBody
	    Message uploadMultiplePicturesHandler(HttpServletRequest request, HttpServletResponse response,	
	    		
	    		@RequestParam("file") MultipartFile[] files) throws IOException {		
		 
		 List<SourceFile> sources= uploadService.uploadAndFormatPictures(files);
		Message message = new Message(Message.VISIT_SUCCESS,
				CodeDefinition.LOGIN_SUCCESS.getCode(),
				"Congratulations,upload file sucess.");
		message.setData(sources);

		return message;
		
	 }
	 @RequestMapping(value="/upload/toupload",method = RequestMethod.GET)	
	 public String toupload(ModelMap model){
			
		
			
			return "upload/upload";
	 }

}
