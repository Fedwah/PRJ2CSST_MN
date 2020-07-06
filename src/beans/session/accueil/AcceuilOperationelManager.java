package beans.session.accueil;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import beans.entities.vehicules.EtatsVehicule;
import beans.session.general.GeneralManager;
import beans.session.general.page.PageGenerator;

@Stateless
public class AcceuilOperationelManager extends GeneralManager {
    private static final String SQL_COUNT_VEHICULE   = " SELECT count(*) from public.vehicule v where v.unite_codeun = :codeun";

    private static final String SQL_PURCENTAGE_LIBRE = "SELECT  \r\n" + 
            "            case\r\n" + 
            "                 when t2.nb != 0 then round((t1.nb_libre\\:\\:decimal / t2.nb)*100,2)\r\n" + 
            "                 else 0 \r\n" + 
            "                   end pourcentage\r\n" + 
            "FROM \r\n" + 
            "            (SELECT count(*) nb_libre from public.vehicule v where v.unite_codeun = :codeun and v.etat = :etat) t1,\r\n" + 
            "            \r\n" + 
            "            (SELECT count(*) nb from public.vehicule v where v.unite_codeun = :codeun ) t2;";

    
    private static final String SQL_COUNT_DRIVER   = " SELECT count(*) from public.driver d where d.unite_codeun = :codeun";

    private static final String SQL_PURCENTAGE_LIBRE_DRIVER = "select \r\n" + 
            "    case \r\n" + 
            "     when(nb!=0) then round(((nb-count(*))\\:\\:decimal/nb)*100) \r\n" + 
            "     else 0\r\n" + 
            "    end nb\r\n" + 
            "from (select count(*) nb from public.driver) t1,public.affectationconducteur aff\r\n" + 
            "join public.driver d  on aff.driver_iddriver = d.iddriver \r\n" + 
            "where aff.enddate is NULL and d.unite_codeun = :codeun "+
            "group by nb";
    
    private static final String SQL_ETAT_VEHICULE ="select v.etat,count (*) nb from vehicule v  "
            + "where v.unite_codeun = :codeun "
            + "group by v.etat";  
    
    private static final String SQL_KM_MODELE = "select mo.titre,t1.total \r\n" + 
            "from (select v.modele_id,sum(km) as total from vehicule v \r\n" + 
            "where  v.unite_codeun = :codeun group by v.modele_id ) t1 \r\n" + 
            "join modele mo on  mo.id = t1.modele_id";

    private static final String SQL_NB_PANNE_MODELE = "select mo.titre,t1.nb from\r\n" + 
            "(select  v.modele_id modId,count(*) nb \r\n" + 
            "from maintenance m , vehicule v \r\n" + 
            "where m.v_matricule_interne =v.matricule_interne and v.unite_codeun = :codeun \r\n" + 
            "group by v.modele_id) t1 join modele mo on t1.modId = mo.id";
    
    private static final String SQL_NB_MAINTENANCE = "select count(*) from maintenance m \r\n"
            + 
            " where m.enddate is null and m.un_codeun = :codeun ";
    
    public AcceuilOperationelManager() {
        // TODO Auto-generated constructor stub
    }
    

    public Integer countVehiculeUn() {
        Query q = this.getEntityManger().createNativeQuery( SQL_COUNT_VEHICULE );
        try {
            q.setParameter( "codeun", PageGenerator.getUtilisateur().getCodeun() );
            return ( (BigInteger) q.getSingleResult() ).intValue();
        } catch ( Exception e ) {
           
            return -1;
        }

    }

    public Double purcentageVehiculeUN( EtatsVehicule etat ) {
        Query q = this.getEntityManger().createNativeQuery( SQL_PURCENTAGE_LIBRE );
        try {
            q.setParameter( "codeun", PageGenerator.getUtilisateur().getCodeun() );
            q.setParameter( "etat", etat.ordinal() );
            return ( (BigDecimal) q.getSingleResult() ).doubleValue();
        } catch ( Exception e ) {
            e.printStackTrace();
            return -1.0;
        }

    }
    
    
   
    public Integer countDriverUN() {
        Query q = this.getEntityManger().createNativeQuery( SQL_COUNT_DRIVER );
        try {
            q.setParameter( "codeun", PageGenerator.getUtilisateur().getCodeun() );
            return ( (BigInteger) q.getSingleResult() ).intValue();
        } catch ( Exception e ) {
           
            return -1;
        }

    }

    public Double purcentageDriverUN() {
        Query q = this.getEntityManger().createNativeQuery( SQL_PURCENTAGE_LIBRE_DRIVER );
        try {
            q.setParameter( "codeun", PageGenerator.getUtilisateur().getCodeun() );
       
            return ( (BigDecimal) q.getSingleResult() ).doubleValue();
        } catch ( Exception e ) {
            e.printStackTrace();
            return -1.0;
        }

    }
    
    public List<?> etatVehiculesUN(){
        Query q = this.getEntityManger().createNativeQuery(SQL_ETAT_VEHICULE);
        try {
            q.setParameter( "codeun", PageGenerator.getUtilisateur().getCodeun() );
            return q.getResultList();
        }catch (Exception e) {
            
            return new ArrayList<Object>();
        }
        
    }
    
    public List<?> kmModeleUN(){
        Query q = this.getEntityManger().createNativeQuery(SQL_KM_MODELE);
        try {
            q.setParameter( "codeun", PageGenerator.getUtilisateur().getCodeun() );
            return q.getResultList();
        }catch (Exception e) {
            
            return new ArrayList<Object>();
        }
        
    }
    
    public List<?> nbPanneModeleUN(){
        Query q = this.getEntityManger().createNativeQuery(SQL_NB_PANNE_MODELE);
        try {
            q.setParameter( "codeun", PageGenerator.getUtilisateur().getCodeun() );
            return q.getResultList();
        }catch (Exception e) {
            
            return new ArrayList<Object>();
        }
    }
    
    public Integer NbMaintenanceUN( ) {
        Query q = this.getEntityManger().createNativeQuery( SQL_NB_MAINTENANCE );
        try {
            q.setParameter( "codeun", PageGenerator.getUtilisateur().getCodeun() );
           
            return ( (BigInteger) q.getSingleResult() ).intValue();
        } catch ( Exception e ) {
            e.printStackTrace();
            return -1;
        }

    }

}
