package beans.session.maintenance;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	public List<Maintenance> findCurrentMaintenace(Maintenance bean)
	{
		Query qr = this.em.createQuery("select b from Maintenance b where b.v.matricule_interne = :mat and b.endDate is null");
		qr.setParameter("mat", bean.getV().getMatricule_interne());
		List<Maintenance> m = qr.getResultList();
		return m;
		
	}
}
