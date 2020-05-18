package beans.entities.vehicules;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Modele {
    
    @Id
    @Size(min=1,max=10)
    private String titre;
    
    public Modele() {
        // TODO Auto-generated constructor stub
    }
    public Modele( String titre ) {
        super();
        this.titre = titre;
    }
    public void setTitre( String titre ) {
        this.titre = titre;
    }
    public String getTitre() {
        return titre;
    }
}
