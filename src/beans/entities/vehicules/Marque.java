package beans.entities.vehicules;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.json.JSONPropertyIgnore;

import beans.entities.general.Image;

@Entity
public class Marque implements Serializable {
    @Id
    @NotEmpty
    @Size(min = 2,max = 20)
    private String titre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private Image image;
   
    
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,mappedBy = "marque")
    private List<Modele> modeles;
   
    public Marque() {
        this.modeles = new ArrayList<Modele>();
    }
    
    public Marque( String titre ) {
        super();
        this.titre = titre;
        this.modeles = new ArrayList<Modele>();
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
    
    @JSONPropertyIgnore
    public List<Modele> getModeles() {
        return modeles;
    }
    
    public void setModeles( ArrayList<Modele> modeles ) {
        this.modeles = modeles;
    }
}
