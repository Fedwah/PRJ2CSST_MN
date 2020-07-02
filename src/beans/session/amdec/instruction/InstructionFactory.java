package beans.session.amdec.instruction;

import javax.servlet.http.HttpServletRequest;

import beans.entities.amdec.Cause;
import beans.entities.amdec.Defaillance;
import beans.entities.amdec.Effet;
import beans.entities.amdec.Instruction;
import beans.entities.pieces.Piece;
import beans.entities.vehicules.Modele;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

public class InstructionFactory  extends BeanFactory<Instruction>{

    public InstructionFactory() {
        super(Instruction.class);
    }
    
    @Override
    public Instruction create( HttpServletRequest request ) {
        // TODO Auto-generated method stub
        return new Instruction( 
               new Modele( this.castInt( request.getParameter( "modele" ) ) ),
                new Piece( request.getParameter( "piece" ) ),
                new Defaillance( this.castInt(request.getParameter( "defaillance-instruction" ))),
                new Cause( this.castInt(request.getParameter( "cause-instruction" )) ), 
                new Effet(this.castInt(request.getParameter( "effet-instruction" ) )), 
                this.castInt( request.getParameter( "gravite" ) ), 
                this.castInt(request.getParameter( "frequence" )),
                this.castInt( request.getParameter( "niveau_detection" ) ), 
                request.getParameter( "demarche_resolution" ));
    }
    
    @Override
    public void updateChange( Instruction newB, Instruction old ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void validateChilds( Instruction bean, BeanManager<Instruction> beanM ) {
        // TODO Auto-generated method stub
        
    }
    
    public void filtreIntruction(Integer modele,String piece,String defaillance,String cause,String effet) {
        this.addFiltreByID( "modele_vehicule", modele );
        this.addFiltreByID( "piece",  piece);
        this.addFiltreByID( "defaillance",this.castInt(defaillance));
        this.addFiltreByID( "cause",  this.castInt(cause));
        this.addFiltreByID( "effet",  this.castInt(effet));
    }

}
