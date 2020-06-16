<%@tag description="Le template d'un texte area dans un form"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@attribute name="col" required="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="false"%>
<%@attribute name="placeholder" required="false" %>
<%@attribute name="value" required="false" %>
<%@attribute name="rows" required="true"%>


<%@attribute name="erreurs_" required="false" type="java.util.List"%>

<div class="form-group ${col}">
	<c:if test="${label!=null}">
		 <label for="${name}">${label}</label>
	</c:if>
   
    <textarea class='form-control  ${empty erreurs_ ?"":"is-invalid"}' id="${name}" rows="${rows}" name="${name}" 
    	<c:if test="${value!=''}">placeholder="${placeholder}"</c:if>>
    	${value}
    </textarea>
    
    <c:if test="${!erreurs_.isEmpty()}">
		<div class="invalid-feedback">
			<c:forEach items='${erreurs_}' var="err">
				<span class="badge badge-pill badge-danger">${err}</span>
			</c:forEach>
		</div>
	</c:if>
</div>