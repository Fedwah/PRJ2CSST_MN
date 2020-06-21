<%@tag description="Le template de bouton" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="id" required="true"%>
<%@attribute name="label" required="true"%>
<%@attribute name="active" required="false" type="java.lang.Boolean"%>

<li class="nav-item" role="presentation">
	<a class="nav-link ${active!=null&&active?'active':''}" id="${id}-tab" data-toggle="tab" href="#${id}"
				role="tab" aria-controls="${id}" aria-selected="${active!=null?active:false}">${label}</a>
</li>