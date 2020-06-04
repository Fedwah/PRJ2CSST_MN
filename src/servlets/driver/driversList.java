package servlets.driver;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.driver.Driver;
import beans.entities.vehicules.Vehicule;
import beans.session.drivers.DriverManager;
import beans.session.general.PageGenerator;
import beans.session.vehicules.VehiculeFactory;

/**
 * Servlet implementation class driversList
 */
@WebServlet("/drivers")
public class driversList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String driverVue = "/WEB-INF/vues/driver/driverLists.jsp";
	@EJB
	private DriverManager dm;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public driversList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(driverVue , "Liste des conducteurs" );
		request.setAttribute( "drivers", dm.lister( 0, 10 ));
		request.setAttribute( "fields", Driver.class.getDeclaredFields());
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
