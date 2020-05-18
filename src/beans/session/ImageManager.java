package beans.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.general.Image;
import beans.session.general.BeanManager;

@Stateless
public class ImageManager extends BeanManager<Image> {

    @PersistenceContext(unitName = "MN_unit")
    EntityManager em;
    
  
    public ImageManager() {
        super(Image.class);
    }
    
    @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
       
        return  this.em;
    }
    
   
}
