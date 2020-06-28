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

import beans.session.amdec.cause.CausesManager;
import beans.session.amdec.defaillance.DefaillanceManager;
import beans.session.amdec.effet.EffetManager;
import beans.session.general.page.PageGenerator;

/**
 * Servlet implementation class DetectionAPI
 */
@WebServlet("/api/amdec/*")
public class AmdecAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   
    @EJB
    private CausesManager causeManager;
    @EJB
    private DefaillanceManager defaiManager;
    @EJB
    private EffetManager effManager;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmdecAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       PageGenerator pg = new PageGenerator();
	       String[] ids = pg.getPathIds( request );
	       
	       List<?> out = null;
	       if(ids!=null && ids.length>=1) {
	           if(ids.length==1) {
	               switch(ids[0]) {
	               case "causes":
	                       out = causeManager.lister();
	                       break;
	                   case "effets":
	                       out = effManager.lister();
	                       break;
	                   case "defaillances":
	                       out= defaiManager.lister();
	                       break;   
	                    default:
	               }
	             
	           }
	           List<JSONObject> objects = new ArrayList<JSONObject>();
	           if(out!=null) {
	               for (Object o :out) {
	                   objects.add( new JSONObject( o ) );
	               }
	           }else {
	               JSONObject obj = new JSONObject();
	               obj.put( "erreur", "operation non existente" );
	               objects.add( obj );
	           }
	          
	           pg.generateJSON( response, objects );
            }
	           
	       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
