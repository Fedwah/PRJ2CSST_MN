package beans.session.amdec.cause;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.amdec.Cause;
import beans.session.general.BeanManager;

@Stateless
public class CausesManager extends BeanManager<Cause> {
	@PersistenceContext(unitName = "MN_unit")
	EntityManager em;
	
	
	public CausesManager() {
		super(Cause.class);
	}


	@Override
	public EntityManager getEntityManger() {
		// TODO Auto-generated method stub
		return em;
	}

}
