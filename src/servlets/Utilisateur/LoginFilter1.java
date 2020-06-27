package servlets.Utilisateur;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter1
 */
@WebFilter("/*")
public class LoginFilter1 implements Filter {
 
    public LoginFilter1() {
       
    }

 
	public void destroy() {
		// TODO Auto-generated method stub
	}

	 
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		    HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        HttpSession session = request.getSession(false);
	        String loginURI = request.getContextPath() + "/Connexion";

	        boolean loggedIn = session != null && session.getAttribute("sessionUtilisateur") != null;
	        boolean loginRequest = request.getRequestURI().equals(loginURI);

	        if (loggedIn || loginRequest) {
	            chain.doFilter(request, response);
	        } else {
	            response.sendRedirect(loginURI);
	        }
	}

	 
	public void init(FilterConfig fConfig) throws ServletException {
	 
	}

}
