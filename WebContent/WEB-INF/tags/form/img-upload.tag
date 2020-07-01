<%@tag description="Le template d'un image et loader dans un form"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="img" tagdir="/WEB-INF/tags/img"%>

<%@attribute name="col" required="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="true"%>


<%@attribute name="erreurs_" required="false" type="java.util.List"%>
<%@attribute name="image" required="true" type="beans.entities.general.Image"%>
<div class="form-group ${col}">
	<label for="${name}">${label}</label>

	<img:img  id="preview" size="200px" value="${image.titre}" default_img="/public/img/notfound.png"/>



	
	<div class="custom-file">
		<input id="photo" name="${name}" type="file"
			class="custom-file-input"> <label class="custom-file-label"
			for="photo">Importer</label>
	</div>

	<div class="invalid-feedback">

		<c:forEach items='${erreurs_}' var="err">
			<span class="badge badge-pill badge-danger">${err}</span>
		</c:forEach>
	</div>
</div>