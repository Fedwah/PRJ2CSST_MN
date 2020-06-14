<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>

<div class="container-fluid">
	<form class="p-4 needs-validation " method="post"
		action="<c:out value=""/>" novalidate enctype="multipart/form-data">

		<!--  ${ erreurs } <!-- pour tester -->
		<div class="form-row">
 
			<div class="col-md-9">
			
				<div class="form-row">
				
					<form:input erreurs_="${erreurs['num_immatriculation']}"
						label="Numero d'immatriculation"
						name="${names.num_immatriculation}" col="col-md" type="text"
						value="${vehicule.num_immatriculation}"
						isDisabled="${disabled_id}">
					</form:input>
					
				</div>


				<div class="form-row">
					<form:select fieldID="titre" fieldToTest="titre"
						items="${categories}" label="Categorie de vehicule"
						name="categorie" col="col-md-4"
						selectedValue="${vehicule.categorie.titre}" fieldToPrint="titre"
						addLink="/Vehicules/Categories"></form:select>

					<form:select name="${names.marque}" fieldToTest="titre"
						col="col-md-4" fieldToPrint="titre"
						selectedValue="${vehicule.marque.titre}" items="${marques}"
						addLink="/Marques/add" label="Marque" fieldID="titre"></form:select>



					<form:filter-select name="${names.modele}" col="col-md-4"
						childfieldToPrint="titre" fieldChild="modeles"
						selectedValue="${vehicule.modele.titre}" items="${marques}"
						childfieldID="id" addLink="/Marques/Modeles" label="Modele"
						fieldID="titre" childfieldToTest="titre">
					</form:filter-select>


				</div>

				<div class="form-row">
					<form:input label="Date d'achat" name="${names.date_achat}"
						col="col-md-4" type="date" value="${vehicule.date_achat}"
						erreurs_="${erreurs[names.date_achat]}" ></form:input>
						
						
					<form:radio name="${names.etat}" fieldToTest="titre" col="col-md-4" 
						fieldToPrint="titre" selectedValue="${vehicule.etat.titre }"
						items="${etats}" 
						addLink="/Vehicules/Etats" label="Etat" fieldID="titre"
						erreurs_="${erreurs[names.etat]}">
					</form:radio>
				</div>
			</div>
			<!-- Probleme lors de la creation perte de l'image en cas d'erreur -->
			<form:img-upload label="Photo du vehicule" name="${names.photo}"
				col="col-md-3" image="${vehicule.photo}">
			</form:img-upload>
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
