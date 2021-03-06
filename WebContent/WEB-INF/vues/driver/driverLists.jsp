<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/form" prefix="form"%>
<script>
function goTo()
{
  document.frm.submit()
}
</script>
<form class="p-4 needs-validation " name="frm" method="post"
		action="<c:out value=""/>" >
<div class="container-fluid ">
	<div class="">
		<h3 class="display" style='color: #3E703D;font-size:40px;font-style:gras;'>Liste des conducteurs</h3>
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
				<c:if test="${ empty sessionScope.sessionUtilisateur.codeun}">
					<option value="unite.codeUN"  ${by=='unite.codeUN'?"selected":""}  >Unité</option>
				</c:if>
				
		</select>
		
		<button type="submit" class="btn btn-light rounded-circle" name="search"
		style='display: inline-block;height:40px; width:40px;position: relative; top: -3px; left: 0px;'>
			<img height="40px" width="40px" style='position: relative; top: -6px; left: -11px;' src='<c:url value="/public/img/icon/search_green_nobackground.png"/>' />
		</button>
		</div>
		<div class="col-md-6" align="right">
		<select class="form-control col-md-5" style='display: inline-block;' name="date" onchange="goTo()">
				<option ${order=="ASC"?"selected":""} value="ASC">Date croissante</option>
				<option ${order=="DESC"?"selected":""} value="DESC">Date décroissante</option>
		</select>
<!--  		<button type="submit" class="form-control col-md-2 btn-outline-success" style='display: inline-block;' name="filter" >filtrer</button> -->
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
				<th scope="col">Affectation</th>
				<th scope="col">Operations</th>
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
					<td>${dr.affectation.car.matricule}</td>
					
					<td align="right">
						<c:choose>
							<c:when test="${vehicule!=null && !empty vehicule}">
								<form:hidden name="affecter" label="Affecter comme conducteur" btn_type=" btn-primary"
									values="${dr.IDdriver},${vehicule }" values_names="driver,vehicule"
								>
								</form:hidden>
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
	<c:if test="${sessionScope.exception }">

		<script>
		$(document).ready(function (){
    	swal({
  		title: "Ce conducteur est lié à d'autres éléments",
  		text: "vous ne pouvez pas le supprimer",
  		icon: "warning",
  		dangerMode: true,
		})
		});
		</script>
		<%session.removeAttribute("exception"); %>
	</c:if>
</div>
</form>