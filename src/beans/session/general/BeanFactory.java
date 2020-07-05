package beans.session.general;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import beans.entities.general.Image;
import beans.session.general.excel.Excel;
import beans.session.general.fields.EntityFields;
import beans.session.general.fields.FieldDefinition;
import beans.session.general.fillter.Filter;
import beans.session.general.page.PageGenerator;

public abstract class BeanFactory<T> {

    private static final String            MSG_ERREUR_ID_NON_UNIQUE   = "doit etre unique";
    private static final String            MSG_ERREUR_CHILD_NOT_FOUND = "n'existe pas";
    private Map<String, ArrayList<String>> erreurs;
    private Filter<T>                      filteres;
    private EntityFields<T>                entityFields;
    private Class<T>                       beanClass;

    public BeanFactory( Class<T> beanClass ) {

        this.entityFields = new EntityFields<T>();
        this.filteres = new Filter<T>();

        this.beanClass = beanClass;
        this.getEntityFields().generateFields( beanClass );

        try {
            String codeun = PageGenerator.getUtilisateur().getCodeun();
            if ( codeun != null && !codeun.isEmpty() ) {
                this.notFilter( "unite" );
            }
        } catch ( Exception e ) {

        }
    }

    public BeanFactory() {

    }

    public String getClassName() {
        return beanClass.getName().substring( beanClass.getName().lastIndexOf( '.' ) + 1 );
    }

    public Class<T> getBeanClass() {
        return beanClass;
    }

    public abstract T create( HttpServletRequest request );

    public T createValidateAjouter( HttpServletRequest request, T obj, BeanManager<T> beanM ) {

        if ( obj == null ) {
            obj = create( request );
        }
        if ( validate( obj, beanM ) ) {
            beanM.ajouter( obj );
            return obj;
        } else {
            request.setAttribute( "erreurs", this.getErreurs() );
            return null;
        }
    }

    public T createValidateAjouter( HttpServletRequest request, BeanManager<T> beanM ) {

        return this.createValidateAjouter( request, null, beanM );
    }

    public boolean validate( T bean, BeanManager<T> beanM ) {
        return this.validate( bean, beanM, null );
    }

    public boolean validate( T bean, BeanManager<T> beanM, Object id ) {
        boolean result = true;
        if ( bean != null ) {
            this.erreurs = new BeanValidator<T>( bean ).getErreurs(); // Test
                                                                      // standard
            System.out.println( "avant validate childs" ); // (size,notNull,..)
            validateChilds( bean, beanM ); // Test personalisé
            System.out.println( "apr�s validate childs" );
            /* Tester l'unicité en BDD */
            if ( beanM != null && id != null ) {
                // System.out.println( "Tester l'unicité de
                // "+this.getClassName()+"avec l'id :"+id);
                if ( beanM.trouver( bean.getClass(), id ) != null ) {
                    this.addErreurs( this.getEntityFields().getIdField().name, MSG_ERREUR_ID_NON_UNIQUE );
                }
            }
            /* Tester l'existence des childs (entity complex) */
            if ( beanM != null )
                this.checkChildsExists( bean, beanM );

            if ( !this.erreurs.isEmpty() ) {

                for ( ArrayList<String> err : this.erreurs.values() ) {
                    // TODO pas sur a 100% de ce test
                    if ( !err.isEmpty() ) {
                        result = result && err.get( 0 ).equals( "{}" );
                    }

                }
            }
            /*
             * System.out .println( this.getClass().getSimpleName() +
             * "is valid ? :" + ( this.erreurs.isEmpty() || result ) );
             */
            return ( this.erreurs.isEmpty() || result );
        } else {
            this.erreurs = new HashMap<String, ArrayList<String>>();
        }

        return false;

    }

    public boolean validate( T bean ) {
        return this.validate( bean, null, null );
    }

    public abstract void validateChilds( T bean, BeanManager<T> beanM );
    
    public boolean enumClass(String fieldsname)
    {
    	try
    	{
    		return this.entityFields.getClass(fieldsname).isEnum();
    	}
    	catch(Exception e)
    	{
    		System.out.println("class " + fieldsname + " not found");
    		return false;
    	}
    }

    public boolean checkChildsExists( T bean, BeanManager<T> beanM ) {
        Map<String, FieldDefinition> m = this.entityFields.fields();
        Boolean exist = true;
        String childName = "";
        Object child = null;
        Class<?> c = null;
        for ( Map.Entry<String, FieldDefinition> f : m.entrySet() ) {
            // System.out.println( "Check child existense "+f.getKey()+"(Persist
            // ? "+ beanM.hasCascadePersist( f.getKey() )+" )");
            childName = f.getValue().name;

            c = this.entityFields.getClass( childName );

            if ( !this.getEntityFields().hasCascadePersist( f.getKey() ) && !f.getValue().isBasicClass
                    && this.getChildIdValue( bean, childName ) != null
                    && c != null && !( c.isEnum() ) ) {

                child = beanM.trouver(c,this.getChildIdValue( bean, childName ) );

                if ( child == null ) {
                    this.addErreurs( childName, childName + MSG_ERREUR_CHILD_NOT_FOUND );
                    exist = false;
                    System.out.println( "Check of existance of " + childName + " = "
                            + this.getChildIdValue( bean, childName ) + " ? Non" );
                } else {
                    System.out.println( "Check of existance of " + childName + " = "
                            + this.getChildIdValue( bean, childName ) + " ?  Oui" );
                }

            }
        }
        return exist;
    }

    public Map<String, ArrayList<String>> getErreurs() {
        return erreurs;
    }

    public ArrayList<String> getFieldError( String fieldName ) {

        if ( getErreurs() != null ) {
            if ( getErreurs().containsKey( fieldName ) ) {
                return getErreurs().get( fieldName );
            }
        }

        return new ArrayList<String>();
    }

    public void addErreurs( String champ, ArrayList<String> erreurs ) {

        addErreurs( champ, (String[]) erreurs.toArray() );
    }

    public void addErreurs( String champ, String... erreurs ) {

        if ( erreurs.length != 0 ) {
            if ( this.erreurs.containsKey( champ ) ) {
                // System.out.println( "Ajout de l'erreur" );
                this.erreurs.get( champ ).addAll( Arrays.asList( erreurs ) );
            } else {
                // System.out.println( "Creation de l'erreur" );
                this.erreurs.put( champ, new ArrayList<String>( Arrays.asList( erreurs ) ) );
            }
        }

    }

    public byte[] readFile( InputStream in ) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int length;
        byte[] buffer = new byte[1024];
        if ( in != null ) {

            try {
                while ( ( length = in.read( buffer ) ) != -1 )
                    out.write( buffer, 0, length );
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return out.toByteArray();
    }

    public Image readImage( HttpServletRequest request, String PARAM_IMAGE ) {

        try {

            return this.readImage( request.getPart( PARAM_IMAGE ).getInputStream(),
                    request.getPart( PARAM_IMAGE ).getSubmittedFileName() );
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch ( ServletException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public Image readImage( InputStream in, String cheminFichier ) {
        Image img = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int length;
        byte[] buffer = new byte[1024];

        if ( cheminFichier != null && !cheminFichier.isEmpty() ) {
            img = new Image();

            img.setTitre( cheminFichier );
            img.setBinary( this.readFile( in ) );
            System.out.println( "IMG readed " + img.getTitre() );
        }

        return img;
    }

    public Date readDate( HttpServletRequest request, String PARAM_DATE ) {
        System.out.println( "Read date : " + request.getParameter( PARAM_DATE ) );
        if ( request.getParameter( PARAM_DATE ).contains( "T" ) ) {
            String datetime = request.getParameter( PARAM_DATE ).replace( 'T', ' ' );
            // System.out.println( "Date transformed : " + datetime );
            return this.readDateTime( datetime );
        } else {
            return this.readDate( request.getParameter( PARAM_DATE ) );
        }

    }

    public Date readDateTime( String date ) {
        Date d = null;

        try {
            d = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).parse( date );
        } catch ( ParseException e ) {
            // TODO Auto-generated catch block
            System.err.println( "Format date time invalide" );
        }
        return d;

    }

    public Date readDate( String date ) {
        Date d = null;
        try {
            d = new SimpleDateFormat( "yyyy-MM-dd" ).parse( date );
        } catch ( ParseException e ) {
            // TODO Auto-generated catch block
            System.err.println( "Format date invalide" );
        }
        return d;
    }

    public boolean uniqueSave( BeanManager<T> em, T bean, Object id, String PARAM_ID ) {

        if ( em.ajouterUnique( bean, id ) ) {
            System.out.println( "Bean " + id + " added" );
            return true;
        } else {
            System.out.println( "Bean " + id + " error unique" );
            System.out.println( "PARAM ID  : " + PARAM_ID );

            this.addErreurs( PARAM_ID, MSG_ERREUR_ID_NON_UNIQUE );
        }

        return false;
    }

    public boolean uniqueField( BeanManager<T> em, String fieldName, Object fieldValue ) {

        Map<String, Object> fields = new HashMap();
        fields.put( fieldName, fieldValue );
        if ( em.trouver( fields ) == null ) {
            System.out.println( "attribut unique" );
            return true;
        } else {
            this.addErreurs( fieldName, "Ce parametre doit �tre unique" );
        }

        return false;
    }

    public boolean updateAndValidate( HttpServletRequest request, T old ) {
        T newB = create( request );

        if ( validate( newB ) ) {
            System.out.println( "NEW bean validated" );
            updateChange( newB, old );
            return true;
        }

        return false;
    };

    public abstract void updateChange( T newB, T old );

    public EntityFields<T> getEntityFields() {
        return entityFields;
    }

    public Map<String, String> fieldName() {
        return getEntityFields().names();
    }

    public Map<String, Object> getFiltres() {
        return filteres.getFiltres();
    }

    public void addFiltre( String field, Object value ) {

        if ( this.getEntityFields().fields().get( field ).class_.equals( "java.util.Date" ) ) {
            this.addFiltre( field, "", this.readDate( (String) value ) );
        } else {
            this.addFiltre( field, "", value );
        }

    }

    public void addFiltre( String field, String subField, Object value ) {
        this.filteres.addFiltre( field, subField, value );
    }

    public void addFiltreByID( String field, Object value ) {
        if ( value != null && field != null && !field.isEmpty() ) {
            if ( this.getEntityFields().fields().get( field ).isBasicClass ) {
                this.addFiltre( field, value );

            } else {
                this.addFiltre( field, this.getEntityFields().getChildIdName( field ), value );
            }
        }
    }

    public Map<String, String> getNamesToFilter() {
        return this.filteres.labelsToFilter( getEntityFields() );
    }

    public void notFilter( String field ) {
        this.filteres.addFieldsNotFilter( field );
    }

    public int castInt( String string ) {
        if ( string != null ) {
            if ( !string.isEmpty() ) {
                try {
                    return Integer.decode( string );
                } catch ( Exception e ) {
                    return -1;
                }

            }
        }
        return 0;
    }

    public Double castDouble( String value ) {
        if ( value != null ) {
            if ( !value.isEmpty() ) {
                return this.round( Double.valueOf( value ), 3 );
            }
        }
        return new Double( 0.0 );
    }

    public Workbook obtenirModeleExcel( HttpServletResponse response, String[] fieldsToIgnore ) {
        Excel<T> e = new Excel<T>();
        return e.obtenirModeleExcel( this, fieldsToIgnore );

    }

    public Workbook exportExcel( List<?> beans ) {
        Excel<T> e = new Excel<T>();

        List<Object> values = null;
        List<Object[]> beansVals = new ArrayList<Object[]>();
        List<String> ignores = this.fieldIgnoreExport();
        String[] fieldsToIgnore = new String[ignores.size()];

        ignores.toArray( fieldsToIgnore );

        for ( Object b : beans ) {
            values = new ArrayList<Object>();
            for ( FieldDefinition f : this.getEntityFields().fields().values() ) {
                if ( Arrays.binarySearch( fieldsToIgnore, f.name ) < 0 ) {
                    // System.out.println( "Get child id value of "+f.name );

                    values.add( this.getChildIdValue( (T) b, f.name ) );

                }
            }
            System.out.println( "Transformed object " + values );
            // System.out.println( "After add
            // :"+Arrays.toString(values.toArray()));
            beansVals.add( values.toArray() );

        }

        return e.exportExcel( this, fieldsToIgnore, beansVals );

    }

    public List<T> importExcel( BufferedInputStream is ) {
        Excel<T> e = new Excel<T>();
        List<Map<String, Object>> values;

        values = e.importExcel( is );
        T o = null;
        List<T> out = new ArrayList<T>();
        for ( Map<String, Object> map : values ) {
            // System.out.println( "creation of : " + map );

            o = create( map );
            if ( o != null ) {
                // System.out.println( "Creation succussed of " + o );
                out.add( o );
            }

        }
        return out;

    }

    public List<Map<String, ArrayList<String>>> insertAll( List<?> beans, BeanManager<?> beanM ) {
        int row = 2;
        BeanManager<T> bM = (BeanManager<T>) beanM;
        List<Map<String, ArrayList<String>>> errs = new ArrayList<Map<String, ArrayList<String>>>();
        for ( Object b : beans ) {
            // System.out.println( "GET ID automaticly : " + this.getIdValue( b
            // ) );
            if ( this.validate( (T) b, bM, this.getIdValue( (T) b ) ) ) {
                System.out.println( this.getIdValue( (T) b ) + " will be inserted" );
                if ( bM.ajouter( (T) b ) ) {
                    System.out.println( this.getIdValue( (T) b ) + " inserted" );
                }
            } else {
                System.out.println( "Erreur lors de l'inserations ligne" + row + " : " + this.getErreurs() );
            }
            errs.add( this.getErreurs() );
            row++;
        }

        return errs;
    }

    public T create( Map<String, Object> values ) {

        T instance = null;

        Object[] objects = new Object[values.size()];
        int i = 0;
        int index = -1;
        String name = "";
        Map<String, Class<?>> mapC = this.entityFields.classes();

        for ( String ignore : this.fieldIgnoreExport() ) {
            //System.out.println( "remove "+ignore );
            if ( mapC.containsKey( ignore ) ) {
                mapC.remove( ignore );
                //System.out.println( "success" );
               
            }
        }
        Class<?>[] classes = mapC.values().toArray( new Class<?>[values.size()] );
        
        //System.out.println( "classes : "+Arrays.toString( classes ) );
        
        for ( Entry<String, Object> map : values.entrySet() ) {
            name = this.entityFields.getValidName( map.getKey() );
            if ( ( index = name.indexOf( '(' ) ) != -1 ) {
                name = name.substring( 0, index );
            }
            if ( map.getValue() != null )
                objects[i] = this.getInstanceClass( name, map.getValue() );
            else
                objects[i] = null;
            // from "String" to "Entity"

            i++;
        }

        try {
            Constructor<T> c = this.beanClass.getConstructor( classes );
            System.out.println( "use of constructor : " + Arrays.toString( classes ) );
            System.out.println( "with values :" + Arrays.toString( objects ) );
            if ( values != null ) {
                instance = c.newInstance( objects );
            }

        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return instance;
    }

    private Object getInstanceClass( String key, Object value ) {
        Class<?> class_ = this.entityFields.classes().get( key );

        /*System.out.println( "Get instance of " + key + " with value " + value + " of class "
                + ( value != null ? value.getClass() : "null" ) +
                " is basic class " + this.entityFields.fields().get( key ).isBasicClass
                + " class " + this.entityFields.fields().get( key ).class_ );
        */
        if ( !this.entityFields.fields().get( key ).isBasicClass ) {
            if ( this.entityFields.getClass( key ).isEnum() ) {
                // System.out.println( key+" is enum" );
                return Enum.valueOf( (Class<Enum>) this.entityFields.getClass( key ), (String) value );
            } else {
                try {
                    if ( this.entityFields.fields().get( key ).class_.contains( "Image" ) )
                        return value;
                    Constructor<?> c = class_
                            .getConstructor( ( value instanceof String ? String.class
                                    : value instanceof Integer ? Integer.class
                                            : value instanceof Double ? Double.class : null ) );

                    return c.newInstance( value );
                } catch ( Exception e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        } else {
            if ( this.entityFields.fields().get( key ).class_.contains( "Date" ) ) {
                if ( value instanceof Date ) {
                    System.out.println( value + " is a Date" );
                    return value;
                } else if ( value instanceof String ) {
                    Date d = this.readDate( (String) value );
                    if ( d == null ) {
                        d = this.readDateTime( (String) value );
                    }
                    return d;
                }

            } else if ( value != null
                    && !this.entityFields.fields().get( key ).class_.equals( value.getClass().toString() ) ) {
                if ( value instanceof Integer && this.entityFields.fields().get( key ).class_.contains( "Double" ) ) {
                    return new Double( value.toString() );
                } else if ( value != null && value instanceof List ) {
                    System.out.println( "Get instance of list " + value );
                    Class<?> c = this.fieldListClassesExport().get( key );
                    System.out.println( "with class :" + c );
                    List<Object> out = new ArrayList<Object>();
                    Constructor<?> con = null;

                    for ( Object v : (List<?>) value ) {
                        try {
                            con = c.getConstructor( Integer.class );
                            out.add( con.newInstance( Integer.valueOf( ((String) v ).trim() )));
                           
                        } catch ( Exception e ) {
                            try {
                                con = c.getConstructor( String.class );
                                out.add( con.newInstance( (String) v ) );
                            } catch ( Exception e1 ) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            e.printStackTrace();
                        }

                    }
                    return out;

                }
            }
        }
        return value;

    }

    public Object getIdValue( T bean ) {
        String s = this.getEntityFields().getIdField().name;
        Method m = this.entityFields.getGetter( this.beanClass, s );

        try {
            if ( m != null )
                return m.invoke( bean, (Object[]) null );
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
           System.out.println( "getIdValue not found" );
        }

        return null;
    }

    public Object getChildIdValue( T bean, String childname ) {

        Method m = this.entityFields.getGetter( this.beanClass, childname );
        Object child = null;
        String childId = this.getEntityFields().getChildIdName( childname );

        try {
            if ( m != null )
                child = m.invoke( bean, (Object[]) null );
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if ( child != null && childId != null && !( child instanceof Image ) ) {
            // System.out.println( "Child ID of
            // "+child.getClass().getSimpleName()+" is "+childId );
            m = this.entityFields.getGetter( child.getClass(), childId );
            try {
                return m.invoke( child, (Object[]) null );
            } catch ( Exception e ) {
                System.out.println( "Child getter invocation error" );
            }
        } else if ( childId == null && child != null ) {
            if ( child instanceof Enum<?> ) {
                child = ( (Enum<?>) child ).toString();
            }

        }

        return child;

    }

    public static BeanFactory<?> getClassFactory( String factoryClass ) {

        try {

            Class<?> classFactory_ = Class.forName( factoryClass );

            Constructor<?> factoryC = classFactory_.getConstructor();

            return (BeanFactory<?>) factoryC.newInstance();

        } catch ( Exception e ) {

            e.printStackTrace();
        }

        return null;
    }

    private Double round( double value, int places ) {
        if ( places < 0 )
            return 0.0;

        BigDecimal bd = new BigDecimal( Double.toString( value ) );
        bd = bd.setScale( places, RoundingMode.HALF_UP );
        return bd.doubleValue();
    }

    public List<String> fieldIgnoreExport() {
        List<String> out = new ArrayList<String>();
        return out;
    }

    public Map<String, Class<?>> fieldListClassesExport() {
        Map<String, Class<?>> c = new HashMap<String, Class<?>>();

        return c;
    }
}
