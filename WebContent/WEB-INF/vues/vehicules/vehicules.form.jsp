<%@page import="beans.entities.vehicules.EtatsVehicule"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid">
	<form class="p-5 needs-validation " method="post"
		action="<c:out value=""/>" novalidate enctype="multipart/form-data">

		<!--  ${ erreurs } <!-- pour tester -->
		<div class="form-row">

			<div class="col-md-9">

				<div class="form-row">
					
					<form:input erreurs_="${erreurs['matricule_interne']}"
						label="Matricule interne" name="${names.matricule_interne}"
						col="col-md-6" type="text" value="${vehicule.matricule_interne}"
						isDisabled="${disabled_id}">
					</form:input>

					<form:input name="${names.matricule_externe}" type="texte"
						value="${vehicule.matricule_externe}" col="col-md-6"
						erreurs_="${erreurs['matricule_externe']}"
						label="Matricule externe">
					</form:input>

				</div>

				<form:input name="${names.unite}" type="hidden"
					value="${empty vehicule.unite ? unite :vehicule.unite.codeUN}"
					isDisabled="${true }" />


				<div class="form-row">
					<form:select fieldID="titre" fieldToTest="titre"
						items="${categories}" label="Categorie de vehicule"
						name="categorie" col="col-md-12"
						selectedValue="${vehicule.categorie.titre}" fieldToPrint="titre"
						addLink="/Vehicules/Categories"></form:select>

					<form:select name="${names.marque}" fieldToTest="titre"
						col="col-md-6" fieldToPrint="titre"
						selectedValue="${vehicule.marque.titre}" items="${marques}"
						addLink="/Marques/add" label="Marque" fieldID="titre"></form:select>



					<form:filter-select name="${names.modele}" col="col-md-6"
						childfieldToPrint="titre" fieldChild="modeles"
						selectedValue="${vehicule.modele.id}" items="${marques}"
						childfieldID="id" addLink="/Marques/Modeles" label="Modele"
						fieldID="titre" childfieldToTest="titre">
					</form:filter-select>


				</div>

				<div class="form-row">

					<form:date name="${names.date_achat}"
						value="${vehicule.date_achat}" col="col-md-6"
						erreurs_="${erreurs[names.date_achat]}" label="Date d'achat">
					</form:date>
					
					<c:choose>

						<c:when test="${vehicule.etat==null}">
							<form:input name="${names.etat}" type="hidden" value="<%= EtatsVehicule.LIBRE.label %>"/>
						</c:when>
						<c:otherwise>
							<form:radio name="${names.etat}" fieldToTest="label"
								col="col-md-4" fieldToPrint="label"
								selectedValue="${vehicule.etat.label}" items="${etats}"
								label="Etat" fieldID="label" erreurs_="${erreurs[names.etat]}">
							</form:radio>
						</c:otherwise>
					</c:choose>


				</div>
			</div>
			<!-- Probleme lors de la creation perte de l'image en cas d'erreur -->
			<form:img-upload label="Photo du vehicule" name="${names.photo}"
				col="col-md-3" image="${vehicule.photo}">
			</form:img-upload>
		</div>


		<button type="submit" class="btn btn-primary">Valider</button>
		<btn:btn type="danger" value="/Vehicules" text="Annuler"
			outline="${false}" />

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

	/*$("document").ready(function(){
		$("#marque").change()
	})*/

	$("#marque").change(function() {
		var marque = this.value
		$("#modele").val(" ")
		$("#modele optgroup").each(function() {
			if (this.label != marque) {
				this.hidden = true
			} else {
				this.hidden = false
				console.log(this.firstElementChild)
				if (this.firstElementChild) {
					this.firstElementChild.selected = true
				}

			}
		})

	})
</script>
