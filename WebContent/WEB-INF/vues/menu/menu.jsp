<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul class="nav flex-column  text-center  p-3">
	<li class="nav-item rounded-pill bg-success  mb-1"><a class="nav-link text-light" href='<c:url value="/"/>'>Tableau</a>
	</li>
	<li class="nav-item rounded-pill bg-success  mb-1"><a class="nav-link text-light " href="#">Calendrier</a></li>
	<li class="nav-item rounded-pill bg-success  mb-1"><a class="nav-link text-light" href="<c:url value="/Vehicules"/>">Vehicules</a></li>
	<li class="nav-item rounded-pill bg-success  mb-1"><a class="nav-link text-light" href="#">Conducteurs</a></li>
	<li class="nav-item rounded-pill bg-success  mb-1"><a class="nav-link text-light" href="#">Pièces</a></li>
	<li class="nav-item rounded-pill bg-success  mb-1"><a class="nav-link text-light" href="#">Directives</a></li>
	<li class="nav-item rounded-pill bg-success  mb-1"><a class="nav-link text-light" href="#">Guide</a></li>
</ul>