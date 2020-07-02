<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<div id="detection"
	class="tab-pane fade 
				${active=='detection' || empty active ?'show active':''}"
	role="tabpanel" aria-labelledby="detection-tab">

	<table class="table">
		<thead>
			<tr class="text-color">
				
				<th scope="col">Vehicule</th>
				<th>Date de detection</th>
				<th>Piéce</th>
				<th>Defaillance</th>
				<th>Causes</th>
				<th>Effet</th>
				<th>Criticité</th>
				<th>Operations</th>

			</tr>
		</thead>

		<tbody>
			<c:forEach items="${detections}" var="d">

				<tr>
					<td scope="row">${d.vehicule.matricule}</td>
					<td >${d.date}</td>
					<td>${d.instruction.piece.refrence}</td>
					<td>${d.instruction.defaillance.defaillance}</td>
					<td>${d.instruction.cause.cause}</td>
					<td>${d.instruction.effet.effet}</td>
					<td>${d.instruction.criticite}</td>
					<td align="right"><btn:remove
							value="/amdec/defaillance/remove/"></btn:remove></td>

				</tr>

			</c:forEach>
		</tbody>
	</table>
</div>
