package beans.entities.vehicules;

public enum EtatsVehicule {
    
    Libre("Libre"),EN_FONCTION("En mission"),EN_PANNE("En panne"),EN_MAINTENACE("En maintenance"),Abandoner("Abandonner");
    public final String label;
   
    
 
    private EtatsVehicule(String label) {
        this.label = label;
        
        
    }
    
    public String getLabel() {
        return label;
    }
    
  
    
    public static EtatsVehicule get(String label) {
        for(EtatsVehicule e: EtatsVehicule.values()) {
            if(e.getLabel().equals( label ))
                return e;
        }
        return null;
    }
}
