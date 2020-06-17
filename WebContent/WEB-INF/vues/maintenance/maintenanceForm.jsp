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
						name="matricule" col="col-md" type="text"
						value="${vehicule.num_immatriculation}">
					</form:input>
					
				</div>
				<div class="form-row">
				
					<form:select fieldID="titre" fieldToTest="titre"
						items="${piece}" label="Niveau de maintenance"
						name="niv" col="col-md"
						value="${piece.id}"
						selectedValue="${piece.pieceName}" fieldToPrint="titre"
						></form:select>
					
				</div>
				<div class="form-row">
					<form:select fieldID="titre" fieldToTest="titre"
						items="${niveaux}" label="Niveau de maintenance"
						name="niv" col="col-md"
						selectedValue="${maintenance.niveau.niv}" fieldToPrint="titre"
						addLink="/maintenance/niveaux"></form:select>
				</div>

				<div class="form-row">
					
					<form:date name="date"  value="" 
						col="col-md" erreurs_="${erreurs[names.date_achat]}"
						label="Date">
						
					</form:date>

				</div>
			</div>
		</div>



		<button type="submit" class="btn btn-primary">Valider</button>
		<button type="reset" class="btn btn-danger">Annuler</button>

	</form>
</div>