package servlets.guides;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.guide.Fichier;
import beans.session.general.page.PageGenerator;
import beans.session.guide.FichierFactory;
import beans.session.guide.FichierManager;

/**
 * Servlet implementation class DetailFichier
 */
@WebServlet("/Fichiers/*")
public class DetailFichier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
    private FichierManager fichM;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailFichier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator( FichierFactory.VUE_DETAIL, "" );
		String id = (String) pg.getPathId( request );
		Fichier f = null;
		FichierFactory fichF = new FichierFactory();
		if(id!=null) {
		    pg.setPageTitle( id );
		    f = fichM.trouver( id );
		    if(f!=null) {
		        request.setAttribute( "supported", !fichF.isFileUnSupportedByBrowser(f.getType()) );
		    }
		}
		
		request.setAttribute( "fichier", f );
		
		pg.generate( getServletContext(), request, response );
	}

	

}
