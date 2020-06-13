<%@tag description="Le template de bouton" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="type" required="true"%>
<%@attribute name="value" required="true"%>

<%@attribute name="text" %>
<%@attribute name="img" %>


<a class="btn btn-outline-${type}" href='<c:url value="${value}"/>'>

	<c:choose>
		<c:when test="${img!=null}">
			<img width="15px" height="15px" src="<c:url value='${img}'/>" />
		</c:when>
		<c:otherwise>
			${text}
		</c:otherwise>
	</c:choose>

</a>
