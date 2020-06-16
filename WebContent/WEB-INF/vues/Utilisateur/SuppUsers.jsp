 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
	<div class="">
		<h1 class="display-4 text-success">Volet suppression des utilisateurs</h1>
	 	 <br />
	 	  <br />
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
              
                 <p style="color:#008000" > ${result}</p>
            	<a type="reset" class="btn btn-danger" href='<c:url value="/Utilisateurs"/>'>Retour à la liste des utilisateurs</a>  
  



