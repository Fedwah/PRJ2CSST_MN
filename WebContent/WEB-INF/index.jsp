<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/public/bootstrap/css/bootstrap.min.css"/>" />
<script src="<c:url value="/public/js/jquery-3.5.1.min.js"/>"></script>
<script src="<c:url value="/public/bootstrap/js/bootstrap.min.js"/>"></script>
<link href="<c:url value='/public/style/main.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
	
	<c:import url="vues/header/header.jsp"></c:import>
	<div class="row">
		<div class="col-md-2">
			<c:choose>
				<c:when test="${param.role == 'admin'}">
					<c:import url="vues/menu/menuAdmin.jsp"></c:import>
					
				</c:when>
				<c:otherwise>
					Menu admin root
					<c:import url="vues/menu/menuAdmin.jsp"></c:import>
					Menu admin opérationnel
					<c:import url="vues/menu/menuAdminOP.jsp"></c:import>
				</c:otherwise>
			</c:choose>

		</div>

		<div class="col-md">
			<c:import url="${vue}"></c:import>
		</div>
	</div>



</body>
</html>