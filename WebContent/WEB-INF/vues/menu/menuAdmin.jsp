<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/menu" prefix="menu"%>

<div  class="overflow-hidden mt-1 border-right border-light">  
<ul  class="nav flex-column pl-1">
  
	<menu:link label="Regions" value="/regions" img="/public/img/menu/localisation_white_nobackground.png" 
	size="28px" top="0px" left="20px" topI="-2px" leftI="10px"
	/>			
	<menu:link label="Utilisateurs" value="/Utilisateurs" img="/public/img/menu/profile_nobackground.png" 
	size="38px" top="0px" left="10px" topI="0px" leftI="5px"
	/>
	
	<menu:link label="Pièces" value="/pieces" img="/public/img/menu/white settings.png"
	size="40px" top="0px" left="10px" topI="0px" leftI="5px"
	/>
	
	<menu:link label="Marques" value="/Marques" img="/public/img/menu/brand.png" 
	size="23px" top="0px" left="28px" topI="-3px" leftI="13px"
	/>
	
	<menu:link label="Catégorie" value="/Vehicules/Categories" img="/public/img/menu/category.png" 
	size="23px" top="0px" left="28px" topI="-3px" leftI="12px"
	/>
	
	<menu:link label="Niveaux" value="/maintenance/niveaux" img="/public/img/menu/level.png" 
	size="20px" top="0px" left="32px" topI="-3px" leftI="15px"
	/>
	
	<menu:link label="Vehicules" value="/Vehicules" img="/public/img/menu/car.png" 
	size="35px" top="0px" left="18px" topI="0px" leftI="8px"
	 />
	
	<menu:link label="Import/Export" value="/Importer/"  img="/public/img/menu/export.png" 
	
	size="25px" top="0px" left="25px" topI="-3px" leftI="13px"
	/>

</ul>
</div>