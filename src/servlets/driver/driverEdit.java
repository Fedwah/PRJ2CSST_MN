package servlets.driver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.driver.Driver;
import beans.entities.pieces.Piece;
import beans.session.drivers.DriverFactory;
import beans.session.drivers.DriverManager;
import beans.session.general.PageGenerator;
import beans.session.pieces.PieceFactory;
import beans.session.regions.unites.UniteManager;

/**
 * Servlet implementation class driverEdit
 */
@WebServlet("/drivers/edit/*")
@MultipartConfig( maxFileSize = 16177215 ) // upload file's size up to 16MB
public class driverEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private DriverManager dm;
	@EJB 
	private UniteManager um;
	private Driver dr = null;
    private static final String Form_Driver = "/WEB-INF/vues/driver/driverForm.jsp";  
    private static final String REDIRECT = "/drivers";
    private boolean edit = false;
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
		String id= "";
		if ( request.getPathInfo() != null ) {
			System.out.println("path info not null");
            id = request.getPathInfo().substring( 1 );
            edit = false;
        }
		if(id.length() != 0 )
		{
			edit =true;
			System.out.println("cas d'edition, id est de longeur"+ id.length());
			dr = (Driver) dm.trouver(Integer.parseInt(id));
			
			if(dr != null)//edition
			{
				request.setAttribute( "driver", dr );
			
			}

		}
		pg.generate( getServletContext(), request, response );
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PageGenerator pg = new PageGenerator( Form_Driver , "Driver", REDIRECT);
		DriverFactory df = new DriverFactory();
		if (edit) // si on est entrain d'editer
		{
			System.out.println("edition");
			Driver newD = (Driver) df.create(request);
			if(df.validate(newD))
			{
				// si les champs simple sans valide --> mettre à jour les modifications
				if(dm.mettreAJour(dr.getIDdriver(), df , newD))
				{
					System.out.println("champs valide");
					pg.redirect(getServletContext(), request, response);
				}

			}
			else
			{
				// champs incorects
				request.setAttribute("driver", newD);
				request.setAttribute( "erreurs", df.getErreurs() );
		        pg.generate( getServletContext(), request, response );

			}
			
		}
		else // cas d'addition
		{
			System.out.println("ajouter un nouveau conducteur");
			dr = (Driver) df.create(request,um);
			if(df.validate(dr))
			{
				// insertion dans la bdd
				System.out.println("valide");
				if(dm.ajouter(dr)) 
				{
					pg.redirect( getServletContext(), request, response );
				}

			}
			else {
				
				request.setAttribute("driver", dr);
				request.setAttribute( "erreurs", df.getErreurs() );
		        pg.generate( getServletContext(), request, response );

			}
	}
	}

}
