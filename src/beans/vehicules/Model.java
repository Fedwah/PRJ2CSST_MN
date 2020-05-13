package beans.vehicules;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Model {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
   
    private String titre;

    public Model( String titre ) {
        super();
        this.titre = titre;
    }
    
    public String getTitre() {
        return titre;
    }
}
