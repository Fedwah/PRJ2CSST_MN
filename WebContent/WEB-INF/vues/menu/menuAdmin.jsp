<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/menu" prefix="menu"%>

<div  class="overflow-hidden mt-5">

<ul class="nav flex-column p-1 mr-n5" >	



	<menu:link label="Regions" value="/regions" img="/public/img/menu/localisation_white_nobackground.png" size="30px"/>
	<menu:link label="Utilisateurs" value="/Utilisateurs" img="/public/img/menu/profile_nobackground.png" size="30px"/>
	<menu:link label="Pièces" value="/pieces" img="/public/img/menu/white settings.png" size="30px"/>
	<menu:link label="Marques" value="/Marques" img="" size="30px"/>
	<menu:link label="Vehicules" value="/Vehicules" img="/public/img/menu/car.png" size="30px" />
	<menu:link label="Importer/Exporter" value="/Importer/Vehicules"  img="/public/img/menu/doc icon.png" size="30px"/>

</ul>
</div>

