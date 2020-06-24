<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<div class="container-fluid">
	<form class="p-4 needs-validation " method="post" action="<c:out value=""/>">

		<!-- ${ erreurs } <!-- pour tester -->

		<div class="form-row">
			<div class="form-group col-md-9">
				<div class="form-group ">
					<label for="mat">Matricule Interne</label> 
					<input
						type="text"
						class='form-control '
						id="mat" name="matricule"
						value="<c:out value="${maintenance.v.matricule_interne}" 
					
						/>"
						${disabled_matricule? 'disabled':''}
					>
					<c:forEach items='${erreurs["v"]}' var="errl">
								<span class="badge badge-pill badge-danger">${errl}</span>
					</c:forEach>
					</div>


						<label for="marque">Niveaux de maintenance</label>
						<div class="input-group mb-3">
							<select id="marque" class="form-control" required="required" name="niveau">
								<c:forEach items="${niveaux}" var="n">
									<option ${maintenance.niv.idNiv==n.idNiv?"selected":""} value = "${n.idNiv}"> ${n.niveau}</option>
								</c:forEach>
							</select>
							<div class="input-group-append">
								<a class="btn btn-outline-success"
									href='<c:url value="/maintenance/niveaux"/>'>+</a>
							</div>
						</div>
						

						<label for="recruit">Date de début</label> 
						<input type="date"
							class="form-control ${empty erreurs['startDate']?'':'is-invalid'} "
							id="recruit" required="required" name="recruit"
							value="<c:out value="${maintenance.startDate}"/>"
							${disabled_date? 'disabled':''}
							/>
						<div>
							<c:forEach items='${erreurs["startDate"]}' var="errD">
								<span class="badge badge-pill badge-danger">${errD}</span>
							</c:forEach>
						</div>
						<label for="recruit">Date de fin</label> 
						<input type="date"
							class="form-control ${empty erreurs['endDate']?'':'is-invalid'} "
						    required" name="dateFin"
							value="<c:out value="${endDate}"/>"
						
						/>
						<div>
							<c:forEach items='${erreurs["endDate"]}' var="errD">
								<span class="badge badge-pill badge-danger">${errD}</span>
							</c:forEach>
						</div>
						
							<label for="nb">Nombre de piece de rechange</label>
						<div class="input-group mb-3">
							<input
								type="text"
								class='form-control '
								id="nb" name="nbP"
								value="<c:out value="${maintenance.nbP}" 
								/>">
							
							<div class="input-group-append">
								<button type="submit" class="btn btn-outline-success" name="addPiece">+</button>
							</div>
							
						</div>
						<div>
							<c:forEach items='${erreurs["piece"]}' var="errD">
								<span class="badge badge-pill badge-danger">${errD}</span>
							</c:forEach>
							</div>
						<c:forEach var = "i" begin = "0" end = "${maintenance.nbP-1}">
         					<label for="marque">Nom de la piece</label>
						<div class="input-group mb-3">
							<select id="marque" class="form-control" required="required" name="${i}">
								<c:forEach items="${piece}" var="p">
									<c:choose>
									<c:when test="${maintenance.pieces.size()>i }">
									<option ${p.id==maintenance.pieces.get(i).id?"selected":""} value = "${p.id}"> ${p.pieceName}</option>
									</c:when>
									<c:otherwise>
									<option value = "${p.id}"> ${p.pieceName}</option>
									</c:otherwise>
									</c:choose>
									
								</c:forEach>
							</select>

						</div>
      					</c:forEach>
						</div>
					</div>

		<button type="submit" class="btn btn-primary" name="save">Valider</button>
		<a type="reset" class="btn btn-danger" href='<c:url value="/calendar"/>'>Annuler</a>
	</form>
</div>