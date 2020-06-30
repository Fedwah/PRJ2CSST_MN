<%@tag description="Le template d'un input dans un form"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  tagdir="/WEB-INF/tags/form" prefix="f"%>

<%@attribute name="col" required="false"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="false"%>
<%@attribute name="value" required="true"%>
<%@attribute name="min" required="true"%>
<%@attribute name="max" required="true"%>
<%@attribute name="steps" required="false"%>
<%@attribute name="unite" required="false"%>


<div class="form-row  ${col} align-items-end">
	<div class="form-group col-md-9">

		<c:if test="${label!=null}">
			<label for="${name}">${label}</label>
		</c:if>

		<input id="${name}-range" class="custom-range range-update" type="range" value="${value}" max="${max}"
			min="${min}" step="${steps!=null?steps:'1'}">
	</div>  
	<div class="form-group col-md-2">
		<input id="${name}-range-value" name="${name}" class="form-control" type="number" 
		value="${value}" max="${max }" min="${min }" step="${steps!=null?steps:'1'}"/>
	</div>
	<c:if test="${!empty unite}">
		<div class="form-group col-md my-auto">
			${unite}
		</div>
	</c:if>
	
	
</div>

