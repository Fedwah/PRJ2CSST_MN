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

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import beans.entities.general.Image;
import beans.session.general.BeanFactory;
import beans.session.general.fields.FieldDefinition;

public class Excel<T> {
    
    private int indexImg;
    public Excel() {
        indexImg = 0;
    }

    public Workbook obtenirModeleExcel( BeanFactory<T> beanFactory, String[] fieldsToIgnore ) {
        Workbook wk = new XSSFWorkbook();

        Sheet sheet = wk.createSheet( beanFactory.getClassName() );

        this.writeHeader( wk, sheet, beanFactory,
                beanFactory.getEntityFields().getIdField().name, fieldsToIgnore );

        return wk;

    }

    public Workbook exportExcel( BeanFactory<T> beanFactory, String[] fieldsToIgnore, List<Object[]> beans ) {
        Workbook wk = obtenirModeleExcel( beanFactory, fieldsToIgnore );
        Object[] values = null;
        for ( int i = 0; i < beans.size(); i++ ) {
            values = beans.get( i );
            writeColumns( values, wk, wk.getSheetAt( 0 ), i + 1, null );
        }

        return wk;
    }

    public List<Map<String, Object>> importExcel( BufferedInputStream is ) {
        BufferedInputStream fis = is;
        Workbook wb = null;
        List<Map<String, Object>> out = new ArrayList<Map<String, Object>>();
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
            Row r = null;
            if ( rowIt.hasNext() ) {
                readHeader( header, rowIt.next() );
            }

            List<Image> imgs = readImages( wb );
            this.indexImg = 0;
            while ( rowIt.hasNext() ) {
                row = new LinkedHashMap<String, Object>( header );
                r = rowIt.next();
                System.out.println( "readed "+imgs.size()+" images" );
                if(!imgs.isEmpty()) {
                   
                    readColumns( row, r, imgs.get( indexImg ));
                }else {
                    
                    readColumns( row, r, null);
                }
               
                System.out.println( "index :"+indexImg);
                out.add( row );

            }
        }
        return out;
    }

    private void writeHeader( Workbook wk, Sheet sheet, BeanFactory<?> beanF, String idName,
            String[] fieldsToIgnore ) {

        Boolean ignore = false;
        Map<String, FieldDefinition> fields = beanF.getEntityFields().fields();
        List<Object> values = new ArrayList<Object>();

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
                if ( f.getValue().isBasicClass ) {
                    if ( f.getValue().class_.equals( "java.util.Date" ) ) {
                        cellVal = ( f.getValue().name + "(yyyy-MM-dd HH:mm:ss)" );

                    } else {
                        cellVal = ( f.getValue().name );
                    }

                    if ( f.getValue().name.equals( idName ) )
                        cellVal = cellVal + "(id)";
                } else {
                    String childId = beanF.getEntityFields().getChildIdName( f.getKey() );
                    if ( childId != null && !childId.isEmpty() && !f.getValue().class_.contains( "Image" ) )
                        cellVal = ( f.getValue().name ) + "." + childId;
                    else if ( f.getValue().class_.contains( "Image" ) )
                        cellVal = ( f.getValue().name ) + "(Image)";
                    else
                        cellVal = ( f.getValue().name ); 
                }

                values.add( cellVal );

            }

        }
        writeColumns( values.toArray(), wk, sheet, 0, headerCellStyle );
    }

    private void writeColumns( Object[] values, Workbook wk, Sheet sheet, int row_num, CellStyle style ) {

        Row row = sheet.createRow( row_num );
        Cell c = null;
        Boolean img = false;
        Object v = null;
        
        CellStyle styleDate = wk.createCellStyle();
        styleDate.setDataFormat( wk.createDataFormat().getFormat("yyyy-mm-dd"));
        
        for ( int i = 0; i < values.length; i++ ) {
            v = values[i];

            c = row.createCell( i );
            img = false;
            if ( v instanceof Boolean )
                c.setCellValue( (Boolean) v );
            else if ( v instanceof Date ) {
                //System.out.println( "write cell date " + v );
                c.setCellValue( getDate(((Date) v).toString()));
                c.setCellStyle( styleDate );
            } else if ( v instanceof Double )
                c.setCellValue( (Double) v );
            else if ( v instanceof Integer )
                c.setCellValue( ( (Integer) v ).doubleValue() );
            else if ( v instanceof Image ) {
                //System.out.println( "wirte an image" );
                if ( v != null ) {
                    writeImage( (Image) v, wk, sheet, row, c );
                }
                img = true;
            } else if ( v instanceof String ) {

                Date d = getDate( (String) v );
                if ( d != null ) {
                    // System.out.println( "Transformed to Date" +d);
                    c.setCellValue( d );
                } else {
                    d = this.getDateTime( (String) v );
                    if ( d != null ) {
                        // System.out.println( "Transformed to Date" +d);
                        c.setCellValue( d );
                    } else {
                        // System.out.println( "Write string "+v );
                        c.setCellValue( (String) v );
                    }

                }
            } else if ( v instanceof List<?> ) {
                //System.out.println( "write a list" );
                String s = ( (List<?>) v ).toString();
                if ( !s.isEmpty() ) {
                    c.setCellValue( s.substring( 1, s.length() - 1 ) );
                } else {
                    c.setCellValue( "" );
                }

            }
            
            if(v==null) {
                //System.out.println( "export default image" );
                c.setCellValue( "" );
            }

            if ( style != null && !img)
                c.setCellStyle( style );
            if ( !img )
                sheet.autoSizeColumn( i );

        }

    }

    private Date getDate( String inDate ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        dateFormat.setLenient( false );
        try {
            return dateFormat.parse( inDate.trim() );
        } catch ( ParseException pe ) {
            return null;
        }

    }

    private Date getDateTime( String inDate ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss:ms" );
        dateFormat.setLenient( false );
        try {
            return dateFormat.parse( inDate.trim() );
        } catch ( ParseException pe ) {
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

    private void readColumns( Map<String, Object> values, Row row, Image img) {

        Object[] header = values.keySet().toArray();

        int lastColumn = row.getLastCellNum();
        
        for ( int col = 0; col < lastColumn; col++ ) {
            Cell cell = row.getCell( col, MissingCellPolicy.RETURN_BLANK_AS_NULL );
            if ( cell == null ) {
                System.out.println( "cell "+col+"is empty" );
                
                if ( !((String)header[col]).contains( "(Image)" ) ) {
                    values.put( (String) header[col], null );
                } else {
                    this.indexImg++;
                    System.out.println( "put image" );
                    values.put( (String) header[col], img);
                }

            } else {
                System.out.println( "Cell "+col+"is not empty :"+cell.getCellType() );
                switch ( cell.getCellType() ) {
                case NUMERIC:
                    if ( DateUtil.isCellDateFormatted( cell ) ) {
                        values.put( (String) header[col], cell.getDateCellValue() );
                    } else {
                        String s = cell.toString();

                        int index = s.indexOf( '.' );
                        String intPart = s.substring( 0, index );
                        String decPart = s.substring( index + 1 );

                        if ( ( decPart.lastIndexOf( '0' ) + 1 ) == decPart.length() ) {
                            // System.out.println( s+ " put as integer" );
                            values.put( (String) header[col], new Integer( intPart ) );
                        } else {
                            // System.out.println( s+ " put as double" );
                            values.put( (String) header[col], new Double( s ) );
                        }

                    }

                    break;
                case STRING:
                    String s = cell.getStringCellValue();
                    String[] list = s.split( "," );
                    
                   System.out.println( "readed a string cell :"+s );
                    if(list.length==1) {
                        if(s.isEmpty()) {
                           
                            values.put((String) header[col], null);
                        }else {
                            values.put( (String) header[col], cleanText( s ) );
                        } 
                    }else {
                        values.put((String) header[col], Arrays.asList( list ) );
                    }
                   
                   
                    break;
                case BOOLEAN:
                    values.put( (String) header[col], cell.getBooleanCellValue() );
                    break;
                default:
                    break;
                }
            }
        }
        
        if(header.length>lastColumn) {
            System.out.println( "insert Image" );
            values.put((String)header[lastColumn],img );
            this.indexImg++;
        }
    }

    private String cleanText( String text ) {
        return text.replaceAll( "^\"|\"$", "" );
    }

    private void writeImage( Image img, Workbook wk, Sheet sheet, Row row, Cell col ) {
        int type = 0;

        switch ( img.getTitre().substring( img.getTitre().lastIndexOf( '.' ) + 1 ) ) {
        case "jpg":
        case "jpeg":
            type = Workbook.PICTURE_TYPE_JPEG;
            break;
        case "dib":
            type = Workbook.PICTURE_TYPE_DIB;
            break;
        case "emf":
            type = Workbook.PICTURE_TYPE_EMF;
            break;
        case "pict":
            type = Workbook.PICTURE_TYPE_PICT;
            break;
        case "wmf":
            type = Workbook.PICTURE_TYPE_WMF;
            break;
        default:
            type = Workbook.PICTURE_TYPE_PNG;

            break;
        }

        int pictureIdx = wk.addPicture( img.getBin(), type );

        // Returns an object that handles instantiating concrete classes
        CreationHelper helper = wk.getCreationHelper();
        // Creates the top-level drawing patriarch.
        Drawing<?> drawing = sheet.createDrawingPatriarch();

        ClientAnchor anchor = helper.createClientAnchor();

        // create an anchor with upper left cell _and_ bottom right cell
        anchor.setCol1( col.getColumnIndex() ); // Column B
        anchor.setRow1( row.getRowNum() ); // Row 3
        anchor.setCol2( col.getColumnIndex() + 1 ); // Column C
        anchor.setRow2( row.getRowNum() + 1 ); // Row 4

        Picture pict = drawing.createPicture( anchor, pictureIdx );

        // set width to n character widths = count characters * 256
        int widthUnits = 50 * 256;

        sheet.setColumnWidth( col.getColumnIndex(), widthUnits );

        // set height to n points in twips = n * 20
        short heightUnits = 120 * 20;
        col.getRow().setHeight( heightUnits );
    }

    private List<Image> readImages( Workbook wk ) {
        List<? extends PictureData> lst = wk.getAllPictures();
        List<Image> imgs = new ArrayList<Image>();
        int index = 0;
        for ( PictureData pict : lst ) {

            String ext = pict.suggestFileExtension();

            imgs.add( new Image( "image_imported-"+index+"." + ext, pict.getData() ) );
            index++;
        }
        return imgs;
    }
}
