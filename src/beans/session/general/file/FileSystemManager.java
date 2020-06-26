package beans.session.general.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.compress.utils.IOUtils;

public class FileSystemManager {
    
    public static final String DEFAULT_DIR_FILE_NAME="Fichier(MN)";
    private String dirFilesPath;
    private String dirFilesName;
    
    public FileSystemManager() {
        this("");
    }
    
    
    
    
    public FileSystemManager( String dirFilesName ) {
        if(dirFilesName==null || dirFilesName.isEmpty()) {
            this.dirFilesName = DEFAULT_DIR_FILE_NAME;
        }else {
            this.dirFilesName = dirFilesName;
        }
       
        this.dirFilesPath = System.getProperty("user.home");
        this.createDirIfNotExist( this.dirFilesPath+"/"+this.dirFilesName );
    }

   


    public void ajouter(String file , InputStream in ) throws IOException {
        File f = new File( this.getPath( file ) );
        OutputStream out = new FileOutputStream( f );
       
        byte[] buffer = new byte[8 * 1024];
        int bytesRead;
        
        
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
    }
    
    public void supprimer(String file) {
        File f = new File( getPath(file) );
        if(file!=null) {
            f.delete();
        }
    }
    
    public List<File> lister(FilenameFilter fileFilter){
   
        try {
            File f = new File(this.getPath());

    
            File[] files = f.listFiles(fileFilter);
            return Arrays.asList( files );
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new ArrayList<File>();
    }
    
    
    private void createDirIfNotExist(String path) {
        
        if(path!=null && !path.isEmpty()) {
            File directory = new File(path);
            if (! directory.exists()){
                directory.mkdirs();
                System.out.println( "create dir : "+path );
            }
        }
       
    }
    
    public String getPath() {
        return this.dirFilesPath+'/'+this.dirFilesName;
    }
    
    public String getPath(String addFile) {
        return getPath()+"/"+addFile;
    }
    
    public byte[] read(String file) {
        File f = new File( getPath() +'\\'+ file);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int length;
        byte[] buffer = new byte[1024];
        
        try {
            FileInputStream in = new FileInputStream( f );
           
            while ( ( length = in.read( buffer ) ) != -1 )
                out.write( buffer, 0, length );
            
            return out.toByteArray();
            
        } catch (Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
    
}
