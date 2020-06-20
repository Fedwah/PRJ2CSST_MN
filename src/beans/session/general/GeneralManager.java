package beans.session.general;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GeneralManager extends BeanManager<Object>{

    @PersistenceContext(unitName = "MN_unit")
    private EntityManager em;
    
   
    public GeneralManager() {
        super( Object.class );
    }
    
   
    @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
        return em;
    }
    
    
    
    
   public List<Object> lister(Class<?> name) {
        // TODO Auto-generated method stub
        return em.createQuery( "select b from "+name.getName() +" b").getResultList();
   }
   
   
}
