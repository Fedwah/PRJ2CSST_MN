package beans.session.vehicules.marques;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.entities.general.Image;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.session.general.BeanFactory;
import beans.session.general.BeanValidator;

public class MarqueFactory extends BeanFactory<Marque> {

    public static final String PARAM_IMAGE = "image";
    public static final String PARAM_TITRE = "titre";

    public static final String VUE_FORM = "/WEB-INF/vues/vehicules/marques/marques.form.jsp";
    public static final String DEFAULT_REDIRECT_URL = "/Marques";
    public MarqueFactory() {
        // TODO Auto-generated constructor stub
    }

    public Marque create( HttpServletRequest request ) {
        Marque marque = new Marque();
        marque.setTitre( request.getParameter( PARAM_TITRE ) );
        marque.setImage( this.readImage( request, PARAM_IMAGE ) );
        return marque;
    }

    @Override
    public void updateChange( Marque newB, Marque old ) {
        // TODO Auto-generated method stub

        if ( old.getTitre() != newB.getTitre() ) {
            old.setTitre( newB.getTitre() );
        }
        
        if ( !old.getImage().equals( newB.getImage() ) ) {
            old.setImage( newB.getImage() );
        }
        
        if(!old.getModeles().equals( newB.getModeles() )) {
            old.setModeles( (ArrayList<Modele>)newB.getModeles() );
        }

    }
    
    @Override
    public void validateChilds( Marque bean ) {
        // TODO Auto-generated method stub
        if(bean.getImage()!=null) {
            this.addErreurs(PARAM_IMAGE,new BeanValidator<Image>(bean.getImage()).getErreurs().toString());
        }
        
    }
}
