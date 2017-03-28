package com.weheros.framework.core.security.csrf;

import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 配置csrf白名单，不需要检查csrf参数
 * TODO:使用正则表达式　
 * @author beijingzhongshangjiaogongyinglianguanliyouxiangongsi
 *
 */
public class CsrfSecurityRequestMatcher implements RequestMatcher {
   // private Pattern allowedMethods = Pattern.compile("^(login|index.jsp|''|/)$");
   // private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("/ext/**", null);
    private final HashSet<String> allowedMethods = new HashSet<String>(
			Arrays.asList("GET", "HEAD", "TRACE", "OPTIONS"));

    @Override
    public boolean matches(HttpServletRequest request) {          
        String servletpath=request.getServletPath();
      
    	if(servletpath.indexOf("login")!=-1){
    		return false;
    	}
    	
    	//String value=request.getParameter("_csrf");
        return !this.allowedMethods.contains(request.getMethod());
    }

	
}
