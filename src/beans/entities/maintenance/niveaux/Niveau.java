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
	
	// criticité max 
	private int maxC;
	
	// criticité min 
	private int minC;


	// constructors
	public Niveau() {

	}

	public Niveau(int idNiv) {
		super();
		this.idNiv = idNiv;
	}
	
	


	public Niveau(int idNiv, String niveau, int maxC, int minC) {
		super();
		this.idNiv = idNiv;
		this.niveau = niveau;
		this.maxC = maxC;
		this.minC = minC;
	}

	public Niveau(String niveau, int maxC, int minC) {
		super();
		this.niveau = niveau;
		this.maxC = maxC;
		this.minC = minC;
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

	public int getMaxC() {
		return maxC;
	}

	public void setMaxC(int maxC) {
		this.maxC = maxC;
	}

	public int getMinC() {
		return minC;
	}

	public void setMinC(int minC) {
		this.minC = minC;
	}
}
