package beans.entities.amdec;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
@Entity
public class Effet implements Serializable {
    
	@Id	
	@NotEmpty
	private String effet;

	public Effet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Effet(String effet) {
		super();
		this.effet = effet;
	}
	
	// getters and setters

	

	public String getEffet() {
		return effet;
	}

	public void setEffet(String effet) {
		this.effet = effet;
	}
	
	
	
	

}
