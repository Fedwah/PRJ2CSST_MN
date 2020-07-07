  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="l" tagdir="/WEB-INF/tags/link"%>
      
              
                <form name="frm" method="post">
                <script>
				swal({
			  	title: "Etes you sure de vous déconnecter ?",
			  	icon: "warning",
			  	buttons: ["Non","Oui"],
			  	dangerMode: true,
				}).then((value) => {
					 if(value == true) document.frm.submit();
					 else window.history.go(-1);
					  })
					  
				</script>
         
             	</form>
         
     
  
 


 