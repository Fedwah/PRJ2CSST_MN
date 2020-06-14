package beans.session.vehicules;

import javax.servlet.http.HttpServletRequest;

import beans.entities.vehicules.CategorieVehicule;
import beans.entities.vehicules.EtatVehicule;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanFactory;
import beans.session.vehicules.categorie.CategorieVehiculeFactory;
import beans.session.vehicules.etats.EtatVehiculeFactory;
import beans.session.vehicules.marques.MarqueFactory;
import beans.session.vehicules.marques.modeles.ModeleFactory;

public class VehiculeFactory extends BeanFactory<Vehicule>{
    
 
    
    public static final String PARAM_ETAT                = "etat";
    public static final String PARAM_PHOTO               = "photo";
    public static final String PARAM_DATE_ACHAT          = "date_achat";
    public static final String PARAM_NUM_IMMATRICULATION = "num_immatriculation";
    public static final String PARAM_MODELE              = "modele";
    public static final String PARAM_MARQUE              = "marque";
    public static final String PARAM_CATEGORIES_VEHICULE = "categorie";
    
    
    
    public static final String VUE_FORM                  = "/WEB-INF/vues/vehicules/vehicules.form.jsp";
    public static final String VUE_LIST                  = "/WEB-INF/vues/vehicules/vehicules.list.jsp";
    public static final String DEFAULT_REDIRECT_URL      = "/Vehicules";

   

    public VehiculeFactory( Class<Vehicule> beanClass ) {
        super( beanClass );
        // TODO Auto-generated constructor stub
    }

    
    @Override
    public Vehicule create( HttpServletRequest request ) {

        Modele modele = new Modele( Integer.decode(request.getParameter( PARAM_MODELE ) ) );

        Marque marque = new Marque( request.getParameter( PARAM_MARQUE ) );
       
        EtatVehicule etat = new EtatVehicule( request.getParameter( PARAM_ETAT ) );

        CategorieVehicule categ =  new CategorieVehicule(request.getParameter(PARAM_CATEGORIES_VEHICULE));
        
        Vehicule v = new Vehicule();

       
        v.setMarque( marque );
        v.setModel( modele );
        v.setEtat( etat );
        v.setCategorie( categ );

        v.setNum_immatriculation( request.getParameter( PARAM_NUM_IMMATRICULATION ) );
        v.setDate_achat( this.readDate( request, PARAM_DATE_ACHAT ) );
        v.setPhoto( this.readImage( request, PARAM_PHOTO ) );

        return v;
    }
    
    @Override
    public void updateChange( Vehicule newB, Vehicule old ) {

        if ( newB.getDate_achat() != old.getDate_achat() ) {
         
            old.setDate_achat(this.readDate(newB.getDate_achat()));
        }

        old.setEtat( newB.getEtat() );
        old.setMarque( newB.getMarque() );
        old.setModel( newB.getModele() );
        old.setCategorie( newB.getCategorie() );
        
        if ( newB.getPhoto() != null ) {
            old.setPhoto( newB.getPhoto() );
        }

    }

    @Override
    public void validateChilds( Vehicule bean ) {
        ModeleFactory modF = new ModeleFactory();
        MarqueFactory marF = new MarqueFactory();
        EtatVehiculeFactory etatF = new EtatVehiculeFactory();
        CategorieVehiculeFactory categF = new CategorieVehiculeFactory();
        
        marF.validate( bean.getMarque() );
        modF.validate( bean.getModele() );
        etatF.validate( bean.getEtat() );
        categF.validate( bean.getCategorie() );
        
        // si on veut aussi valider l'image
        //this.addErreurs( PARAM_PHOTO,new BeanValidator<Image>( bean.getPhoto()).getErreurs().toString());
       
        
        this.addErreurs( PARAM_MARQUE, marF.getErreurs().toString());
        this.addErreurs( PARAM_MODELE, modF.getErreurs().toString());
        this.addErreurs( PARAM_ETAT, etatF.getErreurs().toString());
        this.addErreurs( PARAM_CATEGORIES_VEHICULE,categF.getErreurs().toString());
        
    }
}
