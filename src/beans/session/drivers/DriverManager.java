package beans.session.drivers;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import beans.entities.driver.Driver;
import beans.session.general.BeanManager;
@Stateless
public class DriverManager extends BeanManager<Driver>{
	@PersistenceContext(unitName = "MN_unit")
	EntityManager em;
	
	public DriverManager() {
		super(Driver.class);
	}

	@Override
	public EntityManager getEntityManger() {
		// TODO Auto-generated method stub
		return em;
	}
	
	public List<Driver> listerDESC(String codeUN)
	{
		Query qr = this.em.createQuery("select b from Driver b where b.unite.codeUN = :un order by b.recruitDate DESC");
		qr.setParameter("un", codeUN);
		List<Driver> drs = qr.getResultList();
		return drs;
	}
	
	public List<Driver> listerASC(String codeUN)
	{
		Query qr = this.em.createQuery("select b from Driver b where b.unite.codeUN = :un order by b.recruitDate ASC");
		qr.setParameter("un", codeUN);
		List<Driver> drs = qr.getResultList();
		return drs;
	}
	

}
