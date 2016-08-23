package com.weheros.framework.core.security.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.weheros.framework.core.front.AbstractController;
import com.weheros.framework.core.front.Message;
import com.weheros.framework.core.utils.ToJson;

@Component("accessSecurityFilterProxy")
public class AccessSecurityFilterProxy implements Filter,BeanFactoryAware {
	protected Logger log = Logger.getLogger(getClass());
	private BeanFactory beanFactory2=null;
     
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		
		beanFactory2=beanFactory;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		//Is the url can be visit?
		//Whethe need logined user? Must has special authority (role)?
		this.log.info("--------------------------this is whole security filter--------------------------");
		
		//
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
	
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		//
	}

}
