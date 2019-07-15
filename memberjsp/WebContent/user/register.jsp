<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*,java.net.URLEncoder" %>  
<%!
public void init(){
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}
%>
<%
	Connection con = null;
PreparedStatement pstm = null;

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

int cnt = 0;
try {
	con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl","kitri","kitri");
	StringBuffer sql = new StringBuffer();
	sql.append("insert all \n");
	sql.append("	into member(id,name,pass,emailid,emaildomain,joindate) \n");
	sql.append("	values(?,?,?,?,?,sysdate) \n");
	sql.append("	into member_detail(id,zipcode,address,address_detail,tel1,tel2,tel3) \n");
	sql.append("	values(?,?,?,?,?,?,?) \n");
	sql.append("select * from dual \n");
	pstm = con.prepareStatement(sql.toString());
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
	String root = request.getContextPath();
	if (cnt != 0) {
		response.sendRedirect(root+"/user/registerok.jsp?name="+URLEncoder.encode(name, "utf-8"));
		//*한글깨짐 : 직접적으로 쿼리스트링을 만들어 넘길때
		//	encode(String s, String enc) s=넘겨줄 문자열 enc = 인코더
	} else {
		response.sendRedirect(root+"/user/registerfail.jsp");
	}
%>
