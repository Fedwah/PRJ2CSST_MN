<%@page import="beans.entities.vehicules.EtatsVehicule"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid px-5">
	<div class="row">
		<div class="col-md-3">
			<div class="card text-white bg-color">
				<div class="card-body">
					<h4 class="">non fait</h4>
					<div>Besoin en PR</div>
				</div>

			</div>
		</div>

		<div class="col-md-3">
			<div class="card text-white bg-color">
				<div class="card-body">
					<h4 class="">${count_vehicule_libre}% (${Math.round(count_vehicule_libre/100 * count_vehicule)} sur ${count_vehicule})</h4>
					<div>Vehicule disponible</div>
				</div>

			</div>
		</div>
		<div class="col-md-3">
			<div class="card text-white bg-color">
				<div class="card-body">
					<h4 class="">${count_maintenance}</h4>
					<div>Maintenance en attente</div>
				</div>

			</div>
		</div>
		<div class="col-md-3">
			<div class="card text-white bg-color">
				<div class="card-body">
					<h4 class="">${count_conducteur_libre }%</h4>
					<div>Conducteur non affeceté</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="row">
		<div class="col-md-4 card m-1">
			<canvas class="card-body p-0" id="etats"  aria-label="Hello ARIA World" role="img" height="300px"></canvas>
		</div>
		<div class="col-md card m-1">
			<canvas class="card-body p-0" id="km"  aria-label="Hello ARIA World" role="img" height="300px"></canvas>
		</div>
		
	</div>
	<div class="row card">
		<canvas class="card-body p-0" id="pannes"  aria-label="Hello ARIA World" role="img" height="250px"></canvas>
	</div>
	
</div>



<script>	

	var value = ${etats_vehicule.values}
	var labels = [];
	
	<c:forEach items="${etats_vehicule.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
	
	pie("etats","Etat des vehicules",labels,value)
	
	value = ${km_modeles.values}
	
	labels = [];
	<c:forEach items="${km_modeles.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
		
	horizontalBar("km","Distance parcourue par modele",labels,value,'Distance en Km')
	
	value = ${nb_pannes.values}
	labels = [];
	<c:forEach items="${nb_pannes.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
		
	bar("pannes","Panne par modele",labels,value,'Nombre de panne total')
	
</script>
