 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/link"%>
         <form class="p-4 needs-validation " method="post"
		  action="<c:out value=""/>" >
                    	<nav class="nav justify-content-end mb-2"></nav>
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
              
		 	
		 	
		      <label id="postelabel">Poste de l'utilisateur au sein de l'unité operationnelle  </label> 
			   <div class="input-group mb-3" >			
                <select id="poste" class="form-control"  name="poste" onchange="show()" >
                 <option value="">  </option>
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
  
 