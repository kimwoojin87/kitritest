package com.kitri.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//패키지 오른쪽 마우스클릭 new servlet만들기를 하고 doGet,doPost하면 자동으로 만들어짐. 
//코드가 실행되는 시점 : SERVER
public class helloservlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public helloservlet2() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); //코드상에서 한글이 꺠질떄 사용해야함.
		PrintWriter out = response.getWriter();				//getWriter()사용 전에 지정해줘야함.
		out.println();
		out.println("<html>");
		out.println("	<body>");
		out.println("	Hello Servlet!!! <br>");
		out.println("	안녕 서블릿!!!");
		out.println("	</body>");
		out.println("</html>");
//		HTML이 실행되는 곳 : 클라이언트
//		IO의 최종점이 되는 곳 : NODE	
	}
}
