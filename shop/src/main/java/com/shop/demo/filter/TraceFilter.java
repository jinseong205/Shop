package com.shop.demo.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TraceFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// token 만들어줘야함. id,pw 정상적으로 들어와서 로그인이 완료 되면 토큰을 만들어주고 그것을 응답해준다.
		// 요청할 때 마다 header에 Authorization에 value 값으로 토큰을 가지고오면
		// 토큰이 내가 만든것인지 검증하면 됨
		log.debug("********** Filter Test : TraceFilter **********");
		log.debug("********** Request : " + req.getRequestURL() + " **********");
		log.debug("********** Request : " + req.getMethod() + " **********");
		
		if (req.getMethod().equals("POST")) {
			log.debug("********** Post Request **********");
			String headerAuth = req.getHeader("Authorization");
			
			if(headerAuth == null) {
				chain.doFilter(req, res);
				return;
			}
			log.debug("********** Header Auth : " + headerAuth + " **********");
			
			if(headerAuth.equals("jinseong")) {
				//chain.doFilter(req, res);
			}else {
				//PrintWriter out = res.getWriter();
				//out.println("unauthenticated token : " + headerAuth);
			}
		}
		
		chain.doFilter(req, res);
	}

	
}

