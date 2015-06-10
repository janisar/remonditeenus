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
    <form:form action = "getRequest" method="GET" id = "getRequest">
        <input type = "hidden" id = "requestId" name = "requestId" val=""/>
    </form:form>
    <div id = "wrapper">
        <jsp:include page="partials/header.jsp"></jsp:include>
        <div id = "container">
            <table class = "table" id = "request-table">
                <tr>
                    <th>P��rdumise id</th>
                    <th>Klient</th>
                    <th>Kliendi mure</th>
                    <th>Staatus</th>
                </tr>
                <c:forEach items="${requests}" var="request">
                    <tr class = "request-row">
                        <td>${request.id}</td>
                        <td>${request.customer.subject.firstName }</td>
                        <td>${request.serviceDescriptionByCustomer }</td>
                        <td>${request.serviceRequestStatusType.name }</td>

                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="modal fade" id="newOrderModal" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Tellimuse seadmete lisamine</h4>
            </div>
            <div class="modal-body">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Sulge</button>
                <button type="button" class="btn btn-primary">Salvesta</button>
            </div>
        </div>
    </div>
</div>
    <script type="text/javascript" src='<c:url value = "/resources/js/jquery-2.1.3.min.js"/>'></script>
    <script type="text/javascript" src='<c:url value = "/resources/js/common.js"/>'></script>
    <script type="text/javascript" src='<c:url value = "/resources/js/requests.js"/>'></script>
    <script type="text/javascript" src='<c:url value = "/resources/bootstrap-3.3.4-dist/js/bootstrap.min.js"/>'></script>
    <script>
        row.initRowButtons();
    </script>
</body>
</html>
