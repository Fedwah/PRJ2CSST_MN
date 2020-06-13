<%@tag description="Le template d'un input dans un form" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="col" required="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="true"%>
<%@attribute name="value" required="true"%>
<%@attribute name="type" required="true"%>

<%@attribute name="erreurs" required="false" type="java.util.List"%>
<%@attribute name="isDisabled" required="false" type="java.lang.Boolean"  %>


<div class="form-group ${col}">

	<div class="form-group">
		<label for="${name}">${label}</label> <input type="${type}"
			class='form-control ${empty erreurs ?"":"is-invalid"} ' id="${name}"
			name="${name}" value="<c:out value="${value}" />"
			${isDisabled? 'disabled':''}>
		
		<c:if test="${isDisabled}">
			<input type="hidden" name="${name}" value="${value}">
		</c:if>

		<div class="invalid-feedback">
			<c:forEach items='${erreurs}' var="err">
				<span class="badge badge-pill badge-danger">${err}</span>
			</c:forEach>
		</div>
	</div>
</div>
