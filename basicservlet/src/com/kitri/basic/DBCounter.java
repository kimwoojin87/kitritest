package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DBCounter")
public class DBCounter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int cnt;

	@Override
	public void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");

			String sql = "select * from counter";
			stmt = con.createStatement();
			rs =  stmt.executeQuery(sql);
			rs.next();
			cnt = rs.getInt(1);
			
			sql = "update counter \n";
			sql += "set no = no + 1 \n";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if(rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
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
