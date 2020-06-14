<%@tag description="Le boutton d'affectation" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn" %>
<%@attribute name="value" required="true"%>
<%@attribute name="text" required="false"%>

<btn:btn type="warning" value="${value}" text="${text}" img="/public/img/icon/driver_white_greenBackground.png"></btn:btn>



