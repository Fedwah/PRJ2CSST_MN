<%@page import="beans.entities.vehicules.EtatsVehicule"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid px-5">
	<div class="row">
		<div class="col-md-3">
			<div class="card text-white bg-color">
				<div class="card-body">
					<h4 class="">${count_vehicule}</h4>
					<div>Nombre de vehicule</div>
				</div>

			</div>
		</div>

		<div class="col-md-3">
			<div class="card text-white bg-color">
				<div class="card-body">
					<h4 class="">${count_vehicule_libre}%</h4>
					<div>Vehicule en panne</div>
				</div>

			</div>
		</div>
		<div class="col-md-3">
			<div class="card text-white bg-color">
				<div class="card-body">
					<h4 class="">${count_conducteur }</h4>
					<div>Nombre de conducteur</div>
				</div>

			</div>
		</div>
		<div class="col-md-3">
			<div class="card text-white bg-color">
				<div class="card-body">
					<h4 class="">${count_conducteur_libre }%</h4>
					<div></div>
				</div>
			</div>
		</div>
	</div>
	

	<div class="row">
		<div class="col-md-4 card m-1">
			<canvas class="card-body p-0" id="etats"  aria-label="Hello ARIA World" role="img" height="250px"></canvas>
		</div>
		<div class="col-md card m-1">
			<canvas class="card-body p-0" id="km"  aria-label="Hello ARIA World" role="img" height="250px"></canvas>
		</div>
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
	
	var labels2 = [];
	<c:forEach items="${km_modeles.labels}" var="l">
		labels2.push('${l}')
	</c:forEach>
		
	horizontalBar("km","Distance parcourue par modele",labels2,value,'Distance en Km')
	
</script>
