<%@tag description="Le template de bouton" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn"%>
<%@attribute name="value" required="true"%>
<%@attribute name="label" required="true"%>

<btn:btn type="link" value="${value}"  outline="${false }" text="${label}"></btn:btn>

