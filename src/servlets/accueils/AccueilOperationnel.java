package servlets.accueils;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.EtatsVehicule;
import beans.session.drivers.DriverManager;
import beans.session.general.GeneralManager;
import beans.session.general.page.PageGenerator;
import beans.session.vehicules.VehiculesManager;

/**
 * Servlet implementation class AccueilOperationnel
 */
@WebServlet("/Operationnel/Accueil")
public class AccueilOperationnel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	GeneralManager gM;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilOperationnel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator( "/WEB-INF/vues/accueils/accueil.operationnel.jsp"
		        , "Accueil Operationnel" );
		
		request.setAttribute( "count_vehicule", gM.countVehicule() );
		request.setAttribute( "count_vehicule_libre", gM.purcentageVehicule(EtatsVehicule.LIBRE));
		request.setAttribute( "count_conducteur", gM.countDriver());
		request.setAttribute( "count_conducteur_libre", gM.purcentageDriver());
		request.setAttribute( "evolution_missions", gM.evolutionMissions() );
		pg.generate( getServletContext(), request, response );
		
	}

	

}
