<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	

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
									<option value = "${n}" >								
  										<div >${n.label }</div>
  										<div>${n.desc}</div>											
									 </option>
								</c:forEach>
							</select>
						</div>
						

						<label for="recruit">Date de début</label> 
						<input type="date"
							class="form-control ${empty erreurs['startDate']?'':'is-invalid'} "
							id="recruit" name="recruit"
							value="<c:out value="${maintenance.startDate}"/>">
						<div>
							<c:forEach items='${erreurs["startDate"]}' var="errD">
								<span class="badge badge-pill badge-danger">${errD}</span>
							</c:forEach>
						</div>
						<div class="mt-2">
						<label >Pieces de rechanges</label> 
						<ol id="olP">

						</ol>
						</div>
						<div class=" mt-1 col-md-16" >
						<button class="btn btn-success" id="btn2" style='width:829px'>Ajouter une autre piece</button>
						</div>
						</div>
					</div>
	<form class="p-4 needs-validation " method="post" action="<c:out value=""/>">
		<button type="submit" class="btn btn-primary" name="save">Valider</button>
		<a type="reset" class="btn btn-danger" href='<c:url value="/calendar"/>'>Annuler</a>
	</form>
</div>
<script>
$(document).ready(function(){
 

  $("#btn2").click(function(){
    $("#olP").append("<div class='mt-1 col-md-16'><li><c:set var='i' value='${i + 1}'></c:set><select id='marque' class='form-control' name='${i}''><c:forEach items='${piece}' var='p'><option value = '${p.id}'> ${p.pieceName}</option></c:forEach></select></li></div>");
  });
});
</script>