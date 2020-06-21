<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/form" prefix="form"%>

<form class="p-4 needs-validation " method="post"
		action="<c:out value=""/>" >
<div class="container-fluid p-3">
	<div class="">
		<h3 class="display-4" style='color: #3E703D;font-size:40px;font-style:gras;'>Liste des conducteurs</h3>
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
				<option value="lastN"  ${by=='lastN'?"selected":""}  >Nom</option>
				<option value="firstN"  ${by=='firstN'?"selected":""}  >Prenom</option>
				<option value="recruitDate"  ${by=='recruitDate'?"selected":""}  >Date de recrutement</option>
				<option value="unite.codeUN"  ${by=='unite.codeUN'?"selected":""}  >Unité</option>
		</select>
		<button type="submit" class="btn btn-light rounded-circle" name="search"
		style='display: inline-block;height:40px; width:40px;position: relative; top: -3px; left: 0px;'>
			<img height="40px" width="40px" style='position: relative; top: -6px; left: -11px;' src='<c:url value="/public/img/icon/search_green_nobackground.png"/>' />
		</button>
		</div>
		<div class="col-md-6" align="right">
		<select class="form-control col-md-4" style='display: inline-block;' name="date">
				<option>Date croissante</option>
				<option>Date décroissante</option>
		</select>
		<select class="form-control col-md-4" style='display: inline-block;' name="reg">
				<option>Tous les regions</option>
				<c:forEach items="${region}" var="r">
					<option ${selectedR==r.codeReg?"selected":""}> ${r.codeReg}</option>
				</c:forEach>
		</select>
		<button type="submit" class="form-control col-md-2 btn-outline-success" style='display: inline-block;' name="filter" >filtrer</button>
		<a class="btn btn-light rounded-circle" style='display: inline-block;height:40px; width:40px;'
				href='<c:url value="/drivers/edit/"/>'>
				<img width="50px" height="50px" style='position: relative; top: -10px; left: -17px;' src="<c:url value='/public/img/icon/add_green_nobackground.png'/>" />
		</a>
		</div>
		</nav>
	</div>
	
	<table class="table">
		<thead>
			<tr style='color: #3E703D;'>
				<th scope="col">Profile</th>
				<th scope="col">Nom</th>
				<th scope="col">Prenom</th>
				<th scope="col">Date de recrutement</th>
				<th scope="col">Unité</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${drivers}" var="dr">
				<tr>
					<th scope="row">
						<c:choose>
    						<c:when test="${dr.photo != null}">
       							<img class="img-fluid" src='<c:url value="/Images/${dr.photo.titre}" />' width="60" height="60"
       						
       							 />
    						</c:when>
    						<c:otherwise>
       							<img class="img-fluid" src='<c:url value="/public/img/driver_white_greenBackground.png" />' 
       							
       							width="60" height="60" 
       							/>		
    						</c:otherwise>
						</c:choose>
					
					</th>
					<td>${dr.lastN}</td>
					<td>${dr.firstN}</td>
					<td>${dr.recruitDate}</td>
					<td>${dr.unite.codeUN}</td>
					<td></td>
					
					<td align="right">
						<c:choose>
							<c:when test="${vehicule!=null && !empty vehicule}">
								<button type="submit" name="affecter" class="btn btn-primary">Affecter comme conducteur</button>
								<form:input name="driver" type="hidden" value="${dr.IDdriver}"/>
								<form:input name="vehicule" type="hidden" value="${vehicule }"/>
								<!-- Ajouter par @Syphax pour l'affectatation -->
							</c:when>
							<c:otherwise>
								<a class="btn btn-outline-primary" href='<c:url value="/drivers/edit/${dr.IDdriver}"/>'>
									<img width="20px" height="20px" src="<c:url value='/public/img/icon/edit_green.png'/>" />
								</a>
								<a class="btn btn-outline-danger" href='<c:url value="/drivers/remove/${dr.IDdriver}"/>'>
									<img width="20px" height="20px" src="<c:url value='/public/img/icon/delete_green.png'/>" />
								</a>
							</c:otherwise>
						</c:choose>
						
						
						
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
</form>