package beans.session.vehicules.missions;

import javax.servlet.http.HttpServletRequest;

import beans.entities.vehicules.AffectationConducteur;
import beans.entities.vehicules.Mission;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

public class MissionFactory extends BeanFactory<Mission> {

    private static final String PARAM_AFFECTATION = "affectation";
    private static final String PARAM_DATE_FIN = "dateFin";
    private static final String PARAM_DATE_DEBUT = "dateDebut";
    private static final String PARAM_DECRIPTION = "decription";
    public static final String PARAM_ID = "id";
    public static final String TITRE_VUE_LIST = "L'historique des conducteurs du vehicule ";
    public static final String TITRE_VUE_FORM = "Mission ";
    public static final String VUE_LIST = "";
    public static final String VUE_FORM = "/WEB-INF/vues/vehicules/missions/missions.form.jsp";
    public static final String REDIRECT_URL = "/Vehicules/Affectations/";

    public MissionFactory() {
        super( Mission.class );
    }
    
    @Override
    public Mission create( HttpServletRequest request ) {
        Mission m = new Mission();
        AffectationConducteur aff = new AffectationConducteur();
        aff.setId(this.castId(request.getParameter( PARAM_AFFECTATION)));
        m.setDescription( request.getParameter( PARAM_DECRIPTION ) );
        m.setDateDebut( this.readDate( request, PARAM_DATE_DEBUT ));
        m.setDateFin( this.readDate( request, PARAM_DATE_FIN ) );
        m.setAffectation( aff );
        
        return m;
    }
    
    @Override
    public void updateChange( Mission newB, Mission old ) {
        if(newB!=null) {
            if(newB.getDateDebut()!=null && old.getDateDebut()!=newB.getDateDebut())
                old.setDateDebut( newB.getDateDebut() );
            
            if(newB.getDateFin()!=null && old.getDateFin()!=newB.getDateFin())
                old.setDateFin( newB.getDateFin() );
            
            if(newB.getDescription()!=null && old.getDescription()!=newB.getDescription())
                old.setDescription( newB.getDescription() );
            
        }
        
    }
    
   @Override
   public void validateChilds( Mission bean, BeanManager<Mission> beanM ) {
    // TODO Auto-generated method stub
    
   }
    

}
