package beans.session.regions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.regions.Region;
import beans.session.general.BeanManager;


@Stateless
public class RegionManager extends BeanManager<Region> {
	@PersistenceContext(unitName = "MN_unit")
	EntityManager em;

	public RegionManager() {
		super(Region.class);

	}

	@Override
	public EntityManager getEntityManger() {
		return em;
	}
}
