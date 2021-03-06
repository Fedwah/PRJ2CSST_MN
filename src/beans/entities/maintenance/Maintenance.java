package beans.entities.maintenance;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import beans.entities.amdec.Instruction;
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
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	// date Fin
	

	@Temporal(TemporalType.DATE)
	@JoinColumn(nullable=true)
	private Date endDate = null;
	
	// Vehicule
	
	@NotNull
	@ManyToOne
	private Vehicule v;
	// instruction
	@ManyToMany (fetch = FetchType.EAGER)
	private List<Instruction> instructions;
	// Niveau de maintenance
	
	private Niveau niv;
	
	@NotNull
	@ManyToOne
	private Unite un;
	
	// AMDEC peut etre
	// TODO
	
	// Constructors
	
	


	public Maintenance() {

	}
	
	

	public Maintenance(int idMaintenance, Date startDate, Date endDate, Vehicule v, List<Instruction> instructions,
			Niveau niv, Unite un) {
		super();
		this.idMaintenance = idMaintenance;
		this.startDate = startDate;
		this.endDate = endDate;
		this.v = v;
		this.instructions = instructions;
		this.niv = niv;
		this.un = un;
	}

	

	public Maintenance(Date startDate, Vehicule v, List<Instruction> instructions, Niveau niv, Unite un) {
		super();
		this.startDate = startDate;
		this.v = v;
		this.instructions = instructions;
		this.niv = niv;
		this.un = un;
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
	
	

	public List<Instruction> getInstructions() {
		return instructions;
	}



	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}



	public int getDay()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.startDate);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	

	
	
		
}
