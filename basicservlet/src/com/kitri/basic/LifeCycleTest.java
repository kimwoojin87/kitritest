package com.kitri.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/life")
public class LifeCycleTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public LifeCycleTest() {
//		1.서블릿은 일반적으로 생성자에서 작업하지않음.
		System.out.println("생성자() 호출!!!!!");
	}
	
	@Override
	public void init() throws ServletException {
//		2.서블릿을 초기화 해야되는 작업에서 생성
		System.out.println("init() 호출!!!!!!!");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		3.클라이언트가 호출할때마다 매번 실행되는 영역
//		WAS에서 쓰레드,소켓을 실행해주고있음.
		System.out.println("서비스 호출!!!!!!!!!");
	}
	
	@Override
	public void destroy() {
//		4.서버가 끝날떄 호출됨.
		System.out.println("destroy() 호출!!!!!");
	}
}
