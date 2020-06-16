package beans.entities.vehicules;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import beans.entities.driver.Driver;

@Entity
public class AffectationConducteur implements Serializable {
    
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    

    // id
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id ;
	
	// start driving this car


	@NotNull
	@Temporal(TemporalType.DATE)
	private Date startDate ;
	
	// end driving this car 
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	// driver 
	@OneToOne
	@NotNull
	private Driver driver;
	
	// car
	@OneToOne
	@NotNull
	private Vehicule car;
	
	
	@OneToMany(cascade = CascadeType.REMOVE , mappedBy = "affectation")
	private List<Mission> missions;
	
	
	// Default constructor
	public AffectationConducteur() {
	}

	//constructor without id, endDate
	public AffectationConducteur(Date startDate, Driver driver, Vehicule car) {
		super();
		this.startDate = startDate;
		this.driver = driver;
		this.car = car;
	}
	
	// constructor all fields
	public AffectationConducteur(int id, Date startDate, Date endDate, Driver driver, Vehicule car) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.driver = driver;
		this.car = car;
	}
	
	// getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Vehicule getCar() {
		return car;
	}

	public void setCar(Vehicule car) {
		this.car = car;
	}
	
	
	
}
