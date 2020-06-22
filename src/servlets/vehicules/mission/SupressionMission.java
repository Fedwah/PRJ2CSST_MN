package servlets.vehicules.mission;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.Mission;
import beans.entities.vehicules.Vehicule;
import beans.session.general.page.PageGenerator;
import beans.session.vehicules.VehiculeFactory;
import beans.session.vehicules.VehiculesManager;
import beans.session.vehicules.missions.MissionFactory;
import beans.session.vehicules.missions.MissionManager;

/**
 * Servlet implementation class SupressionMission
 */
@WebServlet("/Vehicules/Missions/remove/*")
public class SupressionMission extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private MissionManager mM;
	
	@EJB
	private VehiculesManager vM;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupressionMission() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PageGenerator pg = new PageGenerator("");
        String id = pg.getPathId( request );
        MissionFactory mF = new MissionFactory();
        VehiculeFactory vehF = new VehiculeFactory();
        Mission m = mM.trouver(mF.castId(id ));
       
        if(m!=null) {
            
            pg.setRedirectURL( "/Vehicules/"+m.getAffectation().getCar().getMatricule_interne());
            if(mM.supprimer( m )) {
                vehF.mettreAjourKM( m.getVehicule(), m.getDistance_parcourue(),0.0, vM);
            }
          
            pg.redirect( getServletContext(), request, response );
        }else {
            response.getWriter().write( "Impossible de supprimer" );
        }
	}

	

}
