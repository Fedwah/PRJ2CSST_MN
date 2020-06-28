package beans.entities.amdec;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
@Entity
public class Cause implements Serializable{
    
	
	@Id
	@NotEmpty
	private String cause;

	public Cause() {
		super();
		// TODO Auto-generated constructor stub
	}

	



	public Cause(String cause) {
		super();
		this.cause = cause;
	}
	
	// getters and setters 


	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	
	
	
	
	
	
	

}
