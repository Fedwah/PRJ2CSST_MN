package beans.session.maintenance;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.maintenance.Maintenance;
import beans.session.general.BeanManager;

@Stateless
public class MaintenanceManager extends BeanManager<Maintenance> {
	
	@PersistenceContext(unitName = "MN_unit")
	EntityManager em;

	public MaintenanceManager() {
		super(Maintenance.class);

	}
	
	@Override
	public EntityManager getEntityManger() {
		
		return em;
	}

}
