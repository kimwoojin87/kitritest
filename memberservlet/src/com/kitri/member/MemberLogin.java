package com.kitri.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberLogin
 */
@WebServlet("/login")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		request.setCharacterEncoding("utf-8");
//		1.data get (아이디와 비밀번호 가져오기)
		String name = null;
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
//		2.logic 처리 (html에서 가져온 아이디와 비밀번호를 db와 비교함.)
//		select name
//		from member
//		where id = ? and pass = ?
		
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl","kitri","kitri");
			StringBuffer sql = new StringBuffer();
			sql.append("select name \n");
			sql.append("from member \n");
			sql.append("where id = ? and pass = ? \n");
			pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, id);
			pstm.setString(2, pass);
			rs = pstm.executeQuery();
			if(rs.next()) {
				name = rs.getString("name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if(pstm !=null) {
					pstm.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
//		3.response 페이지(아이디와 비밀번호가 맞는지 맞지않는지.) 2의 결과에 따라
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("		<body>");
		if(name != null) {
//		홍길동님 안녕하세요. 맞다면... name != null : 홍길동님 안녕하세요
			out.println("<strong>"+name+"</strong>님 안녕하세요.<br>");
		}else {
//		아이디 또는 비밀번호를 다시 확인하세요. 맞지않다면... name == null
//		등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못입력하셨습니다.
			out.println("<font size=\"13\" color=\"red\">");
			out.println("아이디 또는 비밀번호를 다시 입력해주세요.<br>");
			out.println("로그인 후 모든 서비스를 이용할 수 있습니다.<br>");
			out.println("<a href=\"/memberservlet/user/login.html\">로그인</a>");
			out.println("</font>");
		}
		out.println("		</body>");
		out.println("</html>");
		
	}



}
