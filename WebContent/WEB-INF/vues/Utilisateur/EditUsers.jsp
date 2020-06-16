 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

         <form class="p-4 needs-validation " method="post"
		  action="<c:out value=""/>" >
             <!--   <form method="post" action="">-->
     
                 <div class="form-group col-md-4">
                 
                <p style="color:#0000FF" hidden =TRUE > ${param.id}=${id}</p>
                <input type="text"  hidden =TRUE  class='form-control' id="id" name="id" value="<c:out value="${id}"/>" size="20" maxlength="60" required />
                <br />
                <label for="nomUtilisateur">Nom d'utilisateur <span class=>*</span></label>
                <input type="text"  class='form-control' id="nomUtilisateur" name="nomUtilisateur" value="<c:out value="${param.nomUtilisateur}"/>" size="20" maxlength="60" required />
                <span class="badge badge-pill badge-danger"  >${erreurs['motdepasse']}</span>
                <br />

                <label for="motdepasse">Mot de passe <span class=>*</span></label>
                <input type="password"  class='form-control' id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" required />
                <br />
                
                <label for="confirmation">Confirmation du mot de passe <span class=>*</span></label>
                <input type="password" class='form-control' id="confirmation" name="confirmation" value="" size="20" maxlength="20" required />
                <span class="badge badge-pill badge-danger"   >${erreurs['confirmation']}</span>
                <br />
              
                     <label for="role">Role de l'utilisateur <span class=>*</span></label>
			   <div class="input-group mb-3">			
                <select id="role" class="form-control" required="required"name="role" onchange="show()" >
                <c:forEach items="${role}" var="role">
					<option>${role}</option> 
					  <c:out value="${param.role}" />
		        </c:forEach>
			   </select>
		 	</div>	
				
				
				
              
		 
			 	 
                <c:choose>
               	  <c:when test="${empty erreurs}"> 
               	  <button type="submit" class="btn btn-primary">Valider</button>
               	  <a type="reset" class="btn btn-outline-primary" href='<c:url value="/Utilisateurs"/>'>Retourner à la liste des utilisateurs</a>
               	  <p class=""style="color:#008000"> ${result}</p>
               	  
 
                   </c:when>
                  <c:otherwise>
                  <a type="reset" class="btn btn-outline-danger" href='<c:url value="/EditUser/${id1}"/>'>Rééssayer</a>
                  <br />
                  <br />
                  <button type="submit" class="btn btn-primary">Valider</button>
                  <a type="reset" class="btn btn-outline-primary"  href='<c:url value="/Utilisateurs"/>'>Retourner à la liste des utilisateurs</a>
                  <br />
                  <p class="" style="color:#bb0b0b" > ${result}</p> 
                  </c:otherwise>
               	 </c:choose> 
             
      </div>
		   
      
        </form>
  



