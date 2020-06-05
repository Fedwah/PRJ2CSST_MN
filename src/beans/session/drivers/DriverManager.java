package beans.session.drivers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	

}
