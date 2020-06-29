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
<script src="<c:url value="/public/js/sweetalert.min.js"/>"></script>
<script src="<c:url value="/public/bootstrap/js/bootstrap.min.js"/>"></script>

<link href="<c:url value='/public/style/main.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
	
	<c:import url="vues/header/header.jsp"></c:import>
	<div class="row no-gutters">
		<div class="col-md-2">
			<c:choose>
				<c:when test= "${sessionScope.sessionUtilisateur.type.equals('Regional') ||sessionScope.sessionUtilisateur.type.equals('Central') }">
					<c:import url="vues/menu/menuDecideur.jsp"></c:import>					
				</c:when>
				<c:when test="${sessionScope.sessionUtilisateur.type.equals('Operationnel') && sessionScope.sessionUtilisateur.role.equals('admin')} ">
					<c:import url="vues/menu/menuAdminOP.jsp"></c:import>
				</c:when>
				<c:when test="${sessionScope.sessionUtilisateur.type.equals('root')}">
					<c:import url="vues/menu/menuAdmin.jsp"></c:import>
				</c:when>
				<c:when test="${sessionScope.sessionUtilisateur.type.equals('Operationnel') && sessionScope.sessionUtilisateur.role.equals('parc')}">
					<c:import url="vues/menu/menuRespoParc.jsp"></c:import>
				</c:when>
				<c:when test="${sessionScope.sessionUtilisateur.type.equals('Operationnel') && sessionScope.sessionUtilisateur.role.equals('maintenance')}">
					<c:import url="vues/menu/menuRespoMaintenance.jsp"></c:import>
				</c:when>
			</c:choose>

		</div>

		<div class="col-md">
		
			<c:if test="${path!=null && !empty path}">
				<nav aria-label="breadcrumb" class="m-1 bg-light">
					<ol class="breadcrumb bg-light">
						<c:forEach items="${path.pages}" var="p" varStatus="s">
							<li class="breadcrumb-item ${s.isLast()?'active':''}" 
								<c:if test="${s.isLast()}">aria-current="${p}"</c:if>>
								<a href='<c:url value="${p.link}" />' > ${p.path}</a>
							</li>
						</c:forEach>
						
					</ol>
				</nav>
			
			</c:if>
			
			<c:import url="${vue}"></c:import>
		</div>
	</div>



</body>
</html>