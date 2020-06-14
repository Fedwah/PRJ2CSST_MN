<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid p-3">
	<form class=" " method="post" action="<c:out value=""/>" >
		<h3 class="display-4 text-success">Liste des pieces</h3>
		<nav class="nav justify-content-end mb-2" width="500px">
		<input
						type="text"
						class='form-control col-md-3' align="left'
						name= "search" 
						value="<c:out value="${$word}" />"
						placeholder ="chercher"
						
		/>
		<select class="form-control col-md-1" name="type">
				<option>Code</option>
				<option>Reference</option>
				<option>Marque</option>
				<option>Modele</option>
		</select>
		<select class="form-control col-md-2" name="mark">
				<option>Tous les marques</option>
				<c:forEach items="${marques}" var="m">
					<option ${selectedMark==m.titre?"selected":""}> ${m.titre}</option>
				</c:forEach>
		</select>
		<button type="submit" class="btn btn-outline-success" name="filter" >filtrer</button>
		<a class="add"
				href='<c:url value="/pieces/edit/"/>'>
				<img width="40px" height="40px" src="<c:url value='/public/img/icon/add.png'/>" />
		</a>
		</nav>
	
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

					<td align = "right">
						<a class="btn btn-outline-primary" href='<c:url value="/pieces/edit/${piece.id}"/>'>
						<img width="20px" height="20px" src="<c:url value='/public/img/icon/edit_green.png'/>" />
						</a>
						<a class="btn btn-outline-danger" width = "30px" href='<c:url value="/pieces/remove/${piece.id}"/>'>
						<img width="20px" height="20px" src="<c:url value='/public/img/icon/delete_green.png'/>" />
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</form>