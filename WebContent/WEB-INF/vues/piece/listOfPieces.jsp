<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<form class=" " method="post" name="frm" action="<c:out value=""/>">
		<h3 class="display"
			style='color: #3E703D; font-size: 40px; font-style: gras;'>Liste
			des pieces</h3>
		<nav class="nav justify-content-end mb-2" width="500px">
			<div class="col-md-6"
				style='position: relative; top: 0px; left: -15px;'>
				<input type="text" class='form-control col-md-6' name="word"
					value="${wordf}" placeholder="chercher"
					style='display: inline-block;' /> <select
					class="form-control col-md-3" name="type"
					style='display: inline-block;'>
					<option value="refrence" ${by=='reference'?"selected":""}>Reference</option>
					<option value="pieceName" ${by=='pieceName'?"selected":""}>Nom</option>
				</select>
				<button type="submit" class="btn btn-light rounded-circle"
					name="search"
					style='display: inline-block; height: 40px; width: 40px; position: relative; top: -3px; left: 0px;'>
					<img height="40px" width="40px"
						style='position: relative; top: -6px; left: -11px;'
						src='<c:url value="/public/img/icon/search_green_nobackground.png"/>' />
				</button>
			</div>
			<div class="col-md-6" align="right">
				<select class="form-control col-md-4" style='display: inline-block;'
			name="modele" onchange="goTo()">
					<option ${modal==-1?'selected':""} value="all">Tous les modeles</option>
					<c:forEach items='${marques}' var='marq'>
					<optgroup label='${marq.titre}'>
					<c:forEach items='${marq.modeles}' var='m'>
					<option ${modal==m.id?'selected':""}
					value='${m.id}'>${m.titre}</option>
					</c:forEach>
					</optgroup>
					</c:forEach>
										
				</select>
				<a class="btn btn-light rounded-circle"
					style='display: inline-block; height: 40px; width: 40px;position:relative; top:-3px; left:0px'
					href='<c:url value="/pieces/edit/"/>'> <img width="50px"
					height="50px" style='position: relative; top: -10px; left: -17px;'
					src="<c:url value='/public/img/icon/add_green_nobackground.png'/>" />
				</a>
			</div>
		</nav>
		<c:forEach items="${pieces}" var="p">
			<div class="card mt-3">
				<div class="card-body ">

					<div class="row">
						<div class="col-md-4">
							<h3 class="card-title text-color h3-adjust">${p.refrence}</h3>
						</div>
						<div class="col-md-4">
							<h3 class="card-title text-color h3-adjust">${p.pieceName}</h3>
						</div>
						<div class="col-md-4 " align="right">
							<a class="btn btn-outline-primary"
								href='<c:url value="/pieces/edit/${p.refrence}"/>'> <img
								width="20px" height="20px"
								src="<c:url value='/public/img/icon/edit_green.png'/>" />
							</a> <a class="btn btn-outline-danger" width="30px"
								href='<c:url value="/pieces/remove/${p.refrence}"/>'> <img
								width="20px" height="20px"
								src="<c:url value='/public/img/icon/delete_green.png'/>" />
							</a>
						</div>
					</div>
					<div>
						<h3 class="card-title text-color h3-adjust">Modèles associés</h3>
						<c:forEach items="${p.modals}" var="modal">

							<span class="paragraph"><c:out
									value="${modal.marque.titre }"></c:out></span>
							-
							<span class="paragraph"><c:out value="${modal.titre }"></c:out></span>
							<br />
						</c:forEach>
					</div>


				</div>
			</div>

		</c:forEach>
	</form>
</div>
<script>
	function goTo() {
		document.frm.submit()
	}
</script>