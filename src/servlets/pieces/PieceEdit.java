package servlets.pieces;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.pieces.Piece;
import beans.session.general.page.PageGenerator;
import beans.session.pieces.PieceFactory;
import beans.session.pieces.PieceManager;
import beans.session.vehicules.marques.MarqueManager;
import beans.session.vehicules.marques.modeles.ModeleManager;

/**
 * Servlet implementation class PieceEdit
 */
@WebServlet("/pieces/edit/*")
public class PieceEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FORM = "/WEB-INF/vues/piece/EditPiece.jsp"; 
	private static final String REDIRECT = "/pieces"; 
	private Piece oldp = null;
	@EJB
	private PieceManager em;
	@EJB
	private MarqueManager mManager;
	@EJB
	private ModeleManager modManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PieceEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.context(request, response, true);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("init")!=null)
		{
			this.context(request, response, false);
		}
		else if (request.getParameter("send")!=null)
		{
			PageGenerator pg = new PageGenerator( FORM, "Piece","/pieces");
			PieceFactory pf= new PieceFactory(Piece.class);
			Piece newP = pf.create(request);
			newP.setRefrence(oldp.getRefrence());
			if(pf.validate(newP))
			{
				em.mettreAJour(newP.getRefrence(), pf, newP);
				pg.redirect(getServletContext(), request, response);
			}
			else
			{
				
				request.setAttribute("piece", newP);				 
				request.setAttribute("disabled_id",true);				
				request.setAttribute("tai", newP.getModals().size());			
				request.setAttribute("erreurs", pf.getErreurs());
				request.setAttribute( "marques", mManager.lister() );
		        request.setAttribute( "modeles", modManager.lister() );	 
				pg.generate( getServletContext(), request, response );
	
			}
		}
		
	}
	
	private void context(HttpServletRequest request, HttpServletResponse response, boolean doget) throws ServletException, IOException
	{
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
				request.setAttribute("piece", oldp);
				 
				request.setAttribute("disabled_id",true);
				if(doget)
				request.setAttribute("tai", oldp.getModals().size());
				else request.setAttribute("tai", 1);
					
			}
		}
		request.setAttribute( "marques", mManager.lister() );
        request.setAttribute( "modeles", modManager.lister() );	 
		pg.generate( getServletContext(), request, response );
	}

}
