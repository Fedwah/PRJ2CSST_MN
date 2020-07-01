<%@tag description="Le template d'un radio dans un form" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="col" required="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="true"%>
<%@attribute name="addLink" required="false"%>

<%@attribute name="erreurs_" required="false" type="java.util.List"%>
<%@attribute name="isDisabled" required="false" type="java.lang.Boolean"  %>

<%@attribute name="selectedValue" required="true"%>
<%@attribute name="fieldToTest" required="true"%>
<%@attribute name="fieldToPrint" required="true"%>
<%@attribute name="fieldID" required="true"%>
<%@attribute name="items" required="true" type="java.util.List"%>

<div class="form-group col-md-4">

	<label for="${name}">${label}</label>
	<c:forEach items="${items}" var="i">
		<div class="custom-control custom-radio">
		
			<input type="radio" id="${i[fieldID]}" name="${name}"
				class="custom-control-input "
				value="${i[fieldID]}"
				${i[fieldToTest]==selectedValue?"checked":""}> <label
				class="custom-control-label" for="${i[fieldID]}">${i[fieldID]}</label> 
				<!--${empty erreurs_ ?'':'is-invalid'}" a faire apres-->
		</div>
	</c:forEach>
	<c:if test="${!empty addLink}">
		<a class="btn btn-sm btn-outline-success"
		href='<c:url value="${addLink }"/>'>+</a>
	</c:if>
	
</div>