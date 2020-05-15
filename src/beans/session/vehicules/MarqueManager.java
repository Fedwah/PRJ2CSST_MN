package beans.session.vehicules;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.vehicules.Marque;
import beans.session.general.BeanManager;

@Stateless
public class MarqueManager extends BeanManager<Marque> {
    
   @PersistenceContext(unitName = "MN_unit")
   EntityManager em;

   public MarqueManager() {
       super(Marque.class);
       System.out.println("em inti = "+(em==null));
   }       
   @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
        return em;
    }
}
