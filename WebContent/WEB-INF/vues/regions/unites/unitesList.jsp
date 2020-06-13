<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid p-3">
	<div class="">
		<h1 class="display-4 text-success">Liste des unites</h1>
		<nav class="nav justify-content-end mb-2">
			<a class="btn btn-outline-success"
						href='<c:url value="/regions/unite/add/${code}"/>'>Ajouter une nouvelle unite</a>

		</nav>
	</div>
	
	<table class="table">
		<thead>
			<tr class="text-success">
				<th scope="col">Code</th>
				<th scope="col">adresse</th>			
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${unite}" var="unite">
				<tr>
				    <td>${unite.codeUN}</td>
					<td>${unite.adress}</td>
					<td>
						<a class="btn btn-outline-primary" href='<c:url value="/regions/unites/edit/${unite.codeUN}"/>'>Editer</a>
						<a class="btn btn-outline-danger" href='<c:url value="/regions/unites/remove/${unite.codeUN}"/>'>Supprimer</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>