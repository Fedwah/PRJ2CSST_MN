package beans.entities.vehicules;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class EtatVehicule implements Serializable {
    
    @Id
    @NotEmpty
    @Size(min = 1,max = 20)
    private String titre;

    public EtatVehicule() {
        // TODO Auto-generated constructor stub
    }
    public EtatVehicule( String titre ) {
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
