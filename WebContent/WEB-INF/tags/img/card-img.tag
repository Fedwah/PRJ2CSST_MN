<%@tag description="Le template d'une image d'une card" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="value" required="true"%>
<%@attribute name="size" required="true"%>
<%@attribute name="default_img" required="false"%>


<c:choose>
	<c:when test="${value!=null && !empty value}">
		<div class="card-img img-fluid" style=" max-height: ${size}; max-width:${size}">
			<img src='<c:url value="/Images/${value}" />' class="" alt="..."
				width="${size}" height="${size}"/>
		</div>

	</c:when>

	<c:otherwise>
		<div class="card-img img-fluid" style=" max-height: ${size}; max-width:${size}">
			<img class=""
				src='<c:url value="${empty default_img|| default_img==null?'/public/img/notfound.png':default_img}"/>'
				width="${size}" height="${size}"/>
		</div>

	</c:otherwise>
</c:choose>
