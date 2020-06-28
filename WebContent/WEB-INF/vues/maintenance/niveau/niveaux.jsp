<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid p-5">
	<div class="">
		<h3 class="display-4" style='color: #3E703D;font-size:40px;font-style:gras;'>Niveaux de maintenance</h3>
		<div>
			<form action="" method="post" class="needs-validation" novalidate>
				<form:input-button name="niveau" col="" 
					type="texte" value="" placeHolder="Niveau"
					erreurs_="${erreurs['niveau']}">
				</form:input-button>
			</form>

			<table class="table">
				<thead>
					<tr>
						<th scope="col" style='color: #3E703D;'>Niveau</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${niveaux}" var="n" >
						<tr>
							<td>${n.niveau}</td>
							<td align="right">
								<btn:remove value="/maintenance/niveaux/remove/${n.idNiv}"></btn:remove>	
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
		<c:if test="${sessionScope.exception }">

		<script>
		$(document).ready(function (){
    	swal({
  		title: "Ce niveau est lié à des maintenances",
  		text: "vous ne pouvez pas le supprimer",
  		icon: "warning",
  		dangerMode: true,
		})
		});
		</script>
		<%session.removeAttribute("exception"); %>
		</c:if>
	</div>
</div>
