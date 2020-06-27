<%@tag description="Le template de bouton" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="value" required="true"%>
<%@attribute name="label" required="true"%>

<%@attribute name="size" required="true"%>
<%@attribute name="img" required="true"%>

<li class="nav-item d-flex align-items-center w-100" >
	<div class=" row rounded-pill align-items-center" style='background-color: #3E703D;'>
		<div class="img-fluid col-4"
			style=" max-height: ${size}; max-width:${size}">
			<img
				src='<c:url value="${img}"/>'
				width="${size}" height="${size}" class="mx-2"/>
		</div>
		<div class="col-7">
			<a class="nav-link text-light" href="<c:url value="${value}"/>">${label}</a>
		</div>
		
	</div>
	<div class="bg-primary" style="height:70px;width:20px"></div>

</li>

