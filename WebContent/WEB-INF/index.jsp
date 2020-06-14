<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/public/bootstrap/css/bootstrap.min.css"/>" />
<link href="/public/style/main.css" rel="stylesheet" type="text/css">
<script src="<c:url value="/public/js/jquery-3.5.1.min.js"/>"></script>
<script src="<c:url value="/public/bootstrap/js/bootstrap.min.js"/>"></script>
</head>
<body>
	
	<c:import url="vues/header/header.jsp"></c:import>
	<div class="row">
		<div class="col-md-2">
			<c:import url="vues/menu/menuAdmin.jsp"></c:import>
		</div>
		
		<div class="col-md">
			<c:import url="${vue}"></c:import>
		</div>
	</div>
	
	

</body>
</html>