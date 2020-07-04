package servlets.vehicules;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.Vehicule;
import beans.session.general.page.PageGenerator;
import beans.session.vehicules.VehiculeFactory;
import beans.session.vehicules.VehiculesManager;
import beans.session.vehicules.marques.MarqueManager;

/**
 * Servlet implementation class ValidationVehicules
 */
@WebServlet( "/Vehicules" )
public class ListerVehicules extends HttpServlet {

    private static final String ATT_FILTRES = "filtres";
    private static final String ATT_FIELD          = "field";
    private static final String ATT_SEARCH         = "search";
    private static final String ATT_FILTRE_MARQUES = "filtre_marques";
    private static final String ATT_FIELDS         = "fields";
    private static final String ATT_VEHICULES      = "vehicules";
    private static final String TITRE_VUE          = "La Liste des vehicules";
    private static final long   serialVersionUID   = 1L;

    @EJB
    private VehiculesManager    vm;

    @EJB
    private MarqueManager       marM;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerVehicules() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator( VehiculeFactory.VUE_LIST, TITRE_VUE );

        VehiculeFactory vf = new VehiculeFactory( Vehicule.class );

        
        request.setAttribute( ATT_VEHICULES, vm.lister() );
        request.setAttribute( ATT_FIELDS, vf.getEntityFields().labels() );
        request.setAttribute( ATT_FILTRES, vf.getNamesToFilter() );
        request.setAttribute( ATT_FILTRE_MARQUES, marM.lister() );
        pg.generate( getServletContext(), request, response);
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        PageGenerator pg = new PageGenerator( VehiculeFactory.VUE_LIST, TITRE_VUE );

        String search = request.getParameter( ATT_SEARCH );
        String field = request.getParameter( ATT_FIELD );

        VehiculeFactory vf = new VehiculeFactory( Vehicule.class );

        if ( !search.isEmpty() ) {

            if ( vf.getEntityFields().fields().get( field ).isBasicClass ) {
                vf.addFiltre( field, search );

            } else if(!field.equals("unite")){
                vf.addFiltre( field, "titre", search );
            } else {
                vf.addFiltre( field, "codeUN", search );
            }

        }

        System.out.println( "Les filtres : " + vf.getFiltres().toString() );

        request.setAttribute( ATT_VEHICULES, vm.searchby( vf.getFiltres() ) );
        request.setAttribute( ATT_FIELDS, vf.getEntityFields().labels() );
        request.setAttribute( ATT_FILTRES, vf.getNamesToFilter() );
        request.setAttribute( ATT_SEARCH, search );
        request.setAttribute( ATT_FIELD, field );
        request.setAttribute( ATT_FILTRE_MARQUES, marM.lister() );
        
        
        pg.generate( getServletContext(), request, response);

    }

}
