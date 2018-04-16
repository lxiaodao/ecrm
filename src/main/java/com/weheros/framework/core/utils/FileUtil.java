package com.weheros.framework.core.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

/**
 * 
* @ClassName: FileUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xujie
* @date 2016年4月21日 下午2:10:16 
*
 */
public class FileUtil
{
    protected static Logger LOG = Logger.getLogger(FileUtil.class);
    
    /**
     * 
    * @Title: readFile 
    * @Description: 文件字符串读取（小文件如秘钥文件） 
    * @param @param path    设定文件 
    * @return void    返回类型 
    * @throws
     */
    @SuppressWarnings("resource")
    public static String readFile(String path, String charSet)
    {
        InputStream is = null;
        try
        {
            StringBuffer build = new StringBuffer();
            is = new FileInputStream(path);
            byte[] buffer = new byte[100];
            int count = 0;
            while ((count = is.read(buffer, 0, buffer.length)) != -1)
            {
                String str = new String(buffer, 0, count, charSet);
                build.append(str);
            }
            return build.toString();
        }
        catch (FileNotFoundException e)
        {
            LOG.error("readFile >>>>", e);
        }
        catch (IOException e)
        {
            LOG.error("readFile >>>>", e);
        }
        return null;
    }
}
