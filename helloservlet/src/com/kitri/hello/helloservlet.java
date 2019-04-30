package com.kitri.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/hs") // 3.0 부터는 @webServlet으로 간단하게 url-pattern을 만든다.
public class helloservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		out.println();
		out.println("<html>");
		out.println("	<body>");
		out.println("	Hello Servlet!!! 3.0 <br>");
		out.println("	안녕 서블릿 3.0!!!");
		out.println("	</body>");
		out.println("</html>");
	}
}
