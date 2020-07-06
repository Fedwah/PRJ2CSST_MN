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
    
    private static final String SQL_ETATS_VEHICULES ="select v.etat,count (*) nb from vehicule v  "
            + "join unite on unite.codeUN = v.unite_codeun"
            + " where unite.region_codereg = :codereg "
            + "group by v.etat";
    
    private static final String SQL_MOY_AGE_MODELE = "select mo.titre, moy from\r\n" + 
            "\r\n" + 
            "(select t1.modele_id,round(avg(nb)\\:\\:numeric,2) moy  from \r\n" + 
            "(select *,extract(year from CURRENT_DATE) - extract(year from v.date_achat) as nb   \r\n" + 
            "from vehicule v join unite u on u.codeun = v.unite_codeun \r\n" + 
            " where  u.region_codereg = :codereg \r\n" + 
            ")t1\r\n" + 
            "group by t1.modele_id) t2  join modele mo on mo.id = t2.modele_id";
    
    private static final String SQL_MOY_AGE_UNITE = "select *from\r\n" + 
            "\r\n" + 
            "(select t1.unite_codeun,round(avg(nb)\\:\\:numeric,2) moy  from \r\n" + 
            "(select *,extract(year from CURRENT_DATE) - extract(year from v.date_achat) as nb   \r\n" + 
            "from vehicule v join unite u on u.codeun = v.unite_codeun\r\n" + 
            "where u.region_codereg = :codereg\r\n" + 
            ")t1\r\n" + 
            "group by t1.unite_codeun) t2 ";
    
    private static final String SQL_ETATS_UNITES = "select v.unite_codeun,v.etat, count(*) from vehicule v\r\n" + 
            "join unite u on u.codeun = v.unite_codeun\r\n" + 
            "where u.region_codereg = :codereg\r\n" + 
            "group by v.unite_codeun,v.etat";
    
    private static final String SQL_NB_PANNE_MODELE = "select mo.titre,t1.nb from\r\n" + 
            "(select  v.modele_id modId,count(*) nb \r\n" + 
            "from maintenance m , vehicule v \r\n" + 
            " join unite u on u.codeun = v.unite_codeun"
            + " where m.v_matricule_interne = v.matricule_interne and u.region_codereg = :codereg \r\n" + 
            "group by v.modele_id) t1 join modele mo on t1.modId = mo.id";
    private static final String SQL_NB_PANNE_UNITE =  
            "select  v.unite_codeun ,count(*) nb \r\n" + 
            "from maintenance m , vehicule v \r\n" + 
            " join unite u on u.codeun = v.unite_codeun"
            + " where m.v_matricule_interne = v.matricule_interne and u.region_codereg = :codereg \r\n" + 
            "group by v.unite_codeun ";
    
    private static final String SQL_NB_MAINTENANCE = "select count(*) from maintenance \r\n"
            + " join unite u on u.codeun = maintenance.un_codeun " + 
            " where enddate is null and u.region_codereg = :codereg";
    
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
    
    public List<?> moyAgeModeles(  ) {
        Query q = this.getEntityManger().createNativeQuery( SQL_MOY_AGE_MODELE );
        try {
            q.setParameter( "codereg", PageGenerator.getUtilisateur().getCodereg() );
           
            return  q.getResultList();
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }

    }
    
    public List<?> etatVehiculesReg(){
        Query q = this.getEntityManger().createNativeQuery(SQL_ETATS_VEHICULES);
        try {
            q.setParameter( "codereg", PageGenerator.getUtilisateur().getCodereg() );
            return q.getResultList();
        }catch (Exception e) {
            
            return new ArrayList<Object>();
        }
        
    }
    public List<?> moyAgeUnites(  ) {
        Query q = this.getEntityManger().createNativeQuery( SQL_MOY_AGE_UNITE );
        try {
            q.setParameter( "codereg", PageGenerator.getUtilisateur().getCodereg() );
           
            return  q.getResultList();
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }

    }
    
    public List<?> etatsUnites(  ) {
        Query q = this.getEntityManger().createNativeQuery( SQL_ETATS_UNITES );
        try {
            q.setParameter( "codereg", PageGenerator.getUtilisateur().getCodereg() );
           
            return  q.getResultList();
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
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
    
    public Integer NbMaintenanceReg( ) {
        Query q = this.getEntityManger().createNativeQuery( SQL_NB_MAINTENANCE );
        try {
            q.setParameter( "codereg", PageGenerator.getUtilisateur().getCodereg() );
           
            return ( (BigInteger) q.getSingleResult() ).intValue();
        } catch ( Exception e ) {
            e.printStackTrace();
            return -1;
        }

    }
    
    public List<?> nbPanneModeleReg(){
        Query q = this.getEntityManger().createNativeQuery(SQL_NB_PANNE_MODELE);
        try {
            q.setParameter( "codereg", PageGenerator.getUtilisateur().getCodereg() );
            return q.getResultList();
        }catch (Exception e) {
            
            return new ArrayList<Object>();
        }
    }
    
    public List<?> nbPanneUniteReg(){
        Query q = this.getEntityManger().createNativeQuery(SQL_NB_PANNE_UNITE);
        try {
            q.setParameter( "codereg", PageGenerator.getUtilisateur().getCodereg() );
            return q.getResultList();
        }catch (Exception e) {
            
            return new ArrayList<Object>();
        }
    }
}
