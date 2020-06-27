 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/link"%>
         <form class="p-4 needs-validation " method="post"
		  action="<c:out value=""/>" >
            
               
                <div style="float:left; width:550px; height:auto;">	 
                <div class="col-md-9">
                <label for="nomUtilisateur">Nom d'utilisateur <span class=>*</span></label>
                <input type="text"  class='form-control' id="nomUtilisateur" name="nomUtilisateur" value="<c:out value="${param.nomUser}"/>" size="20" maxlength="60" required />
                <br />
                
                
              
                <label for="motdepasse">Mot de passe <span class=>*</span></label>
                <input type="password"  class='form-control' id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" required />
                <span class="badge badge-pill badge-danger"  >${erreurs['motdepasse']}</span>
                </div>
                 <br />
                 <br />
                
		 		 
		
               	<button type="submit" class="btn btn-primary">Valider</button>
               
              
               	 <c:choose>
               	  <c:when test="${empty erreurs}"> 
               	  <p class=""style="color:#3E703D"> ${resultat}</p>
                  </c:when>
                  <c:otherwise>
                  <p class="" style="color:#bb0b0b" > ${resultat}</p> 
                  </c:otherwise>
               	 </c:choose>
             
         </div>
        </form>
  
 


