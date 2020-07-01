package servlets.vehicules.affectation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.AffectationConducteur;
import beans.session.general.page.PageGenerator;
import beans.session.vehicules.affectation.AffectationConducteurManager;

/**
 * Servlet implementation class SupressionAffectationVehicule
 */
@WebServlet("/Vehicules/Affectations/remove/*")
public class SupressionAffectationVehicule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private AffectationConducteurManager affM;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupressionAffectationVehicule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Integer id = null;
        String idVeh="";
        PageGenerator pg = new PageGenerator("");
        AffectationConducteur aff=null;
        
        id = (Integer) pg.getPathId( request );
        aff = affM.trouver( id);
        idVeh = aff.getCar().getMatricule_interne();
        
        if(affM.trouverSupprimer( id)) {
            pg.setRedirectURL("/Vehicules/"+idVeh );
            pg.redirectBackSuccess( getServletContext(), request, response,
                    "Suppresion de l'affectation du vehicule "+idVeh
                    ,"Réussie");
        }else {
            pg.redirectBackErreur( getServletContext(), request, response,
                    "L'affection a des missions en cour",
                    "Suppression immposible");
        }
        
       
       
	}

}
