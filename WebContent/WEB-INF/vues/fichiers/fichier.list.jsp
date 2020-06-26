<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="file" tagdir="/WEB-INF/tags/file"%>

<div class="container-fluid p-3">
	<div class="">
		<h1 class="page-title">${title}</h1>
	</div>
	
	<div class="row p-1">
		<form class="col-md-11" method="post" action="">
			<div class="form-row">

				<form:input name="search" col="col-md-3" type="text"
					value="${search}" placeHolder="chercher"></form:input>

				<form:select name="field" fieldToTest="key" col="col-md-3"
					fieldToPrint="value" selectedValue="${field}" map="${filtres}"
					fieldID="key"></form:select>

				<btn:search name="search" />
			</div>
		</form>
		
		
		
		<div class="col-md-1 " >
			<btn:add value="/Fichier/importer"></btn:add>
		</div>
		
	</div>
	<table class="table">
		<thead>
			<tr class="text-color">
				<th scope="col">Nom fichier</th>
				<th scope="col">Description</th>
				<th scope="col">Taille</th>
				
				
				<th scope="col">Operations</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${fichiers}" var="f">
				<tr >
					<th scope="row"> 
						<file:icon size="40px" typename="${f.nameType}" filename="${f.nom}"/>
					</th>
					
					<td>${f.description}</td>
					<td>${f.taille}</td>
					
					
					<td>
						<btn:btn type="primary" value='/Fichiers/"${f.nom}"' text="Lire" />
						<btn:remove value="/Fichiers/remove/${f.nom}" />
					   
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>