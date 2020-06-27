package beans.session.general.fields;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


public class EntityFields<T> {

    private Map<String, FieldDefinition> fields;
    private FieldDefinition              idField;
    private static String[]     fieldsToBan = { "serialVersionUID"};
    private Map<String,Field> fields_java;
   

    public EntityFields() {
        this.fields = new LinkedHashMap<String, FieldDefinition>();
        this.fields_java = new LinkedHashMap<String, Field>();
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
        String class_="";
   
        try {
            for ( Map.Entry<String, FieldDefinition> f : this.fields.entrySet() ) {
                class_= f.getValue().class_;
               
                out.put( f.getKey(), Class.forName(  class_) );
            }

            return out;

        } catch ( ClassNotFoundException e ) {
            // TODO Auto-generated catch block
            
            System.out.println( "EntityFields | class not found" );
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
            this.putField( formatField( f.getName() ), fd ,f);
            
            
            if(f.getAnnotation( Id.class )!=null) {
                //System.out.println( "ID found : "+ fd.name );
                this.idField = fd;
            }

        }
    }

    public void putField( String field, FieldDefinition definition ,Field f ) {
        
        if ( definition.label != " " && field != " " && Arrays.binarySearch(fieldsToBan,field)<0) {
            
            this.fields.put( field, definition );
            this.fields_java.put( field, f );
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
    

        out = out.replaceAll( "_", " " );
        out = out.substring( 0, 1 ).toUpperCase() + out.substring( 1 );

        return out;
    }

    private String formatClass( String className ) {
        
        return className.substring( className.indexOf( ' ' ) + 1, className.lastIndexOf( ' ' ) );
    }

    private boolean isBasicClass( String className ) {
        
        if(formatClass( className ).startsWith( "java" ))
            return true;
        else
            try {
                Class.forName( className );
                return false;
            } catch ( ClassNotFoundException e ) {
                return true;
            }
     
                
    }
    
   

    public FieldDefinition getIdField() {

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
    
    public Class<?> getClass(String name) {
        try {
            System.out.println( "GET CLASS of "+name );
            return Class.forName( this.fields.get( name ).class_);
        } catch ( ClassNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public String getChildId(String childName) {
        Class<?> class_ = this.getClass( childName );
        for(Field f :class_.getDeclaredFields()) {
            if(f.getAnnotation( Id.class )!=null) {
              return f.getName();
            }
        }
        return null;
    }
    
    public Method getGetter(Class<?> class_ ,String name) {
        if(name!=null) {
            String out = name.substring( 0,1 ).toUpperCase()+name.substring( 1);
            try {
                return class_.getMethod( "get"+out, (Class<?>[])null );
                
            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        }
       
        return null;
    }

    public Field getFields_java(String name) {
        return fields_java.get( name );
    }
    
    
    public List<String> getListFields(){
        List<String> out = new ArrayList<String>();
        
        for (FieldDefinition f :this.fields.values()) {
            if(f.class_.contains( "List" )) {
                out.add( f.name );
            }
        }
        return out;
    }
    
    public boolean hasCascadePersist(String field) {
        Field f = this.getFields_java( field );
        Annotation a = null;
        
        
        
        CascadeType[] types = null;
       
        
        if((a = f.getAnnotation( ManyToOne.class ))!=null) {
            types = ((ManyToOne) a).cascade();
        }else if((a = f.getAnnotation( OneToMany.class ))!=null) {
            types = ((OneToMany) a).cascade();  
        }else if((a = f.getAnnotation( OneToOne.class ))!=null) {
            types = ((OneToOne) a).cascade();
        }else if ((a = f.getAnnotation( ManyToMany.class ))!=null) {
            types = ((ManyToMany) a).cascade();
        }
      
        
        //System.out.println( "this child "+field+" is annotated  "+(types!=null));
        
   
       
        if(types!=null) {
            return Arrays.binarySearch( types, CascadeType.ALL )>=0 || Arrays.binarySearch( types, CascadeType.PERSIST )>=0;
        }else {
            return false;
        }
        
    }
    
}
