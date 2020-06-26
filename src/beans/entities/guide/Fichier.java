package beans.entities.guide;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class Fichier {

    @Id
    @Size(min = 5,max=200)
    private String nom;
    
    @Size(min=3)
    private String type;
    
    private long taille;
    
    private String description;
    
    
   
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private FileData data;
   
    
    public Fichier() {
        // TODO Auto-generated constructor stub
    }
    
    

    public Fichier( String nom, String type, String desciption, long taille , byte[] bin) {
        super();
        this.nom = nom;
        this.type = type;
        this.description = desciption;
        this.taille = taille;
        this.data = new FileData( bin );
    }


   
   



   



    public void setData( FileData data ) {
        this.data = data;
    }



    public long getTaille() {
        return taille;
    }





    public void setTaille( long taille ) {
        this.taille = taille;
    }




    

   

    public String getNom() {
        return nom;
    }



    public void setNom( String nom ) {
        this.nom = nom;
    }



    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }



    public FileData getData() {
        return data;
    }


    public String getNameType(){
        if(this.nom!=null && !this.nom.isEmpty()) {
            return this.nom.substring( nom.lastIndexOf( '.' )+1 ).toLowerCase();
        }
        return "";
        
    }
   
    
    
    

}
