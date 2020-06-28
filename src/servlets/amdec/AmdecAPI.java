package servlets.amdec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.keyinfo.PGPData;

import org.json.JSONObject;

import beans.entities.amdec.Cause;
import beans.entities.amdec.Defaillance;
import beans.entities.amdec.Effet;
import beans.session.amdec.cause.CauseFactory;
import beans.session.amdec.cause.CausesManager;
import beans.session.amdec.defaillance.DefaillanceFactory;
import beans.session.amdec.defaillance.DefaillanceManager;
import beans.session.amdec.effet.EffetFactory;
import beans.session.amdec.effet.EffetManager;
import beans.session.general.page.PageGenerator;

/**
 * Servlet implementation class DetectionAPI
 */
@WebServlet( "/api/amdec/*" )
public class AmdecAPI extends HttpServlet {
    private static final long  serialVersionUID = 1L;

    @EJB
    private CausesManager      causeManager;
    @EJB
    private DefaillanceManager defaiManager;
    @EJB
    private EffetManager       effManager;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmdecAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator();
        String[] ids = pg.getPathIds( request );

        CauseFactory cF = new CauseFactory();
        EffetFactory eF = new EffetFactory();
        DefaillanceFactory dF = new DefaillanceFactory();
        
        Cause c = null;
        Effet e = null;
        Defaillance d = null;
        
        List<?> out = null;
        
        
        if ( ids != null && ids.length >= 1 ) {
            switch ( ids[0] ) {
            case "causes":
                if(ids.length==2) { //ajouter
                    c = cF.createValidateAjouter( request,new Cause( ids[1] ),causeManager );
                    if(c == null) {
                        
                       pg.generateJSON( response, false, "Echec de l'operation "+cF.getErreurs());
                         
                    }
                }else { //lister
                    out = causeManager.lister();
                }
                
                break;
            case "effets":
                if(ids.length==2) { //ajouter
                    e = eF.createValidateAjouter( request,new Effet( ids[1] ),effManager );
                    if(e==null) {
                        pg.generateJSON( response, false, "Echec de l'operation"+eF.getErreurs());
                     }
                }else { //lister
                    out = effManager.lister();
                }
        
                break;
            case "defaillances":
                if(ids.length==2) { //ajouter
                    d = dF.createValidateAjouter( request, new Defaillance( ids[1] ),defaiManager );
                    if(d==null) {
                        pg.generateJSON( response, false, "Echec de l'operation"+dF.getErreurs());
                     }
                }else { //lister
                    out = defaiManager.lister();
                }
               
                break;
            default:
            }
            
            if(out!=null) {
                List<JSONObject> objects = new ArrayList<JSONObject>();
                for ( Object o : out ) {
                    objects.add( new JSONObject( o ) );
                }
                pg.generateJSON( response, objects );
            }else if(e!=null || d!=null || c!=null) {   
               pg.generateJSON( response, true, "Ajout reussie" );
            }

        }else {
           pg.generateJSON( response, false, "Operation non existente , essayé avec : api/amdec/causes,api/amdec/effets,api/amdec/defaillances pour les lister"
                   + " et api/amdec/causes/{titre} ,api/amdec/effets/{titre},api/amdec/defaillances/{titre} pour ajouter" ); 
        }
        
    

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet( request, response );
    }

}
