<%@tag description="Le template d'un input dans un form"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@attribute name="col" required="false"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="false"%>
<%@attribute name="value" required="true"%>
<%@attribute name="placeHolder" required="false"%>

<%@attribute name="erreurs_" required="false" type="java.util.List"%>
<%@attribute name="isDisabled" required="false" type="java.lang.Boolean"%>




<form:input name="${name}" type="date"
				 value='${value}'
				 label="${label}" col="${col}"
				 erreurs_="${erreurs_}" placeHolder="${placeHolder}" isDisabled="${isDisabled}">
</form:input>