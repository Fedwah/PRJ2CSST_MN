package servlets.driver;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.session.drivers.DriverManager;
import beans.session.general.page.PageGenerator;

/**
 * Servlet implementation class DriverDelete
 */
@WebServlet("/drivers/remove/*")
public class DriverDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DRIVERS = "/drivers";
	@EJB
	private DriverManager em;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(DRIVERS);
        String id = request.getPathInfo().substring( 1 );// id of element
        try
        {
    	
        	em.trouverSupprimer( Integer.parseInt(id) );
        }
        catch(Exception e)
        {
        	System.out.println("inside catch");
        	HttpSession session = request.getSession();
        	boolean exemple = true ;
        	session.setAttribute( "exception", exemple );
        	
        }
  
        pg.redirect( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
