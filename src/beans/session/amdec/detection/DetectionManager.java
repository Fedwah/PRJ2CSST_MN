package beans.session.amdec.detection;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import beans.entities.amdec.Detection;
import beans.session.general.BeanManager;

@Stateless
public class DetectionManager extends BeanManager<Detection> {
    @PersistenceContext( unitName = "MN_unit" )
    EntityManager em;

    public DetectionManager() {
        super( Detection.class );
    }

    @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
        return em;
    }

    public List<Detection> listerUn( String codeun ) {
         Query q = this.getEntityManger().
         createQuery( "select b from "+Detection.class.getName()+
                 " b where b.vehicule.unite.codeUN = :un");
         q.setParameter( "un", codeun );
         return q.getResultList();
    }

    public List<Detection> listerReg( String codeReg ) {
        Query q =  this.getEntityManger().
                createQuery( "select b from "+Detection.class.getName()+
                        " b where b.vehicule.unite.region = :reg" );
        
        q.setParameter( "reg", codeReg );
        return q.getResultList();
    }
    
    
}
