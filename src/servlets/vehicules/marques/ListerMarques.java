package servlets.vehicules.marques;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.session.general.PageGenerator;
import beans.session.vehicules.marques.MarqueManager;

/**
 * Servlet implementation class ListerMarques
 */
@WebServlet("/Marques")
public class ListerMarques extends HttpServlet {
    
   
    private static final String VUE = "/WEB-INF/vues/vehicules/marques/marques.list.jsp";
    private static final String TITRE_VUE= "Liste des marques";
    
    @EJB
    private MarqueManager db;
    
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerMarques() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PageGenerator pg = new PageGenerator( VUE, TITRE_VUE );
		
		request.setAttribute( "marques",db.lister(0,10));
		pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
