package servlets.pieces;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.pieces.Piece;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.session.general.PageGenerator;
import beans.session.pieces.PieceManager;
import beans.session.vehicules.marques.MarqueManager;
import beans.session.vehicules.modeles.ModeleManager;

/**
 * Servlet implementation class pieceServelet
 */
@WebServlet("/pieces/add")
public class PieceServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PieceManager em;
	@EJB
	private MarqueManager mManager;
	@EJB
	private ModeleManager modManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PieceServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PageGenerator pg = new PageGenerator( "/WEB-INF/vues/piece/addPiece.jsp", "Ajouter une nouvelle piece");
		pg.generate( getServletContext(), request, response );
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String code= request.getParameter("code");
		String nom = request.getParameter("nom");
		String mark = request.getParameter("mark");	
		Marque m = mManager.ObtenirRefrence(mark);
		String modal = request.getParameter("modal");
		Modele mod = modManager.ObtenirRefrence(modal);
		Piece p = new Piece(code,nom,m,mod);
		em.addPiece(p);
		doGet(request,response);
	}

}
