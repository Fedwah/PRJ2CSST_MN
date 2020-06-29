 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/link"%>
         <form class="p-4 needs-validation " method="post"
		  action="<c:out value=""/>" >
              <!--  <form method="post" action="createUser"> -->
                    	<nav class="nav justify-content-end mb-2">
                 <a class="btn btn-outline-success"
		           	 href='<c:url value="/ImporterUsers"/>'>Importer un fichier </a>
                    	</nav>
                <div class="col-md-9">
                <label for="nomUtilisateur">Nom d'utilisateur <span class=></span></label>
                <input type="text"  class='form-control' id="nomUtilisateur" name="nomUtilisateur" value="<c:out value="${param.nomUser}"/>" size="20" maxlength="60" required />
                <br />
                
                
                <div style="float:left; width:350px; height:auto;">
                <label for="motdepasse">Mot de passe <span class=></span></label>
                <input type="password"  class='form-control' id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" required />
                <span class="badge badge-pill badge-danger"  >${erreurs['motdepasse']}</span>
                </div>
                <div style="float:right; width:350px; height:auto;">
                <label for="confirmation">Confirmation du mot de passe <span class=></span></label>
                <input type="password" class='form-control' id="confirmation" name="confirmation" value="" size="20" maxlength="20" required />
                <span class="badge badge-pill badge-danger"   >${erreurs['confirmation']}</span>
                </div>
               	<div style="clear:both"></div>
                <br />
                
                
                <div style="float:left; width:350px; height:auto;">
                <label for="nom">Nom <span class=></span></label>
                <input type="text" class='form-control'  id="nom" name="nom" value="<c:out value="${param.nom}"/>" size="20" maxlength="20" required/>
                </div>
                <div style="float:right; width:350px; height:auto;">
                <label for="prenom">Prenom <span class=></span></label>
                <input type="text" class='form-control' id="prenom" name="prenom" value="<c:out value="${param.prenom}"/>" size="20" maxlength="20" required />
                </div>
                <div style="clear:both"></div>
                <br />
              
                  
               <label for="type">Type de l'utilisateur <span class=></span></label>
			   <div class="input-group mb-3">			
                <select id="type" class="form-control" required="required"name="type" onchange="show()" >
                    
					<option value="Operationnel">Operationnel</option> 
					<option value="Regional">Regional</option> 
					<option value="Central">Central</option> 
					<c:out value="${param.type}" />
		 
			   </select>
			  
			   </div>	
                  
               <label id="rolelabel">Role de l'utilisateur  </label> 
			   <div class="input-group mb-3"  >			
                <select id="role" class="form-control"  name="role"   onchange="show()" >
                <c:forEach items="${role}" var="role">
			    <option>${role}</option> 
			    <c:out value="${param.role}" />
		        </c:forEach>
			   </select>
		  
		 	</div>	
		 	
		   
		 	<label id="codereglabel">Region  de l'utilisateur </label> 
			<div class="input-group mb-3"  >			
                <select id="codereg" class="form-control"  name="codereg"  onchange="show()" >
                <c:forEach items="${reg}" var="reg1">
			    <option>${reg1.codeReg} </option> 
			    <c:out value="${param.codereg}" />
		        </c:forEach>
			   </select>
		 	 
		 	</div>	
		  
		 	
		 	 
		    	<label id="codeunlabel">Unite  de l'utilisateur  </label>
			   <div class="input-group mb-3">			
                <select id="codeun" class="form-control"  name="codeun"   onchange="show()" >
                <c:forEach items="${un}" var="un1">
                
                <option> ${un1.codeUN} </option>
				<c:out value="${param.codeun}" />
		        </c:forEach>
			   </select>
		 	 
		 	 </div>	
		 	
		 	
		      <label id="postelabel">Poste de l'utilisateur au sein de l'unité operationnelle  </label> 
			   <div class="input-group mb-3" >			
                <select id="poste" class="form-control"  name="poste" onchange="show()" >
                 
                 <option value="Responsable de maintenance"> Responsable de maintenance </option>
                 <option value="Responsable du parc" > Responsable du parc </option>
				 <c:out value="${param.poste}" />  
			   </select>
		 	</div>	
		 	 </div>	
		  
		 		 
		
               	<button type="submit" class="btn btn-primary">Valider</button>
               	<a type="reset" class="btn btn-danger" href='<c:url value="/Utilisateurs"/>'>Annuler</a>
              
               	 <c:choose>
               	  <c:when test="${empty erreurs}"> 
               	  <p class=""style="color:#3E703D"> ${resultat}</p>
                  </c:when>
                  <c:otherwise>
                  <p class="" style="color:#bb0b0b" > ${resultat}</p> 
                  </c:otherwise>
               	 </c:choose>
             
         
        </form>
  
 <script>
 var select = document.getElementById("type");
 
 select.onchange=function()
 {   
     

     if ( $("#type").val() === "Central")
     {    $("#poste").hide ()
    	  $("#poste").val ()==""
    	  $("#postelabel").hide () 
    	  $("#codeun").hide ()
    
    	  $("#codeunlabel").hide () 
    	  $("#codereg").hide ()
    	 
    	  $("#codereglabel").hide () 
    	  $("#role").hide ()
          $("#rolelabel").hide ()
     
     }
     if (  $("#type").val() ==="Regional")
     {
    	 $("#poste").hide ()
    	 $("#postelabel").hide () 
    	 $("#codeun").hide ()
    	 $("#codeunlabel").hide () 
    	 $("#codereg").show ()
    	 $("#codereglabel").show () 
    	 $("#role").hide ()
         $("#rolelabel").hide ()
     
    	  
    	 
     }
     if ($("#type").val() == "Operationnel"){
     $("#poste").show ()
     $("#postelabel").show ()
     $("#codeun").show ()
     $("#codeunlabel").show () 
     $("#role").show ()
     $("#rolelabel").show ()
	 $("#codereg").show ()
	 $("#codereglabel").show ()
	  
     }
 }
</script>



