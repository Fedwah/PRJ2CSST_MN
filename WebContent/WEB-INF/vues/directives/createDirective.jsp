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
                
               <label id="typel">Type du destinataire  </label> 
			   <div class="input-group mb-3"  >			
                <select id="type" class="form-control"  name="type"   onchange="show()" >
                 <option></option> 
			    <option>Operationnel</option> 
			    <option>Regional</option> 
			   </select>
		 	</div>	
                
                  <label id="distl">Destinataire opérationnel </label> 
			   <div class="input-group mb-3"  >			
                <select id="distop" class="form-control"  name="distreg"   onchange="show()" >
                <c:forEach items="${usersop}" var="usersop">
			    <option value ="${usersop.id}" >${usersop.codeun} ${usersop.nom} ${usersop.prenom}</option> 
			    <c:out value="${param.distop} " />
		        </c:forEach>
			   </select>
		 	</div>	
		 	
		 	    <label id="distl1">Destinataire regional </label> 
			   <div class="input-group mb-3"  >			
                <select id="distreg" class="form-control"  name="distreg"   onchange="show()" >
                <c:forEach items="${usersreg}" var="usersreg">
			    <option value ="${usersreg.id}" >${usersreg.codereg} ${usersreg.nom} ${usersreg.prenom}</option> 
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
        
        
 <script>
 var select = document.getElementById("type");
 
 select.onchange=function()
 {   
     

     if ( $("#type").val() === "Operationnel")
     {    $("#distl1").hide ()
    	  $("#distreg").hide () 
    	  $("#distl").show()
     	  $("#distop").show () 
  
     
     }
     if (  $("#type").val() ==="Regional")
     {
    	 $("#distl").hide ()
    	 $("#distop").hide () 
    	 $("#distl1").show ()
   	     $("#distreg").show () 
    	 
     
    	  
    	 
     }
  
 }
</script>



        
  