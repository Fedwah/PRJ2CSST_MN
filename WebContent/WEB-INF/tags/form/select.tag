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
		<select id="${name}" class="form-control" required="required"
			name="${name}">
			<c:forEach items="${items!=null?items:map}" var="i">
				<option ${selectedValue==i[fieldToTest]?"selected":""}
					value="${i[fieldID]}">${i[fieldToPrint]}</option>
			</c:forEach>
		</select>

		<c:if test="${addLink!=null}">
			<div class="input-group-append">
				<a class="btn btn-outline-success"
					href='<c:url value="${addLink}"/>'>+</a>
			</div>
		</c:if>

	</div>
</div>