package servlets.amdec.effet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.session.amdec.defaillance.DefaillanceManager;
import beans.session.amdec.effet.EffetManager;
import beans.session.general.page.PageGenerator;

/**
 * Servlet implementation class DeleteEffet
 */
@WebServlet("/amdec/effet/remove/*")
public class DeleteEffet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String AMDEC = "/amdec";
	@EJB
	private EffetManager effM;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEffet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(AMDEC);
        String id = request.getPathInfo().substring( 1 );// id of element
        try
        {     	
        	effM.trouverSupprimer( Integer.parseInt(id) );
        }
        catch(Exception e)
        {
        	System.out.println("inside catch");
        	HttpSession session = request.getSession();
        	boolean exemple = true ;
        	session.setAttribute( "exception", exemple );
        	
        }
        HttpSession session = request.getSession();    	
    	session.setAttribute( "effet", true );
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
