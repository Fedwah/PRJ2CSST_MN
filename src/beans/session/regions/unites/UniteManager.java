package beans.session.regions.unites;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import beans.entities.regions.unites.Unite;
import beans.session.general.BeanManager;
@Stateless
public class UniteManager extends BeanManager<Unite> {
	@PersistenceContext(unitName = "MN_unit")
	EntityManager em;

	public UniteManager() {
		super(Unite.class);

	}

	@Override
	public EntityManager getEntityManger() {
		return em;
	}
}
