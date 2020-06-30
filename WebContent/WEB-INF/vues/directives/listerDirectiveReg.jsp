 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid p-3">
	<div class="">
	    <h3 class="display-4" style='color: #3E703D;font-size:40px;font-style:gras;'>Liste des directives </h3>
		<!--  <h1 class="display-4 text-success">Liste des utilisateurs</h1>-->
		<a class="btn btn-outline-success"
			 href='<c:url value="/createDirective"/>'>Ajouter une directive</a>
		<nav class="nav justify-content-end mb-2">
			 
			 	   
		</nav>	
	              </div>
	            
	        <div class="input-group mb-3">			
                <select id="type" class="form-control" required="required"name="type" onchange="show()" >
                    <option value=" "> </option> 
					<option value="directives recues">directives recues</option> 
					<option value="directives envoyees">directives envoyees</option> 
			   </select>
			  
			   </div>	     
	      
	
	<table class="table" id = 1>
		<thead>
			<tr style='color: #3E703D;' >
			   
				<th scope="col">Objet de la directive</th>
				<th scope="col">Contenu de la directive </th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${directives1}" var="directives1">
				<tr > 
				     
					<td>${directives1.objet}</td>
					<td>${directives1.message}</td>	 	 
				</tr>
			</c:forEach>
		</tbody>
	</table>
	 ${utilisateurs.nom} 
	<table class="table" id =2>
		<thead>
			<tr style='color: #3E703D;' >
			    <th scope="col" hidden = TRUE>Id</th>
				<th scope="col">Objet de la diretive </th>
				<th scope="col">Contenu de la directive</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${directives}" var="directives">
				<tr > 
				     
					<td>${directives.objet}</td>
					<td>${directives.message}</td>
					  
				</tr>
			</c:forEach>
		</tbody>
	</table>
 
 
</div>
 
<script>
 var select = document.getElementById("type");
 
 select.onchange=function()
 {   
     

     if ( $("#type").val() === "directives recues")
     {    $("#2").hide ()
    	 $("#1").show ()
     }
     if ( $("#type").val() === "directives envoyees")
     {    $("#1").hide ()
    	 $("#2").show ()
     } 
     
     }
 </script>
 