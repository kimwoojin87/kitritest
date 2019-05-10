package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class multiparapTest
 */
@WebServlet("/multiparam")
public class multiparametorTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.data get
		String name= request.getParameter("name");
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age"));
		
		String fruits[]=request.getParameterValues("fruits");
//		2.data logic
		String color = age==10 ? "pink":"cyan";
		String sysResult = "";
		
		if(fruits ==null) {
			sysResult += "없습니다.";
		} else {
			for (int i = 0; i < fruits.length; i++) {
				sysResult += fruits[i];
				if (i < fruits.length - 1) {
					sysResult += ", ";
				}
			}
			sysResult += "입니다.";
		}
//		3.response page 안효인(10대면 빨간색,20대면 파란색 id보이게함),안녕하세요.
//		당신이 좋아하는 과일을 선택하세요 사과 바나나 수박 딸기 추가 전송을 누르면
//		~~님 안녕하세요. 빨 파 똑같이 하고, 당신이 좋아하는 과일은 =사과 선택시 사과입니다.
//		사과 바나나 두개 선택하면 사과,바나나입니다.
//		4개 선택시 사과,바나나,수박,딸기 입니다.
//		모두 미선택시 없습니다.
//		,다음은 한칸띄기
//		단일 선택시 붙여넣기.
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("		<body>");
		out.println(name+"("+"<font color=\""+color+"\">"+id+"</font>"+")"+",안녕하세요."+"당신이 좋아하는 과일은 "+sysResult);
		out.println("		</body>");
		out.println("</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		doGet(request, response);
	}

}
