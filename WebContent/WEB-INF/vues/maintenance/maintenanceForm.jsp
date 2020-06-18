<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
						value="<c:out value="${Vehicule.matricule_interne}" 
						/>"
						
					>
					<c:forEach items='${erreurs["v"]}' var="errl">
								<span class="badge badge-pill badge-danger">${errl}</span>
					</c:forEach>
					</div>

						<label for="marque">Piece</label>
						<div class="input-group mb-3">
							<select id="marque" class="form-control" required="required"
								name="marque">
								<option>Aucune</option>
								<c:forEach items="${piece}" var="p">
									
									<option> ${p.pieceName}</option>
								</c:forEach>
							</select>
							<div class="input-group-append">
								<a class="btn btn-outline-success"
									href='<c:url value="/pieces/edit/"/>'>+</a>
							</div>
						</div>
						<label for="marque">Niveaux de maintenance</label>
						<div class="input-group mb-3">
							<select id="marque" class="form-control" required="required" name="niveau">
								<c:forEach items="${niveaux}" var="n">
									<option value = "${n.idNiv}"> ${n.niveau}</option>
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
							value="<c:out value="${maintenance.startDate}"/>">
						<div>
							<c:forEach items='${erreurs["startDate"]}' var="errD">
								<span class="badge badge-pill badge-danger">${errD}</span>
							</c:forEach>
						</div>
				</div>
		</div>

		<button type="submit" class="btn btn-primary">Valider</button>
		<a type="reset" class="btn btn-danger" href='<c:url value="/drivers"/>'>Annuler</a>
	</form>
</div>