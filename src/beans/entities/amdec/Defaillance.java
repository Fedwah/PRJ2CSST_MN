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
	@NotEmpty
	private String defaillance;
	
	// constructors

	
	public Defaillance() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	public Defaillance(String defaillance) {
		super();
		this.defaillance = defaillance;
	}

	// getters and setters

	public String getDefaillance() {
		return defaillance;
	}

	public void setDefaillance(String defaillance) {
		this.defaillance = defaillance;
	}
	
	
	

}
