<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Validation d'ajout d'un vehicules</title>
		<link type="text/css" rel="stylesheet"
				href="<c:url value="/public/bootstrap/css/bootstrap.min.css"/>" />
		<script src="<c:url value="/public/bootstrap/js/bootstrap.min.js"/>"></script>
	</head>
<body>
		<p>${errerus}</p>
		<c:forEach items="${erreurs}" var="erreur">
			<p>${erreur.getKey()}</p>
			<p>${erreur.getValue()}</p>
			
		</c:forEach> 
		
		<p>${vehicule.num_immatriculation}</p>
		<p>${vehicule.marque}</p>
		
		<p>${vehicule.date_achat}</p>
		<p>${vehicule.etat}</p>
		
</body>
</html>