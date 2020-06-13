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
					<label for="code">Code de l'unité</label> 
					<input
						type="text"
						class='form-control' 
						name= "code" 
						value="<c:out value="${unite.codeUN}" />"
						${disabled_id? 'disabled':''}
						/>
					 <c:forEach items='${erreurs["codeUN"]}' var="errl">
								<span class="badge badge-pill badge-danger">${errl}</span>
					</c:forEach>			
				</div>
				<div class="form-group ">
					<label for="nompiece">Adresse</label> 
					<input
						type="text"
						class='form-control'
						name= "adr"
						value="<c:out value="${unite.adress}" />" 
					/>
					<c:forEach items='${erreurs["adress"]}' var="errf">
								<span class="badge badge-pill badge-danger">${errf}</span>
					</c:forEach>				
				</div>
				<label for="marque">Region</label>
						<div class="input-group mb-3">
							<select id="region" class="form-control" required="required"
								name="region">
								<c:forEach items="${region}" var="reg">
									<option  ${idreg==reg.codeReg?"selected":""} > ${reg.codeReg}</option>
								</c:forEach>
							</select>
						</div>
			</div>			
		</div>
		
		<button type="submit" class="btn btn-primary">Valider</button>
		<a type="reset" class="btn btn-danger" href='<c:url value="/regions/${idreg}"/>'>Annuler</a>
	</form>
</div>