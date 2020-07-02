package servlets.vehicules.categorie;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.CategorieVehicule;
import beans.session.general.page.PageGenerator;
import beans.session.vehicules.categorie.CategorieVehiculeFactory;
import beans.session.vehicules.categorie.CategorieVehiculeManager;


/**
 * Servlet implementation class CreationEtListeEtat
 */
@WebServlet( "/Vehicules/Categories" )
public class CreationEtListeCategorie extends HttpServlet {
    private static final String ATT_CATEGORIES       = "categories";
    private static final String ATT_NEW_CATEGORIE     = "new_categorie";
    private static final String ATT_ERREURS      = "erreurs";
    private static final long   serialVersionUID = 1L;

    private static final String TITRE_VUE        = "Liste des categories de vehicule";

    @EJB
    CategorieVehiculeManager   categM;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationEtListeCategorie() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
       
        PageGenerator pg = new PageGenerator( CategorieVehiculeFactory.VUE_LIST_FORM, TITRE_VUE );
        request.setAttribute( ATT_CATEGORIES, categM.lister( ) );
        pg.generate( getServletContext(), request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator( CategorieVehiculeFactory.VUE_LIST_FORM, TITRE_VUE );
        CategorieVehiculeFactory categF = new CategorieVehiculeFactory();

        CategorieVehicule categ = categF.create( request );
        
        if (categF.validate( categ ) ) {
            categF.uniqueSave(categM, categ,categ.getTitre(),"titre"); 
                  
        }
        request.setAttribute( ATT_ERREURS, categF.getErreurs() );
        request.setAttribute( ATT_NEW_CATEGORIE, categ );
        request.setAttribute( ATT_CATEGORIES, categM.lister() );
        pg.generate( getServletContext(), request, response );
    }

}
