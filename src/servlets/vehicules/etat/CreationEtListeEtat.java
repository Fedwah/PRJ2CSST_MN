package servlets.vehicules.etat;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.EtatVehicule;
import beans.session.general.PageGenerator;
import beans.session.vehicules.etats.EtatVehiculeFactory;
import beans.session.vehicules.etats.EtatVehiculeManager;

/**
 * Servlet implementation class CreationEtListeEtat
 */
@WebServlet( "/Vehicules/Etats" )
public class CreationEtListeEtat extends HttpServlet {
    private static final String ATT_ETATS        = "etats";
    private static final String ATT_NEW_ETAT     = "new_etat";
    private static final String ATT_ERREURS      = "erreurs";
    private static final long   serialVersionUID = 1L;

    private static final String TITRE_VUE        = "Liste des etats d'un vehicule";

    @EJB
    EtatVehiculeManager         etatM;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationEtListeEtat() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        PageGenerator pg = new PageGenerator( EtatVehiculeFactory.VUE_LIST_FORM, TITRE_VUE );
        request.setAttribute( ATT_ETATS, etatM.lister( ) );
        pg.generate( getServletContext(), request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator( EtatVehiculeFactory.VUE_LIST_FORM, TITRE_VUE );
        EtatVehiculeFactory etatF = new EtatVehiculeFactory();

        EtatVehicule etat = etatF.create( request );
        if (etatF.validate( etat ) ) {
            etatF.uniqueSave( etatM, etat, etat.getTitre(), EtatVehiculeFactory.PARAM_TITRE ); 
                  
        }
        request.setAttribute( ATT_ERREURS, etatF.getErreurs() );
        request.setAttribute( ATT_NEW_ETAT, etat );
        request.setAttribute( ATT_ETATS, etatM.lister() );
        pg.generate( getServletContext(), request, response );
    }

}
