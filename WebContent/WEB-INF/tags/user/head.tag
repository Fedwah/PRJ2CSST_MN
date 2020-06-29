<%@tag description="Le template de bouton" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/img" prefix="img"%>
<%@attribute name="label" required="true"%>

<!--  <div class="card broder rounded-pill bg-light m-2 "> -->
	  <div class="row no-gutters flex-nowrap align-items-end"> 
	
		<div class="mx-2 border rounded-pill  d-flex align-items-baseline bg-white">
			<img:card-img size="30px" value="" 
			default_img="/public/img/icon/profile_greenBackground.png" />
			<div class="mr-2">${label}</div>
		</div>
			
	<!--  	<div class="card-body p-0">
			
		<!-- 	<a class="btn btn-link  " href='<c:url value="/Deconnexion"/>'>
				Deconnexion </a>  
		</div> -->
	 </div> 


<!--</div>-->