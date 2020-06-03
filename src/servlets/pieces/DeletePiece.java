package servlets.pieces;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.mail.Message;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.pieces.Piece;
import beans.session.general.PageGenerator;
import beans.session.pieces.PieceManager;

/**
 * Servlet implementation class DeletePiece
 */
@WebServlet("/pieces/remove/*")
public class DeletePiece extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PieceManager pm = new PieceManager();
	private static final String Piece_Vue = "/pieces";
	private String id;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePiece() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(Piece_Vue);
        id = request.getPathInfo().substring( 1 );// id of element
        if ( pm.trouverSupprimer( id ) ) // chercher l'element et le supprimer
        {
            pg.redirect( getServletContext(), request, response );
        } 
        else //echec de suppression
        {
            response.getWriter().append( "Erreur: La suppresion de la piece n∞"+id+" a echou√©" );
        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

}
