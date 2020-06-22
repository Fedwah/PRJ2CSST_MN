package servlets.vehicules.affectation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.AffectationConducteur;
import beans.session.general.PageGenerator;
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
	    String id = "";
        String idVeh="";
        PageGenerator pg = new PageGenerator("");
        AffectationConducteur aff=null;
        
        id = pg.getPathId( request );
        aff = affM.trouver( Integer.decode(id ));
        idVeh = aff.getCar().getMatricule_interne();
        affM.trouverSupprimer( Integer.decode(id ));
        System.out.println( "Allez vers "+"/Vehicules/Affectations/"+idVeh );
        pg.setRedirectURL("/Vehicules/"+idVeh );
        pg.redirect( getServletContext(), request, response );
	}

}
