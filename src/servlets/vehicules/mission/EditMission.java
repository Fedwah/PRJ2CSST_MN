package servlets.vehicules.mission;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.AffectationConducteur;
import beans.entities.vehicules.Mission;
import beans.entities.vehicules.Vehicule;
import beans.session.general.PageGenerator;
import beans.session.vehicules.VehiculeFactory;
import beans.session.vehicules.VehiculesManager;
import beans.session.vehicules.affectation.AffectationConducteurManager;
import beans.session.vehicules.missions.MissionFactory;
import beans.session.vehicules.missions.MissionManager;

/**
 * Servlet implementation class CreationMission
 */
@WebServlet( "/Vehicules/Missions/*" )
public class EditMission extends HttpServlet {
    private static final long    serialVersionUID = 1L;

    @EJB
    MissionManager               miM;

    @EJB
    AffectationConducteurManager affM;
    
    @EJB
    VehiculesManager vehM;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditMission() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String[] ids = null;
        PageGenerator pg = new PageGenerator( MissionFactory.VUE_FORM, "" );
        Mission m = null;
        AffectationConducteur aff = new AffectationConducteur();
        
        MissionFactory mF = new MissionFactory();
        Boolean trouver = false;
        ids = pg.getPathIds( request );

        if ( ids != null ) {
            if ( ids.length >= 1 ) {
                aff = affM.trouver( mF.castId( ids[0] ) );
                if ( aff != null ) {

                    if ( ids.length == 2 && aff.getEndDate()==null) {
                        // Affichage
                        System.out.println( "Recherche de la mission "+mF.castId( ids[1] ) );
                        m = miM.trouver( mF.castId( ids[1] ) );
                        if ( m != null ) {
                            System.out.println( "Mission trouver" );
                            trouver = true;
                            if ( m.getAffectation().getId() == mF.castId( ids[0] ) ) {
                                pg.setPageTitle( MissionFactory.TITRE_VUE_FORM + ids[1] );
                                if(m.getDateFin() == null) {
                                    m.setDateFin( new Date() );
                                    m.setDistance_parcourue((Math.random()*1190)+10); // min = 10mk ,max 1200 km
                                }
                            }
                           
                        }
                        
                    }

                    if ( !trouver ) {
                        pg.setPageTitle( "Creation d'une mission pour l'affectation " + ids[0] );
                        m = new Mission();
                        m.setAffectation( aff );
                        m.setDateDebut( new Date() );
                          
                    }
                    m.setVehicule( aff.getCar() );
                    m.setAffectation( aff );
                }

            }else {
                System.out.println( "Erreurs dans le nombre d'ids" );
            }
        }

        if(m!=null) {
            request.setAttribute( "mission", m );
            pg.generate( getServletContext(), request, response );
        }else {
            response.getWriter().write( "Operation impossible");
        }
        
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator( MissionFactory.VUE_FORM,"");
        String[] ids = pg.getPathIds( request );
        Mission oldM = null;
        Mission newM = null;
        
        MissionFactory mF = new MissionFactory();
        VehiculeFactory vehF = new VehiculeFactory();
        AffectationConducteur aff = affM.trouver(mF.castId( ids[0] ));
        
        pg.setPageTitle( MissionFactory.TITRE_VUE_FORM+aff.getCar().getMatricule_interne());
        pg.setRedirectURL( MissionFactory.REDIRECT_URL+aff.getCar().getMatricule_interne());
        
        
        newM=mF.create( request );
         if(mF.validate( newM )){
             if(ids.length==1) {
                 //creation
                
                 if(mF.uniqueSave( miM, newM, newM.getId(), MissionFactory.PARAM_ID )) {
                     System.out.println( "Mission crée" );
                     
                 }
             }else {
                 oldM=miM.trouver( mF.castId( ids[1] ) );
                 vehF.mettreAjourKM(aff.getCar(),oldM.getDistance_parcourue(),newM.getDistance_parcourue(),vehM);
                 miM.mettreAJour( oldM.getId(), mF, newM );
             }
             pg.redirect( getServletContext(), request, response );
         }else {
             request.setAttribute("mission", newM);
             request.setAttribute( "erreurs", mF.getErreurs() );
             pg.generate( getServletContext(), request, response );
         }
     
    }

}
