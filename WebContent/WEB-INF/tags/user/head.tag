<%@tag description="Le template de bouton" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/img" prefix="img"%>
<%@attribute name="label" required="true"%>
<%@attribute name="nom" required="true"%>

<div class="row no-gutters flex-nowrap align-items-center">

	<div class="">
		<a class="text-white" href='<c:url value="#"/>'> <c:out value="Bonjour ${nom}"></c:out>
		</a>
	</div>
	<div
		class="mx-2 border rounded-pill  d-flex align-items-baseline bg-white">
		<img:card-img size="30px" value=""
			default_img="/public/img/icon/profile_greenBackground.png" />
		<div class="mr-2"><strong>${label}</strong></div>
	</div>

	<div class="">
		<a class="" href='<c:url value="/Deconnexion"/>'> <img:img
				size="40px" value="" default_img="/public/img/icon/logout.png" />
		</a>
	</div>
</div>

