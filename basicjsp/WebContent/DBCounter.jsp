<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, java.io.*"%>
    
<%!
int cnt;

public void init(){
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e1) {
		e1.printStackTrace();
	}
}
%>
<%
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
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DBCounter 디비카운터.</title>
</head>
<body>
당신은
<%for(int i=0;i<number.length();i++) { %>
<img src = '/basicjsp/img/<%= number.charAt(i)%>.png' width='50' height='50'>
<% } %> 번째 방문자 입니다.
</body>
</html>