package servlets.vehicules.categorie;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.EtatVehicule;
import beans.session.general.PageGenerator;
import beans.session.vehicules.categorie.CategorieVehiculeFactory;
import beans.session.vehicules.categorie.CategorieVehiculeManager;
import beans.session.vehicules.etats.EtatVehiculeManager;

/**
 * Servlet implementation class SuppressionEtatVehicule
 */
@WebServlet("/Vehicules/Categories/remove/*")
public class SuppressionCategorieVehicule extends HttpServlet {
	

    private static final long serialVersionUID = 1L;
    
	@EJB
	CategorieVehiculeManager categM;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuppressionCategorieVehicule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	   
	    PageGenerator pg = new PageGenerator( CategorieVehiculeFactory.DEFAULT_REDIRECT_URL );
	    String id = pg.getPathId( request );
		
	    if(id!="") {
	        if(categM.trouverSupprimer( id )) {
	            pg.redirect( getServletContext(), request, response );
	        } 
	    }
	 
	    response.getWriter().append( "Erreur: la supression a echoué" );
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
