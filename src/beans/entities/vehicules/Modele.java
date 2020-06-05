package beans.entities.vehicules;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class Modele {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Size(min=1,max=10)
    private String titre;
    
    @ManyToOne
    private Marque marque;
    
    public Modele() {
        // TODO Auto-generated constructor stub
    }
   
    
    public Modele( Integer id ) {
        super();
        this.id = id;
    }


    public void setTitre( String titre ) {
        this.titre = titre;
    }
    public String getTitre() {
        return titre;
    }
    
    public Marque getMarque() {
        return marque;
    }
    
    public void setMarque( Marque marque ) {
        this.marque = marque;
    }
    
    public Integer getId() {
        return id;
    }
}
