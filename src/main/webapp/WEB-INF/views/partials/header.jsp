<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id = "header_section">
	<h3 id = "header">Remondit��koda</h3>
	<a href = "/Remonditeenus">Kodu</a>
    <c:if test ="${subjectType.id == 3}">
        <a href = "/Remonditeenus/requests">Klientide p��rdumised</a>
    </c:if>
    <c:if test="${subjectType.id != 3 }">
        <a href = "/Remonditeenus/myRequests">Minu p��rdumised</a>
    </c:if>
	<a id = "logout" href="javascript:formSubmit()"> Logi v�lja</a>
<%-- 	<h4 >Tere, ${pageContext.request.userPrincipal.name}</h4> --%>
</div>
<form action="/Remonditeenus/j_spring_security_logout" method="post" id="logoutForm">
    <input type="hidden" 
        name="${_csrf.parameterName}"
        value="${_csrf.token}" />
</form>
<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>