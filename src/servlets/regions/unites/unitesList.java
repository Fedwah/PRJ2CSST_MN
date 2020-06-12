package servlets.regions.unites;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.regions.Region;
import beans.entities.regions.unites.Unite;
import beans.session.general.PageGenerator;
import beans.session.regions.RegionManager;
import beans.session.regions.unites.UniteManager;

/**
 * Servlet implementation class unitesList
 */
@WebServlet("/regions/*")
public class unitesList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UNITES = "/WEB-INF/vues/regions/unites/unitesList.jsp" ; 
	private static final String title = "Liste des unites";
	@EJB
	private UniteManager em;
	@EJB
	private RegionManager regManager ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public unitesList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator( UNITES, title);
		String id = "";
		if ( request.getPathInfo() != null ) {
            id = request.getPathInfo().substring( 1 );          
        }
		if(id != "")
		{
			Map<String,Object> fields = new HashMap();
			fields.put("region", regManager.trouver(id));
			request.setAttribute("code", id);
			request.setAttribute( "unite", em.lister(fields));
			request.setAttribute( "fields", Unite.class.getDeclaredFields());
			pg.generate( getServletContext(), request, response );
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
