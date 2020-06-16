package beans.session.general;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class PageGenerator {
    
    private static final String ATT_VUE = "vue";
    private static final String ATT_TITLE = "title";
    private static final String PAGE_WRAPPER ="/WEB-INF/index.jsp";
    private String pageWrapperJSP;
    private String vueJSP;
    private String pageTitle;
    private String redirectURL;
    
    
    
    public PageGenerator( String redirectURL ) {
        super();
        this.redirectURL = redirectURL;
    }


    public PageGenerator(String vueJSP, String pageTitle,String redirectURL) {
        super();
        this.pageWrapperJSP =  PAGE_WRAPPER;
        this.vueJSP = vueJSP;
        this.pageTitle = pageTitle;
        this.redirectURL= redirectURL;
    }


    public PageGenerator( String vueJSP, String pageTitle ) {
        super();
        this.vueJSP = vueJSP;
        this.pageTitle = pageTitle;
        this.pageWrapperJSP = PAGE_WRAPPER;
    }
    
   
    

    public PageGenerator( String pageWrapperJSP, String vueJSP, String pageTitle, String redirectURL ) {
        super();
        this.pageWrapperJSP = pageWrapperJSP;
        this.vueJSP = vueJSP;
        this.pageTitle = pageTitle;
        this.redirectURL = redirectURL;
    }




    public String getPageWrapperJSP() {
        return pageWrapperJSP;
    }
    
    private RequestDispatcher getRequestDispatcher(ServletContext contexte ,HttpServletRequest request ,HttpServletResponse response) {
        request.setAttribute( ATT_TITLE, this.pageTitle );
        request.setAttribute( ATT_VUE, this.vueJSP );
        
        return contexte.getRequestDispatcher(this.pageWrapperJSP);
    }
    
    public void generate(ServletContext contexte ,HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException {
        this.getRequestDispatcher( contexte, request, response ).forward( request, response );
    }
    
    public void redirect(ServletContext contexte,HttpServletRequest request,HttpServletResponse response) throws IOException {
        if(redirectURL!=null) {
            response.sendRedirect(contexte.getContextPath()+redirectURL );
        }else{
            this.getRequestDispatcher( contexte, request, response );
        }
    }
    
    public String getPathId(HttpServletRequest request) {
        String id ="";
        if ( request.getPathInfo() != null ) {
            id = request.getPathInfo().substring( 1 );
        }
        return id;
    }
    
    public String[] getPathIds(HttpServletRequest request){
        if(request.getPathInfo()!=null) {
            return request.getPathInfo().substring( 1 ).split( "/" );
        }else {
            return null;
        }
        
    }
    
    public void setRedirectURL( String redirectURL ) {
        this.redirectURL = redirectURL;
    }
    
    public void setPageTitle( String pageTitle ) {
        this.pageTitle = pageTitle;
    }
}
