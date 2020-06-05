<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<form class="p-4 needs-validation " method="post"
		action="<c:out value=""/>" novalidate enctype="multipart/form-data">

		<!-- ${ erreurs } <!-- pour tester -->

		<div class="form-row">
			<div class="form-group col-md-9">

				<div class="form-group">
					<label for="num_immatriculation">Numero d'immatriculation</label> <input
						type="text"
						class='form-control ${empty erreurs["num_immatriculation"]?"":"is-invalid"} '
						id="num_immatriculation" name="numImmatriculation"
						value="<c:out value="${vehicule.num_immatriculation}" />"
						${disabled_id? 'disabled':''}>
					<c:if test="${disabled_id}">
						<input type="hidden" name="numImmatriculation"
							value="${vehicule.num_immatriculation}">
					</c:if>
					<div class="invalid-feedback">
						<c:forEach items='${erreurs["num_immatriculation"]}' var="err">
							<span class="badge badge-pill badge-danger">${err}</span>
						</c:forEach>
					</div>
				</div>

				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="categoie">Categorie de vehicule</label>
						<div class="input-group mb-3">
							<select id="categorie" class="form-control" required="required"
								name="categorie">
								<c:forEach items="${categories}" var="categ">
									<option ${vehicule.categorie.titre==categ.titre?"selected":""}>${categ.titre}</option>
								</c:forEach>
							</select>

							<div class="input-group-append">
								<a class="btn btn-outline-success"
									href='<c:url value="/Vehicules/Categories"/>'>+</a>
							</div>
						</div>
					</div>
					<div class="form-group col-md-4">
						<label for="marque">Marque</label>
						<div class="input-group mb-3">
							<select id="marque" class="form-control" required="required"
								name="marque">
								<c:forEach items="${marques}" var="marque">
									<option ${vehicule.marque.titre==marque.titre?"selected":""}>${marque.titre}</option>
								</c:forEach>
							</select>
							<div class="input-group-append">
								<a class="btn btn-outline-success"
									href='<c:url value="/Marques/add"/>'>+</a>
							</div>
						</div>

					</div>

					<div class="form-group col-md-4">
						<label for="modele">Modele</label>
						<div class="input-group mb-3">
							<select id="modele" class="form-control" required="required"
								name="modele">
								<c:forEach items="${marques}" var="marq">
									<optgroup label="${marq.titre}">
										<c:forEach items="${marq.modeles}" var="m">
											<option ${vehicule.modele.titre==m.titre?"selected":""}>${m.titre}</option>
										</c:forEach>
									</optgroup>

								</c:forEach>
							</select>
							<div class="input-group-append">
								<a class="btn btn-outline-success"
									href='<c:url value="/Marques/Modeles"/>'>+</a>
							</div>
						</div>

					</div>



				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="date_achat">Date d'achat</label> <input type="date"
							class="form-control ${empty erreurs['date_achat']?'':'is-invalid'} "
							id="date_achat" required="required" name="dateAchat"
							value="<c:out value="${vehicule.date_achat}"/>">
						<div class="invalid-feedback">
							<c:forEach items='${erreurs["date_achat"]}' var="err">
								<span class="badge badge-pill badge-danger">${err}</span>
							</c:forEach>
						</div>
					</div>
					<div class="form-group col-md-4">

						<label for="etat">Etat</label>
						<c:forEach items="${etats}" var="etat">
							<div class="custom-control custom-radio">
								<input type="radio" id="${etat.titre}" name="etat"
									class="custom-control-input ${empty erreurs['date_achat']?'':'is-invalid'}"
									value="${etat.titre}"
									${etat.titre==vehicule.etat.titre?"checked":""}> <label
									class="custom-control-label" for="${etat.titre}">${etat.titre}</label>
							</div>
						</c:forEach>
						<a class="btn btn-sm btn-outline-success"
							href='<c:url value="/Vehicules/Etats"/>'>+</a>
					</div>

				</div>
			</div>




			<div class="form-group col-md-3">
				<label for="photo">Photo du vehicule</label> <img id="preview"
					class="img-fluid rounded shadow-sm mx-auto d-block"
					src='<c:url value="${empty vehicule.photo.titre?'/public/img/notfound.png':'/Images/'}${vehicule.photo.titre}" />'
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

	//pour la preview de l'image
	$("#photo").change(function() {
		readURL(this);
	});

	
	//pour le filtrage des modeles selon la marque
	
	$("document").ready(function(){
		$("#marque").change()
	})
	
	$("#marque").change(function() {
		var marque = this.value
		console.log(marque)
		$("#modele optgroup").each(function() {
			if (this.label != marque) {
				this.hidden = true
			} else {
				this.hidden = false
				console.log(this.firstElementChild.text)
				var newVal = this.firstElementChild.text
				$("#modele").val(newVal)
			}
		})

	})
	
	
</script>
