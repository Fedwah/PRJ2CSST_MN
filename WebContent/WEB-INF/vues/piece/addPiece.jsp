<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<form class="p-4 needs-validation " method="post"
		action="<c:out value=""/>" novalidate enctype="multipart/form-data">

		<!-- ${ erreurs } <!-- pour tester -->

		<div class="form-row">
			<div class="form-group col-md-9">
				<div class="form-group ">
					<label for="code">Code de la piece</label> 
					<input
						type="text"
						class='form-control'/>				
				</div>
				<div class="form-group ">
					<label for="nom">Nom de la piece</label> 
					<input
						type="text"
						class='form-control'/>				
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="marque">Marque</label>
						<div class="input-group mb-3">
							<select id="marque" class="form-control" required="required"
								name="marque">
								<c:forEach items="${marques}" var="marque">
									<option ${piece.marque.titre==marque.titre?"selected":""}>${marque.titre}</option>
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
								name="marque">
								<c:forEach items="${marques}" var="marque">
									<option ${piece.modele.titre==modele.titre?"selected":""}>${modele.titre}</option>
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
		</div>
		<button type="submit" class="btn btn-primary">Valider</button>
		<button type="reset" class="btn btn-danger">Annuler</button>
	</form>
</div>