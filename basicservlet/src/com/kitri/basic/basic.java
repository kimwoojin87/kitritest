package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/basic")
public class basic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	String name;
	int age;
	
	
	@Override
	public void init() throws ServletException {
		name = "김우진";
		age = 20;
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("		<body>");
		String fontColor="";
		if(age >= 18) {
			fontColor="steelblue";
		}else {
			fontColor="tomato";
		}
		out.println("<font color=\""+fontColor+"\">" + name + "(" + age + ")님 안녕하세요</font>");
		out.println("		</body>");
		out.println("</html>");
	}

}
