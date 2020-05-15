package beans.session.general;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class PageGenerator {
    
    private String parentJSP;
    private String childJSP;
    private String title;
    
    
    public PageGenerator( String parentJSP, String childJSP, String title ) {
        super();
        this.parentJSP = parentJSP;
        this.childJSP = childJSP;
        this.title = title;
    }


    public void generate(ServletContext contexte ,HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute( "title", this.title );
        request.setAttribute( "vue", this.childJSP );
        
        contexte.getRequestDispatcher(this.parentJSP).forward( request, response );
    }
    
}
