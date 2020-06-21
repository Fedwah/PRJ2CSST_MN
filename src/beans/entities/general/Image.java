package beans.entities.general;


import java.io.Serializable;
import java.util.Arrays;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Image implements Serializable {

    @Id
    @NotEmpty
    @Size(min = 2,max=100)
    private String titre;

    @NotNull
    private byte[] bin;

    public Image() {
        // TODO Auto-generated constructor stub
    }

    
    public Image( String titre ) {
        super();
        this.titre = titre;
    }

    
    public Image( String titre, byte[] bin ) {
        super();
        this.titre = titre;
        this.bin = bin;
    }


    public String getTitre() {
        return this.titre;
    }

    public void setTitre( String titre ) {
        
        if(titre != null) {
            if(!titre.isEmpty()) {
                titre = extractName( titre );
                //System.out.println("new titre image "+titre.length());
                String type = titre.substring( titre.lastIndexOf( '.' ) );

                String name = titre.substring( 0, titre.lastIndexOf( '.' ) );

                this.titre = name + '-' + System.currentTimeMillis() + type;
                //System.out.println( "Image set : " + this.titre );
            }
           
        }
       
    }

    public byte[] getBin() {
        return this.bin;
    }

    public void setBinary( byte[] binary ) {
        this.bin = binary;
    }
     public String getOriginalTitre(){
         if(this.titre!=null) {
             return this.titre.substring( 0, this.titre.lastIndexOf( "-" ) );
         }else {
             return "";
         }
        
     }
     
     @Override
    public boolean equals( Object obj ) {
        // TODO Auto-generated method stub
         if(obj==null) {
             return false;
         }else {
             return this.getOriginalTitre() == ((Image)obj).getOriginalTitre();
         }
        
    }
     
   private String extractName(String cheminFichier) {
       int index = cheminFichier.lastIndexOf( '\\' );
       if(index!=-1) cheminFichier = cheminFichier.substring(index+1);
       
       return cheminFichier;
   }
}
