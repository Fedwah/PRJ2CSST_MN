package beans.entities.maintenance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import beans.entities.maintenance.niveaux.Niveau;
import beans.entities.pieces.Piece;
import beans.entities.regions.unites.Unite;
import beans.entities.vehicules.Vehicule;

@Entity
public class Maintenance implements Serializable{
	
	// ID
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idMaintenance;
	
	// date Debut
	
	@NotNull
	@FutureOrPresent
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	// date Fin
	
	@FutureOrPresent
	@Temporal(TemporalType.DATE)
	@JoinColumn(nullable=true)
	private Date endDate;
	
	// Vehicule
	
	@NotNull
	private Vehicule v;
	
	// Piece 
	
	@JoinColumn(nullable = true)
	private Piece p;

	
	// Niveau de maintenance
	@NotNull
	private Niveau niv;
	
	@NotNull
	private Unite un;
	
	// AMDEC peut etre
	// TODO
	
	// Constructors
	
	


	public Maintenance() {

	}

	
	public Maintenance(Date startDate, Date endDate, Vehicule v, Piece p, Niveau niv) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.v = v;
		this.p = p;
		this.niv = niv;
	}


	public Maintenance(int idMaintenance, Date startDate, Date endDate, Vehicule v, Piece p, Niveau niv) {
		super();
		this.idMaintenance = idMaintenance;
		this.startDate = startDate;
		this.endDate = endDate;
		this.v = v;
		this.p = p;
		this.niv = niv;
	}


	// Getters and setters
	
	public int getIdMaintenance() {
		return idMaintenance;
	}


	public void setIdMaintenance(int idMaintenance) {
		this.idMaintenance = idMaintenance;
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


	public Vehicule getV() {
		return v;
	}


	public void setV(Vehicule v) {
		this.v = v;
	}


	public Piece getP() {
		return p;
	}


	public void setP(Piece p) {
		this.p = p;
	}


	public Niveau getNiv() {
		return niv;
	}


	public void setNiv(Niveau niv) {
		this.niv = niv;
	}


	public Unite getUn() {
		return un;
	}


	public void setUn(Unite un) {
		this.un = un;
	}
	
	
	
		
}
