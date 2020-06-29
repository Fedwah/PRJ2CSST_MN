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
import beans.session.regions.unites.UniteFactory;
import beans.session.regions.unites.UniteManager;

/**
 * Servlet implementation class uniteAdd
 */
@WebServlet("/regions/unite/add/*")
public class uniteAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FORM = "/WEB-INF/vues/regions/unites/uniteForm.jsp";
	private static final String REDIRECT = "/regions/";
	private String idReg = "";
	@EJB
	private RegionManager em;
	@EJB
	private UniteManager unManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uniteAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator( FORM, "  ");
		if ( request.getPathInfo() != null ) {
            idReg = request.getPathInfo().substring( 1 );     
        }
		request.setAttribute("region", em.lister());
		request.setAttribute("idreg", idReg);
		pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UniteFactory uf = new UniteFactory(Unite.class);
		Unite u = uf.create(request);
		u.setRegion(em.ObtenirRefrence(u.getRegion().getCodeReg()));
		PageGenerator pg = new PageGenerator(FORM, "Unité", REDIRECT+ u.getRegion().getCodeReg());
		if(uf.validate(u,unManager,u.getCodeUN()))
		{
			if(unManager.ajouterUnique(u, u.getCodeUN()))
			{
				System.out.println("unite saved");
				pg.redirect(getServletContext(), request, response);
			}
		}
		else
		{
			request.setAttribute("unite", u);
			request.setAttribute( "erreurs", uf.getErreurs() );
			request.setAttribute("region", em.lister());
			System.out.println("champs incorrecte " + u.getRegion().getCodeReg());
			pg.generate( getServletContext(), request, response );
		}
		
	}

}
