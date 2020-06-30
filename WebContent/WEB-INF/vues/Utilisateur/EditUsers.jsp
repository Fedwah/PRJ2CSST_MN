 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

         <form class="p-4 needs-validation " method="post"
		  action="<c:out value=""/>" >
             <!--   <form method="post" action="">-->
     
                <div class="col-md-9">
                 
                <p style="color:#0000FF" hidden =TRUE > ${param.id}=${id}</p>
                <input type="text"  hidden =TRUE  class='form-control' id="id" name="id" value="<c:out value="${id}"/>" size="20" maxlength="60" required />
                <br />
                   
                <label for="nomUtilisateur">Nom d'utilisateur </label>
                <input type="text"  class='form-control' id="nomUtilisateur" name="nomUtilisateur" value="<c:out value="${param.nomUtilisateur}"/>" size="20" maxlength="60" required />
                <span class="badge badge-pill badge-danger"  >${erreurs['motdepasse']}</span>
                  <br />
                <div style="float:left; width:350px; height:auto;">
                <label for="motdepasse">Mot de passe  </label>
                <input type="password"  class='form-control' id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" required />
                </div>
                <div style="float:right; width:350px; height:auto;">
                <label for="confirmation">Confirmation du mot de passe  </label>
                <input type="password" class='form-control' id="confirmation" name="confirmation" value="" size="20" maxlength="20" required />
                <span class="badge badge-pill badge-danger"   >${erreurs['confirmation']}</span>
                </div>
                <br />
              
               
              
				   
				 <br />
				
                <c:choose>
               	  <c:when test="${empty erreurs}"> 
				 <br />
               	  <p class=""style="color:#3E703D"> ${result}</p>
               	  <div style="float:left; width:50px; height:auto;">
               	  <button type="submit" class="btn btn-primary">Valider</button>
               	  </div>
               	  <div style="float:right; width:650px; height:auto;">
               	   <a type="reset" class="btn btn-outline-danger" href='<c:url value="/EditUser/${id1}"/>'>Rééssayer</a> 
               	  </div>
                  </c:when>
                  <c:otherwise>
				 <br />
                  <p class="" style="color:#bb0b0b" > ${result}</p> 
                 <div style="float:left; width:50px; height:auto;">
               	  <button type="submit" class="btn btn-primary">Valider</button>
               	  </div>
               	  <div style="float:right; width:650px; height:auto;">
               	   <a type="reset" class="btn btn-outline-danger" href='<c:url value="/EditUser/${id1}"/>'>Rééssayer</a> 
               	  </div>
                  </c:otherwise>
               	 </c:choose> 
             
      </div>
		   
      
        </form>
  



