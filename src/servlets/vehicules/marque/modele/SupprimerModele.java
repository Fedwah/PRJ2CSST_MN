package servlets.vehicules.marque.modele;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.session.general.page.PageGenerator;
import beans.session.vehicules.marques.modeles.ModeleFactory;
import beans.session.vehicules.marques.modeles.ModeleManager;

/**
 * Servlet implementation class SupprimerModele
 */
@WebServlet( "/Marques/Modeles/remove/*" )
public class SupprimerModele extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    ModeleManager             modM;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerModele() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator( ModeleFactory.DEFAULT_REDIRECT_URL );
        
        if ( modM.trouverSupprimer( pg.getPathId( request ) ) ) {

            pg.redirectCurrentSuccess( getServletContext(), request, response,
                    "Suppression de "+pg.getPathId( request ),"Reussie");
        } else {
            pg.redirectCurrentError( getServletContext(), request, response,
                    "Ce modele est liée à certains véhicules",
                    "Vous ne pouvez pas la supprimer" );
        }

    }

}
