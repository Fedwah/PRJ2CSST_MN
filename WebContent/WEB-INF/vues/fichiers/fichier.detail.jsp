<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/file" prefix="file"%>

<div class="container-fluid p-5">
	<div class="row mb-1 ">
		<div class="col">
			<h5 class="d-inline">Nom du fichier :</h5>
			<span id="name">${fichier.nom}</span>
		</div>
		<div class="col">
			<h5 class="d-inline">Taille:</h5>
			<span id="taille">${fichier.taille}</span>
		</div>
		<div class="col">
			<h5 class="d-inline">Type:</h5>
			<span id="type">${fichier.type}</span>
		</div>
		<div class="col-12">
			<h5>Description</h5>
			<form:textarea name="description" col="" rows="5"
				value="${fichier.description}" />
		</div>

	</div>
	<div class="p-3">
	
		<file:viewer downloadable="${!supported}" filename="${fichier.nom}" previewable="${supported}"></file:viewer>
	</div>

</div>
