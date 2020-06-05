package servlets.pieces;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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
import beans.session.pieces.PieceFactory;
import beans.session.pieces.PieceManager;
import beans.session.vehicules.VehiculeFactory;
import beans.session.vehicules.marques.MarqueManager;
import beans.session.vehicules.marques.modeles.ModeleManager;

/**
 * Servlet implementation class pieceServelet
 */
@WebServlet("/pieces/edit/*")
public class PieceServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PieceManager em;
	@EJB
	private MarqueManager mManager;
	@EJB
	private ModeleManager modManager;
	private static final String Piece_Vue = "/WEB-INF/vues/piece/addPiece.jsp"; 
	private static final String REDIRECT = "/pieces"; 
	private Piece p = null;
       
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
		PageGenerator pg = new PageGenerator( Piece_Vue, "  ");
		String id = "";
		if ( request.getPathInfo() != null ) {
            id = request.getPathInfo().substring( 1 );
        }
		if(id != "")
		{
			p = (Piece) em.trouver(id);
			
			if(p != null)
			{
				request.setAttribute( "piece", p );
				request.setAttribute( "disabled_id", true );
			
			}
			else {
				System.out.println("p est null");
				request.setAttribute( "disabled_id", false );
			}
		}
		request.setAttribute( "marques", mManager.lister( 0, 10 ) );
        request.setAttribute( "modeles", modManager.lister( 0, 10 ) );		
		pg.generate( getServletContext(), request, response );
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PageGenerator pg = new PageGenerator(Piece_Vue, "Piece", REDIRECT);
		PieceFactory pf = new PieceFactory();
		String code = request.getParameter("codepiece");
		boolean insert = false ;
		if (p!= null) // si on est entrain d'editer
		{
			Piece newP = pf.create(request);
			newP.setId(p.getId());
			if(pf.validate(newP))
			{
				
				
				if(em.mettreAJour(p.getId(),pf, newP)) 
				{
					pg.redirect(getServletContext(), request, response);
				}

			}
			else
			{
				// champs incorects
				System.out.println("champs non valide");//at this case only name of piece is empty
			}
			
		}
		else // cas d'addition
		{
			
			p = pf.create(request);
			if(pf.validate(p))
			{
				// insertion dans la bdd
				System.out.println("valide");
				if(em.ajouterUnique(p,code)) 
				{
					pg.redirect( getServletContext(), request, response );
				}
				else 
				{
					//code dupliqu�
					System.out.println("non ins�r�e");
					System.out.println("code est " + code);
					PrintWriter out = response.getWriter();// je vais l'afficher apres en dessous de code piece
					out.println("<script>alert(\"Ce code de piece existe d�j�\");</script>");
				}
			}
			else {
				// champs incorects
				System.out.println("non valide");// should get the error and show it the user

			}
			
		}

		
	}

}
