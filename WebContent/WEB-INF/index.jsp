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
					Menu respo parc
					<c:import url="vues/menu/menuRespoParc.jsp"></c:import>
					Menu respo maintenance
					<c:import url="vues/menu/menuRespoMaintenance.jsp"></c:import>
					Menu respo Regional
					<c:import url="vues/menu/menuRegional.jsp"></c:import>
				</c:otherwise>
			</c:choose>

		</div>

		<div class="col-md">
		
			<c:if test="${path!=null && !empty path}">
				<div class="border-bottom p-2  rounded-0 mt-1">
					<c:if test="${path.tooLong}"> ... </c:if>
					<c:forEach items="${path.pages}" var="p">
						/<a href='<c:url value="${p.link}" />' > ${p.path}</a>
					</c:forEach>
					
				</div>
			</c:if>
			
			<c:import url="${vue}"></c:import>
		</div>
	</div>



</body>
</html>