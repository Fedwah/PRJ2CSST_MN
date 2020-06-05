package beans.session.general;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EntityField<T> {
    
    
    private Map<String,String> fieldsLabels;
    private static String[] fieldsToBan = {"serialVersionUID"};
    public EntityField() {
       
        this.fieldsLabels = new HashMap<String, String>();
        
       
    }
    
    public Map<String, String> filedsLabels() {
        return fieldsLabels;
    }
    
    public void generateFields(Class<T> beanClass ) {
        for(Field f : beanClass.getDeclaredFields()) {
            this.putField(formatField(f.getName()), formatLabel( formatField(f.getName()) ) );
        }
    }
    
    public void putField(String field , String label) {
        if(label!="" && field!="" && Arrays.binarySearch( fieldsToBan, label )==-1) {
           this.fieldsLabels.put( field, label );
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
}
