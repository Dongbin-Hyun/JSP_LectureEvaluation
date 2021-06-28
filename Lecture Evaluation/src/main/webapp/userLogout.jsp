<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%
	session.invalidate();//현재 세션의 정보 모두 파기
	
%>
<script>
	location.href = "index.jsp";
</script>
