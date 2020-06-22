package beans.session.general.page;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.jboss.resteasy.spi.HttpRequest;
import org.wildfly.common.iteration.IntIterator;

public class PageGenerator {

    private static final String ATT_PATH = "path";
    private static final String ATT_REQUEST  = "request";
    private static final String ATT_VUE      = "vue";
    private static final String ATT_TITLE    = "title";
    private static final String PAGE_WRAPPER = "/WEB-INF/index.jsp";
    private static final String DEFAUTL_ROOT = "/";
    private String              pageWrapperJSP;
    private String              vueJSP;
    private String              pageTitle;
    private String              redirectURL;
    

    public PageGenerator() {
        // TODO Auto-generated constructor stub
    }
    
    public PageGenerator( String redirectURL ) {
        super();
        this.redirectURL = redirectURL;
    }

    public PageGenerator( String vueJSP, String pageTitle, String redirectURL ) {
        super();
        this.pageWrapperJSP = PAGE_WRAPPER;
        this.vueJSP = vueJSP;
        this.pageTitle = pageTitle;
        this.redirectURL = redirectURL;
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

    private RequestDispatcher getRequestDispatcher( ServletContext contexte, HttpServletRequest request,
            HttpServletResponse response ) {
        request.setAttribute( ATT_TITLE, this.pageTitle );
        request.setAttribute( ATT_VUE, this.vueJSP );

        return contexte.getRequestDispatcher( this.pageWrapperJSP );
    }

    public void generate( ServletContext contexte, HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        this.pather( request );
        this.getRequestDispatcher( contexte, request, response ).forward( request, response );
    }
    
    public void generate( ServletContext contexte, HttpServletRequest request, HttpServletResponse response ,boolean root) throws ServletException, IOException {
        if(root) {
            this.clearPath( request );
        }
        this.generate( contexte, request, response );
    }

    public void redirect( ServletContext contexte, HttpServletRequest request, HttpServletResponse response )
            throws IOException {
        if ( redirectURL != null ) {
            response.sendRedirect( contexte.getContextPath() + redirectURL );
        } 
    }
    
    public void redirectBack( ServletContext contexte, HttpServletRequest request, HttpServletResponse response )
            throws IOException {
        Page page  = this.getPather( request ).goBack();
        response.sendRedirect( contexte.getContextPath() + page.getLink() );
    }
    

    public String getPathId( HttpServletRequest request ) {
        String id = "";
        if ( request.getPathInfo() != null ) {
            id = request.getPathInfo().substring( 1 );
        }
        return id;
    }

    public String[] getPathIds( HttpServletRequest request ) {
        if ( request.getPathInfo() != null ) {
            return request.getPathInfo().substring( 1 ).split( "/" );
        } else {
            return null;
        }

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
        HttpSession s = request.getSession();
        s.setAttribute( ATT_REQUEST, request );
    }

    public HttpServletRequest getSavedReuest( HttpSession session ) {
        HttpServletRequest r = (HttpServletRequest) session.getAttribute( ATT_REQUEST );
        session.removeAttribute( ATT_REQUEST );

        return r;

    }

    private Pather initPath( HttpSession s , String root) {
        Pather path = new Pather( root );
        s.setAttribute( ATT_PATH, path );
        return path;
    }
    
    
    public void clearPath( HttpServletRequest request) {
        getPather( request ).clear();
        //s.removeAttribute(ATT_PATH );
    }
    
    private Pather getPather(HttpServletRequest request) {
        HttpSession session = request.getSession( false );
        if (session != null) {
            return (Pather) session.getAttribute( ATT_PATH );
        } else {
            // no session
            return initPath( request.getSession(), DEFAUTL_ROOT );
        }
    }
    
    private void pather(HttpServletRequest request) {
        Pather p  = getPather( request );
        System.out.println( "GO TO :"+this.pageTitle+" = "+this.getPath( request ));
        p.goTo( this.pageTitle,this.getPath( request ) );
    }
    
    
    public String getPath(HttpServletRequest request) {
        return  request.getRequestURI().substring(request.getContextPath().length());
    }
    

}
