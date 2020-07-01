package servlets.vehicules.affectation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.session.drivers.DriverManager;
import beans.session.general.page.PageGenerator;
import beans.session.vehicules.affectation.AffectationConducteurFactory;

/**
 * Servlet implementation class CreationAffectationConducteur
 */
@WebServlet("/Vehicules/Affectation/*")
public class CreationAffectationConducteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String driverVue = "/WEB-INF/vues/driver/driverLists.jsp";
    @EJB
    private DriverManager dm;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationAffectationConducteur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PageGenerator pg = new PageGenerator(driverVue , "Affectation d'un conducteur" );
        request.setAttribute( "drivers", dm.lister());
        request.setAttribute( "vehicule", pg.getPathId( request ) );
        pg.generate( getServletContext(), request, response );
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PageGenerator pg = new PageGenerator( AffectationConducteurFactory.DEFAULT_REDIRECT ) ;
	    String  id = (String) pg.getPathId( request );
	    if(id!=null) {
	        
	        pg.setRedirectURL( AffectationConducteurFactory.DEFAULT_REDIRECT+id );
	    }
	   
	    
	
	}

}
