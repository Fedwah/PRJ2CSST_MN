<%@tag description="Le boutton de recherche" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/btn" prefix="btn"%>

<%@attribute name="filename" required="true"%>
<%@attribute name="previewable" required="false"
	type="java.lang.Boolean"%>
<%@attribute name="downloadable" required="true"
	type="java.lang.Boolean"%>


<c:if test="${previewable==null || previewable}">
	<div class="embed-responsive embed-responsive-16by9">
		<iframe class="embed-responsive-item"
			src='${pageContext.request.contextPath }/Fichier/generate/"${filename}"'
			width="100%" height="100%"> </iframe>

	</div>
</c:if>

<c:if test="${downloadable}">
	<div class="row">
		<btn:btn type="success" value='/Fichier/generate/"${filename}"'
			text="Telecharger" class_="col"></btn:btn>
	</div>
</c:if>

