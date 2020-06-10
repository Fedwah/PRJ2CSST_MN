package beans.session.general;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import beans.entities.vehicules.Modele;

public abstract class BeanManager<T> {

    private Class<T> beanClass;

    public BeanManager() {
        // TODO Auto-generated constructor stub
    }

    public BeanManager( Class<T> beanClass ) {
        this.beanClass = beanClass;
    }

    public abstract EntityManager getEntityManger();

    // inserer � la base de donn�es
    public boolean ajouter( T bean ) {
        try {

            this.getEntityManger().persist( bean );

            return true;
        } catch ( Exception e ) {
            e.printStackTrace();
            return false;
        }

    }

    // inserer si l'objet n'existe pas d�j�
    public boolean ajouterUnique( T bean, Object id ) {
        if ( trouver( id ) == null ) {
            ajouter( bean );
            return true;
        }
        return false;

    }

    // supprimer
    public boolean supprimer( T bean ) {
        try {
            this.getEntityManger().remove(
                    ( this.getEntityManger().contains( bean ) ? bean : this.getEntityManger().merge( bean ) ) );
            return true;
        } catch ( Exception e ) {
            e.printStackTrace();
            return false;
        }
    }

    public List<T> lister( int debut, int nb ) {
        return this.getEntityManger().createQuery( "SELECT b from " + beanClass.getName() + " b" )
                .setFirstResult( debut ).setMaxResults( nb ).getResultList();
    }

    public List<T> lister() {
        return this.getEntityManger().createQuery( "SELECT b from " + beanClass.getName() + " b" ).getResultList();
    }

    public List<T> lister( Map<String, Object> fields ) {
        return this.QuerryBuilder( fields, true ).getResultList();
    }

    public T trouver( Object id ) {
        T bean = null;
        try {
            bean = (T) trouver( Class.forName( this.beanClass.getName() ), id );

            return bean;
        } catch ( ClassNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public T trouver( Class classToFind, Object idOfClass ) {
        try {
            return (T) this.getEntityManger().find( classToFind, idOfClass );
        } catch ( EntityNotFoundException e ) {
            System.out.println( "Erreur trouver :" + e.getMessage() );
            return null;
        }
    }

    public T trouver( Map<String, Object> fields ) {
    	
    	return (T) this.QuerryBuilder( fields, true ).getSingleResult() ;
          
    }

    public boolean trouverSupprimer( Object id ) {
        T bean;
        if ( id != null ) {
            if ( ( bean = this.trouver( id ) ) != null ) {
                return this.supprimer( bean );
            }
        }

        return false;
    }

    /**
     * // mise � jour dans la base de donn�e
     * 
     * @param id
     *            identifiant de la classe
     * @param beanF
     *            classe bean factory
     * @param newBean
     *            nouvelle classe � ins�rer
     * @return booleen : true si la modification a �t� faite, false si echec
     * 
     */
    public boolean mettreAJour( Object id, BeanFactory<T> beanF, T newBean ) {
        T bean = trouver( id );
        if ( bean != null ) {
            beanF.updateChange( newBean, bean );
            return true;
        }
        return false;

    }

    public T ObtenirRefrence( Object id ) {
        try {
            return (T) ObtenirRefence( Class.forName( this.beanClass.getName() ), id );
        } catch ( ClassNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public Object ObtenirRefence( Class classTorRef, Object idToRef ) {
        try {
            return this.getEntityManger().getReference( classTorRef, idToRef );
        } catch ( RuntimeException e ) {
            System.out.println( "Erreur ObtenirRefrence :" + e.getMessage() );
            return null;
        }

    }

    private Query QuerryBuilder(Map<String, Object> fields ,boolean and) {
        
        String qr = "";
        
        Iterator<Map.Entry<String, Object>> iterator = fields.entrySet().iterator();
        while(iterator.hasNext())
        {
            Map.Entry<String, Object> mapentry =  iterator.next();
            qr = qr + " b." + mapentry.getKey() + "= :" + mapentry.getKey();
            System.out.println(mapentry.getValue());
            if(iterator.hasNext())
            {
                qr = qr + (and?" and " :" or ") ;
            }
        }
        
        System.out.println(qr);
        
        Query query  = this.getEntityManger().createQuery("SELECT b FROM " + beanClass.getName() + " b"+ (qr!=""?" where" + qr:""));
        for (Map.Entry<String, Object> mapentry : fields.entrySet()) {
              query.setParameter((String) mapentry.getKey(), mapentry.getValue());
               
        }
        
        return query;
    }

}
