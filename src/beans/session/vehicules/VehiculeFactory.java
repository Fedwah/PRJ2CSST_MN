package beans.session.vehicules;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

import beans.entities.general.Image;
import beans.entities.vehicules.EtatVehicule;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanFactory;
import beans.session.general.BeanValidator;
import beans.session.vehicules.etats.EtatVehiculeFactory;
import beans.session.vehicules.marques.MarqueFactory;
import beans.session.vehicules.modeles.ModeleFactory;

public class VehiculeFactory extends BeanFactory<Vehicule>{
    public static final String PARAM_ETAT                = "etat";
    public static final String PARAM_PHOTO               = "photo";
    public static final String PARAM_DATE_ACHAT          = "dateAchat";
    public static final String PARAM_NUM_IMMATRICULATION = "numImmatriculation";
    public static final String PARAM_MODELE              = "modele";
    public static final String PARAM_MARQUE              = "marque";

    public static final String VUE_FORM                  = "/WEB-INF/vues/vehicules/vehicules.form.jsp";
    public static final String VUE_LIST                  = "/WEB-INF/vues/vehicules/vehicules.list.jsp";
    public static final String DEFAULT_REDIRECT_URL      = "/Vehicules";

    public VehiculeFactory() {

    }

    @Override
    public Vehicule create( HttpServletRequest request ) {

        Modele modele = new Modele( request.getParameter( PARAM_MODELE ) );

        Marque marque = new Marque( request.getParameter( PARAM_MARQUE ) );
        EtatVehicule etat = new EtatVehicule( request.getParameter( PARAM_ETAT ) );

        Vehicule v = new Vehicule();

        v.setMarque( marque );
        v.setModel( modele );
        v.setEtat( etat );

        v.setNum_immatriculation( request.getParameter( PARAM_NUM_IMMATRICULATION ) );
        try {
            v.setDate_achat( new SimpleDateFormat( "yyyy-MM-dd" ).parse( request.getParameter( PARAM_DATE_ACHAT ) ) );
        } catch ( ParseException e ) {
            // TODO Auto-generated catch block
           System.err.println( "Format date invalide" );
        }
        v.setPhoto( this.readImage( request, PARAM_PHOTO ) );

        return v;
    }

    @Override
    public void updateChange( Vehicule newB, Vehicule old ) {

        if ( newB.getDate_achat() != old.getDate_achat() ) {
            try {
                old.setDate_achat( new SimpleDateFormat( "yyyy-MM-dd" ).parse( newB.getDate_achat() ) );
            } catch ( ParseException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        old.setEtat( newB.getEtat() );
        old.setMarque( newB.getMarque() );
        old.setModel( newB.getModele() );

        if ( newB.getPhoto().getTitre() != null ) {
            old.setPhoto( newB.getPhoto() );
        }

    }

    @Override
    public void validateChilds( Vehicule bean ) {
        ModeleFactory modF = new ModeleFactory();
        MarqueFactory marF = new MarqueFactory();
        EtatVehiculeFactory etatF = new EtatVehiculeFactory();
       
        marF.validate( bean.getMarque() );
        modF.validate( bean.getModele() );
        etatF.validate( bean.getEtat() );

        
        // si on veut aussi valider l'image
        //this.addErreurs( PARAM_PHOTO,new BeanValidator<Image>( bean.getPhoto()).getErreurs().toString());
       
        
        this.addErreurs( PARAM_MARQUE, marF.getErreurs().toString());
        this.addErreurs( PARAM_MODELE, modF.getErreurs().toString());
        this.addErreurs( PARAM_ETAT, etatF.getErreurs().toString());
    }
}
