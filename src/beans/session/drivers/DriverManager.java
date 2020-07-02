package beans.session.drivers;

import java.math.BigInteger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import beans.entities.driver.Driver;
import beans.entities.vehicules.EtatsVehicule;
import beans.session.general.BeanManager;
import beans.session.general.page.PageGenerator;
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
