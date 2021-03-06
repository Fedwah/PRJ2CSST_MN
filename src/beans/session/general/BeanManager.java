package beans.session.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.transaction.RollbackException;

import beans.entities.regions.Region;
import beans.entities.regions.unites.Unite;
import beans.entities.utilisateurs.Utilisateur;
import beans.session.general.fields.EntityFields;
import beans.session.general.fields.FieldDefinition;
import beans.session.general.page.PageGenerator;

public abstract class BeanManager<T> {

    private Class<T>        beanClass;
    private EntityFields<T> fields;

    public BeanManager() {
        // TODO Auto-generated constructor stub
    }

    public BeanManager( Class<T> beanClass ) {
        this.beanClass = beanClass;
        this.fields = new EntityFields<T>();
        this.fields.generateFields( beanClass );
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
            this.getEntityManger().flush();
            return true;
        } catch ( Exception e ) {
            System.out.println( "Suppprimer impossible " + e.getMessage() );
            return false;
        }

    }

    public boolean trouverSupprimer( Object id ) {
        T bean;
        if ( id != null ) {
            if ( ( bean = this.trouver( id ) ) != null ) {
          

                return this.supprimer( bean );

            }
            else System.out.println("element non trouv� ");
        }

        return false;
    }

    // lister

    public List<T> lister( int debut, int nb ) {
        return this.getEntityManger().createQuery( "SELECT b from " + beanClass.getName() + " b" )
                .setFirstResult( debut ).setMaxResults( nb ).getResultList();
    }

    public List<T> lister() {

        return lister( new HashMap<String, Object>() );

    }

    public List<T> lister( Map<String, Object> fields ) {

        return this.QuerryBuilder( fields, true, "" ).getResultList();

    }

    public List<T> searchby( Map<String, Object> fields ) {
        return this.QuerryBuilderSearch( fields, true, "" ).getResultList();
    }

    public List<T> lister( Map<String, Object> fields, String orderBy ) {

        return this.QuerryBuilder( fields, true, orderBy ).getResultList();

    }

    public T trouver( Object id ) {
        T bean = null;
        try {
            bean = (T) trouver( Class.forName( this.beanClass.getName() ), id );

            return bean;
        } catch ( ClassNotFoundException e ) {
            // TODO Auto-generated catch block
            System.out.println( "erreur trouver " + e.getMessage() );
            return null;
        }

    }

    public Object trouver( Class classToFind, Object idOfClass ) {
        try {
            return  this.getEntityManger().find( classToFind, idOfClass );
        } catch ( EntityNotFoundException e ) {
            System.out.println( "Erreur trouver :" + e.getMessage() );
            return null;
        }
    }

    public T trouver( Map<String, Object> fields ) {
        try {
            return (T) this.QuerryBuilder( fields, true, "" ).getSingleResult();
        } catch ( Exception e ) {
            System.out.println( "non trouver"+fields );
            return null;
        }
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
        System.out.println(
                "Change in " + this.beanClass.getName() + "updated " + ( this.getEntityManger().contains( bean ) ) );
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

    public Object ObtenirRefence( Class<?> classTorRef, Object idToRef ) {
        try {
            return this.getEntityManger().getReference( classTorRef, idToRef );
        } catch ( RuntimeException e ) {
            System.out.println( "Erreur ObtenirRefrence :" + e.getMessage() );
            return null;
        }

    }

    private Query QuerryBuilder(Map<String, Object> values, boolean and, String orderBy ) {

        EntityFields<T> fields = new EntityFields<T>();
        String where = "";
        String join = "";
        String q = null;

        values.putAll( getUserRoleFiltre() );

        System.out.println( this.beanClass );
        fields.generateFields( this.beanClass );
        System.out.println( "fields : " + fields.fields().toString() );
        join = joinBuilder( fields, values );
        System.out.println( "join est " + join );
        where = whereBuilder( fields, values, and );
        System.out.println( "where est " + where );
        
        q = "SELECT b FROM " + beanClass.getName() + " b" +
                ( join != "" ? join : "" ) +
                ( where != "" ? " WHERE " + where : "" ) +
                ( orderBy != "" ? " ORDER BY b." + orderBy : "" );

        System.out.println( "Query genrated: " + q );

        Query query = this.getEntityManger().createQuery( q );

        System.out.println( "Query Builded " );

        for ( Map.Entry<String, Object> mapentry : values.entrySet() ) {
            // System.out.println( "Bounded :"+fields.getValidName((
            // mapentry.getKey()))+" "+ mapentry.getValue());
            query.setParameter( (String) mapentry.getKey().replace( '.', '_' ), 
                    fields.cast(mapentry.getKey(),mapentry.getValue()) );
        }

        return query;
    }

    private Query QuerryBuilderSearch( Map<String, Object> values, boolean and, String orderBy ) {

        String where = "";
        String join = "";
        String q = null;

        values.putAll( getUserRoleFiltre() );

        fields.generateFields( this.beanClass );
        //System.out.println( "fields : " + fields.fields().toString() );;
        where = whereBuilderSearch( fields, values, and );
       
        join = joinBuilder( fields, values );
        
        q = "SELECT b FROM " + beanClass.getName() + " b" +
                ( join != "" ? join : "" ) +
                ( where != "" ? " WHERE " + where : "" ) +
                ( orderBy != "" ? " ORDER BY b." + orderBy : "" );

        Query query = this.getEntityManger().createQuery( q );

        System.out.println( "Query Build: " + q );

        for ( Map.Entry<String, Object> mapentry : values.entrySet() ) {
            if(fields.fields().
                    get( fields.getValidName( 
                            ( mapentry.getKey() ) )).class_.equals( "java.lang.String" )) {
                
                query.setParameter( mapentry.getKey().replace( '.', '_' ),
                        "%" + mapentry.getValue() + "%" );
                System.out.println( "set param String" );
            }else {
                query.setParameter( mapentry.getKey().replace( '.', '_' ),
                       fields.cast(mapentry.getKey(),mapentry.getValue()));
            }
            
        }

        return query;
    }

    private String joinBuilder( EntityFields<T> fields, Map<String, Object> values ) {
        String j = "";
        String add = "";
        Iterator<Map.Entry<String, Object>> it = values.entrySet().iterator();
        Map<String, FieldDefinition> map = fields.fields();

        List<String> added = new ArrayList<String>();
        while ( it.hasNext() ) {
            Map.Entry<String, Object> f = it.next();
            
            if ( !map.get( fields.getValidName( f.getKey() ) ).isBasicClass ) {
                add = f.getKey().substring( 0, f.getKey().indexOf( '.' ) );
                if(!added.contains( add )) {
                    added.add( add );
                    j = j + " JOIN b." + add + " "
                            + fields.getValidName( f.getKey() );
                       
                }
             
            }

        }

        return j;
    }

    private String whereBuilder( EntityFields<T> fields, Map<String, Object> values, boolean and ) {
        String qr = "";

        Iterator<Map.Entry<String, Object>> iterator = values.entrySet().iterator();
        Map<String, FieldDefinition> map = fields.fields();

        while ( iterator.hasNext() ) {
            Map.Entry<String, Object> f = iterator.next();

            if ( map.get( fields.getValidName( f.getKey() ) ).isBasicClass ) {
                qr = qr + " b." + f.getKey() + " = :" + f.getKey();
            } else {
                qr = qr + f.getKey() + " = :" +  f.getKey().replace( '.', '_' );
            }

            if ( iterator.hasNext() ) {
                qr = qr + ( and ? " and " : " or " );
            }
        }

        return qr;
    }

    
    private String whereBuilderSearch( EntityFields<T> fields, Map<String, Object> values, boolean and ) {
        String qr = "";

        Iterator<Map.Entry<String, Object>> iterator = values.entrySet().iterator();
        Map<String, FieldDefinition> map = fields.fields();

        while ( iterator.hasNext() ) {
            Map.Entry<String, Object> f = iterator.next();

            if ( map.get( fields.getValidName( f.getKey() ) ).isBasicClass ) {
                if(map.get( fields.getValidName( f.getKey() ) ).class_.contains( "java.lang.String" )) {
                    qr = qr + " b." + f.getKey() + " like :" + f.getKey().replace( '.', '_' );
                }else {
                    // le  like marche que sur les String
                    qr = qr + " b." + f.getKey() + " = :" + f.getKey().replace( '.', '_' );
                }
                
            } else {
                qr = qr + f.getKey() + " like :" +  f.getKey().replace( '.', '_' ) ;
            }

            if ( iterator.hasNext() ) {
                qr = qr + ( and ? " and " : " or " );
            }
        }

        return qr;
    }

    public T ObtenirDernier() {
        List<?> l = this.getEntityManger()
                .createQuery( "SELECT b from " + this.beanClass.getName() + " b ORDER by "
                        + this.fields.getIdField().name + " DESC" )
                .setMaxResults( 1 )
                .getResultList();

        if ( l.size() > 0 ) {
            return (T) l.get( 0 );
        } else
            return null;

    }

    public T ObtenirDernier( Map<String, Object> filtre ) {
        List<?> l = this.QuerryBuilder( filtre, true, this.fields.getIdField().name + " DESC" )
                .setMaxResults( 1 ).getResultList();
        if ( l.size() > 0 ) {
            return (T) l.get( 0 );
        } else
            return null;

    }

    private Map<String, Object> getUserRoleFiltre() {

        Map<String, Object> filtre = new LinkedHashMap<String, Object>();

        FieldDefinition f = null;
        String idName = "";
        Object value = null;
        try {
            Utilisateur u = PageGenerator.getUtilisateur();
            if ( u != null ) {
                
                f = this.fields.getFieldByClass( Unite.class );
                
                if ( f != null ) {
                    System.out.println( "call get method : " + f.name );
                    
                    idName = this.fields.getChildIdName( f.name );
                    value = u.getCodeun();
                    if ( value != null && !((String)value).isEmpty() ) {
                        filtre.put( f.name + "." + idName, value );
                    }else {
                        value = this.trouver( Region.class, u.getCodereg() );
                        if(value!=null) {
                            filtre.put( f.name + "." + "region", value );
                        }
                        
                    }
                    
                        
                }
                
             
             
               

                

            }
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        System.out.println( "Role filter : " + filtre );
        return filtre;
    }

    public Integer count() {
        
        return ((Number)getEntityManger().
                createQuery( "SELECT COUNT(b) from " + beanClass.getName() + " b ")
                .getSingleResult()).intValue();
    }
}
