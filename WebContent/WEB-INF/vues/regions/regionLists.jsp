<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid p-3">
	<div class="">
		<h1 class="display-4 text-success">Liste des régions</h1>
		<nav class="nav justify-content-end mb-2">
			<a class="btn btn-outline-success"
						href='<c:url value="/regions/edit/"/>'>Ajouter une nouvelle région</a>

		</nav>
	</div>
	
	<table class="table">
		<thead>
			<tr class="text-success">
				<th scope="col">Code</th>
				<th scope="col">adresse</th>
				<th scope="col">Nombre d'unité</th>				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${region}" var="region">
				<tr>
				    <td>${region.codeReg}</td>
					<td>${region.adress}</td>
					<td>${region.unites.size()}</td>
					<td>
						<a class="btn btn-outline-primary" href='<c:url value="/regions/${region.codeReg}"/>'>Unités</a>
						<a class="btn btn-outline-primary" href='<c:url value="/regions/edit/${region.codeReg}"/>'>Editer</a>
						<a class="btn btn-outline-danger" href='<c:url value="/regions/remove/${region.codeReg}"/>'>Supprimer</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>