  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/link"%>
      
              
                
                
             
		        <label for="type">Etes-vous sures de vous déconnecter ? <span class=>*</span></label>
               	 
               	<a type="reset" class="btn btn-danger" style='color:  #FFFFFF;' href='<c:url value="/Deconnexion"/>'>Oui</a>
                <a type="reset" class="btn btn-danger" style='color:  #FFFFFF;'  onclick="history.go(-1)" > Non</a>
         
             
         
     
  
 


 