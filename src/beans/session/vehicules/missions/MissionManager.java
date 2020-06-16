package beans.session.vehicules.missions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.vehicules.Mission;
import beans.session.general.BeanManager;

@Stateless
public class MissionManager extends BeanManager<Mission>{

    @PersistenceContext(unitName = "MN_unit")
    EntityManager em;
    
    public MissionManager() {
        super( Mission.class );
    }
    
    @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
        return this.em;
    }
    

}
