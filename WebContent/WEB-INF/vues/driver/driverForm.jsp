<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<form class="p-4 needs-validation " method="post"
		action="<c:out value=""/>" enctype="multipart/form-data" >

		<!-- ${ erreurs } <!-- pour tester -->

		<div class="form-row">
			<div class="form-group col-md-9">
				<div class="form-group ">
					<label for="nom">Nom</label> 
					<input
						type="text"
						class='form-control '
						id="nom" name="nom"
						value="<c:out value="${driver.lastN}" />"
						
					>
					<c:forEach items='${erreurs["lastN"]}' var="errl">
								<span class="badge badge-pill badge-danger">${errl}</span>
					</c:forEach>
					</div>
					<label for="prenom">Prenom</label> 
					<input
						type="text"
						class='form-control '
						id="prenom" name="prenom"
						value="<c:out value="${driver.firstN}" />"
						
					>
					<div>
					<c:forEach items='${erreurs["firstN"]}' var="errf">
								<span class="badge badge-pill badge-danger">${errf}</span>
					</c:forEach>
					</div>
						<label for="recruit">Date de recrutement</label> 
						<input type="date"
							class="form-control ${empty erreurs['recruit']?'':'is-invalid'} "
							id="recruit" required="required" name="recruit"
							value="<c:out value="${driver.recruitDate}"/>">
						<div>
							<c:forEach items='${erreurs["recruitDate"]}' var="errD">
								<span class="badge badge-pill badge-danger">${errD}</span>
							</c:forEach>
						</div>
						<label for="unite">Code unité</label> 
						<input
						type="text"
						class='form-control '
						id="unite" name="un"
						value="<c:out value="${driver.unite.codeUN}" />"					
						>
						<div>
							<c:forEach items='${erreurs["unite"]}' var="errf">
								<span class="badge badge-pill badge-danger">${errf}</span>
							</c:forEach>
						</div>
				</div>
			
			<div class="form-group col-md-3">
				<label for="photo">Photo du conducteur</label> 
				<img id="preview"
					class="img-fluid rounded shadow-sm mx-auto d-block"
					
					src='<c:url value="${empty driver.photo.titre?'/public/img/driver_white_greenBackground.png':'/Images/'}${driver.photo.titre}" />'
					width="200" />

				<div class="custom-file">
					<input id="photo" name="photo" type="file"
						class="custom-file-input"> <label
						class="custom-file-label" for="photo">Importer une image</label>
				</div>

				<div class="invalid-feedback">

					<c:forEach items='${erreurs["photo"]}' var="err">
						<span class="badge badge-pill badge-danger">${err}</span>
					</c:forEach>
				</div>
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Valider</button>
		<a type="reset" class="btn btn-danger" href='<c:url value="/drivers"/>'>Annuler</a>
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