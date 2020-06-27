package beans.session.general.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.wildfly.security.http.HttpServerRequest;

public class SessionManager {
    private  HttpSession session;
    
    public SessionManager() {
        
    }

    public void save(HttpServletRequest request , String name , Object value) {
        getSession( request ).setAttribute( name, value );
    }
    
    public Object get(HttpServletRequest request , String name) {
        return getSession( request ).getAttribute( name );
    }
    
    public void remove(HttpServletRequest request , String name) {
        getSession( request ).removeAttribute( name );
    }
    
    public HttpSession getSession(HttpServletRequest request) {
        if(this.session==null) {
            this.session = request.getSession();
        }
        
        return this.session;
    }
    
   
}
