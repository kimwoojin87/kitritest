<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/template/header.jsp"></jsp:include>
<!--include page는 화면만 뿌려주는 것이고,
파일자체를 가져오는 include file은 변수도 쓸수 있다.하지만 중복선언은 불가능하다.  -->
<%@ include file="/template/header.jsp" %>
<%=name %>사진이나와요!!!
<%@ include file="/template/footer.jsp" %>
</body>
</html>