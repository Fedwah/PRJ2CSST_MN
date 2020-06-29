package beans.session.amdec.detection;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import beans.entities.amdec.Detection;
import beans.entities.amdec.Instruction;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

public class DetectionFactory  extends BeanFactory<Detection>{

    public DetectionFactory() {
        super(Detection.class);
    }
    
    @Override
    public Detection create( HttpServletRequest request ) {
        // TODO Auto-generated method stub
        return new Detection(new Date(),
                new Instruction(this.castInt(request.getParameter( "instruction" ))),
                new Vehicule( request.getParameter( "vehicule" ) ));
    }
    @Override
    public void validateChilds( Detection bean, BeanManager<Detection> beanM ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void updateChange( Detection newB, Detection old ) {
        // TODO Auto-generated method stub
        
    }

}
