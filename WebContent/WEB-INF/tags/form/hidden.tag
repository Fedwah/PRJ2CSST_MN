<%@tag description="Le template d'une image d'une card"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/form" prefix="form"%>

<%@attribute name="values" required="false" %>
<%@attribute name="values_names" required="false"%>
<%@attribute name="action" required="false"%>
<%@attribute name="label" required="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="btn_type" required="true"%>




<form method="post" action='<c:url value="${action}"/>' class="col">
	
	<input type="submit" value="${label}" name ="${name}" class="btn  ${btn_type}" />
	<c:forEach items="${values_names.split(',')}" var="n" varStatus="s">
		<form:input name="${n}" type="hidden" value="${values.split(',')[s.index]}"/>
		
	</c:forEach>
	
</form>
