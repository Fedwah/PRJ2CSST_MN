   <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/link"%>
         <form class="p-4 needs-validation " method="post"
		  action="<c:out value=""/>" >
              
                    	<nav class="nav justify-content-end mb-2">
                  
                    	</nav>
                    	 
                    	
                
                
			   <div class="input-group mb-3"  >			
                <select id="origin" class="form-control"  name="origin" hidden=true   onchange="show()" >
			    <option>${sessionScope.sessionUtilisateur.id}</option> 
			    <c:out value="${param.origin}"/>
			   </select>
		 	</div>	
                <div class="col-md-9">
                <label for="objetlabel">Objet de la directive <span class=></span></label>
                <input type="text"  class='form-control' id="objet" name="objet" value="<c:out value="${param.objet}"/>" size="20" maxlength="60" required />
                <br />
                
                  <label id="distl">Destinataire opérationnel </label> 
			   <div class="input-group mb-3"  >			
                 <select id="distreg" class="form-control"  name="distreg"   >
                 <c:forEach items="${usersop}" var="usersop">
                <optgroup label= "${usersop.codeun}">
                  <option value="${usersop.id}"> ${usersop.nom} ${usersop.prenom}</option>
                  </optgroup>
			    <c:out value="${param.distreg}" />
		        </c:forEach>
			   </select>
		 	</div>	
		 	
		 	 	
		 	
		 	 <label for="messagel">Message de la directive </label>
             <input type="text"  class='form-control' id="message" name="message" value="<c:out value="${param.message}"/>" size="20" maxlength="160" required />
             <br />
		 	
		 	</div>	
		  
		 		 
		
               	<button type="submit" class="btn btn-primary">Valider</button>
               	<a type="reset" class="btn btn-danger" href='<c:url value="/listerDirective"/>'>Annuler</a>
               	<p class=""style="color:#3E703D"> ${resultat}</p>
                  
        </form>
        
        
 


        
  