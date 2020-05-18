package beans.session.general;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import beans.entities.general.Image;
import beans.session.vehicules.marques.MarqueFactory;

public abstract class BeanFactory<T> {

    private static final String            MSG_ERREUR_ID_NON_UNIQUE = "doit etre ounique";
    private Map<String, ArrayList<String>> erreurs;

    public abstract T create( HttpServletRequest request );

    public BeanFactory() {
        // TODO Auto-generated constructor stub
    }

    public boolean validate( T bean ) {
        boolean result = true;
        if ( bean != null ) {
            this.erreurs = new BeanValidator<T>( bean ).getErreurs();
            validateChilds( bean );

            if ( !this.erreurs.isEmpty() ) {
                for ( ArrayList<String> err : this.getErreurs().values() ) {
                    System.out.println( "List " + err.get( 0 ) + " Empty : " + err.isEmpty() );
                    result = result && err.isEmpty();
                }
            }

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
     
        addErreurs( champ, (String[])erreurs.toArray());
    }
    public void addErreurs(String champ,String... erreurs) {
        
        if(erreurs.length==0) {
            if ( this.erreurs.containsKey( champ ) ) {
                this.erreurs.get( champ ).addAll(Arrays.asList(erreurs ));
            } else {
                this.erreurs.put( champ, new ArrayList<String>(Arrays.asList(erreurs )));
            }
        }
       
    }

    public Image readImage( HttpServletRequest request, String PARAM_IMAGE ) {
        InputStream in = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Image img = null;
        try {
            in = request.getPart( PARAM_IMAGE ).getInputStream();
            img = new Image();
            img.setTitre( request.getPart( PARAM_IMAGE ).getSubmittedFileName() );

            int length;
            byte[] buffer = new byte[1024];
            while ( ( length = in.read( buffer ) ) != -1 )
                out.write( buffer, 0, length );

            System.out.println( "IMG readed " + img.getTitre() );

            if ( in != null )
                img.setBinary( out.toByteArray() );

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
            // System.out.println( "Bean "+id+" added" );
            return true;
        } else {
            // System.out.println( "Bean "+id+" read" );
            this.addErreurs( MarqueFactory.PARAM_TITRE, MSG_ERREUR_ID_NON_UNIQUE );
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

}
