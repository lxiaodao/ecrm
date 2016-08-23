/**
 * Copyright (c) 2011-2014 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.upload.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import magick.MagickException;

import com.weheros.framework.core.exception.UploadFileException;
import com.weheros.framework.core.infrastructure.filesystem.FileSystemAccessFactory;
import com.weheros.framework.core.log.LogService;
import com.weheros.framework.core.thread.CommonThreadPool;
import com.weheros.framework.core.upload.domain.SourceFile;
import com.weheros.framework.core.upload.domain.SourceFile.PictureSize;
import com.weheros.framework.core.utils.ToJson;

/**
 * @ClassName: PictureService 
 * @author Administrator
 * @date 2014年12月18日 上午11:29:39
 */
public class PictureService {
	
	/**
	  * 
	  * @author Administrator
	  * @param originName
	  * @param contentType
	  * @param stream
	  * @return
	  * @throws IOException
	  * @throws InterruptedException
	  * @throws ExecutionException 
	  * SourceFile
	  *
	 */
	public static SourceFile formatAndUpload(String originName,String contentType,InputStream stream) throws IOException, InterruptedException, ExecutionException{
		//create three threads to complete three size picture format.		
		//format three size and upload to filesystem server.
		String origin="";
		String big="";
		String thumb="";
		String medium="";
		//store the origin pic at local.		
		String filepath=storeLocalOrigin(originName,stream);
		LogService.info(PictureService.class, "--- The origin image will be stored in ---"+filepath);
		//原图上传				
		FutureTask<String> fuorigin = new FutureTask<String>(new UploadAction(filepath));
		//三种尺寸的图
		String target_big_path=filepath.replace(originName, SourceFile.PictureSize.BIG.name()+"_"+originName);
		FutureTask<String> big_future = new FutureTask<String>(new FormatUploadAction(filepath,target_big_path,SourceFile.PictureSize.BIG));
		
		String mediumpath=filepath.replace(originName, SourceFile.PictureSize.MEDIUM.name()+"_"+originName);
		FutureTask<String> fumedium = new FutureTask<String>(new FormatUploadAction(filepath,mediumpath,SourceFile.PictureSize.MEDIUM));
		
		String thumbpath=filepath.replace(originName, SourceFile.PictureSize.THUMB.name()+"_"+originName);
		FutureTask<String> futhumb = new FutureTask<String>(new FormatUploadAction(filepath,thumbpath,SourceFile.PictureSize.THUMB));
		
		CommonThreadPool.executor.execute(fuorigin);
		CommonThreadPool.executor.execute(big_future);
		CommonThreadPool.executor.execute(fumedium);
		CommonThreadPool.executor.execute(futhumb);
		
		while (true) {
            //所有图片上传到文件服务器后，返回所有的图片地址
			if(fuorigin.isDone()&&big_future.isDone()&&fumedium.isDone()&&futhumb.isDone()){
				LogService.debug(PictureService.class, "--- All FILE UPLOAD SUCCESS.---");
				origin=fuorigin.get();
				big =big_future.get();
				medium=fumedium.get();
				thumb=futhumb.get();
				
				break;
			}
		}
		
		
		SourceFile source=new SourceFile(originName,origin,contentType,big,medium,thumb);
		LogService.debug(PictureService.class, "--- UPLOAD SUCCESS and the source is:---"+ToJson.toJson(source));
		return source;
	}

	private static String storeLocalOrigin(String originalFilename,
			InputStream inputStream){
		String directory=System.getProperty("java.io.tmpdir") + "/"+"iteluploadfiles"+"/"+UUID.randomUUID().toString();
		String filepath = directory+"/"+ originalFilename;
		File theDir = new File(directory);//new lcoal file path
		 if(!theDir.exists()){
			 theDir.mkdirs();
		 }
		FileOutputStream outstream=null;
		
		try {
			outstream = new FileOutputStream(filepath);// 打开通道
			FileChannel filechannel = outstream.getChannel();
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
		} catch (IOException e) {
			LogService.error(PictureService.class, "",e);
			throw new UploadFileException("---storeLocalOrigin error---",e);
		}finally{
			if(outstream!=null){
				try {
					outstream.close();
				} catch (IOException e) {
					LogService.error(PictureService.class, "",e);
				}
			}
		}
		return filepath;
	 }
	
}
class FormatUploadAction implements Callable<String>{
	private String targetPath="";
	public FormatUploadAction(String originPath,String targetPath,PictureSize size) {		
		
		IM4Format.getInstance().format(originPath,targetPath,size.getWide(),size.getHigh());
		
		this.targetPath=targetPath;
	}

	@Override
	public String call() throws Exception {
		File file=new File(targetPath);
		return FileSystemAccessFactory.buildFileSystemAccess().uploadFile(new FileInputStream(file));		
	}
	
}
class UploadAction implements Callable<String>{
    
	private String targetPath="";	
	
	public UploadAction(String filepath) {
		this.targetPath=filepath;
	}

	@Override
	public String call() throws Exception {
		File file=new File(targetPath);
		return FileSystemAccessFactory.buildFileSystemAccess().uploadFile(new FileInputStream(file));	
		
	}
	
}
