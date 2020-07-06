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
					<div>Besoin en piece de rechange</div>
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
	</div>
	
	
	<div class=" card mt-1">
		<div class="row">
			<div class="col-md-4">
				<canvas class="card-body p-0" id="etats"
					aria-label="Hello ARIA World" role="img" height="250px"></canvas>
			</div>
			<div class="col-md-8">
				<canvas class="card-body p-0" id="etats_unites"
					aria-label="Hello ARIA World" role="img"></canvas>
			</div>
		</div>


	</div>
	<div class="row px-2">
		<div class="col-md-6 card m-1">
			<canvas class="card-body p-0" id="nb_pannes"
				aria-label="Hello ARIA World" role="img" height="250px"></canvas>
		</div>
		<div class="col-md card m-1">
			<canvas class="card-body p-0" id="nb_pannes_unites"
				aria-label="Hello ARIA World" role="img" height=""></canvas>
		</div>
		
	</div>
	<div class="row px-2">
		<div class="col-md-6 card m-1">
			<canvas class="card-body p-0" id="moy_modeles"
				aria-label="Hello ARIA World" role="img" height="250px"></canvas>
		</div>
		<div class="col-md card m-1">
			<canvas class="card-body p-0" id="moy_unites"
				aria-label="Hello ARIA World" role="img" height="250px"></canvas>
		</div>
	</div>

	
</div>


<script>	

	var value = ${moy_modeles.values};
	var labels = [];
	
	
	<c:forEach items="${moy_modeles.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
		
	horizontalBar("moy_modeles","Moyenne d'age des vehicules par modele",labels,value,'Moyenne d\'age en ans')
	
	value = ${moy_unites.values}
	labels=[]
	<c:forEach items="${moy_unites.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
		
	horizontalBar("moy_unites","Moyenne d'age des vehicules par unité",labels,value,'Moyenne d\'age en ans')
	
	value = ${etats_vehicule.values}
	labels=[]
	<c:forEach items="${etats_vehicule.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
		
	pie("etats","Etats des vehicule dans la region",labels,value)
	
	var colors = getColors(${etas_unites.dataset.size()})
	var datasets = [
	<c:forEach items="${etas_unites.dataset}" var="e" varStatus="s">
			{
			label:"${e.key}",
			data : ${e.value} ,
			backgroundColor: colors[${s.index}],
			backgroundColor: colors[${s.index}],
			},
	</c:forEach>
	]
	
	labels=[]
	<c:forEach items="${etas_unites.labels.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
	
	var data = {
	    datasets: datasets,
	    labels: labels,
	    
	}
	
	var options =  {
	    	title: {
	             display: true,
	             text: "Etats des vehicules par unité"
	         },
	        responsive: true,
	        legend: {
	          display: true,
	        },
	        hover: {
	          onHover: function(e) {
	            var point = this.getElementAtEvent(e);
	            e.target.style.cursor = point.length ? 'pointer' : 'default';
	          },
	        },
	        tooltips: {
	            enabled: true
	        },
	        plugins: {
	            datalabels: {
	                formatter: (value, ctx) => {
	                   
	                   
	                  
	                    return value;
	                },
	                color: '#000',
	            }
	        },
	        scales: {
	            xAxes: [{
	                ticks: {
	                    beginAtZero: true,
	                    steps: 10,
	                    stepValue: 6,
	                    
	                    }
	                }
	            ]}
	        
	        
	    }
	
	chart("horizontalBar","etats_unites",data,options)
	
	value = ${nb_pannes_unites.values}
	labels=[]
	<c:forEach items="${nb_pannes_unites.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
	
	bar("nb_pannes_unites","Nombre de panne dans la region par unité",labels,value,"Nombre de panne")
	
	value = ${nb_pannes.values}
	labels=[]
	<c:forEach items="${nb_pannes.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
	
	bar("nb_pannes","Nombre de panne dans la region par modele",labels,value,"Nombre de panne")
</script>
