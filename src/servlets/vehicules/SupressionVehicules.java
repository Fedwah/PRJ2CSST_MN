package servlets.vehicules;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.session.general.page.PageGenerator;
import beans.session.general.page.pather.PageState;
import beans.session.vehicules.VehiculesManager;

/**
 * Servlet implementation class SupressionVehicules
 */
@WebServlet( "/Vehicules/remove/*" )
public class SupressionVehicules extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private VehiculesManager  vm;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupressionVehicules() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        PageGenerator pg = new PageGenerator();
        String id = request.getPathInfo().substring( 1 );
        if (vm.trouverSupprimer(id ) ) {

            pg.redirectCurrentSuccess( getServletContext(), request, response,
                   "Suppression de "+pg.getPathId( request ),
                    "Reussie");
        } else {
            pg.redirectCurrentError( getServletContext(), request, response,
                    "Ce Vehicule  est utilis�",
                    "Vous ne pouvez pas le supprimer");
        }
    }

   
}
