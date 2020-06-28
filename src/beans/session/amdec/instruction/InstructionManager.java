package beans.session.amdec.instruction;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.entities.amdec.Instruction;
import beans.session.general.BeanManager;

@Stateless
public class InstructionManager extends BeanManager<Instruction> {
    @PersistenceContext(unitName = "MN_unit")
    EntityManager em;
    
    public InstructionManager() {
        super(Instruction.class);
    }
    
    @Override
    public EntityManager getEntityManger() {
        // TODO Auto-generated method stub
        return em;
    }

}
