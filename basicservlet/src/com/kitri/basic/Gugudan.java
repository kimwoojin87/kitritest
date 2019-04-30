package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class gugudan
 */
@WebServlet("/ggd")
public class Gugudan extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    String bgColor="";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		out.println("<table align=\"center\" border=\"2\">");
		for(int i=1;i<10;i++) {
			out.println("<tr>");
			for(int j=2;j<10;j++) {
				if(j%2==0) {
					bgColor ="tomato";
				}else {
					bgColor ="steelblue";
				}
				out.print("<td bgcolor=\""+bgColor+"\">"+j+"X"+i+"="+j*i+"</td>");
			}
		}
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}
}
