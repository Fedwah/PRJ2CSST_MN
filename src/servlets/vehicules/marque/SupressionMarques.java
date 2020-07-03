package servlets.vehicules.marque;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.session.general.page.PageGenerator;
import beans.session.vehicules.marques.MarqueManager;

/**
 * Servlet implementation class Supression
 */
@WebServlet( "/Marques/remove/*" )
public class SupressionMarques extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    MarqueManager             m;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupressionMarques() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator( "/Marques" );

        if ( m.trouverSupprimer( pg.getPathId( request ) ) ) {

            pg.redirectCurrentSuccess( getServletContext(), request, response,
                    "Suppression de " + pg.getPathId( request ),
                    "Réussie" );

        } else {
            pg.redirectCurrentError( getServletContext(), request, response,
                    "Suppression de " + pg.getPathId( request ),
                    "Le Marque ne peut pas etre supprimer car elle est utlisé." );
        }
    }

}
