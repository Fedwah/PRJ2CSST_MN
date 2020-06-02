package beans.session.general;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import beans.entities.pieces.Piece;

public abstract class BeanManager<T> {

    private Class<T> beanClass;

    public BeanManager() {
        // TODO Auto-generated constructor stub
    }

    public BeanManager( Class<T> beanClass ) {
        this.beanClass = beanClass;
    }

    public abstract EntityManager getEntityManger();
    //inserer � la base de donn�es
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
    // Lister tout les �lements de la table (does not work for the moment)
  /*  public List<Piece> getList()
	{
		return this.getEntityManger().createQuery("select l from" + beanClass.getName() + "l").getResultList();
	}*/
    public List<T> lister( int debut, int nb ) {
        return this.getEntityManger().createQuery( "SELECT b from " + beanClass.getName() + " b" )
                .setFirstResult( debut ).setMaxResults( nb ).getResultList();
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

    public Object trouver( Class classToFind, Object idOfClass ) {
        try {
            return this.getEntityManger().find( classToFind, idOfClass );
        } catch ( EntityNotFoundException e ) {
            System.out.println( "Erreur trouver :" + e.getMessage() );
            return null;
        }
    }

    public T trouver( Map<String, String[]> parameters ) {
        String where = "";

        for ( Map.Entry<String, String[]> param : parameters.entrySet() ) {
            where.concat( param.getKey() + "= :" + param.getKey() );
            this.getEntityManger().setProperty( param.getKey(), param.getValue()[0] );
        }

        return (T) this.getEntityManger().createQuery( "SELCET b FROM " + beanClass.getName() + " b where " + where )
                .getSingleResult();
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

    public void mettreAJour( Object id, BeanFactory<T> beanF, T newBean ) {
        T bean = trouver( id );
        beanF.updateChange( newBean, bean );
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
    

}
