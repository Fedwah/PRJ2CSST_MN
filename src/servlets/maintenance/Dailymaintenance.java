package servlets.maintenance;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.driver.Driver;
import beans.entities.maintenance.Maintenance;
import beans.session.drivers.DriverFactory;
import beans.session.general.PageGenerator;
import beans.session.maintenance.CalendarFactory;
import beans.session.maintenance.MaintenanceFactory;
import beans.session.maintenance.MaintenanceManager;

/**
 * Servlet implementation class Dailymaintenance
 */
@WebServlet("/maintenance/day/*")
public class Dailymaintenance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAINTENANCES = "/WEB-INF/vues/maintenance/daylyMaintenance.jsp";
	private static final String TITLE = "Liste des maintenaces";
    @EJB
    private MaintenanceManager mainM;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dailymaintenance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(MAINTENANCES,TITLE);
        String date = request.getPathInfo().substring( 1 );// id of element
        if(!date.isEmpty())
        {
            MaintenanceFactory mainF = new MaintenanceFactory(Maintenance.class);
            CalendarFactory cf = new CalendarFactory();
            request.setAttribute( "cal", cf);
    		mainF.addFiltre("startDate", date);
    		request.setAttribute( "main", mainM.lister(mainF.getFiltres()));
            pg.generate( getServletContext(), request, response );
        }
        
       

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
