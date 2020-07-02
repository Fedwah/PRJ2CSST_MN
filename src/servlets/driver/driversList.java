package servlets.driver;

import java.io.IOException;
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

import beans.entities.driver.Driver;
import beans.entities.pieces.Piece;
import beans.entities.utilisateurs.Utilisateur;
import beans.entities.vehicules.AffectationConducteur;
import beans.entities.vehicules.Vehicule;
import beans.session.drivers.DriverFactory;
import beans.session.drivers.DriverManager;
import beans.session.general.page.PageGenerator;
import beans.session.regions.RegionManager;
import beans.session.vehicules.VehiculeFactory;
import beans.session.vehicules.affectation.AffectationConducteurFactory;
import beans.session.vehicules.affectation.AffectationConducteurManager;

/**
 * Servlet implementation class driversList
 */
@WebServlet({"/drivers","/Vehicules/Affectation/*"})
public class driversList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String driverVue = "/WEB-INF/vues/driver/driverLists.jsp";
	@EJB
	private DriverManager dm;
	@EJB
	private RegionManager regManager;
	@EJB
	private AffectationConducteurManager affM;
	private Utilisateur user = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public driversList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(driverVue , "Liste des conducteurs" );
		HttpSession session = request.getSession();
		user = (Utilisateur) session.getAttribute("sessionUtilisateur");
		if(user.getCodeun()!=null)
		{
			request.setAttribute( "drivers", dm.listerASC(user.getCodeun()));			
		}


		request.setAttribute( "vehicule", pg.getPathId( request ));
		pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filter = request.getParameter("reg");
		PageGenerator pg = new PageGenerator(driverVue , "Liste des conducteurs");
		Map<String,Object> fields = new HashMap();
		String order = request.getParameter("date");
		if(user.getCodeun()!= null)
		{
		if (request.getParameter("search")!= null)
		{
			System.out.println("cas de recherche");
			String search = request.getParameter("word");
			String by = request.getParameter("type");
			List<Driver> drivers = null;
			DriverFactory df = new DriverFactory(Driver.class);
			fields.put(by, search);	
			if(by.equals("recruitDate"))
			{
	
				df.addFiltre(by, search);
				drivers = dm.lister(df.getFiltres());
				request.setAttribute( "drivers", df.filterUN(drivers, user.getCodeun()));				
			}
			else 
			{
				System.out.println("type est "+ by);
				drivers = dm.searchby(fields);
				request.setAttribute( "drivers", df.filterUN(drivers, user.getCodeun()));
			}
			
			request.setAttribute("by", by);
			request.setAttribute("wordf", search);
		}
	
		else if(order!= null)
		{
			System.out.println("order is " + order);
			if(order.equals("ASC"))
			{
				request.setAttribute( "drivers", dm.listerASC(user.getCodeun()));	
			}
			else if(order.equals("DESC"))
			{

				request.setAttribute( "drivers", dm.listerDESC(user.getCodeun()));
				
					
			}
			request.setAttribute("order", order);
			
		}
		}
		else if (request.getParameter( "affecter" )!=null){
		    //AJouter par @Syphax pour faire l'affectation
		    AffectationConducteurFactory affF = new AffectationConducteurFactory();
		    String id = (String) pg.getPathId( request );
		    AffectationConducteur oldAff = null;
		    
		    
		    affF.addFiltre( "car", "matricule_interne", id );
		    oldAff=affM.ObtenirDernier(affF.getFiltres());
		    
		    affF.affecter( request, affM, oldAff );
		    
		    
		    pg.setRedirectURL( AffectationConducteurFactory.DEFAULT_REDIRECT+id );
		    pg.redirect( getServletContext(), request, response );
		    
		}
		
	
		pg.generate( getServletContext(), request, response );
		}
	}


