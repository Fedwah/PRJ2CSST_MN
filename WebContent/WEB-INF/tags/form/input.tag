<%@tag description="Le template d'un input dans un form"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="col" required="false"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="false"%>
<%@attribute name="value" required="true"%>
<%@attribute name="type" required="true"%>
<%@attribute name="placeHolder" required="false"%>

<%@attribute name="erreurs" required="false" type="java.util.List"%>
<%@attribute name="isDisabled" required="false" type="java.lang.Boolean"%>




<div class="form-group ${col}">
	<c:if test="${lablel!=null}">
		<label for="${name}">${label}</label>
	</c:if>

	<input type="${type}"
		class='form-control ${empty erreurs ?"":"is-invalid"} ' id="${name}"
		name="${name}" ${isDisabled?'disabled':''}
		<c:choose>
				<c:when test="${empty value}">
					placeholder="${placeHolder}"
				</c:when>
				<c:otherwise>
					value= "<c:out value='${value}' />"
				</c:otherwise>
		</c:choose> />

	<c:if test="${isDisabled}">
		<input type="hidden" name="${name}" value="<c:out value='${value}' />" />
	</c:if>

	<div class="invalid-feedback">
		<c:forEach items='${erreurs}' var="err">
			<span class="badge badge-pill badge-danger">${err}</span>
		</c:forEach>
	</div>
</div>

