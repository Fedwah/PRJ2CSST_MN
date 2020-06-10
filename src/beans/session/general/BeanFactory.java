package beans.session.general;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import beans.entities.general.Image;
import beans.session.vehicules.marques.MarqueFactory;

public abstract class BeanFactory<T> {

    private static final String            MSG_ERREUR_ID_NON_UNIQUE = "doit etre ounique";
    private Map<String, ArrayList<String>> erreurs;
    private Map<String, Object>            filtres;
    private EntityField<T>                 entityFields;
    private Class<T> beanClass;
    
    public BeanFactory( Class<T> beanClass) {
        this.filtres = new HashMap<String, Object>();
        
        this.entityFields = new EntityField<T>();
        this.beanClass = beanClass;
        this.getEntityFields().generateFields( beanClass );
    }
    
    public BeanFactory() {
        this.filtres = new HashMap<String, Object>();
    }

    public abstract T create( HttpServletRequest request );

    public boolean validate( T bean ) {
        boolean result = true;
        if ( bean != null ) {
            this.erreurs = new BeanValidator<T>( bean ).getErreurs();
            validateChilds( bean );

            if ( !this.erreurs.isEmpty() ) {
                System.out.println( "list of errors not empty" );

                for ( ArrayList<String> err : this.erreurs.values() ) {
                    // TODO pas sur a 100% de ce test
                    if ( !err.isEmpty() ) {
                        result = result && err.get( 0 ).equals( "{}" );
                    }

                }
            }
            System.out
                    .println( this.getClass().getSimpleName() + "is valid ? :" + ( this.erreurs.isEmpty() || result ) );
            return ( this.erreurs.isEmpty() || result );
        } else {
            this.erreurs = new HashMap<String, ArrayList<String>>();
        }

        return false;

    }

    public abstract void validateChilds( T bean );

    public Map<String, ArrayList<String>> getErreurs() {
        return erreurs;
    }

    public ArrayList<String> getFieldError( String fieldName ) {

        if ( getErreurs() != null ) {
            if ( getErreurs().containsKey( fieldName ) ) {
                return getErreurs().get( fieldName );
            }
        }

        return new ArrayList<String>();
    }

    public void addErreurs( String champ, ArrayList<String> erreurs ) {

        addErreurs( champ, (String[]) erreurs.toArray() );
    }

    public void addErreurs( String champ, String... erreurs ) {

        if ( erreurs.length != 0 ) {
            if ( this.erreurs.containsKey( champ ) ) {
                // System.out.println( "Ajout de l'erreur" );
                this.erreurs.get( champ ).addAll( Arrays.asList( erreurs ) );
            } else {
                // System.out.println( "Creation de l'erreur" );
                this.erreurs.put( champ, new ArrayList<String>( Arrays.asList( erreurs ) ) );
            }
        }

    }

    public Image readImage( HttpServletRequest request, String PARAM_IMAGE ) {
        InputStream in = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Image img = null;
        try {
            in = request.getPart( PARAM_IMAGE ).getInputStream();

            int length;
            byte[] buffer = new byte[1024];

            if ( in != null && !request.getPart( PARAM_IMAGE ).getSubmittedFileName().isEmpty() ) {
                img = new Image();
                while ( ( length = in.read( buffer ) ) != -1 )
                    out.write( buffer, 0, length );

                img.setTitre( request.getPart( PARAM_IMAGE ).getSubmittedFileName() );
                img.setBinary( out.toByteArray() );
                System.out.println( "IMG readed " + img.getTitre() );
            }

        } catch ( IOException e ) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        } catch ( ServletException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return img;
    }

    public boolean uniqueSave( BeanManager<T> em, T bean, Object id, String PARAM_ID ) {

        if ( em.ajouterUnique( bean, id ) ) {
            System.out.println( "Bean " + id + " added" );
            return true;
        } else {
            System.out.println( "Bean " + id + " error unique" );
            System.out.println( "PARAM ID  : " + PARAM_ID );

            this.addErreurs( PARAM_ID, MSG_ERREUR_ID_NON_UNIQUE );
        }

        return false;
    }

    public boolean updateAndValidate( HttpServletRequest request, T old ) {
        T newB = create( request );

        if ( validate( newB ) ) {
            System.out.println( "NEW bean validated" );
            updateChange( newB, old );
            return true;
        }

        return false;
    };

    public abstract void updateChange( T newB, T old );

    public EntityField<T> getEntityFields() {   
        return entityFields;
    }
    
    public Map<String,String> fieldName() {
        return getEntityFields().fieldsNames();
    }

    public Map<String, Object> getFiltres() {
        return filtres;
    }
    
    public void addFiltre(String field , Object value) {
         addFiltre(field,"", value );
    }
    
    public void addFiltre(String field ,String subField,Object value) {
        if(field!="" && value!=null) {
            if(subField!="") {
                this.filtres.put(field+"."+subField, value );
            }else {
                this.filtres.put(field, value );
            }
            
        }
    }
    
    
}
