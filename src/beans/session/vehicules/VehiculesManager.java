package beans.session.vehicules;




import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanManager;

@Stateless
public class VehiculesManager  extends BeanManager<Vehicule>{

   @PersistenceContext(unitName = "MN_unit")
   EntityManager em;

   public VehiculesManager() {
       super(Vehicule.class);
   }       
    
   @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
        return em;
    }
}
