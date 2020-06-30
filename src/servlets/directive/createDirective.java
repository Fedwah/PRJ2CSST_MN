package servlets.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.entities.directive.directive;
import beans.entities.utilisateurs.Utilisateur;
import beans.session.Utilisateur.MethodeUtilisateur;
import beans.session.directive.MethodesDirectives;
import beans.session.general.page.Page;
import beans.session.general.page.PageGenerator;

 
@WebServlet("/createDirective")
public class createDirective extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Utilisateur> usersop = new ArrayList<Utilisateur>();
	private List<Utilisateur> usersreg = new ArrayList<Utilisateur>();
	 
	 @EJB
	 private MethodesDirectives drctv;
	 
    public createDirective() {
        super();
       
    }

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 PageGenerator pg = new PageGenerator("/WEB-INF/vues/directives/createDirective.jsp", "Créer une directive");
	 usersop =drctv.ListerUsersOperationnel();
	 usersreg=drctv.ListerUsersRegional();
	 request.setAttribute("usersreg", usersreg);
	 request.setAttribute("usersop", usersop);
	 pg.generate( getServletContext(), request, response );
	 
	 }

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageGenerator pg = new PageGenerator("/WEB-INF/vues/directives/createDirective.jsp", "Créer une directive");
		 usersop =drctv.ListerUsersOperationnel();
		 usersreg=drctv.ListerUsersRegional();
		 request.setAttribute("usersop", usersop);
		 request.setAttribute("usersreg", usersreg);
		 String objet = request.getParameter("objet");
		 String message = request.getParameter("message");
		 int dist=0;
		/* int distop= Integer.parseInt(request.getParameter("distop"));*/
		 int distreg= Integer.parseInt(request.getParameter("distreg"));
		 if (distreg != 0 )
		 {
			 dist=distreg ;
		 }
		 /*else {
		    dist = distop;
		 }
		/* HttpSession session = request.getSession();
		 int origin = Integer.parseInt((String) session.getAttribute("sessionScope.sessionUtilisateur.id"));*/
		 int origin=Integer.parseInt(request.getParameter("origin"));
		 directive directv = new directive (origin,objet,message,dist);
		 drctv.creer(directv);
		 String resultat = "la directive est envoyee !";
		 request.setAttribute( "resultat", resultat );
	
		 pg.generate( getServletContext(), request, response );
		 
	}

}
