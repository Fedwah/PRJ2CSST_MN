package beans.session.vehicules.marques.modeles;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

public class ModeleFactory extends BeanFactory<Modele> {
    
    public static final String PARAM_TITRE = "new_modele";
    public static final String VUE_LIST_FORM                  = "/WEB-INF/vues/vehicules/marques/modeles/marques.modeles.jsp";
    public static final String DEFAULT_REDIRECT_URL      = "/Marques";
    public ModeleFactory() {
        super( Modele.class );
    }

    @Override
    public Modele create( HttpServletRequest request ) {
        Modele m = new Modele();
        m.setTitre( request.getParameter( PARAM_TITRE ) );
        return(m);
    }

    @Override
    public void updateChange( Modele newB, Modele old ) {
        
        if(newB.getTitre()!=old.getTitre()) {
            old.setTitre( newB.getTitre() );
        }
        
    }
   
    @Override
    public void validateChilds( Modele bean, BeanManager<Modele> beanM ) {
        // TODO Auto-generated method stub
        
    }
    
    public Modele filtrer(HttpServletRequest request, ModeleManager em)
    {
    	String mark = request.getParameter("marque");	
		
		String modal = request.getParameter("modele");
		this.addFiltre( "titre", modal );
		this.addFiltre("marque","titre",mark);
		
        Modele modele = em.trouver(this.getFiltres());
        return modele;
    }
   
}
