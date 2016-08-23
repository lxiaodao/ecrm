/**
 * 
 */
package com.billioncube.ecrm.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.billioncube.ecrm.organization.application.IOrganizationService;
import com.weheros.framework.core.front.AbstractController;
import com.weheros.framework.core.front.Message;

/**
 * @author beijingzhongshangjiaogongyinglianguanliyouxiangongsi
 *
 */
@Controller
//@RequestMapping("/login/*")
public class IndexController extends AbstractController {
	
	@Autowired
	@Qualifier("organizationService")
	IOrganizationService organizationService;
	
	@RequestMapping(value="index")	
	public String index(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		
		//model.addAttribute("message",new Message("200","Welcome to simplecontroller"));
		//登录成功后，获取当前用户信息
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	     //User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	     String name = auth.getName(); //get logged in username
	     com.billioncube.ecrm.organization.domain.User user=organizationService.findUser(name);	
	   
	     request.getSession(false).setAttribute("username", name);
	     request.getSession(false).setAttribute("fullname", user.getFullname());
	     request.getSession(false).setAttribute("companyId", user.getCompanyId());
	     request.getSession(false).setAttribute("companyName", user.getCompanyName());
	     
	     request.getSession(false).setAttribute("departmentId", user.getDepartmentId());
	     request.getSession(false).setAttribute("departmentName", user.getDepartmentName());
	     
	     request.getSession(false).setAttribute("positionId", user.getPositionId());
	     request.getSession(false).setAttribute("positionName", user.getPositionName());
		
		return "index";
	}

}
