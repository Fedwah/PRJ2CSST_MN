package beans.entities.vehicules;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class CategorieVehicule {
    
    @Id
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
