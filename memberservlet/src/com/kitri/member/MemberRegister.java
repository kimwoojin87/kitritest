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


@WebServlet("/register")
public class MemberRegister extends HttpServlet {
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
		
		//		1.data get (이름,아이디,이메일,이메일(2),전화번호1,전화번호2,전화번호3,우편번호,주소,상세주소)
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String emailid = request.getParameter("emailid");
		String emaildomain = request.getParameter("emaildomain");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String zipcode = request.getParameter("zipcode");
		String address = request.getParameter("address");
		String addressdetail = request.getParameter("address_detail");
		
//		sql += "(values(yaho,김우진,123123,asdasd,gmail.com,sysdate))";
//		sql += "(values(yaho,123-123,서울시 구로구,구로밥동,02,123,4567)"
//		2.logic 처리 (1의 데이터를 DB에 인서트)
		
		int cnt = 0;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl","kitri","kitri");
//			String sql = "insert all "
//					+ "into member(id,name,pass,emailid,emaildomain,joindate)"
//					+ "values(?,?,?,?,?,sysdate)"
//					+ "into member_det(id,zipcode,address,address_detail,tel1,tel2,tel3)"
//					+ "values(?,?,?,?,?,?,?)"
//					+ "select * from dual";
			
			StringBuffer sql = new StringBuffer();
			sql.append("insert all \n");
			sql.append("	into member(id,name,pass,emailid,emaildomain,joindate) \n");
			sql.append("	values(?,?,?,?,?,sysdate) \n");
			sql.append("	into member_detail(id,zipcode,address,address_detail,tel1,tel2,tel3) \n");
			sql.append("	values(?,?,?,?,?,?,?) \n");
			sql.append("select * from dual \n");
			pstm = con.prepareStatement(sql.toString()); // sql 문장 가져가고
			int idx = 0;
			pstm.setString(++idx, id);
			pstm.setString(++idx, name);
			pstm.setString(++idx, pass);
			pstm.setString(++idx, emailid);
			pstm.setString(++idx, emaildomain);
			pstm.setString(++idx, id);
			pstm.setString(++idx, zipcode);
			pstm.setString(++idx, address);
			pstm.setString(++idx, addressdetail);
			pstm.setString(++idx, tel1);
			pstm.setString(++idx, tel2);
			pstm.setString(++idx, tel3);
			cnt = pstm.executeUpdate(); // sql 문장 가져가지않음.
			
			
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
		
//		3.response page : 2의 결과에 따라.
//		3-1 !0 : 홍길동님 회원가입을 환영합니다.
//		3-2 0 : 서버 문제로 회원가입이 실패하였습니다. 
//				다음에 다시 시도하세요.
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("		<body>");
		if(cnt !=0) {
			out.println(name+"님 회원가입을 환영합니다.<br>");
			out.println("로그인 후 모든 서비스를 이용할 수 있습니다.<br>");
			out.println("<a href=\"/memberservlet/user/login.html\">로그인</a>");
		}else {
			out.println("<font size=\"13\" color=\"red\">");
			out.println("서버 문제로 회원가입이 실패하였습니다.");
			out.println("다음에 다시 시도해주세요 ^^;");
			out.println("</font>");
		}
		
		out.println("		</body>");
		out.println("</html>");
		
	}



}
