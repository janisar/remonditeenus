<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href='<c:url value="/resources/bootstrap-3.3.4-dist/css/bootstrap.min.css"/>'>
	<link rel="stylesheet" href='<c:url value="/resources/css/style.css"/>'>
</head>
<body>
	<div id = "loginSector">
		<h2>Logige sisse</h2>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		<form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
			<input type='text' class="form-control" name='username'/><br>
			<input type ="password" class="form-control" name ="password"/><br>
			<input type="submit" class="btn btn-default" id = "loginButton" value="Logi sisse"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
	<script type="text/javascript" src='<c:url value = "/resources/js/jquery-2.1.3.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value = "/resources/bootstrap-3.3.4-dist/js/bootstrap.min.js"/>'></script>
</body>
</html>
