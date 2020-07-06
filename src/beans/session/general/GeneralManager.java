package beans.session.general;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import beans.entities.vehicules.EtatsVehicule;
import beans.session.general.page.PageGenerator;

@Stateless
public class GeneralManager extends BeanManager<Object> {
    /*
     * Requetes du l'accueil operationel
     * 
     */
  
    /*
     * 
     * 
     */
    private static final String SQL_ETAT_PAR_MARQUE = "select v.marque_titre,v.etat,count(*) "
            + "from public.vehicule v\r\n" + 
            "group by  v.marque_titre,v.etat";
    
   
    @PersistenceContext( unitName = "MN_unit" )
    private EntityManager em;

    public GeneralManager() {
        super( Object.class );
    }

    @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
        return em;
    }

    public List<Object> lister( Class<?> name ) {
        System.out.println( "Get all"+name.getName() );
        return em.createQuery( "select b from " + name.getName() + " b" ).getResultList();
    }


        
    /**Regional **/
    public List<?> etatVehiculeParMarque(){
        Query q = this.getEntityManger().createNativeQuery(SQL_ETAT_PAR_MARQUE);
        try {
            return q.getResultList();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Object>();
        }
        
    }
    
    
    
  
}
