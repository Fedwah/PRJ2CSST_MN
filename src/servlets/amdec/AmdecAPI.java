package servlets.amdec;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.Lists;
import org.json.JSONObject;

import beans.entities.amdec.Cause;
import beans.entities.amdec.Defaillance;
import beans.entities.amdec.Detection;
import beans.entities.amdec.Effet;
import beans.entities.amdec.Instruction;
import beans.entities.maintenance.Maintenance;
import beans.entities.maintenance.niveaux.Niveau;
import beans.entities.vehicules.EtatsVehicule;
import beans.entities.vehicules.Mission;
import beans.entities.vehicules.Vehicule;
import beans.session.amdec.cause.CauseFactory;
import beans.session.amdec.cause.CausesManager;
import beans.session.amdec.defaillance.DefaillanceFactory;
import beans.session.amdec.defaillance.DefaillanceManager;
import beans.session.amdec.detection.DetectionFactory;
import beans.session.amdec.detection.DetectionManager;
import beans.session.amdec.effet.EffetFactory;
import beans.session.amdec.effet.EffetManager;
import beans.session.amdec.instruction.InstructionFactory;
import beans.session.amdec.instruction.InstructionManager;
import beans.session.general.page.PageGenerator;
import beans.session.maintenance.CalendarFactory;
import beans.session.maintenance.MaintenanceFactory;
import beans.session.maintenance.MaintenanceManager;
import beans.session.vehicules.VehiculesManager;
import beans.session.vehicules.missions.MissionFactory;
import beans.session.vehicules.missions.MissionManager;

/**
 * Servlet implementation class DetectionAPI
 */
@WebServlet( "/api/amdec/*" )
public class AmdecAPI extends HttpServlet {
    private static final long  serialVersionUID = 1L;

    @EJB
    private CausesManager      causeManager;
    @EJB
    private DefaillanceManager defaiManager;
    @EJB
    private EffetManager       effManager;
    
    @EJB
    private InstructionManager instM;
    
    @EJB
    private VehiculesManager vehM;
    
    @EJB
    private DetectionManager detM;
    
    @EJB
    private MaintenanceManager mainM; 
    
    @EJB
    private MissionManager missM;
    
    private static final String MSG_ERR = "Operation non existente ,  "
            + "essayé avec :  api/amdec/causes , api/amdec/effets, api/amdec/defaillances ou api/amdec/intructions pour les lister "
            + " et api/amdec/causes/{titre} ,api/amdec/effets/{titre},api/amdec/defaillances/{titre} pour ajouter "
            + " pour ajouter une detections faite :"
            + "api/amdec/detecter/{matricule_interne}/{id piece}/{id defaillance}/{id cause}/{id effet}";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmdecAPI() {
        
       
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        PageGenerator pg = new PageGenerator();
        String[] ids = pg.getPathIds( request );

        CauseFactory cF = new CauseFactory();
        EffetFactory eF = new EffetFactory();
        DefaillanceFactory dF = new DefaillanceFactory();
        
        Cause c = null;
        Effet e = null;
        Defaillance d = null;
        Detection detection = null;
        Instruction i =null;
        List<?> out = null;
        
        Boolean success = true;
        String message =MSG_ERR;
        
        if(success=( ids != null && ids.length >= 1 )) {
            switch ( ids[0] ) {
            case "causes":
                if(ids.length==2) { //ajouter
                    c = cF.createValidateAjouter( request,new Cause( ids[1] ),causeManager );
                    if(c == null) {
                        success = false;
                        message  = "Echec de l'operation "+cF.getErreurs();
                         
                    }
                }else { //lister
                    out = causeManager.lister();
                }
                
                break;
            case "effets":
                if(ids.length==2) { //ajouter
                    e = eF.createValidateAjouter( request,new Effet( ids[1] ),effManager );
                    if(e==null) {
                        success = false;
                        message  = "Echec de l'operation "+eF.getErreurs();
                     }
                }else { //lister
                    out = effManager.lister();
                }
        
                break;
            case "defaillances":
                if(ids.length==2) { //ajouter
                    d = dF.createValidateAjouter( request, new Defaillance( ids[1] ),defaiManager );
                    if(d==null) {
                        
                        success = false;
                        message  = "Echec de l'operation"+dF.getErreurs();
                        
                     }
                }else { //lister
                    out = defaiManager.lister();
                }
               
                break;
            case "instructions":
                out = instM.lister();
                break;
            case "detecter":
                if(ids.length== 6) {
                    InstructionFactory iF = new InstructionFactory();
                 
                    Vehicule v = vehM.trouver( ids[1] );
                    if(v!=null){
                        iF.filtreIntruction(v.getModele().getId(),
                                ids[2],
                                ids[3],
                                ids[4],
                                ids[5]);
                      
                        System.out.println( "Filtre intruction "+ iF.getFiltres() );
                
                        i = instM.trouver( iF.getFiltres() );
                      
                        if(i!=null) {
                            detection= new Detection(i,v); 
                            // ajout d'une maintenace automatique
                            if(detM.ajouter( detection ))
                            {
                            	CalendarFactory cf =new CalendarFactory();
                            	Date d1 = new Date() ;
                            	MaintenanceFactory mainF = new MaintenanceFactory();
                            	List<Maintenance> mains = mainM.findCurrentMaintenance((detection.getVehicule()));
                            	// si maintenace d�j� programm�
                            	if(mains.size()>0)
                            	{
                            		this.addInstToMaintenace(mainF, mains, detection);
                            	}
                            	// sinon ajouter une nouvelle maintenance
                            	else 
                            	{
                            		if(detection.getVehicule().getEtat() == EtatsVehicule.LIBRE)
                                	{
                                			// sinon inserer la maintenance
                                			try {
        										d1 = cf.getNextDayOf(d1);
        									} catch (ParseException e1) {
        										// TODO Auto-generated catch block
        										e1.printStackTrace();
        									}
                                			this.addMaintenance(cf, detection, d1);

                                		
                                	}
                                	else if(detection.getVehicule().getEtat() == EtatsVehicule.EN_FONCTION)
                                	{
                                	
                                		Map<String,Object> fields = new HashMap();
                                		fields.put("vehicule.matricule_interne",detection.getVehicule().getMatricule_interne());
                                		Mission mission  = missM.trouver(fields);
                                		List<Instruction> ins = new ArrayList();
                                    	ins.add(detection.getInstruction());
                                    	if(mission.getDateFin() != null)
                                    	{
                                    		try 
                                    		{
    											d1 = cf.getNextDayOf(mission.getDateFin());
    										} catch (ParseException e1) {
    											// TODO Auto-generated catch block
    											e1.printStackTrace();
    										}
                                    		this.addMaintenance(cf, detection, d1);
                                    	}
                                    	else
                                    	{
                                    		try {
												d1 = cf.getNextWeekOf(d1);
											} catch (ParseException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
                                    		this.addMaintenance(cf, detection, d1);
                                    	}
                                    	
                                		
                                	}
                            	}
                            	
                            	
                            }
                            
                        }else {
                            success  = false;
                            message  = "Instruction "+ iF.getFiltres() + "inexistente ";

                        }
                    }
                    
                }
                break;
                
            default:
               success  = false;
              
            }
            
          

        }
        
        if(out!=null) {
            
            List<JSONObject> objects = new ArrayList<JSONObject>();
            for ( Object o : out ) {
                objects.add( new JSONObject( o ) );
            }
            pg.generateJSON( response, objects );
            
        }else if (success) {
        
            pg.generateJSON( response, true,"Ajout reussie");  
        
        }else {
            pg.generateJSON( response, false,message); 
        }

    }
    
    public void addMaintenance(CalendarFactory cf, Detection detection, Date d1)
    {
		while(cf.occupiedDay(detection, mainM, cf.converDateToString(d1)))
    	{
    		try {
				d1 = cf.getNextDayOf(d1);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
		System.out.println("date apres occupied day est " + d1);
    	List<Instruction> ins = new ArrayList();
    	ins.add(detection.getInstruction());
    	Maintenance m = new Maintenance(d1,detection.getVehicule(),ins,Niveau.niv1,
    			detection.getVehicule().getUnite());
    	if(mainM.ajouter(m)) System.out.println("maintenance ajout� avec succ�s");
    	
    }
    public void addInstToMaintenace(MaintenanceFactory mainF, List<Maintenance> mains, Detection detection)
    {
    	if(!mainF.hasInstruction(mains.get(0), detection.getInstruction()))
		{
			Maintenance newM = mains.get(0);
			newM.getInstructions().add(detection.getInstruction());
			mainM.mettreAJour(newM.getIdMaintenance(), mainF, newM);
		}
    }

    

}
