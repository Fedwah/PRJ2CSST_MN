<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form class=" " method="post" action="<c:out value=""/>" >
<div class="container-fluid p-3">
	<div class="">
		<h3 class="display-4" style='color: #3E703D;font-size:40px;font-style:gras;'>Liste des régions</h3>
		<nav class="nav justify-content-end mb-2">
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
				<option value="codeReg"  ${by=='id'?"selected":""}  >Code</option>
				<option value="adress"  ${by=='reference'?"selected":""}  >Adresse</option>
				<option value="user"  ${by=='user'?"selected":""}  >Responsable</option>
		</select>
		<button type="submit" class="btn btn-light rounded-circle" name="search"
		style='display: inline-block;height:40px; width:40px;position: relative; top: -3px; left: 0px;'>
			<img height="40px" width="40px" style='position: relative; top: -6px; left: -11px;' src='<c:url value="/public/img/icon/search_green_nobackground.png"/>' />
		</button>
		</div>
		<div class="col-md-6" align="right">
		<a class="btn btn-light rounded-circle" style='display: inline-block;height:40px; width:40px;'
				href='<c:url value="/regions/edit/"/>'>
				<img width="50px" height="50px" style='position: relative; top: -10px; left: -17px;' 
				src="<c:url value='/public/img/icon/add_green_nobackground.png'/>" />
		</a>
		</div>
		</nav>
	</div>
	
	<table class="table">
		<thead>
			<tr style='color: #3E703D;'>
				<th scope="col">Code</th>
				<th scope="col">Adresse</th>
				<th scope="col">Responsable</th>
				<th scope="col">Nombre d'unité</th>				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${region}" var="region">
				<tr>
				    <td>${region.codeReg}</td>
					<td>${region.adress}</td>
					<td></td>
					<td>${region.unites.size()}</td>
					<td align = "right">
						<a class="btn btn-outline-success" href='<c:url value="/regions/${region.codeReg}"/>'>Unités</a>
						<a class="btn btn-outline-primary" href='<c:url value="/regions/edit/${region.codeReg}"/>'>
							<img width="20px" height="20px" src="<c:url value='/public/img/icon/edit_green.png'/>" />
						</a>
						<a class="btn btn-outline-danger" href='<c:url value="/regions/remove/${region.codeReg}"/>'>
							<img width="20px" height="20px" src="<c:url value='/public/img/icon/delete_green.png'/>" />
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form>