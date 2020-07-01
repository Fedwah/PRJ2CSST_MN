<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<form class="p-4 needs-validation " method="post" id="subF"
		action="<c:out value=""/>">
		<!-- ${ erreurs } <!-- pour tester -->

		<div class="form-row">
			<div class="form-group col-md-9">
				<div class="form-group ">
					<label for="nompiece">Reference de la piece</label> <input
						type="text" class='form-control' name="ref"
						value="<c:out value="${piece.reference}" />" />
					<c:forEach items='${erreurs["refrence"]}' var="errf">
						<span class="badge badge-pill badge-danger">${errf}</span>
					</c:forEach>
				</div>
				<div class="form-group ">
					<label for="nompiece">Nom de la piece</label> <input type="text"
						class='form-control' name="nom"
						value="<c:out value="${piece.pieceName}" />" />
					<c:forEach items='${erreurs["pieceName"]}' var="errf">
						<span class="badge badge-pill badge-danger">${errf}</span>
					</c:forEach>
				</div>
				<div class="form-row">
					<div class="form-group col-md-12">
						<c:forEach items='${erreurs["modals"]}' var="errf">
							<span class="badge badge-pill badge-danger">${errf}</span>
						</c:forEach>
						<div class="mt-2" id="modalsList">
							<div class="row">
								<label class="col-11">Modele</label>
								<div class="input-group-append" align="right">
									<button class="btn btn-outline-success" id="zap"
										style='width: 40px; position: relative; top: 0px; left: 14px;'>-</button>
								</div>
							</div>
							<input id="cptLi" name="cpt" value="1" type="hidden" />
							<c:set var="i" value="0"></c:set>
							<div class='mt-1 col-md-16' id="newModele">

										<select id='1' class='form-control' required='required'
											name='1'>
											<c:forEach items='${marques}' var='marq'>
												<optgroup label='${marq.titre}'>
													<c:forEach items='${marq.modeles}' var='m'>
														<option ${piece.modal.titre==m.titre?'selected':""}
															value='${m.id}'>${m.titre}</option>
													</c:forEach>
												</optgroup>
											</c:forEach>
										</select>
							</div>
						</div>
						<div class=" mt-1 col-md-16">
							<button class="btn btn-success" id="btn2" style='width: 829px'>Ajouter
								un autre modele</button>
						</div>
					</div>

				</div>
			</div>
		</div>


		<button type="submit" class="btn btn-primary" id="send">Valider</button>
		<a type="reset" class="btn btn-danger" href='<c:url value="/pieces"/>'>Annuler</a>
	</form>
</div>
<script>

$( document ).ready(function(){
	   $("#zap").on("click", function(){
		    $("#liM").first().remove();
	   });
	});

document.querySelector("#btn2").addEventListener("click", function(event) {
	var element = document.getElementById($("#cptLi").val());
	$("#cptLi").val(parseInt($("#cptLi").val()) + 1);	
	$( "#newModele" ).clone().appendTo( "#modalsList" );
	
	element.id = $("#cptLi").val();
	element.name = $("#cptLi").val();
   // $("#olP").append("<li ><div class='mt-1 col-md-16'><select id='modele' class='form-control' required='required' name='${i}'><c:forEach items='${marques}' var='marq'><optgroup label='${marq.titre}'><c:forEach items='${marq.modeles}' var='m'><option ${piece.modal.titre==m.titre?'selected':""} value='${m.id}'>${m.titre}</option></c:forEach></optgroup></c:forEach></select></div></li>");
    
     event.preventDefault();
}, false);
</script>