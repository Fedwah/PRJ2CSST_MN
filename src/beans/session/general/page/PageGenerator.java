package beans.session.general.page;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.jboss.resteasy.spi.HttpRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.wildfly.common.iteration.IntIterator;

import beans.entities.utilisateurs.Utilisateur;
import servlets.Utilisateur.Connexion;

public class PageGenerator {

    
    private static final String ATT_MESSAGE = "message";
    private static final String ATT_REQUEST  = "request";
    private static final String ATT_VUE      = "vue";
    private static final String ATT_TITLE    = "title";
    private static final String PAGE_WRAPPER = "/WEB-INF/index.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/error.jsp";
    
    private String              pageWrapperJSP;
    private String              vueJSP;
    private String              pageTitle;
    private String              redirectURL;
    private String              errorPage;
    
    private SessionManager sessionManager;
    public static Utilisateur pageUser;
    
    public PageGenerator() {
        super();
        this.sessionManager = new SessionManager();
        this.errorPage = ERROR_PAGE;
        this.pageWrapperJSP = PAGE_WRAPPER;
    }
    
    public PageGenerator( String redirectURL ) {
        this();
        this.redirectURL = redirectURL;
    }

    public PageGenerator( String vueJSP, String pageTitle, String redirectURL ) {
        this();
        this.vueJSP = vueJSP;
        this.pageTitle = pageTitle;
        this.redirectURL = redirectURL;
    }

    public PageGenerator( String vueJSP, String pageTitle ) {
        this();
        this.vueJSP = vueJSP;
        this.pageTitle = pageTitle;
       
    }
    
    public PageGenerator( String pageWrapperJSP, String vueJSP, String pageTitle, String redirectURL) {
        this();
        this.pageWrapperJSP = pageWrapperJSP;
        this.vueJSP = vueJSP;
        this.pageTitle = pageTitle;
        this.redirectURL = redirectURL;
    }
    
    public PageGenerator( String pageWrapperJSP, String vueJSP, String pageTitle, String redirectURL,String pageError) {
        this();
        this.pageWrapperJSP = pageWrapperJSP;
        this.vueJSP = vueJSP;
        this.pageTitle = pageTitle;
        this.redirectURL = redirectURL;
        this.errorPage = pageError;
    }

    public String getPageWrapperJSP() {
        return pageWrapperJSP;
    }
    
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private RequestDispatcher getRequestDispatcher( ServletContext contexte, HttpServletRequest request,
            HttpServletResponse response , Boolean error) {
        request.setAttribute( ATT_TITLE, this.pageTitle );
        request.setAttribute( ATT_VUE, this.vueJSP );
        
        this.pather( request );
        if(error) {
            return contexte.getRequestDispatcher( this.errorPage );
        }else
            return contexte.getRequestDispatcher( this.pageWrapperJSP );
    }

    public void generate( ServletContext contexte, HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        this.generate( contexte, request, response ,false);
    }
    
    public void generate( ServletContext contexte, HttpServletRequest request, HttpServletResponse response ,boolean root) throws ServletException, IOException {
        if(root) {
            this.clearPath( request );
        }
        this.getRequestDispatcher( contexte, request, response,false).forward( request, response );
    }
    
    public void generateErreur(ServletContext contexte, HttpServletRequest request, HttpServletResponse response ,String message) throws ServletException, IOException {
        request.setAttribute( ATT_MESSAGE, message);
        this.getRequestDispatcher( contexte, request, response,true).forward( request, response );
    }
    
    public void generateJSON(HttpServletResponse response , List<JSONObject> objects) {
        JSONArray arr = new JSONArray( objects );
        System.out.println( "Generate JSON : "+arr );
        response.reset();
        response.setContentType("application/json;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            
            out.print(arr);
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
    }
    
    public void generateJSON(HttpServletResponse response ,Boolean success,String message) {
        
        JSONObject obj = new JSONObject();
        obj.put( "success", success );
        obj.put( "message", message );
        response.reset();
        response.setContentType("application/json;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            
            out.print(obj);
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
    }
    public void redirect( ServletContext contexte, HttpServletRequest request, HttpServletResponse response )
            throws IOException {
        if ( redirectURL != null ) {
            response.sendRedirect( contexte.getContextPath() + redirectURL );
        } 
    }
    
    public void redirectBack( ServletContext contexte, HttpServletRequest request, HttpServletResponse response )
            throws IOException {
        Page page  = Pather.getPather( request ).goBack();
        response.sendRedirect( contexte.getContextPath() + page.getLink() );
    }
    

    public String getPathId( HttpServletRequest request ) {
        String id = "";
        if ( request.getPathInfo() != null ) {
            id = request.getPathInfo().substring( 1 );
        }
        return cleanText(id);
    }

    public String[] getPathIds( HttpServletRequest request ) {
        String[] out = null;
        if ( request.getPathInfo() != null ) {
           out  = request.getPathInfo().substring( 1 ).split( "/" );
           for ( int i = 0; i < out.length; i++ ) {
               out[i] = cleanText( out[i] );
           }
           return out;
        } else {
            return null;
        }

    }

    private String cleanText(String text) {
        return text.replaceAll("^\"|\"$", "").trim();
    }
    public void writeWorkBook( Workbook wk, String fileName, HttpServletResponse response ) {
        int DEFAULT_BUFFER_SIZE = 10240; // 10 ko

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            wk.write( byteArrayOutputStream );

            response.reset();
            response.setBufferSize( DEFAULT_BUFFER_SIZE );
            response.setContentType( "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
            response.setHeader( "Content-Length", String.valueOf( byteArrayOutputStream.size() ) );
            response.setHeader( "Content-Disposition", "attachment; filename=\"" + fileName + ".xlsx\"" );
            wk.write( response.getOutputStream() );
        } catch ( IOException e1 ) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        ;

    }

    public void setRedirectURL( String redirectURL ) {
        this.redirectURL = redirectURL;
    }

    public void setPageTitle( String pageTitle ) {
        this.pageTitle = pageTitle;
    }

    public void saveRequest( HttpServletRequest request ) {
        this.sessionManager.save( request, ATT_REQUEST, request );
    }

    public HttpServletRequest getSavedReuest( HttpServletRequest request ) {
        HttpServletRequest r = (HttpServletRequest) this.sessionManager.get(request,ATT_REQUEST );
        this.sessionManager.remove(request,ATT_REQUEST);
        return r;

    }

  
    
    
    public void clearPath( HttpServletRequest request) {
        Pather.getPather( request ).clear();
        //s.removeAttribute(ATT_PATH );
    }
    
   
    
    private void pather(HttpServletRequest request) {
        Pather p  = Pather.getPather( request );
        //System.out.println( "GO TO :"+this.pageTitle+" = "+this.getPath( request ));
        p.goTo( this.pageTitle,this.getURL( request ) );
    }
    
    
    public String getURL(HttpServletRequest request) {
        return  request.getRequestURI().substring(request.getContextPath().length());
    }
    
 
    public void save(HttpServletRequest request , String name , Object value) {
        getSessionManager().save( request, name, value );
    }
    
    public Object get(HttpServletRequest request , String name) {
        return getSessionManager().get( request, name );
    }
    
    public void remove(HttpServletRequest request , String name) {
        getSessionManager().remove( request, name );
    }
    
    public  Utilisateur getUtilisateur(HttpServletRequest request) {
        
        pageUser = (Utilisateur) get( request, Connexion.ATT_SESSION_USER );
        return pageUser;
    
    }
    
    
    public static Utilisateur getUtilisateur() throws Exception {
        if(pageUser==null) {
            
            throw new Exception( "Page user not initialized , with pg.getUtilisateur" );
        }
        System.out.println( "getUtilisateur appelé" );
        return pageUser;
    }
    
    
}
