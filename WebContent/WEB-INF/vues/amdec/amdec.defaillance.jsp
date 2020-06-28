<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>

<div id="defaillance"
	class="tab-pane fade
			${active=='defaillance'?'show active':''}"
	role="tabpanel" aria-labelledby="defaillance-tab">
	<div class="container-fluid p-2">
		<div class="">

			<div>
				<form action="" method="post" class="needs-validation" novalidate>
					<form:input-button name="def" col="" type="texte" value=""
						placeHolder="Defaillance" erreurs_="${erreurs['defaillance']}">
					</form:input-button>
				</form>

				<table class="table">
					<thead>
						<tr>
							<th scope="col" style='color: #3E703D;'>Défaillance</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${defai}" var="d">
							<tr>
								<td>${d.defaillance}</td>
								<td align="right"><btn:remove
										value="/amdec/defaillance/remove/"></btn:remove></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
			<c:if test="${sessionScope.exception}">
				<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
				<script
					src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


				<script>
					$(document)
							.ready(
									function() {
										swal({
											title : "Cette défaillance est liée à d'autre élément",
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

