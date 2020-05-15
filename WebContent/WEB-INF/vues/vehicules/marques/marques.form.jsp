<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid ">
	<form class="p-5 needs-validation" method="post"
		action="<c:out value="add"/>" novalidate enctype="multipart/form-data">

		<div class="form-group">
			<label for="titre">Nom de la marque</label> <input type="text"
				class='form-control ${empty erreurs["titre"]?"":"is-invalid"} '
				id="titre" name="titre" value="<c:out value="${marque.titre}"/>">

			<div class="invalid-feedback">
				<c:forEach items='${erreurs["titre"]}' var="err">
					<span class="badge badge-pill badge-danger">${err}</span>
				</c:forEach>
			</div>
		</div>


		<div class="form-group">
			<label for="photo"> Photo du vehicule</label> <img id="preview"
				class='img-fluid rounded shadow-sm mx-auto d-block'
				src='<c:url value="/public/img/notfound.png" />' width="200" />
			<div class="custom-file">
				<input id="photo" name="image" type="file"
					class='custom-file-input form-control ${empty erreurs["image"]?"":"is-invalid"}'>
				<label class='custom-file-label' for="photo">Importer une
					image</label>
			</div>

			<div class="invalid-feedback">

				<c:forEach items='${erreurs["image"]}' var="err">
					<span class="badge badge-pill badge-danger">${err}</span>
				</c:forEach>
			</div>
		</div>

		<div class="form-group">
			<button type="submit" class="btn btn-primary btn-block">Valider</button>
			<button type="reset" class="btn btn-danger btn-block">Annuler</button>
		</div>


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
