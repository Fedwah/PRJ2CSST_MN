package servlets.regions;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.pieces.Piece;
import beans.entities.regions.Region;
import beans.session.general.PageGenerator;
import beans.session.regions.RegionManager;

/**
 * Servlet implementation class regionsLists
 */
@WebServlet("/regions")
public class regionsLists extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String vueReg = "/WEB-INF/vues/regions/regionLists.jsp" ; 
	private static final String title = "Liste des régions";
	@EJB
	private RegionManager em;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public regionsLists() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(vueReg, title);
		request.setAttribute( "region", em.lister());
		request.setAttribute( "fields", Region.class.getDeclaredFields());
		pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
