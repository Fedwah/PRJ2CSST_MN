package servlets.vehicules.affectation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.AffectationConducteur;
import beans.entities.vehicules.Mission;
import beans.entities.vehicules.Vehicule;
import beans.session.drivers.DriverManager;
import beans.session.general.PageGenerator;
import beans.session.vehicules.VehiculesManager;
import beans.session.vehicules.affectation.AffectationConducteurFactory;
import beans.session.vehicules.affectation.AffectationConducteurManager;
import beans.session.vehicules.missions.MissionFactory;
import beans.session.vehicules.missions.MissionManager;

/**
 * Servlet implementation class AffectationVehicule
 */

@WebServlet( "/Vehicules/Affectations/*" )
public class ListerAffectationVehicule extends HttpServlet {

    private static final long    serialVersionUID = 1L;

    @EJB
    AffectationConducteurManager affM;

    
    @EJB
    MissionManager miM;

    @EJB
    DriverManager                conM;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerAffectationVehicule() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator( AffectationConducteurFactory.VUE_LIST,AffectationConducteurFactory.TITRE_VUE_LIST );
        
        AffectationConducteurFactory affF = new AffectationConducteurFactory();
        MissionFactory miF = new MissionFactory();
        
        AffectationConducteur actuelle = null;
        List<AffectationConducteur> affectations = null;
        List<Mission> missions = null;
        String id = pg.getPathId( request );
       
        pg.setPageTitle( AffectationConducteurFactory.TITRE_VUE_LIST + id );

        affF.addFiltre( "car", "num_immatriculation", id );
        
        affectations = affM.lister( affF.getFiltres());
        
        actuelle = (affectations.size()>0?affectations.get( affectations.size()-1 ):null);
        
        if(actuelle!=null) {
            if(actuelle.getEndDate()!=null) {
                actuelle =null;
            }else{
                miF.addFiltre( "affectation","id",actuelle.getId());
                missions=miM.lister( miF.getFiltres() ) ;
            }
            
        }
        
        
        Collections.reverse( affectations );
        request.setAttribute( "vehicule", id );
        request.setAttribute( "affectations", affectations );
        request.setAttribute( "actuelle", actuelle );
        request.setAttribute( "drivers", conM.lister() );
        request.setAttribute( "missions",missions);
        pg.generate( getServletContext(), request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator( AffectationConducteurFactory.VUE_LIST,
                AffectationConducteurFactory.TITRE_VUE_LIST );
        
        AffectationConducteurFactory affF = new AffectationConducteurFactory();
       
        
        AffectationConducteur newAffecter = null;
        
        List<AffectationConducteur> affectations = null;
        String id = pg.getPathId( request );
       

        pg.setPageTitle( AffectationConducteurFactory.TITRE_VUE_LIST + id );

        affF.addFiltre( "car", "num_immatriculation", id );
        
        affectations = affM.lister( affF.getFiltres() );
        
      
        
        newAffecter = affF.affecter(request, affM, affectations);
        
        
       
    
        Collections.reverse( affectations );
        
        request.setAttribute( "vehicule", id );
        request.setAttribute( "affectations", affectations );
        request.setAttribute( "actuelle", newAffecter );
        request.setAttribute( "drivers", conM.lister() );
      
        pg.generate( getServletContext(), request, response );
        
    }
    
  

}
