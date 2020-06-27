package beans.session.Utilisateur;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import beans.entities.utilisateurs.Utilisateur;
import beans.session.general.BeanManager;

 
@Stateless
@LocalBean
public class MethodeUtilisateur extends BeanManager<Utilisateur> {
	    @PersistenceContext(unitName="MN_unit")
		private EntityManager em;
	    private static final String JPQL_SELECT_PAR_NOM = "SELECT u FROM Utilisateur u WHERE u.nom=:nom";
	    private static final String JPQL_SELECT_PAR_PRENOM = "SELECT u FROM Utilisateur u WHERE u.prenom=:prenom";
	    private static final String JPQL_SELECT_PAR_ID = "DELETE FROM Utilisateur u WHERE u.id=:id";
	    private static final String JPQL_MODIF_PAR_ID = "UPDATE Utilisateur u SET "
	    		+ "u.nomUtilisateur=:nomUtilisateur,"
	    		+ "u.motDePasse=:motdepasse,"
	    		+ "u.role=:role  "
	    		+ "WHERE u.id=:id";
	    private static final String JPQL_SELECT_PAR_EMAIL_PASS = "SELECT u FROM Utilisateur u WHERE u.nomUtilisateur =:nomUtilisateur and u.motDePasse=:motdepasse";
	    private static final String PARAM_NOM   = "nom";
	    private static final String PARAM_PRENOM= "prenom";
	    public static final String PARAM_ROLE   = "role";  
	    public static final String PARAM_PASS   = "motdepasse";  
	    public static final String PARAM_USER   ="nomUtilisateur";
	    public static final String PARAM_USER2 ="nomUtilisateur";
	    public static final String PARAM_ID     ="id";
	    public static String result    ="id";
	    private static final String JPQL_FILTRE_TYPE= "SELECT u FROM Utilisateur u WHERE u.type =:type ";
	    private static final String JPQL_FILTRE_ROLE= "SELECT u FROM Utilisateur u WHERE u.role =:role ";
	    public static final String PARAM_TYPE    ="type";
 
	    
    public MethodeUtilisateur() {
       super(Utilisateur.class);
    }
    

    // Enregistrement d'un nouvel utilisateur
    public void creer( Utilisateur utilisateur )   {
            em.persist( utilisateur );
                                                   }
    
 // Recherche d'un utilisateur à partir de son nom 
    public List<Utilisateur> trouverNOM( String nom )   {
      /*  Utilisateur utilisateur = null;*/
        List<Utilisateur> list = null ;
        Query requete = em.createQuery( JPQL_SELECT_PAR_NOM );
        requete.setParameter( PARAM_NOM, nom);
        try{ 
        list = requete.getResultList();
        }catch(javax.persistence.NoResultException e)
        {   
        }
        if(list == null){
            return null;
           }else{
               return list;
           }
      
    }
    
    
 // Recherche d'un utilisateur à partir de son Prenom 
    public Utilisateur trouverPRENOM( String prenom )   {
        Utilisateur utilisateur = null;
    	
        Query requete = em.createQuery( JPQL_SELECT_PAR_PRENOM );
        requete.setParameter( PARAM_PRENOM, prenom);
        try{ 
        utilisateur = (Utilisateur) requete.getSingleResult();
        }catch(javax.persistence.NoResultException e)
        {   
        }
        if(utilisateur == null){
            return null;
           }else{
               return utilisateur;
           }
      
    }
    
    //Récupérer tous les utilisateurs
    public List<Utilisateur> recupererTOUTUtilisateur() {
	   
		return em.createQuery("SELECT u FROM Utilisateur u").getResultList();
	}
    
    //Supprimer un utilisateur via son id
    public String supprimerUser (int id)
    {
    	  Query requete = em.createQuery( JPQL_SELECT_PAR_ID );
          int delet = em.createQuery(JPQL_SELECT_PAR_ID).setParameter(PARAM_ID, id).executeUpdate();
          if (delet == 0) {
        	  result = "Impossible de supprimer l'utilisateur";	
    }
          else {
        	  result = "Utilisateur supprimé !";	
          }
          return result;
          

}
    
  // Modfier un utilisateur 
    public String modifierUser (int id,String nomUtilisateur,String motdepasse, String role )
    {
    	 
          int modif = em.createQuery(JPQL_MODIF_PAR_ID).setParameter(PARAM_ID, id).setParameter(PARAM_USER2,nomUtilisateur).setParameter(PARAM_PASS, motdepasse).setParameter(PARAM_ROLE, role)
        		  .executeUpdate();
          if (modif == 0) {
              result = "Impossible de modifier l'utilisateur";	
    }else
    {
    	      result = "Utilisateur modifié !";	
    }
              return result;

}

 //Appliquer le filtre type sur les users    
    public List<Utilisateur> FiltrerparType(String type) {
 	   
		return em.createQuery(JPQL_FILTRE_TYPE).setParameter(PARAM_TYPE, type).getResultList();
			 
	}  
    
    
    //Appliquer le filtre type sur les users    
    public List<Utilisateur> FiltrerparRole(String role) {
 	   
		return em.createQuery(JPQL_FILTRE_ROLE).setParameter(PARAM_ROLE, role).getResultList();
			 
	}  
    
    
    //Récupérer tous les coderegs
    public List<String> recupererCodereg() {
	   
		return em.createQuery("SELECT r FROM Region r").getResultList();
	}
    
    
    //Récupérer tous les coderuns
    public List<String> recupererCodeun() {
	   
		return em.createQuery("SELECT u FROM Unite u").getResultList();
	}
    
    
    // login du user
    public Utilisateur connecter( String nomUtilisateur, String motdepasse )   {
    	Utilisateur utilisateur = null;
    	Query requete =em.createQuery( JPQL_SELECT_PAR_EMAIL_PASS );
        requete.setParameter( PARAM_USER,nomUtilisateur  );
        requete.setParameter( PARAM_PASS, motdepasse );
        try{ 
        	 utilisateur = (Utilisateur) requete.getSingleResult();
        	 
        }catch(javax.persistence.NoResultException e)
        {   
        }
        if(utilisateur == null){
            return null;
           }else{
               return utilisateur;
           }
          
        	}
       

    
    
    
    

	@Override
	public EntityManager getEntityManger() {
		
		return em;
	}
}
