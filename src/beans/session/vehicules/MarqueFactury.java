package beans.session.vehicules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.jboss.resteasy.util.InputStreamToByteArray;

import beans.entities.vehicules.Marque;
import beans.session.general.BeanValidator;

public class MarqueFactury {
    
    private static final String IMAGE = "image";
    private static final String PARAM_TITRE = "titre";
    private Map<String, ArrayList<String>> erreurs;
   
    
    public MarqueFactury() {
        // TODO Auto-generated constructor stub
    }
    
    public Marque create(HttpServletRequest request) {
        Marque marque = new Marque();
        marque.setTitre( request.getParameter( PARAM_TITRE ) );
        InputStreamToByteArray in = null;
        try {
            in = new InputStreamToByteArray(request.getPart( IMAGE ).getInputStream());
            if(in != null) marque.setImage(in.toByteArray() );
            
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch ( ServletException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return marque;
    }
    public boolean validate(Marque marque) {
       this.erreurs= new BeanValidator<Marque>(marque).getErreurs();
       return this.erreurs.isEmpty();
    }
    
    public Map<String, ArrayList<String>> getErreurs() {
        return erreurs;
    }
    

}
