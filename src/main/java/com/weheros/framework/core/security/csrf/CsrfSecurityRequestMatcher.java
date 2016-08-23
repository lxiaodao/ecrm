package com.weheros.framework.core.security.csrf;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 培训csrf白名单，不需要检查csrf参数
 * TODO:使用正则表达式　
 * @author beijingzhongshangjiaogongyinglianguanliyouxiangongsi
 *
 */
public class CsrfSecurityRequestMatcher implements RequestMatcher {
    private Pattern allowedMethods = Pattern.compile("^(login|index.jsp|''|/)$");
   // private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("/ext/**", null);

    @Override
    public boolean matches(HttpServletRequest request) {          
        String servletpath=request.getServletPath();
    	if(servletpath.equals("")||servletpath.equals("/\\")){
    		return false;
    	}
    	if(servletpath.indexOf("login")!=-1||servletpath.indexOf("/accessDenied.jsp")!=-1||servletpath.indexOf("index.do")!=-1){
    		return false;
    	}
        return true;
    }

	
}
