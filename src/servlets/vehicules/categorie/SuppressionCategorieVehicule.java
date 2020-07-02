package servlets.vehicules.categorie;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.session.general.page.PageGenerator;
import beans.session.vehicules.categorie.CategorieVehiculeFactory;
import beans.session.vehicules.categorie.CategorieVehiculeManager;


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
	   
	    
	
            if(categM.trouverSupprimer( pg.getPathId( request ))) {
                
                pg.redirect( getServletContext(), request, response );
            }else {
                pg.redirectBackErreur( getServletContext(), request, response,
                        "Cette catégorie est liée à certains véhicules" ,
                        "Vous ne pouvez pas la supprimer");
            }
            
      
		
	}



}
