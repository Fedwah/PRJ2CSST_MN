package beans.entities.maintenance.niveaux;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import beans.entities.regions.unites.Unite;

@Entity
public class Niveau implements Serializable {
	
	// id
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idNiv;
	
	// nom 	
	@NotEmpty
	@Size(min=3,max=20)
	private String niveau;
	
	// unite
	
	@NotNull
	private Unite un;


	// constructors
	
	public Niveau(int idNiv, String niveau, Unite un) {
		super();
		this.idNiv = idNiv;
		this.niveau = niveau;
		this.un = un;
	}


	public Niveau(String niveau, Unite un) {
		super();
		this.niveau = niveau;
		this.un = un;
	}


	public Niveau() {

	}

	public Niveau(int idNiv) {
		super();
		this.idNiv = idNiv;
	}
	// getters and setters 
	



	public int getIdNiv() {
		return idNiv;
	}


	public void setIdNiv(int idNiv) {
		this.idNiv = idNiv;
	}


	public String getNiveau() {
		return niveau;
	}


	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}


	public Unite getUn() {
		return un;
	}


	public void setUn(Unite un) {
		this.un = un;
	}



	
	
	
	

}
