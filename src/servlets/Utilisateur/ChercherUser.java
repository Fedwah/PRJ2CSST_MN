package servlets.Utilisateur;

import java.io.IOException;
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
import beans.session.general.PageGenerator;

 
@WebServlet("/ChercherUser")
public class ChercherUser extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 public static final String VUE1         = "/WEB-INF/vues/Utilisateur/ChercherUsers.jsp";	
	 private static final String CHAMP_NOM   = "nom";
	 public static final String ATT_ERREURS  = "erreurs";
	 public static final String ATT_RESULTAT = "resultat";
	 public static final String ATT = "user";
	List<Utilisateur> user;
	 @EJB
	 private MethodeUtilisateur User;  
    public ChercherUser() {
        super();
      
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/ChercherUsers.jsp",  "Chercher des utilisateurs");
		 pg.generate( getServletContext(), request, response );
	}
	
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	            PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/ChercherUsers.jsp",  "Chercher des utilisateurs");
	           	String resultat = null;  
	    		String nom = request.getParameter( CHAMP_NOM );
	            user= User.trouverNOM(nom); 		  
	       	    if (user!= null) {
	               resultat = "Utilisateur trouvé.";
	        	   request.setAttribute( ATT, user );
	               request.setAttribute( ATT_RESULTAT, resultat );
	               pg.generate( getServletContext(), request, response );
	       	        } else 
	 	           resultat = "Aucun utilisateur n'a été trouvé."; 
	 	           request.setAttribute( ATT, user );
	 	           request.setAttribute( ATT_RESULTAT, resultat ); 
	 	           pg.generate( getServletContext(), request, response );
	
	}
	              
	            
	             
	}


