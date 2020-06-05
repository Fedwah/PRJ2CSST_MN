<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<ul class="d-flex flex-column p-2">
		<li class="list-group-item"><a
			href='<c:url value="/Marques/add"/>'
			class="btn btn-primary btn-block">Ajouter</a></li>
		<c:forEach items="${marques}" var="marque">
			<li class="list-group-item">

				<div class="card mb-1">
					<div class="row no-gutters">
						<div class="col-md-2 my-auto ">
							<img src='<c:url value="/Images/${marque.image.titre}" />'
								class="img-fluid text-center " alt="..." width="75" height="75">
						</div>
						<div class="">
							<div class="card-body">
								<h5 class="card-title">
									<c:out value="${marque.titre}" />
								</h5>
								<div class="row">
									<a href='<c:url value="/Marques/edit/${marque.titre}"/>'
										class="btn btn-sm btn-outline-primary btn-block">Editer</a> 
									<a href='<c:url value="/Marques/Modeles/${marque.titre}"/>'
										class="btn btn-sm btn-outline-secondary btn-block">Voir ces modeles</a>
								</div>

							</div>
						</div>
					</div>
				</div>
			</li>
		</c:forEach>

	</ul>

</div>
