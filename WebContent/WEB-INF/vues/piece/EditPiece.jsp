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
					<label >Reference de la piece</label> 
					<input
						type="text" class='form-control' name="ref"
						value="<c:out value="${piece.refrence}" />" 
						${disabled_id? 'disabled':''}
						/>
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
								<button id="removeli" class="btn btn-outline-success" style='width:35px;position:relative; top:0px;left:19px;'>-</button>
							</div>
							<input id="cptLi" name="cpt" value="${piece.modals.size()}" type="hidden" />
							<c:set var="i" value="0"></c:set>
							<div class='mt-1 col-md-16' id="newModele">
								<ol id="olP">
									
									<c:forEach var="i" begin='1' end="${piece.modals.size()}">
									<li id='liM'>
									<select id='${i}' class='form-control' required='required' name='${i}'>
											<c:forEach items='${marques}' var='marq'>
												<optgroup label='${marq.titre}'>
													<c:forEach items='${marq.modeles}' var='m'>
														<option  ${m.id==piece.modals.get(i-1).id?"selected":""} value='${m.id}'>${m.titre}</option>
													</c:forEach>
												</optgroup>
											</c:forEach>
									</select>
									</li>
									</c:forEach>
									
								</ol>
							
							</div>
						</div>
						<div class=" mt-1 col-md-16">
							<button class="btn btn-success" id="btn2" style='width: 829px'>Ajouter un autre modele</button>
						</div>
					</div>

				</div>
			</div>
		</div>
		<button type="submit" class="btn btn-primary" name="send">Valider</button>		
		<a type="reset" class="btn btn-danger" href='<c:url value="/pieces"/>'>Annuler</a>
	</form>
</div>
<script>
//remove li 
document.querySelector("#removeli").addEventListener("click",function(e) {
if(parseInt($("#cptLi").val())>1)
{
	$('#olP li:last-child').remove();
	$("#cptLi").val(parseInt($("#cptLi").val()) - 1);		
}

e.preventDefault();
}, false);
	document.querySelector("#btn2").addEventListener("click",function(event) {
	$("#cptLi").val(parseInt($("#cptLi").val()) + 1);
	var nom = $("#cptLi").val();

	var element = "<li id='liM'><select class='form-control' required='required'name="+nom+"><c:forEach items='${marques}' var='marq'>optgroup label='${marq.titre}'><c:forEach items='${marq.modeles}' var='m'><option value='${m.id}'>${m.titre}</option></c:forEach></optgroup></c:forEach></select></li>";
	$("#olP").append(element);
	event.preventDefault();
	}, false);
</script>