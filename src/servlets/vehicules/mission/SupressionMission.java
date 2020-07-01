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
@WebServlet( "/Vehicules/Missions/remove/*" )
public class SupressionMission extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private MissionManager    mM;

    @EJB
    private VehiculesManager  vM;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupressionMission() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator( "" );
        Integer id = (Integer) pg.getPathId( request );

        VehiculeFactory vehF = new VehiculeFactory();
        Mission m = mM.trouver( id );

        if ( m != null ) {

            pg.setRedirectURL( "/Vehicules/" + m.getAffectation().getCar().getMatricule_interne() );
            if ( mM.supprimer( m ) ) {
                vehF.mettreAjourKM( m.getVehicule(), m.getDistance_parcourue(), 0.0, vM );
                pg.redirectBackSuccess( getServletContext(), request, response,
                        "Suppression de " + pg.getPathId( request ), "Reussie" );
            }

        }

        pg.redirectBackErreur( getServletContext(), request, response,
                "Ce Vehicule  est utlisé",
                "Vous ne pouvez pas la supprimer" );

    }

}
