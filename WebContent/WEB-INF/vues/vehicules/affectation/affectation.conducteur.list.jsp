<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>
<%@taglib prefix="img" tagdir="/WEB-INF/tags/img"%>
<div class="container-fluid  p-3">
	<div class="row mb-2">
		<div class="col-md-7">
			<div class="card  border-${actuelle!=null?'success':'danger'}">
				<div class="row no-gutters">
					<div class="cold-md-3">
						<img:card-img szie="150px" value="${actuelle.driver.photo}"/>
					</div>
					<div class="col-md-3">
						<img:card-img szie="150px"  value="${actuelle.car.photo}"/>
						
					</div>
					<div class="col-md-6">
						<div class="card-body">
							<c:choose>
								<c:when test="${actuelle!=null}">
									<h5 class="card-title">Affectation actuelle</h5>
									<p class="card-text">${actuelle.driver.lastN}
										${actuelle.driver.firstN}</p>
									<p class="card-text">
										<small class="text-muted">Affecter depuis
											${actuelle.startDate}</small>
									</p>
								</c:when>
								<c:otherwise>
									<h5 class="card-title">Pas d'affectation</h5>
								</c:otherwise>

							</c:choose>

						</div>
					</div>
				</div>
				<div class="row no-gutters p-2">
					<btn:btn class_="col-6 mr-2" type="primary"
						value="/Vehicules/Missions/${actuelle.id}/"
						text="Démarer une mission" outline="${false}" small="${true}"
						disable="${actuelle==null}">
					</btn:btn>


					<form method="post" action="" class="col-6 form-row ">
						<form:input name="driver" type="hidden"
							value="${actuelle.driver.IDdriver}">
						</form:input>
						<input type="submit" value="Mettre fin a l'affectation"
							class="btn btn-sm btn-danger w-100"
							${actuelle==null?'disabled':''} />
					</form>
				</div>
			</div>

		</div>
		<div class="col-md-5">
			<div class="card p-2">
				<h5>Recommandation de conducteur</h5>
				<div class="overflow-auto p-2" style="max-height: 250px;">
					<table class="table table-sm table-bordered "
						style="font-size: 12px;">
						<thead>
							<tr class="text-success">
								<th scope="col">Profile</th>
								<th scope="col">Nom et Prenom</th>
								<th scope="col">Score</th>
								<th scope="col" class="col">Operations</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${drivers}" var="dr">
								<tr>
									<th scope="row"><img class="img-fluid"
										src='<c:url value="/Images/${dr.photo.titre}" />' width="50"
										height="50" /></th>
									<td>${dr.lastN}${dr.firstN}</td>

									<td>mettre des etoiles</td>

									<td><btn:edit value="/drivers/edit/${dr.IDdriver}"></btn:edit>

										<form method="post" action="">
											<form:input name="driver" type="hidden"
												value="${dr.IDdriver}"></form:input>
											<form:input name="vehicule" type="hidden" value="${vehicule}"></form:input>
											<input type="submit" value="Affecter"
												class="btn btn-sm btn-outline-success" />
										</form></td>
								</tr>
							</c:forEach>
						</tbody>

					</table>
				</div>

			</div>

		</div>
	</div>

	<div class="row">
		<div class="col-md-6 p-1">
			<div class="card p-1">
				<h5>Les liste des missions du vehicule</h5>
				<table class="table table-bordered" style="font-size: 12px;">
					<thead>
						<tr class="text-success">
							<th scope="col">Conducteur</th>
							<th scope="col">description</th>
							<th scope="col">Perdiode</th>
							<th scope="col">Operations</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${missions}" var="m">
							<tr>
								<th>${m.affectation.id}</th>
								<td>${m.description}</td>
								<td>${m.dateDebut}-${m.dateFin}</td>
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
		<div class="col-md-6 p-1">
			<div class="card p-1">
				<h5>Historique des conducteur affecté</h5>
				<table class="table table-bordered" style="font-size: 12px;">
					<thead>
						<tr class="text-success">
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
								<th scope="row"><img class="img-fluid"
									src='<c:url value="/Images/${aff.driver.photo.titre}" />'
									width="50" height="50" /></th>
								<td>${aff.driver.lastN}${aff.driver.firstN}</td>
								<td>De ${aff.startDate} à <fmt:formatDate
										value="${aff.endDate}" pattern="yyyy-MM-dd" />
								</td>
								<td>l'unité ici</td>
								<td>mettre des etoiles</td>

								<td><btn:edit value="/drivers/edit/${aff.driver.IDdriver}"></btn:edit>

									<form method="post" action="">
										<form:input name="driver" type="hidden"
											value="${aff.driver.IDdriver}"></form:input>
										<form:input name="vehicule" type="hidden"
											value="${aff.car.matricule_interne}"></form:input>
										<input type="submit" value="Affecter"
											class="btn btn-sm btn-outline-success" />
									</form> <btn:remove value="/Vehicules/Affectations/remove/${aff.id}"></btn:remove>

								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>
</div>

