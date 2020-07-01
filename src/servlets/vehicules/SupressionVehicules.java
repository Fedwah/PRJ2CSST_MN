package servlets.vehicules;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.session.general.page.PageGenerator;
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
        PageGenerator pg = new PageGenerator( "/Vehicules" );
        if (vm.trouverSupprimer( pg.getPathId( request ) ) ) {

            pg.redirectBackSuccess( getServletContext(), request, response,
                    "Suppression de "+pg.getPathId( request ),"Reussie");
        } else {
            pg.redirectBackErreur( getServletContext(), request, response,
                    "Ce Vehicule  est utlisé",
                    "Vous ne pouvez pas la supprimer" );
        }
    }

   
}
