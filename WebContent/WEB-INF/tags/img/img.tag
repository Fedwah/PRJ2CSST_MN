<%@tag description="Le template d'une image d'une card" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="value" required="true"%>
<%@attribute name="size" required="true"%>
<%@attribute name="default_img" required="false"%>
<%@attribute name="class_" required="false"%>



<c:choose>
	<c:when test="${value!=null && !empty value}">
		<div class="img-fluid ">
			<img 
				src='<c:url value="/Images/${value}" />' class="${class_}" alt="..."
				width="${size}" />
		</div>

	</c:when>

	<c:otherwise>
		<div class="img-fluid ">
			<img class="${class_}"
				src='<c:url value="${empty default_img|| defautl_img==null?'/public/img/notfound.png':default_img}"/>'
				width="${size}" />
		</div>

	</c:otherwise>
</c:choose>
