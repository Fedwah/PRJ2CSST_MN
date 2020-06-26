package beans.session.guide;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import beans.entities.guide.Fichier;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

public class FichierFactory  extends BeanFactory<Fichier>{
    
    public static final String VUE_FORM = "/WEB-INF/vues/fichiers/fichier.form.jsp";
    public static final String TITRE_VUE_FORM = "Importer un fichier";
    public static final String VUE_LIST = "/WEB-INF/vues/fichiers/fichier.list.jsp";
    public static final String TITRE_VUE_LIST = "Liste des fichiers";
    public static final String REDIRECCT_URL = "/Fichiers";
    public static final String VUE_DETAIL = "/WEB-INF/vues/fichiers/fichier.detail.jsp";
    
    public static final String[] browserUnSupportedTypes = {"officedocument"};
    
    
    public FichierFactory() {
        super( Fichier.class );
    }
    
    
    
    @Override
    public Fichier create( HttpServletRequest request ) {
        try {
            Part file = request.getPart( "file" );
            System.out.println( "Name "+file.getName() );
            return new Fichier( file.getSubmittedFileName(), file.getContentType(), 
                       request.getParameter( "description" ),file.getSize(),
                       this.readFile( file.getInputStream() ));
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch ( ServletException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public void updateChange( Fichier newB, Fichier old ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void validateChilds( Fichier bean, BeanManager<Fichier> beanM ) {
        // TODO Auto-generated method stub
        
    }



    public Boolean isFileUnSupportedByBrowser( String type ) {
        for ( int i = 0; i < browserUnSupportedTypes.length; i++ ) {
            if(type.contains( browserUnSupportedTypes[i] ))
                return true;
    
        }
        return false ;
    }
    
    
    

}
