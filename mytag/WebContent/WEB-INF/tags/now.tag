<%@tag import="java.util.Date"%><%--태그전용지시자. --%>
<%@tag import="java.text.SimpleDateFormat"%>
<%@ tag body-content="empty" pageEncoding="UTF-8"%><%--바디가 없는 태그 --%>
<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>
<!-- jsp2.0이상의 커스텀태그 -->