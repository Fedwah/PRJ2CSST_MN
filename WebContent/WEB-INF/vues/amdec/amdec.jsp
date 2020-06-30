<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

<%@taglib prefix="m" tagdir="/WEB-INF/tags/menu"%>


<ul class="nav nav-tabs" id="myTab" role="tablist">

	<m:tab-link label="Detections de pannes" id="detection"
		active="${active == 'detection' || empty active}" />
	<m:tab-link label="Instructions" id="instructions"
		active="${active == 'instruction'}" />
	<m:tab-link label="Defaillances" id="defaillance"
		active="${active == 'defaillance'}" />
	<m:tab-link label="Causes" id="cause" active="${active == 'cause'}" />
	<m:tab-link label="Effets" id="effet" active="${active == 'effet'}" />

</ul>

<div class="tab-content" id="myTabContent">

	<c:import url="vues/amdec/amdec.detections.jsp" />
	<c:import url="vues/amdec/amdec.instructions.jsp" />
	<c:import url="vues/amdec/amdec.defaillance.jsp" />
	<c:import url="vues/amdec/amdec.causes.jsp" />
	<c:import url="vues/amdec/amdec.effets.jsp" />

</div>