 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

         <form class="p-4 needs-validation " method="post"
		  action="<c:out value=""/>" >
              <!--  <form method="post" action="createUser"> -->
     
                <div class="form-group col-md-4">
                <label for="nomUtilisateur">Nom d'utilisateur <span class=>*</span></label>
                <input type="text"  class='form-control' id="nomUtilisateur" name="nomUtilisateur" value="<c:out value="${param.nomUser}"/>" size="20" maxlength="60" required />
                <br />

                <label for="motdepasse">Mot de passe <span class=>*</span></label>
                <input type="password"  class='form-control' id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" required />
                <span class="badge badge-pill badge-danger"  >${erreurs['motdepasse']}</span>
                <br />

                <label for="confirmation">Confirmation du mot de passe <span class=>*</span></label>
                <input type="password" class='form-control' id="confirmation" name="confirmation" value="" size="20" maxlength="20" required />
                <span class="badge badge-pill badge-danger"   >${erreurs['confirmation']}</span>
                <br />

                <label for="nom">Nom <span class=>*</span></label>
                <input type="text" class='form-control'  id="nom" name="nom" value="<c:out value="${param.nom}"/>" size="20" maxlength="20" required/>
                <br />
                
                 <label for="prenom">Prenom <span class=>*</span></label>
                <input type="text" class='form-control' id="prenom" name="prenom" value="<c:out value="${param.prenom}"/>" size="20" maxlength="20" required />
                 
                <br />
              
                 
                 <label for="type">Type de l'utilisateur <span class=>*</span></label>
			   <div class="input-group mb-3">			
                <select id="type" class="form-control" required="required"name="type" onchange="show()" >
                <c:forEach items="${type}" var="type">
					<option>${type}</option> 
					  <c:out value="${param.type}" />
		        </c:forEach>
			   </select>
			   </div>	
                 
                <label for="role">Role de l'utilisateur <span class=>*</span></label>
			   <div class="input-group mb-3">			
                <select id="role" class="form-control" required="required"name="role" onchange="show()" >
                <c:forEach items="${role}" var="role">
					<option>${role}</option> 
					  <c:out value="${param.role}" />
		        </c:forEach>
			   </select>
		 	</div>	
		
               	<button type="submit" class="btn btn-primary">Valider</button>
               	<a type="reset" class="btn btn-danger" href='<c:url value="/Utilisateurs"/>'>Annuler</a>
              
               	 <c:choose>
               	  <c:when test="${empty erreurs}"> 
               	  <p class=""style="color:#008000"> ${resultat}</p>
                  </c:when>
                  <c:otherwise>
                  <p class="" style="color:#bb0b0b" > ${resultat}</p> 
                  </c:otherwise>
               	 </c:choose>
             
      </div>
        </form>
  



