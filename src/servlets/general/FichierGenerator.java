package servlets.general;

import java.io.IOException;
import java.net.URLEncoder;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptor.Uri;

import beans.entities.guide.Fichier;
import beans.session.general.page.PageGenerator;
import beans.session.guide.FichierFactory;
import beans.session.guide.FichierManager;

/**
 * Servlet implementation class FichierGenerator
 */
@WebServlet("/Fichier/generate/*")
public class FichierGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
    private FichierManager fichM;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FichierGenerator() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator();
		String id = pg.getPathId( request );
		Fichier f = null;
		FichierFactory fichF = new FichierFactory();
		System.out.println( "test : '"+id+"'");
		if(id!=null && !id.isEmpty()) {
		    f = fichM.trouverAvecDonnee( id );
		    if(f!=null) {
		        response.reset();
		        
	            response.setContentType(f.getType());
	            response.setContentLength( (int) f.getTaille() );
	            if(fichF.isFileUnSupportedByBrowser( f.getType() )) {
	                response.setHeader( "Content-Disposition", "attachment; filename=\"" +  f.getNom() +"\"" );
	            }
	           
	            response.getOutputStream().write( f.getData().getBin());
	            System.out.println( "file generated (size : "+f.getData().getBin().length+')');
	            
		    }else {
		        response.getWriter().write( "erreur fichier non trouver" );
		    }
		}else {
		    response.getWriter().write( "erreur pas d'id" );
		}
		
		
		
	 }
	

	

}
