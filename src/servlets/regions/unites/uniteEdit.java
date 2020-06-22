package servlets.regions.unites;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.regions.Region;
import beans.entities.regions.unites.Unite;
import beans.session.general.page.PageGenerator;
import beans.session.regions.RegionManager;
import beans.session.regions.unites.UniteFactory;
import beans.session.regions.unites.UniteManager;

/**
 * Servlet implementation class uniteEdit
 */
@WebServlet("/regions/unites/edit/*")
public class uniteEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FORM = "/WEB-INF/vues/regions/unites/uniteForm.jsp";
	private static final String REDIRECT = "/regions/";
	private Unite oldU = null;
	@EJB
	private UniteManager em;
	@EJB
	private RegionManager regManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uniteEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator( FORM, "  ");
		String id = "";
		if ( request.getPathInfo() != null ) {
            id = request.getPathInfo().substring( 1 );     
    
        }
		if(id != "")
		{
			oldU = em.trouver(id);
			if(oldU != null)
			{
				request.setAttribute("unite", oldU);
				request.setAttribute("idreg", oldU.getRegion().getCodeReg());
				request.setAttribute("region", regManager.lister());
				request.setAttribute( "disabled_id", true );
			}
		}
		
		pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(FORM, "Unit�", REDIRECT);
		UniteFactory uf = new UniteFactory();
		Unite newU = uf.create(request);
		newU.setCodeUN(oldU.getCodeUN());
		if(uf.validate(newU))
		{
			em.mettreAJour(oldU.getCodeUN(), uf, newU);
			pg.setRedirectURL(REDIRECT+ newU.getRegion().getCodeReg());
			pg.redirect(getServletContext(), request, response);
		}
		else
		{
			request.setAttribute("unite", newU);
			request.setAttribute( "erreurs", uf.getErreurs() );
			request.setAttribute("idreg", newU.getRegion().getCodeReg());
			request.setAttribute("region", regManager.lister());
			request.setAttribute( "disabled_id", true );
			pg.generate( getServletContext(), request, response );
		}
	}

}
