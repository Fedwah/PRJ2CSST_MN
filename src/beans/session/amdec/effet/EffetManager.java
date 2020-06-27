package beans.session.amdec.effet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.amdec.Effet;
import beans.session.general.BeanManager;

@Stateless
public class EffetManager extends BeanManager<Effet> {
	@PersistenceContext(unitName = "MN_unit")
	EntityManager em;
	
	
	
	public EffetManager() {
		super(Effet.class);
	
	}



	@Override
	public EntityManager getEntityManger() {
		// TODO Auto-generated method stub
		return em;
	}

}
