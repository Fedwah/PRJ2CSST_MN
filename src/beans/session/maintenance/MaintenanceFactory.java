package beans.session.maintenance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.wildfly.security.manager.action.GetProtectionDomainAction;

import beans.entities.amdec.Instruction;
import beans.entities.maintenance.Maintenance;
import beans.entities.maintenance.niveaux.Niveau;
import beans.entities.pieces.Piece;
import beans.entities.regions.unites.Unite;
import beans.entities.utilisateurs.Utilisateur;
import beans.entities.vehicules.Vehicule;
import beans.session.amdec.instruction.InstructionManager;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

import beans.session.pieces.PieceManager;
import beans.session.vehicules.VehiculesManager;

public class MaintenanceFactory extends BeanFactory<Maintenance> {

    public MaintenanceFactory() {
        super(Maintenance.class);
    }

   

    @Override
    public Maintenance create( HttpServletRequest request ) {
        Maintenance m = new Maintenance();

        Vehicule v = new Vehicule( request.getParameter( "matricule" ) );
        m.setV( v );
        HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("sessionUtilisateur");
		Unite un = new Unite(user.getCodeun());
        m.setUn(un);
    
        String indexNiv = request.getParameter( "niveau" );
        Niveau n = Niveau.values()[Integer.parseInt(indexNiv)];
        
        try {
            m.setStartDate( new SimpleDateFormat( "yyyy-MM-dd" ).parse( request.getParameter( "recruit" ) ) );
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        m.setInstructions(createInstructionsList(request));
        return m;
    }
    
    private List<Instruction> createInstructionsList(HttpServletRequest request) {
		int nb = Integer.parseInt(request.getParameter("cpt"));
		System.out.println("nombre d instruction est  " + nb);
		List<Instruction> instructions = new ArrayList();
		for(int i = 1; i<= nb ;i++)
		{
			String index = Integer.toString(i);
			System.out.println("index de select  " + index);
			int id = castInt(request.getParameter(index));
			Instruction inst = new Instruction(id);
			instructions.add(inst);			
		}
		return  instructions;
	}



	public Maintenance create( HttpServletRequest request, Maintenance bean ) {
        Maintenance m = new Maintenance();

       // Niveau n = new Niveau( Integer.parseInt( request.getParameter( "niveau" ) ) );
       // m.setNiv( n ); // niveau de maintenance
        m.setStartDate(bean.getStartDate()); // date debut
        try {
            m.setEndDate( new SimpleDateFormat( "yyyy-MM-dd" ).parse( request.getParameter( "dateFin" ) ) ); // date de fin 
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public void validateChilds( Maintenance bean, BeanManager<Maintenance> beanM ) {
    			List<Instruction> insts = bean.getInstructions();
    			for(Instruction i : insts)
    			{
    				Instruction in = (Instruction) beanM.trouver(Instruction.class, i.getId());
    				if(in == null) this.addErreurs("instructions", "Code instruction n'existe pas");
    			}
    }


    @Override
    public void updateChange( Maintenance newB, Maintenance old ) {
        old.setEndDate(newB.getEndDate());
        old.setNiv(newB.getNiv());


    }

    public boolean validateStartDate( MaintenanceManager em, Maintenance bean ) {
        Map<String, Object> fields = new HashMap();
        boolean valide = true;
        List<Maintenance> currentM = em.findCurrentMaintenace( bean );
        if ( currentM != null ) {
            System.out.println( "liste des maintenaces de ce vehicule est non null " + currentM.size() );
            if ( currentM.size() > 0 ) {
                // System.out.println("liste des maintenaces de ce vehicule est
                // non null " + currentM.size());
                this.addErreurs( "v", "Ce vehicule a dï¿½jï¿½ une maintenance non terminï¿½" );
               valide = valide && false;
            }

        }
        if(bean.getStartDate().compareTo(new Date())< 0)
        {
        	valide = valide && false;
        	this.addErreurs( "startDate", "Vous ne pouvez pas crï¿½er des maintenaces passï¿½es" );
        }
        System.out.println("cette date est valide " + valide);
        return valide;
    }

    public boolean validateEndDate( Maintenance m ) {
        if ( m.getEndDate().compareTo( m.getStartDate() ) >= 0 ) {

            System.out.println( "end date valide" );
            return true;
        } else {
            this.addErreurs( "endDate", "date de fin est inferieure ï¿½ la date dï¿½but" );
            return false;
        }

    }

    public boolean validateInsertion( Maintenance bean, MaintenanceManager em ) {
        if ( this.validate( bean , em) && this.validateStartDate( em, bean ) ) {
        	
            return true;
        }
        return false;
    }
    
    public boolean validateEdit( Maintenance bean ) {
        if ( this.validate( bean ) && this.validateEndDate( bean ) ) {
            return true;
        }
        return false;
    }
    
    public boolean validateV(Maintenance bean, VehiculesManager vm)
    {
    	Vehicule v = vm.trouver(bean.getV().getMatricule_interne());
    	if(v!=null)
    	{
    		return true;
    	}
    	this.addErreurs("v", "ce code véhicule n'existe pas");
    	return false;
    }
    
   /*public boolean validateInstructions(Maintenance bean , InstructionManager im)
    {
    	List<Instruction> inst = bean.getInstructions();
    	boolean validate = 
    	for(Instruction i : inst)
    	{
    		Instruction instruction = im.trouver(i.getId());
    		if(instruction != null) this.addErreurs("instructions", "Code d'instruction n'existe pas");
    	}
    }*/


}
