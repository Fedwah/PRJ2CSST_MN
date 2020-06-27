<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav style='background-color: #3E703D;'>
	<a style='color: white;display: inline-block;' href="#"> <!-- mettre logo ici --> 
		<img style='display: inline-block;'
		src='<c:url value="/public/img/logos/mn_greenBackground.png" />' width="60"
		height="60" alt="" loading="lazy">
		<h3 style='display: inline-block;position: relative; top: 10px; left: 10px;'>Maintenance nationale</h3>
		   <div style="float:right; width:150px; height:auto;">
		<a class="btn btn-outline-success"   style='color:  #FFFFFF;'
			 href='<c:url value="/Deconnexion"/>'>Deconnexion</a>
			 </div>
	</a>
</nav>
