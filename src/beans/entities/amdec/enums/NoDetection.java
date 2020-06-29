package beans.entities.amdec.enums;

public enum NoDetection {

    SIGNES_AVANT_COUREURS("Signes avant coureurs",
            "L'opérateur pourra détecter facilement la défaillance"),
    PEU_DE_SIGNES("Peu de signes",
            "La défaillance est décelable avec une certaine "
            + "recherche"),
    AUCUN_SINGE("Aucun signe","la recherche de la défaillance"
            + "n'est pas facile."),
    EXPERTISE_NECESSAIRE("Expertise nécessaire",
            "la défaillance n'est pas décelable ou encore sa localisation nécessite"
            + "une expertise approfondie");
    
    public final String label;
    public final String message;
    
    private NoDetection(String label,String message) {
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
