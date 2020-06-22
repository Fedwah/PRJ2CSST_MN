package servlets.general;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import beans.session.general.BeanFactory;
import beans.session.general.GeneralManager;
import beans.session.general.page.PageGenerator;
import beans.session.vehicules.VehiculeFactory;
import beans.session.vehicules.marques.MarqueFactory;
import beans.session.vehicules.marques.modeles.ModeleFactory;

/**
 * Servlet implementation class beanImporter
 */
@WebServlet( "/Importer/*" )
@MultipartConfig( maxFileSize = 16177215 )
public class beanImporter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     * 
     * 
     */
    @EJB
    GeneralManager generalM;
    
    public beanImporter() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        PageGenerator pg = new PageGenerator( "/WEB-INF/vues/importer/importer.jsp", "" );
        String id = pg.getPathId( request );

        
        pg.setPageTitle( "Importer/Exporter : " + id  );

        Map<String, Class<?>> classes = new LinkedHashMap<String, Class<?>>();
        classes.put( "Vehicules", VehiculeFactory.class );
        classes.put( "Marques", MarqueFactory.class );
        classes.put( "Modele", ModeleFactory.class );

        
        
        
        request.setAttribute( "classes", classes );
        pg.generate( getServletContext(), request, response );

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator( "/WEB-INF/vues/importer/importer.jsp", "" );
        String id = pg.getPathId( request );

        pg.setPageTitle( "Importer/Exporter : " + id );

        Map<String, Class<?>> classes = new LinkedHashMap<String, Class<?>>();
        classes.put( "Vehicules", VehiculeFactory.class );
        classes.put( "Marques", MarqueFactory.class );
        classes.put( "Modele", ModeleFactory.class );

        if ( id != null ) {
            String s = request.getParameter( "class" );
            s = s.substring( s.lastIndexOf( ' ' ) + 1 );
           
            BeanFactory<?> beanF = BeanFactory.getClassFactory( s );
            
          
            
            if(request.getParameter( "modele" )!=null) {
                List<String > ignore = beanF.getEntityFields().getListFields();
                
                if(request.getParameter( "choix-export" )=="modele") {
                    Workbook wk = beanF.obtenirModeleExcel(response, ignore.toArray( new String[ignore.size()] ) );
                    pg.writeWorkBook( wk,id, response );    
                }else {
                   List<?> beans  = generalM.lister( beanF.getClass() );
                   
                   Workbook wk = beanF.exportExcel(beans , ignore.toArray( new String[ignore.size()] )  );
                   pg.writeWorkBook( wk, beanF.getClassName(), response );
                }
                
            }else {
                
                System.out.println( "File readed : "+  request.getPart( "file" ).getSubmittedFileName());
                System.out.println( "File type : "+  request.getPart( "file" ).getContentType());
                System.out.println( "File size :"+request.getPart( "file" ).getSize());
                List<?> beans  = beanF.importExcel((BufferedInputStream) request.getPart( "file" ).getInputStream());
                
                List<Map<String,ArrayList<String>>> errs = beanF.insertAll(  beans, generalM );
                
                request.setAttribute( "classes", classes );
                request.setAttribute( "erreurs",  errs);
                request.setAttribute( "names", beanF.getEntityFields().names() );
                pg.generate( getServletContext(), request, response );
            }
           
             
        }
        
    }

}
