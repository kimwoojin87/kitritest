package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/counter")
public class counter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int cnt;
	@Override
	public void init() throws ServletException {
		cnt = 0;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		cnt++;
		//클라이언트가 올때마다 총자릿수 8자리
		String number = String.valueOf(cnt); 
		String number2 = "";
		for(int i=0;i<8-number.length();i++) {
			number2 += "0";
		}
		number = number2+number;
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("		<body>");
		out.println("당신은 ");
		for(int i=0;i<number.length();i++) {
			out.println("<img src = '/basicservlet/img/"+number.charAt(i)+".png'/ width='50' height='50'>");
		}
		out.println(" 번째 방문자 입니다.");
		out.println("		</body>");
		out.println("</html>");
	}
}
