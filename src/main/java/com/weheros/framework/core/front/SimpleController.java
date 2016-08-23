/**
 * Copyright (c) 2011-2013 iTel Technology Inc,All Rights Reserved.
 */
	
package com.weheros.framework.core.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.weheros.framework.core.infrastructure.configure.InitializeApplicationDataAndParameters;


/**
 * @ClassName: SimpleController
 * 展示了怎样兼顾application/json,application/xml,jsp请求
 * <p>
 * @author Yang
 * @date 2013年11月6日 下午3:56:50
 */
@Controller
@RequestMapping("/simple/*")
public class SimpleController extends AbstractController {

	  public @ResponseBody Message uploadFile(HttpServletRequest request,
				HttpServletResponse response,@RequestParam(value="name", required=false, defaultValue="World") String name) {
		  RequestContextUtils.getLocale(request);

	    	return new Message("200","upload success.");
	}
	/**
	 *  能够接受JSON,XML,JSP请求
	 *  Could return json,xml,jsp response.
	  * @author Administrator
	  * @param model
	  * @return 
	  * String
	  *
	 */
	@RequestMapping(value="saywel",method = RequestMethod.GET)	
	public String welcome2(ModelMap model){
		
		model.addAttribute("message",new Message("200","Welcome to simplecontroller"));
		
		return "content";
	}
	/** Json POST
	 *  Only return json response.
	  * @author Administrator
	  * @param newMessage
	  * @param model
	  * @return 
	  * Message
	  *
	 */
	@RequestMapping(value="saveMessage",method = RequestMethod.POST)
	@ResponseBody
	public Message recieveJson(@RequestBody Message newMessage, ModelMap model){
		log.info("---------post the message-----------"+newMessage.getMsg());
		return new Message("9999","recieveJson is success.");
		
	}
	
	@RequestMapping(value="postByParam",method = RequestMethod.POST)	
	public String postByParam(@RequestBody Message newMessage,@RequestParam("jsonString") String ajson, ModelMap model){
		log.info("---------from requestParam-----------"+ajson);
		log.info("---------from requestBody-----------"+newMessage.getMsg());
		//return new Message("99990","recieveJson is success.");
		model.addAttribute("message",new Message("99990","recieveJson to simplecontroller"));
		return "content";
	}
	
	@RequestMapping(value="getme",method = RequestMethod.GET)
	@ResponseBody
	public Message getJson( ModelMap model){		
		return new Message("8888","recieveJson is success.");
		
	}
	
	
	
}
