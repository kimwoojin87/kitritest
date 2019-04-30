package com.kitri.hello;

import java.io.IOException;

import javax.servlet.*;

//1. 이 클래스는 웹에서 돌아가는 클래스라는 걸 알려줘야함.
public class helloservlet implements Servlet{

	@Override
	public void destroy() {
//		프로그램이 종료될떄 뭐 할 거냐
	}

	@Override
	public ServletConfig getServletConfig() {
//		서블릿의 환경설정을 읽어올수있는 메쏘드?
		return null;
	}

	@Override
	public String getServletInfo() {
		
		return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
//		굳이 자바와 비교하자면 생성자
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//		req를 갖고 데이터를 받아라 res를 갖고 응답해라
	}

}





//server+network = servlet