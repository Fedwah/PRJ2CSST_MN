package servlets.vehicules;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.maintenance.Maintenance;
import beans.entities.vehicules.AffectationConducteur;
import beans.entities.vehicules.Mission;
import beans.entities.vehicules.Vehicule;
import beans.session.drivers.DriverManager;
import beans.session.general.page.PageGenerator;
import beans.session.maintenance.CalendarFactory;
import beans.session.maintenance.MaintenanceFactory;
import beans.session.maintenance.MaintenanceManager;
import beans.session.vehicules.VehiculeFactory;
import beans.session.vehicules.VehiculesManager;
import beans.session.vehicules.affectation.AffectationConducteurFactory;
import beans.session.vehicules.affectation.AffectationConducteurManager;
import beans.session.vehicules.missions.MissionFactory;
import beans.session.vehicules.missions.MissionManager;

/**
 * Servlet implementation class DetailVehicule
 */
@WebServlet("/Vehicules/*")
public class DetailVehicule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@EJB
	private VehiculesManager vehM;
	

    @EJB
    AffectationConducteurManager affM;

    
    @EJB
    MissionManager miM;

    @EJB
    DriverManager conM;
    
    @EJB
    private MaintenanceManager mainM;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailVehicule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator( VehiculeFactory.VUE_DETAIL, "" );
		//String id = (String)pg.getPathId( request );
		String id = request.getPathInfo().substring( 1 );
		Vehicule v = null;
		AffectationConducteur aff = null;
		Mission m = null;
		List<AffectationConducteur> affections= null;
		List<Mission> missions =null;
		List<Maintenance> maintenances = null;
		
		AffectationConducteurFactory affF = new AffectationConducteurFactory();
		MissionFactory mF = new MissionFactory();
		MaintenanceFactory mainF = new MaintenanceFactory();
		
		if(id!=null) {
		    pg.setPageTitle( "Detail du vehicule "+id );
		    v = vehM.trouver( id );
		    if(v!=null) {
		        affF.addFiltre( "car", "matricule_interne", id );
		        affections = affM.lister( affF.getFiltres() );
		        if(affections.size()>0) {
		            aff = affections.get( affections.size()-1 );
		            
		            //mF.addFiltre( "affectation", "id", aff.getId());
		            mF.addFiltre( "vehicule","matricule_interne",id);
		            missions = miM.lister( mF.getFiltres() );
		                  
		                   
		            if(missions.size()>0) {
		                m = missions.get( missions.size()-1 );
		                m = (m.getDateFin()!=null?null:m);
		                Collections.reverse( missions );
		            }
		            
		            aff = (aff.getEndDate()!=null?null:aff);    
		            Collections.reverse( affections );
		            
		            
		            
		       }
		       
		     // trouver l'historique des maintenances 
	            mainF.addFiltre( "v","matricule_interne",id);
	            maintenances = mainM.lister( mainF.getFiltres() );
	            System.out.println("taille de la liste des maintenaces est " + maintenances.size());
		        
		    }
		}
		
	
		
		request.setAttribute( "vehicule", v );
		request.setAttribute( "missions", missions );
		request.setAttribute( "affectations", affections );
		request.setAttribute( "mission", m );
		request.setAttribute( "affectation", aff );
		/********* pour le volet de maintenace ******/
        CalendarFactory cf = new CalendarFactory();
        request.setAttribute( "cal", cf);
		request.setAttribute("main",maintenances );
		/**************************************/
		pg.generate( getServletContext(), request, response );
	}

	
	

}
