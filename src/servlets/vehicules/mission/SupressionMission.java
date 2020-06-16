package servlets.vehicules.mission;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.session.general.PageGenerator;
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
	    PageGenerator pg = new PageGenerator( "/Vehicules" );
        String id = pg.getPathId( request );
        MissionFactory mF = new MissionFactory();
        if(mM.trouverSupprimer( mF.castId(id ))) {
            pg.redirect( getServletContext(), request, response );
        }else {
            response.getWriter().write( "Impossible de supprimer" );
        }
	}

	

}
