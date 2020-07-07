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
					<h4 class="">${count_piece}</h4>
					<div>Besoin en PR</div>
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
				<canvas class="card-body p-0" id="etas_regions"
					aria-label="Hello ARIA World" role="img"></canvas>
			</div>
		</div>


	</div>
	<div class="card mt-1">
		<div class="row px-2">
		<canvas class="card-body " id="besoin_piece"
			aria-label="Hello ARIA World" role="img" height="250px"></canvas>
	</div>
	</div>
	


	<div class="row px-2">
		<div class="col-md-6 card m-1">
			<canvas class="card-body p-0" id="nb_pannes"
				aria-label="Hello ARIA World" role="img" height="250px"></canvas>
		</div>
		<div class="col-md card m-1">
			<canvas class="card-body p-0" id="nb_pannes_regions"
				aria-label="Hello ARIA World" role="img" height=""></canvas>
		</div>

	</div>
	<div class="row px-2">
		<div class="col-md-6 card m-1">
			<canvas class="card-body p-0" id="moy_modeles"
				aria-label="Hello ARIA World" role="img" height="250px"></canvas>
		</div>
		<div class="col-md card m-1">
			<canvas class="card-body p-0" id="moy_regions"
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
	
	value = ${moy_regions.values}
	labels=[]
	<c:forEach items="${moy_regions.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
		
	horizontalBar("moy_regions","Moyenne d'age des vehicules par region",labels,value,'Moyenne d\'age en ans')
	
	value = ${etats_vehicule.values}
	labels=[]
	<c:forEach items="${etats_vehicule.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
		
	pie("etats","Etats des vehicule dans la region",labels,value)
	
	var colors = getColors(${etas_regions.dataset.size()})
	var datasets = [
	<c:forEach items="${etas_regions.dataset}" var="e" varStatus="s">
			{
			label:"${e.key}",
			data : ${e.value} ,
			backgroundColor: colors[${s.index}],
			backgroundColor: colors[${s.index}],
			},
	</c:forEach>
	]
	
	labels=[]
	<c:forEach items="${etas_regions.labels.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
	
	var data = {
	    datasets: datasets,
	    labels: labels,
	    
	}
	
	var options =  {
	    	title: {
	             display: true,
	             text: "Etats des vehicules par regions"
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
	
	chart("horizontalBar","etas_regions",data,options)
	
	value = ${nb_pannes_regions.values}
	labels=[]
	<c:forEach items="${nb_pannes_regions.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
	
	bar("nb_pannes_regions","Nombre de panne dans la region par regions",labels,value,"Nombre de panne")
	
	value = ${nb_pannes.values}
	labels=[]
	<c:forEach items="${nb_pannes.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
	
	bar("nb_pannes","Nombre de panne par modele",labels,value,"Nombre de panne")
	
	value = ${besoin_piece.values}
	
	labels = [];
	<c:forEach items="${besoin_piece.labels}" var="l">
		labels.push('${l}')
	</c:forEach>
		
	horizontalBar("besoin_piece","Les besoins en piece des maintenances en attente",labels,value,'Nombre de pieces')
</script>
