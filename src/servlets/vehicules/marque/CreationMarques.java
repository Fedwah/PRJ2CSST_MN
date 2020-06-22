package servlets.vehicules.marque;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.Marque;
import beans.session.general.page.PageGenerator;
import beans.session.vehicules.marques.MarqueFactory;
import beans.session.vehicules.marques.MarqueManager;

/**
 * Servlet implementation class CreationMarque
 */
@WebServlet("/Marques/add")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class CreationMarques extends HttpServlet {
    private static final String PARAM_TITRE = "titre";
    private static final String ATT_ERREURS = "erreurs";
    private static final String ATT_MARQUE = "marque";
    
   
    private static final String TITRE_VUE= "Creation d'une marque";
    private static final long serialVersionUID = 1L;
	
    @EJB
    private MarqueManager db;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationMarques() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PageGenerator pg = new PageGenerator( MarqueFactory.VUE_FORM, TITRE_VUE );
        
	    pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PageGenerator pg = new PageGenerator( MarqueFactory.VUE_FORM,TITRE_VUE, MarqueFactory.DEFAULT_REDIRECT_URL);
        
        System.out.println(request.getParameter(PARAM_TITRE));
        MarqueFactory mf = new MarqueFactory();
        Marque m = mf.create( request );
        
        //System.out.println( "IMAGE ID "+m.getImage().getTitre() );
        
        if(mf.validate( m )){
            mf.uniqueSave( db, m, m.getTitre(),MarqueFactory.PARAM_TITRE);
            pg.redirect(getServletContext(), request, response );
            
        }else{
            request.setAttribute(ATT_MARQUE, m );
            request.setAttribute(ATT_ERREURS, mf.getErreurs() );
            pg.generate( getServletContext(), request, response );
        };
       
	}
	

}
