<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<div class="container-fluid ">
	<div class="">
	    <h3 class="display" style='color: #3E703D;font-size:40px;font-style:gras;'>Liste des utilisateurs</h3>
		 
		<nav class="nav justify-content-end mb-2">
			<a class="btn btn-outline-success"
			 href='<c:url value="/createUser"/>'>Ajouter un utilisateur</a>
			 	   
		</nav>	
		          <div class="form-row">
		  	     
		  	     <img height="40px" width="40px" style='position: relative; top: -6px; left: -11px;' src='<c:url value="/public/img/icon/search_green_nobackground.png"/>' />
			     
			     <div style="float:left; width:550px; height:auto;">
			     <form class="" method ="post" action="Utilisateurs">
				  <input type="text" class="form-control" id="search" name="search" placeholder="Entrez le nom et cliquez sur Entrée"  value="<c:out value="${param.search}"/>">
		        </form>  
	            </div>
	            
	             <br/>
	             <br/>
	              
		 	     	    <form class="" method ="post" action="Utilisateurs">   
		 	       <div style="float:left; width:200px; height:auto;">		
                   <select id="filtreVal" class="form-control" required="required"name="filtreVal" onchange="show()" >
                 
                     <option >Operationnel</option>
					 <option>Regional</option>
					 <option>Central</option>
                      <option>Admin</option>
                      <option>Utilisateur</option> 
                  
					 <c:out value="${param.filtreVal}" />
		            </select>
		              </div>	 
		             <div style="float:right; width:100px; height:auto;">	
		               <button type="submit" class="btn btn-primary">Filtrer</button>
		 	      	</div>	
		 	      	
		 	             
	              
          
              
                
				  </form>  	     
	           
	              </div>
	            
	            
	      
	
	<table class="table">
		<thead>
			<tr style='color: #3E703D;' >
			    <th scope="col" hidden = TRUE>Id</th>
				<th scope="col">Nom</th>
				<th scope="col">Prenom</th>
				<th scope="col">Nom d'utilisateur</th>
				<th scope="col">Type</th>
			    <th scope="col">Role</th>
			    <th scope="col">Unite</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${Users}" var="usr">
				<tr > 
				    <td  hidden = TRUE>${usr.id}</td>
					<td>${usr.nom}</td>
					<td>${usr.prenom}</td>
					<td>${usr.nomUtilisateur}</td>
					<td>${usr.type}</td>
					<td>${usr.role}</td>
					<td>${usr.codeun}</td>
					 
					<td>
					<btn:edit value="/EditUser/${usr.id}" /> 
					<btn:remove value="/SuppUser/${usr.id}" /> 
					</td>	 
				</tr>
			</c:forEach>
		</tbody>
	</table>
 
</div>
</div>

