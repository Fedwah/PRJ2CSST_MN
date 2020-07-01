package servlets.vehicules.etat;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.EtatVehicule;
import beans.session.general.page.PageGenerator;
import beans.session.vehicules.etats.EtatVehiculeManager;

/**
 * Servlet implementation class SuppressionEtatVehicule
 */
@WebServlet("/Vehicules/Etats/remove/*")
public class SuppressionEtatVehicule extends HttpServlet {
	private static final String REDIRECT_URL = "/Vehicules/Etats";

    private static final long serialVersionUID = 1L;
    
	@EJB
	EtatVehiculeManager etatM;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuppressionEtatVehicule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	   
	    PageGenerator pg = new PageGenerator( REDIRECT_URL );
	  
	    if ( etatM.trouverSupprimer( pg.getPathId( request ) ) ) {

            pg.redirectBackSuccess( getServletContext(), request, response,
                    "Suppression de " + pg.getPathId( request ),
                    "Réussie" );

        } else {
            pg.redirectBackErreur( getServletContext(), request, response,
                    "Suppression de " + pg.getPathId( request ),
                    "L'etat ne peut pas etre supprimer car il est utlisé." );
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
