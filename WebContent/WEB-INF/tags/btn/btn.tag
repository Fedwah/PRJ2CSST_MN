<%@tag description="Le template de bouton" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="type" required="true"%>
<%@attribute name="value" required="true"%>
<%@attribute name="outline" required="false" type="java.lang.Boolean"%>
<%@attribute name="small" required="false"  type="java.lang.Boolean"%>
<%@attribute name="disable" required="false"  type="java.lang.Boolean"%>
<%@attribute name="class_" required="false"%>
<%@attribute name="text" %>
<%@attribute name="img" %>


<a class="btn ${small!=null && small?'btn-sm':''} 
		  btn${outline!=null && !outline?'':'-outline'}-${type} ${class_}
		  ${disable!=null && disable?'disabled':''}" 
	href='<c:url value="${value}"/>' >

	<c:choose>
		<c:when test="${img!=null}">
			<img width="20px" height="20px" src="<c:url value='${img}'/>" alt="${value}"/>
		</c:when>
		<c:otherwise>
			${text}
		</c:otherwise>
	</c:choose>

</a>
