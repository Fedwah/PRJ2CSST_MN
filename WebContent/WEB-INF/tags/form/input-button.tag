<%@tag description="Le template d'un input dans un form"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="col" required="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="false"%>
<%@attribute name="value" required="true"%>
<%@attribute name="type" required="true"%>
<%@attribute name="placeHolder" required="false"%>

<%@attribute name="erreurs_" required="false" type="java.util.List"%>

<div class="form-group ${col}">
	<c:if test="${lablel!=null}">
		<label for="${name}">${label}</label>
	</c:if>
	<div class='input-group ${empty erreurs_?"":"is-invalid"}'>
		<input type="${type}"
			class='form-control ${empty erreurs_?"":"is-invalid"}' name="${name}"
			aria-label="Recipient's username" aria-describedby="button-add"
			id="${name}"
			<c:choose>
				<c:when test="${empty value}">
					placeholder="${placeHolder}"
				</c:when>
				<c:otherwise>
					value= "<c:out value='${value}' />"
				</c:otherwise>
			</c:choose> 
		/>
		<div class="input-group-append">
			<button class="btn btn-outline-success" type="submit" id="button-add">Ajouter</button>
		</div>
	</div>

	<div class="invalid-feedback">
		<c:forEach items='${erreurs_}' var="err">
			<span class="badge badge-pill badge-danger">${err}</span>
		</c:forEach>
	</div>

</div>

