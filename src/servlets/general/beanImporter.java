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

import beans.entities.amdec.Instruction;
import beans.entities.regions.unites.Unite;
import beans.session.amdec.cause.CauseFactory;
import beans.session.amdec.defaillance.DefaillanceFactory;
import beans.session.amdec.effet.EffetFactory;
import beans.session.amdec.instruction.InstructionFactory;
import beans.session.drivers.DriverFactory;
import beans.session.general.BeanFactory;
import beans.session.general.GeneralManager;
import beans.session.general.page.PageGenerator;
import beans.session.maintenance.MaintenanceFactory;
import beans.session.pieces.PieceFactory;
import beans.session.regions.RegionFactory;
import beans.session.regions.unites.UniteFactory;
import beans.session.vehicules.VehiculeFactory;
import beans.session.vehicules.marques.MarqueFactory;
import beans.session.vehicules.marques.modeles.ModeleFactory;
import servlets.regions.regionsLists;

/**
 * Servlet implementation class beanImporter
 */
@WebServlet( "/Importer/*" )
@MultipartConfig( maxFileSize = 16177215 )
public class beanImporter extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private Map<String, Class<?>> classes;

    /**
     * @see HttpServlet#HttpServlet()
     * 
     * 
     */
    @EJB
    GeneralManager generalM;
    
    public beanImporter() {
        super();
        this.classes =  new LinkedHashMap<String, Class<?>>();
        /*Ajouter ici les classes a importer/exporter */
        classes.put( "Vehicules", VehiculeFactory.class );
        classes.put( "Marques", MarqueFactory.class );
        classes.put( "Modeles", ModeleFactory.class );
        classes.put( "Pieces", PieceFactory.class );
        classes.put( "Regions", RegionFactory.class );
        classes.put( "Untiés", UniteFactory.class  );
        classes.put("Conducteurs",DriverFactory.class);
        classes.put( "Causes", CauseFactory.class );
        classes.put("Défaillances" , DefaillanceFactory.class);
        classes.put( "Effets",EffetFactory.class );
        classes.put("Instruction",InstructionFactory.class);
        classes.put( "Maintenaces", MaintenanceFactory.class );
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        PageGenerator pg = new PageGenerator( "/WEB-INF/vues/importer/importer.jsp", "" );
        String id = (String) pg.getPathId( request );

        
        pg.setPageTitle( "Importer/Exporter : " + id  );

      
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
        String id = (String) pg.getPathId( request );

        pg.setPageTitle( "Importer/Exporter : " + id );

  

        if ( id != null ) {
            String s = request.getParameter( "class" );
            s = s.substring( s.lastIndexOf( ' ' ) + 1 );
           
            BeanFactory<?> beanF = BeanFactory.getClassFactory( s );
            
            System.out.println( "Get factory of : "+beanF.getClassName() );
            if(request.getParameter( "modele" )!=null) {
                List<String > ignore = beanF.getEntityFields().getListFields();
                
                if(request.getParameter( "choix-export" )=="modele") {
                    Workbook wk = beanF.obtenirModeleExcel(response, ignore.toArray( new String[ignore.size()] ) );
                    pg.writeWorkBook( wk,id, response );    
                }else {
                   List<?> beans  = generalM.lister( beanF.getBeanClass() );
                   System.out.println( "Obtenir les données avec  "+beans.size()+" lignes" );
                   Workbook wk = beanF.exportExcel(beans);
                   pg.writeWorkBook( wk, beanF.getClassName(), response );
                }
                
            }else {
                
                System.out.println( "File readed : "+  request.getPart( "file" ).getSubmittedFileName());
                System.out.println( "File type : "+  request.getPart( "file" ).getContentType());
                System.out.println( "File size :"+request.getPart( "file" ).getSize());
                String  name = request.getPart( "file" ).getSubmittedFileName();
                List<Map<String,ArrayList<String>>> errs = null;
                
                if(!name.isEmpty()) {
                   
                    List<?> beans  = beanF.importExcel((BufferedInputStream) request.getPart( "file" ).getInputStream());
                    
                    errs = beanF.insertAll(  beans, generalM );
                    
                }else {
                    request.setAttribute( "message", "Le fichier est vide");
                }
                
                
                request.setAttribute( "classes", classes );
                request.setAttribute( "erreurs",  errs);
                request.setAttribute( "names", beanF.getEntityFields().names() );
                pg.generate( getServletContext(), request, response );
            }
           
             
        }
        
    }

}
