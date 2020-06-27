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
		
</body>
</html>