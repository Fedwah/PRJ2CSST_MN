package beans.entities.vehicules;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Modele implements Serializable {
    
   
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
   
    
    public Modele(String titre, Marque marque) {
		super();
		this.titre = titre;
		this.marque = marque;
	}


	public Modele( Integer id ) {
        super();
        this.id = id;
    }

	public Modele(String titre) {
	    super();
	    this.id = null;
	    this.titre = titre;
	    this.marque = null;
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
