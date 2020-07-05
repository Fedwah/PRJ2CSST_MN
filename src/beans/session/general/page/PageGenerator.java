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
import beans.session.general.page.pather.Page;
import beans.session.general.page.pather.PageState;
import beans.session.general.page.pather.Pather;
import servlets.Utilisateur.Connexion;

public class PageGenerator {

    private static final String SESSION_REQUEST = "request";
    private static final String ATT_VUE         = "vue";
    private static final String ATT_TITLE       = "title";
    private static final String PAGE_WRAPPER    = "/WEB-INF/index.jsp";

    private String              pageWrapperJSP;
    private String              vueJSP;
    private String              pageTitle;
    private String              redirectURL;

    private SessionManager      sessionManager;
    public static Utilisateur   pageUser;

    public PageGenerator() {
        super();
        this.sessionManager = new SessionManager();

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

    public PageGenerator( String pageWrapperJSP, String vueJSP, String pageTitle, String redirectURL ) {
        this();
        this.pageWrapperJSP = pageWrapperJSP;
        this.vueJSP = vueJSP;
        this.pageTitle = pageTitle;
        this.redirectURL = redirectURL;
    }

    public PageGenerator( String pageWrapperJSP, String vueJSP, String pageTitle, String redirectURL,
            String pageError ) {
        this();
        this.pageWrapperJSP = pageWrapperJSP;
        this.vueJSP = vueJSP;
        this.pageTitle = pageTitle;
        this.redirectURL = redirectURL;

    }

    public String getPageWrapperJSP() {
        return pageWrapperJSP;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private RequestDispatcher getRequestDispatcher( ServletContext contexte, HttpServletRequest request,
            HttpServletResponse response, PageState ps ) {

        Page page = this.pather( request );

        if ( ps != null ) {
            page.setPageState( ps );
            Pather.getPather( request ).upadateLastPage( page );
        }

        request.setAttribute( ATT_TITLE, this.pageTitle );
        request.setAttribute( ATT_VUE, this.vueJSP );
        return contexte.getRequestDispatcher( this.pageWrapperJSP );

    }

    public void generate( ServletContext contexte, HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        this.generate( contexte, request, response, false );
    }

    public void generate( ServletContext contexte, HttpServletRequest request, HttpServletResponse response,
            boolean root ) throws ServletException, IOException {
        if ( root ) {
            this.clearPath( request );
        }
        this.getRequestDispatcher( contexte, request, response, PageState.none() ).forward( request, response );
    }

    public void generate( ServletContext contexte, HttpServletRequest request, HttpServletResponse response,
            PageState ps ) throws ServletException, IOException {

        this.getRequestDispatcher( contexte, request, response, ps );
    }

    public void generateSuccess( ServletContext contexte, HttpServletRequest request, HttpServletResponse response,
            String title, String message ) throws ServletException, IOException {
        this.generate( contexte, request, response, PageState.succes( title, message ) );
    }

    public void generateError( ServletContext contexte, HttpServletRequest request, HttpServletResponse response,
            String title, String message ) throws ServletException, IOException {
        this.generate( contexte, request, response, PageState.error( title, message ) );
    }

    public void generateJSON( HttpServletResponse response, List<JSONObject> objects ) {
        JSONArray arr = new JSONArray( objects );
        System.out.println( "Generate JSON : " + arr );
        response.reset();
        response.setContentType( "application/json;charset=utf-8" );
        try {
            PrintWriter out = response.getWriter();

            out.print( arr );
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            System.out.println( "Erreur generate JSON " + e.getMessage() );
        }

    }

    public void generateJSON( HttpServletResponse response, Boolean success, String message ) {

        JSONObject obj = new JSONObject();
        obj.put( "success", success );
        obj.put( "message", message );
        response.reset();
        response.setContentType( "application/json;charset=utf-8" );
        try {
            PrintWriter out = response.getWriter();

            out.print( obj );
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
    
    private void redirect( ServletContext contexte, HttpServletRequest request, HttpServletResponse response, Page page,
            PageState ps ) throws IOException {
        
        System.out.println( "Redirect to "+page.getPath()+" = "+page.getLink() );
        
        if ( ps != null ) {
            page.setPageState( ps );
            Pather.getPather( request ).forward( page );
        }
        
        response.sendRedirect( contexte.getContextPath() + page.getLink() );
    }

   
    public void redirectBack( ServletContext contexte, HttpServletRequest request, HttpServletResponse response )
            throws IOException {
        this.redirectBack( contexte, request, response, null );
    }

    public void redirectBack( ServletContext contexte, HttpServletRequest request, HttpServletResponse response,
            PageState ps )
            throws IOException {

        Page page = Pather.getPather( request ).goBack(); //current
        System.out.println( "Current page :"+page.getPath()+" = "+page.getLink() );
        if ( page != null ) {
            page = Pather.getPather( request ).goBack(); //back
            System.out.println( "Back page :"+page.getPath()+" = "+page.getLink() );
        }

        this.redirect( contexte, request, response, page,ps );
    }

    public void redirectCurrent( ServletContext contexte, HttpServletRequest request, HttpServletResponse response,
            PageState ps )
            throws IOException {

        Page page = Pather.getPather( request ).goBack();
       
        this.redirect( contexte, request, response,page,ps);

    }

    
    public void redirectBackSuccess( ServletContext contexte, HttpServletRequest request, HttpServletResponse response,
            String title, String message ) throws ServletException, IOException {

        this.redirectBack( contexte, request, response, PageState.succes( title, message ) );

    }

    public void redirectBackErreur( ServletContext contexte, HttpServletRequest request, HttpServletResponse response,
            String title, String message ) throws ServletException, IOException {

        this.redirectBack( contexte, request, response, PageState.error( title, message ) );

    }

    public void redirectCurrentSuccess( ServletContext contexte, HttpServletRequest request,
            HttpServletResponse response,
            String title, String message ) throws IOException {
        
        
       
        this.redirectCurrent( contexte, request, response, PageState.succes( title, message ) );
    }

    public void redirectCurrentError( ServletContext contexte, HttpServletRequest request, HttpServletResponse response,
            String title, String message ) throws IOException {
      
        this.redirectCurrent( contexte, request, response, PageState.error( title, message ) );
    }

    
    public Object getPathId( HttpServletRequest request ) {
        String id = "";
        if ( request.getPathInfo() != null ) {
            id = request.getPathInfo().substring( 1 );
            id = cleanText( id );

            try {
                Integer i = Integer.valueOf( id );
                return i;
            } catch ( Exception e ) {
                return id;
            }
        }

        return null;

    }

    public String[] getPathIds( HttpServletRequest request ) {
        String[] out = null;
        if ( request.getPathInfo() != null ) {
            out = request.getPathInfo().substring( 1 ).split( "/" );
            for ( int i = 0; i < out.length; i++ ) {
                out[i] = cleanText( out[i] );
            }
            return out;
        } else {
            return null;
        }

    }

    private String cleanText( String text ) {
        return text.replaceAll( "^\"|\"$", "" ).trim();
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
            System.out.println( "Exportation erreur dans PG :" + e1.getMessage() );
            ;
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
        this.sessionManager.save( request, SESSION_REQUEST, request );
    }

    public HttpServletRequest getSavedReuest( HttpServletRequest request ) {
        HttpServletRequest r = (HttpServletRequest) this.sessionManager.get( request, SESSION_REQUEST );
        this.sessionManager.remove( request, SESSION_REQUEST );
        return r;

    }

    public void clearPath( HttpServletRequest request ) {
        Pather.getPather( request ).clear();
        // s.removeAttribute(ATT_PATH );
    }

    private Page pather( HttpServletRequest request ) {
        Pather p = Pather.getPather( request );
        System.out.println( "GO TO :" + this.pageTitle + " = " + this.getURL( request ) );
        return p.goTo( this.pageTitle, this.getURL( request ) );

    }

    public String getURL( HttpServletRequest request ) {
        return request.getRequestURI().substring( request.getContextPath().length() );
    }

    public void save( HttpServletRequest request, String name, Object value ) {
        getSessionManager().save( request, name, value );
    }

    public Object get( HttpServletRequest request, String name ) {
        return getSessionManager().get( request, name );
    }

    public void remove( HttpServletRequest request, String name ) {
        getSessionManager().remove( request, name );
    }

    public Utilisateur getUtilisateur( HttpServletRequest request ) {

        pageUser = (Utilisateur) get( request, Connexion.ATT_SESSION_USER );
        return pageUser;

    }

    public static Utilisateur getUtilisateur() throws Exception {
        if ( pageUser == null ) {

            throw new Exception( "Page user not initialized , with pg.getUtilisateur" );
        }
        //System.out.println( "getUtilisateur appelé" );
        return pageUser;
    }

}
