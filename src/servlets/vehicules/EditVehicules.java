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
import beans.entities.utilisateurs.Utilisateur;
import beans.entities.vehicules.CategorieVehicule;
import beans.entities.vehicules.Modele;
import beans.entities.vehicules.Vehicule;
import beans.session.ImageManager;
import beans.session.general.page.PageGenerator;
import beans.session.regions.unites.UniteManager;
import beans.session.vehicules.VehiculeFactory;
import beans.session.vehicules.VehiculesManager;
import beans.session.vehicules.categorie.CategorieVehiculeFactory;
import beans.session.vehicules.categorie.CategorieVehiculeManager;
import beans.session.vehicules.etats.EtatVehiculeManager;
import beans.session.vehicules.marques.MarqueManager;
import beans.session.vehicules.marques.modeles.ModeleFactory;
import beans.session.vehicules.marques.modeles.ModeleManager;

/**
 * Servlet implementation class EditVehicule
 */
@WebServlet( "/Vehicules/edit/*" )
@MultipartConfig( maxFileSize = 16177215 ) // upload file's size up to 16MB
public class EditVehicules extends HttpServlet {
    private static final String ATT_UNITE = "unite";
    private static final String ATT_NAMES = "names";
    private static final String ATT_LABELS = "labels";
    private static final String ATT_CATEGORIES_VEHICULE = "categories";
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
    private CategorieVehiculeManager categM;

    @EJB
    private ImageManager        imgM;
    
    @EJB
    private UniteManager uM;  

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
        VehiculeFactory vehiculeF = new VehiculeFactory(Vehicule.class);
        
        id = pg.getPathId( request );

        Utilisateur u = pg.getUtilisateur( request );
        
        if ( id != "" ) {
            v = vehM.trouver( id );
            trouver = ( v != null );
            if ( !trouver ) {
                v = new Vehicule();
                v.setMatricule_interne( id );
            }
        }
        
       
        request.setAttribute( ATT_LABELS,vehiculeF.getEntityFields().labels());
        request.setAttribute( ATT_NAMES,vehiculeF.getEntityFields().names());
        request.setAttribute( ATT_VEHICULE, v );
        request.setAttribute( ATT_MARQUES, marM.lister() );
        request.setAttribute( ATT_MODELES, modM.lister() );
        request.setAttribute( ATT_UNITE, (u!=null?u.getCodeun():"Pas d'unité") );
        request.setAttribute( ATT_CATEGORIES_VEHICULE, categM.lister() );
        request.setAttribute( ATT_ETATS, etaM.lister() );
        request.setAttribute( ATT_ERREURS, vehiculeF.getErreurs() );
        request.setAttribute( ATT_DISABLE_ID, v!=null );
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

        VehiculeFactory vehiculeF = new VehiculeFactory(Vehicule.class);
        
        Utilisateur u = pg.getUtilisateur( request );
       
        
        Vehicule old_v = vehM.trouver( id );
        Vehicule new_v = vehiculeF.create( request );

      
        
        Image old_img = ( old_v != null ? old_v.getPhoto() : null );

        
        System.out.println( "IMG EDIT OLD :" + ( old_img != null ? old_img.getTitre() : "pas d'image" ) );
        
        if ( vehiculeF.validate( new_v ) ) {
                if ( old_v == null ) {
                   
                    // Creation
                    if ( vehiculeF.uniqueSave(vehM, new_v, new_v.getMatricule_interne(),
                            VehiculeFactory.PARAM_MATRICULE_INTERNE ) ) {
                        System.out.println( "Vehicule crée" );
                        pg.redirect( getServletContext(), request, response );
                    }
                } else { 
                    // Insertion
                    System.out.println( "old_v unit : "+old_v.getUnite().getCodeUN() );
                    System.out.println( "new_v unit : "+new_v.getUnite().getCodeUN() );
                    vehM.mettreAJour( id, vehiculeF, new_v );
                }
            
        }else {
       
            System.out.println( "Erreur le vehicule n'a pas été crée " + vehiculeF.getErreurs().toString() );
        }
        
      
        request.setAttribute( ATT_LABELS,vehiculeF.getEntityFields().labels());
        request.setAttribute( ATT_NAMES,vehiculeF.getEntityFields().names());
        request.setAttribute( ATT_VEHICULE, new_v );
        request.setAttribute( ATT_MARQUES, marM.lister() );
        request.setAttribute( ATT_MODELES, modM.lister() );
        request.setAttribute( ATT_UNITE, (u!=null?u.getCodeun():"Pas d'unité") );
        request.setAttribute( ATT_CATEGORIES_VEHICULE, categM.lister() );
        request.setAttribute( ATT_ETATS, etaM.lister() );
        request.setAttribute( ATT_ERREURS, vehiculeF.getErreurs() );
        request.setAttribute( ATT_DISABLE_ID,new_v!=null );
        pg.generate( getServletContext(), request, response );

    }

}
