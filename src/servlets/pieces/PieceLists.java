package servlets.pieces;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.pieces.Piece;
import beans.session.general.PageGenerator;
import beans.session.pieces.PieceManager;
import beans.session.vehicules.marques.MarqueManager;

/**
 * Servlet implementation class PieceLists
 */
@WebServlet("/pieces")
public class PieceLists extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LIST = "/WEB-INF/vues/piece/listOfPieces.jsp";
	@EJB
	private PieceManager em;
    @EJB
    private MarqueManager markManager;
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
		PageGenerator pg = new PageGenerator(LIST , "Liste des pieces");
		
		request.setAttribute( "pieces", em.lister());
		request.setAttribute( "marques", markManager.lister());
		request.setAttribute( "fields", Piece.class.getDeclaredFields());
		pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filter = request.getParameter("mark");
		PageGenerator pg = new PageGenerator(LIST , "Liste des pieces");
		Map<String,Object> fields = new HashMap();
		if(request.getParameter("filter")!= null)
		{
			System.out.println("cas de filtrer");
			if(!filter.contentEquals("Tous les marques"))
			{
			fields.put("mark.titre", filter);	
			System.out.println(filter);
			request.setAttribute( "pieces", em.lister(fields));
			request.setAttribute( "marques", markManager.lister());
			request.setAttribute( "selectedMark", filter);
			request.setAttribute( "fields", Piece.class.getDeclaredFields());
			}
			else
			{
			request.setAttribute( "pieces", em.lister());
			
			}
		
			
		}
		else if (request.getParameter("search")!= null)
		{
			System.out.println("cas de recherche");
			String search = request.getParameter("word");
			String by = request.getParameter("type");
			fields.put(by, search);	
			request.setAttribute( "pieces", em.searchby(fields));
			request.setAttribute("by", by);
			request.setAttribute("wordf", search);
		}
		request.setAttribute( "marques", markManager.lister());
		pg.generate( getServletContext(), request, response );
		}

}
