package servlets.Utilisateur;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.entities.utilisateurs.Utilisateur;
import beans.session.Utilisateur.MethodeUtilisateur;
 
import beans.session.general.page.PageGenerator;
 
 

 
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 public static final String VUE1          = "/WEB-INF/connexion.jsp";
	 public static final String VUE2          = "/WEB-INF/menuCnxn.jsp";
	 public static final String CHAMP_USER  = "nomUtilisateur";
	 public static final String CHAMP_PASS   = "motdepasse";  
	 public static final String CHAMP_ERROR = "errorCnxn";
	 public static final String ATT_ERREURS  = "erreurs";
	 public static final String ATT_RESULTAT = "resultat";
	 public static final String ATT_SESSION_USER = "sessionUtilisateur";
	 Utilisateur utilisateur;
	  @EJB
	   private MethodeUtilisateur User;
   
    public Connexion() {
        super();
 
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator("/WEB-INF/indexLogin.jsp", "/WEB-INF/vues/Utilisateur/login.jsp","Se connecter", "/Connexion");
	    pg.generate( getServletContext(), request, response);

	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String resultat;  
		 String nomUtilisateur = request.getParameter( CHAMP_USER );
	     String motdepasse = request.getParameter( CHAMP_PASS );
	     Map<String, String> erreurs = new HashMap<String, String>();
	     
	     HttpSession session = request.getSession();
	                                
	        /* Validation des champs mot de passe  */
	        try {
	            validationMotsDePasse( motdepasse);
	            } catch ( Exception e ) {
	            erreurs.put( CHAMP_PASS, e.getMessage() );
	                                    }
	        
	        /* Initialisation du rï¿½sultat global de la validation. */
	        if ( erreurs.isEmpty() ) {
	        	
	        utilisateur= User.connecter(nomUtilisateur,motdepasse) ;
	        		  
		            if (utilisateur != null) {
<<<<<<< HEAD
		               System.out.println("utilisateur trouvé");
		               PageGenerator pg1 = new PageGenerator("/WEB-INF/index.jsp", "/pieces", "Menu", "");		              
		               session.setAttribute( ATT_SESSION_USER, utilisateur );
		               String role = utilisateur.getRole();
		               session.setAttribute( "role", role );
		               String type=utilisateur.getType();
		               session.setAttribute( "type", type );
		               int id = utilisateur.getId();
		               session.setAttribute( "id", id );
		        	   resultat = "Vous etes connecté ! .";
=======
		               
		                /* Code avant changement pas @Syphax
		                 * 
		               System.out.println("utilisateur trouvï¿½");
		               PageGenerator pg1 = new PageGenerator("/WEB-INF/index.jsp", "/pieces", "Menu",);		              
		               
		        	   resultat = "Vous etes connectï¿½ ! .";
>>>>>>> branch 'master' of https://github.com/Fedwah/PRJ2CSST_MN.git
		        	   request.setAttribute("connected", true);
		        	   pg1.generate( getServletContext(), request, response);
		        	  
		    	      */
		                
		                session.setAttribute( ATT_SESSION_USER, utilisateur );
		                PageGenerator pg1 = new PageGenerator( "/pieces" );
		                pg1.redirect( getServletContext(), request, response );
		                
		             } 
					else {
					  PageGenerator pg2 = new PageGenerator("/WEB-INF/indexLogin.jsp", "/WEB-INF/vues/Utilisateur/login.jsp","Se connecter", "/Connexion");
		              resultat = "Nom d'utilisateur ou mot de passe erronï¿½s.";   
		              request.setAttribute( ATT_ERREURS, erreurs );
			          request.setAttribute( ATT_RESULTAT, resultat );
			          pg2.generate( getServletContext(), request, response);

	        }   
		    }
	        else 
	        {
	        	PageGenerator pg = new PageGenerator("/WEB-INF/indexLogin.jsp", "/WEB-INF/vues/Utilisateur/login.jsp","Se connecter", "/Connexion");
	            resultat = "ï¿½chec ï¿½ cause des erreurs de saisie.";
	            request.setAttribute( ATT_ERREURS, erreurs );
		        request.setAttribute( ATT_RESULTAT, resultat );
		        pg.generate( getServletContext(), request, response);

	            
	        }
	               
	      
	}

	
	 
	
	
	/**
	 * Valide les mots de passe saisis.
	 */
	private void validationMotsDePasse( String motDePasse ) throws Exception{
	    
	        if (motDePasse.trim().length() < 5) {
	          throw new Exception("Le mot de passe est court, merci de le saisir ï¿½ nouveau.");
	                                             }
	    }
	
}
