package servlets.maintenance;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;

import beans.entities.maintenance.Maintenance;
import beans.entities.vehicules.Vehicule;
import beans.session.general.page.PageGenerator;
import beans.session.maintenance.MaintenanceFactory;
import beans.session.maintenance.MaintenanceManager;

import beans.session.pieces.PieceManager;

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

	@EJB
	private PieceManager pManager;
	private Maintenance m = null;
       
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
			
			m = mManager.trouver(Integer.parseInt(id));
			
			if(m != null)
			{
								
				LocalDate date = LocalDate.now();
				request.setAttribute("endDate", date);
				request.setAttribute("maintenance", m);
				request.setAttribute("disabled_matricule", true);
				request.setAttribute("disabled_date", true);
				//request.setAttribute("niveaux", nManager.lister());
				
				pg.generate( getServletContext(), request, response );								
			}
		}
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(FORM, TITLE,REDIRECT);
		MaintenanceFactory mf = new MaintenanceFactory();
		Maintenance newM = mf.create(request,m);
		if(request.getParameter("save")!= null)
		{
			
			System.out.println("date de debut " + newM.getStartDate());
			System.out.println("date de fin " + newM.getEndDate());
			if(mf.validateEdit(newM))
			{
					System.out.println("maintenace valide");
					mManager.mettreAJour(m.getIdMaintenance(), mf, newM);
					pg.redirect(getServletContext(), request, response);				
			}
			else
			{
				System.out.println(" maintenance non valide");
				System.out.println(mf.getErreurs());
				request.setAttribute( "erreurs", mf.getErreurs() );
				request.setAttribute("maintenance", newM);
				request.setAttribute("disabled_matricule", true);
				request.setAttribute("disabled_date", true);
				//request.setAttribute("niveaux",nManager.lister());
				
	
				
				
				pg.generate( getServletContext(), request, response );
				
			}
		}
		else if(request.getParameter("addPiece")!= null)
		{
			// ajout des pieces de rechnages 
			System.out.println(" ajout de piece de rechange");
			request.setAttribute("maintenance", newM);
			request.setAttribute("disabled_matricule", true);
			request.setAttribute("disabled_date", true);
			//request.setAttribute("niveaux",nManager.lister());
			Map<String,Object> fields = new HashMap();
			fields.put("modal.id",newM.getV().getModele().getId());	
			request.setAttribute("piece", pManager.lister(fields));
			pg.generate( getServletContext(), request, response );

		}
	}

}
