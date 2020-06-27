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
	    private ArrayList<String> type1 = new ArrayList<String>();
	    private ArrayList<String> role1 = new ArrayList<String>();
	    private ArrayList<String> reg = new ArrayList<String>();
	    private ArrayList<String> un = new ArrayList<String>();
	    
	  

	   
	    
	   

    public createUser() {
        super();
        type1.add("Operationnel");
        type1.add("Regional");
        type1.add("Central");
        role1.add("Admin");
        role1.add("Utilisateur");
        
       
    }

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reg=(ArrayList<String>) User.recupererCodereg();
        un=(ArrayList<String>) User.recupererCodeun();
	    PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/createUser.jsp", "Cr�er un utilisateur");
	    request.setAttribute( "un", un );
	    request.setAttribute( "reg", reg );
	    request.setAttribute( ATT_TYPE, type1 );
	    request.setAttribute( ATT_ROLE, role1 );
	    pg.generate( getServletContext(), request, response );
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    reg=(ArrayList<String>) User.recupererCodereg();
            un=(ArrayList<String>) User.recupererCodeun();
		    String resultat;  
		    Map<String, String> erreurs = new HashMap<String, String>();
	        String nomUtilisateur = request.getParameter( CHAMP_USER );
	        String motDePasse = request.getParameter( CHAMP_PASS );
	        String confirmation = request.getParameter( CHAMP_CONF );
	        String nom = request.getParameter( CHAMP_NOM );
	        String prenom = request.getParameter( CHAMP_PRENOM );
	        String type = request.getParameter(CHAMP_TYPE);
	        String role = request.getParameter( CHAMP_ROLE );
	        String codereg = request.getParameter( CHAMP_REG );
	        String codeun = request.getParameter( CHAMP_UN );
	        
	     
	       
	   
	      try {
	            validationMotsDePasse( motDePasse, confirmation );
	        } catch ( Exception e ) {
	            erreurs.put( CHAMP_PASS, e.getMessage() );
	        }

	        
	        if ( erreurs.isEmpty() ) {
	        	PageGenerator pg1 = new PageGenerator("/WEB-INF/vues/Utilisateur/createUser.jsp", "", "/Utilisateurs");
	            resultat = "Succ�s de l'inscription.";
	            Utilisateur utilisateur = new Utilisateur(nomUtilisateur, motDePasse, nom, prenom, type, role, codereg,codeun);
		        User.creer(utilisateur) ;
		        request.setAttribute( ATT_ERREURS, erreurs );
		        request.setAttribute( ATT_RESULTAT, resultat );
		        request.setAttribute( ATT_TYPE, type1 );
		  	    request.setAttribute( ATT_ROLE, role1 );
		  	    request.setAttribute( "reg", reg );
		  	    request.setAttribute( "un", un );
		        pg1.generate( getServletContext(), request, response);
		        
		        

	        } else {
	        	PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/createUser.jsp","Cr�er un utilisateur");
	            resultat = "�chec de l'inscription.";
	            request.setAttribute( ATT_ERREURS, erreurs );
		        request.setAttribute( ATT_RESULTAT, resultat );
		        request.setAttribute( ATT_TYPE, type1 );
		  	    request.setAttribute( ATT_ROLE, role1 );
		  	    request.setAttribute( "reg", reg );
		  	    request.setAttribute( "un", un );
	            pg.generate( getServletContext(), request, response ); 
	        }     
	}

	/**
	 * Valide les mots de passe saisis.
	 */
	 private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception{
	    if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
	        if (!motDePasse.equals(confirmation)) {
	            throw new Exception("Les mots de passe entr�s sont diff�rents, merci de les saisir � nouveau.");
	        } else if (motDePasse.trim().length() < 5) {
	            throw new Exception("Les mots de passe doivent contenir au moins 5 caract�res.");
	        }
	    } else {
	        throw new Exception("Merci de saisir et confirmer votre mot de passe.");
	    }
	}
 



}
