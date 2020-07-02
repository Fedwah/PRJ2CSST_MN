<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid px-5" >
	<div class="row">
		<div class="col-md-3">
			<div class="card text-white bg-primary">
				<div class="card-body">
					<h4 class="">${count_vehicule}</h4>
					<div>Nombre de vehicule</div>
				</div>
				
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="card text-white bg-info">
				<div class="card-body">
					<h4 class="">${count_vehicule_libre}%</h4>
					<div>Vehicule disponible</div>
				</div>
			
			</div>
		</div>
		<div class="col-md-3">
			<div class="card text-white bg-warning">
				<div class="card-body">
					<h4 class="">${count_conducteur }</h4>
					<div>Nombre de conducteur</div>
				</div>
				
			</div>
		</div>
		<div class="col-md-3">
			<div class="card text-white bg-danger">
				<div class="card-body">
					<h4 class="">${count_conducteur_libre }</h4>
					<div>Conducteur non affeceté</div>
				</div>
			</div>
		</div>
	</div>
	<c:forEach items="${evolution_missions}" var="m">
		${ m[0]} , ${ m[1] }
	</c:forEach>
	
	<div  class="m-auto" style="width: 400px; height: 400px;">
		<canvas id="myChart"  aria-label="Hello ARIA World" role="img"></canvas>
	</div>
	
</div>


<script>
	var ctx = document.getElementById('myChart');
	var myChart = new Chart(ctx, {
		type : 'bar',
		data : {
			labels : [ 'Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange' ],
			datasets : [ {
				label : '# of Votes',
				data : [ 12, 19, 3, 5, 2, 3 ],
				backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
						'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
						'rgba(255, 159, 64, 0.2)' ],
				borderColor : [ 'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
						'rgba(255, 159, 64, 1)' ],
				borderWidth : 1
			} ]
		},
		options : {
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	});
	
</script>
