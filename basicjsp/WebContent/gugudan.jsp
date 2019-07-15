<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
String bgcolor="";
String tr="<tr>";
String td="<td>";
String trr="</tr>";
String tdr="</tr>";
String bgColor="";
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 jsp</title>
</head>
<body>
<div align="center">
<h3>*** 구구단 *** out.println</h3>
<table align="center" border="1" width="800" height="700">
<% 
for(int i=1;i<10;i++) {
	out.println("<tr>");
	for(int j=2;j<10;j++) {
		if(j%2==0) {
			bgColor ="tomato";
		}else {
			bgColor ="steelblue";
		}
		out.print("<td bgcolor=\""+bgColor+"\">"+j+"X"+i+"="+j*i+"</td>");
	}
	out.println("</td>");
}
%>
</table>
<h3>*** 구구단 *** 섞어서</h3>
<table align="center" border="1" width="800" height="700" border-radius=10px;>
<%
for(int i=1;i<10;i++) {
%>
<tr>
<%
	for (int j = 2; j < 10; j++) {
			if (j % 2 == 0) {
				bgColor = "tomato";
			} else {
				bgColor = "steelblue";
			}
%>
<td bgcolor=<%=bgColor%>><%=j%>X<%=i%> = <%=j * i%>  </td>

<%		}%>
</tr>
<% 	}
%>
</table>
<h3>*** 구구단 *** 출력태그만</h3>
<table align="center" border="1" width="800" height="700">
<%
for(int i=1;i<10;i++) {;
%>
<tr>
<%
	for (int j = 2; j < 10; j++) {
			if (j % 2 == 0) {
				bgColor = "tomato";
			} else {
				bgColor = "steelblue";
			}
%>

<td bgcolor=<%=bgColor%>><%=j %>X<%= i%>= <%=j * i%>  </td>

<%		}%>
</tr>
<% 	}
%>
</table>
</div>
</body>
</html>