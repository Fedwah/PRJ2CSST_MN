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
import beans.session.Utilisateur.MethodeUtilisateur;
import beans.session.general.PageGenerator;
import beans.session.regions.RegionFactory;
import beans.session.regions.RegionManager;

/**
 * Servlet implementation class regionEdit
 */
@WebServlet("/regions/edit/*")
public class regionEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FORM = "/WEB-INF/vues/regions/regionForm.jsp";
	private static final String REDIRECT =  "/regions";
	private Region oldReg = null;
	private boolean edit = false;
	@EJB
	private RegionManager em;
	@EJB
	private MethodeUtilisateur userManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public regionEdit() {
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
			
			oldReg = (Region) em.trouver(id);
			
			if(oldReg != null) // cas d'edition
			{
				request.setAttribute( "region", oldReg );
				request.setAttribute( "disabled_id", true );
				edit = true ;
				System.out.println("cas d'edition");
			}
		}
		
		pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(FORM, "Piece", REDIRECT);
		String code = request.getParameter("code");
		RegionFactory rf = new RegionFactory();
		Region reg = rf.create(request,userManager);
		if (edit)
		{
			request.setAttribute( "disabled_id", true );
			reg.setCodeReg(oldReg.getCodeReg());
			
		}
		if(rf.validate(reg))
		{
			if(edit)
			{
				em.mettreAJour(oldReg.getCodeReg(), rf, reg);
				pg.redirect(getServletContext(), request, response);
			}
			else 
			{
				if(em.ajouterUnique(reg,code))
				{
					pg.redirect(getServletContext(), request, response);
				}
				else
				{
					// code dupliqué
				}
				
			}
			
		}
		else
		{
			
			request.setAttribute("region", reg);
			request.setAttribute( "erreurs", rf.getErreurs() );
	        pg.generate( getServletContext(), request, response );
		}
	}

}
