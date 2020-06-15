<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>
<div class="container-fluid p-3">
	<div class="">
		<h1 class="display-4 text-success">Liste des utilisateurs</h1>
		<nav class="nav justify-content-end mb-2">
		<a class="btn btn-outline-success"
						href='<c:url value="/createUser"/>'>Ajouter un utilisateur</a>	 	   
		</nav>
		
	</div>
       
	

	   
	  <table class="table">
		<thead>
			<tr class="text-success">
				<th scope="col">Nom</th>
				<th scope="col">Prenom</th>
				<th scope="col">Nom d'utilisateur</th>
			    <th scope="col">Type</th>
			    <th scope="col">Role</th>
			</tr>
		</thead>
		<tbody>
		 <c:forEach items="${user}" var="usr">
				<tr>
					<td>${usr.nom}</td>
					<td>${usr.prenom}</td>
					<td>${usr.nomUtilisateur}</td>
					<td>${usr.type}</td>
					<td>${usr.role}</td>
				 
					<td>
					<btn:edit value="/EditUser/${usr.id}" /> 
					<btn:remove value="/SuppUser/${usr.id}" /> 
					</td>
				</tr>
		</c:forEach>
		</tbody>
	</table>  

</div>