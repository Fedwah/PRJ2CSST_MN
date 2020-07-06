package beans.session.accueil;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.ejb.Stateless;
import javax.persistence.Query;

import beans.entities.vehicules.EtatsVehicule;
import beans.session.general.GeneralManager;
import beans.session.general.page.PageGenerator;

@Stateless
public class AccueilRegionalManager extends GeneralManager{
    private static final String SQL_COUNT_VEHICULE   = " SELECT count(*) from public.vehicule v"
            + " join unite on  unite.codeUN = v.unite_codeun where unite.region_codereg = :codereg";
    
    private static final String SQL_PURCENTAGE_LIBRE = "SELECT  \r\n" + 
            "            case\r\n" + 
            "                 when t2.nb != 0 then round((t1.nb_libre\\:\\:decimal / t2.nb)*100,2)\r\n" + 
            "                 else 0 \r\n" + 
            "                   end pourcentage\r\n" + 
            "FROM \r\n" + 
            "            (SELECT count(*) nb_libre from public.vehicule v join unite on  unite.codeUN = v.unite_codeun where unite.region_codereg = :codereg"
            + " and v.etat = :etat) t1,\r\n" + 
            "            \r\n" + 
            "            (SELECT count(*) nb from public.vehicule v join unite on  unite.codeUN = v.unite_codeun where unite.region_codereg = :codereg ) t2;";
    
    
    public AccueilRegionalManager() {
        // TODO Auto-generated constructor stub
    }

    public Integer countVehiculeReg() {
        Query q = this.getEntityManger().createNativeQuery( SQL_COUNT_VEHICULE );
        try {
            q.setParameter( "codereg", PageGenerator.getUtilisateur().getCodereg());
            return ( (BigInteger) q.getSingleResult() ).intValue();
        } catch ( Exception e ) {
           
            return -1;
        }

    }
    
    public Double purcentageVehiculeReg( EtatsVehicule etat ) {
        Query q = this.getEntityManger().createNativeQuery( SQL_PURCENTAGE_LIBRE );
        try {
            q.setParameter( "codereg", PageGenerator.getUtilisateur().getCodereg() );
            q.setParameter( "etat", etat.ordinal() );
            return ( (BigDecimal) q.getSingleResult() ).doubleValue();
        } catch ( Exception e ) {
            e.printStackTrace();
            return -1.0;
        }

    }
}
