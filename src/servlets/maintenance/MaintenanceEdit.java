package servlets.maintenance;

import java.io.IOException;
import java.time.LocalDate;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.maintenance.Maintenance;
import beans.entities.maintenance.niveaux.Niveau;
import beans.session.general.page.PageGenerator;
import beans.session.maintenance.CalendarFactory;
import beans.session.maintenance.MaintenanceFactory;
import beans.session.maintenance.MaintenanceManager;

/**
 * Servlet implementation class MaintenanceEdit
 */
@WebServlet("/maintenance/edit/*")
public class MaintenanceEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FORM = "/WEB-INF/vues/maintenance/maintenanceEdit.jsp";
	private static final String REDIRECT = "/calendrier";
	private static final String TITLE = "Maintenance";
	@EJB
	private MaintenanceManager mManager;
	private Maintenance oldM = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaintenanceEdit() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator( FORM, TITLE);
		String id = "";
		if ( request.getPathInfo() != null ) {
          id = request.getPathInfo().substring( 1 );
        }
		if(! id.equals(""))		
		{
			
			oldM = mManager.trouver(Integer.parseInt(id));
			
			if(oldM != null)
			{
								
				LocalDate date = LocalDate.now();
				request.setAttribute("endDate", date);
				CalendarFactory cf = new CalendarFactory();
				request.setAttribute("cal", cf);
				
				request.setAttribute("main", oldM);
				request.setAttribute("disabled_matricule", true);
				request.setAttribute("niveaux", Niveau.values());
				
				pg.generate( getServletContext(), request, response );								
			}
		}
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MaintenanceFactory mf = new MaintenanceFactory();
		CalendarFactory cf =new CalendarFactory() ;
        String etat = cf.getEtat(oldM);
		Maintenance newM = mf.create(request,oldM,etat);
		if(! etat.equals("à venir"))
		{
			if(mf.validateEdit(newM,mManager)) validateDoPost(request, response, mf, newM);
			else unvalidDoPost(request, response, mf, newM);

		}
		else 
		{
			if(mf.validate(newM,mManager)) validateDoPost(request, response, mf, newM);
			else unvalidDoPost(request, response, mf, newM);

		}
		
	}
	
	private void validateDoPost(HttpServletRequest request, HttpServletResponse response, MaintenanceFactory mainF, Maintenance newMain) throws IOException
	{
		PageGenerator pg = new PageGenerator(FORM, TITLE,REDIRECT);
		mManager.mettreAJour(oldM.getIdMaintenance(), mainF, newMain);
		pg.redirectBack(null, request, response);
	}
	private void unvalidDoPost(HttpServletRequest request, HttpServletResponse response,MaintenanceFactory mainF,Maintenance newMain) throws ServletException, IOException
	{
		PageGenerator pg = new PageGenerator( FORM, TITLE);
		request.setAttribute( "erreurs", mainF.getErreurs() );
		request.setAttribute("maintenance", newMain);
		CalendarFactory cf = new CalendarFactory();
		request.setAttribute("cal", cf);	
		request.setAttribute("main", newMain);
		request.setAttribute("disabled_matricule", true);
		request.setAttribute("niveaux", Niveau.values());		
		pg.generate( getServletContext(), request, response );
	}

}
