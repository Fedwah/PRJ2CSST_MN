package servlets.guides;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import beans.entities.guide.Fichier;
import beans.session.general.BeanManager;
import beans.session.general.page.PageGenerator;
import beans.session.guide.FichierFactory;
import beans.session.guide.FichierManager;

/**
 * Servlet implementation class CreationFichier
 */
@WebServlet("/Fichier/importer")
@MultipartConfig
public class CreationFichier extends HttpServlet {
	
  
    private static final long serialVersionUID = 1L;
    
    
    @EJB
    private FichierManager fm ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationFichier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PageGenerator pg = new PageGenerator( FichierFactory.VUE_FORM, FichierFactory.TITRE_VUE_FORM );

		pg.generate( getServletContext(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PageGenerator pg = new PageGenerator( FichierFactory.VUE_FORM, FichierFactory.TITRE_VUE_FORM ,FichierFactory.REDIRECCT_URL);
	    FichierFactory fichF = new FichierFactory();
	    Part p =  request.getPart( "file" );
	    Fichier f = fichF.create( request );
	    List<JSONObject> o = new ArrayList<JSONObject>(); 
	    
	    
	    System.out.println( "test" );
        if(fichF.validate( f ,fm,f.getNom())) {
            if(fm.ajouter( f, p.getInputStream() )) {
                JSONObject obj = new JSONObject();
                obj.put( "success", 1 );
                obj.put( "message", f.getNom() +" a bien été ajouter" );
                o.add( obj );
                
                pg.generateJSON( response, o );
                return;
            }
        }
        
        JSONObject obj = new JSONObject();
        obj.put( "success", 0 );
        obj.put( "erreurs", fichF.getErreurs() );
        o.add( obj );
        pg.generateJSON( response, o );
        
	}

}
