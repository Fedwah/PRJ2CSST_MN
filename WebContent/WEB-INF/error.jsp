<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/pather" prefix="pather"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/public/bootstrap/css/bootstrap.min.css"/>" />
<script src="<c:url value="/public/js/jquery-3.5.1.min.js"/>"></script>
<script src="<c:url value="/public/bootstrap/js/bootstrap.min.js"/>"></script>

<link href="<c:url value='/public/style/main.css'/>" rel="stylesheet"
	type="text/css">
</head>
<body>

	<c:import url="vues/header/header.jsp"></c:import>
	<div class="p-2">
		<div>
			<pather:path path="${path }"></pather:path>
		</div>
		<h1 class="page-title">${title}</h1>
		
		<div class="alert alert-danger p-4 m-5" role="alert">
			${message}
		</div>
		
	</div>

</body>
</html>