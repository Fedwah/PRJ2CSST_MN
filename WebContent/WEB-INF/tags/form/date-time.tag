<%@tag description="Le template d'un input dans un form"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@attribute name="col" required="false"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="false"%>
<%@attribute name="value" required="true" type="java.util.Date"%>
<%@attribute name="placeHolder" required="false"%>

<%@attribute name="erreurs_" required="false" type="java.util.List"%>
<%@attribute name="isDisabled" required="false" type="java.lang.Boolean"%>


 
<fmt:formatDate value="${value}" pattern="yyyy-MM-dd hh:mm:ss" var="date"/>

<form:input name="${name}" type="datetime-local"
				 value='${fn:replace(date, " ", "T")}'
				 label="${label}" col="${col}"
				 erreurs_="${erreurs_}" placeHolder="${placeHolder}" isDisabled="${isDisabled}">
</form:input>

