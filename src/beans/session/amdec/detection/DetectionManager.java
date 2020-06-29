package beans.session.amdec.detection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.amdec.Detection;
import beans.session.general.BeanManager;

@Stateless
public class DetectionManager extends BeanManager<Detection>{
    @PersistenceContext(unitName = "MN_unit")
    EntityManager em;
    
    public DetectionManager() {
        super(Detection.class);
    }
    
    @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
        return em;
    }


}
