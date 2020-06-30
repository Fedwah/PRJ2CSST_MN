package beans.entities.amdec.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum Gravite {
    
    SANS_DOMMAGE("Sans dommage","défaillance mineure ne provoquant "
            + "pas d'arret de production et aucune dégradation notable du matériel"),
    MOYENNE("Moyenne","défaillance provoquant un arrêt de production "
            + "et nécessistant une intervetnion mineure"),
    IMPORTANT("Important","défaillance provoquant un arrêt significatif "
            + "et nécessitant une intervention importante")
    ,CATASROPHIQUE("Catastrophique","défaillance provoquant un arrêt impliquant "
            + "des problèmes majeurs");
    
    public final String label;
    public final String message;
    
   
    
    private Gravite(String label,String message) {
        this.label = label;
        this.message = message;
        
    }
    
    public String getLabel() {
        return label;
    }
    
    public String getMessage() {
        return message;
    }
    
   
}

