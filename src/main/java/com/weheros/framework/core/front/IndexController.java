/**
 * 
 */
package com.weheros.framework.core.front;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * @author beijingzhongshangjiaogongyinglianguanliyouxiangongsi
 *
 */
@Controller

public class IndexController extends AbstractController {
	private static final String DEFAULT_HDSIGHT_CSRF_NAME="_csrf";

	@RequestMapping("/index")	
	public String index(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		
		//model.addAttribute("message",new Message("200","Welcome to simplecontroller"));
		//登录成功后，获取当前用户信息
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	     User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	     String name = auth.getName(); //get logged in username
	     request.getSession(false).setAttribute("username", name);
	     /*com.billioncube.ecrm.organization.domain.User user=organizationService.findUser(name);	
	   
	     request.getSession(false).setAttribute("username", name);
	     request.getSession(false).setAttribute("userid", user.getId());
	     request.getSession(false).setAttribute("fullname", user.getFullname());
	     request.getSession(false).setAttribute("companyId", user.getCompanyId());
	     request.getSession(false).setAttribute("companyName", user.getCompanyName());
	     
	     request.getSession(false).setAttribute("departmentId", user.getDepartmentId());
	     request.getSession(false).setAttribute("departmentName", user.getDepartmentName());
	     
	     request.getSession(false).setAttribute("positionId", user.getPositionId());
	     request.getSession(false).setAttribute("positionName", user.getPositionName());*/
	     
	     //csrf token
	   
		//setCsrfCookie(request,response);
		setCsrfHeader(request,response);
		return "index";
	}
   private void setCsrfHeader(HttpServletRequest request,
			HttpServletResponse response) {
	   CsrfToken csrfToken =(CsrfToken) request.getAttribute(DEFAULT_HDSIGHT_CSRF_NAME);
	   response.setHeader(DEFAULT_HDSIGHT_CSRF_NAME, csrfToken.getToken());
	}

	private void setCsrfCookie(HttpServletRequest request,
			HttpServletResponse response) {
		CsrfToken csrfToken =(CsrfToken) request.getAttribute(DEFAULT_HDSIGHT_CSRF_NAME);
		Cookie cookie = new Cookie(DEFAULT_HDSIGHT_CSRF_NAME, csrfToken.getToken());
		cookie.setSecure(request.isSecure());
		cookie.setPath(getCookiePath(request));
		if (csrfToken == null) {
			cookie.setMaxAge(0);
		}
		else {
			cookie.setMaxAge(-1);
		}
	

		response.addCookie(cookie);
		
		
		
	}
	private String getCookiePath(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		return contextPath.length() > 0 ? contextPath : "/";
	}
	

}
