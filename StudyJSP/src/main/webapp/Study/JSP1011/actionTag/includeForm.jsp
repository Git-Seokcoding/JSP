<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>includeForm.jsp</h1>
	<hr>
<%-- 	<% pageContext.include("includePro.jsp"); %> --%>
<%-- 	<jsp:include page="includePro.jsp"></jsp:include> --%>
<%-- 	<jsp:include page="includePro.jsp"> --%>
<%-- 		<jsp:param value="Parameter Value" name="paramValue"/> --%>
<%-- 	</jsp:include> --%>
	<%@ include file="includePro.jsp" %>

</body>
</html>