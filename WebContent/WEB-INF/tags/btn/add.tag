<%@tag description="Le boutton de recherche" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/btn" prefix="btn"%>

<%@attribute name="value" required="true"%>

<a class="btn btn-light rounded-circle" style='display: inline-block;height:40px; width:40px;'
				href='<c:url value="${value}"/>'>
		<img width="50px" height="50px" style='position: relative; top: -10px; left: -17px;' src="<c:url value='/public/img/icon/add_green_nobackground.png'/>" />
</a>
