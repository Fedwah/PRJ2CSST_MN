package beans.session.general.fields;



public class FieldDefinition {
    public String name;
    public String label;
    public String class_;
    public boolean isBasicClass;
    public boolean isEnum;
    
    public FieldDefinition(String name,String label, String class_, boolean isBasicClass ) {
        super();
        this.name = name;
        this.label = label;
        this.class_ = class_;
        this.isBasicClass = isBasicClass;
       
    }
}