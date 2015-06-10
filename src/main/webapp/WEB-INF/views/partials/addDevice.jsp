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
    <meta charset = "UTF8"/>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta name="requestId" content="${requestId}"/>
</head>
<body>

    <div id = "search-wrapper">
                <div>
                    <c:forEach items = "${errors}" var = "error"> 
                        <p>${error.defaultMessage}
                    </c:forEach>
                </div>
        <form:form action="saveDevice" method = "POST" id = "saveDevice" modelAttribute="newDevice"  commandName="newDevice">
                <div class = "formInput">
                            <label for ="name">Seadme nimi: </label>
                            <input type = "text" class="form-control" name = "name" id = "name" value = "${device.name}"/><br>
                        </div>
                        <div class = "formInput">
                            <label for ="regNr">Registreerimisnumber: </label>
                            <input type = "text" class="form-control" name = "registrationNumber" id = "regNr" value = "${device.registrationNumber}"/><br>
                        </div>
                        <div class = "formInput">
                            <label for ="model">Seadme mudel: </label>
                            <input type = "text" class = "form-control" name = "model" id = "model" value = "${device.model}"/><br>
                        </div>
                        <div class = "formInput">
                            <label for ="manufacturer">Seadme tootja: </label>
                            
                            <input type = "text" class = "form-control" name = "manufacturer" id = "manufacturer" value = "${device.manufacturer}"/><br>
                        </div>
                        <div class = "formInput">
                            <label for ="text">Seadme kirjeldus: </label>
                            <input type = "text" class = "form-control" name = "description" id = "text" value = "${device.description}"/><br>
                        </div>
                        <div class = "formInput">
                            <label for ="type">Seadme t��p: </label>
                            <select class="form-control" id = "type" name ="deviceId">
                                <c:forEach var="deviceType" items="${deviceTypes}">
                                    <option value ="${deviceType.id }">${deviceType.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                            <input type = "hidden" name = "requestId" value = "${requestId}"/>
<%--                         <input type="hidden" id ="csrf" name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
                        <input type = "submit" class = "btn btn-primary"  value = "Lisa seade"/>
                    </form:form>
        
        <button id = "search-devices">otsi</button>
    </div>
    <div id = "searchResult">
    
    </div>
    <script type="text/javascript" src="<c:url value = '/resources/js/callHelper.js'/>"></script>
    <script type="text/javascript" src='<c:url value = "/resources/js/jquery-2.1.3.min.js"/>'></script>
    <script type="text/javascript" src='<c:url value = "/resources/js/common.js"/>'></script>
    <script type="text/javascript" src='<c:url value = "/resources/js/requests.js"/>'></script>
    <script type="text/javascript" src='<c:url value = "/resources/js/repair.js"/>'></script>
    <script type="text/javascript" src='<c:url value = "/resources/bootstrap-3.3.4-dist/js/bootstrap.min.js"/>'></script>
    <script>
        SearchDevice.initButton();
    </script>
</body>
</html>
