<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page  language="java" import="java.util.*,java.text.*"%>
<%!
public int nullIntconv(String inv)
{   
	int conv=0;
		
	try{
		conv=Integer.parseInt(inv);
	}
	catch(Exception e)
	{}
	return conv;
}
%>
<%
 int iYear=nullIntconv(request.getParameter("iYear"));
 int iMonth=nullIntconv(request.getParameter("iMonth"));

 Calendar ca = new GregorianCalendar();
 int iTDay=ca.get(Calendar.DATE);
 int iTYear=ca.get(Calendar.YEAR);
 int iTMonth=ca.get(Calendar.MONTH);

 if(iYear==0)
 {
	  iYear=iTYear;
	  iMonth=iTMonth;
 }

 GregorianCalendar cal = new GregorianCalendar (iYear, iMonth, 1); 

 int days=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
 int weekStartDay=cal.get(Calendar.DAY_OF_WEEK);
 
 cal = new GregorianCalendar (iYear, iMonth, days); 
 int iTotalweeks=cal.get(Calendar.WEEK_OF_MONTH);
  
%>

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
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="6%">Year</td>
        <td width="7%">
		<select name="iYear" onchange="goTo()">
        <%
		// start year and end year in combo box to change year in calendar
	    for(int iy=iTYear-70;iy<=iTYear+70;iy++)
		{
		  if(iy==iYear)
		  {
		    %>
          <option value="<%=iy%>" selected="selected"><%=iy%></option>
          <%
		  }
		  else
		  {
		    %>
          <option value="<%=iy%>"><%=iy%></option>
          <%
		  }
		}
	   %>
        </select></td>
        <td width="73%" align="center"><h3><%=new SimpleDateFormat("MMMM").format(new Date(2008,iMonth,01))%> <%=iYear%></h3></td>
        <td width="6%">Month</td>
        <td width="8%">
		<select name="iMonth" onchange="goTo()">
        <%
		// print month in combo box to change month in calendar
	    for(int im=0;im<=11;im++)
		{
		  if(im==iMonth)
		  {
	     %>
          <option value="<%=im%>" selected="selected"><%=new SimpleDateFormat("MMMM").format(new Date(2008,im,01))%></option>
          <%
		  }
		  else
		  {
		    %>
          <option value="<%=im%>"><%=new SimpleDateFormat("MMMM").format(new Date(2008,im,01))%></option>
          <%
		  }
		}
	   %>
        </select></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table align="center" border="1" cellpadding="3" cellspacing="0" width="100%">
      <tbody>
        <tr>
          <th>Dim</th>
          <th>Lun</th>
          <th>Mar</th>
          <th>Mer</th>
          <th>Jeu</th>
          <th>Ven</th>
          <th>Sam</th>
        </tr>
        <%
        int cnt =1;
        for(int i=1;i<=iTotalweeks;i++)
        {
		%>
        <tr>
          <% 
	        for(int j=1;j<=7;j++)
	        {
		        if(cnt<weekStartDay || (cnt-weekStartDay+1)>days)
		        {
		         %>
                <td align="center" height="35">&nbsp;</td>
               <% 
		        }
		        else
		        {
		         %>
                <td align="center" height="35" id="day_<%=(cnt-weekStartDay+1)%>"><span><%=(cnt-weekStartDay+1)%></span></td>
               <% 
		        }
		        cnt++;
		      }
	        %>
        </tr>
        <% 
	    }
	    %>
      </tbody>
    </table></td>
  </tr>
</table>
</div>
</form>

