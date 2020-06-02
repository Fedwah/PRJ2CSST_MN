<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<form class="p-4 needs-validation " method="post"
		action="<c:out value="add"/>" >

		<!-- ${ erreurs } <!-- pour tester -->

		<div class="form-row">
			<div class="form-group col-md-9">
				<div class="form-group ">
					<label for="code">Code de la piece</label> 
					<input
						type="text"
						class='form-control' 
						name= "codepiece" />				
				</div>
				<div class="form-group ">
					<label for="nompiece">Nom de la piece</label> 
					<input
						type="text"
						class='form-control'
						name= "nom" />				
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="marque">Marque</label>
						<div class="input-group mb-3">
							<select id="marque" class="form-control" required="required"
								name="marque">
								<c:forEach items="${marques}" var="m">
									<option> ${m.titre}</option>
								</c:forEach>
							</select>
							<div class="input-group-append">
								<a class="btn btn-outline-success"
									href='<c:url value="/Marques/add"/>'>Ajouter</a>
							</div>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label for="marque">Modele</label>
						<div class="input-group mb-3">
							<select id="marque" class="form-control" required="required"
								name="modele">
								<c:forEach items="${modeles}" var="modele">
									<option>${modele.titre}</option>
								</c:forEach>
							</select>
							<div class="input-group-append">
								<a class="btn btn-outline-success"
									href='<c:url value="/Marques/add"/>'>Ajouter</a>
							</div>
						</div>
					</div>

				</div>
			</div>			
		</div>
		
		<button type="submit" class="btn btn-primary">Valider</button>
		<a type="reset" class="btn btn-danger" href='<c:url value="/pieces"/>'>Annuler</a>
	</form>
</div>