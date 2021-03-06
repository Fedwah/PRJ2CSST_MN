<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>

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
							<c:choose>
								<c:when test="${marque.image!=null}">
									<img id="preview"
										class="img-fluid rounded shadow-sm mx-auto d-block"
										src='<c:url value="/Images/${marque.image.titre}"/>'
										height="75" width="75" />
								</c:when>
								<c:otherwise>
									<img id="preview"
										class="img-fluid rounded shadow-sm mx-auto d-block"
										src='<c:url value="/public/img/notfound.png" />' height="75"
										width="75" />
								</c:otherwise>
							</c:choose>

						</div>
						<div class="">
							<div class="card-body">
								<h5 class="card-title">
									<c:out value="${marque.titre}" />
								</h5>

								<btn:btn type="secondary"
									value="/Marques/Modeles/${marque.titre}"
									text="Voir
										ses modeles">
								</btn:btn>
								<btn:remove value="/Marques/remove/${marque.titre}"></btn:remove>


							</div>
						</div>
					</div>
				</div>
			</li>
		</c:forEach>

	</ul>

</div>
