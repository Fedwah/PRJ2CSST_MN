<%@tag description="Le template de bouton" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="value" required="true"%>
<%@attribute name="label" required="true"%>
<%@attribute name="size" required="true"%>
<%@attribute name="img" required="true"%>
<%@attribute name="top" required="false"%>
<%@attribute name="left" required="false"%>
<%@attribute name="topI" required="false"%>
<%@attribute name="leftI" required="false"%>

<li class="nav-item d-flex  align-items-center  w-100  mt-1" >

	<div class=" row  no-gutters rounded-pill flex-fill  align-items-center" style='background-color: #3E703D;'>
		<div class="img-fluid col-4"
			style=" max-height: ${size}; max-width:${size}">
			<img
				style="position: relative; top:${topI}; left: ${leftI};"
				src='<c:url value="${img}"/>'
				width="${size}" height="${size}" class="mx-2"/>
		</div>
		<div class="col-7" style="position: relative; top:${top}; left: ${left}">
			<a class="nav-link text-light" href="<c:url value="${value}"/>">${label}</a>
		</div>
		
	</div>
	<div class="triangle-fin mr-n5" >
	</div>

</li>

