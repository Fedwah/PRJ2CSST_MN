<%@tag description="Le template d'un image et loader dans un form"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>

<%@attribute name="col" required="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="false"%>
<%@attribute name="value" required="false"%>
<%@attribute name="text" required="false"%>

<%@attribute name="erreurs_" required="false" type="java.util.List"%>

<div class="form-group ${col}">

	<c:if test="${label!=null }">
		<label for="${name}">${label}</label>
	</c:if>
	
	<div class="custom-file">
		<input id="photo" name="${name}" type="file" value="${value}"
 			class="custom-file-input" webkitdirectory directory> 
			<label class="custom-file-label"
			for="photo" >${text!=null?text:"Importer"}</label>
	</div>

	<div class="invalid-feedback">
		<c:forEach items='${erreurs_}' var="err">
			<span class="badge badge-pill badge-danger">${err}</span>
		</c:forEach>
	</div>
</div>