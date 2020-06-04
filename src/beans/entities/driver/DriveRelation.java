package beans.entities.driver;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Constraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;

import beans.entities.vehicules.Vehicule;

@Entity
public class DriveRelation implements Serializable {
	// id
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id ;
	// start driving this car
	@PastOrPresent
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
	// Default constructor
	public DriveRelation() {
	}

	//constructor without id, endDate
	public DriveRelation(Date startDate, Driver driver, Vehicule car) {
		super();
		this.startDate = startDate;
		this.driver = driver;
		this.car = car;
	}
	// constructor all fields
	public DriveRelation(int id, Date startDate, Date endDate, Driver driver, Vehicule car) {
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
