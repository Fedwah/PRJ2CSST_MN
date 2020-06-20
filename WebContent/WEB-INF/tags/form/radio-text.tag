<%@tag description="Le template d'un radio dans un form" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@attribute name="col" required="true"%>
<%@attribute name="name" required="true"%>
<%@attribute name="label" required="true"%>
<%@attribute name="value" required="true"%>
<%@attribute name="id" required="false"%>
<%@attribute name="inline" required="false" type="java.lang.Boolean"%>
<%@attribute name="selected" required="false" type="java.lang.Boolean"%>
<div class="custom-control custom-radio ${col} ${inline!=null && inline?'custom-control-inline':''}" >
  <input type="radio" id="${id}" 
  			name="${name}" class="custom-control-input " ${selected!=null && selected?'checked="checked"':""} value="${value}" >
  <label class="custom-control-label" for="${id}">${label }</label>
</div>