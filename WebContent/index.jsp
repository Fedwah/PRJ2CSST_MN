<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu (de test)</title>
<link href="/boostrap/css/bootstrap.min.css" />
<script src="boostrap/js/bootstrap.min.js"></script>
</head>
<body>

	<ul>
		<li>
			<a href="<c:url value="/Vehicules/form"/>">Ajouter un vehicule</a>
		</li>

		<li>
			<a href="<c:url value="/Vehicules"/>">Liste des vehicules</a>
		</li>

	</ul>

</body>
</html>