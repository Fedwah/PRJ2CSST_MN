<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/user" prefix="user"%>

<nav class="d-flex justify-content-between  sticky-top"
	style='background-color: #3E703D;'>
	<a style='color: white; display: inline-block;' href="#"> <img
		style='display: inline-block;'
		src='<c:url value="/public/img/logos/mn_greenBackground.png" />'
		width="60" height="60" alt="" /> <span class="h2"
		style='display: inline-block; position: relative; top: 10px; left: 10px;'>Maintenance
			nationale</span>
	</a>
	<c:if test="${sessionScope.sessionUtilisateur != null}">

		<user:head
			label="${sessionScope.sessionUtilisateur.type}/${sessionScope.sessionUtilisateur.role}"
			nom="${sessionScope.sessionUtilisateur.nom}"/>
			
	</c:if>

</nav>
