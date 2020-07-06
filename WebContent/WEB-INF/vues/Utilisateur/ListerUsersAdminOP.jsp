 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid ">
	<div class="">
	    <h3 class="display" style='color: #3E703D;font-size:40px;font-style:gras;'>Liste des utilisateurs</h3>
		<!--  <h1 class="display-4 text-success">Liste des utilisateurs</h1>-->
		<nav class="nav justify-content-end mb-2">
		<a class="btn btn-light rounded-circle"
					style='display: inline-block; height: 40px; width: 40px;position:relative; top:-3px; left:0px'
					href='<c:url value="/createUser"/>'> <img width="50px"
					height="50px" style='position: relative; top: -10px; left: -17px;'
					src="<c:url value='/public/img/icon/add_green_nobackground.png'/>" />
				</a>
			 
			 	   
		</nav>	 
	              </div>
	            
	            
	      
	
	<table class="table">
		<thead>
			<tr style='color: #3E703D;' >
			    <th scope="col" hidden = TRUE>Id</th>
				<th scope="col">Nom</th>
				<th scope="col">Prenom</th>
				<th scope="col">Nom d'utilisateur</th>
				<th scope="col">Poste</th>
			     
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${Users}" var="usr">
				<tr > 
				    <td  hidden = TRUE>${usr.id}</td>
					<td>${usr.nom}</td>
					<td>${usr.prenom}</td>
					<td>${usr.nomUtilisateur}</td>
					<td>${usr.poste}</td>
				 
					 
					<td>
					<btn:edit value="/EditUser/${usr.id}" /> 
					<btn:remove value="/SuppUser/${usr.id}" /> 
					</td>	 
				</tr>
			</c:forEach>
		</tbody>
	</table>
 
</div>
 

 