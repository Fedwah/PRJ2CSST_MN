package servlets.Utilisateur;

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
import javax.servlet.http.HttpSession;

import beans.entities.regions.Region;
import beans.entities.utilisateurs.Utilisateur;
 
import beans.session.Utilisateur.MethodeUtilisateur;
import beans.session.general.page.PageGenerator;
 
@WebServlet("/createUser")
public class createUser extends HttpServlet {
	   private static final long serialVersionUID = 1L;
	    @EJB
	   private MethodeUtilisateur User;
	    public static final String ATT_USER = "utilisateur";
	    public static final String VUE          = "/WEB-INF/vues/Utilisateur/createUser.jsp";
	    public static final String CHAMP_USER  = "nomUtilisateur";
	    public static final String CHAMP_PASS   = "motdepasse";
	    public static final String CHAMP_CONF   = "confirmation";
	    public static final String CHAMP_NOM    = "nom";
	    public static final String CHAMP_PRENOM    = "prenom";
	    public static final String CHAMP_TYPE    ="type";
	    public static final String CHAMP_ROLE    ="role";
	    public static final String CHAMP_UNITE    ="unite";
	    public static final String CHAMP_REG    ="codereg";
	    public static final String CHAMP_UN    ="codeun";
	    public static final String ATT_ERREURS  = "erreurs";
	    public static final String ATT_TYPE = "type";
	    public static final String ATT_ROLE = "role";
	    public static final String ATT_RESULTAT = "resultat";
	    public static final String CHAMP_POSTE ="poste";
	    private ArrayList<String> type2 = new ArrayList<String>();
	    private ArrayList<String> role2 = new ArrayList<String>();
	    private ArrayList<String> reg = new ArrayList<String>();
	    private ArrayList<String> un = new ArrayList<String>();
	    private Region region = new Region ();
	     

    public createUser() {
        super();
        type2.add("Operationnel");
        type2.add("Regional");
        type2.add("Central");
        role2.add("Admin");
        role2.add("Utilisateur");
        
       
    }

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String type =  (String) session.getAttribute("type");
		String role= (String) session.getAttribute("role");
		String codereg= (String) session.getAttribute("codereg");
	 
		if (type.contentEquals("Operationnel") && role.contentEquals("Admin")) {
			reg=(ArrayList<String>) User.recupererCodereg();
	        un=(ArrayList<String>) User.recupererCodeun();
		    PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/createUserAdminOp.jsp", "Créer un utilisateur");
		    pg.generate( getServletContext(), request, response );
		}
		else {
		reg=(ArrayList<String>) User.recupererCodereg();
        un=(ArrayList<String>) User.recupererCodeun();
	    PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/createUser.jsp", "Créer un utilisateur");
	    request.setAttribute( "un", un );
	    request.setAttribute( "reg", reg );
	    request.setAttribute( ATT_TYPE, type2 );
	    request.setAttribute( ATT_ROLE, role2 );
	    pg.generate( getServletContext(), request, response );}
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String type =  (String) session.getAttribute("type");
		String role= (String) session.getAttribute("role");
		String codereg= (String) session.getAttribute("codereg");
		String codeun= (String) session.getAttribute("codeun");
		reg=(ArrayList<String>) User.recupererCodereg();
        un=(ArrayList<String>) User.recupererCodeun();
	    String resultat;  
	    Map<String, String> erreurs = new HashMap<String, String>();
        String nomUtilisateur = request.getParameter( CHAMP_USER );
        String motDePasse = request.getParameter( CHAMP_PASS );
        String confirmation = request.getParameter( CHAMP_CONF );
        String nom = request.getParameter( CHAMP_NOM );
        String prenom = request.getParameter( CHAMP_PRENOM );
        String type1 = request.getParameter(CHAMP_TYPE);
        String role1 = request.getParameter( CHAMP_ROLE );
        String codereg1 = request.getParameter( CHAMP_REG );
        String codeun1 = request.getParameter( CHAMP_UN );
        String poste = request.getParameter( CHAMP_POSTE );
        try {
            validationMotsDePasse( motDePasse, confirmation );
        } catch ( Exception e ) {
            erreurs.put( CHAMP_PASS, e.getMessage() );
        }
        
		if (type.contentEquals("Operationnel") && role.contentEquals("Admin")) {
			 if ( erreurs.isEmpty() ) {
		        	PageGenerator pg1 = new PageGenerator("/WEB-INF/vues/Utilisateur/createUserAdminOp.jsp", "", "/Utilisateurs");
		            resultat = "Succés de l'inscription.";
		            type1="Operationnel";
		            role1="Utilisateur";
		            codereg1=codereg;
		            codeun1=codeun;
		            Utilisateur utilisateur = new Utilisateur(nomUtilisateur, motDePasse, nom, prenom, type1, role1, codereg1,codeun1, poste);
			        User.creer(utilisateur) ;
			        request.setAttribute( ATT_ERREURS, erreurs );
			        request.setAttribute( ATT_RESULTAT, resultat );
			        pg1.generate( getServletContext(), request, response);}
			        else {
			        	PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/createUserAdminOp.jsp","Crï¿½er un utilisateur");
			            resultat = "Echec de l'inscription.";
			            request.setAttribute( ATT_ERREURS, erreurs );
				        request.setAttribute( ATT_RESULTAT, resultat ); 
			            pg.generate( getServletContext(), request, response ); 
			        }
			        
		}
		else {
		
	        if ( erreurs.isEmpty() ) {
	        	PageGenerator pg1 = new PageGenerator("/WEB-INF/vues/Utilisateur/createUser.jsp", "", "/Utilisateurs");
	            resultat = "Succés de l'inscription.";
	            if (type1.contentEquals("Central"))
	            {
	            	poste=null;
	            	codereg1=null;
	            	codeun1=null;
	            	role1="Utilisateur";
	            			
	            }
	            if (type1.contentEquals("Regional"))
	            {
	            	poste=null;
	            	codeun1=null;
	            	role1="Utilisateur";
	            }
	            if (type1.contentEquals("Operationnel"))
	            {
	            	role1="Admin";
	            	region=User.trouverRegion(codeun1);
	            	codereg1=region.getCodeReg();
	            	poste=null;
	            }
	            Utilisateur utilisateur = new Utilisateur(nomUtilisateur, motDePasse, nom, prenom, type1, role1, codereg1,codeun1, poste);
		        User.creer(utilisateur) ;
		        request.setAttribute( ATT_ERREURS, erreurs );
		        request.setAttribute( ATT_RESULTAT, resultat );
		        request.setAttribute( ATT_TYPE, type2 );
		  	    request.setAttribute( ATT_ROLE, role2 );
		  	    request.setAttribute( "reg", reg );
		  	    request.setAttribute( "un", un );
		        pg1.generate( getServletContext(), request, response);
		        
		        

	        } else {
	        	PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/createUser.jsp","Crï¿½er un utilisateur");
	            resultat = "Echec de l'inscription.";
	            request.setAttribute( ATT_ERREURS, erreurs );
		        request.setAttribute( ATT_RESULTAT, resultat );
		        request.setAttribute( ATT_TYPE, type2 );
		  	    request.setAttribute( ATT_ROLE, role2 );
		  	    request.setAttribute( "reg", reg );
		  	    request.setAttribute( "un", un );
	            pg.generate( getServletContext(), request, response ); 
	        }     }
	}

	/**
	 * Valide les mots de passe saisis.
	 */
	 private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception{
	    if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
	        if (!motDePasse.equals(confirmation)) {
	            throw new Exception("Les mots de passe entrï¿½s sont diffï¿½rents, merci de les saisir ï¿½ nouveau.");
	        } else if (motDePasse.trim().length() < 5) {
	            throw new Exception("Les mots de passe doivent contenir au moins 5 caractï¿½res.");
	        }
	    } else {
	        throw new Exception("Merci de saisir et confirmer votre mot de passe.");
	    }
	}
 



}
