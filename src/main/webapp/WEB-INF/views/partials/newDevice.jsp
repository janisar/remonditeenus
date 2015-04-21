<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal fade" id="newDeviceModal" >
	<div class="modal-dialog">
    	<div class="modal-content">
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title">Uue seadme lisamine</h4>
      		</div>
      		<div class="modal-body">
      			<form action="saveDevice" method = "POST" id = "saveDevice">
      				<div class = "formInput">
	      				<label for ="name">Seadme nimi: </label>
			      		<input type = "text" class="form-control" name = "name" id = "name"/><br>
      				</div>
      				<div class = "forminput">
			      		<label for ="regNr">Registreesnumber: </label>
			      		<input type = "text" class = "form-control" name = "regNr" id="regNr"/><br>
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
			      		<select class="form-control" id = "type">
			      			<c:forEach var="deviceType" items="${deviceTypes}">
			      				<option>${deviceType.name}</option>
			      			</c:forEach>
			      		</select>
      				</div>
      			</form>
      		</div>
      		<div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Sulge</button>
		        <button type="button" class="btn btn-primary">Salvesta</button>
      		</div>
    	</div>
  	</div>
</div>