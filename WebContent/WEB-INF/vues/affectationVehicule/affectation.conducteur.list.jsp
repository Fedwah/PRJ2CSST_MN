<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid  p-3">
	<div class="row ">
		<div class="col-7">
			<div class="card mb-3 border-${actuelle!=null?'success':'danger'}">
				<div class="row no-gutters" style="min-height: 200px;">
					<div class="cold-md-3">
						<c:choose>
							<c:when test="${actuelle.driver.photo!=null}">
								<div class="card-img img-fluid">
									<img
										src='<c:url value="/Images/${actuelle.driver.photo.titre}" />'
										class="" alt="..." width="150px" height="200px">
								</div>

							</c:when>
							<c:otherwise>
								<div class="card-img img-fluid">
									<img class="" src='<c:url value="/public/img/notfound.png" />'
										width="150px" height="200px" />
								</div>

							</c:otherwise>
						</c:choose>
					</div>
					<div class="col-md-3">
						<c:choose>
							<c:when test="${actuelle.car.photo!=null}">
								<div class="card-img">
									<img class=""
										src='<c:url value="/Images/${actuelle.car.photo.titre}" />'
										width="150px" height="200px" />
								</div>

							</c:when>
							<c:otherwise>
								<div class="card-img">
									<img class="" src='<c:url value="/public/img/notfound.png" />'
										width="150px" height="200px" />
								</div>

							</c:otherwise>
						</c:choose>
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
									<form method="post" action="">
										<form:input name="driver" type="hidden"
											value="${actuelle.driver.IDdriver}" >
										</form:input>
										<input type="submit" value="Mettre fin a l'affectation"
											class="btn btn-sm btn-danger" />
									</form>

								</c:when>
								<c:otherwise>
									<h5 class="card-title">Pas d'affectation</h5>
								</c:otherwise>

							</c:choose>

						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="col-5 ">
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

								<td>
									<btn:edit value="/drivers/edit/${dr.IDdriver}"></btn:edit>
									
									<form method="post" action="">
										<form:input name="driver" type="hidden" value="${dr.IDdriver}"></form:input>
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

	<div class="row p-1">
		<div class="col card ">
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

							<td>
								<btn:edit value="/drivers/edit/${aff.driver.IDdriver}"></btn:edit>
						
								<form method="post" action="">
									<form:input name="driver" type="hidden" value="${aff.driver.IDdriver}"></form:input>
									<form:input name="vehicule" type="hidden" value="${aff.car.num_immatriculation}"></form:input>
									<input type="submit" value="Affecter"
										class="btn btn-sm btn-outline-success" />
								</form> 
								<btn:remove value="/Vehicules/Affectations/remove/${aff.id}"></btn:remove>
							
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>