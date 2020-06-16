package beans.session.vehicules.affectation;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.entities.driver.Driver;
import beans.entities.vehicules.AffectationConducteur;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanFactory;

public class AffectationConducteurFactory extends BeanFactory<AffectationConducteur> {

    public static final String TITRE_VUE_LIST = "L'historique des conducteurs du vehicule ";
    public static final String TITRE_VUE_FORM = "Affectation d'un conducteur au vehicule ";
    public static final String VUE_LIST = "/WEB-INF/vues/vehicules/affectation/affectation.conducteur.list.jsp";
    public static final String VUE_FORM = "";
   
    public AffectationConducteurFactory() {
        super(AffectationConducteur.class);
    }
    
    @Override
    public AffectationConducteur create( HttpServletRequest request ) {
        AffectationConducteur aff = new AffectationConducteur();
        Vehicule v = new Vehicule();
        Driver d = new Driver();
      
        v.setNum_immatriculation( request.getParameter( "vehicule" ) );
        aff.setCar(v);
        d.setIDdriver( Integer.decode(request.getParameter("driver") ));
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
    public void validateChilds( AffectationConducteur bean ) {
        // TODO Auto-generated method stub
        
    }
    
    public AffectationConducteur affecter(HttpServletRequest request ,AffectationConducteurManager affM,List<AffectationConducteur> affectations) {
        
        AffectationConducteur newAff = this.create( request );
        AffectationConducteur oldAffecter = null;
        
        System.out.println( "Operation affecter sur :"+newAff.getStartDate()+" "+newAff.getCar().getNum_immatriculation()+" "
                +newAff.getDriver().getIDdriver());
        
        oldAffecter = (affectations.size()>0?(affectations.get( affectations.size()-1 )):null);
        
        if(oldAffecter!=null) {
            finirAffectation( oldAffecter, affM );
        }
      
        if(this.validate( newAff ) && newAff.getCar().getNum_immatriculation()!=null) {
            System.out.println( "Affectation ajouter" );
            if(affM.ajouter( newAff )) {
                newAff = affM.trouver(newAff.getId());
                if(newAff!=null) {
                    if(oldAffecter!=null) affectations.get( affectations.size()-1).setEndDate( newAff.getStartDate());
                    affectations.add( newAff );
                }
            }
            return newAff;
        }else {
             affectations.get( affectations.size()-1).setEndDate(new Date());
             return null;
        }
        
        
       
   
    }
    
    public boolean finirAffectation(AffectationConducteur bean , AffectationConducteurManager affM) {
        AffectationConducteur newAff = new AffectationConducteur();
        
        newAff.setEndDate(new Date());
        System.out.println("Affectation enlevez");
        
        return affM.mettreAJour(bean.getId(), this, newAff );
    }
    
    

}
