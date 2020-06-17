<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid p-5">
	<div class="">
		<h1 class="display-5 text-success">${title}</h1>
		<div>
			<form action="" method="post" class="needs-validation" novalidate>
				<form:input-button name="niveau" col="" 
					type="texte" value="" placeHolder="Niveau"
					erreurs_="${erreurs['niv']}">
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
					<c:forEach items="${niveaux}" var="n" varStatus="status">
						<tr>
							<th scope="row">${status.index}</th>
							<td>${n.titre}</td>
							<td>
								<btn:remove value="/maintenance/niveau/remove/${n.id}"></btn:remove>	
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</div>