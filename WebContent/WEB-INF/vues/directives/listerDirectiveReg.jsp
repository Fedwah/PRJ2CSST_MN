 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid p-3">
	<div class="">
	    <h3 class="display-4" style='color: #3E703D;font-size:40px;font-style:gras;'>Liste des directives </h3>
		<!--  <h1 class="display-4 text-success">Liste des utilisateurs</h1>-->
		<div style="float:right; width:250px; height:auto;">
		<a class="btn btn-light rounded-circle"
					style='display: inline-block; height: 40px; width: 40px;position:relative; top:-3px; left:0px'
					href='<c:url value="/createDirective"/>'> <img width="50px"
					height="50px" style='position: relative; top: -10px; left: -17px;'
					src="<c:url value='/public/img/icon/add_green_nobackground.png'/>" />
				</a>
		 
			 </div>
		<nav class="nav justify-content-end mb-2">
			 
			 	   
		</nav>	
	              </div>
	             <div style="float:left; width:350px; height:auto;">
	        		
                <select id="type" class="form-control" required="required"name="type" onchange="show()" >
                    <option value=" ">Choisir le type de directives à afficher </option> 
					<option value="directives recues">directives reçues</option> 
					<option value="directives envoyees">directives envoyées</option> 
			   </select>
			  
			   </div>	     
	      
	
	<table class="table" id = 11>
		<thead>
			<tr style='color: #3E703D;' >
			    <th scope="col">Numéro de la directive</th>
				<th scope="col">Objet de la directive</th>
				<th scope="col">Contenu de la directive </th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${directives1}" var="directives1">
				<tr > 
				    <td>${directives1.id}</td>
					<td>${directives1.objet}</td>
					<td>${directives1.message}</td>	 	
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
  
	<table class="table" id =2>
		<thead>
			<tr style='color: #3E703D;' >
			   
			     
				<th scope="col">Objet de la diretive </th>
				<th scope="col">Contenu de la directive</th>
				<th scope="col">Envoyé à l'unité</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${directives}" var="directives">
				<tr > 
				 
					<td>${directives.objet}</td>
					<td>${directives.message}</td>
					<td>${directives.reciever_code}</td> 
					  
				</tr>
			</c:forEach>
		</tbody>
	</table>
 
 
</div>
 
<script>
 var select = document.getElementById("type");
 
 select.onchange=function()
 {   
	 
     if ( $("#type").val() === "directives envoyees")
     {    $("#11").hide ()
    	  $("#2").show ()
     } 
     if ( $("#type").val() === "directives recues")
     {    $("#2").hide ()
    	  $("#11").show ()
     }
     
     }
 </script>
 