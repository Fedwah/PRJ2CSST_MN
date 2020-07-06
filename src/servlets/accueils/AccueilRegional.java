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
import beans.session.accueil.AccueilRegionalManager;
import beans.session.general.GeneralManager;
import beans.session.general.page.PageGenerator;

/**
 * Servlet implementation class AccueilRegional
 */
@WebServlet( "/Regional/Accueil" )
public class AccueilRegional extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    AccueilRegionalManager          gM;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilRegional() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator( "/WEB-INF/vues/accueils/accueils.regional.jsp",
                "Accueil Operationnel" );

        request.setAttribute( "count_vehicule", gM.countVehiculeReg() );
        request.setAttribute( "count_vehicule_libre", gM.purcentageVehiculeReg( EtatsVehicule.EN_PANNE ) );
        request.setAttribute( "count_maintenance", gM.NbMaintenanceReg(  ));
        request.setAttribute( "moy_modeles", getLabelsValues( gM.moyAgeModeles(), null ) );
        request.setAttribute( "moy_unites", getLabelsValues( gM.moyAgeUnites(), null ) );
        request.setAttribute( "etats_vehicule", getLabelsValues( gM.etatVehiculesReg(), EtatsVehicule.labels() ) );
        request.setAttribute( "etas_unites", getListLabelsValues( gM.etatsUnites(), EtatsVehicule.labels() ) );
        request.setAttribute( "nb_pannes", getLabelsValues( gM.nbPanneModeleReg(), null ));
        request.setAttribute( "nb_pannes_unites", getLabelsValues( gM.nbPanneUniteReg(), null ));
        /*request.setAttribute( "km", gM.kmModele() );*/
        pg.generate( getServletContext(), request, response );
    }
    
    private Map<String,Map<String,List<Object>>>getListLabelsValues( List<?> table, String[] enumLabels ) {
        
        Map<String,Map<String,List<Object>>> out = new HashMap<String, Map<String,List<Object>>>();
        Map<String,List<Object>> data = new HashMap<String, List<Object>>();;
        List<Object> labels = new ArrayList<Object>();
       
        
       
        for ( Object[] o : (List<Object[]>)table ) {
            
     
            if(!data.containsKey( o[0] )) {
                data.put((String)o[0],new ArrayList<Object>());
            }
            
            data.get( o[0] ).add( o[2]);
            
            if(enumLabels!=null) {
                if(!labels.contains( enumLabels[(int) o[1]] ) )
                    labels.add((enumLabels[(int) o[1]] ));
            }else if (!labels.contains(  (String)o[1]))
                labels.add( (String)o[1] );
           
           
           System.out.println( "labels "+labels );
        }
        
        out.put("dataset",data);
        data = new HashMap<String, List<Object>>();
        data.put( "labels",  labels);
        out.put( "labels", data);
        
        
        return out;
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
