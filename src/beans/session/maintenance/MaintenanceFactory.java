package beans.session.maintenance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.entities.maintenance.Maintenance;
import beans.entities.maintenance.niveaux.Niveau;
import beans.entities.pieces.Piece;
import beans.entities.regions.unites.Unite;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;
import beans.session.maintenance.niveaux.NiveauManager;
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

        Niveau n = new Niveau( Integer.parseInt( request.getParameter( "niveau" ) ) );
        m.setNiv( n );
        try {
            m.setStartDate( new SimpleDateFormat( "yyyy-MM-dd" ).parse( request.getParameter( "recruit" ) ) );
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        m.setUn( new Unite( "un1" ) );
        return m;
    }

    @Override
    public void validateChilds( Maintenance bean, BeanManager<Maintenance> beanM ) {
        // TODO Auto-generated method stub
    }

    public void validateChilds( Maintenance bean ) {
        if ( bean.getV() == null ) {
            this.addErreurs( "v", "Ce numero d'immatriculation n'appartient � aucun v�hicule" );
        }
        if ( bean.getPieces() != null ) {
            if ( bean.getNbP() != bean.getPieces().size() ) {
                this.addErreurs( "piece", "veuillze inserer les pieces de rechange en cliquant sur le bouton ajouter" );
            }
        }
    }

    @Override
    public void updateChange( Maintenance newB, Maintenance old ) {
        // TODO Auto-generated method stub

    }

    public boolean validateStartDate( MaintenanceManager em, Maintenance bean ) {
        Map<String, Object> fields = new HashMap();

        List<Maintenance> currentM = em.findCurrentMaintenace( bean );
        if ( currentM != null ) {
            System.out.println( "liste des maintenaces de ce vehicule est non null " + currentM.size() );
            if ( currentM.size() > 0 ) {
                // System.out.println("liste des maintenaces de ce vehicule est
                // non null " + currentM.size());
                this.addErreurs( "v", "Ce vehicule a d�j� une maintenance non termin�e" );
                return false;
            }

        }
        return true;
    }

    public boolean validateEndDate( Maintenance m ) {
        if ( m.getEndDate().compareTo( m.getStartDate() ) > 0 ) {

            System.out.println( "end date valide" );
            return true;
        } else {
            this.addErreurs( "startDate", "ce v�hicule a d�j� une maintenance non termin�" );
            return false;
        }

    }

    public boolean validateInsertion( Maintenance bean, MaintenanceManager em ) {
        if ( validate( bean ) && validateStartDate( em, bean ) ) {
            return true;
        }
        return false;
    }

    public void createPieces( HttpServletRequest request, Maintenance bean ) {

        List<Piece> pieces = new ArrayList();

        for ( int i = 0; i < bean.getNbP(); i++ ) {
            Piece p = new Piece( request.getParameter( Integer.toString( i ) ) );
            pieces.add( p );

        }

        bean.setPieces( pieces );

    }

}
