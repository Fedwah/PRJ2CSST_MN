package servlets.driver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.driver.Driver;
import beans.entities.pieces.Piece;
import beans.session.drivers.DriverManager;
import beans.session.general.PageGenerator;
import beans.session.pieces.PieceFactory;

/**
 * Servlet implementation class driverEdit
 */
@WebServlet("/drivers/edit/*")
public class driverEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private DriverManager dm;
	private Driver dr = null;
    private static final String Form_Driver = "/WEB-INF/vues/driver/driverForm.jsp";  
    private static final String REDIRECT = "/WEB-INF/vues/driver/driverLists.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public driverEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator( Form_Driver, "",REDIRECT);
		String id = "";
		if ( request.getPathInfo() != null ) {
            id = request.getPathInfo().substring( 1 );
        }
		if(id != "")
		{
			/*dr = (Driver) dm.trouver(id);
			
			if(p != null)//edition
			{
				request.setAttribute( "piece", p );
				request.setAttribute( "disabled_id", true );
			
			}
			else {
				System.out.println("p est null");
				request.setAttribute( "disabled_id", false );
			}*/
		}
		//request.setAttribute( "marques", mManager.lister( 0, 10 ) );
        //request.setAttribute( "modeles", modManager.lister( 0, 10 ) );		
		pg.generate( getServletContext(), request, response );
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PageGenerator pg = new PageGenerator( Form_Driver , "Driver", REDIRECT);
		//PieceFactory pf = new PieceFactory();
		String code = request.getParameter("codepiece");
		//boolean insert = false ;
		if (dr != null) // si on est entrain d'editer
		{
			/*Piece newP = pf.create(request);
			newP.setId(p.getId());
			if(pf.validate(newP))
			{
				if(em.updatePiece(p.getId(), newP)) 
				{
					pg.redirect(getServletContext(), request, response);
				}

			}
			else
			{
				// champs incorects
				System.out.println("champs non valide");//at this case only name of piece is empty
			}*/
			
		}
		else // cas d'addition
		{
			
			/*p = pf.create(request);
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
					//code dupliqué
					System.out.println("non insérée");
					System.out.println("code est " + code);
					PrintWriter out = response.getWriter();// je vais l'afficher apres en dessous de code piece
					out.println("<script>alert(\"Ce code de piece existe déjà\");</script>");
				}
			}
			else {
				// champs incorects
				System.out.println("non valide");// should get the error and show it the user

			}*/
	}
	}

}
