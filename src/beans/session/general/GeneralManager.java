package beans.session.general;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import beans.entities.vehicules.EtatsVehicule;
import beans.session.general.page.PageGenerator;

@Stateless
public class GeneralManager extends BeanManager<Object> {
    /*
     * Requetes du l'accueil operationel
     * 
     */
    private static final String SQL_COUNT_VEHICULE   = " SELECT count(*) from public.vehicule v where v.unite_codeun = :codeun";

    private static final String SQL_PURCENTAGE_LIBRE = "SELECT   \r\n" +
            "        case\r\n" +
            "            when t2.nb!=0 then (t1.nb_libre / t2.nb)*100\r\n" +
            "            else 0 \r\n" +
            "        end\r\n" +
            "        \r\n" +
            "FROM \r\n" +
            "(SELECT count(*) nb_libre from public.vehicule v where v.unite_codeun = :codeun and v.etat = :etat) t1 ,\r\n"
            +
            "(SELECT count(*) nb from public.vehicule v where v.unite_codeun = :codeun ) t2;";

    
    private static final String SQL_COUNT_DRIVER   = " SELECT count(*) from public.driver d where d.unite_codeun = :codeun";

    private static final String SQL_PURCENTAGE_LIBRE_DRIVER = "select \r\n" + 
            "    case \r\n" + 
            "     when(nb!=0) then ((nb-count(*))/nb)*100 \r\n" + 
            "     else 0\r\n" + 
            "    end nb\r\n" + 
            "from (select count(*) nb from public.driver) t1,public.affectationconducteur aff\r\n" + 
            "join public.driver d  on aff.driver_iddriver = d.iddriver \r\n" + 
            "where aff.enddate is NULL and d.unite_codeun = :codeun "+
            "group by nb";
    
    private static final String SQL_EVOLUTION_NB_MISSION = "select date(mis.datedebut),count(mis.id) from public.mission mis \r\n" + 
            "join public.vehicule v on  v.matricule_interne = mis.vehicule_matricule_interne\r\n" + 
            "where v.unite_codeun = :codeun \r\n" + 
            "group by date(mis.datedebut)";
    
    private static final String SQL_EVOLUTION_NB_PANNE = "select date(mis.startdate),count(*) from public.maintenance mis \r\n" + 
            "join public.vehicule v on  v.matricule_interne = mis.v_matricule_interne\r\n" + 
            "where v.unite_codeun = :codeun \r\n" + 
            "group by date(mis.startdate)";
    /*
     * 
     * 
     */
    private static final String SQL_ETAT_PAR_MARQUE = "select v.marque_titre,v.etat,count(*) "
            + "from public.vehicule v\r\n" + 
            "group by  v.marque_titre,v.etat";
    
    @PersistenceContext( unitName = "MN_unit" )
    private EntityManager em;

    public GeneralManager() {
        super( Object.class );
    }

    @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
        return em;
    }

    public List<Object> lister( Class<?> name ) {
        // TODO Auto-generated method stub
        return em.createQuery( "select b from " + name.getName() + " b" ).getResultList();
    }


    public Integer countVehicule() {
        Query q = this.getEntityManger().createNativeQuery( SQL_COUNT_VEHICULE );
        try {
            q.setParameter( "codeun", PageGenerator.getUtilisateur().getCodeun() );
            return ( (BigInteger) q.getSingleResult() ).intValue();
        } catch ( Exception e ) {
            e.printStackTrace();
            return -1;
        }

    }

    public Integer purcentageVehicule( EtatsVehicule etat ) {
        Query q = this.getEntityManger().createNativeQuery( SQL_PURCENTAGE_LIBRE );
        try {
            q.setParameter( "codeun", PageGenerator.getUtilisateur().getCodeun() );
            q.setParameter( "etat", etat.ordinal() );
            return ( (BigInteger) q.getSingleResult() ).intValue();
        } catch ( Exception e ) {
            e.printStackTrace();
            return -1;
        }

    }
    
    
   
    public Integer countDriver() {
        Query q = this.getEntityManger().createNativeQuery( SQL_COUNT_DRIVER );
        try {
            q.setParameter( "codeun", PageGenerator.getUtilisateur().getCodeun() );
            return ( (BigInteger) q.getSingleResult() ).intValue();
        } catch ( Exception e ) {
            e.printStackTrace();
            return -1;
        }

    }

    public Integer purcentageDriver() {
        Query q = this.getEntityManger().createNativeQuery( SQL_PURCENTAGE_LIBRE_DRIVER );
        try {
            q.setParameter( "codeun", PageGenerator.getUtilisateur().getCodeun() );
       
            return ( (BigInteger) q.getSingleResult() ).intValue();
        } catch ( Exception e ) {
            e.printStackTrace();
            return -1;
        }

    }
    
    public List<?> evolutionMissions(){
        Query q = this.getEntityManger().createNativeQuery(SQL_EVOLUTION_NB_MISSION);
        try {
            q.setParameter( "codeun", PageGenerator.getUtilisateur().getCodeun() );
            return q.getResultList();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Object>();
        }
        
    }
    
    public List<?> evolutionMaitenances(){
        Query q = this.getEntityManger().createNativeQuery(SQL_EVOLUTION_NB_PANNE);
        try {
            q.setParameter( "codeun", PageGenerator.getUtilisateur().getCodeun() );
           return q.getResultList();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Object>();
        }
        
    }
    public List<?> etatVehiculeParMarque(){
        Query q = this.getEntityManger().createNativeQuery(SQL_ETAT_PAR_MARQUE);
        try {
            return q.getResultList();
        }catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Object>();
        }
        
    }
    
  
}
