<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>

<div id="effet"
	class="tab-pane fade
		${active=='effet' || sessionScope.effet ?'show active':''}"
	role="tabpanel" aria-labelledby="effet-tab">
	<div class="container-fluid p-2">
		<div class="">

			<div>
				<%
				    session.removeAttribute( "effet" );
				%>
				<form action="" method="post" class="needs-validation" novalidate>
					<form:input-button name="eff" col="" type="texte" value=""
						placeHolder="Effet" erreurs_="${erreurs['effet']}">
					</form:input-button>
				</form>

				<table class="table">
					<thead>
						<tr>
							<th scope="col" style='color: #3E703D;'>Effet</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${effets}" var="e">
							<tr>
								<td>${e.effet}</td>
								<td align="right"><btn:remove
										value="/amdec/effet/remove/${e.id}"></btn:remove></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
			<c:if test="${sessionScope.exception }">
				<script>
					$(document).ready(function() {
						swal({
							title : "Cet effet est liée à d'autre éléments",
							text : "vous ne pouvez pas le supprimer",
							icon : "warning",
							dangerMode : true,
						})
					});
				</script>
				<%
				    session.removeAttribute( "exception" );
				%>
			</c:if>
		</div>
	</div>
</div>

