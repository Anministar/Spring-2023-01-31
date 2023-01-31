package com.test.interceptor;

//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;


public class TestInterceptor implements HandlerInterceptor {

	// DispatcherServlet -> prehandle -> SubController
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("TestInterceptor - prehandle......"); 
		return false; // false로 바꾸면 DispatcherServlet에서 SubController로 진입이 안됨.
	}

	// DispatcherServlet <- posthandle <- SubController
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("TestInterceptor - posthandle........"); 
	
	}

	// view 파일 호출 이후에 실행
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("TestInterceptor - afterCompletion........."); 
	}

	
	
}
