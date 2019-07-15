package com.kitri.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gbwrite")
public class GuestBookWrite extends HttpServlet {
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
		Connection con =null;
		PreparedStatement pstm = null;
		request.setCharacterEncoding("utf-8");
//		1.data 얻기 작성자이름,제목,내용얻기
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		StringReader sr = new StringReader(content);
		int cnt = 0;
//		2.logic 처리
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl","kitri","kitri");
			
			StringBuffer sql = new StringBuffer();
			
//			INSERT INTO guestbook(seq,name,subject,content,logtime)
//			VALUES (?,?,?,?,sysdate);
			sql.append("INSERT INTO guestbook(seq,name,subject,content,logtime)");
			sql.append("VALUES (guestbook_seq.nextval,?,?,?,sysdate)");
			pstm = con.prepareStatement(sql.toString());
			
			pstm.setString(1, name);
			pstm.setString(2, subject);
			pstm.setCharacterStream(3, sr, content.length());
			cnt = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		3. 2를 이용한 reponse page
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("		<body>");
		if(cnt != 0) {
			out.println("방명록 등록완료<br>");
			out.println("<a href=\"/guestbookservlet/gblist\">리스트 보기</a>");
		}else {
			out.println("<font size=\"13\" color=\"red\">");
			out.println("서버에 이상이 있어 등록되지않았습니다.");
			out.println("</font>");
			out.println("<a href=\"/guestbookservlet/guestbook/write.html\">글쓰기</a>");
		}
		out.println("		</body>");
		out.println("</html>");
	}

}
