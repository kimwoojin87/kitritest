<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%! 
int cnt;

public void init(){
	cnt = 0;
}
%>

<%
cnt++;
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
<title>Insert title here</title>
</head>
<body>
당신은
<%for(int i=0;i<number.length();i++) { %>
<img src = '/basicjsp/img/<%= number.charAt(i)%>.png'/ width='50' height='50'>
<% } %> 번째 방문자 입니다.
<!-- jsp는 자동적으로 html로 바뀌기 떄문에 공백도 생각해야한다.
자바에선 문제가 없던 중괄호없는 1줄 for문이라도
jsp에선 써주는것이좋다. -->
</body>
</html>