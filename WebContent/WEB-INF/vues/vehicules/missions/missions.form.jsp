<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<div class="container-fluid p-4">
	<h1 class="text-success">${title}</h1>
	<form class="p-4 needs-validation " method="post" action="" novalidate >
			
		<form:input name="affectation" type="texte" label="N° Affectation " isDisabled="${true}" value="${mission.affectation.id}"></form:input>
		<form:textarea name="decription" col="" rows="3" 
			label="Description de la mission" placeholder="La descritpion ici" 
			value="${mission.description}" erreurs_="${erreurs['decription']}">
		</form:textarea>
	
		<div class="form-row">
		
			<form:date-time name="dateDebut"  value="${mission.dateDebut}"
				col="col-md-6" erreurs_="${erreurs['dateDebut']}" label="Date de debut">
			</form:date-time>
			<form:date-time name="dateFin" value="${mission.dateFin }" label="Date de fin "
				col="col-md-6" erreurs_="${erreurs['dateDebut']}" >
			</form:date-time>
			
		</div>
		
		<button type="submit" class="btn btn-primary">Valider</button>
		<button type="reset" class="btn btn-danger">Annuler</button>
	</form>
</div>