<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid p-3">
	<div class="">
		<h1 class="display-4 text-success">Liste des pieces</h1>
		<nav class="nav justify-content-end mb-2">
			<a class="btn btn-outline-success"
						href='<c:url value="/pieces/edit/"/>'>Ajouter une nouvelle piece</a>

		</nav>
	</div>
	
	<table class="table">
		<thead>
			<tr class="text-success">
				<th scope="col">Code</th>
				<th scope="col">Reference</th>
				<th scope="col">Nom</th>
				<th scope="col">Marque</th>
				<th scope="col">Modele</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pieces}" var="piece">
				<tr>
				    <td>${piece.id}</td>
				    <td>${piece.reference}</td>
					<td>${piece.pieceName}</td>
					<td>${piece.mark.titre}</td>
					<td>${piece.modal.titre}</td>
					<td></td>
					<td>
						<a class="btn btn-outline-primary" href='<c:url value="/pieces/edit/${piece.id}"/>'>
						<img width="15px" height="15px" src="<c:url value='/public/img/icon/edit_green.png'/>" />
						</a>
						<a class="btn btn-outline-danger" href='<c:url value="/pieces/remove/${piece.id}"/>'>
						<img width="15px" height="15px" src="<c:url value='/public/img/icon/delete_green.png'/>" />
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>