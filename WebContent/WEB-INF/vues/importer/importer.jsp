<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid">
	<h1 class="page-title">${title}</h1>
	<form method="post" action="" class="p-1" enctype="multipart/form-data">
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
			<c:if test="${erreurs.isEmpty()}">
				<div class="alert alert-success p-4 m-1" role="alert">
					Insretion réussite</div>

			</c:if>

			<c:if test="${!empty message}">
				<div class="alert alert-danger p-4 m-1" role="alert">
					${message}</div>
			</c:if>
			<c:if test="${!erreurs.isEmpty() && erreurs!=null}">
				<table class="table table-sm table-bordered mt-1"
					style="max-width: 600px; min-width: 100%">
					<thead>
						<tr class="text-color">

							<th scope="col">Lignes</th>
							<th scope="col" colspan="${names.size()}" class="text-center">Remarque</th>

						</tr>
						<tr>
							<th><c:forEach items="${names}" var="n">
									<th scope="col">${n.key}</th>
								</c:forEach></th>
						</tr>
					</thead>
					<tbody>



						<c:forEach items="${erreurs}" var="errs" varStatus="s">
							<tr>

								<th scope="row">${s.index}</th>


								<c:forEach items="${names}" var="n">

									<td scope="col"><c:forEach items="${errs[n.key]}" var="e">
													${ !empty e?(!e.equals('{}')?e:'Valide'):'Valide'}
										</c:forEach> <c:if test="${errs[n.key]==null}">
											Valide
										</c:if></td>
								</c:forEach>


							</tr>
						</c:forEach>
					</tbody>
				</table>

			</c:if>

		</div>
		<div class="card p-3 m-2">
			<div class="m-2">
				<form:radio-text label="Obtenir un modele sans donnée"
					name="choix-export" col="" inline="${true}" id="choix-1"
					selected="${true}" value="modele" />
				<form:radio-text label="Exporter toutes les données"
					name="choix-export" col="" inline="${true}" id="choix-2"
					value="donnee" />


			</div>

			<button type="submit" class="btn btn-success col mt-1" name="modele">Exporter</button>
		</div>





	</form>

</div>

