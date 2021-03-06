package beans.session.vehicules;

import javax.servlet.http.HttpServletRequest;

import beans.entities.regions.unites.Unite;
import beans.entities.vehicules.CategorieVehicule;
import beans.entities.vehicules.EtatsVehicule;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

public class VehiculeFactory extends BeanFactory<Vehicule> {

    private static final String PARAM_MATRICULE_EXTERNE   = "matricule_externe";
    private static final String PARAM_UNITE               = "unite";
    public static final String  PARAM_ETAT                = "etat";
    public static final String  PARAM_PHOTO               = "photo";
    public static final String  PARAM_DATE_ACHAT          = "date_achat";
    public static final String  PARAM_MATRICULE_INTERNE   = "matricule_interne";
    public static final String  PARAM_MODELE              = "modele";
    public static final String  PARAM_MARQUE              = "marque";
    public static final String  PARAM_CATEGORIES_VEHICULE = "categorie";

    public static final String  VUE_FORM                  = "/WEB-INF/vues/vehicules/vehicules.form.jsp";
    public static final String  VUE_LIST                  = "/WEB-INF/vues/vehicules/vehicules.list.jsp";
    public static final String  DEFAULT_REDIRECT_URL      = "/Vehicules";
    public static final String  VUE_DETAIL                = "/WEB-INF/vues/vehicules/vehicules.detail.jsp";

    public VehiculeFactory() {
        super( Vehicule.class );
    }

    public VehiculeFactory( Class<Vehicule> beanClass ) {
        super( beanClass );
       
       
    }

    @Override
    public Vehicule create( HttpServletRequest request ) {

        Modele modele = new Modele( this.castInt( ( request.getParameter( PARAM_MODELE ) ) ) );

        Marque marque = new Marque( request.getParameter( PARAM_MARQUE ) );

        EtatsVehicule etat = EtatsVehicule.get( request.getParameter( PARAM_ETAT ));

        CategorieVehicule categ = new CategorieVehicule( request.getParameter( PARAM_CATEGORIES_VEHICULE ) );

        Vehicule v = new Vehicule();

        v.setMarque( marque );
        v.setModele( modele );
        v.setEtat( etat );
        v.setCategorie( categ );

        v.setMatricule_externe( request.getParameter( PARAM_MATRICULE_EXTERNE ) );
        v.setMatricule_interne( request.getParameter( PARAM_MATRICULE_INTERNE ) );
        v.setDate_achat( this.readDate( request, PARAM_DATE_ACHAT ) );

        v.setPhoto( this.readImage( request, PARAM_PHOTO ) );
        v.setUnite( new Unite( request.getParameter( PARAM_UNITE ) ) );
        v.setKm( new Double(0.0) );
        return v;
    }

    @Override
    public void updateChange( Vehicule newB, Vehicule old ) {

        if ( !newB.getDate_achat().equals(old.getDate_achat())) {
            old.setDate_achat(newB.getDate_achat() );
        }

        old.setEtat( newB.getEtat() );
        old.setMarque( newB.getMarque() );
        old.setModele( newB.getModele() );
        old.setCategorie( newB.getCategorie() );
        old.setMatricule_externe( newB.getMatricule_externe() );
        old.setUnite( newB.getUnite() );
        
       
        old.setPhoto( newB.getPhoto() );
        System.out.println( "photo set to "+old.getPhoto() );
        old.setKm( newB.getKm() );

    }

    @Override
    public void validateChilds( Vehicule bean, BeanManager<Vehicule> beanM ) {
       

        // si on veut aussi valider l'image
        // this.addErreurs( PARAM_PHOTO,new BeanValidator<Image>(
        // bean.getPhoto()).getErreurs().toString());

    }

   
}
