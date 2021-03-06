<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>
<%@taglib prefix="img" tagdir="/WEB-INF/tags/img"%>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/menu"%>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/link"%>

<div class="container-fluid p-3">
	
   
  <c:forEach items="${main}" var="maintenance" >  
	<div class="card mt-3"  >
		<div class="row no-gutters flex-nowrap">
			<div class="cold-md-8 ">
				<div class="card-body ">
					
					<div class="row">
					<div class="col-md-6" >			
					<h3 class="card-title text-color">					
					${maintenance.startDate}
					</h3>
					</div>
					<div class="col-md-6 " align="right">
					<c:choose>
						<c:when test="${cal.getEtat(maintenance).equals('en cours')}">
						<a href='<c:url value="/maintenance/edit/${maintenance.idMaintenance}"/>' 
						name="end" class="btn" style='background-color: #3E703D;color:white;' >
						terminer</a>
						</c:when>
						<c:otherwise>
						<a class="btn btn-outline-primary" href='<c:url value="/maintenance/edit/${maintenance.idMaintenance}"/>'>
						<img width="20px" height="20px" src="<c:url value='/public/img/icon/edit_green.png'/>" />
						</a>						
						</c:otherwise>
					</c:choose>	
					
					<btn:remove value="/maintenance/remove/${maintenance.idMaintenance}" />
					</div>
					</div>
					<div >
					<h3 class="card-title text-color">
					${maintenance.v.marque.titre}-${maintenance.v.modele.titre}</h3>
					</div>
					<div class="row">
						<div class="col-md-4 " with="40%">
							<h5 class="d-inline">Matricule interne :</h5>
							<a  href='<c:url value="/Vehicules/${maintenance.v.matricule_interne}"/>'>
							${ maintenance.v.matricule_interne}
							</a>
						</div>

						<div class="col-md-4" with="30%">
							<h5 class="d-inline">Matricule externe :</h5>${ maintenance.v.matricule_externe}
						</div>


						<div class="col-md-4" with="30%">
							<h5 class="d-inline">Categorie :</h5>
							${maintenance.v.categorie.titre }
						</div>
						
						<div class="col-md-4" with="30%">
							<h5 class="d-inline">Etat :</h5>
							${cal.getEtat(maintenance)}

						</div>
						<div class="col-md-4" with="30%">
							<c:choose>
								<c:when test="${cal.getEtat(maintenance).equals('terminée')}">
								<h5 class="d-inline">Date de fin :</h5>
								${maintenance.endDate}
								
								</c:when>
							</c:choose>						
							
						</div>
						<div class="col-md-4"></div>
						<div class="col-md-4" with="40%">
							<h5 class="d-inline">Niveau : </h5>
							${maintenance.niv.label }
						</div>
						<div class="col-md-12" with="40%">
							<h5 class="d-inline">Instructions : </h5>
						
						</div>
								
								<table class="table">
								<thead>
								<tr style='color: #3E703D;' >
								<th scope="col">#</th>
								<th scope="col">Piece</th>
								<th scope="col">Defaillance</th>
								<th scope="col">Cause</th>
								<th scope="col">Effet</th>
								<th scope="col">Demarche</th>
								</tr>
								</thead>
								<tbody>
								  
								<c:forEach items="${maintenance.instructions}" var="inst">
								<tr>
				    			<td>${inst.id}</td>
				    			<td>${inst.piece.refrence}</td>
								<td>${inst.defaillance.defaillance}</td>
								<td>${inst.cause.cause}</td>
								<td>${inst.effet.effet}</td>
								<td>${inst.demarche_resolution}</td>
								</tr>
								</c:forEach> 
								</tbody>
								</table>
					
					</div>
				</div>
			</div>
		</div>
	</div>
	
</c:forEach>
</div>