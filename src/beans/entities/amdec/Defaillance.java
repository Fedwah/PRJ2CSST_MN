package beans.entities.amdec;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Defaillance implements Serializable {
    
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	private String defaillance;
	
	// constructors

	public Defaillance(int id, String defaillance) {
		super();
		this.id = id;
		this.defaillance = defaillance;
	}

	public Defaillance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Defaillance(int id) {
		super();
		this.id = id;
	}
	
	
	
	public Defaillance(String defaillance) {
		super();
		this.defaillance = defaillance;
	}

	// getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDefaillance() {
		return defaillance;
	}

	public void setDefaillance(String defaillance) {
		this.defaillance = defaillance;
	}
	
	
	

}
