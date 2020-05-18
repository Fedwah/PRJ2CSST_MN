package beans.session.vehicules.etats;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.vehicules.EtatVehicule;
import beans.session.general.BeanManager;

@Stateless
public class EtatVehiculeManager extends BeanManager<EtatVehicule>{

    @PersistenceContext(unitName = "MN_unit")
    EntityManager em;
    public EtatVehiculeManager() {
        super(EtatVehicule.class);
    }

    @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
        return this.em;
    }
}
