package servlets.maintenance;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.session.general.page.PageGenerator;
import beans.session.maintenance.MaintenanceManager;

/**
 * Servlet implementation class MaintenanceDelete
 */
@WebServlet("/maintenance/remove/*")
public class MaintenanceDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAINTENANCES = "/WEB-INF/vues/maintenance/daylyMaintenance.jsp";
	@EJB
	private MaintenanceManager mainM;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaintenanceDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(MAINTENANCES);
        String id = request.getPathInfo().substring( 1 );// id of element
        if ( mainM.trouverSupprimer( Integer.parseInt(id) ) ) // chercher l'element et le supprimer
        {
        	pg.redirectBack(null, request, response);
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
