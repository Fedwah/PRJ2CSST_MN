package servlets.vehicules.marque.modele;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
import beans.session.general.page.PageGenerator;
import beans.session.vehicules.marques.MarqueManager;
import beans.session.vehicules.marques.modeles.ModeleFactory;
import beans.session.vehicules.marques.modeles.ModeleManager;

/**
 * Servlet implementation class CreationEtListeModele
 */
@WebServlet("/Marques/Modeles/*")
public class CreationEtListeModele extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ATT_MODELES        = "modeles";
    private static final String ATT_NEW_ETAT     = "new_modele";
    private static final String ATT_ERREURS      = "erreurs";
    
   
    private static final String TITRE_VUE        = "Liste des modeles de ";
       
    
    @EJB
    ModeleManager modM;
    
    @EJB
    MarqueManager marM;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationEtListeModele() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    PageGenerator pg = null;
	    String id ="";
	    Marque marque = null;
	    
	    if ( request.getPathInfo() != null ) {
            id = request.getPathInfo().substring( 1 );
        }
	    
	    pg = new PageGenerator( ModeleFactory.VUE_LIST_FORM,TITRE_VUE+id,ModeleFactory.DEFAULT_REDIRECT_URL);
	    
	    if(id!="") {
	        marque = marM.trouver(id);
	    }
	   
	    if(marque!=null) {
	        request.setAttribute( ATT_MODELES, marque.getModeles() );
	        pg.generate( getServletContext(), request, response );
	    }else {
	        pg.redirect( getServletContext(), request, response );
	    }
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    PageGenerator pg = null;
	    ModeleFactory modF = new ModeleFactory();
	    Marque marque =null;
	    Modele modele = modF.create( request );
	    String id ="";
	    
      
        
        
	    pg = new PageGenerator( ModeleFactory.VUE_LIST_FORM, TITRE_VUE+id);
        id = (String) pg.getPathId( request );
        pg.setPageTitle( TITRE_VUE+id );
        
    
        if(id!="") {
            marque = marM.trouver(id);
        }
       
        
        if(marque!=null) {
            System.out.println( "Modele lue : "+ modele.getTitre()+" et la marque : "+marque.getTitre());
            modele.setMarque( marque );
            
            if( modF.validate( modele )) {
                modM.ajouter( modele );
            }
            
            request.setAttribute( ATT_ERREURS, modF.getErreurs() );
            request.setAttribute( ATT_NEW_ETAT, modele );
            marque = marM.trouver(id); //Mettre a jour marque 
            request.setAttribute( ATT_MODELES,marque.getModeles());
        }
	   
	    
        pg.generate( getServletContext(), request, response );
	}

}
