/**
 * Copyright HZCW (He Zhong Chuang Wei) Technologies Co.,Ltd. 2013-2015. All rights reserved.
 */

package com.weheros.framework.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;



/**
 * Load the properties file.
 * 
 * @ClassName: PropertiesService
 * @author Yang
 * @date 2014年5月7日 下午10:10:02
 */
public class PropertiesService
{
	private static final Logger LOG = Logger.getLogger(PropertiesService.class);
    public final static String PROPS_NAME_APP = "app";
    
    private static Properties appProps = new Properties();
    
    private final static String PROPS_PATH_APP = "infrastructure/app.properties";
    
    static
    {
        try
        {
            InputStream is = PropertiesService.class.getClassLoader().getResourceAsStream(PROPS_PATH_APP);
            appProps.load(is);
        }
        catch (IOException e)
        {
            
            LOG.error("---load the properties " + PROPS_PATH_APP + " fiel fail.---");
        }
    }
    
    public static String takeValueOfKey(String key, String fileName)
    {
        return appProps.getProperty(key);
    }
    
    public static Properties takeProperties(String fileName)
    {
        return appProps;
    }
    
}
