package servlets.Utilisateur;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.regions.Region;
import beans.entities.utilisateurs.Utilisateur;
import beans.session.Utilisateur.MethodeUtilisateur;
import beans.session.general.PageGenerator;
import beans.session.regions.RegionFactory;
import beans.session.regions.RegionManager;

 
@WebServlet("/SuppUser/*")
public class SuppUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int id;
	 private static final String CHAMP_ID   = "id";
	 public static final String ATT_RESULTAT = "result";
	 private static String result = ""; 
	 @EJB
	 private MethodeUtilisateur User;         
     @EJB
     private RegionManager regM;
    public SuppUser() {
        super();
         
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  	/* PageGenerator pg = new PageGenerator("/Utilisateurs");*/
		 PageGenerator pg = new PageGenerator("/WEB-INF/vues/Utilisateur/SuppUsers.jsp", "Liste des utilisateurs");
		 id = Integer.parseInt(request.getPathInfo().substring( 1 ));
		 /*******************************************************************/
		 // afin de mettre la region null avant de supprimer l'utilisateur
		 Utilisateur u = User.trouver(id);
		 if(u.getRegion() != null)
		 {
			 Region oldReg = u.getRegion();
			 Region newReg = u.getRegion();
			 //newReg.setUser(null);
			 RegionFactory rf = new RegionFactory();
			 regM.mettreAJour(oldReg.getCodeReg(), rf, newReg);
			 
		 }
		 /******************************************************************/
		 result = User.supprimerUser(id);
		 request.setAttribute( ATT_RESULTAT, result );
		 pg.generate( getServletContext(), request, response );
	 
		 /*pg.redirect( getServletContext(), request, response );*/
		 
		 
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		doGet(request, response);
	}

}
