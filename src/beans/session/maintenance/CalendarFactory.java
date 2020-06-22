package beans.session.maintenance;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
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
	
	public CalendarFactory() 
	{
		this.ca = new GregorianCalendar();
		this.iTDay=ca.get(Calendar.DATE);
		this.iTYear=ca.get(Calendar.YEAR);
		this.iTMonth=ca.get(Calendar.MONTH);
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
	
	public Map<Integer, List<Maintenance>> maintenaceByDate(List<Maintenance> l)
	{
		Map<Integer, List<Maintenance>> map = new HashMap();
		for(Maintenance m : l)
		{
			int key = m.getDay();
			if(map.get(key) == null)
			{
				map.put(key, new ArrayList());
			}
			map.get(key).add(m);
		}
		return map;
	}
	
	public String getEtat(Maintenance m)
	{
		if(m.getEndDate() == null)
		{
			if(m.getDay() > this.iTDay)
			{
				return "à venir";
			}
			
			else
			{
				return "en cours";
			}
		}
		else
		{
			return "termine";
		}
	}
	
	public ArrayList<Integer> getIndexById(ArrayList<Integer> idMain,List<Maintenance> monthList)
	{
		ArrayList<Integer> indexes = new ArrayList();
		for(int i = 0; i< monthList.size() ; i++)
		{
			for(int j : idMain)
			{
				if(monthList.get(i).getIdMaintenance() == j)
				{
					indexes.add(i);
				}
			}
		}
		return indexes;
	}
	
	
	public List<Maintenance> treatList(List<Maintenance> monthList)
	{
		Map<Integer,List<Maintenance>> organizedList = this.maintenaceByDate(monthList);
		Iterator iterator = organizedList.entrySet().iterator();
			while(iterator.hasNext())
			{
				Map.Entry mapentry = (Map.Entry) iterator.next();
				List<Maintenance> listOfDay = (List<Maintenance>) mapentry.getValue();
				if((listOfDay.size() > 1) && ((Integer) mapentry.getKey() < this.iTDay))
				{
					boolean hasPriority = false;
					ArrayList<Integer> idMain = new ArrayList();
					for(Maintenance m : listOfDay)
					{
						if(this.getEtat(m).equals("en cours"))
						{
							hasPriority =true;
							
						}
						else if (this.getEtat(m).equals("termine"))
						{
							idMain.add(m.getIdMaintenance());
						}
					}
					
					if(hasPriority)
					{
						ArrayList indexes = this.getIndexById(idMain, monthList);
						
						for(int i = indexes.size() - 1; i >= 0 ; i--)
						{
							monthList.remove(indexes.get(i));
						
						}
						
					}
				}
			}
			
			return monthList;
		
	}
	
	public String dateFromVlues(int day)
	{ 
		String date = Integer.toString(this.iYear)  + "-" + Integer.toString(this.iMonth +1) + "-" + Integer.toString(day);
		return date;
	}

}
