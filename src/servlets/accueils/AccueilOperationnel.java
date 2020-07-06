package servlets.accueils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.EtatsVehicule;
import beans.session.accueil.AcceuilOperationelManager;
import beans.session.general.page.PageGenerator;

/**
 * Servlet implementation class AccueilOperationnel
 */
@WebServlet("/Operationnel/Accueil")
public class AccueilOperationnel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	AcceuilOperationelManager gM;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilOperationnel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator( "/WEB-INF/vues/accueils/accueil.operationnel.jsp"
		        , "Accueil Operationnel" );
		
		request.setAttribute( "count_vehicule", gM.countVehiculeUn() );
		request.setAttribute( "count_vehicule_libre", gM.purcentageVehiculeUN(EtatsVehicule.LIBRE));
		request.setAttribute( "count_maintenance", gM.NbMaintenanceUN());
		request.setAttribute( "count_conducteur_libre", gM.purcentageDriverUN());
		request.setAttribute( "etats_vehicule", getLabelsValues(gM.etatVehiculesUN(),EtatsVehicule.labels()));
		request.setAttribute( "km_modeles", getLabelsValues(gM.kmModeleUN(),null));
		request.setAttribute( "nb_pannes", getLabelsValues(gM.nbPanneModeleUN(),null));
		pg.generate( getServletContext(), request, response );
		
	}
	
	private Map<String,List<Object>> getLabelsValues(List<?> table,String[] enumLabels){
	    Map<String,List<Object>> out = new HashMap<String, List<Object>>();
	    List<Object> labels = new ArrayList<Object>();
	    List<Object> values = new ArrayList<Object>();
	    
	    for ( Object[] o : (List<Object[]>)table ) {
	        if(enumLabels!=null) {
	            labels.add((enumLabels[(int) o[0]] ));
	        }else
	            labels.add( (String)o[0] );
	        values.add( o[1]);
        }
	    
	    out.put( "labels", labels );
	    out.put("values",values);
	    
	    return out;
	}

	

}
