package servlets.guides;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.session.general.page.PageGenerator;
import beans.session.guide.FichierFactory;
import beans.session.guide.FichierManager;

/**
 * Servlet implementation class ListeFichiers
 */
@WebServlet("/Fichiers")
public class ListeFichiers extends HttpServlet {
	private static final String ATT_FICHIERS = "fichiers";
    private static final long serialVersionUID = 1L;
	private static final String ATT_FILTRES = "filtres";
    private static final String ATT_FIELD          = "field";
    private static final String ATT_SEARCH         = "search";
    private static final String ATT_FIELDS         = "fields";
	@EJB
	private FichierManager fichM;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeFichiers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PageGenerator pg = new PageGenerator( FichierFactory.VUE_LIST, FichierFactory.TITRE_VUE_LIST);
	    FichierFactory fichF = new FichierFactory();
	    
	    fichF.notFilter( "data" );
	    
	    request.setAttribute( ATT_FIELDS, fichF.getEntityFields().labels() );
        request.setAttribute( ATT_FILTRES, fichF.getNamesToFilter() );
	    request.setAttribute( ATT_FICHIERS, fichM.lister() );
	    pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PageGenerator pg = new PageGenerator( FichierFactory.VUE_LIST, FichierFactory.TITRE_VUE_LIST);
        FichierFactory fichF = new FichierFactory();
        
        String search = request.getParameter( ATT_SEARCH );
        String field = request.getParameter( ATT_FIELD );
        
        fichF.notFilter( "data" );
        fichF.addFiltreByID( field, search );
        
       
        request.setAttribute( ATT_FIELDS, fichF.getEntityFields().labels() );
        request.setAttribute( ATT_FILTRES, fichF.getNamesToFilter() );
        request.setAttribute( ATT_SEARCH, search );
        request.setAttribute( ATT_FIELD, field );
        request.setAttribute( ATT_FICHIERS, fichM.searchby(fichF.getFiltres()) );
        pg.generate( getServletContext(), request, response );
	}

}
