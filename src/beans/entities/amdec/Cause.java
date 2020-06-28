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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	private String cause;

	public Cause() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cause(int id, String cause) {
		super();
		this.id = id;
		this.cause = cause;
	}

	public Cause(int id) {
		super();
		this.id = id;
	}

	public Cause(String cause) {
		super();
		this.cause = cause;
	}
	
	// getters and setters 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	
	
	
	
	
	
	

}
