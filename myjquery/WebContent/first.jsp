<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.text.SimpleDateFormat" import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first.jsp</title>
</head>
<body>
첫번째 JSP입니다.
<%int i; //scriptlet:_jspService()내부에 작성될 구문
	i=99;
%>
<%//expression : _jspService()내부에 작성될 구문
  //			out.print()자동호출됨.
%>
<%=i%>

<%//declaration : _jspService()외부에 작성될 구문
  //					인스턴스변수임%>
<%! int i;%>
<hr>
<%=i%>, this.i=<%=this.i%>
<hr>
<h3>지시자</h3>
<ul>
	<li>page directive
		<%Date dt = new Date();
		  String pattern = "yyyy-MM-dd";
		  SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		  sdf.format(dt);
		%>
		<%=sdf.format(dt)%>
	</li>
	<li>include directive : 정적포함(.java파일에 포함), 속성-file</li>
	<li>taglib directive</li>
</ul>
<hr>
<h3>ACTION TAG</h3>
<ul>
	<li>STANDARD Action Tag
		<ol>
			<li>JSP:include **중요. 여러view를 합쳐서 응답해야할때 사용. 동적포함(실행결과에 포함). 속성-page.</li>
			<li>JSP:forward</li>
			<li>JSP:param</li>
			<li>JSP:useBean</li>
			<li>JSP:setProperty</li>
			<li>JSP:getProperty</li>
		</ol>
	</li>
	<li>CUSTOM Action Tag</li>
</ul>
</body>
</html>