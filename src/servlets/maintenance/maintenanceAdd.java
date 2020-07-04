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
import beans.entities.maintenance.niveaux.Niveau;
import beans.entities.vehicules.Vehicule;
import beans.session.amdec.instruction.InstructionManager;
import beans.session.general.page.PageGenerator;
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
	private static final String FORM = "/WEB-INF/vues/maintenance/maintenanceAdd.jsp";
	private static final String REDIRECT = "/calendrier";
	private static final String TITLE = "Maintenance";  

	@EJB
	private VehiculesManager vehM;
	@EJB
	private MaintenanceManager mm;
	@EJB
	private PieceManager pm;
	private String id ="";
	@EJB
	private InstructionManager inM;
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
		if ( request.getPathInfo() != null ) {
            id = request.getPathInfo().substring( 1 );
        }
		if(!id.equals(""))
		{
			Vehicule v = vehM.trouver(id);
			
			if(v!= null)
			{
				System.out.println(id);
				request.setAttribute("Vehicule", v);
								
			}
		}
		request.setAttribute("niveaux",Niveau.values());
		request.setAttribute("instruction", inM.lister());
		pg.generate( getServletContext(), request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator(FORM, TITLE,REDIRECT);
		MaintenanceFactory mf = new MaintenanceFactory(Maintenance.class);
		Maintenance newM = mf.create(request);
		newM.setV(vehM.trouver(newM.getV().getMatricule_interne()));
		if(request.getParameter("save")!= null)
		{
			
			if(mf.validateInsertion(newM, mm))
			{
					System.out.println("maintenace valide");
					mm.ajouter(newM);
					pg.redirect(getServletContext(), request, response);				
			}
			else
			{
				System.out.println(" maintenance non valide");
				System.out.println(mf.getErreurs());
				request.setAttribute( "erreurs", mf.getErreurs() );
				if(newM.getV() != null)
				{
					request.setAttribute("Vehicule", newM.getV());
					request.setAttribute("maintenance", newM);
					//request.setAttribute("niveaux",nManager.lister());
					Map<String,Object> fields = new HashMap();
					fields.put("modal.id",newM.getV().getModele().getId());	
					request.setAttribute("piece", pm.lister(fields));
				}
				
				pg.generate( getServletContext(), request, response );
				
			}
		}
		else if(request.getParameter("addPiece")!= null)
		{
			// ajout des pieces de rechnages 
			System.out.println(" ajout de piece de rechange");
			request.setAttribute("Vehicule", newM.getV());
			request.setAttribute("maintenance", newM);
			//request.setAttribute("niveaux",nManager.lister());
			Map<String,Object> fields = new HashMap();
			fields.put("modal.id",newM.getV().getModele().getId());	
			request.setAttribute("piece", pm.lister(fields));
			pg.generate( getServletContext(), request, response );

		}	
		
	}

}
