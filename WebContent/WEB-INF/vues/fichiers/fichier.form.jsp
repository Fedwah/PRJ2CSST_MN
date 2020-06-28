<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>

<div class="container-fluid p-5">

	<form method="post" action="" enctype="multipart/form-data" id="form">

		<form:file-upload name="file" col="" text="Importer un fichier"  />

		<div id="preview" class="row mb-1 d-none">
			<div class="col">
				<h5 class="d-inline">Nom du fichier :</h5>
				<span id="name"></span>
			</div>
			<div class="col">
				<h5 class="d-inline">Taille:</h5>
				<span id="taille"></span>
			</div>
			<div class="col">
				<h5 class="d-inline">Type:</h5>
				<span id="type"></span>
			</div>
			<div class="col-12">
				<h5>Description</h5>
				<form:textarea name="description" col="" rows="5" />
			</div>

		</div>
			<button id="btn-submit" type="submit" class="btn btn-primary col" name="importer">Importer</button>
			<div id="progress" class="btn btn-success col text-center mt-1" style="display:none;width: 0%;">
				100%
			</div>
			<div id="erreurs" class="alert alert-danger col mt-1 " style="display:none">
				
			</div>
	
	</form>



</div>

<script>
	var form = document.getElementById('form');
	
	
	//Adds a listener for the "submit" event.
	form.addEventListener('submit', function(e) {

		e.preventDefault();
		
		// get the form data
		// there are many ways to get this data using jQuery (you can use the class or id also)
		var data = new FormData();

		data.append('file',e.target[0].files[0]);
		data.append('description',e.target[1].value)
		
		

		// process the form
		$.ajax({
			type : 'POST', // define the type of HTTP verb we want to use (POST for our form)
			url : 'http://localhost:8080/EQ16_PRJ2CSST_MN/Fichier/importer', // the url where we want to POST
			data : data, // our data object
			dataType : 'json', // what type of data do we expect back from the server
			processData: false,
		    contentType: false,
			encode : true,
			xhr : function() {
				var xhr = new XMLHttpRequest();
				
				 // Upload progress
		        xhr.upload.addEventListener("progress", function(evt){
		            if (evt.lengthComputable) {
		                var percentComplete = evt.loaded / evt.total;
		                //Do something with upload progress
		                console.log(percentComplete);
		                $("#progress").css("width" , Math.round(percentComplete * 100) + "%")
		                $("#progress").html(Math.round(percentComplete * 100) + "%");
		            }
		       }, false);
			
				
				return xhr
			},
			error: function(xhr, status, error) {
				$("#erreurs").html(xhr.responseText);
				$("#erreurs").show();
			},
			beforeSend: function () {
		        $('#btn-submit').hide()
		        $("#progress").show()
		    },
		    complete: function () {
		        
		    },
		    success: function (json) {
		    	console.log(json)
		    	if(json[0].success){
		    		$("#progress").html(json[0].message);
		    	}else{
		    	
		    		$("#progress").removeClass("btn-success")
		    		$("#progress").addClass("btn-danger")
		    		$("#progress").html("Imporation echoué");
		    		$("#btn-submit").show();
		    		var str = "<ul>"
		    		console.log(json[0].erreurs)
		    		for(var p in json[0].erreurs) {
		    			console.log(p)
		    			str = str + "<li>"+ p+" : "+json[0].erreurs[p]+"</li>"
		    			
					}
		    		console.log(str)
		    		$("#erreurs").html(str+" </ul>")
		    		$("#erreurs").show()
		    	}
		        
		    }
		}).done()
		
		
	});

	//pour la preview de l'image
	$("#file").change(function() {
		if (this.files && this.files[0]) {

			$('#preview').removeClass('d-none')
			$('#name').text(this.files[0].name)
			$('#taille').text(this.files[0].size)
			$('#type').text(this.files[0].type)
			$("#progress").hide()
			$("#erreurs").hide()
			$("#progress").removeClass("btn-danger")
		    $("#progress").addClass("btn-success")
			$("#btn-submit").show()
		} else {
			$('#preview').addClass('d-none')
		}

	});


	
</script>