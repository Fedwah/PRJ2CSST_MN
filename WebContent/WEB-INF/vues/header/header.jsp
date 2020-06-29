<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/user" prefix="user"%>

<nav class="d-flex justify-content-between " style='background-color: #3E703D;'>
	<a style='color: white;display: inline-block;' href="#"> 
		<img style='display: inline-block;'
		src='<c:url value="/public/img/logos/mn_greenBackground.png" />' width="60"
		height="60" alt="" loading="lazy"/>
		<h3 style='display: inline-block;position: relative; top: 10px; left: 10px;'>Maintenance nationale</h3>
		<span class="h2" style='display: inline-block;position: relative; top: 10px; left: 10px;'>Maintenance nationale</span>
	</a>
	<c:if test="${sessionScope.sessionUtilisateur != null}">

	<div style="float:right; width:350px; height:auto;">
	<a style='color:white;display: inline-block;position:relative; top: 16px; left: 0px;'
	href='<c:url value="#"/>'>
	<c:out value="Bonjour ${sessionScope.sessionUtilisateur.nom}"></c:out>
	</a>
	<div style='display: inline-block;position:relative; top: 15px; left: 0px;'>
	<user:head label="${sessionScope.sessionUtilisateur.type}/${sessionScope.sessionUtilisateur.role}"></user:head>
	</div>
	<a style='position:relative; top: 15px; left: 0px;'
	href='<c:url value="/Deconnexion"/>'>
	<img style='display: inline-block;'
		src='<c:url value="/public/img/icon/logout.png" />' width="40"
		height="40" alt="" loading="lazy"/>
		
	</a>	
		
	</div>
	</c:if>
	
</nav>
