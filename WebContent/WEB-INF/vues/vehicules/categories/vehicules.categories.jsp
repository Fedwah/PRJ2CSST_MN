<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid ">
	<div class="">

		<h3 class="display"
			style='color: #3E703D; font-size: 40px; font-style: gras;'>${title}</h3>
		<div>
			<form action="" method="post" class="needs-validation" novalidate>
				<form:input-button name="new_categorie" col="" type="texte" value=""
					label="Inserer un nouvelle categorie ?"
					placeHolder="Titre de la categorie a ajouter"
					erreurs_="${erreurs['titre']}">
				</form:input-button>
			</form>


			<table class="table">
				<thead>
					<tr>

						<th scope="col" style='color: #3E703D;'>Titre</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${categories}" var="categ" varStatus="status">
						<tr>

							<td>${categ.titre}</td>
							<td align="right"><btn:remove
									value="/Vehicules/Categories/remove/${categ.titre}"></btn:remove>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			
			
		</div>
	</div>
</div>
