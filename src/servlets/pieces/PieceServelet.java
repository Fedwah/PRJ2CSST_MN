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
import beans.session.general.PageGenerator;
import beans.session.pieces.PieceFactory;
import beans.session.pieces.PieceManager;
import beans.session.vehicules.VehiculeFactory;
import beans.session.vehicules.marques.MarqueManager;
import beans.session.vehicules.marques.modeles.ModeleFactory;
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
	private static final String FORM = "/WEB-INF/vues/piece/addPiece.jsp"; 
	private static final String REDIRECT = "/pieces"; 
	private boolean edit = false;
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
		PageGenerator pg = new PageGenerator( FORM, "  ");
		String id = "";
		if ( request.getPathInfo() != null ) {
            id = request.getPathInfo().substring( 1 );
        }
		if(id != "")
		{
			p = (Piece) em.trouver(id);
			
			if(p != null) // cas d'edition
			{
				request.setAttribute( "piece", p );
				request.setAttribute( "disabled_id", true );
				edit = true;
			
			}
			else {
				System.out.println("p est null");
				request.setAttribute( "disabled_id", false );
				edit = false ;
				
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
		
		PageGenerator pg = new PageGenerator(FORM, "Piece", REDIRECT);
		PieceFactory pf = new PieceFactory();
		String code = request.getParameter("codepiece");
		// chercher le modele dans la base de données
		ModeleFactory modF = new ModeleFactory();
		Modele mod = modF.filtrer(request,modManager);
		if (edit) // si on est entrain d'editer
		{
			Piece newP = pf.create(request);
			newP.setId(p.getId());
			newP.setModal(mod);
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
				request.setAttribute("piece", newP);
				request.setAttribute( "erreurs", pf.getErreurs() );
				request.setAttribute( "disabled_id", true );
				request.setAttribute( "marques", mManager.lister( 0, 10 ) );
		        request.setAttribute( "modeles", modManager.lister( 0, 10 ) );	
		        pg.generate( getServletContext(), request, response );
			}
			
		}
		else // cas d'addition
		{
		
			p = pf.create(request);
			p.setModal(mod);
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
					//code dupliquï¿½
					System.out.println("non insï¿½rï¿½e");
					System.out.println("code est " + code);
					PrintWriter out = response.getWriter();// je vais l'afficher apres en dessous de code piece
					out.println("<script>alert(\"Ce code de piece existe dï¿½jï¿½\");</script>");
				}
			}
			else {
				// champs incorects
				
				request.setAttribute("piece", p);
				request.setAttribute( "erreurs", pf.getErreurs() );
				request.setAttribute( "marques", mManager.lister( 0, 10 ) );
		        request.setAttribute( "modeles", modManager.lister( 0, 10 ) );	
		        pg.generate( getServletContext(), request, response );

			}
			
		}

		
	}

}
