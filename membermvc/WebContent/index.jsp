<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/header.jsp" %> 
<div align="center">
<h1>MVC 패턴을 이용한 회원가입 &amp; 로그인</h1>
<a href="<%=root%>/user?act=mvjoin">회원가입</a><br>
<a href="<%=root%>/user?act=mvlogin">로그인</a><br>
</div>
<%@ include file="/template/footer.jsp" %>