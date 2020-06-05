package servlets.pieces;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.pieces.Piece;
import beans.session.general.PageGenerator;
import beans.session.pieces.PieceManager;

/**
 * Servlet implementation class PieceLists
 */
@WebServlet("/pieces")
public class PieceLists extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PieceManager em;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PieceLists() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PageGenerator pg = new PageGenerator( "/WEB-INF/vues/piece/listOfPieces.jsp", "Liste des pieces");
		
		request.setAttribute( "pieces", em.lister());
		request.setAttribute( "fields", Piece.class.getDeclaredFields());
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
