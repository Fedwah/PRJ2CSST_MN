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
				<img:card-img size="150px" value="${vehicule.photo.titre}" />
			</div>
			<div class="cold-md-8 ">
				<div class="card-body ">
					<h3 class="card-title text-color">
						${vehicule.marque.titre}- ${vehicule.modele.titre} <span
							class="badge badge-primary">${vehicule.etat.label}</span>
					</h3>
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
							${vehicule.etat.label}

						</div>
						<div class="col-md-6">
							<h5 class="d-inline">Km :</h5>
							${vehicule.km }
						</div>

						<div class="col-md-6">
							<h5 class="d-inline">Structure de ratachement :</h5>
							${vehicule.unite.codeUN}
						</div>
					</div>


				</div>
			</div>
		</div>
	</div>
	<div class="row my-3">

		<div class="col-md-8 d-flex align-items-center">
			<h3 class="">Conducteur :</h3>
			<c:choose>
				<c:when test="${affectation!=null}">
					<div>
						<img:img size="40px" value="${affectation.driver.photo.titre}"
							class_="border border-primary rounded-circle mx-1" />
					</div>



					<l:link
						label='${affectation.driver.lastN} ${affectation.driver.firstN}'
						value="Vehicules/Affectations/${affectation.id}" />

					<div class="row no-gutters">
						<form:hidden label="Terminer"
							action="/Vehicules/Affectation/${vehicule.matricule_interne}"
							btn_type="btn-sm btn-danger" name="affecter"
							disabled="${mission!=null}">
						</form:hidden>
						<btn:btn type="primary"
							value="/Vehicules/Affectation/${vehicule.matricule_interne}"
							text="Affecter un autre conducteur" outline="${false }"
							small="${true}" class_="mx-1" disable="${mission!=null}" />
					</div>

				</c:when>
				<c:otherwise>
					<btn:btn type="primary"
						value="/Vehicules/Affectation/${vehicule.matricule_interne}"
						text="Libre , Affecter un conducteur" outline="${false }"
						small="${true}" />
				</c:otherwise>

			</c:choose>


		</div>
		<div class="col d-flex align-items-center">
			<h3 class="d-inline align-middle">Mission :</h3>

			<c:choose>
				<c:when test="${mission!=null}">
					<btn:btn type="danger"
						value="/Vehicules/Missions/${affectation.id}/${mission.id}"
						text="En cour, Terminer" outline="${false }" small="${true}" />
				</c:when>
				<c:when test="${affectation!=null && vehicule.etat.ordinal() == 0}">
					<btn:btn type="primary"
						value="/Vehicules/Missions/${affectation.id}/"
						text="Libre , Démarer une mission" outline="${false }"
						small="${true}" />
				</c:when>
				<c:when test="${affectation==null }">
					<btn:btn type="danger" value="" text="Pas d'affectation"
						disable="${true}" small="${true}" />
				</c:when>
				<c:otherwise>
					<btn:btn type="danger" value="" text="Vehicule non diponible"
						disable="${true}" small="${true}" />
					<span></span>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<m:tab-link label="Maintenances" id="maintenances" active="${true}" />
		<m:tab-link label="Missions" id="missions" />
		<m:tab-link label="Conducteurs" id="conducteurs" />
	</ul>
	<div class="tab-content" id="myTabContent">

		<div id="maintenances" class="tab-pane fade show active"
			role="tabpanel" aria-labelledby="maintenances-tab">
			<div class="container-fluid p-3">
				<div align="right">
					<a class="btn btn-light rounded-circle"
						style='display: inline-block; height: 40px; width: 40px;'
						href='<c:url value="/maintenance/add/${ vehicule.matricule_interne}"/>'>
						<img width="50px" height="50px"
						style='position: relative; top: -10px; left: -17px;'
						src="<c:url value='/public/img/icon/add_green_nobackground.png'/>" />
					</a>
				</div>

				<c:forEach items="${main}" var="maintenance">
					<div class="card mt-3">
						<div class="row no-gutters flex-nowrap">
								<div class="card-body m-0 ">

									<div class="row">
										<div class="col-md-10">
											<h3 class="card-title text-color">
												${maintenance.startDate}</h3>
										</div>
										<div class="col-md-2 " align="right">
											<c:choose>
												<c:when
													test="${cal.getEtat(maintenance).equals('en cours')}">
													<a
														href='<c:url value="/maintenance/edit/${maintenance.idMaintenance}"/>'
														class="btn"
														style='background-color: #3E703D; color: white;'>
														terminer</a>
												</c:when>
												<c:otherwise>
													<a class="btn btn-outline-primary"
														href='<c:url value="/maintenance/edit/${maintenance.idMaintenance}"/>'>
														<img width="20px" height="20px"
														src="<c:url value='/public/img/icon/edit_green.png'/>" />
													</a>
												</c:otherwise>
											</c:choose>

											<btn:remove
												value="/maintenance/remove/${maintenance.idMaintenance}" />
										</div>
									</div>
									<div class="row">
										<div class="col-md-4">
											<h5 class="d-inline">Etat :</h5>
											${cal.getEtat(maintenance)}

										</div>
										<div class="col-md-4">
											<c:choose>
												<c:when test="${cal.getEtat(maintenance).equals('terminé')}">
													<h5 class="d-inline">Date de fin :</h5>
								${maintenance.endDate}
								
								</c:when>
											</c:choose>

										</div>
										<div class="col-md-4"></div>
										<div class="col-md-4">
											<h5 class="d-inline">Niveau :</h5>
											${maintenance.niv.label }
										</div>
										<table class="table">
											<thead>
												<tr style='color: #3E703D;'>
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

				</c:forEach>
			</div>
		</div>
		<div id="missions" class="tab-pane fade" role="tabpanel"
			aria-labelledby="missions-tab">
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
								<th>${m.affectation.driver.lastN}
									${m.affectation.driver.firstN}</th>
								<td>${m.description}</td>
								<td>${m.dateDebut}-${m.dateFin}</td>
								<td>${m.distance_parcourue}</td>
								<td><btn:btn type="info"
										value="/Vehicules/Missions/${m.affectation.id}/${m.id}"
										text="${m.dateFin==null?'Terminer':'Editer'}"
										outline="${false}]">
									</btn:btn> <btn:remove value="/Vehicules/Missions/remove/${m.id}"></btn:remove>
								</td>
							</tr>
						</c:forEach>

					</tbody>



				</table>
			</div>
		</div>
		<div id="conducteurs" class="tab-pane fade" role="tabpanel"
			aria-labelledby="conducteurs-tab">

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
								<th scope="row"><img:img size="50px"
										value="${aff.driver.photo.titre}" /></th>
								<td>${aff.driver.lastN}${aff.driver.firstN}</td>
								<td>De ${aff.startDate} à <fmt:formatDate
										value="${aff.endDate}" pattern="yyyy-MM-dd" />
								</td>
								<td>l'unité ici</td>
								<td>mettre des etoiles</td>

								<td>

									<div class="row">

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


