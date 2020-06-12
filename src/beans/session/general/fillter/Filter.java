package beans.session.general.fillter;


import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import beans.session.general.fields.EntityFields;

public class Filter<T> {

   
    private static String[]     fieldsToNotFilter = { "serialVersionUID", "photo", "image", "id" };
    private List<String>        notFilter;
    private Map<String, Object> filtres;

    public Filter() {
        notFilter = Arrays.asList(fieldsToNotFilter); 
        this.filtres = new LinkedHashMap<String, Object>(); 
    }

    public void addFieldsNotFilter( String field ) {
        this.notFilter.add( field );
    }

    public Map<String, Object> getFiltres() {
        return filtres;
    }

    public void addFiltre( String field, String subField, Object value ) {
        if ( field != "" && value != null && !this.notFilter.contains(field )) {
            if ( subField != "" ) {
                this.filtres.put( field + "." + subField, value );
            } else {
                this.filtres.put( field, value );
            }

        }
    }

    public Map<String, String> labelsToFilter(EntityFields<T> fields) {
        Map<String, String> m = fields.labels();

        for ( String f : this.notFilter ) {
            m.remove( f );

        }

        return m;
    }
}
