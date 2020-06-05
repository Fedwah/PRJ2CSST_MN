package servlets.vehicules;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.jboss.resteasy.util.InputStreamToByteArray;

import beans.entities.pieces.Piece;
import beans.entities.vehicules.EtatVehicule;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanValidator;
import beans.session.general.PageGenerator;
import beans.session.vehicules.VehiculeFactory;
import beans.session.vehicules.VehiculesManager;
import beans.session.vehicules.marques.MarqueManager;




/**
 * Servlet implementation class ValidationVehicules
 */
@WebServlet("/Vehicules")
public class ListerVehicules extends HttpServlet {
  
    
    private static final String ATT_FILTRE_MARQUES = "filtre_marques";
    private static final String ATT_FIELDS = "fields";
    private static final String ATT_VEHICULES = "vehicules";
    private static final String TITRE_VUE        = "La Liste des vehicules";
	private static final long serialVersionUID = 1L;
       
	@EJB
	private VehiculesManager vm;
	
	@EJB
	private MarqueManager marM;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerVehicules() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator( VehiculeFactory.VUE_LIST, TITRE_VUE );
		
	    VehiculeFactory vf = new VehiculeFactory();
        vf.getEntityFields().generateFields( Vehicule.class );
        System.out.println("les fields : "+vf.getEntityFields().filedsLabels().toString());
        
		request.setAttribute( ATT_VEHICULES, vm.lister());
		request.setAttribute( ATT_FIELDS, VehiculeFactory.FIELDS);
		request.setAttribute( ATT_FILTRE_MARQUES,marM.lister());
		pg.generate( getServletContext(), request, response );
	}

	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	    PageGenerator pg = new PageGenerator( VehiculeFactory.VUE_LIST, TITRE_VUE );
        
	    String search = request.getParameter( "search" );
	    String field = request.getParameter( "field" );
	    
	    VehiculeFactory vf = new VehiculeFactory();
	    vf.getEntityFields().generateFields( Vehicule.class );
        System.out.println("les fields : "+vf.getEntityFields());
	    request.setAttribute( ATT_VEHICULES, vm.lister());
        request.setAttribute( ATT_FIELDS, VehiculeFactory.FIELDS);
        request.setAttribute( ATT_FILTRE_MARQUES,marM.lister());
        pg.generate( getServletContext(), request, response );
	
	}
	
	

}
