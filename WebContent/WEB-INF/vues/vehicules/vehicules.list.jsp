<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid p-3">
	<div class="">
		<h1 class="display-4 text-success">Liste des vehicules</h1>
		<nav class="nav justify-content-end mb-2">
			<a class="btn btn-outline-success"
						href='<c:url value="/Vehicules/add"/>'>Ajouter un vehicule</a>
			<a class="nav-link " href="<c:url value="/Vehicules/Etats" />">Liste des etats de vehicule</a>
			<a class="nav-link"href="<c:url value="/Marques" />">Liste des marques</a> 	
		</nav>
	</div>
	
	<table class="table">
		<thead>
			<tr class="text-success">
				<th scope="col">Marque</th>
				<th scope="col">Modele</th>
				<th scope="col">Code</th>
				<th scope="col">Date d'achat</th>
				<th scope="col">Etat</th>
				<th scope="col">Operations</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${vehicules}" var="vehicule">
				<tr>
					<th scope="row"><img class="img-fluid"
						src='<c:url value="/Images/${vehicule.marque.image.titre}" />' width="75" height="75" />
					</th>
					<td>${vehicule.modele.titre}</td>
					<td>${vehicule.num_immatriculation}</td>
					<td>${vehicule.date_achat}</td>
					<td>${vehicule.etat.titre}</td>
					<td>
						<a class="btn btn-outline-primary" href='<c:url value="/Vehicules/edit/${vehicule.num_immatriculation}"/>'>Editer</a>
						<a class="btn btn-outline-danger" href='<c:url value="/Vehicules/remove/${vehicule.num_immatriculation}"/>'>Supprimer</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>