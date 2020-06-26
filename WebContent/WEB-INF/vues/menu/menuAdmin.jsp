<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/menu" prefix="menu"%>

<div>

<ul class="nav flex-column  text-center  p-3">
	
	<menu:link label="Regions" value="/regions"></menu:link>
	<menu:link label="Utilisateurs" value="/Utilisateurs"></menu:link>
	<menu:link label="Pièces" value="/pieces"></menu:link>
	<menu:link label="Marques" value="/Marques"></menu:link>
	<menu:link label="Catégorie" value="/Vehicules/Categories"></menu:link>
	<menu:link label="Catégorie" value="/maintenance/niveaux"></menu:link>
	<menu:link label="Vehicules" value="/Vehicules"></menu:link>
	<menu:link label="Importer/Exporter" value="/Importer/Vehicules"></menu:link>
</ul>
</div>

