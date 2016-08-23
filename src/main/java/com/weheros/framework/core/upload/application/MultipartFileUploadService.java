/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.upload.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.weheros.framework.core.exception.UploadFileException;
import com.weheros.framework.core.infrastructure.filesystem.FileSystemAccessFactory;
import com.weheros.framework.core.log.LogService;
import com.weheros.framework.core.upload.domain.SourceFile;
import com.weheros.framework.core.upload.format.PictureService;

/**
 * @ClassName: MultipartFileUploadService 
 * @author Administrator
 * @date 2014年12月15日 下午4:45:28
 */
@Service("multipartFileUploadService")
public class MultipartFileUploadService implements IMultipartFileUpload {
    
	public List<SourceFile> upload(MultipartFile[] files) throws UploadFileException {
		List<SourceFile> sources=new ArrayList<SourceFile>();
	    for(MultipartFile file:files){
	    	if(file.isEmpty()){
	    		throw new UploadFileException("the_upload_file_stream_is_empty:"+file.getOriginalFilename());
	    	}
	    	LogService.info(MultipartFileUploadService.class, "---The origin file name is---"+file.getOriginalFilename());
	
		    String url="";
			try {
				url = FileSystemAccessFactory.buildFileSystemAccess().uploadFile(file.getInputStream(), "");
			} catch (IOException e) {
				LogService.fatal(MultipartFileUploadService.class, "--- uploadFile error ---",e);
				throw new UploadFileException("---uploadAndFormatPictures error---",e);
			}
		    SourceFile source=new SourceFile(file.getOriginalFilename(),url,file.getContentType());
		    sources.add(source);
	    }
		return sources;
		
	}
	


	@Override
	public List<SourceFile> uploadAndFormatPictures(MultipartFile[] files)
			throws UploadFileException {
		List<SourceFile> sources=new ArrayList<SourceFile>();
	    for(MultipartFile file:files){
	    	if(file.isEmpty()){
	    		throw new UploadFileException("the_upload_file_stream_is_empty:"+file.getOriginalFilename());
	    	}
	    	LogService.info(FileUploadService.class, "---The origin file name is---"+file.getOriginalFilename());

	    	SourceFile source=null;
			try {
				source = PictureService.formatAndUpload(file.getOriginalFilename(),file.getContentType(),file.getInputStream());
			} catch (InterruptedException | ExecutionException | IOException e) {
				LogService.fatal(MultipartFileUploadService.class, "--- formatAndUpload error ---",e);
				throw new UploadFileException("---uploadAndFormatPictures error---",e);
			}
	    	sources.add(source);
	    }
		return sources;
	}



	@Override
	public List<SourceFile> storeLocal(String baseurl,String projectpath, MultipartFile[] files) {
		List<SourceFile> sources=new ArrayList<SourceFile>();
		int number=0;
	    for(MultipartFile file:files){
	    	if(file.isEmpty()){
	    		//throw new UploadFileException("the_upload_file_stream_is_empty:"+file.getOriginalFilename());
	    		continue;
	    	}
	    	
	    	number++;
	    	LogService.info(FileUploadService.class, "---The origin file name is---"+file.getOriginalFilename());
	
		    String url="";
			try {
				url = storeLocal(file, projectpath);
				//LogService.info(this.getClass(), "----before replace baseurl is---"+baseurl);
				url=url.replace(projectpath, baseurl+"/");
				LogService.info(this.getClass(), "----baseurl is---"+url);
			} catch (IOException e) {
				LogService.fatal(MultipartFileUploadService.class, "--- uploadFile error ---",e);
				throw new UploadFileException("---uploadAndFormatPictures error---",e);
			}
		    SourceFile source=new SourceFile(file.getOriginalFilename(),url,file.getContentType());
		    sources.add(source);
	    }
	    if(number==0){
    		throw new UploadFileException("the_upload_file_stream_is_empty:");
    	}
		return sources;
	}



	private String storeLocal(MultipartFile mfile, String projectpath) throws IOException {
		FileOutputStream outstream=null;
		FileChannel filechannel=null;
		
		String theStorePath=projectpath+"uploadpics/";
		
        String random=UUID.randomUUID().toString();
        for(char achar:random.toCharArray()){
        	if(achar!='-'){
        	theStorePath+=achar+"/";
        	}
        }
        InputStream inputStream=mfile.getInputStream();
        File path=new File(theStorePath);
        if(!path.exists()){
        	path.mkdirs();
        }
        String uuidname=UUID.randomUUID().toString();
        uuidname=uuidname.substring(0, 8);
        
        theStorePath+=uuidname;
        LogService.info(this.getClass(),"-------the really store file path is----------------"+theStorePath);
       File outFile=new File(theStorePath);
        if(!outFile.exists()){        	
        	outFile.createNewFile();
        }
		outstream = new FileOutputStream(outFile);
		
		filechannel = outstream.getChannel();
		int index = 0;
		for (;;) {
			byte[] content = new byte[8192];
			int res = inputStream.read(content);// 从流中读取固定长度的byte[]
			if (res == -1) {
				break;
			}

			ByteBuffer contentBuffer = ByteBuffer.wrap(content);							
			filechannel.write(contentBuffer, index);// 一段一段写入
			index = index + res;

		}
		return theStorePath;
	}

}
