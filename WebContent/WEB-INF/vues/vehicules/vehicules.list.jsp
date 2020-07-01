<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/link"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>


<div class="container-fluid ">
	<div class="">
		<h1 class="page-title">${title}</h1>
	</div>
	<div class="row p-1">
		<form class="col-md-8" method="post" action="">
			<div class="form-row">

				<form:input name="search" col="col-md-3" type="text"
					value="${search}" placeHolder="chercher"></form:input>

				<form:select name="field" fieldToTest="key" col="col-md-3"
					fieldToPrint="value" selectedValue="${field}" map="${filtres}"
					fieldID="key"></form:select>



				<btn:search name="search" />
			</div>

		</form>
		<div class="col-md-3">

			<nav class="nav justify-content-end mb-2">
				<l:link label="Les Categories de vehicule"
					value="/Vehicules/Categories"></l:link>
			</nav>
		</div>

		<div class="col-md-1 ">
			<btn:add value="/Vehicules/edit/"></btn:add>
		</div>

	</div>
	<table class="table">
		<thead>
			<tr class="text-color">
				<th scope="col">Marque</th>
				<th scope="col">Modele</th>
				<th scope="col">Code</th>
				<th scope="col">Date d'achat</th>
				<th scope="col">Etat</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${vehicules}" var="vehicule">
				<tr>
					<th scope="row"><img class="img-fluid"
						src='<c:url value="/Images/${vehicule.marque.image.titre}" />'
						width="75" height="75" /></th>
					<td>${vehicule.modele.titre}</td>
					<td>${vehicule.matricule_interne}${empty vehicule.matricule_externe?"":"/"}
						${vehicule.matricule_externe}</td>
					<td>${vehicule.date_achat}</td>
					<td>${vehicule.etat.label}</td>
					<td align="right"><btn:btn type="primary"
							value="/Vehicules/${vehicule.matricule_interne}" text="Détail" />
						<btn:edit value="/Vehicules/edit/${vehicule.matricule_interne}" />
						<btn:remove
							value="/Vehicules/remove/${vehicule.matricule_interne}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>