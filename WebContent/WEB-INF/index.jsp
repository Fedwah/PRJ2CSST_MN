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
</head>
<body>
	<!-- 
	<ul>
	d
		<li>
			<a href="<c:url value="/Vehicules/form"/>">Ajouter un vehicule</a>
		</li>

		<li>
			<a href="<c:url value="/Vehicules"/>">Liste des vehicules</a>
		</li>

	</ul>
	
 -->

	<c:import url="${vue}"></c:import>

</body>
</html>