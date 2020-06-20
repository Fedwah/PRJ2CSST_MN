package servlets.maintenance.niveaux;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.maintenance.niveaux.Niveau;
import beans.entities.pieces.Piece;
import beans.entities.regions.unites.Unite;
import beans.session.general.PageGenerator;
import beans.session.maintenance.niveaux.NiveauFactory;
import beans.session.maintenance.niveaux.NiveauManager;

/**
 * Servlet implementation class Niveau
 */
@WebServlet("/maintenance/niveaux")
public class Niveaux extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NIVEAU = "/WEB-INF/vues/maintenance/niveau/niveaux.jsp";
	@EJB
	private NiveauManager em;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Niveaux() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(NIVEAU , "Niveaux de maintenace");	
		request.setAttribute("niveaux", em.lister());
		pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(NIVEAU , "Niveaux de maintenace");
		String niv = request.getParameter("niveau");
		Unite un = new Unite("un1");
		Niveau n = new Niveau(niv,un);
		NiveauFactory nivF = new NiveauFactory();
		if(nivF.validate(n) && nivF.uniqueField(em, "niveau", niv))
		{
			System.out.println("tout est bien");
			em.ajouter(new Niveau(niv,un));
			doGet(request,response);
		}
		else
		{
			request.setAttribute("erreurs", nivF.getErreurs());
			request.setAttribute("niveaux", em.lister());
			pg.generate(getServletContext(), request, response);
		}
		
		
	}

}
