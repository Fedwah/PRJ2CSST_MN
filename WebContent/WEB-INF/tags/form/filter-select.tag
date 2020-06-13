<%@tag description="Le template d'un select hiarchique dans un form"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="col" required="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="true"%>
<%@attribute name="addLink" required="true"%>

<%@attribute name="selectedValue" required="true"%>

<%@attribute name="fieldChild" required="true"%>
<%@attribute name="childfieldID" required="true"%>
<%@attribute name="childfieldToPrint" required="true"%>
<%@attribute name="childfieldToTest" required="true"%>
<%@attribute name="fieldID" required="true"%>
<%@attribute name="items" required="true" type="java.util.List"%>


<div class="form-group ${col}">
	<label for="${name}">${label}</label>
	<div class="input-group mb-3">
		<select id="${name}" class="form-control" required="required"
			name="${name}">
			<c:forEach items="${items}" var="i">
				<optgroup label="${i[fieldID]}">
					<c:forEach items="${i[fieldChild]}" var="m">
						<option ${selectedValue==m[childfieldID]?"selected":""}
							value='${ m[childfieldID]}'>${m[childfieldToPrint]}</option>
					</c:forEach>
				</optgroup>

			</c:forEach>
		</select>
		<div class="input-group-append">
			<a class="btn btn-outline-success"
				href='<c:url value="${addLink}"/>'>+</a>
		</div>
	</div>

</div>