<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Include.jsp</title>
</head>
<body>
<h3>포함지시자</h3>
<%@include file="loginresult.jsp" %>
<hr>
<h3>태그를 이용한 포함</h3>
<jsp:include page="loginresult.jsp"/>
</body>
</html>