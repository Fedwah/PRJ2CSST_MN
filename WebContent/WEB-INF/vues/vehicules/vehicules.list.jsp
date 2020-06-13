<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn" %>

<div class="container-fluid p-3">
	<div class="">
		<h1 class="display-4 text-success">${title}</h1>
		<nav class="nav justify-content-end mb-2">
			<a class="btn btn-outline-success"
				href='<c:url value="/Vehicules/edit/"/>'>Ajouter un vehicule</a> 
				<a
				class="nav-link " href="<c:url value="/Vehicules/Etats" />">Liste
				des etats de vehicule</a> <a class="nav-link"
				href="<c:url value="/Marques" />">Liste des marques</a> <a
				class="nav-link" href="<c:url value="/Vehicules/Categories" />">Liste
				des categories de vehicule</a>
		</nav>
	</div>
	<div>
		<form class="" method="post" action="">
			<div class="form-row">
				<div class="form-group mt-3 col-md-4">
					<input type="text" class="form-control" id="search" name="search"
						 ${empty search ?'placeholder="search"':'value='}"${search}">
				</div>
				<div class="form-group mt-3 col-md-3">
					<select id="field" class="form-control" required="required"
						name="field">
						
						<c:forEach items="${filtres}" var="f">
							<option value="${f.key}" ${f.key==field ?'selected':''}>${f.value}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="form-group mt-3 col-md-2">
					<select id="filtre_regions" class="form-control"
						name="filtre_regions">
						<option value="">Toutes les regions</option>
						<c:forEach items="${filtre_regions}" var="r">
							<option value="r.titre">${r.titre}</option>
						</c:forEach>
					</select>
				</div>
				<button type="submit" class="btn btn-light rounded-circle m-auto">
					<img height="20px" width="20px"
						src='<c:url value="/public/img/icon/search_green_nobackground.png"/>' />
				</button>
			</div>
		</form>
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
						src='<c:url value="/Images/${vehicule.marque.image.titre}" />'
						width="75" height="75" /></th>
					<td>${vehicule.modele.titre}</td>
					<td>${vehicule.num_immatriculation}</td>
					<td>${vehicule.date_achat}</td>
					<td>${vehicule.etat.titre}</td>
					<td>
						<btn:edit value="/Vehicules/edit/${vehicule.num_immatriculation}" />
						<btn:remove value="/Vehicules/remove/${vehicule.num_immatriculation}"/>
						<btn:affectation value="/Vehicules/Affectations/${vehicule.num_immatriculation}" text="Conducteur" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>