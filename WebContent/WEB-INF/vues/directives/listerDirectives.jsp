 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid p-3">
	<div class="">
	    <h3 class="display-4" style='color: #3E703D;font-size:40px;font-style:gras;'>Liste des directives reçues </h3>
		<!--  <h1 class="display-4 text-success">Liste des utilisateurs</h1>-->
		<nav class="nav justify-content-end mb-2">
			 
			 	   
		</nav>	
	              </div>
	            
	            
	      
	
	<table class="table">
		<thead>
			<tr style='color: #3E703D;' >
			     
				<th scope="col">Objet</th>
				<th scope="col">Contenu </th>
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
 
</div>
</div>

 