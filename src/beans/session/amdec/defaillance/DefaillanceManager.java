package beans.session.amdec.defaillance;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.amdec.Defaillance;
import beans.session.general.BeanManager;

@Stateless
public class DefaillanceManager extends BeanManager<Defaillance>{
	@PersistenceContext(unitName = "MN_unit")
	EntityManager em;
	
	public DefaillanceManager() {
		super(Defaillance.class);
	
	}

	@Override
	public EntityManager getEntityManger() {
		// TODO Auto-generated method stub
		return em;
	}
	
	

}
