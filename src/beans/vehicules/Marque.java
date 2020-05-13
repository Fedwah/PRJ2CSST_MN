package beans.vehicules;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Marque implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
   
    private String titre;

    public Marque( String titre ) {
        super();
        this.titre = titre;
    }
    
    public String getTitre() {
        return titre;
    }
    
}
