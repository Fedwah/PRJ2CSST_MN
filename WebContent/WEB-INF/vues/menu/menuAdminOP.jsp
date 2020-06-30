<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib tagdir="/WEB-INF/tags/menu" prefix="m"%>

<div class="nav flex-column  text-center  p-4" >
</div>

<div class=" overflow-hidden mt-1 border-right border-light">
<ul class="nav flex-column pl-1">



	<m:link label="Utilisateurs" value="/Utilisateurs" img="/public/img/menu/profile_nobackground.png" 
	size="38px" top="0px" left="10px" topI="0px" leftI="5px"
	/>
	
	<m:link label="Calendrier" value="/calendrier" img="/public/img/menu/white calendar.png" 
	size="35px" top="0px" left="16px" topI="0px" leftI="7px"
	/>
	
	<m:link label="Vehicules" value="/Vehicules" img="/public/img/menu/car.png" 
	size="35px" top="0px" left="18px" topI="0px" leftI="8px"
	/>
	
	<m:link label="Conducteur" value="/drivers" img="/public/img/menu/driver_white.png" 
	size="24px" top="0px" left="28px" topI="-2px" leftI="13px"
	/>
	
	<m:link label="Pièces" value="/pieces" img="/public/img/menu/white settings.png" 
	size="40px" top="0px" left="10px" topI="0px" leftI="5px"
	/>
	
	<m:link label="AMDEC" value="/amdec" img="/public/img/menu/analyse_white.png" 
	size="23px" top="0px" left="27px" topI="-2px" leftI="15px"
	/>
	
	<m:link label="Guides" value="/Fichiers" img="/public/img/menu/i.png" 
	size="22px" top="0px" left="27px" topI="-3px" leftI="15px"
	/>
	
	<m:link label="Directives" value="/listerDirective" img="/public/img/menu/directive.png" 
	size="25px" top="0px" left="27px" topI="-2px" leftI="15px"
	/>


</ul>
</div>