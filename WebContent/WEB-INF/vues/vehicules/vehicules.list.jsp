<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/link"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>

<div class="container-fluid p-3">
	<div class="">
		<h1 class="display-4 text-success">${title}</h1>
		<nav class="nav justify-content-end mb-2">
			<l:link label="Ajouter un vehicule" value="/Vehicules/edit/"></l:link>
			<l:link label="Liste des etats de vehicule" value="/Vehicules/Etats"></l:link>
			<l:link label="Liste des marques" value="/Marques"></l:link>
			<l:link label="Liste des categories de vehicule" value="/Vehicules/Categories"></l:link>
			
		</nav>
	</div>
	<div>
		<form class="" method="post" action="">
			<div class="form-row">
				
				<form:input  name="search" col="col-md-4" type="text" value="${search}" placeHolder="search"></form:input>
				
				<form:select name="field" fieldToTest="key" col="col-md-3" fieldToPrint="value" selectedValue="${field}" map="${filtres}"  fieldID="key"></form:select>
				
				<!--<form:select name="filtre_regions" fieldToTest="titre" col="col-md-2" fieldToPrint="titre" selectedValue="" fieldID="titre" items="${filtre_regions}"></form:select>
				-->

				<button type="submit" class="btn btn-light rounded-circle mx-auto mb-3">
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
					<td><btn:edit
							value="/Vehicules/edit/${vehicule.num_immatriculation}" /> <btn:remove
							value="/Vehicules/remove/${vehicule.num_immatriculation}" /> <btn:affectation
							value="/Vehicules/Affectations/${vehicule.num_immatriculation}"
							text="Conducteur" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>