<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.* , java.net.URLEncoder"%>
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
ResultSet rs = null;

request.setCharacterEncoding("utf-8");
String name = null;
String id = request.getParameter("id");
String pass = request.getParameter("pass");
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
String root = request.getContextPath();

	if (name != null) {
		response.sendRedirect(root+"/user/loginok.jsp?name="+URLEncoder.encode(name, "utf-8"));
	} else {
		response.sendRedirect(root+"/user/loginfail.jsp");
	}
%>
