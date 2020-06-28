<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>

<div id="cause"
	class="tab-pane fade ${active=='cause' || sessionScope.cause ?'show active':''}"
	role="tabpanel" aria-labelledby="cause-tab">
	<div class="container-fluid p-2">
		<div class="">
			<div>
				<%
				    session.removeAttribute( "cause" );
				%>
				<form action="" method="post" class="needs-validation" novalidate>
					<form:input-button name="cause" col="" type="texte" value=""
						placeHolder="Cause" erreurs_="${erreurs['cause']}">
					</form:input-button>
				</form>

				<table class="table">
					<thead>
						<tr>
							<th scope="col" style='color: #3E703D;'>Cause</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${causes}" var="c">
							<tr>
								<td>${c.cause}</td>
								<td align="right"><btn:remove value="/amdec/cause/remove/${c.id}"></btn:remove>
								</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
			<c:if test="${sessionScope.exception }">
				<script>
					$(document).ready(function() {
						swal({
							title : "Cette cause est liée à d'autre éléments",
							text : "vous ne pouvez pas la supprimer",
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