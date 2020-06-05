package servlets.vehicules.marque.modele;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.Modele;
import beans.session.general.PageGenerator;
import beans.session.vehicules.marques.modeles.ModeleFactory;
import beans.session.vehicules.marques.modeles.ModeleManager;

/**
 * Servlet implementation class CreationEtListeModele
 */
@WebServlet("/Modeles")
public class CreationEtListeModele extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ATT_MODELES        = "modeles";
    private static final String ATT_NEW_ETAT     = "new_modele";
    private static final String ATT_ERREURS      = "erreurs";
    
   
    private static final String TITRE_VUE        = "Liste des modeles d'une marque";
       
    
    @EJB
    ModeleManager modM;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationEtListeModele() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    PageGenerator pg = new PageGenerator( ModeleFactory.VUE_LIST_FORM, TITRE_VUE );
        request.setAttribute( ATT_MODELES, modM.lister() );
        pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    PageGenerator pg = new PageGenerator( ModeleFactory.VUE_LIST_FORM, TITRE_VUE );
	    ModeleFactory modF = new ModeleFactory();
	    
	    Modele modele = modF.create( request );
	    
	    System.out.println( "Modele lue : "+ modele.getTitre());
	    if( modF.validate( modele )) {
	        modF.uniqueSave( modM, modele,modele.getTitre(),ModeleFactory.PARAM_TITRE);
	    }
	    
	    request.setAttribute( ATT_ERREURS, modF.getErreurs() );
        request.setAttribute( ATT_NEW_ETAT, modele );
        request.setAttribute( ATT_MODELES, modM.lister() );
	    
        pg.generate( getServletContext(), request, response );
	}

}
