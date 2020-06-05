<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid p-3">
	<div class="">
		<h1 class="display-4 text-success">Liste des conducteurs</h1>
		<nav class="nav justify-content-end mb-2">
			<a class="btn btn-outline-success"
						href='<c:url value="/drivers/edit/"/>'>Ajouter un conducteur</a>
		</nav>
	</div>
	
	<table class="table">
		<thead>
			<tr class="text-success">
				<th scope="col">profile</th>
				<th scope="col">Nom</th>
				<th scope="col">Prenom</th>
				<th scope="col">Date de recrutement</th>
				<th scope="col">Unité</th>
				<th scope="col">Operations</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${drivers}" var="dr">
				<tr>
					<th scope="row"><img class="img-fluid"
						src='<c:url value="/Images/${dr.image}" />' width="75" height="75" />
					</th>
					<td>${dr.nom}</td>
					<td>${dr.prenom}</td>
					<td>${dr.recruitDate}</td>
					<td></td>
					
					<td>
						<a class="btn btn-outline-primary" href='<c:url value="/drivers/edit/${dr.id}"/>'>Editer</a>
						<a class="btn btn-outline-danger" href='<c:url value="/drivers/remove/${dr.id}"/>'>Supprimer</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>