package beans.entities.vehicules;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class CategorieVehicule implements Serializable {
    
    @Id
    @NotEmpty
    @Size(min=1,max=10)
    private String titre;
    
    public CategorieVehicule() {
        // TODO Auto-generated constructor stub
    }
     
    
    public CategorieVehicule( String titre ) {
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
