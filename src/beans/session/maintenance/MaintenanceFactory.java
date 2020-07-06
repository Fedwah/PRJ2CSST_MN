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
        System.out.println("index niveau est "+ indexNiv);
        Niveau n = Niveau.values()[Integer.parseInt(indexNiv)];
        System.out.println(" niveau est "+n);
        m.setNiv(n);
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



	public Maintenance create( HttpServletRequest request, Maintenance bean, String etat ) {
        Maintenance m = new Maintenance();
        m.setIdMaintenance(bean.getIdMaintenance());
        m.setV(bean.getV());
        m.setUn(bean.getUn());
        String indexNiv = request.getParameter( "niveau" );
        System.out.println("index niveau est "+ indexNiv);
        Niveau n = Niveau.values()[Integer.parseInt(indexNiv)];
        m.setNiv(n);
        
        try {
            m.setStartDate( new SimpleDateFormat( "yyyy-MM-dd" ).parse( request.getParameter( "debut" ) ) ); // date debut
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        if(etat != "à venir")
        {
        	try {
                m.setEndDate( new SimpleDateFormat( "yyyy-MM-dd" ).parse( request.getParameter( "datefin" ) ) ); // date de fin 
            } catch ( ParseException e ) {
                e.printStackTrace();
            }
        }
        m.setInstructions(createInstructionsList(request));
        
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
    			for(int i = 0 ; i<insts.size()-1;i++)
    			{
    				for(int j =i+1;j<insts.size();j++ )
    				{
    					if(insts.get(i).getId() == insts.get(j).getId()) 
    						this.addErreurs("instructions", "Vous ne pouvez pas inserer la meme instruction deux fois");
    				}
    			}
    }


    @Override
    public void updateChange( Maintenance newB, Maintenance old ) {
        old.setEndDate(newB.getEndDate());
        old.setNiv(newB.getNiv());
        old.setStartDate(newB.getStartDate());
        old.setInstructions(newB.getInstructions());
    }

    public boolean validateStartDate( MaintenanceManager em, Maintenance bean ) {
        Map<String, Object> fields = new HashMap();
        boolean valide = true;
        List<Maintenance> currentM = em.findCurrentMaintenance( bean );
        if ( currentM != null ) {
            System.out.println( "liste des maintenaces de ce vehicule est non null " + currentM.size() );
            if ( currentM.size() > 0 ) {
                // System.out.println("liste des maintenaces de ce vehicule est
                // non null " + currentM.size());
                this.addErreurs( "v", "Ce vehicule a deja une maintenance non terminée" );
               valide = valide && false;
            }

        }
        if(bean.getStartDate().compareTo(new Date())< 0)
        {
        	valide = valide && false;
        	this.addErreurs( "startDate", "Vous ne pouvez pas creer des maintenaces passées" );
        }
        System.out.println("cette date est valide " + valide);
        return valide;
    }

    public boolean validateEndDate( Maintenance m ) {
        if ( m.getEndDate().compareTo( m.getStartDate() ) >= 0 ) {

            System.out.println( "end date valide" );
            return true;
        } else {
            this.addErreurs( "endDate", "date de fin est inferieure à la date de debut" );
            return false;
        }

    }

    public boolean validateInsertion( Maintenance bean, MaintenanceManager em ) {
        if ( this.validate( bean , em) && this.validateStartDate( em, bean ) ) {
        	
            return true;
        }
        return false;
    }
    
    public boolean validateEdit( Maintenance bean, MaintenanceManager em ) {
        if ( this.validate( bean, em ) && this.validateEndDate( bean ) ) {
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
    
   public boolean hasInstruction(Maintenance bean , Instruction in)
    {
    	List<Instruction> inst = bean.getInstructions();
    	for(Instruction i : inst)
    	{
    		if(in.getId() == i.getId()) return true;
    	}
    	return false;
    }


}
