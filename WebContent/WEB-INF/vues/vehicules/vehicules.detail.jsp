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
	
   
    
	<div class="card">
		<div class="row no-gutters flex-nowrap">
			<div class="col-md-3 p-2">
				<img:card-img size="250px" value="${vehicule.photo.titre}" />
			</div>
			<div class="cold-md-8 ">
				<div class="card-body ">
					<h3 class="card-title text-color">${vehicule.marque.titre}-
						${vehicule.modele.titre}</h3>
					<div class="row">
						<div class="col-md-6 ">
							<h5 class="d-inline">Matricule interne :</h5>
							${ vehicule.matricule_interne}
						</div>

						<div class="col-md-6">
							<h5 class="d-inline">Matricule externe :</h5>${ vehicule.matricule_externe}
						</div>


						<div class="col-md-6">
							<h5 class="d-inline">Categorie :</h5>
							${vehicule.categorie.titre }
						</div>
						<div class="col-md-6">
							<h5 class="d-inline">Date d'achat :</h5>
							${vehicule.date_achat}
						</div>
						<div class="col-md-6">
							<h5 class="d-inline">Etat :</h5>
							${vehicule.etat.titre}

						</div>
						<div class="col-md-6">
							<h5 class="d-inline">Km :</h5>
							${vehicule.km }
						</div>
					</div>


				</div>
			</div>
		</div>
	</div>
	<div class="row my-3">
		
		<div class="col-md-8 d-flex align-items-center justify-content-start">
			<h3 class="">Conducteur :</h3>
			<c:choose>
				<c:when test="${affectation!=null}">
		
					 <img:img size="50px" value="${affectation.driver.photo.titre}" class_="border border-primary rounded-circle mx-2" />
		
					 <l:link label='${affectation.driver.lastN} ${affectation.driver.firstN}' value="Vehicules/Affectations/${affectation.id}" />
		
					 <div class="row no-gutters">
					 	<form:hidden  
					 		label="Terminer" 
					 		action="/Vehicules/Affectation/${vehicule.matricule_interne}"  
					 		btn_type="btn-sm btn-danger" name="affecter"  disabled="${mission!=null}">
					 	</form:hidden>
					 	<btn:btn type="primary" value="/Vehicules/Affectation/${vehicule.matricule_interne}" 
							text="Affecter un autre conducteur" outline="${false }" small="${true}" 
							class_="mx-1" disable="${mission!=null}"/>
					 </div>
					 
				</c:when>
				<c:otherwise>
					<btn:btn type="primary" value="/Vehicules/Affectation/${vehicule.matricule_interne}" 
							text="Libre , Affecter un conducteur" outline="${false }" small="${true}"/>
				</c:otherwise>
				
			</c:choose>
			
			
		</div>
		<div class="col d-flex align-items-center">
			<h3 class="d-inline align-middle">Mission :</h3>
			<c:choose>
				<c:when test="${mission!=null}">
					<btn:btn type="danger" value="/Vehicules/Missions/${affectation.id}/${mission.id}" 
						text="En cour, Terminer" outline="${false }" small="${true}"/>
				</c:when>
				<c:when test="${affectation!=null}">
					<btn:btn type="primary" value="/Vehicules/Missions/${affectation.id}/" 
						text="Libre , Démarer un mission" outline="${false }" small="${true}"/>
				</c:when>
				<c:otherwise>
					<btn:btn type="danger" value="" text="Pas d'affectation " disable="${true}" small="${true}"/>
					<span></span>
				</c:otherwise>
			</c:choose>
			
		</div>
	</div>
	<ul class="nav nav-tabs" id="myTab" role="tablist">
			<m:tab-link label="Maintenances" id="maintenances" active="${true}"/>
			<m:tab-link label="Missions" id="missions"/>
			<m:tab-link label="Conducteurs" id="conducteurs"/>
	</ul>
	<div class="tab-content" id="myTabContent">
		
		<div id="maintenances" class="tab-pane fade show active" role="tabpanel" aria-labelledby="maintenances-tab">
			A faire (@fedwa)
		</div>
		<div id="missions" class="tab-pane fade" role="tabpanel" aria-labelledby="missions-tab">
			<div class=" p-2">
				<h5>Les liste des missions du vehicule</h5>
				<table class="table " style="font-size: 12px;">
					<thead>
						<tr class="text-color">
							<th scope="col">Conducteur</th>
							<th scope="col">description</th>
							<th scope="col">Perdiode</th>
							<th scope="col">Distance parcourue(Km)</th>
							<th scope="col">Operations</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${missions}" var="m">
							<tr>
								<th>${m.affectation.driver.lastN} ${m.affectation.driver.firstN}</th>
								<td>${m.description}</td>
								<td>${m.dateDebut}-${m.dateFin}</td>
								<td>${m.distance_parcourue}</td>
								<td>
									<btn:btn type="info" value="/Vehicules/Missions/${m.affectation.id}/${m.id}" 
										text="${m.dateFin==null?'Terminer':'Editer'}"
										outline="${false}]">
									</btn:btn>
									<btn:remove value="/Vehicules/Missions/remove/${m.id}"></btn:remove>
								</td>
							</tr>
						</c:forEach>
						
					</tbody>



				</table>
			</div>
		</div>
		<div id="conducteurs" class="tab-pane fade" role="tabpanel" aria-labelledby="conducteurs-tab">

			<div class="p-2">
				<h5>Historique des conducteurs</h5>
				<table class="table" style="font-size: 12px;">
					<thead>
						<tr class="text-color">
							<th scope="col">Profile</th>
							<th scope="col">Nom et Prenom</th>
							<th scope="col">Periode</th>
							<th scope="col">Unité</th>
							<th scope="col">Score</th>
							<th scope="col">Operations</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${affectations}" var="aff">
							<tr>
								<th scope="row">
								
									<img:img size="50px" value="${aff.driver.photo.titre}"/>
								</th>
								<td>${aff.driver.lastN} ${aff.driver.firstN}</td>
								<td>De ${aff.startDate} à <fmt:formatDate
										value="${aff.endDate}" pattern="yyyy-MM-dd" />
								</td>
								<td>l'unité ici</td>
								<td>mettre des etoiles</td>

								<td>
									
									<div class="row">
										
										<form:hidden name="affecter" label="Réaffecter" btn_type="btn-primary"
												action="/Vehicules/Affectation/${vehicule.matricule_interne}"
												values="${aff.driver.IDdriver},${aff.car.matricule_interne}"
												values_names="driver,vehicule"
										/>
										
							
								  		 <div class="col">
								  		 	<btn:remove value="/Vehicules/Affectations/remove/${aff.id}"></btn:remove>
								  		 </div>
								   		
									</div>
									
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>


		</div>
	</div>
</div>


