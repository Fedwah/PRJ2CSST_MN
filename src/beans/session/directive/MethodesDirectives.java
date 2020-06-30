package beans.session.directive;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;

import beans.entities.directive.directive;
import beans.entities.utilisateurs.Utilisateur;

 
@Stateless
@LocalBean
public class MethodesDirectives {
	 @PersistenceContext(unitName="MN_unit")
	 private EntityManager em;
	 private static final String JPQL_SELECT_op ="FROM Utilisateur u where u.type=:type and u.role=:role";
	 private static final String JPQL_REG = "SELECT u FROM Utilisateur u where u.type=:type";
	 public static final String PARAM_ROLE   = "role"; 
	 public static final String PARAM_TYPE    ="type";
	 public static final String PARAM_SENDER= "sender";
	 public static final String PARAM_rec= "reciever";
	 private static final String JPQL_CENTRAL ="SELECT d FROM directive d where d.sender=:sender";
	 private static final String JPQL_operationnel ="SELECT d FROM directive d where d.reciever=:reciever";
	 private static final String JPQL_users = "select u from Utilisateur u where u.id=:reciever";
	 
    public MethodesDirectives() {
        
    }
    

    // Enregistrement d'une nouvelle directive
    public void creer( directive directive )   {
            em.persist( directive );
                                                   }
    //Lister les responsables de region et les admins opérationnels
    public List<Utilisateur> ListerUsersRegional() {
 	   
		return em.createQuery(JPQL_REG).setParameter(PARAM_TYPE ,"Regional").getResultList();
	}
    public List<Utilisateur> ListerUsersOperationnel() {
  	 
		return  em.createQuery(JPQL_SELECT_op).setParameter(PARAM_TYPE , "Operationnel").setParameter( PARAM_ROLE, "Admin").getResultList();
	}
    
    public List<directive> ListerdirectiveEnvoye(int sender) {
    	List<directive> result= new ArrayList<directive>();
    	result=null;
    	result = em.createQuery(JPQL_CENTRAL).setParameter(PARAM_SENDER, sender).getResultList();
    	if (result !=null )
    		return result ;
        	else {
        		result =null;
        		return result; 
        	}
	}
    
    public List<directive> ListerdirectiveRecues(int reciever) {
    	List<directive> result= new ArrayList<directive>();
    	result=null;
    	result = em.createQuery(JPQL_operationnel).setParameter(PARAM_rec, reciever).getResultList();
    	if (result !=null )
		return result ;
    	else {
    		result =null;
    		return result; 
    	}
	}
    public List<Utilisateur> ListerUsers(int reciever){
    	List<Utilisateur> result= new ArrayList<Utilisateur>();
    	result=null;
    	result = em.createQuery(JPQL_users).setParameter(PARAM_rec, reciever).getResultList();
    	if (result !=null )
		return result ;
    	else {
    		result =null;
    		return result; 
    	}
    }
    
}
