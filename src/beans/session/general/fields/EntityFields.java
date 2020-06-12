package beans.session.general.fields;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


public class EntityFields<T> {

    private Map<String, FieldDefinition> fields;
    private FieldDefinition              idField;

   

    public EntityFields() {
        this.fields = new LinkedHashMap<String, FieldDefinition>();
        this.idField = null;
    }

    public Map<String, FieldDefinition> fields() {
        return fields;
    }

    public Map<String, String> labels() {
        Map<String, String> out = new LinkedHashMap<String, String>();

        for ( Map.Entry<String, FieldDefinition> f : this.fields.entrySet() ) {
            out.put( f.getKey(), f.getValue().label );
        }

        return out;
    }

    public Map<String, String> names() {
        Map<String, String> out = new LinkedHashMap<String, String>();

        for ( Map.Entry<String, FieldDefinition> f : this.fields.entrySet() ) {
            out.put( f.getKey(), f.getKey() );
        }

        return out;
    }

    public Map<String, Class<?>> classes() {
        Map<String, Class<?>> out = new LinkedHashMap<String, Class<?>>();

        try {
            for ( Map.Entry<String, FieldDefinition> f : this.fields.entrySet() ) {
                out.put( f.getKey(), Class.forName( f.getValue().class_ ) );
            }

            return out;

        } catch ( ClassNotFoundException e ) {
            // TODO Auto-generated catch block
            
            e.printStackTrace();
            return out;
        }
    }

    

    public void generateFields( Class<T> beanClass ) {
        FieldDefinition fd = null;
        this.fields.clear();

        for ( Field f : beanClass.getDeclaredFields() ) {
            fd = new FieldDefinition( formatName( f.getName() ), formatLabel( formatField( f.getName() ) ),
                    formatClass( f.toGenericString() ), isBasicClass( f.toGenericString() ) );
            /*System.out.println( "field add : " + formatField( f.getName() ) + " | " + fd.label + " | " + fd.class_
                    + " | " + fd.isBasicClass );*/
            this.putField( formatField( f.getName() ), fd );

        }
    }

    public void putField( String field, FieldDefinition definition ) {
        if ( definition.label != " " && field != " " ) {
            if ( this.idField == null ) {
                this.idField = definition;
            }
            this.fields.put( field, definition );

        }
    }

    private String formatField( String field ) {
        return field;
    }

    private String formatName( String name ) {
        return name;
    }

    private String formatLabel( String label ) {
        String out = label;
        int index = 0;

        out = out.replaceAll( "_", " " );
        out = out.substring( 0, 1 ).toUpperCase() + out.substring( 1 );

        return out;
    }

    private String formatClass( String className ) {

        return className.substring( className.indexOf( ' ' ) + 1, className.lastIndexOf( ' ' ) );
    }

    private boolean isBasicClass( String className ) {
        return formatClass( className ).startsWith( "java" );
    }

    public FieldDefinition idField() {

        return this.idField;
    }

    public String getValidName( String fieldName ) {
        int index = 0;
        if ( ( index = fieldName.indexOf( '.' ) ) != -1 ) {
            return fieldName.substring( 0, index );
        } else {
            return fieldName;
        }
    }
}
