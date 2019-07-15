<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
//1.data get
String name= request.getParameter("name");
String id = request.getParameter("id");
int age = Integer.parseInt(request.getParameter("age"));
//2.data logic
String color = age==10 ? "pink":"cyan";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=name+"님("%><font color="<%= color%>"><%=id%></font>),안녕하세요.
</body>
</html>