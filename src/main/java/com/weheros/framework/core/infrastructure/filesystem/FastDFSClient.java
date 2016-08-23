/** 
 * Copyright (c) 2013 hyhc,Inc. All Rights Reserved.
 */
package com.weheros.framework.core.infrastructure.filesystem;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;

import com.weheros.framework.core.exception.FastDFSClientException;
import com.weheros.framework.core.infrastructure.configure.ApplicationConfigureFactory;
import com.weheros.framework.core.infrastructure.configure.FastDFSConfigure;
import com.weheros.framework.core.log.LogService;

/**
 * 通用操作FastDFS的客户端接口 <p/>
 * 类不能被调用者实例化，屏蔽若干初始化细节，屏蔽调用FastDFS的接口细节。
 * @ClassName: FastDFSClient
 * @author wb
 * @author Yang 
 * @date 2013-1-20
 * 
 */
class FastDFSClient {
	private static final Lock lock = new ReentrantLock();
	private static TrackerGroup trackerGroup = null;
    private FastDFSClient(){
    	
    }
	/*public static TrackerGroup trackerGroup() {
		if (trackerGroup == null) {
			ClientGlobal.setG_connect_timeout(Integer.parseInt("100000"));
			ClientGlobal.setG_charset("UTF-8");
			ClientGlobal.setG_anti_steal_token(Boolean.parseBoolean("false"));
			ClientGlobal.setG_network_timeout(Integer.parseInt("300000"));
			ClientGlobal.setG_secret_key("FastDFS1234567890");
			ClientGlobal.setG_tracker_http_port(Integer.parseInt("8000"));
			ClientGlobal
					.setG_tracker_group(new TrackerGroup(
							new InetSocketAddress[] { new InetSocketAddress(
									"121.199.29.37:22122".split(":")[0],
									Integer.parseInt("121.199.29.37:22122"
											.split(":")[1])) }));
			trackerGroup = ClientGlobal.getG_tracker_group();
		}

		return trackerGroup;
	}*/
	
	private static TrackerGroup buildTrackerGroup() {
		lock.lock();
		try {
			if (trackerGroup == null) {
				FastDFSConfigure fdfs = ApplicationConfigureFactory
						.takeConfigure(FastDFSConfigure.class);
				ClientGlobal.setG_connect_timeout(fdfs.getConnect_timeout());
				ClientGlobal.setG_charset(fdfs.getCharset());
				ClientGlobal.setG_anti_steal_token(fdfs.isAnti_steal_token());
				ClientGlobal.setG_network_timeout(fdfs.getNetwork_timeout());
				ClientGlobal.setG_secret_key(fdfs.getSecret_key());
				ClientGlobal
						.setG_tracker_http_port(fdfs.getTracker_http_port());
				ClientGlobal.setG_tracker_group(new TrackerGroup(
						new InetSocketAddress[] { new InetSocketAddress(fdfs
								.takeTrackerIp(), fdfs.takeTrackerPort()) }));
				trackerGroup = ClientGlobal.getG_tracker_group();
			}
		} finally {
			lock.unlock();
		}

		return trackerGroup;
	}


	private static TrackerClient trackerClient() {
		return new TrackerClient(buildTrackerGroup());
	}
    /**
     *  上传文件，返回文件地址
      * @author Yang
      * @param inputStream
      * @param fileExtName
      * @return the file id(including group name and filename)
      *                <p/>极端异常情况下，返回null。
      * @throws FastDFSClientException
      *  
     */
	public static String uploadFile(InputStream inputStream, String suffix){
		
		TrackerClient trackerClient = trackerClient();
		TrackerServer trackerServer=null;
		StorageServer storageServer=null;
		String filePath =null;
		try {
			trackerServer = trackerClient.getConnection();
			storageServer = trackerClient
					.getStoreStorage(trackerServer);
			StorageClient storageClient = new StorageClient(
					trackerServer, storageServer);
			String parts[] = storageClient.upload_file(IOUtils.toByteArray(inputStream), suffix, null);
			
			if (parts != null)
			{
				filePath=parts[0] + StorageClient1.SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR + parts[1];
			}
			else
			{
				LogService.error(FastDFSClient.class, "FastDFS:Upload file to fastdfs server is failure.The group name is:"+suffix);
				throw new FastDFSClientException("FastDFS:Upload file to fastdfs server is failure.The group name is:"+suffix);
			}

		} catch (IOException | MyException e) {
			LogService.error(FastDFSClient.class, "FastDFS:upload file to fastdfs server is error.",e);
			throw new FastDFSClientException("FastDFS:upload file to fastdfs server is error.",e);
		}finally{

			if (storageServer != null) {
				try {
					storageServer.close();
				} catch (IOException e) {
					LogService.fatal(FastDFSClient.class, "Something is wrong when close storageServer",e);
				}
			}
			if (trackerServer != null) {
				try {
					trackerServer.close();
				} catch (IOException e) {
					LogService.fatal(FastDFSClient.class, "Something is wrong when close trackerServer",e);
				}
			}
		}
		
		return filePath;
	}

	/**
	 * Delete the file by id,which commonly is a string like url.	 
	 * @author Yang
	 * @param file_id the file id(including group name and filename)
	 * @return 0 for success, none zero for fail (error code)<p/>
	 *         -10000,This number does not been throw out.
	 * @throws FastDFSClientException        
	 * 
	 */
	public static Integer deleteByfileId(String file_id) {
		TrackerClient trackerClient = trackerClient();
		TrackerServer trackerServer=null;
		StorageServer storageServer=null;
		int resultCode=-10000; 
		try {
			trackerServer = trackerClient.getConnection();
			storageServer = trackerClient.getStoreStorage(trackerServer);
			StorageClient storageClient = new StorageClient(
					trackerServer, storageServer);
			String[] parts = new String[2];
			
			if (StorageClient1.split_file_id(file_id, parts) != 0)
			{
				throw new FastDFSClientException("FastDFS:There is no ERR_NO_EINVAL in the file name!");
			}
				
			resultCode = storageClient.delete_file(parts[0], parts[1]);
		

		} catch (IOException | MyException e) {
			throw new FastDFSClientException("FastDFS:Delete file from fastdfs server is error.",e);
		}finally{

			if (storageServer != null) {
				try {
					storageServer.close();
				} catch (IOException e) {
					LogService.fatal(FastDFSClient.class, "Something is wrong when close storageServer",e);
				}
			}
			if (trackerServer != null) {
				try {
					trackerServer.close();
				} catch (IOException e) {
					LogService.fatal(FastDFSClient.class, "Something is wrong when close trackerServer",e);
				}
			}
		}
	    return resultCode;

	}

}
