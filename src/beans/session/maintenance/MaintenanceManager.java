package beans.session.maintenance;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import beans.entities.maintenance.Maintenance;
import beans.entities.utilisateurs.Utilisateur;
import beans.entities.vehicules.Vehicule;
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

	public List<Maintenance> findCurrentMaintenance(Maintenance bean)
	{
		Query qr = this.em.createQuery("select b from Maintenance b where b.v.matricule_interne = :mat and b.endDate is null ");
		qr.setParameter("mat", bean.getV().getMatricule_interne());
		List<Maintenance> m = qr.getResultList();
		return m;
		
	}
	
	public List<Maintenance> findCurrentMaintenance(Vehicule v)
	{
		Query qr = this.em.createQuery("select b from Maintenance b where b.v.matricule_interne = :mat and b.endDate is null ");
		qr.setParameter("mat", v.getMatricule_interne());
		List<Maintenance> m = qr.getResultList();
		return m;
		
	}
	public List<Maintenance> monthlyMaintenance(int month, int year, Utilisateur user)
	{
		// dans cette fonction j'ai oublié de traiter le filtre par unité 
		Query qr = this.em.createQuery("select b from Maintenance b where function('YEAR', b.startDate) = :year and "
				+ "function('MONTH',b.startDate) = :month and b.un.codeUN = :un order by b.startDate");
		qr.setParameter("year", year);
		qr.setParameter("month", month);
		qr.setParameter("un", user.getCodeun());
		List<Maintenance> m = qr.getResultList();
		return m;
	}
}
