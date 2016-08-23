/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */

package com.weheros.framework.core.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.weheros.framework.core.log.LogService;


/**
 * @ClassName: AbstractController
 * @author Administrator
 * @date 2013年11月26日 下午4:39:09
 */
public abstract class AbstractController
{
    protected Logger log = Logger.getLogger(getClass());
    
    @Autowired
    MessageSource messageSource;
    
    /**
     * 构建国际化的message
     * 
     * @author Yang
     * @param definition 代码定义 @see CodeDefinition
     * @param message
     * @param valuses
     * @param request
     * @return Message
     *         
     */
    public Message constructInternationalMessage(CodeDefinition definition, Message message, Object[] valuses,
        HttpServletRequest request)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        String internationalation = messageSource.getMessage(definition.getInternationalMessageCode(), valuses, locale);
        message.setCode(definition.getCode());
        message.setMsg(internationalation);
        return message;
    }
    
    public String getMsgByCodeDefinition(CodeDefinition definition, Object[] valuses, HttpServletRequest request)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        return messageSource.getMessage(definition.getInternationalMessageCode(), valuses, locale);
    }
    
    public String constructInternationalMessage(String key, Object[] valuses, HttpServletRequest request)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        String internationalation = messageSource.getMessage(key, valuses, locale);
        return internationalation;
    }
    /**
     * 这个输出json纯文本字符串，不是真正意义上的json协议结果。
     * @param response
     * @param cxt
     * @return
     */
    public String responseAjax(HttpServletResponse response, String cxt)
    {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter printwriter = null;
        try
        {
            printwriter = response.getWriter();
        }
        catch (IOException e)
        {
           LogService.error(this.getClass(),"response write content fail.", e);
        }
        printwriter.write(cxt);
        printwriter.flush();
        return null;
    }
    
    /**
     * 
     * 返回一个系统错误
     * 
     * @author jiegege
     * @param request
     * @return Message
     *         
     */
    public Message getServerMessageError(HttpServletRequest request)
    {
        return new Message(Message.VISIT_FAIL, null,
            getMsgByCodeDefinition(CodeDefinition.SERVER_EXCEPTION, null, request));
    }
    
  
}
