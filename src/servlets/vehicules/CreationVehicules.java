package servlets.vehicules;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.session.general.PageGenerator;
import beans.session.vehicules.VehiculesManager;

/**
 * Servlet implementation class CreationVehicules
 */
@WebServlet("/Vehicules/form")
public class CreationVehicules extends HttpServlet {
    private static final String PARENT = "/WEB-INF/index.jsp";
    private static final String VUE = "/WEB-INF/vues/vehicules/vehicules.form.jsp";
    private static final String TITRE_VUE= "Creation d'un vehicule";

    private static final long serialVersionUID = 1L;
    
    @EJB
    private VehiculesManager vm;
    /**
     * Default constructor. 
     */
    public CreationVehicules() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(PARENT,VUE,TITRE_VUE);
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
