package servlets.Utilisateur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.session.general.page.PageGenerator;

 
@WebServlet("/Deconnexion")
public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
   
    public Deconnexion() {
        super();
   
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator("/WEB-INF/indexLogin.jsp", "/WEB-INF/vues/Utilisateur/logout.jsp", "", "/Connexion");
		 HttpSession session = request.getSession();
	     session.invalidate();
      	pg.generate( getServletContext(), request, response);

      
        
		 
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator("/WEB-INF/indexLogin.jsp", "/WEB-INF/vues/Utilisateur/login.jsp","Se connecter", "/Connexion");

	   	pg.generate( getServletContext(), request, response);
	}

}
