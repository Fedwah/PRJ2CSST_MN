package beans.session.vehicules.affectation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.vehicules.AffectationConducteur;
import beans.session.general.BeanManager;

@Stateless
public class AffectationConducteurManager extends BeanManager<AffectationConducteur> {

    @PersistenceContext(unitName = "MN_unit")
    EntityManager em;
    
    public AffectationConducteurManager() {
        super(AffectationConducteur.class);
    }
    
    @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
        return em;
    }
    
    

}
