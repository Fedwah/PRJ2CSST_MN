package servlets.vehicules;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.general.Image;
import beans.entities.vehicules.Modele;
import beans.entities.vehicules.Vehicule;
import beans.session.ImageManager;
import beans.session.general.PageGenerator;
import beans.session.vehicules.VehiculeFactory;
import beans.session.vehicules.VehiculesManager;
import beans.session.vehicules.etats.EtatVehiculeManager;
import beans.session.vehicules.marques.MarqueManager;
import beans.session.vehicules.modeles.ModeleFactory;
import beans.session.vehicules.modeles.ModeleManager;

/**
 * Servlet implementation class EditVehicule
 */
@WebServlet( "/Vehicules/edit/*" )
@MultipartConfig( maxFileSize = 16177215 ) // upload file's size up to 16MB
public class EditVehicules extends HttpServlet {
    private static final String ATT_DISABLE_ID   = "disabled_id";
    private static final String ATT_ERREURS      = "erreurs";
    private static final String ATT_ETATS        = "etats";
    private static final String ATT_MODELES      = "modeles";
    private static final String ATT_MARQUES      = "marques";
    private static final String ATT_VEHICULE     = "vehicule";

    private static final long   serialVersionUID = 1L;

    @EJB
    private VehiculesManager    vehM;

    @EJB
    private MarqueManager       marM;

    @EJB
    private ModeleManager       modM;

    @EJB
    private EtatVehiculeManager etaM;

    @EJB
    private ImageManager        imgM;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditVehicules() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String id = "";
        PageGenerator pg = new PageGenerator( VehiculeFactory.VUE_FORM, "Vehicule " + id );
        Vehicule v = null;
        Boolean trouver = false;

        if ( request.getPathInfo() != null ) {
            id = request.getPathInfo().substring( 1 );
        }

        if ( id != "" ) {
            v = vehM.trouver( id );
            trouver = ( v != null );
            if ( !trouver ) {
                v = new Vehicule();
                v.setNum_immatriculation( id );
            }
        }

        request.setAttribute( ATT_VEHICULE, v );
        request.setAttribute( ATT_MARQUES, marM.lister( 0, 10 ) );
        request.setAttribute( ATT_MODELES, modM.lister( 0, 10 ) );
        request.setAttribute( ATT_ETATS, etaM.lister( 0, 10 ) );
        request.setAttribute( ATT_DISABLE_ID, ( !trouver ? false : true ) );

        pg.generate( getServletContext(), request, response );

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )

            throws ServletException, IOException {
        String id = request.getPathInfo().substring( 1 );
        PageGenerator pg = new PageGenerator( VehiculeFactory.VUE_FORM, "Vehicule " + id,
                VehiculeFactory.DEFAULT_REDIRECT_URL );

        VehiculeFactory vehiculeF = new VehiculeFactory();

        Vehicule old_v = vehM.trouver( id );
        Vehicule new_v = vehiculeF.create( request );

        Modele m = null;
        Image old_img = ( old_v != null ? old_v.getPhoto() : null );

        ModeleFactory modeleF = new ModeleFactory();
        System.out.println( "IMG EDIT OLD :" + ( old_img != null ? old_img.getTitre() : "pas d'image" ) );

        if ( vehiculeF.validate( new_v ) ) {

            // Creation d'un nouveau modele si necessaire
            modeleF.uniqueSave( modM, new_v.getModele(), new_v.getModele().getTitre(), ModeleFactory.PARAM_TITRE );
            new_v.setModel( modM.trouver( new_v.getModele().getTitre() ) );
            // Creation du vehicule si y'a pas eu d'erreurs avec le modele
            if ( vehiculeF.getFieldError( VehiculeFactory.PARAM_MODELE ).size() <= 1 ) {
                System.out.println( "Modele charger avec success" );
                if ( old_v == null ) {// Creation
                    if ( vehiculeF.uniqueSave( vehM, new_v, new_v.getNum_immatriculation(),
                            VehiculeFactory.PARAM_NUM_IMMATRICULATION ) ) {
                        System.out.println( "Vehicule crée" );
                        pg.redirect( getServletContext(), request, response );
                    }
                } else { // Insertion
                    vehM.mettreAJour( id, vehiculeF, new_v );
                }
            }

        } else {
            System.out.println( "Erreur le vehicule n'a pas été crée " + vehiculeF.getErreurs().toString() );
        }

        request.setAttribute( ATT_VEHICULE, new_v );
        request.setAttribute( ATT_MARQUES, marM.lister() );
        request.setAttribute( ATT_MODELES, modM.lister() );
        request.setAttribute( ATT_ETATS, etaM.lister() );
        request.setAttribute( ATT_ERREURS, vehiculeF.getErreurs() );
        pg.generate( getServletContext(), request, response );

    }

}
