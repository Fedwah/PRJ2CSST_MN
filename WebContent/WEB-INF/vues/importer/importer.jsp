<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid">
	<h1 class="page-title">${title}</h1>
	<form method="post" action="" class="p-5" enctype="multipart/form-data">
		<div class="m-2">
			<form:select name="class" fieldToTest="key" col="" fieldToPrint="key"
				selectedValue="" fieldID="value" map="${classes}"
				label="Importer des">
			</form:select>
		</div>

		<div class="card p-3 m-2">
			<form:file-upload name="file" col="" text="Importer un excel"></form:file-upload>
			<button type="submit" class="btn btn-primary col" name="importer">Valider
				l'importation</button>
		</div>
		<div class="card p-3 m-2">
			<div class="m-2">
				<form:radio-text label="Obtenir un modele sans donnée"
					name="choix-export" col="" inline="${true}" id="choix-1" selected="${true}" value="modele"/>
				<form:radio-text label="Exporter toutes les données"
					name="choix-export" col="" inline="${true}"  id="choix-2" value="donnee"/>
					
					
			</div>
				
			<button type="submit" class="btn btn-success col mt-1" name="modele">Exporter</button>
		</div>

		
				


	</form>

</div>

