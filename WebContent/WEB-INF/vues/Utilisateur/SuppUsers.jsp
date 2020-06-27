 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
	<div class="">
		 
		<h3 class="display-4" style='color: #3E703D;font-size:40px;font-style:gras;'>Suppression des utilisateurs</h3>
	 	 <br />
	 	 
	    </div>
          <table class="table">
		<thead>
			<tr class="text-success">
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${Users}" var="usr">
				<tr>
				</tr>
			</c:forEach>
		</tbody>
	</table>

  <br />
  <br />
              
                 <p style="color:#3E703D" > ${result}</p>
                  <br />
            	 <!--  <a type="reset" class="btn btn-danger" href='<c:url value="/Utilisateurs"/>'>Retour à la liste des utilisateurs</a>--> 
            	 <a class="btn btn-outline-primary"  href='<c:url value="/Utilisateurs"/>' >Retouner à la liste des utilisateurs</a>  
  



