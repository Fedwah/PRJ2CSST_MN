<%@tag description="Le template d'un select dans un form"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="col" required="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="true"%>
<%@attribute name="addLink" required="true"%>

<%@attribute name="selectedValue" required="true"%>
<%@attribute name="fieldToTest" required="true"%>
<%@attribute name="fieldToPrint" required="true"%>
<%@attribute name="fieldID" required="true"%>
<%@attribute name="items" required="true" type="java.util.List"%>

<div class="form-group ${col}">
	<label for="${name}">${label}</label>
	<div class="input-group mb-3">
		<select id="${name}" class="form-control" required="required"
			name="${name}">
			<c:forEach items="${items}" var="i">
				<option ${selectedValue==i[fieldToTest]?"selected":""} value="${i[fieldID]}">${i[fieldToPrint]}</option>
			</c:forEach>
		</select>

		<div class="input-group-append">
			<a class="btn btn-outline-success"
				href='<c:url value="${addLink}"/>'>+</a>
		</div>
	</div>
</div>