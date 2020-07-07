<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/menu" prefix="m"%>


<div class="overflow-hidden mt-1 border-right border-light">
<ul class="nav flex-column pl-1">
	<c:choose>
		<c:when test="${sessionScope.sessionUtilisateur.type.equals('Regional')}"> 
				<m:link label="Tableau"  value="/Regional/Accueil" img="/public/img/menu/analyse_white.png" 
				size="28px" top="0px" left="20px" topI="0px" leftI="10px"
				/>	
		</c:when>
		<c:when test="${sessionScope.sessionUtilisateur.type.equals('Central')}"> 
				<m:link label="Tableau"  value="/Central/Accueil" img="/public/img/menu/analyse_white.png" 
				size="28px" top="0px" left="20px" topI="0px" leftI="10px"
				/>	
				
		</c:when>
		
	</c:choose>
	
	
	<m:link label="Directives" value="/listerDirective" img="/public/img/menu/directive.png" 
	size="28px" top="0px" left="23px" topI="0px" leftI="12px"
	/>

</ul>
</div>