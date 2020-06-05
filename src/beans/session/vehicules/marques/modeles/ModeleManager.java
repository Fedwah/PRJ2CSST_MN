package beans.session.vehicules.marques.modeles;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.vehicules.Modele;
import beans.session.general.BeanManager;

@Stateless
public class ModeleManager extends BeanManager<Modele> {

    @PersistenceContext(unitName = "MN_unit")
    EntityManager em;
    public ModeleManager() {
        super(Modele.class);
    }
    @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
        return this.em;
    }
}
