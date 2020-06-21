package beans.session.vehicules.affectation;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.entities.driver.Driver;
import beans.entities.vehicules.AffectationConducteur;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

public class AffectationConducteurFactory extends BeanFactory<AffectationConducteur> {

    public static final String TITRE_VUE_LIST = "L'historique des conducteurs du vehicule ";
    public static final String TITRE_VUE_FORM = "Affectation d'un conducteur au vehicule ";
    public static final String VUE_LIST = "/WEB-INF/vues/vehicules/affectation/affectation.conducteur.list.jsp";
    public static final String VUE_FORM = "";
    public static final String DEFAULT_REDIRECT = "/Vehicules/";
   
    public AffectationConducteurFactory() {
        super(AffectationConducteur.class);
    }
    
    @Override
    public AffectationConducteur create( HttpServletRequest request ) {
        AffectationConducteur aff = new AffectationConducteur();
        Vehicule v = new Vehicule();
        Driver d = new Driver();
      
        v.setMatricule_interne( request.getParameter( "vehicule" ) );
        aff.setCar(v);
        d.setIDdriver( this.castId(request.getParameter("driver") ));
        aff.setDriver(d);
        
        aff.setStartDate( new Date());
        aff.setEndDate( null );
        return aff;
    }
    
    @Override
    public void updateChange( AffectationConducteur newB, AffectationConducteur old ) {
       
       
            if( newB.getCar()!=null && newB.getCar()!= old.getCar()) old.setCar( newB.getCar() );
            
            if( newB.getDriver()!=null && newB.getDriver()!= old.getDriver()) old.setDriver( newB.getDriver() );
            
            if((newB.getStartDate()!=null) && newB.getStartDate()!=old.getStartDate()) old.setStartDate( newB.getStartDate() );
            
            if( newB.getEndDate()!=old.getEndDate()) old.setEndDate(newB.getEndDate() );
        
       
        

    }
    
    
    
    
    @Override
    public void validateChilds( AffectationConducteur bean, BeanManager<AffectationConducteur> beanM ) {
        // TODO Auto-generated method stub
        
    }
    
    public AffectationConducteur affecter(HttpServletRequest request ,AffectationConducteurManager affM,AffectationConducteur oldAffecter) {
        
        AffectationConducteur newAff = this.create( request );
      
        
        System.out.println( "Operation affecter sur :"+newAff.getStartDate()+" "+newAff.getCar().getMatricule_interne()+" "
                +newAff.getDriver().getIDdriver());
        
        
        
        if(oldAffecter!=null) {
            finirAffectation( oldAffecter, affM );
        }
      
        if(this.validate( newAff ) && newAff.getCar().getMatricule_interne()!=null) {
            System.out.println( "Affectation ajouter" );
            if(affM.ajouter( newAff )) {
                newAff = affM.trouver(newAff.getId());
            }
            return newAff;
        }else {
            return null;
        }
        
     
    }
    
    public boolean finirAffectation(AffectationConducteur bean , AffectationConducteurManager affM) {
        AffectationConducteur newAff = new AffectationConducteur();
        
        if(bean.getEndDate()==null) {
            newAff.setEndDate(new Date());
            System.out.println("Affectation enlevez");
            
            return affM.mettreAJour(bean.getId(), this, newAff ); 
        }
        return false;
       
    }
    
    

}
