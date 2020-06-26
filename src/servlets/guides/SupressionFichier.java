package servlets.guides;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.session.general.page.PageGenerator;
import beans.session.guide.FichierManager;

/**
 * Servlet implementation class SupressionFichier
 */
@WebServlet("/Fichiers/remove/*")
public class SupressionFichier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private FichierManager fm;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupressionFichier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator("/Fichiers" );
		String id = pg.getPathId( request );
		
		if(id!=null && !id.isEmpty()) {
		    fm.trouverSupprimer( id );
		    pg.redirect( getServletContext(), request, response );
		}else {
		    response.getWriter().write( "Impossible de supprimer" );
		}
		
	}



}
