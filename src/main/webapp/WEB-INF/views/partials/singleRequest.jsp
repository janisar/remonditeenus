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
    <meta name="status" content="${request.serviceRequestStatusType.id}"/>
</head>
<body>

    <c:if test='${empty orderParts}'> 
        <c:set value="hidden-section" var="order_hidden_section"></c:set>
    </c:if> 
    <c:if test='${empty orderDevices}'> 
        <c:set value="hidden-section" var="devices_hidden_section"></c:set>
    </c:if> 
    <c:if test='${empty orderJobs}'> 
        <c:set value="hidden-section" var="order_hidden_jobs"></c:set>
    </c:if> 

    <form:form action = "getRequest" method="GET" id = "getRequest">
        <input type="hidden" id ="csrf" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type = "hidden" id = "requestId" name = "requestId" val=""/>
        <div id = "initialCall" style = "display:none;">${initialCall }</div>
    </form:form>
    <div id = "wrapper">
        <jsp:include page="header.jsp"></jsp:include>
        <div id = "container">
            <a href = "/Remonditeenus/requests">Tagasi</a>
<form:form action="generateOrder" method = "POST" id = "generateOrder" modelAttribute="serviceOrder">
    <p>
    <div>
        Tellimus esitatud: ${request.createdDate } <br/>
        Tellimuse klient: ${request.customer.subject.firstName } ${request.customer.subject.lastName } <br/>
        Tellimuse kirjeldus kliendilt: ${request.serviceDescriptionByCustomer }<br> 
        Tellimuse summa: <b id = "current-price">${price} &euro;</b> 
        <br>
        <label>Pöördumise staatus</label>
        <select id = "requestStatus">
            <option></option>
            <c:forEach items = "${statusTypes}" var = "type">
                <option id = "${type.id}">${type.name}</option>
            </c:forEach>
        </select>
    </div>
    <input type ="hidden" id = "requestId" value = "${request.id }" />
    <div class = "formInput">
        <label for ="description">Tellimuse kirjeldus: </label>
        <input type = "text" class = "form-control" name = "description" id = "description" value = "${request.serviceDescriptionByEmployee }"/><br>
        <br><button id = "saveOrder" class = "btn btn-success">Salvesta kirjeldus</button>
        <p>
    </div>
</form:form>   
    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseDevice" aria-expanded="false" aria-controls="collapseDevice">
        Lisa seade tellimusele
    </button>
    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapsePart" aria-expanded="false" aria-controls="collapsePart">
        Lisa varuosa tellimusele
    </button>
    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseJob" aria-expanded="false" aria-controls="collapseJob">
        Lisa töö tellimusele
    </button>
    <div style = "">
        <div class="collapse" id="collapseDevice">
        
        
<!--             <div style ="width: 100%;float:left;margin-right: 15px;"> -->
                <a class="btn btn-primary btn-lg" data-toggle="modal" id="searchDevices">otsi</a>
                <h3>Vali seade: </h3>
                
                <label>&nbsp;</label>
                
                <select id = "device-selecter" class = "form-control" name ="deviceId">
                    <c:forEach items="${devices }" var = "device">
                        <option value="${device.deviceId}">${device.name}</option>
                    </c:forEach>
                    <option value="-1">Muu</option>
                </select>
                <br>
                <form:form method= "POST" action = "addDeviceToOrder" id = "addDeviceToOrder">
                    <input type="hidden" id ="csrf" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button id = "add_device_to_order" type="button" class="btn btn-default" aria-label="Left Align">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                        Lisa toode tellimusele
                    </button>
                </form:form>
                <a href ="#" id = "addNewDevice">Lisa uus</a>
                <div id = "newDeviceForm">
                <div class="modal-header">
                    <h4 class="modal-title">Uue seadme lisamine</h4>
                </div>
                <div class="modal-body">
                    <form:form action="saveDevice" method = "POST" id = "saveDevice" modelAttribute="newDevice">
                        <form:errors path="*" cssClass="errorblock" element="div" />
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
                            <label for ="text">Seadme kirjeldus: </label>
                            <input type = "text" class = "form-control" name = "description" id = "text"/><br>
                        </div>
                        <div class = "formInput">
                            <label for ="price">Seadme hind: </label>
                            <input type = "text" class = "form-control" name = "price" id = "price"/><br>
                        </div>
                        <div class = "formInput">
                            <label for ="type">Seadme tï¿½ï¿½p: </label>
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
                </div>
            </div>
        </div>

        <div class="collapse" id="collapsePart">
            <div style ="width: 100%;float:right;">
                
                <h3>Lisa varuosa: </h3>
                <div id = "service-part-error">
                
                </div>
                <label>Nimi</label>
                <input type = "text" class = "form-control" name = "name" id = "part_name"/><br>
                <label>Hind</label>
                <input type = "text" class = "form-control" name="price" id = "part_price"/><br>
                <label>Kogus</label>
                <input type = "text" class = "form-control" name="amount" id = "part_amount"/><br>
                <label>Seerianumber</label>
                <input type = "text" class = "form-control" name="seriaNr" id = "part_seria_nr"/><br>
    <!--             <button id = "add-part" class = "btn btn-primary">Lisa varuosa</button> -->
                <button id = "add-part" type="button" class="btn btn-default" aria-label="Left Align">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    Lisa varuosa tellimusele
                </button>
            </div>
        </div>
        
        <div class="collapse" id="collapseJob">
            <div style ="width: 100%;float:right;">
                <h3>Lisa töö: </h3>
                <div id = "order-job-errors">
                
                </div>
                <label>Kirjeldus</label>
                <input type = "text" class = "form-control" name = "name" id = "job_description"/><br>
<!--                 <label>Tï¿½ï¿½ staatus</label> -->
<%--                 <select class = "form-control"> --%>
<%--                     <option id ="${serviceActionStatusTypes[0].id }">${serviceActionStatusTypes[0].name }</option> --%>
<%--                     <option id ="${serviceActionStatusTypes[1].id }">${serviceActionStatusTypes[1].name }</option> --%>
<%--                 </select> --%>
                <label>Tï¿½ï¿½ liik ja kogus</label><br>
                <input class = "form-control" type ="number" id = "job_amount" style="width: 15%; display: inline-block;" placeholder = "kogus.."/>
                <select class = "form-control" id = "job_type" style = "width: 85%; display: inline-block;float:right">
                    <c:forEach items="${ serviceTypes}" var ="type">
                        <option id = "${type.id }">${type.typeName }[${type.serviceUnitType.name }]</option>
                    </c:forEach>
                </select>
                <br>
               
                <label>ï¿½he ï¿½hiku hind</label>
                <input type = "number" class = "form-control" name="price" id = "job_price"/><br>
                <button id = "add-job" type="button" class="btn btn-default" aria-label="Left Align">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    Lisa tï¿½ï¿½ tellimusele
                </button>
            </div>
        </div>
    </div>
        <p>
    <div id = "order-devices" class = "${devices_hidden_section}">
        <h3>Tellimusega seotud seadmed</h3>
        <table class = "table" id = "current_devices">
                   <tr>
                       <th>Id</th>
                       <th style="width: 50%;text-align: center;">Nimi</th>
                       <th style="text-align: right;">Staatus</th>
                       <th> </th>
                   </tr>
                   <c:forEach items="${orderDevices}" var = "sDevice">
    <%--                hello ${sDevice.parts } --%>
                        <tr id = "${sDevice.device.deviceId}" >
                            <td class = "orderDeviceIds" >${sDevice.device.deviceId}</td>
                            <td>${sDevice.device.name}</td>
                            <td>
                                <select class = "form-control status-selector" id = "serviceDeviceStatus">
                                    <option> </option>
                                    <c:forEach items="${deviceStatuses}" var="status">
                                        <c:choose>
                                            <c:when test="${sDevice.serviceDeviceStatusType.id==status.id }">
                                                <option value="${status.id}" selected="selected">${status.name }</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${status.id}">${status.name }</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><span class="glyphicon glyphicon-remove remove-device" aria-hidden="true"><a style="display:none;">${sDevice.id}</a></span></td>
                        </tr>
                   </c:forEach>
               </table>
           </div>
           <div id ="order-parts" class = "${order_hidden_section}">
               <h3>Tellimusega seotud varuosad</h3>
               <table class = "table" id = "current_parts">
                    <tr>
                        <th>id</th>
                        <th>nimi</th>
                        <th>hind</th>
                        <th>kogus</th>
                        <th>seade</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${orderParts}" var="part">
                    <tr>
                        <td>${part.id }</td>
                        <td>${part.name }</td>
                        <td>${part.partPrice }</td>
                        <td>${part.partCount }</td>
                        <td>
                            <c:choose>
                                <c:when test="${!empty part.serviceDevice}">
                                    ${part.serviceDevice.device.name }                                
                                </c:when>
                                <c:otherwise>
                                    <select class = "selector currentDeviceSelector form-control"></select>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><span class="glyphicon glyphicon-remove remove-part" aria-hidden="true"><a style="display:none;">${part.id}</a></span></td>
                    </tr>
               </c:forEach>
               </table>
           </div>
           
           <div id = "order-jobs" class = "${order_hidden_jobs}">
               <h3>Tellimusega seotud tööd</h3>
               <table class = "table" id = "current_jobs">
                    <tr>
                        <th>id</th>
                        <th>nimi</th>
                        <th>hind</th>
                        <th>kogus</th>
                        <th>seade</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${orderJobs}" var = "job">
                        <tr>
                            <td class = "job-id">${job.id }</td>
                            <td>${job.description }</td>
                            <td><input type = "text" value = "${job.price }" class = "form-control job-current-price" id = "job-price" readonly/></td>
                            <td><input type = "text" value = "${job.amount }" class = "form-control job-current-amount" id = "job-amount" readonly/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${!empty job.serviceDevice}">
                                        ${job.serviceDevice.device.name }                                
                                    </c:when>
                                    <c:otherwise>
                                        <select class = "selector currentJobSelector form-control"></select>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <span class="glyphicon glyphicon-remove remove-job" aria-hidden="true"><a style="display:none;">${job.id}</a></span>
                                <span class="glyphicon glyphicon-pencil edit-job" aria-hidden="true"><a style="display:none;">${job.id}</a></span>
                                
                            </td>
                        </tr>
                    </c:forEach>
               </table>
           </div>
        </div>
        <button class = "btn btn-default delete-order" data-toggle="modal" data-target="#deleteOrderModal">Kustuta tellimus</button>
    </div>
    <div class="modal fade" id="deleteOrderModal" >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Tellimuse kustutamine</h4>
                </div>
                <div class="modal-body">
                    Olete kindel et soovite tellimust kustutada?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Sulge</button>
                    <button type="button" class="btn btn-primary" id = "deleteAll">Kustuta</button>
                </div>
            </div>
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
    <script type="text/javascript" src="<c:url value = '/resources/js/callHelper.js'/>"></script>
    <script type="text/javascript" src='<c:url value = "/resources/js/jquery-2.1.3.min.js"/>'></script>
    <script type="text/javascript" src='<c:url value = "/resources/js/common.js"/>'></script>
    <script type="text/javascript" src='<c:url value = "/resources/js/requests.js"/>'></script>
    <script type="text/javascript" src='<c:url value = "/resources/js/repair.js"/>'></script>
    <script type="text/javascript" src='<c:url value = "/resources/bootstrap-3.3.4-dist/js/bootstrap.min.js"/>'></script>
    <script>
        SearchDevice.initButton();
        row.initRowButtons();
        repairDeviceSelector.initField();
        postOrder.initField();
        postNewDevice.initField();
        SaveOrder.initButton();
        DeleteDevice.initButtons();
        ServiceDeviceStatus.initSelector();
        ServicePart.initButton();
        ServicePart.initDeleteButton();
        OrderService.populateArray();
        ServicePart.populateSelectors();
        ServiceAction.initButton();
        JobPrice.initField();
        RequestStatus.initField();
        DeleteOrder.initButton();
    </script>
</body>
</html>
