package beans.session.general;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EntityField<T> {
    
    
    private Map<String,String> fieldsLabels;
    private Map<String,String> fieldsNames;
    private Map<String,String> fieldsClasses;
    
    private static String[] fieldsToBan = {"serialVersionUID","photo","image"};
    public EntityField() {
       
        this.fieldsLabels = new HashMap<String, String>();
        this.fieldsNames = new HashMap<String, String>();
        this.fieldsClasses = new HashMap<String, String>();
    }
    
    public Map<String, String> filedsLabels() {
        return fieldsLabels;
    }
    
    public Map<String, String> fieldsNames() {
       return fieldsNames;
    }
    
    public Map<String, String> fieldsClasses() {
        return fieldsClasses;
     }
    
    public void generateFields(Class<T> beanClass ) {
        this.fieldsLabels.clear();
        for(Field f : beanClass.getDeclaredFields()) {
            this.putField(formatField(f.getName()), formatLabel( formatField(f.getName()) ),formatClass(f.toGenericString()));
        }
    }
    
    public void putField(String field , String label , String className) {
        if(label!=" " && field!=" " && Arrays.binarySearch( fieldsToBan, field)==-1) {
            
           this.fieldsLabels.put( field, label );
           this.fieldsNames.put( field, field );
           this.fieldsClasses.put( field, className);
        }
    }
    
    private String formatField(String field) {
        return field;
    }
    private String formatLabel(String label) {
       String out = label;
       int index = 0;
       
       out = out.replaceAll( "_", " " );
       out = out.substring(0, 1).toUpperCase() + out.substring(1);
       
       return out;
    }
    
    private String formatClass(String className) {
       
        return className.substring( className.indexOf( ' ' )+1, className.lastIndexOf( ' ' ) );
    }
}
