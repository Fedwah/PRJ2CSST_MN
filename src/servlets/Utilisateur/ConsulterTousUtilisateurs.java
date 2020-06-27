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


 
@WebServlet("/Utilisateurs")
public class ConsulterTousUtilisateurs extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 public static final String VUE1         = "/WEB-INF/vues/Utilisateur/ChercherUsers.jsp";	
	 private static final String CHAMP_S     = "search";
	 public static final String ATT_ERREURS  = "erreurs";
	 public static final String ATT_RESULTAT = "resultat";
	 public static final String ATT = "Users";
	 
	  
	List<Utilisateur> user;
	List<Utilisateur> user1;
	 @EJB
	 private MethodeUtilisateur User;
	    
    
	 
    public ConsulterTousUtilisateurs() {
        super();
   
        
    }

	
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String search = request.getParameter( CHAMP_S );
		if (search  != null) {
			PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/ChercherUsers.jsp","Rechercher les utilisateurs");
	 
			pg.generate( getServletContext(), request, response );
        }
		else {
			
			PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/ListerUsers.jsp", "Liste des utilisateurs") ;
		 
	        List<beans.entities.utilisateurs.Utilisateur> Users =User.recupererTOUTUtilisateur();
			request.setAttribute("Users", Users);
			pg.generate( getServletContext(), request, response );
		}
		

	}

	 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		    String search = request.getParameter("search");
		   /* String filtre= request.getParameter("filtre");*/
		    String filtreVal= request.getParameter("filtreVal");
		    
		    if (search !=null)
		    {
		    PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/ChercherUsers.jsp",  "Liste des utilisateurs");
         	String resultat = null;  
  		    String nom = request.getParameter( CHAMP_S );
            user= User.trouverNOM(nom); 		  
     	    if (user!= null) {
             resultat = "Utilisateur trouvé.";
      	      request.setAttribute( ATT, user );
              request.setAttribute( ATT_RESULTAT, resultat );
         
             pg.generate( getServletContext(), request, response );
     	        } else {
	           resultat = "Aucun utilisateur n'a été trouvé."; 
	           request.setAttribute( ATT, user );
	           request.setAttribute( ATT_RESULTAT, resultat ); 
	       	    
	           pg.generate( getServletContext(), request, response );}}
		    else {
		    
	          if ((filtreVal.contentEquals("Operationnel") )|| (filtreVal.contentEquals("Regional") ) || (filtreVal.contentEquals("Central") ) )
	          {
	        	  PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/ListerUsers.jsp", "Liste des utilisateurs","/Utilisateurs");
	        	 
	        	   user1=User.FiltrerparType(filtreVal);
	        	   request.setAttribute( ATT, user1 );
	        	   pg.generate( getServletContext(), request, response ); }
	          else {
	        		  
	        			 
	          if (filtreVal.contentEquals("Admin")  || filtreVal.contentEquals("Utilisateur") )
	          {
	        	  PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/ListerUsers.jsp", "Liste des utilisateurs","/Utilisateurs");
	        	   user1=User.FiltrerparRole(filtreVal);
	        	   
	        	   request.setAttribute( ATT, user1 );
	        	   pg.generate( getServletContext(), request, response ); }
	          }
	         }
	          
     
       }

	   
		
	}
	 


