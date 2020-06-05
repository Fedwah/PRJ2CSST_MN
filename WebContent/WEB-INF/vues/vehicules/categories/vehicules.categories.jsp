<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid p-5">
	<div class="">
		
		<h1 class="display-5 text-success">${title}</h1>
		<div>
			<form action="" method="post" class="needs-validation" novalidate>
				<div class="form-group ">
					<label for="new_categorie">Inserer un nouvelle categorie ?</label>
					<div class='input-group ${empty erreurs["titre"]?"":"is-invalid"}'>
						<input type="text"
							class='form-control ${empty erreurs["titre"]?"":"is-invalid"}'
							name="new_categorie" placeholder="Titre de la categorie a ajouter"
							aria-label="Recipient's username" aria-describedby="button-add"
							id="new_categorie">
						<div class="input-group-append">
							<button class="btn btn-outline-success" type="submit"
								id="button-add">Ajouter</button>
						</div>
					</div>
					<div class="invalid-feedback">
						<c:forEach items='${erreurs["titre"]}' var="err">
							<span class="badge badge-pill badge-danger">${err}</span>
						</c:forEach>
					</div>
				</div>
			</form>


			<table class="table table-bordered">
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col">Titre</th>
						<th scope="col">Operation</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${categories}" var="categ" varStatus="status">
						<tr>
							<th scope="row">${status.index}</th>
							<td>${categ.titre}</td>
							<td><a class="btn btn-outline-danger"
								href='<c:url value="/Vehicules/Categories/remove/${categ.titre}"/>'>Supprimer</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</div>
