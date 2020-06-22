package servlets.Utilisateur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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


@WebServlet("/EditUser/*")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 public static final String CHAMP_NOM    = "nom";
	    public static final String CHAMP_PRENOM    = "prenom";
	    public static final String CHAMP_ID    = "id";
	    public static final String CHAMP_PASS   = "motdepasse";
	    public static final String CHAMP_USER  = "nomUtilisateur";
	    public static final String CHAMP_ROLE    ="role";
	    public static final String ATT_ID   ="id";
	    public static final String ATT_ID1   ="id1";
	    public static final String ATT_RES = "result";
	    public static final String ATT_ERREURS  = "erreurs";
	    public static final String CHAMP_CONF   = "confirmation";
	    public static final String ATT_ROLE = "role";
	    private ArrayList<String> role1 = new ArrayList<String>();
	    int id = 0;
	    int id1 = 0;
    @EJB
	private MethodeUtilisateur User;         
   

    public EditUser() {
        super();
        role1.add("Admin");
        role1.add("Utilisateur");
      
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/EditUsers.jsp","Modifier un utilisateur");
	 id = Integer.parseInt(request.getPathInfo().substring( 1 ));
	 request.setAttribute( ATT_ID,id );
	 request.setAttribute( ATT_ROLE, role1 );
	 pg.generate( getServletContext(), request, response );
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	  Map<String, String> erreurs = new HashMap<String, String>();
      id = Integer.parseInt(request.getParameter(CHAMP_ID));
      String nomUtilisateur = request.getParameter( CHAMP_USER );
      String motDePasse = request.getParameter( CHAMP_PASS );
      String nom = request.getParameter( CHAMP_NOM );
      String prenom = request.getParameter( CHAMP_PRENOM );
      String role = request.getParameter( CHAMP_ROLE );
      String confirmation = request.getParameter( CHAMP_CONF );
      String  result;
      
      try {
          validationMotsDePasse( motDePasse, confirmation );
      } catch ( Exception e ) {
          erreurs.put( CHAMP_PASS, e.getMessage() );
      }

	 

      if ( erreurs.isEmpty() ) {
    	   /* PageGenerator pg1 = new PageGenerator("/WEB-INF/vues/Utilisateur/createUser.jsp", "", "/Utilisateurs");*/
    	    PageGenerator pg1 = new PageGenerator("/WEB-INF/vues/Utilisateur/EditUsers.jsp",  "", "/EditUser/");
    	    id1 = Integer.parseInt(request.getPathInfo().substring( 1 ));
    	    result = User.modifierUser(id, nomUtilisateur,motDePasse,role);  
            request.setAttribute( ATT_RES,result);
            request.setAttribute( ATT_ID1,id1 );
            request.setAttribute( ATT_ERREURS, erreurs );
            request.setAttribute( ATT_ROLE, role1 );
            pg1.generate( getServletContext(), request, response);
	       

      } else {
    	  PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/EditUsers.jsp",  "", "/EditUser/");
          result = "�chec lors de la saisie des champs.";
          request.setAttribute( ATT_RES,result);
          request.setAttribute( ATT_ID1,id );
          request.setAttribute( ATT_ERREURS, erreurs );
          request.setAttribute( ATT_ROLE, role1 );
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
