<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid">
	
	<form class="needs-validation mx-5" method="post"
		action="<c:out value="add"/>" novalidate enctype="multipart/form-data">
		
		<form:input name="titre" col="" type="text" value="${marque.titre}" 
			label="Nom de la marque" erreurs_="${erreurs['titre']}" >
		</form:input>
		
		<form:img-upload label="Photo de la marque" name="image" 
			col="" image="${marque.image}" erreurs_="${erreurs['image']}"  >
		</form:img-upload>
	
	
	
	
	
		<div class="form-group">
			<button type="submit" class="btn btn-primary btn-block">Valider</button>
			<btn:btn type="danger" value="/Marques" text="Annuler" outline="${false}" class_="w-100 mt-1" />
		</div>


	</form>
</div>
<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]); // convert to base64 string
		}
	}

	
	$("#photo").change(function() {
		readURL(this);
	});
</script>
