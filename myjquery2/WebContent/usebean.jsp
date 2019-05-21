<%@page import="com.kitri.dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>usebean.jsp</title>
</head>
<body>
<%-- <%
//1.request의 속성(이름:"c" 타입:com.kitri.dto.Customer) 얻기
Customer c = (Customer)request.getAttribute("c");
//2. 1번으로 얻어온 속성이 null인 경우
//	customer객체생성하여 c잠조변수에 대입
// c참조변수를 request의 속성(이름:"c")로 추가.
if(c == null){
	c = new Customer();
	request.setAttribute("c", c);
}
%> --%>
<jsp:useBean id="c" class="com.kitri.dto.Customer" scope="request"/>
<%--
c.setId("id1");
--%>
<jsp:setProperty property="id" name="c" value="id1"/>
<%--
c.setName(request.getParameter("n"));
--%>
<jsp:setProperty property="name" name="c" param="n"/>
<%--
c.getId();
--%>
<jsp:getProperty property="id" name="c"/>
<%-- jsp:useBean 은 유연성이 떨어진다. --%>
</body>
</html>