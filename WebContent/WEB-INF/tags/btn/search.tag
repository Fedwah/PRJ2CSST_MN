<%@tag description="Le boutton de recherche" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="name"  required="true"%>


<button type="submit" class="btn btn-light rounded-circle" name="${name}"
		style='display: inline-block;height:40px; width:40px;position: relative; top: -3px; left: 0px;'>
		
		<img height="40px" width="40px" style='position: relative; top: -6px; left: -11px;' src='<c:url value="/public/img/icon/search_green_nobackground.png"/>' />
</button>

