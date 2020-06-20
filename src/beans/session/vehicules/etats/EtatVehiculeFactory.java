package beans.session.vehicules.etats;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.entities.vehicules.EtatVehicule;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

public class EtatVehiculeFactory extends BeanFactory<EtatVehicule> {

    public static final String PARAM_TITRE = "new_etat";
    
    public static final String VUE_LIST_FORM                  = "/WEB-INF/vues/vehicules/etats/vehicules.etats.jsp";
    public static final String DEFAULT_REDIRECT_URL      = "/Vehicules/Etats";

    public EtatVehiculeFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public EtatVehicule create( HttpServletRequest request ) {
        EtatVehicule etat = new EtatVehicule();

        etat.setTitre( request.getParameter( PARAM_TITRE ) );
        return etat;
    }

    public EtatVehicule create( HttpServletRequest request, List<EtatVehicule> listeEtats ) {

        for ( EtatVehicule choixEtat : listeEtats ) {
            if ( request.getParameter( choixEtat.getTitre() ) == "1" ) {
                return choixEtat;
            }
        }
        return create( request );
    }

    @Override
    public void updateChange( EtatVehicule newB, EtatVehicule old ) {
        // TODO Auto-generated method stub
        if(newB.getTitre()!=old.getTitre()) {
            old.setTitre( newB.getTitre() );
            
        }
        
    }
    
    @Override
    public void validateChilds( EtatVehicule bean, BeanManager<EtatVehicule> beanM ) {
        // TODO Auto-generated method stub
        
    }
}
