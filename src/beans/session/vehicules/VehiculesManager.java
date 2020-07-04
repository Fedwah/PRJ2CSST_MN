package beans.session.vehicules;

import java.math.BigInteger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.vehicules.EtatsVehicule;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;


@Stateless
public class VehiculesManager extends BeanManager<Vehicule> {



    @PersistenceContext( unitName = "MN_unit" )
    EntityManager               em;

    public VehiculesManager() {
        super( Vehicule.class );
    }

    @Override
    public EntityManager getEntityManger() {
        return em;
    }

    
    @Override
    public boolean mettreAJour( Object id, BeanFactory<Vehicule> beanF, Vehicule newBean ) {
        Vehicule v = trouver( id );

        if ( v != null ) {

            
            newBean.setMarque( (Marque) ObtenirRefence( Marque.class, newBean.getMarque().getTitre() ) );
            newBean.setModele( (Modele) ObtenirRefence( Modele.class, newBean.getModele().getId() ) );

         
            // Remettre l'ancienne photo , si pas de nouveau
            if ( newBean.getPhoto() == null ) {
                System.out.println( "Vehicule image update" );
                newBean.setPhoto( v.getPhoto() );
            }
            beanF.updateChange( newBean, v );

            return true;
        }
        return false;
    }

    @Override
    public Vehicule trouver( Object id ) {
        // TODO Auto-generated method stub
        Vehicule v = super.trouver( id );
        if ( v != null ) {
            v.getUnite();
            System.out.println( "unite init " + v.getUnite() );
        }
        return v;
    }

  
    public void mettreAjourKM(Vehicule old ,Double distance_parcourue_old, Double distance_parcourue_new) {
        
        double newKm = old.getKm()+distance_parcourue_new-distance_parcourue_old;
        Vehicule v = trouver( old.getMatricule_interne() );
       
        System.out.println( "Mise a jour de KM de "+old.getKm() +" à "+newKm);
        v.setKm( newKm );
        
      
      
    }
    
    public void mettreAjourEtat(Vehicule old ,EtatsVehicule newEtat) {
        Vehicule new_v = trouver( old.getMatricule_interne() );
        
        System.out.println( "Mise a jour de l'etat de "+old.getEtat().label+" à "+new_v.getEtat().label );
       
       new_v.setEtat( newEtat );
        
    }

}
