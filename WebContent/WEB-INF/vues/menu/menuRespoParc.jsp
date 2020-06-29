<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/menu" prefix="m"%>

<div class="overflow-hidden mt-1 border-right border-light">
<ul class="nav flex-column pl-1">
	
	<m:link label="Calendrier" value="/calendrier" img="/public/img/menu/white calendar.png" 
	size="35px" top="0px" left="16px" topI="0px" leftI="7px"
	/>
	<m:link label="Vehicules" value="/Vehicules" img="/public/img/menu/car.png" 
	size="35px" top="0px" left="18px" topI="0px" leftI="8px"
	 />
	<m:link label="Conducteur" value="/drivers" img="/public/img/menu/driver_white.png" 
	size="24px" top="0px" left="28px" topI="-2px" leftI="13px"
	/>
		
</ul>
</div>