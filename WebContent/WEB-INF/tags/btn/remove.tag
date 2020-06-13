<%@tag description="Le boutton de suppression " pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="btn" tagdir="/WEB-INF/tags/btn" %>
<%@attribute name="value" required="true"%>


<btn:btn img="/public/img/icon/delete_green.png" type="danger" value="${value }"></btn:btn>