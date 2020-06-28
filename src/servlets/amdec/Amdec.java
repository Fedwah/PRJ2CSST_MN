package servlets.amdec;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Servlet implementation class Amdec
 */
@WebServlet( "/amdec" )
public class Amdec extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String AMDEC = "/WEB-INF/vues/amdec/amdec.jsp";  
	
	@EJB
	private CausesManager causeManager;
	@EJB
	private DefaillanceManager defaiManager;
	@EJB
	private EffetManager effManager;
    


    /**
     * @see HttpServlet#HttpServlet()
     */
    public Amdec() {
        super();
        // TODO Auto-generated constructor stub
    }



    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator( AMDEC, "Analyse AMDEC" );
        request.setAttribute( "causes", causeManager.lister() );
        request.setAttribute( "effets", effManager.lister() );
        request.setAttribute( "defai", defaiManager.lister() );
        pg.generate( getServletContext(), request, response );
    }



    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator( AMDEC, "Analyse AMDEC" );
        String active = ""; //Pour garder le dernier tab selectionner
        
        // volet cause
        if ( request.getParameter( "cause" ) != null ) {
            System.out.println( "post from cause" );
            active="cause";
            CauseFactory cf = new CauseFactory();
            Cause c = cf.create( request );

            // j'ai depalacer le uniqueField au validateChilds
            if ( cf.validate( c, causeManager ) ) {
                causeManager.ajouter( c );

            } else {
                request.setAttribute( "erreurs", cf.getErreurs() );

            }
        }
        // volet effet
        if ( request.getParameter( "eff" ) != null ) {
            System.out.println( "post from effet" );
            active="effet";
            EffetFactory ef = new EffetFactory();
            Effet e = ef.create( request );
            
            if ( ef.validate( e, effManager ) ) {
                effManager.ajouter( e );

            } else {
                System.out.println( "not valide effets" );
                System.out.println( "errors "+ef.getErreurs() );
                request.setAttribute( "erreurs", ef.getErreurs() );

            }
        }
        // volet defaillance
        if ( request.getParameter( "def" ) != null ) {
            System.out.println( "post from defaillance" );
            active="defaillance";    
            DefaillanceFactory df = new DefaillanceFactory();
            Defaillance d = df.create( request );
            if ( df.validate( d,defaiManager ) ) {
                defaiManager.ajouter( d );

            } else {
                request.setAttribute( "erreurs", df.getErreurs() );

            }
        }

        request.setAttribute( "active", active );
        request.setAttribute( "causes", causeManager.lister() );
        request.setAttribute( "effets", effManager.lister() );
        request.setAttribute( "defai", defaiManager.lister() );
        pg.generate( getServletContext(), request, response );

    }

}
