package beans.session.general.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import beans.session.general.BeanFactory;
import beans.session.general.fields.FieldDefinition;

public class Excel<T> {

    

    public Excel(  ) {
    
    }

    public Workbook obtenirModeleExcel( BeanFactory<T> beanFactory, String[] fieldsToIgnore ) {
        Workbook wk = new XSSFWorkbook();
        
        Sheet sheet = wk.createSheet( beanFactory.getClassName() );
       
        
        this.writeHeader( wk, sheet, beanFactory.getEntityFields().fields()
                , beanFactory.getEntityFields().getIdField().name, fieldsToIgnore );
        
        return wk;

    }

    public Workbook exportExcel( BeanFactory<T> beanFactory, String[] fieldsToIgnore ,List<Object[]> beans) {
        Workbook wk = obtenirModeleExcel( beanFactory, fieldsToIgnore );
        int i = 1;
        for ( Object[] values : beans ) {
            writeColumns( values,wk.getSheetAt( 0 ),i, null );
            i++;
        }
        return wk;
    }

    public List<Map<String,Object>> importExcel( BufferedInputStream  is)  {
        BufferedInputStream fis =  is;
        Workbook wb = null;
        List<Map<String,Object>> out = new ArrayList<Map<String,Object>>();
        try {
            wb = new XSSFWorkbook( fis );
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if ( wb != null ) {
            XSSFSheet sheet = (XSSFSheet) wb.getSheetAt( 0 );
            Map<String, Object> header = new LinkedHashMap<String, Object>(); 
            Iterator<Row> rowIt = sheet.iterator();
            Map<String, Object> row = null;
            if ( rowIt.hasNext() ) {
                readHeader(header, rowIt.next() );
            }

            while ( rowIt.hasNext() ) {
                row = new LinkedHashMap<String, Object>(header); 
                readColumns(row, rowIt.next());
                out.add( row );
                
            }
        }
        return out;
    }

  
    
    private void writeHeader(Workbook wk,Sheet sheet,Map<String, FieldDefinition> fields , String idName,String[] fieldsToIgnore) {
     
        Boolean ignore = false;
        
        
        List<Object>values = new ArrayList<Object>();
        
        Font headerFont = wk.createFont();
        headerFont.setBold( true );
        headerFont.setFontHeightInPoints( (short) 12 );
        headerFont.setColor( IndexedColors.RED.getIndex() );

        CellStyle headerCellStyle = wk.createCellStyle();
        headerCellStyle.setFont( headerFont );
        
        String cellVal = "";
        for ( Map.Entry<String, FieldDefinition> f : fields.entrySet() ) {

            ignore = false;
            if ( fieldsToIgnore != null ) {

                if ( Arrays.binarySearch( fieldsToIgnore, f.getKey() ) >= 0 ) {
                    ignore = true;
                }

            }

            if ( !ignore ) {

                
                if ( f.getValue().class_.equals( "java.util.Date" ) ) {
                    cellVal = ( f.getValue().name + "(yyyy-MM-dd HH:mm:ss)" );
                
                } else {
                    cellVal = ( f.getValue().name );
                }
                
                if(f.getValue().name.equals(idName))
                    cellVal = cellVal+"(id)";
                values.add( cellVal );
                
           
            }
            
           

        }
        writeColumns( values.toArray(), sheet,0, headerCellStyle );
    }
    
    private void writeColumns(Object[] values ,Sheet sheet ,int row_num ,CellStyle style) {
        int i = 0;
      
        Row row = sheet.createRow( row_num );
        Cell c = null;
        for ( Object v : values) {
            c = row.createCell( i );
            if(v instanceof Boolean)
                c.setCellValue( (Boolean)v );
            else if(v instanceof Date ) {
                System.out.println( "write cell date "+v );
                c.setCellValue( (Date)v);
            }
            else if(v instanceof Double)
                c.setCellValue( (Double) v );
            else if(v instanceof Integer)
                c.setCellValue( ((Integer)v ).doubleValue());
            else {
                Date d = getDate( (String)v );
                if(d!=null) {
                    //System.out.println( "Transformed to Date" +d);
                    c.setCellValue( d );
                }else {
                    d= this.getDateTime( (String)v );
                    if(d!=null) {
                        //System.out.println( "Transformed to Date" +d);
                        c.setCellValue( d ); 
                    }else {
                        //System.out.println( "Write string "+v );
                        c.setCellValue((String) v );
                    }
                    
                }
            }
                
            
           if(style!=null) c.setCellStyle( style );
           sheet.autoSizeColumn( i );
           i++;
        }
    }
    
    private Date getDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return null;
        }
        
    }
    
    private Date getDateTime(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return null;
        }
        
    }
    
    private void readHeader( Map<String, Object> values, Row header ) {
        Iterator<Cell> cellIterator = header.cellIterator();
        while ( cellIterator.hasNext() ) {
            Cell cell = (Cell) cellIterator.next();
            values.put( cell.getStringCellValue(), null );
        }
    }
    
  
    private void readColumns( Map<String, Object> values, Row row ) {

        Object[] header = values.keySet().toArray();

        int lastColumn = row.getLastCellNum();

        for ( int col = 0; col < lastColumn; col++ ) {
            Cell cell = row.getCell( col, MissingCellPolicy.RETURN_BLANK_AS_NULL );
            if ( cell == null ) {
                values.put( (String) header[col], null );
            } else {
                switch ( cell.getCellType() ) {
                case NUMERIC:
                    if ( DateUtil.isCellDateFormatted( cell ) ) {
                        values.put( (String) header[col], cell.getDateCellValue() );
                    } else {
                        String s = cell.toString();
                        
                        int index = s.indexOf( '.' );
                        String intPart = s.substring( 0,index );
                        String decPart = s.substring( index+1 );
                        
                        
                      
                        if((decPart.lastIndexOf( '0' )+1)== decPart.length()) {
                            //System.out.println( s+ " put as integer" );
                            values.put( (String) header[col],new Integer(intPart));
                        }else {
                            //System.out.println( s+ " put as double" );
                            values.put( (String) header[col],new Double(s));
                        }
                      
                    }

                    break;
                case STRING:
                    String s = cell.getStringCellValue();
                    
                    values.put( (String) header[col], cleanText( s ));
                    break;
                case BOOLEAN:
                    values.put( (String) header[col], cell.getBooleanCellValue() );
                    break;
                default:
                    break;
                }
            }
        }
    }
    
    private String cleanText(String text) {
        return text.replaceAll("^\"|\"$", "");
    }
}
