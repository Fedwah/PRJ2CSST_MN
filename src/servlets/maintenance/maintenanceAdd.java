package servlets.maintenance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.maintenance.Maintenance;
import beans.entities.vehicules.Vehicule;
import beans.session.general.PageGenerator;
import beans.session.maintenance.MaintenanceFactory;
import beans.session.maintenance.MaintenanceManager;
import beans.session.pieces.PieceManager;
import beans.session.vehicules.VehiculesManager;

/**
 * Servlet implementation class maintenanceEdit
 */
@WebServlet("/maintenance/add/*")
public class maintenanceAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FORM = "/WEB-INF/vues/maintenance/maintenanceForm.jsp";
	private static final String REDIRECT = "/WEB-INF/vues/maintenance/calender.jsp";
	private static final String TITLE = "Maintenance";  
	@EJB
	private VehiculesManager vehM;
	@EJB
	private MaintenanceManager mm;
	@EJB
	private PieceManager pm;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public maintenanceAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator( FORM, TITLE);
		String id = "";
		if ( request.getPathInfo() != null ) {
            id = request.getPathInfo().substring( 1 );
        }
		if(!id.equals(""))
		{
			Vehicule v = vehM.trouver(id);
			if(v!= null)
			{
				request.setAttribute("vehicule", v);
				Map<String,Object> fields = new HashMap();
				fields.put("mark.titre",v.getMarque());
				fields.put("modal.titre",v.getMarque());
				request.setAttribute("piece", pm.lister(fields));
				
			}
		}
		pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator( FORM, TITLE,REDIRECT);
		MaintenanceFactory mf = new MaintenanceFactory();
		Maintenance newM = mf.create(request, vehM);
		if(mf.validate(newM))
		{
			
				mm.ajouter(newM);
				pg.redirect(getServletContext(), request, response);
			
		}
		else
		{
			
		}
	}

}
