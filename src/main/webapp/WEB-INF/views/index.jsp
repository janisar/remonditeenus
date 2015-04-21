<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href='<c:url value="/resources/bootstrap-3.3.4-dist/css/bootstrap.min.css"/>'>
	<link rel="stylesheet" href='<c:url value="/resources/css/style.css"/>'>
</head>
<body>
	<div id = "wrapper">
		<jsp:include page="partials/header.jsp"></jsp:include>
		<div id = "container">
			<form action = "repair" method = "GET">
				<input type="submit" class="btn btn-primary btn-lg repair-btn" value = "Seadme parandus"/>
			</form>
		</div>
	</div>
	<script type="text/javascript" src='<c:url value = "/resources/js/jquery-2.1.3.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value = "/resources/js/common.js"/>'></script>
	<script type="text/javascript" src='<c:url value = "/resources/bootstrap-3.3.4-dist/js/bootstrap.min.js"/>'></script>
</body>
</html>
