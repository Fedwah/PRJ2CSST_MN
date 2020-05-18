package beans.entities.vehicules;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceUnit;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import beans.entities.general.Image;

@Entity
public class Marque implements Serializable {
    @Id
    @NotEmpty
    @Size(min = 2,max = 20)
    private String titre;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;
   
    
    public Marque() {
        // TODO Auto-generated constructor stub
    }
    
    public Marque( String titre ) {
        super();
        this.titre = titre;
    }
    
    public String getTitre() {
        return titre;
    }
    public Image getImage() {
        return image;
    }

    public void setTitre( String titre ) {
        this.titre = titre;
    }

    public void setImage( Image image ) {
        this.image = image;
    }
    
    
}
