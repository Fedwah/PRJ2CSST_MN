<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/menu" prefix="menu"%>

<div  class="overflow-hidden mt-5 border-right border-light">


<ul class="nav  flex-column pl-3" >	


	<c:if test="${!empty sessionScope.sessionUtilisateur}">
                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                    <p class="succes">Bonjour ${sessionScope.sessionUtilisateur.nom} !</p>
     </c:if>
  
  
 
  
	<menu:link label="Regions" value="/regions" img="/public/img/menu/localisation_white_nobackground.png" size="30px"/>
	<menu:link label="Utilisateurs" value="/Utilisateurs" img="/public/img/menu/profile_nobackground.png" size="30px"/>
	<menu:link label="Pièces" value="/pieces" img="/public/img/menu/white settings.png" size="30px"/>
	<menu:link label="Marques" value="/Marques" img="" size="30px"/>
	<menu:link label="Catégorie" value="/Vehicules/Categories" img="" size="30px"/>
	<menu:link label="Catégorie" value="/maintenance/niveaux" img="" size="30px"/>
	<menu:link label="Vehicules" value="/Vehicules" img="/public/img/menu/car.png" size="30px" />
	<menu:link label="Importer/Exporter" value="/Importer/"  img="/public/img/menu/doc icon.png" size="30px"/>
     
</ul>
</div>

