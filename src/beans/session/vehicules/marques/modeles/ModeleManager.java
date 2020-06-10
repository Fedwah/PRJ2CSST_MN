package beans.session.vehicules.marques.modeles;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import beans.entities.vehicules.Modele;
import beans.session.general.BeanManager;

@Stateless
public class ModeleManager extends BeanManager<Modele> {

    @PersistenceContext(unitName = "MN_unit")
    EntityManager em;
    public ModeleManager() {
        super(Modele.class);
    }
    @Override
    public EntityManager getEntityManger() {
        return this.em;
    }
    public Modele find(Map<String,Object> fields)
    {
    	String qr = "";
        Iterator iterator = fields.entrySet().iterator();
		while(iterator.hasNext())
		{
			Map.Entry mapentry = (Map.Entry) iterator.next();
			qr = qr + " b." + mapentry.getKey() + "= :" + mapentry.getKey();
			System.out.println(mapentry.getValue());
			if(iterator.hasNext())
			{
				qr = qr + " and " ;
			}
		}
		System.out.println(qr);
		Query query  = this.em.createQuery("SELECT b FROM Modele b where" + qr);
		for (Map.Entry mapentry : fields.entrySet()) {
	          query.setParameter((String) mapentry.getKey(), mapentry.getValue());
	           
	        }
    	return (Modele) query.getSingleResult() ;
    }
}
