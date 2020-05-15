package servlets.vehicules;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.Marque;
import beans.session.general.PageGenerator;
import beans.session.vehicules.MarqueFactury;
import beans.session.vehicules.MarqueManager;

/**
 * Servlet implementation class CreationMarque
 */
@WebServlet("/Marques/add")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class CreationMarques extends HttpServlet {
    private static final String ERREURS = "erreurs";
    private static final String ATT_MARQUE = "marque";
    private static final String PARENT = "/WEB-INF/index.jsp";
    private static final String VUE_IN = "/WEB-INF/vues/vehicules/marques/marques.form.jsp";
    private static final String VUE_OUT = "/WEB-INF/vues/vehicules/marques/marques.show.jsp";
    private static final String TITRE_VUE_IN= "Creation d'une marque";
    private static final String TITRE_VUE_OUT= "Detail d'une marque";
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
	    PageGenerator pg = new PageGenerator(PARENT,VUE_IN,TITRE_VUE_IN);
        pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PageGenerator pg = null;
        
        
        MarqueFactury mf = new MarqueFactury();
        Marque m = mf.create( request );
        
        if(mf.validate( m )) {
            System.out.println(db==null);
            db.ajouter( m );
            pg = new PageGenerator(PARENT,VUE_OUT,TITRE_VUE_OUT);
            
        }else {
            pg = new PageGenerator(PARENT,VUE_IN,TITRE_VUE_IN);
        }
        request.setAttribute( ATT_MARQUE, m );
        request.setAttribute(ERREURS, mf.getErreurs() );
        pg.generate( getServletContext(), request, response );
	}
	

}
