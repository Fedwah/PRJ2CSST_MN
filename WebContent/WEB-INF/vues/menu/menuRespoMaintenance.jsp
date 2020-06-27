<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/menu" prefix="m"%>

<div class="nav flex-column  text-center  p-4" >
</div>
<div>
<ul class="nav flex-column  text-center  p-3">


	<m:link label="Utilisateurs" value="/Utilisateurs" img="/public/img/menu/profile_nobackground.png" size="30px"/>
	<m:link label="Calendrier" value="/calendrier" img="/public/img/menu/white calendar.png" size="30px"/>
	<m:link label="Vehicules" value="/Vehicules" img="/public/img/menu/car.png" size="30px" />
	<m:link label="Pièces" value="/pieces" img="/public/img/menu/white settings.png" size="30px"/>
	<m:link label="AMDEC" value="#" img="" size="30px"/>
	<m:link label="Guides" value="/Fichiers" img="" size="30px"/>
	<m:link label="Directives" value="#" img="/public/img/menu/directive.png" size="30px"/>
	
</ul>
</div>