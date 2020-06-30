package servlets.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.entities.directive.directive;
import beans.entities.utilisateurs.Utilisateur;
import beans.session.directive.MethodesDirectives;
import beans.session.general.page.PageGenerator;

 
@WebServlet("/listerDirective")
public class listerDirective extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<directive> directives= new ArrayList<directive>();
	List<directive> directives1= new ArrayList<directive>();
	List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>(); 
	
	 
	 
	 @EJB
	 private MethodesDirectives drctv;  
    
    public listerDirective() {
        super();
        
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 String type =  (String) session.getAttribute("type");
		 String role= (String) session.getAttribute("role");
		 int sender = (int) session.getAttribute("id"); 
		 if (type.contentEquals("Central"))
		 {
			 PageGenerator pg2 = new PageGenerator("/WEB-INF/vues/directives/listerDirectiveCentral.jsp", "Lister les directives");
			 directives =drctv.ListerdirectiveEnvoye(sender);
			 request.setAttribute("directives", directives); 
			 pg2.generate( getServletContext(), request, response );
		 }
		if (type.contentEquals("Operationnel") && role.contentEquals("Admin"))
		 {
			 
			PageGenerator pg3 = new PageGenerator("/WEB-INF/vues/directives/listerDirectives.jsp", "Lister les directives");
			directives1=drctv.ListerdirectiveRecues(sender);
			request.setAttribute("directives1", directives1); 
			pg3.generate( getServletContext(), request, response );
		 }
		
		 if (type.contentEquals("Regional"))
		 {
			 PageGenerator pg1 = new PageGenerator("/WEB-INF/vues/directives/listerDirectiveReg.jsp", "Lister les directives");
			 directives =drctv.ListerdirectiveEnvoye(sender);
			 request.setAttribute("directives", directives); 
			 directives1=drctv.ListerdirectiveRecues(sender);
			  
				 
			 request.setAttribute("directives1", directives1);  
			 pg1.generate( getServletContext(), request, response );
		 }
		 
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		doGet(request, response);
	}

}
