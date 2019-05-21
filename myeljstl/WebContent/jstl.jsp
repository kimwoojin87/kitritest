<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl.jsp</title>
</head>
<body>
<c:set var="num" value="${param.num}"/><%-- int num=99; --%>
요청전달데이터 num=${num}<br>
<c:if test="${num%2==0}"><%--if(){}--%>
짝수입니다.<br>
</c:if>
<hr>
<c:choose>
	<c:when test="${num%2==0}">
		짝수입니다.
	</c:when>
	<c:otherwise>
		홀수입니다.
	</c:otherwise>
</c:choose>
<hr>
<c:forEach begin="1" end="10" step="1" var="i"><%--for(int i=1;i<=10;i++){}--%>
${i}
</c:forEach>
<c:set var="total" value="0"/>
<c:forEach begin="1" end="10" var="i">
	<c:set var="total" value="${total+i }"/>
</c:forEach>
<hr>
1~10의 합 : ${total}
<hr>
<%
List<String>list = new ArrayList();
list.add("one");list.add("two");list.add("three");
request.setAttribute("list", list);
%>
<c:forEach var="e" items="${requestScope.list}"><%--for (String e:request.getAttribute("list")){}--%>
${e}<br>
</c:forEach>
<hr>
<c:forEach var="e" items="${requestScope.list}" varStatus="Obj">
${Obj.index} - ${e} <br>
</c:forEach>
<c:forEach var="e" items="${requestScope.list}" varStatus="Obj">
${Obj.index} - ${e} : ${obj.coint}회 <br>
</c:forEach>
</body>
</html>