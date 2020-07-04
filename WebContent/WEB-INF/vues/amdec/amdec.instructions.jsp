<%@page import="beans.entities.amdec.enums.Gravite"%>
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
				<form id="form" action="" method="post" class="needs-validation"
					novalidate>
					<div id="form-instruction" style="display: ${erreurs.isEmpty()||erreurs ==null ?'none':'block'};">
						<form:list name="modele" fieldToTest="id" col=""
							fieldToPrint="titre" selectedValue="" fieldID="id"
							label="Modele du vehicule" items="${modeles}" erreurs_="${erreurs['modele_vehicule']}">
						</form:list>

						<form:list name="piece" fieldToTest="refrence" col=""
							fieldToPrint="refrence" selectedValue="" fieldID="refrence"
							label="Piece" items="${pieces}" erreurs_="${erreurs['piece']}">
						</form:list>

						<div class="form-row">


							<form:list name="defaillance-instruction" fieldToTest="id"
								col="col-md" fieldToPrint="defaillance" selectedValue=""
								fieldID="id" items="${defai}" label="Dafaillance" erreurs_="${erreurs['defaillance']}">
							</form:list>

							<form:list name="cause-instruction" fieldToTest="id" col="col-md"
								fieldToPrint="cause" selectedValue="" fieldID="id"
								items="${causes}" label="Cause" erreurs_="${erreurs['cause']}">
							</form:list>
							<form:list name="effet-instruction" fieldToTest="id" col="col-md"
								fieldToPrint="effet" selectedValue="" fieldID="id"
								items="${effets}" label="Effet" erreurs_="${erreurs['effet']}">
							</form:list>


						</div>

						<div class="form-row align-items-center" style="height: 90px;">
							<form:range max="5" name="gravite" min="1"
								value="${instruction.gravite }" label="Gravité" col="col-md-9" />

							<div class="col-md-3">
								<c:forEach items="${gravites}" var="g" varStatus="s">
									<div id="gravite-${s.index}" style="display: none;"
										class="media">
										<div class="media-body" style="font-size: small;">
											<h6>${g.label}</h6>
											${g.message}
										</div>
									</div>
								</c:forEach>
							</div>

						</div>


						<div class="form-row align-items-center" style="height: 90px;">
							<form:range max="365" name="frequence" min="1"
								value="${instruction.frequence}" label="Frequence" steps="10"
								unite="Jours" col="col-md-9" />

							<div class="col-md-3">
								<c:forEach items="${frequences}" var="g" varStatus="s">
									<div id="frequence-${s.index}" style="display: none;"
										class="media">
										<div class="media-body" style="font-size: small;">
											<h6>${g.label}</h6>
											${g.message}
										</div>
									</div>
								</c:forEach>

							</div>
						</div>



						<div class="form-row align-items-center" style="height: 90px;">
							<form:range max="4" name="niveau_detection" min="1"
								value="${instruction.niveau_detection }"
								label="Niveau de detection" col="col-md-9" />
							<div class="col-md-3">
								<c:forEach items="${niveaux}" var="g" varStatus="s">
									<div id="niveau_detection-${s.index}" style="display: none;"
										class="media">
										<div class="media-body" style="font-size: small;">
											<h6>${g.label}</h6>
											${g.message}
										</div>

									</div>
								</c:forEach>
							</div>
						</div>


						<form:textarea name="demarche_resolution" col="" rows="4"
							label="Demarche de resolution" />
					</div>

					<button name="instruction" type="submit"
						class="btn btn-primary w-100">Ajouter</button>

				</form>

				<table class="table ">
					<thead>
						<tr class="text-color">
							<th scope="col">#</th>
							<th scope="col">Piece</th>
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
								<td scope="row">${i.id}</td>
								<td>${i.piece.refrence}</td>
								<td>${i.defaillance.defaillance}</td>
								<td>${i.cause.cause}</td>
								<td>${i.effet.effet}</td>
								<td>${i.demarche_resolution}</td>
								<td>${i.gravite}</td>
								<td>${i.frequence}</td>
								<td>${i.niveau_detection}</td>
								<td>${i.criticite}</td>
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
			if (div.css('display') == "none") {
				e.preventDefault();
				div.show()
			}

		})

		//pour sycnhronisez le range avec la value au debut
		update("gravite", $("#gravite-range").val())
		update("frequence", $("#frequence-range").val())
		update("niveau_detection", $("#niveau_detection-range").val())

		$("#gravite-range").change(function() {
			update("gravite", $(this).val())
		});

		$('#gravite-range-value').on('input', function() {

			update("gravite", $(this).val())

		});

		$("#frequence-range").change(function(e) {
			update("frequence", e.target.value)
		});

		$("#niveau_detection-range").change(function(e) {
			update("niveau_detection", e.target.value)
		});

		$("#frequence-range-value").on('input', function(e) {
			update("frequence", e.target.value)
		});

		$("#niveau_detection-range-value").on('input', function(e) {
			update("niveau_detection", e.target.value)
		});

		function update(id, val) {
			var input = $("#" + id + "-range")
			var value = $("#" + id + "-range-value")

			if (input.val() != val) {

				$("#" + id + "-" + (convertFreq(id, input.val()) - 1)).hide()
				input.val(val)

			}

			if (value.val() != val) {

				$("#" + id + "-" + ((convertFreq(id, value.val()) - 1))).hide()
				value.val(val)

			}

			$("#" + id + "-" + ((convertFreq(id, val) - 1))).show()

		}

		function convertFreq(id, freq) {
			if (id == "frequence") {
				if (freq > 0) {
					console.log("converted :"
							+ (Math.round((freq / 365) * 3) + 1))
					return (Math.round((freq / 365) * 3) + 1)
				} else
					return 1
			} else
				return freq

		}
	</script>

</div>









