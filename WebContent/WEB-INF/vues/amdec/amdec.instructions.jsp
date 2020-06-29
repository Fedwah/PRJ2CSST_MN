<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>


<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<div id="instructions"
	class="tab-pane fade
		${active=='instruction'?'show active':''}"
	role="tabpanel" aria-labelledby="intruction-tab">
	<div class="container-fluid p-2">
		<div class="">


			<div>
				<form id="form" action="" method="post" class="needs-validation" novalidate>
					<div id="form-instruction" style="display:none;">
						<form:list name="modele" fieldToTest="id" col="" fieldToPrint="titre" 
								selectedValue="" fieldID="id" label="Modele du vehicule"
								items="${modeles}">
							</form:list>
							
							<form:list name="piece" fieldToTest="id" col="" fieldToPrint="reference" 
								selectedValue="" fieldID="id" label="Piece"
								items="${pieces}">
							</form:list>
							
						<div class="form-row">
							
							
							<form:list name="defaillance-instruction" fieldToTest="id" col="col-md" 
								fieldToPrint="defaillance" selectedValue="" fieldID="id" items="${defai}" label="Dafaillance">
							</form:list>
							
							<form:list name="cause-instruction" fieldToTest="id" col="col-md" 
								fieldToPrint="cause" selectedValue="" fieldID="id" items="${causes}" label="Cause">
							</form:list>
							<form:list name="effet-instruction" fieldToTest="id" col="col-md" 
								fieldToPrint="effet" selectedValue="" fieldID="id" items="${effets}" label="Effet">
							</form:list>
							
							
						</div>

						<form:range max="5" name="gravite" min="1"
							value="${instruction.gravite }" label="Gravité" />
						<form:range max="365" name="frequence" min="1"
							value="${instruction.gravite }" label="Frequence" steps="10"
							unite="Jours" />
						<form:range max="5" name="niveau_detection" min="1"
							value="${instruction.niveau_detection }"
							label="Niveau de detection" />
							
						<form:textarea name="demarche_resolution" col="" rows="4" label="Demarche de resolution"/>
					</div>

					<button name="instruction" type="submit" class="btn btn-primary w-100" >Ajouter</button>

				</form>

				<table class="table ">
					<thead>
						<tr class="text-color">
							<th scope="col">#</th>
							<th scope="col">Defaillance</th>
							<th scope="col">Causes</th>
							<th scope="col">Effet</th>
							<th scope="col">Demarche de resolution</th>
							<th scope="col">Gravité</th>
							<th scope="col">Frequence</th>
							<th scope="col">Niveau de detection</th>
							<th scope="col">Criticité</th>
							<th scope="col">Operations</th>
							
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${instructions}" var="i">

							<tr>
								<td scope="row"> ${i.id}</td>
								<td> ${i.defaillance.defaillance}</td>
								<td> ${i.cause.cause}</td>
								<td> ${i.effet.effet}</td>
								<td> ${i.demarche_resolution}</td>
								<td> ${i.gravite}</td>
								<td> ${i.frequence}</td>
								<td> ${i.niveau_detection}</td>
								<td> ${i.criticite}</td>
								<td align="right"><btn:remove
										value="/amdec/defaillance/remove/"></btn:remove></td>

							</tr>

						</c:forEach>
					</tbody>
				</table>

			</div>

		</div>
	</div>
<script>

	//pour afficher le form d'instruction ou envoie le formulaire
	var form = document.getElementById('form');
	
	form.addEventListener('submit', function(e) {
		
		var div = $("#form-instruction")
		if(div.css('display')=="none"){
			e.preventDefault();
			div.show()
		}
		
	})
	
	//pour sycnhronisez le range avec la value
	$("#gravite-range-value").val($("#gravite-range").val())
	$("#frequence-range-value").val($("#frequence-range").val())
	$("#niveau_detection-range-value").val($("#niveau_detection-range").val())

	$("#gravite-range").change(function(e) {
		console.log(e)
		update($("#gravite-range-value"), e.target.value)
	});

	$("#frequence-range-value").change(function(e) {
		update($("#frequence-range"), e.target.value)
	});

	$("#frequence-range-value").val($("#frequence-range").val())
	$("#frequence-range").change(function(e) {
		console.log(e)
		update($("#frequence-range-value"), e.target.value)
	});

	$("#frequence-range-value").change(function(e) {
		update($("#frequence-range"), e.target.value)
	});

	$("#niveau_detection-range-value").val($("#niveau_detection-range").val())
	$("#niveau_detection-range").change(function(e) {
		console.log(e)
		update($("#niveau_detection-range-value"), e.target.value)
	});

	$("#niveau_detection-range-value").change(function(e) {
		update($("#niveau_detection-range"), e.target.value)
	});
	function update(input, val) {
		console.log(input, val)
		if (input.val() != val) {
			input.val(val)
		}

	}
</script>

</div>









