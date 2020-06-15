<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid p-3">
	<form class=" " method="post" action="<c:out value=""/>" >
		<h3 class="display-4" style='color: #3E703D;font-size:40px;font-style:gras;'>Liste des pieces</h3>
		<nav class="nav justify-content-end mb-2" width="500px">
		<div class="col-md-6" style='position: relative; top: 0px; left: -15px;'>
		<input
						type="text"
						class='form-control col-md-6'
						name= "word" 
						value="${wordf}"
						placeholder ="chercher"
						style='display: inline-block;'
						
		/>
		<select class="form-control col-md-3" name="type" style='display: inline-block;'>
				<option value="id"  ${by=='id'?"selected":""}  >Code</option>
				<option value="reference"  ${by=='reference'?"selected":""}  >Reference</option>
				<option value="pieceName"  ${by=='pieceName'?"selected":""}  >Nom</option>
		</select>
		<button type="submit" class="btn btn-light rounded-circle" name="search"
		style='display: inline-block;height:40px; width:40px;position: relative; top: -3px; left: 0px;'>
			<img height="40px" width="40px" style='position: relative; top: -6px; left: -11px;' src='<c:url value="/public/img/icon/search_green_nobackground.png"/>' />
		</button>
		</div>
		<div class="col-md-6" align="right">
		<select class="form-control col-md-4" style='display: inline-block;' name="mark">
				<option>Tous les marques</option>
				<c:forEach items="${marques}" var="m">
					<option ${selectedMark==m.titre?"selected":""}> ${m.titre}</option>
				</c:forEach>
		</select>
		<button type="submit" class="form-control col-md-2 btn-outline-success" style='display: inline-block;' name="filter" >filtrer</button>
		<a class="btn btn-light rounded-circle" style='display: inline-block;height:40px; width:40px;'
				href='<c:url value="/pieces/edit/"/>'>
				<img width="50px" height="50px" style='position: relative; top: -10px; left: -17px;' src="<c:url value='/public/img/icon/add_green_nobackground.png'/>" />
		</a>
		</div>
		</nav>
	
	<table class="table">
		<thead>
			<tr style='color: #3E703D;' >
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