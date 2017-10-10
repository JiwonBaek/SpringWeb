package com.newlecture.webapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//System.out.println("헬로 필터");//서블릿 또는 이후 필터가 실행되기 전에 실행
		
		request.setCharacterEncoding("UTF-8");//ISO-8859-1로 변환된 것을 다시 UTF-8로 변환
		
		chain.doFilter(request, response);
		
		//System.out.println("잘가 필터");//서블릿 또는 이후 필터가 실행되고 후에 실행
		
	}

}
