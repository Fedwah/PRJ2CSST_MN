<%@tag description="Le template d'un select dans un form"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="col" required="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="false"%>
<%@attribute name="addLink" required="false"%>

<%@attribute name="selectedValue" required="true"%>
<%@attribute name="fieldToTest" required="true"%>
<%@attribute name="fieldToPrint" required="true"%>
<%@attribute name="fieldID" required="true"%>

<%@attribute name="items" required="false" type="java.util.List"%>
<%@attribute name="map" required="false" type="java.util.Map"%>

<div class="form-group ${col}">
	<c:if test="${label!=null}">
		<label for="${name}">${label}</label>
	</c:if>

	<div class="input-group">
		<input list="list-${name}" id="${name}"
			name="${name}" class="form-control" />

		<datalist id="list-${name}">
			
			<c:forEach items="${items!=null?items:map}" var="i">
				<option ${selectedValue==i[fieldToTest]?"selected":""}
							value="${i[fieldID]}" >${i[fieldToPrint]}</option>
			</c:forEach>

		</datalist>
	

	
	</div>
</div>