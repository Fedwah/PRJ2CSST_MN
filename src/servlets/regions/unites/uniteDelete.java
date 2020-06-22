package servlets.regions.unites;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.regions.unites.Unite;
import beans.session.general.page.PageGenerator;
import beans.session.regions.RegionManager;
import beans.session.regions.unites.UniteManager;

/**
 * Servlet implementation class uniteDelete
 */
@WebServlet("/regions/unites/remove/*")
public class uniteDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UNLIST =  "/regions/";
	@EJB
	private UniteManager em;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uniteDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String id = request.getPathInfo().substring( 1 );// id of element
        Unite u = em.trouver(id);
        PageGenerator pg = new PageGenerator(UNLIST+ u.getRegion().getCodeReg());
        if ( em.supprimer(u)) // chercher l'element et le supprimer
        {
            pg.redirect( getServletContext(), request, response );
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
