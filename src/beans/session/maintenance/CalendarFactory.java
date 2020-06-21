package beans.session.maintenance;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.entities.maintenance.Maintenance;

public class CalendarFactory {
	private int iYear;
	private int iMonth;
	private Calendar ca;
	private int iTDay;
	private int iTYear;
	private int iTMonth;
	private GregorianCalendar cal; 
	private int days;
	private int weekStartDay ;
	private int iTotalweeks;
	
	
	
	public CalendarFactory(HttpServletRequest request) {
		this.iYear = this.nullIntconv(request.getParameter("iYear"));
		this.iMonth = this.nullIntconv(request.getParameter("iMonth"));
		this.ca = new GregorianCalendar();
		this.iTDay=ca.get(Calendar.DATE);
		this.iTYear=ca.get(Calendar.YEAR);
		this.iTMonth=ca.get(Calendar.MONTH);
		if(this.iYear==0)
		 {
			  this.iYear=iTYear;
			  this.iMonth=iTMonth;
		 }

		 this.cal = new GregorianCalendar (iYear, iMonth, 1);
		 this.days=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		 this.weekStartDay=cal.get(Calendar.DAY_OF_WEEK);
		 this.cal = new GregorianCalendar (iYear, iMonth, days); 
		 this.iTotalweeks=cal.get(Calendar.WEEK_OF_MONTH);
		 System.out.println("itday est " + this.iTDay);
		
		}
	
	//getters and setters
	
	
	public int getiYear() {
		return iYear;
	}

	public void setiYear(int iYear) {
		this.iYear = iYear;
	}

	public int getiMonth() {
		return iMonth;
	}

	public void setiMonth(int iMonth) {
		this.iMonth = iMonth;
	}

	public Calendar getCa() {
		return ca;
	}

	public void setCa(Calendar ca) {
		this.ca = ca;
	}

	public int getiTDay() {
		return iTDay;
	}

	public void setiTDay(int iTDay) {
		this.iTDay = iTDay;
	}

	public int getiTYear() {
		return iTYear;
	}

	public void setiTYear(int iTYear) {
		this.iTYear = iTYear;
	}

	public int getiTMonth() {
		return iTMonth;
	}

	public void setiTMonth(int iTMonth) {
		this.iTMonth = iTMonth;
	}

	public GregorianCalendar getCal() {
		return cal;
	}

	public void setCal(GregorianCalendar cal) {
		this.cal = cal;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getWeekStartDay() {
		return weekStartDay;
	}

	public void setWeekStartDay(int weekStartDay) {
		this.weekStartDay = weekStartDay;
	}

	public int getiTotalweeks() {
		return iTotalweeks;
	}

	public void setiTotalweeks(int iTotalweeks) {
		this.iTotalweeks = iTotalweeks;
	}
	
	public Map<Integer,String> ListOfMonths()
	{
		 Map<Integer,String> mois = new HashMap();
		 String[] months = new DateFormatSymbols().getMonths();
	     for (int i=0; i< months.length;i++) 
	     {
	         mois.put(i,months[i]);
	     }
	     
	    return mois;
	}

	public String getMonthName(int index)
	{
		String monthName = new SimpleDateFormat("MMMM").format(new Date(2008,index,01));
		return monthName;
	}

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
	
	public Map<Integer, Maintenance> maintenaceByDate(List<Maintenance> l)
	{
		Map<Integer, Maintenance> map = new HashMap();
		for(Maintenance m : l)
		{
			map.put(m.getDay(),m);
		}
		return map;
	}

}
