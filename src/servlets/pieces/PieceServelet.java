package servlets.pieces;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.pieces.Piece;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.entities.vehicules.Vehicule;
import beans.session.general.page.PageGenerator;
import beans.session.pieces.PieceFactory;
import beans.session.pieces.PieceManager;
import beans.session.vehicules.VehiculeFactory;
import beans.session.vehicules.marques.MarqueManager;
import beans.session.vehicules.marques.modeles.ModeleFactory;
import beans.session.vehicules.marques.modeles.ModeleManager;

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
	private static final String FORM = "/WEB-INF/vues/piece/addPiece.jsp"; 
	private static final String REDIRECT = "/pieces"; 
	private boolean edit = false;
	private Piece oldp = null;
       
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
		PageGenerator pg = new PageGenerator( FORM, "Piece");
		String id = "";
		if ( request.getPathInfo() != null ) {
            id = request.getPathInfo().substring( 1 );          
        }
		if(id!="")
		{
			oldp = em.trouver(id);
			if(oldp!=null)
			{
				edit=true;
				request.setAttribute("piece", oldp);
				request.setAttribute("disabled_id",true);
			}
		}
		request.setAttribute( "marques", mManager.lister() );
        request.setAttribute( "modeles", modManager.lister() );	 
		pg.generate( getServletContext(), request, response );
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PageGenerator pg = new PageGenerator(FORM, "Piece", REDIRECT);
		PieceFactory pf = new PieceFactory(Piece.class);
		Piece p =  pf.create(request);
		if(pf.validate(p, em, p.getRefrence()))
		{
			em.ajouter(p);
			pg.redirect(getServletContext(), request, response);
		}
		else
		{
			request.setAttribute( "marques", mManager.lister() );
	        request.setAttribute( "modeles", modManager.lister() );
	        request.setAttribute("erreurs", pf.getErreurs());
			pg.generate( getServletContext(), request, response );
		}
	}

}
