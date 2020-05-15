package beans.session.general;


import java.util.List;
import java.util.Map;


import javax.persistence.EntityManager;





public abstract class BeanManager<T> {
    
  
    private Class<T> beanClass;
    
    public BeanManager() {
        // TODO Auto-generated constructor stub
    }
    
   
    public BeanManager( Class<T> beanClass ) {
        this.beanClass = beanClass; 
    }

    public abstract EntityManager getEntityManger();
    
    public boolean ajouter(T bean) {
        try {
               
            
            this.getEntityManger().persist(bean);
            
            return true;
        } catch (Exception e) {
           e.printStackTrace();
           return false;
        }

    }
    
    public boolean ajouterUnique(T bean , Object id) {
       if(trouver(id)==null) {
           ajouter(bean);
           return true;
       }
       return false;
       
    }
    public void supprimer(T bean) {
        try {
            this.getEntityManger().remove( bean);
        } catch ( Exception e ) {
            System.err.println(e.getMessage());
        }
    }
    
    
    public List<T> lister(int debut , int nb ) {
        return this.getEntityManger().createQuery( "SELECT b from"+beanClass.getName()+" b").setFirstResult(debut).setMaxResults(nb).getResultList();
    }
    
    
    public T trouver(Object id)   {
      
        return (T) this.getEntityManger().find(this.beanClass.getClass(),id);
      
    }
    public T trouver(Map<String,String[]> parameters ) {
        String where ="";
      
        for ( Map.Entry<String, String[]>param : parameters.entrySet() ) {
            where.concat(param.getKey()+"= :"+param.getKey());
            this.getEntityManger().setProperty( param.getKey(), param.getValue()[0] );
        }
    
        return (T) this.getEntityManger().createQuery( "SELCET b FROM "+beanClass.getName()+" b where "+ where ).getSingleResult();
    }
}
