<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<form class="p-5 needs-validation " method="post"
		action="<c:out value="add"/>" novalidate enctype="multipart/form-data">
		<div class="form-row">
			<div class="form-group col-md-9">
				<div class="form-group ">
					<label for="num_immatriculation">Numero d'immatriculation</label> <input
						type="text"
						class='form-control ${empty erreurs["num_immatriculation"]?"":"is-invalid"} '
						id="num_immatriculation" name="numImmatriculation"
						value="<c:out value="${vehicule.num_immatriculation}"/>">

					<div class="invalid-feedback">
						<c:forEach items='${erreurs["num_immatriculation"]}' var="err">
							<span class="badge badge-pill badge-danger">${err}</span>
						</c:forEach>
					</div>
				</div>


				<div class="form-row">
					<div class="form-group col-md-6">

						<label for="modele">Modele</label> <input type="text"
							class="form-control" id="modele" placeholder="1234 Main St"
							required="required" name="model"
							value="<c:out value="${vehicule.model.titre}"/>">
					</div>
					<div class="form-group col-md-6">
						<label for="marque">Marque</label> <input type="text"
							class="form-control" id="marque" required="required"
							name="marque" value="<c:out value="${vehicule.marque.titre}"/>">
					</div>
				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="date_achat">Date d'achat</label> <input type="date"
							class="form-control" id="date_achat" required="required"
							name="dateAchat" value="<c:out value="${vehicule.date_achat}"/>">
					</div>
					<div class="form-group col-md-4">
						<label for="etat">Etat</label> <select id="etat"
							class="form-control" required="required" name="etat">
							<optionselected}>En fonction </option>
								<option>En panne</option>
								<option>En maintenance</option>
								<option>Abandonner</option>
							</select>
					</div>

				</div>

			</div>

			<div class="form-group col-md-3">
				<label for="photo">Photo du vehicule</label> <img id="preview"
					class="img-fluid rounded shadow-sm mx-auto d-block"
					src='<c:url value="/public/img/notfound.png" />' width="200" />
				<div class="custom-file">
					<input id="photo" name="photo" type="file"
						class="custom-file-input"> <label
						class="custom-file-label" for="photo">Importer une image</label>
				</div>




				<div class="invalid-feedback">

					<c:forEach items='${erreurs["num_immatriculation"]}' var="err">
						<span class="badge badge-pill badge-danger">${err}</span>
					</c:forEach>
				</div>
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Valider</button>
		<button type="reset" class="btn btn-danger">Annuler</button>
	</form>
</div>
<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]); // convert to base64 string
		}
	}

	$("#photo").change(function() {
		readURL(this);
	});
</script>
