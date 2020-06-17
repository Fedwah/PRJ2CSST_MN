package beans.session.maintenance.niveaux;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.maintenance.niveaux.Niveau;
import beans.session.general.BeanManager;

@Stateless
public class NiveauManager extends BeanManager<Niveau> {

	@PersistenceContext(unitName = "MN_unit")
	EntityManager em;
	
	
	
	public NiveauManager() {
		super(Niveau.class);
	
	}



	@Override
	public EntityManager getEntityManger() {	
		return em;
	}

}
