package beans.session.Utilisateur;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.entities.utilisateurs.Utilisateur;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

public class UtilisateurFactory  extends BeanFactory<Utilisateur>{

    public UtilisateurFactory() {
        super(Utilisateur.class);
    }
    
    @Override
    public Utilisateur create( HttpServletRequest request ) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void updateChange( Utilisateur newB, Utilisateur old ) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void validateChilds( Utilisateur bean, BeanManager<Utilisateur> beanM ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public List<String> fieldIgnoreExport() {
        List<String> l = super.fieldIgnoreExport();
        l.add( "id" );
        return l;
    }

}
