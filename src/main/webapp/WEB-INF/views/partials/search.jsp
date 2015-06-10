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
        <input type = "text" class = "form-control" placeholder = "Sisesta id.." id ="search-device-id"/>
        <br/>
        <input type ="text" class = "form-control" placeholder = "Sisesta nimi.." id = "search-device-name"/>
          <br/>
          <input type ="text" class = "form-control" placeholder = "Sisesta mudel.." id = "search-device-model"/>
          <br/>
          <label>Seadme tüüp: </label>
          <select class="form-control" name ="deviceId" id = "search-device-type">
            <c:forEach var="deviceType" items="${deviceTypes}">
                <option value ="${deviceType.id }">${deviceType.name}</option>
            </c:forEach>
        </select>
        
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
