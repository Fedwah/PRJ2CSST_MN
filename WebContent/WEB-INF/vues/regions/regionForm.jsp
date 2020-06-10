<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<form class="p-4 needs-validation " method="post"
		action="<c:out value=""/>" >

		<!-- ${ erreurs } <!-- pour tester -->

		<div class="form-row">
			<div class="form-group col-md-9">
				<div class="form-group ">
					<label for="code">Code de la région</label> 
					<input
						type="text"
						class='form-control' 
						name= "code" 
						value="<c:out value="${region.codeReg}" />"
						${disabled_id? 'disabled':''}
						/>
					 <c:forEach items='${erreurs["codeReg"]}' var="errl">
								<span class="badge badge-pill badge-danger">${errl}</span>
					</c:forEach>			
				</div>
				<div class="form-group ">
					<label for="nompiece">Adresse</label> 
					<input
						type="text"
						class='form-control'
						name= "adr"
						value="<c:out value="${region.adress}" />" 
					/>
					<c:forEach items='${erreurs["adress"]}' var="errf">
								<span class="badge badge-pill badge-danger">${errf}</span>
					</c:forEach>				
				</div>
			</div>			
		</div>
		
		<button type="submit" class="btn btn-primary">Valider</button>
		<a type="reset" class="btn btn-danger" href='<c:url value="/regions"/>'>Annuler</a>
	</form>
</div>