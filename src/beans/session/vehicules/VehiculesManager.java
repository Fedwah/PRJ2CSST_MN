package beans.session.vehicules;




import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.regions.unites.Unite;
import beans.entities.vehicules.EtatVehicule;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;
import beans.session.vehicules.marques.modeles.ModeleFactory;

@Stateless
public class VehiculesManager  extends BeanManager<Vehicule>{

   @PersistenceContext(unitName = "MN_unit")
   EntityManager em;

   public VehiculesManager() {
       super(Vehicule.class);
   }       
    
   @Override
    public EntityManager getEntityManger() {
        return em;
    }
   
   @Override
    public boolean mettreAJour( Object id, BeanFactory<Vehicule> beanF, Vehicule newBean ) {
        Vehicule v = trouver( id );
       
        if(v!=null) {
            
            newBean.setEtat( (EtatVehicule)ObtenirRefence( EtatVehicule.class, newBean.getEtat().getTitre() ) );
            newBean.setMarque( (Marque)ObtenirRefence( Marque.class, newBean.getMarque().getTitre() ) );    
            newBean.setModele((Modele)ObtenirRefence( Modele.class, newBean.getModele().getId()));
            
            //Remettre l'ancienne photo , si pas de nouveau
            if(newBean.getPhoto()==null) {
                newBean.setPhoto( v.getPhoto() );
            }
                 
            beanF.updateChange(newBean,v);
            
            return true;
        }
        return false;
    }
   
  @Override
    public Vehicule trouver( Object id ) {
        // TODO Auto-generated method stub
        Vehicule v = super.trouver( id );
        if(v!=null) {
            System.out.println("unite init "+v.getUnite());
        }
        return v;
    }
}
