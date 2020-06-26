<%@tag description="Le boutton de recherche" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@attribute name="typename" required="true"%>
<%@attribute name="filename" required="false"%>
<%@attribute name="size" required="true"%>

<div class=" row  align-items-center">
	<div class="img-fluid col-4" style=" max-height: ${size}; max-width:${size}">
		<img src='<c:url value="/public/img/icon/files/type-${typename}.png"/>' 
		width="${size}" height="${size}"/>
	</div>
	<div class="col-8">
		${filename}
	</div>
</div>




