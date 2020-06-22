package servlets.maintenance.niveaux;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.session.general.page.PageGenerator;
import beans.session.maintenance.niveaux.NiveauManager;

/**
 * Servlet implementation class NiveauDelete
 */
@WebServlet("/maintenance/niveaux/remove/*")
public class NiveauDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NIVEAUX = "/maintenance/niveaux";
	@EJB
	private NiveauManager nm;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NiveauDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(NIVEAUX);
        String id = request.getPathInfo().substring( 1 );// id of element
        if ( nm.trouverSupprimer( Integer.parseInt(id) ) ) // chercher l'element et le supprimer
        {
            pg.redirect( getServletContext(), request, response );
        } 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
