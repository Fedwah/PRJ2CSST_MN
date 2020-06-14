<%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid p-5">
	<div class="">
		<h1 class="display-5 text-success">${title}</h1>
		<div>
			<form action="" method="post" class="needs-validation" novalidate>
				<form:input-button name="new_etat" col="" type="texte" value=""
					label="Inserer un nouveau etat ?" erreurs_="${erreurs['titre']}">
				</form:input-button>
			</form>


			<table class="table table-bordered">
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col">Titre</th>
						<th scope="col">Operation</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${etats}" var="etat" varStatus="status">
						<tr>
							<th scope="row">${status.index}</th>
							<td>${etat.titre}</td>
							<td>
								<btn:remove value="/Vehicules/Etats/remove/${etat.titre}"></btn:remove>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</div>
