package servlets.amdec;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.amdec.Detection;
import beans.entities.amdec.enums.Frequence;
import beans.entities.amdec.enums.Gravite;
import beans.entities.amdec.enums.NoDetection;
import beans.entities.utilisateurs.Utilisateur;
import beans.entities.vehicules.Modele;
import beans.session.amdec.cause.CauseFactory;
import beans.session.amdec.cause.CausesManager;
import beans.session.amdec.defaillance.DefaillanceFactory;
import beans.session.amdec.defaillance.DefaillanceManager;
import beans.session.amdec.detection.DetectionFactory;
import beans.session.amdec.detection.DetectionManager;
import beans.session.amdec.effet.EffetFactory;
import beans.session.amdec.effet.EffetManager;
import beans.session.amdec.instruction.InstructionFactory;
import beans.session.amdec.instruction.InstructionManager;
import beans.session.general.page.PageGenerator;
import beans.session.pieces.PieceManager;
import beans.session.regions.unites.UniteManager;
import beans.session.vehicules.marques.modeles.ModeleManager;

/**
 * Servlet implementation class Amdec
 */
@WebServlet( "/amdec" )
public class Amdec extends HttpServlet {

    private static final long   serialVersionUID = 1L;
    private static final String AMDEC            = "/WEB-INF/vues/amdec/amdec.jsp";

    @EJB
    private CausesManager       causeManager;
    @EJB
    private DefaillanceManager  defaiManager;
    @EJB
    private EffetManager        effManager;

    @EJB
    private InstructionManager  instManager;

    @EJB
    private ModeleManager       modM;

    @EJB
    private PieceManager        pM;

    @EJB
    private DetectionManager    detM;

    @EJB
    private UniteManager        uM;

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
        Utilisateur u = pg.getUtilisateur( request );
        DetectionFactory df = new DetectionFactory();

        if ( u.getCodeun() != null && !u.getCodeun().isEmpty() ) {
            request.setAttribute( "detections", detM.listerUn( u.getCodeun() ) );
            
        }else if ( u.getCodereg() != null && u.getCodereg().isEmpty() ) {
            request.setAttribute( "detections", detM.listerReg( u.getCodereg() ) );
        } else {
            request.setAttribute( "detections", detM.lister() );
        }
        request.setAttribute( "causes", causeManager.lister() );
        request.setAttribute( "effets", effManager.lister() );
        request.setAttribute( "defai", defaiManager.lister() );
        request.setAttribute( "instructions", instManager.lister() );
        request.setAttribute( "modeles", modM.lister() );
        request.setAttribute( "pieces", pM.lister() );
      

        request.setAttribute( "gravites", Gravite.values() );
        request.setAttribute( "frequences", Frequence.values() );
        request.setAttribute( "niveaux", NoDetection.values() );
        pg.generate( getServletContext(), request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String active = ""; // Pour garder le dernier tab selectionner

        // volet cause
        if ( request.getParameter( "cause" ) != null ) {
            System.out.println( "post from cause" );
            active = "cause";
            CauseFactory cf = new CauseFactory();
            // j'ai deplacer le uniqueField au validateChilds
            cf.createValidateAjouter( request, causeManager );
        }
        // volet effet
        if ( request.getParameter( "eff" ) != null ) {
            System.out.println( "post from effet" );
            active = "effet";
            EffetFactory ef = new EffetFactory();
            ef.createValidateAjouter( request, effManager );
        }
        // volet defaillance
        if ( request.getParameter( "def" ) != null ) {
            System.out.println( "post from defaillance" );
            active = "defaillance";
            DefaillanceFactory df = new DefaillanceFactory();
            df.createValidateAjouter( request, defaiManager );

        }

        // volet instruction
        if ( request.getParameter( "instruction" ) != null ) {

            active = "instruction";
            InstructionFactory iF = new InstructionFactory();

            iF.createValidateAjouter( request, instManager );

        }

        request.setAttribute( "active", active );
        doGet( request, response );

    }

}
