package beans.session.accueil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import beans.entities.vehicules.EtatsVehicule;
import beans.session.general.GeneralManager;


@Stateless
public class AccueilCentralManager extends GeneralManager {

    private static final String SQL_COUNT_VEHICULE   = " SELECT count(*) from public.vehicule v ";
           
    
    private static final String SQL_PURCENTAGE_LIBRE = "SELECT  \r\n" + 
            "            case\r\n" + 
            "                 when t2.nb != 0 then round((t1.nb_libre\\:\\:decimal / t2.nb)*100,2)\r\n" + 
            "                 else 0 \r\n" + 
            "                   end pourcentage\r\n" + 
            "FROM \r\n" + 
            "            (SELECT count(*) nb_libre from public.vehicule v "
            + " where v.etat = :etat) t1,\r\n" + 
            "            \r\n" + 
            "            (SELECT count(*) nb from public.vehicule v ) t2;";
    
    private static final String SQL_ETATS_VEHICULES ="select v.etat,count (*) nb from vehicule v  "
            + "group by v.etat";
    
    private static final String SQL_MOY_AGE_MODELE = "select mo.titre, moy from\r\n" + 
            "\r\n" + 
            "(select t1.modele_id,round(avg(nb)\\:\\:numeric,2) moy  from \r\n" + 
            "(select *,extract(year from CURRENT_DATE) - extract(year from v.date_achat) as nb   \r\n" + 
            "from vehicule v  \r\n" + 
            ")t1\r\n" + 
            "group by t1.modele_id) t2  join modele mo on mo.id = t2.modele_id";
    
    private static final String SQL_MOY_AGE_UNITE = "select * from\r\n" + 
            "\r\n" + 
            "(select t1.region_codereg,round(avg(nb)\\:\\:numeric,2) moy  from \r\n" + 
            "(select *,extract(year from CURRENT_DATE) - extract(year from v.date_achat) as nb   \r\n" + 
            "from vehicule v join unite u on u.codeun = v.unite_codeun\r\n" + 
            ")t1\r\n" + 
            "group by t1.region_codereg) t2 ";
    
    private static final String SQL_ETATS_UNITES = "select u.region_codereg,v.etat, count(*) from vehicule v\r\n" + 
            "join unite u on u.codeun = v.unite_codeun\r\n" + 
            "group by u.region_codereg , v.etat";
    
    private static final String SQL_NB_PANNE_MODELE = "select mo.titre,t1.nb from\r\n" + 
            "(select  v.modele_id modId,count(*) nb \r\n" + 
            "from maintenance m , vehicule v \r\n" + 
            " join unite u on u.codeun = v.unite_codeun"
            + " where m.v_matricule_interne = v.matricule_interne  \r\n" + 
            "group by v.modele_id) t1 join modele mo on t1.modId = mo.id";
    
    private static final String SQL_NB_PANNE_UNITE =  
            "select  u.region_codereg ,count(*) nb \r\n" + 
            "from maintenance m , vehicule v \r\n" + 
            " join unite u on u.codeun = v.unite_codeun"
            + " where m.v_matricule_interne = v.matricule_interne \r\n" + 
            "group by  u.region_codereg ";
    
    private static final String SQL_NB_MAINTENANCE = "select count(*) from maintenance \r\n"
            + " join unite u on u.codeun = maintenance.un_codeun " + 
            " where enddate is null ";
    
    private static final String SQL_BESOIN_PIECE ="select i.piece_refrence,count(*) from maintenance_instruction mi , maintenance ,instruction i , unite u \r\n"
            +
            "where i.id = mi.instructions_id and u.codeun = maintenance.un_codeun \r\n" + 
            "and maintenance.idmaintenance = mi.maintenance_idmaintenance\r\n" + 
            "and maintenance.enddate is null\r\n" + 
            "group by i.piece_refrence";
    
    private static final String SQL_NB_PIECE = "select count(*) from maintenance_instruction mi , maintenance  ,instruction i ,unite u \r\n"+
            "where i.id = mi.instructions_id  and u.codeun = maintenance.un_codeun \r\n" + 
            "and maintenance.idmaintenance = mi.maintenance_idmaintenance\r\n" + 
            "and maintenance.enddate is null\r\n" ;
    
    
    public AccueilCentralManager() {
        // TODO Auto-generated constructor stub
    }
    
    public Integer countVehicule() {
        Query q = this.getEntityManger().createNativeQuery( SQL_COUNT_VEHICULE );
        try {
           
            return ( (BigInteger) q.getSingleResult() ).intValue();
        } catch ( Exception e ) {
           
            return -1;
        }

    }
    
    public List<?> moyAgeModeles(  ) {
        Query q = this.getEntityManger().createNativeQuery( SQL_MOY_AGE_MODELE );
        try {
           
           
            return  q.getResultList();
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }

    }
    
    public List<?> etatVehicules(){
        Query q = this.getEntityManger().createNativeQuery(SQL_ETATS_VEHICULES);
        try {
          
            return q.getResultList();
        }catch (Exception e) {
            
            return new ArrayList<Object>();
        }
        
    }
    public List<?> moyAgeRegions(  ) {
        Query q = this.getEntityManger().createNativeQuery( SQL_MOY_AGE_UNITE );
        try {
            
           
            return  q.getResultList();
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }

    }
    
    public List<?> etatsRegions(  ) {
        Query q = this.getEntityManger().createNativeQuery( SQL_ETATS_UNITES );
        try {
            
           
            return  q.getResultList();
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }

    }
    public Double purcentageVehicule( EtatsVehicule etat ) {
        Query q = this.getEntityManger().createNativeQuery( SQL_PURCENTAGE_LIBRE );
        try {
            
            q.setParameter( "etat", etat.ordinal() );
            return ( (BigDecimal) q.getSingleResult() ).doubleValue();
        } catch ( Exception e ) {
            e.printStackTrace();
            return -1.0;
        }

    }
    
    public Integer NbMaintenance( ) {
        Query q = this.getEntityManger().createNativeQuery( SQL_NB_MAINTENANCE );
        try {
            
           
            return ( (BigInteger) q.getSingleResult() ).intValue();
        } catch ( Exception e ) {
            e.printStackTrace();
            return -1;
        }

    }
    
    public Integer NbPiece( ) {
        Query q = this.getEntityManger().createNativeQuery( SQL_NB_PIECE );
        try {
            
           
            return ( (BigInteger) q.getSingleResult() ).intValue();
        } catch ( Exception e ) {
            e.printStackTrace();
            return -1;
        }

    }
    
    public List<?> nbPanneModele(){
        Query q = this.getEntityManger().createNativeQuery(SQL_NB_PANNE_MODELE);
        try {
            
            return q.getResultList();
        }catch (Exception e) {
            
            return new ArrayList<Object>();
        }
    }
    
    public List<?> nbPanne(){
        Query q = this.getEntityManger().createNativeQuery(SQL_NB_PANNE_UNITE);
        try {
            
            return q.getResultList();
        }catch (Exception e) {
            
            return new ArrayList<Object>();
        }
    }
    
    public List<?> besoinPiece(){
        Query q = this.getEntityManger().createNativeQuery(SQL_BESOIN_PIECE);
        try {
          
            return q.getResultList();
        }catch (Exception e) {
            
            return new ArrayList<Object>();
        }
    }

}
