<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib tagdir="/WEB-INF/tags/menu" prefix="m"%>
<div class="nav flex-column  text-center  p-4" >
</div>
<div>
<ul class="nav flex-column  text-center  p-3">
<<<<<<< HEAD
	<li class="nav-item rounded-pill mb-1" style='background-color: #3E703D;'><a class="nav-link text-light" href="<c:url value="/Utilisateurs"/>">Utilisateurs</a></li>
	<li class="nav-item rounded-pill mb-1" style='background-color: #3E703D;'><a class="nav-link text-light " href="<c:url value="/calendrier"/>">Calendrier</a></li>
	<li class="nav-item rounded-pill mb-1" style='background-color: #3E703D;'><a class="nav-link text-light" href="<c:url value="/Vehicules"/>">Vehicules</a></li>
	<li class="nav-item rounded-pill mb-1" style='background-color: #3E703D;'><a class="nav-link text-light" href="<c:url value="/drivers"/>">Conducteurs</a></li>
	<li class="nav-item rounded-pill mb-1" style='background-color: #3E703D;'><a class="nav-link text-light" href="<c:url value="/pieces"/>">Pièces</a></li>
	<li class="nav-item rounded-pill mb-1" style='background-color: #3E703D;'><a class="nav-link text-light" href="#">AMDEC</a></li>
	<li class="nav-item rounded-pill mb-1" style='background-color: #3E703D;'><a class="nav-link text-light" href="#">Guide</a></li>
	<li class="nav-item rounded-pill mb-1" style='background-color: #3E703D;'><a class="nav-link text-light" href="#">Directives</a></li>
=======
	<m:link label="Utilisateurs" value="/Utilisateurs"/>
	<m:link label="Calendrier" value="/calendrier"/>
	<m:link label="Vehicules" value="/Vehicules"/>
	<m:link label="Conducteur" value="/drivers"/>
	<m:link label="Pièces" value="/pieces"/>
	<m:link label="Directives" value="/Utilisateurs"/>
	<m:link label="Guides" value="/Fichiers"/>
>>>>>>> branch 'master' of https://github.com/Fedwah/PRJ2CSST_MN.git
	
</ul>
</div>