<%@tag description="Le template de bouton" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="value" required="true"%>
<%@attribute name="label" required="true"%>

<li class="nav-item rounded-pill mb-1" style='background-color: #3E703D;'>
	<a class="nav-link text-light" href="<c:url value="${value}"/>">${label}</a>
</li>

