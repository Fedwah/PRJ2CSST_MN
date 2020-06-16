<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid p-3">
	<div class="">
	    <h3 class="display-4" style='color: #3E703D;font-size:40px;font-style:gras;'>Liste des utilisateur</h3>
		<!--  <h1 class="display-4 text-success">Liste des utilisateurs</h1>-->
		<nav class="nav justify-content-end mb-2">
			<a class="btn btn-outline-success"
			 href='<c:url value="/createUser"/>'>Ajouter un utilisateur</a>
						 
		</nav>	 
	    </div>
	        <form class="" method ="post" action="Utilisateurs">
			<div class="form-row">
				<div class="form-group mt-3 col-md-4">
					<input type="text" class="form-control" id="search" name="search"
						placeholder="Rechercher par nom"  value="<c:out value="${param.search}"/>">
				</div>
			   </div>
		     </form>  
	
	<table class="table">
		<thead>
			<tr style='color: #3E703D;' >
			    <th scope="col">Id</th>
				<th scope="col">Nom</th>
				<th scope="col">Prenom</th>
				<th scope="col">Nom d'utilisateur</th>
				<th scope="col">Type</th>
			    <th scope="col">Role</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${Users}" var="usr">
				<tr > 
				    <td>${usr.id}</td>
					<td>${usr.nom}</td>
					<td>${usr.prenom}</td>
					<td>${usr.nomUtilisateur}</td>
					<td>${usr.type}</td>
					<td>${usr.role}</td>
					<!--  <td>
						<a class="btn btn-outline-primary" href='<c:url value="/EditUser/${usr.id}"/>'>Editer</a> 
						<a class="btn btn-outline-danger"   href='<c:url value="/SuppUser/${usr.id}"/>' >Supprimer</a>  
					</td>-->
					<td>
					<btn:edit value="/EditUser/${usr.id}" /> 
					<btn:remove value="/SuppUser/${usr.id}" /> 
					</td>	 
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>