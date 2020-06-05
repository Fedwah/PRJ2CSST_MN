package beans.session.vehicules;




import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import beans.entities.vehicules.EtatVehicule;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;
import beans.session.vehicules.modeles.ModeleFactory;

@Stateless
public class VehiculesManager  extends BeanManager<Vehicule>{

   @PersistenceContext(unitName = "MN_unit")
   EntityManager em;

   public VehiculesManager() {
       super(Vehicule.class);
   }       
    
   @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
        return em;
    }
   
   @Override
    public boolean mettreAJour( Object id, BeanFactory<Vehicule> beanF, Vehicule newBean ) {
        // TODO Auto-generated method stub
        Vehicule v = trouver( id );
        Modele m = null;
        ModeleFactory mf = new ModeleFactory();
        if(v!=null) {
            
            newBean.setEtat( (EtatVehicule)ObtenirRefence( EtatVehicule.class, newBean.getEtat().getTitre() ) );
            newBean.setMarque( (Marque)ObtenirRefence( Marque.class, newBean.getMarque().getTitre() ) );
         
            
           
            newBean.setModel((Modele)ObtenirRefence( Modele.class, newBean.getModele().getTitre() ));
           
            //Remettre l'ancienne photo , si pas de nouveau
            if(newBean.getPhoto().getTitre()==null) {
                newBean.setPhoto( v.getPhoto() );
            }
                 
            beanF.updateChange(newBean,v);
            System.out.println( "Change updated "+ (this.getEntityManger().contains( v )) );
            return true;
        }
        return false;
    }
}
