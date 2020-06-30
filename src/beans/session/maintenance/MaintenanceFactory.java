package beans.session.maintenance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.wildfly.security.manager.action.GetProtectionDomainAction;

import beans.entities.maintenance.Maintenance;
import beans.entities.maintenance.niveaux.Niveau;
import beans.entities.pieces.Piece;
import beans.entities.regions.unites.Unite;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

import beans.session.pieces.PieceManager;
import beans.session.vehicules.VehiculesManager;

public class MaintenanceFactory extends BeanFactory<Maintenance> {

    private Map<String, String> indexPiece;

    public MaintenanceFactory() {

    }

    public MaintenanceFactory( Class<Maintenance> beanClass ) {
        super( beanClass );

    }

    @Override
    public Maintenance create( HttpServletRequest request ) {
        Maintenance m = new Maintenance();

        Vehicule v = new Vehicule( request.getParameter( "matricule" ) );
        m.setV( v );
        m.setUn( v.getUnite() );
        try {
            m.setNbP( Integer.parseInt( request.getParameter( "nbP" ) ) );
        } catch ( Exception e ) {
            m.setNbP( 0 );
        }

       // Niveau n = new Niveau( Integer.parseInt( request.getParameter( "niveau" ) ) );
        //m.setNiv( n );
        try {
            m.setStartDate( new SimpleDateFormat( "yyyy-MM-dd" ).parse( request.getParameter( "recruit" ) ) );
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        m.setUn( new Unite( "un1" ) );
        return m;
    }
    
    public Maintenance create( HttpServletRequest request, Maintenance bean ) {
        Maintenance m = new Maintenance();

        m.setV( bean.getV() ); // vehicule
        m.setUn(bean.getUn()); // unité
        try {
            m.setNbP( Integer.parseInt( request.getParameter( "nbP" ) ) ); // nombre de piece de rechange
        } catch ( Exception e ) {
            m.setNbP( 0 );
        }

       // Niveau n = new Niveau( Integer.parseInt( request.getParameter( "niveau" ) ) );
       // m.setNiv( n ); // niveau de maintenance
        m.setStartDate(bean.getStartDate()); // date debut
        try {
            m.setEndDate( new SimpleDateFormat( "yyyy-MM-dd" ).parse( request.getParameter( "dateFin" ) ) ); // date de fin 
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public void validateChilds( Maintenance bean, BeanManager<Maintenance> beanM ) {
    	 if ( bean.getV() == null ) {
             this.addErreurs( "v", "Ce numero d'immatriculation n'appartient ï¿½ aucun vï¿½hicule" );
         }
         if ( bean.getPieces() != null ) {
             if ( bean.getNbP() != bean.getPieces().size() ) {
                 this.addErreurs( "piece", "veuillez inserer les pieces de rechange" );
             }
         }
    }


    @Override
    public void updateChange( Maintenance newB, Maintenance old ) {
        old.setEndDate(newB.getEndDate());
        old.setNbP(newB.getNbP());
        old.setNiv(newB.getNiv());
        old.setPieces(newB.getPieces());

    }

    public boolean validateStartDate( MaintenanceManager em, Maintenance bean ) {
        Map<String, Object> fields = new HashMap();
        boolean valide = true;
        List<Maintenance> currentM = em.findCurrentMaintenace( bean );
        if ( currentM != null ) {
            System.out.println( "liste des maintenaces de ce vehicule est non null " + currentM.size() );
            if ( currentM.size() > 0 ) {
                // System.out.println("liste des maintenaces de ce vehicule est
                // non null " + currentM.size());
                this.addErreurs( "v", "Ce vehicule a déjà une maintenance non terminé" );
               valide = valide && false;
            }

        }
        if(bean.getStartDate().compareTo(new Date())< 0)
        {
        	valide = valide && false;
        	this.addErreurs( "startDate", "Vous ne pouvez pas créer des maintenaces passées" );
        }
        return valide;
    }

    public boolean validateEndDate( Maintenance m ) {
        if ( m.getEndDate().compareTo( m.getStartDate() ) >= 0 ) {

            System.out.println( "end date valide" );
            return true;
        } else {
            this.addErreurs( "endDate", "date de fin est inferieure à la date début" );
            return false;
        }

    }

    public boolean validateInsertion( Maintenance bean, MaintenanceManager em ) {
        if ( this.validate( bean ) && this.validateStartDate( em, bean ) ) {
            return true;
        }
        return false;
    }
    
    public boolean validateEdit( Maintenance bean ) {
        if ( this.validate( bean ) && this.validateEndDate( bean ) ) {
            return true;
        }
        return false;
    }

    public void createPieces( HttpServletRequest request, Maintenance bean ) 
    {
        List<Piece> pieces = new ArrayList();
        
        for ( int i = 1; i <= bean.getNbP(); i++ ) 
        {
        	System.out.println("valeur de " + i + " est " + request.getParameter( Integer.toString( i )));
        	if(request.getParameter( Integer.toString( i ) ) != null)
        	{
        		Piece p = new Piece( request.getParameter( Integer.toString( i ) ) );
                pieces.add( p );
        	}
        }

        bean.setPieces( pieces );

    }

}
