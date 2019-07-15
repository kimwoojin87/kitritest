<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String name= request.getParameter("name");
String id = request.getParameter("id");
int age = Integer.parseInt(request.getParameter("age"));

String fruits[]=request.getParameterValues("fruits");
//2.data logic
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
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=name+"("%><font color="<%=color %>"><%= id%></font>),안녕하세요.당신이 좋아하는 과일은 <%= sysResult%>
</body>
</html>