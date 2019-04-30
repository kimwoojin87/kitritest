package com.kitri.hello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
// 가장 일반적인 서블릿
// servlet은 public이 아니면 사용못함.
// 반드시 http서블릿을 상속받아야함.
// 반드시 doGet이나, doPost 메쏘드를 오버라이드 해야함.
public class httpservlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
//		되도록이면 request,response 는 약어 사용하지말고 풀네임으로 써라.
		super.doGet(request, response);
	}

}


/*
page 이동방식
1. 주소에 직접입력
	-get
2. link를 통해 이동
	-get
3. form을 통해 이동
	-get
----------------------	
	-post
form에 post를 선언하지않으면 모든 방식은 get방식임.
*/