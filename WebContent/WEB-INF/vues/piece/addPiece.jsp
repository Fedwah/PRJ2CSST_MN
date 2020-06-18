<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<form class="p-4 needs-validation " method="post"
		action="<c:out value=""/>" >

		<!-- ${ erreurs } <!-- pour tester -->

		<div class="form-row">
			<div class="form-group col-md-9">
				<div class="form-group ">
					<label for="code">Code de la piece</label> 
					<input
						type="text"
						class='form-control' 
						name= "codepiece" 
						value="<c:out value="${piece.id}" />"
						
						/>
					 <c:forEach items='${erreurs["id"]}' var="errl">
								<span class="badge badge-pill badge-danger">${errl}</span>
					</c:forEach>			
				</div>
				<div class="form-group ">
					<label for="nompiece">Reference de la piece</label> 
					<input
						type="text"
						class='form-control'
						name= "ref"
						value="<c:out value="${piece.reference}" />" 
					/>
					<c:forEach items='${erreurs["reference"]}' var="errf">
								<span class="badge badge-pill badge-danger">${errf}</span>
					</c:forEach>				
				</div>
				<div class="form-group ">
					<label for="nompiece">Nom de la piece</label> 
					<input
						type="text"
						class='form-control'
						name= "nom"
						value="<c:out value="${piece.pieceName}" />" 
					/>
					<c:forEach items='${erreurs["pieceName"]}' var="errf">
								<span class="badge badge-pill badge-danger">${errf}</span>
					</c:forEach>				
				</div>
				<div class="form-row">
					<div class="form-group col-md-12">
						<label for="marque">Marque</label>
						<div class="input-group mb-3">
							<select id="marque" class="form-control" required="required"
								name="marque">
								<c:forEach items="${marques}" var="m">
									<option ${piece.mark.titre==m.titre?"selected":""} > ${m.titre}</option>
								</c:forEach>
							</select>
							<div class="input-group-append">
								<a class="btn btn-outline-success"
									href='<c:url value="/Marques/add"/>'>+</a>
							</div>
						</div>
					</div>
					<div class="form-group col-md-12">
						<label for="modele">Modele</label>
						<div class="input-group mb-3">
							<select id="modele" class="form-control" required="required"
								name="modele">
								<c:forEach items="${marques}" var="marq">
									<optgroup label="${marq.titre}">
										<c:forEach items="${marq.modeles}" var="m">
											<option ${piece.modal.titre==m.titre?"selected":""} value='${m.id}'>${m.titre}</option>
										</c:forEach>
									</optgroup>
								</c:forEach>
							</select>

							<div class="input-group-append">
								<a class="btn btn-outline-success"
									href='<c:url value="/Marques/Modeles"/>'>+</a>
							</div>
						    
						</div>
						<c:forEach items='${erreurs["modal"]}' var="errf">
								<span class="badge badge-pill badge-danger">${errf}</span>
						</c:forEach>

					</div>

				</div>
			</div>			
		</div>
		
		<button type="submit" class="btn btn-primary">Valider</button>
		<a type="reset" class="btn btn-danger" href='<c:url value="/pieces"/>'>Annuler</a>
	</form>
</div>