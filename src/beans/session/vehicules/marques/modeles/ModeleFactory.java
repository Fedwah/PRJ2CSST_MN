package beans.session.vehicules.marques.modeles;

import javax.servlet.http.HttpServletRequest;

import beans.entities.vehicules.Modele;
import beans.session.general.BeanFactory;

public class ModeleFactory extends BeanFactory<Modele> {
    
    public static final String PARAM_TITRE = "new_modele";
    public static final String VUE_LIST_FORM                  = "/WEB-INF/vues/vehicules/marques/modeles/marques.modeles.jsp";
    public static final String DEFAULT_REDIRECT_URL      = "/Modeles";

    public ModeleFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Modele create( HttpServletRequest request ) {
        Modele m = new Modele();
        m.setTitre( request.getParameter( PARAM_TITRE ) );
        return m;
    }

    @Override
    public void updateChange( Modele newB, Modele old ) {
        
        if(newB.getTitre()!=old.getTitre()) {
            old.setTitre( newB.getTitre() );
        }
        
    }
    @Override
    public void validateChilds( Modele bean ) {
        // TODO Auto-generated method stub
        //Pas de propietie fils complex (manyToOne ou autre) 
    }
}
