package servlets.maintenance;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.entities.maintenance.Maintenance;
import beans.entities.utilisateurs.Utilisateur;
import beans.session.general.page.PageGenerator;
import beans.session.maintenance.CalendarFactory;
import beans.session.maintenance.MaintenanceManager;

/**
 * Servlet implementation class calendrier
 */
@WebServlet("/calendrier")
public class calendrier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private MaintenanceManager em;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public calendrier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator("/WEB-INF/vues/maintenance/calendrier.jsp" , "Calendrier");	
		CalendarFactory calendrier = new CalendarFactory(request);
		request.setAttribute("cal", calendrier);
		request.setAttribute("months", calendrier.ListOfMonths());
	    request.setAttribute("mois", calendrier.getMonthName(calendrier.getiMonth()));  
	    HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute( "sessionUtilisateur" );
	    List<Maintenance> showList = em.monthlyMaintenance(calendrier.getiMonth() + 1 , calendrier.getiYear(),user);
	    List<Maintenance> threatedList = calendrier.treatList(showList);
	    request.setAttribute("main", threatedList);
	   
		pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
