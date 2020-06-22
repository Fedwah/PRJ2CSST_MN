<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page  language="java" import="java.util.*,java.text.*"%>




<title>Calendrier de maintenance</title>
<script>
function goTo()
{
  document.frm.submit()
}
</script>
<form name="frm" method="post">
<div></div>
<div align="right">
	<a class="btn btn-light rounded-circle" style='display: inline-block;height:40px; width:40px;'
	   href='<c:url value="/maintenance/add/"/>'>
	  <img width="50px" height="50px" style='position: relative; top: -10px; left: -17px;' 
	  src="<c:url value='/public/img/icon/add_green_nobackground.png'/>" />
	</a>
</div>
<div>
<table class="table" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
      	
        <td width="6%">Year</td>
        <td width="10%">
		<select class="form-control" name="iYear" onchange="goTo()">
        
		<!-- start year and end year in combo box to change year in calendar --> 
			<c:forEach var = "i" begin = "${cal.iTYear-70}" end = "${cal.iTYear+70}">
        		 <option ${cal.iYear==i?"selected":""} value = "${i}"><c:out value = "${i}"/></option>
      		</c:forEach>
        </select>
        </td>
        <td width="64%" align="center"><h3><c:out value="${mois}" /> <c:out value="${cal.iYear}" /></h3></td>
        <td width="6%">Month</td>
        <td width="14%">
		<select class="form-control" name="iMonth" onchange="goTo()">
    
		<!--  print month in combo box to change month in calendar -->
			<c:forEach items= "${months}" var="m">
        		 <option ${cal.iMonth==m.key?"selected":""} value = "${m.key}"><c:out value = "${m.value}" /></option>
      		</c:forEach>
	   
        </select>
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table  class="table" align="center"  cellpadding="3" cellspacing="0" width="100%">
      <tbody>
        <tr align =" center ">
          <th>Dim</th>
          <th>Lun</th>
          <th>Mar</th>
          <th>Mer</th>
          <th>Jeu</th>
          <th>Ven</th>
          <th>Sam</th>
        </tr>
        <c:set var="cnt" value="${1}" />
      
        <c:forEach var= "i" begin="1" end="${cal.iTotalweeks}">
        
        	<tr>
        	<c:forEach var="j" begin="1" end="7" >
        		<c:choose>
        			
        			<c:when test="${cnt<cal.weekStartDay || (cnt-cal.weekStartDay+1)>cal.days}">
        				<td align="center" height="35">&nbsp;</td>
        			</c:when>
        			<c:otherwise>
        				<td align="center" height="35" id="day_${cnt-cal.weekStartDay+1}">    					
        					
        						<c:set var="exist" value="${false}"></c:set>
        						<c:forEach items="${main}" var="m" >
        						<c:choose>
        							<c:when test="${m.getDay() == cnt-cal.weekStartDay+1}">
        								<c:set var="maintenance" value="${m}"></c:set>
        								<c:set var="exist" value="${true}"></c:set>
        							</c:when>
        						</c:choose>
        						</c:forEach>
        						<c:choose>
        							<c:when test="${cal.iTDay == cnt-cal.weekStartDay+1 && cal.iTMonth == cal.iMonth && cal.iTYear == cal.iYear }">
        								<c:choose>
        									<c:when test="${exist}">
        										<a type="button" class="btn btn-info rounded-circle" 
        										href='<c:url value="/maintenance/day/${cal.dateFromVlues(cnt-cal.weekStartDay+1) }"/>'>
        										<span>${cnt-cal.weekStartDay+1}        										
        										</span>       										
        										</a>
        										<span class="badge badge-danger rounded-circle " style='position: relative; top: -10px; left: -17px;'>!</span>
        									</c:when>
        									<c:otherwise>
        										<button type="button" class="btn btn-info rounded-circle">
        										<span>${cnt-cal.weekStartDay+1}
        										</span>
        										</button>
        									</c:otherwise>
        								</c:choose>
        								
        							</c:when>
        							<c:when test="${exist && maintenance.endDate != null }">
        							<a type="button" class="btn btn-success rounded-circle" 
        							href='<c:url value="/maintenance/day/${cal.dateFromVlues(cnt-cal.weekStartDay+1) }"/>'>
        								<span>${cnt-cal.weekStartDay+1}</span>
        							</a>        								
        							</c:when>
        							<c:when test="${exist && maintenance.endDate == null && maintenance.getDay() <= cal.iTDay }">
        							<a type="button" class="btn btn-danger rounded-circle" 
        							href='<c:url value="/maintenance/day/${cal.dateFromVlues(cnt-cal.weekStartDay+1) }"/>'>
        								<span>${cnt-cal.weekStartDay+1}</span>
        							</a>        								
        							</c:when>
        							<c:when test="${exist && maintenance.endDate == null && maintenance.getDay() > cal.iTDay }">
        							<a type="button" class="btn btn-warning rounded-circle" 
        							href='<c:url value="/maintenance/day/${cal.dateFromVlues(cnt-cal.weekStartDay+1) }"/>'>
        								<span>${cnt-cal.weekStartDay+1}</span>
        							</a>        								
        							</c:when>
        							<c:otherwise>
        								<span>${cnt-cal.weekStartDay+1}</span>        								
        							</c:otherwise>
        						</c:choose>				
                		</td>
        			</c:otherwise>        			
        		</c:choose>
        		<c:set var="cnt" value="${cnt+1}" />
        	
        </c:forEach>
		</c:forEach>
      </tbody>
    </table></td>
  </tr>
</table>
</div>
</form>