<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <head>
    </head>
<form:form action="generateOrder" method = "POST" id = "generateOrder" modelAttribute="serviceOrder">
    <div class = "formInput">
        <label for ="description">Tellimuse kirjeldus: </label>
        <input type = "text" class = "form-control" name = "description" id = "description"/><br>
    </div>
    <h3>Vali seade: </h3>
   <select id = "device-selecter" class = "form-control" name ="deviceId">
       <c:forEach items="${devices }" var = "device">
           <option value="${device.deviceId}">${device.name}</option>
       </c:forEach>
       <option value="-1">Muu</option>
   </select>
    <button id = "add_device_to_order" type="button" class="btn btn-default" aria-label="Left Align">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Lisa toode tellimusele
    </button>
    <a href ="#" id = "addNewDevice">Lisa uus</a>
    <table class = "table" id = "current_devices">
               <tr>
                   <th>Id</th>
                   <th>Nimi</th>
                   <th>Mudel</th>
                   <th>Tootja</th>
                   <th>${serviceOrderBean.serviceOrder.serviceDevices}</th>
               </tr>
                
               <c:forEach items="${serviceOrderBean.devices }" var="sdevice">
                   <tr>
                       <td>${sdevice.device.deviceId }</td>
                       <td>${sdevice.device.name }</td>
                       <td>${sdevice.device.model }</td>
                       <td>${sdevice.device.manufacturer }</td>
                   </tr>
               </c:forEach>
           </table>
                        
                <div id = "newDeviceForm">
                <div class="modal-header">
                    <h4 class="modal-title">Uue seadme lisamine</h4>
                </div>
                <div class="modal-body">
                    <form:form action="saveDevice" method = "POST" id = "saveDevice" modelAttribute="newDevice">
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
                            <label for ="type">Seadme tüüp: </label>
                            <select class="form-control" id = "type" name ="deviceId">
                                <c:forEach var="deviceType" items="${deviceTypes}">
                                    <option value ="${deviceType.id }">${deviceType.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <input type="hidden" id ="csrf" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button class = "btn btn-primary" id = "saveNewDevice">Lisa seade</button>
                    </form:form>
                </div>
            </div>
</form:form>
<script type="text/javascript" src='<c:url value = "/resources/js/jquery-2.1.3.min.js"/>'></script>
<script type="text/javascript" src='<c:url value = "/resources/js/common.js"/>'></script>
<script type="text/javascript" src='<c:url value = "/resources/js/repair.js"/>'></script>
<script type="text/javascript" src='<c:url value = "/resources/bootstrap-3.3.4-dist/js/bootstrap.min.js"/>'></script>
<script>
    repairDeviceSelector.initField();
    postOrder.initField();
    
    $(document).ready(function () {
        $("#saveNewDevice").click(function (event) {
            event.preventDefault();
            
            
            $( "#saveDevice" ).submit();
        });
    });
</script>