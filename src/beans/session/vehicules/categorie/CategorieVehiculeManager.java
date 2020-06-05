package beans.session.vehicules.categorie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.vehicules.CategorieVehicule;
import beans.session.general.BeanManager;

@Stateless
public class CategorieVehiculeManager extends BeanManager<CategorieVehicule> {

    @PersistenceContext(unitName = "MN_unit")
    EntityManager em;
    
    public CategorieVehiculeManager() {
        super(CategorieVehicule.class);
    }
    @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
        return this.em;
    }
}
