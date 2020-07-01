<%@tag description="Le template de bouton" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="path" required="true" type="beans.session.general.page.Pather"%>

<c:if test="${path!=null && !empty path}">
	<nav aria-label="breadcrumb" class="m-1 bg-light">
		<ol class="breadcrumb bg-light">
			<c:forEach items="${path.pages}" var="p" varStatus="s">
				<li class="breadcrumb-item ${s.isLast()?'active':''}"
					<c:if test="${s.isLast()}">aria-current="${p}"</c:if>><a
					href='<c:url value="${p.link}" />'> ${p.path}</a></li>
			</c:forEach>

		</ol>
	</nav>

</c:if>