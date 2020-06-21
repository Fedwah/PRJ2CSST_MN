package servlets.general;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.general.Image;
import beans.session.ImageManager;

/**
 * Servlet implementation class ImageGenerator
 */
@WebServlet( "/Images/*" )
public class ImageGenerator extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ImageManager      im;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageGenerator() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String imgName = "";
        Image img = null;
           
        if ( request.getPathInfo() == null ) {
            response.sendError( HttpServletResponse.SC_NOT_FOUND ); // 404.
            return;
        }else {
            imgName = request.getPathInfo().substring( 1 );
            img = im.trouver( imgName );
        }
        
        if(img == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"Image "+imgName+" not found" );
            return;
        }

       
        System.out.println( "IMG name: " + imgName );
        if ( imgName != null ) {
            response.reset();
            response.setContentType( getServletContext().getMimeType( imgName ) );
            response.setContentLength( img.getBin().length );
            //System.out.println( "IMG found: "+img.getTitre() );
            //System.out.println( "IMG type: " + getServletContext().getMimeType( imgName ) );
            //System.out.println( "IMG Enocded length :" + img.getBin().length);
            //System.out.println( "IMG Deccoded length : " +Base64.getDecoder().decode( img.getBinary() ).length);
            response.getOutputStream().write(img.getBin());
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

}
