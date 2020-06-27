<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>
<%@taglib prefix="img" tagdir="/WEB-INF/tags/img"%>
<%@taglib prefix="m" tagdir="/WEB-INF/tags/menu"%>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/link"%>

	<ul class="nav nav-tabs" id="myTab" role="tablist">
			<m:tab-link label="Detections" id="detection" active="${true}"/>
			<m:tab-link label="Defaillances" id="defaillance"/>
			<m:tab-link label="Causes" id="cause"/>
			<m:tab-link label="Effets" id="effet"/>
	</ul>
	<div class="tab-content" id="myTabContent">
		
		<div id="detection" class="tab-pane fade show active" role="tabpanel" aria-labelledby="detection-tab">
			
		</div>
		<div id="defaillance" class="tab-pane fade" role="tabpanel" aria-labelledby="defaillance-tab">
		<div class="container-fluid p-2">
		<div class="">
		
		<div>
			<form action="" method="post" class="needs-validation" novalidate>
				<form:input-button name="def" col="" 
					type="texte" value="" placeHolder="Defaillance"
					erreurs_="${erreurs['defaillance']}">
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
					<c:forEach items="${defai}" var="d" >
						<tr>
							<td>${d.defaillance}</td>
							<td align="right">
								<btn:remove value="/amdec/defaillance/remove/${d.id}"></btn:remove>	
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
		<c:if test="${sessionScope.exception }">
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


		<script>
		$(document).ready(function (){
    	swal({
  		title: "Cette défaillance est liée à d'autre élément",
  		text: "vous ne pouvez pas la supprimer",
  		icon: "warning",
  		dangerMode: true,
		})
		});
		</script>
		<%session.removeAttribute("exception"); %>
		</c:if>
		</div>
		</div>	
		</div>
		<div id="cause" class="tab-pane fade" role="tabpanel" aria-labelledby="cause-tab">
		<div class="container-fluid p-2">
		<div class="">
		<div>
			<form action="" method="post" class="needs-validation" novalidate>
				<form:input-button name="cause" col="" 
					type="texte" value="" placeHolder="Cause"
					erreurs_="${erreurs['cause']}">
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
					<c:forEach items="${causes}" var="c" >
						<tr>
							<td>${c.cause}</td>
							<td align="right">
								<btn:remove value="/amdec/cause/remove/${c.id}"></btn:remove>	
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
		<c:if test="${sessionScope.exception }">
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


		<script>
		$(document).ready(function (){
    	swal({
  		title: "Cette cause est liée à d'autre éléments",
  		text: "vous ne pouvez pas la supprimer",
  		icon: "warning",
  		dangerMode: true,
		})
		});
		</script>
		<%session.removeAttribute("exception"); %>
		</c:if>
		</div>
		</div>

		</div>
		<div id="effet" class="tab-pane fade" role="tabpanel" aria-labelledby="effet-tab">
		<div class="container-fluid p-2">
		<div class="">
		
		<div>
			<form action="" method="post" class="needs-validation" novalidate>
				<form:input-button name="eff" col="" 
					type="texte" value="" placeHolder="Effet"
					erreurs_="${erreurs['effet']}">
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
					<c:forEach items="${effets}" var="e" >
						<tr>
							<td>${e.effet}</td>
							<td align="right">
								<btn:remove value="/amdec/defaillance/remove/${e.id}"></btn:remove>	
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
		<c:if test="${sessionScope.exception }">
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


		<script>
		$(document).ready(function (){
    	swal({
  		title: "Cet effet est liée à d'autre éléments",
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
		</div>
	</div>



