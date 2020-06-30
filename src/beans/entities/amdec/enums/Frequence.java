package beans.entities.amdec.enums;

public enum Frequence {
    
    EXCEPTIONNELLE("Exceptionnelle","La possiblité d'une défaillance "
            + "est pratiquement inexistante"),
    RARE("Rare","Une défaillance occasionnelle s'est déja produite"
            + "ou pourrait se produire"),
    CERTAINE("Certaine","Il  y a eu traditionnellement "
            + "des défailances dans le passé"),
    RES_FREQUANTE("Très fréquante","Il est presque certain que"
            + "se produira souvent");
    
    public final String label;
    public final String message;
    
    private Frequence(String label,String message) {
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
