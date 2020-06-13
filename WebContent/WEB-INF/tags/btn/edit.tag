<%@tag description="Le boutton d'edition" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn" %>
<%@attribute name="value" required="true"%>


<btn:btn img="/public/img/icon/edit_green.png" type="primary" value="${value}"/>