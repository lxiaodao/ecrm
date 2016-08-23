/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.upload.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;

import com.weheros.framework.core.exception.FastDFSClientException;
import com.weheros.framework.core.exception.UploadFileException;
import com.weheros.framework.core.infrastructure.filesystem.FileSystemAccessFactory;
import com.weheros.framework.core.log.LogService;

/**
 * Single file or picture is limited 4M.
 * @ClassName: FileUploadService
 * @Description: 文件上传服务
 * @author Administrator
 * @date 2013年11月4日 下午5:08:58
 */
public class FileUploadService implements IFileUploadService{
	
	private List<String> upload(HttpServletRequest request) {
		long maxSize = 4 * 1024 * 1024 * 1024 ;//4M
		 ServletFileUpload upload = new ServletFileUpload();
		 upload.setFileSizeMax(maxSize);
		
		FileItemIterator fileIterator=null;
		List<String> urls=new ArrayList<String>();
		try {
			
			fileIterator = upload.getItemIterator(request);
		
		while (fileIterator.hasNext()) {
			//FileItem item = (FileItem) iter.next();
			  FileItemStream item = fileIterator.next();
			
			  String originName=item.getName();
			  if(originName==null||originName.isEmpty()){
				  continue;
			  }
			  //String contentType=item.getContentType();
			  LogService.info(FileUploadService.class, "---The origin file name is---"+originName);			 
			  //String suffix=originName.indexOf(".")!=-1?originName.split(".")[1]:"none";
			  String urloffile=FileSystemAccessFactory.buildFileSystemAccess().uploadFile(item.openStream(),"");
			  urls.add(urloffile);
		}
		
		} catch (FileUploadException | IOException | FastDFSClientException e) {
			LogService.fatal(FileUploadService.class, "FATAL:---upload file error---",e);
			throw new UploadFileException("getItemIterator error.",e);
		}
		if(urls.size()==0){
			throw new UploadFileException("Do not know the reason but upload fail.");
		}
		
		return urls;
	}
	
	@Override
	public List<String> uploadFiles(HttpServletRequest request) {		
		return this.upload(request);
	}
	@Override
	public List<String> uploadPictures(HttpServletRequest request) {
		return this.upload(request);
	}

	@Override
	public String uploadStream(InputStream inputStream,String name) throws IOException {		
		return FileSystemAccessFactory.buildFileSystemAccess().uploadFile(inputStream, name);
	}

}
