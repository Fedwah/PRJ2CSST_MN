package beans.session.vehicules.modeles;

import javax.servlet.http.HttpServletRequest;

import beans.entities.vehicules.Modele;
import beans.session.general.BeanFactory;

public class ModeleFactory extends BeanFactory<Modele> {
    public static final String PARAM_TITRE = "modele";

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
        //Pas de propietie fils complex (manyToOneou autre) 
    }
}
