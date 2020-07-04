<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
<form class="p-4 needs-validation " method="post" action="<c:out value=""/>">	

		<!-- ${ erreurs } <!-- pour tester -->

		<div class="form-row">
			
			<div class="form-group col-md-9">
				<div class="form-group ">
					<label for="mat">Matricule Interne</label> 
					<input
						type="text"
						class='form-control '
						id="mat" name="matricule"
						
						value="<c:out value="${Vehicule.matricule_interne}" 
						/>"
						
					>
					<c:forEach items='${erreurs["v"]}' var="errl">
								<span class="badge badge-pill badge-danger">${errl}</span>
					</c:forEach>
					</div>


						<label for="marque">Niveaux de maintenance</label>
						<div class="input-group mb-3">
							<select id="marque" class="form-control" name="niveau" >
								<c:forEach items="${niveaux}" var="n">
									<option value = "${n.ordinal()}" title="${n.desc}" >								
  										${n.label }										
									 </option>
								</c:forEach>
							</select>
						</div>
						

						<label for="recruit">Date de début</label> 
						<input type="date"
							class="form-control ${empty erreurs['startDate']?'':'is-invalid'} "
							id="recruit" name="recruit" required
							value="<c:out value="${maintenance.startDate}"/>">
						<div>
							<c:forEach items='${erreurs["startDate"]}' var="errD">
								<span class="badge badge-pill badge-danger">${errD}</span>
							</c:forEach>
						</div>
						<div class="mt-2">
						<label class="col-10">Instructions</label> 
						<a tyepe="reset" class="btn btn-outline-success" href='<c:url value="/maintenance/add/${Vehicule.matricule_interne}"/>'>Réinitialiser</a>
						<input id="cptLi" name="cpt" value="1" type="hidden" />
						<div class="p-1"></div>
						<c:forEach items='${erreurs["instructions"]}' var="errl">
								<span class="badge badge-pill badge-danger">${errl}</span>
						</c:forEach>
						<ol id="olP">
						<li class="pb-1">
						
						<input list="browsers" class="form-control" name="1" id="browser">

						<datalist id="browsers" >
  							<c:forEach items="${instruction}" var= "i">
  							
  							<option value="${i.id }">Defaillance : ${i.defaillance.defaillance}, 
  							Cause:${i.cause.cause}, Effet: ${i.effet.effet}, Démarche: ${i.demarche_resolution} 
  							</option>
  							</c:forEach>
						</datalist>
						</li>
						</ol>
						</div>
						<div class=" mt-1 col-md-16" >
						<button  class="btn btn-success" id="btn2" style='width:812px'>Ajouter une autre instruction</button>
						</div>
						</div>
					</div>
	
		<button type="submit" class="btn btn-primary" name="save">Valider</button>
		
		<a type="reset" class="btn btn-danger" href='<c:url value="/Vehicules/${Vehicule.matricule_interne}"/>'>Annuler</a>
	</form>
</div>
<script>
	document.querySelector("#btn2").addEventListener("click",function(e) {
	$("#cptLi").val(parseInt($("#cptLi").val()) + 1);
	var nom = $("#cptLi").val();

	var element = "<li class='pb-1'><input list='browsers' class='form-control' name="+nom + " ><datalist><c:forEach items='${instruction}' var= 'i'>" +
	"<option value='${i.id }'>Defaillance : ${i.defaillance.defaillance}, Cause:${i.cause.cause}, Effet: ${i.effet.effet} </option></c:forEach></datalist></li>";
	$("#olP").append(element);
	e.preventDefault();
	}, false);
</script>