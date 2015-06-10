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
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<meta name="_csrf_parameter" content="${_csrf.parameterName}"/>
</head>
<body>
	<div id = "wrapper">
		<jsp:include page="partials/header.jsp"></jsp:include>
		<div id = "container">
			<form action ="addNewDevice" method="GET" id = "newDeviceSubmitForm">
<!--                 <h3>Vali seade: </h3> -->
<%-- 				<select id = "device-selecter" class = "form-control" name ="deviceId"> --%>
<%-- 					<c:forEach items="${devices }" var = "device"> --%>
<%-- 						<option value="${device.deviceId}">${device.name}</option> --%>
<%-- 					</c:forEach> --%>
<!-- 					<option value="-1">Muu</option> -->
<%-- 				</select> --%>
<!--                 <h4>Nende seadmetega on meil k�ige rohkem kokkupuuteid ning neid me parandame kiirelt,  -->
<!--                 kui soovid uut seadet parandusse tuua, siis oma seadme saad lisada meie s�steemi <a href ="#" id = "addNewDevice">SIIT</a></h4> -->
                
				<input type="hidden" id ="csrf" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <h3>Mis seadmel viga on?</h3>
                <textarea id ="desc" class="form-control" rows="2" cols=""></textarea><br>
                <h4>Sisesta seade ning Sinu arvamus, mis seadmel viga on.</h4>
			</form>
<!-- 			<button id = "add_device_to_order" type="button" class="btn btn-default" aria-label="Left Align"> -->
<%-- 				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> --%>
<!-- 				Lisa toode tellimusele -->
<!-- 			</button> -->
            <p>
<!-- 			<table class = "table" id = "current_devices"> -->
<!-- 				<tr> -->
<!-- 					<th>Id</th> -->
<!-- 					<th>Nimi</th> -->
<!-- 					<th>Mudel</th> -->
<!-- 					<th>Tootja</th> -->
<%-- 					<th>${serviceOrderBean.serviceOrder.serviceDevices}</th> --%>
<!-- 				</tr> -->
				
<%-- 				<c:forEach items="${serviceOrderBean.devices }" var="sdevice"> --%>
<!-- 					<tr> -->
<%-- 						<td>${sdevice.device.deviceId }</td> --%>
<%-- 						<td>${sdevice.device.name }</td> --%>
<%-- 						<td>${sdevice.device.model }</td> --%>
<%-- 						<td>${sdevice.device.manufacturer }</td> --%>
<!-- 					</tr> -->
<%-- 				</c:forEach> --%>
<!-- 			</table> -->
						
            <button class = "btn btn-primary" id = "postOrder" type = "button">Saada seadmed remonti</button>
				<div id = "newDeviceForm">
				<div class="modal-header">
	        		<h4 class="modal-title">Uue seadme lisamine</h4>
	      		</div>
	      		<div class="modal-body">
	      			<form action="saveDevice" method = "POST" id = "saveDevice" modelAttribute="newDevice">
	      				<div class = "formInput">
		      				<label for ="name">Seadme nimi: </label>
				      		<input type = "text" class="form-control" name = "name" id = "name"/><br>
	      				</div>
	      				<div class = "formInput">
		      				<label for ="regNr">Registreerimisnumber: </label>
				      		<input type = "text" class="form-control" name = "registrationNumber" id = "regNr"/><br>
	      				</div>
	      				<div class = "formInput">
				      		<label for ="model">Seadme mudel: </label>
				      		<input type = "text" class = "form-control" name = "model" id = "model"/><br>
	      				</div>
	      				<div class = "formInput">
				      		<label for ="manufacturer">Seadme tootja: </label>
				      		<input type = "text" class = "form-control" name = "manufacturer" id = "manufacturer"/><br>
	      				</div>
	      				<div class = "formInput">
				      		<label for ="description">Seadme kirjeldus: </label>
				      		<input type = "text" class = "form-control" name = "description" id = "description"/><br>
	      				</div>
			      		<div class = "formInput">
				      		<label for ="type">Seadme t��p: </label>
				      		<select class="form-control" id = "type" name ="deviceId">
				      			<c:forEach var="deviceType" items="${deviceTypes}">
				      				<option value ="${deviceType.id }">${deviceType.name}</option>
				      			</c:forEach>
				      		</select>
	      				</div>
                        <input type="hidden" id ="csrf" name="${_csrf.parameterName}" value="${_csrf.token}" />
	      				<button class = "btn btn-primary" id = "saveNewDevice">Lisa seade</button>
	      			</form>
	      		</div>
			</div>
            
		</div>
	</div>

	<script type="text/javascript" src='<c:url value = "/resources/js/jquery-2.1.3.min.js"/>'></script>
	<script type="text/javascript" src='<c:url value = "/resources/js/common.js"/>'></script>
	<script type="text/javascript" src='<c:url value = "/resources/js/repair.js"/>'></script>
	<script type="text/javascript" src='<c:url value = "/resources/js/callHelper.js"/>'></script>
	<script type="text/javascript" src='<c:url value = "/resources/bootstrap-3.3.4-dist/js/bootstrap.min.js"/>'></script>
	<script>
		repairDeviceSelector.initField();
		postOrder.initField();
	</script>
</body>
</html>