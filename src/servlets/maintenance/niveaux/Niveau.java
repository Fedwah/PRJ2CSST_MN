package servlets.maintenance.niveaux;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.pieces.Piece;
import beans.session.general.PageGenerator;

/**
 * Servlet implementation class Niveau
 */
@WebServlet("/maintenace/niveaux")
public class Niveau extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NIVEAU = "/WEB-INF/vues/maintenance/niveau/niveauForm.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Niveau() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(NIVEAU , "Niveaux de maintenace");		
		pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
