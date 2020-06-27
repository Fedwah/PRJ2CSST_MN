package beans.entities.amdec;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import beans.entities.pieces.Piece;
import beans.entities.vehicules.Vehicule;

@Entity
public class Detection implements Serializable {
	
	// attributes
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@NotNull
	private Vehicule v;
	
	@ManyToOne
	@NotNull
	private Piece p;
	
	@ManyToOne
	@NotNull
	private Defaillance defai;
	
	@ManyToOne
	@NotNull
	private Cause cause;
	
	@ManyToOne
	@NotNull
	private Effet eff;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date date ;
	
	private int gravite;
	
	private int freq;
	
	private int detection; 
	
	private int creticite;
	
	// Constructors

	public Detection() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Detection(Vehicule v, Piece p, Defaillance defai, Cause cause, Effet eff, int gravite, int freq,
			int detection) {
		super();
		this.v = v;
		this.p = p;
		this.defai = defai;
		this.cause = cause;
		this.eff = eff;
		this.gravite = gravite;
		this.freq = freq;
		this.detection = detection;
		this.creticite = this.freq * this.gravite * this.detection ;
	}



	public Detection(int id, Vehicule v, Piece p, Defaillance defai, Cause cause, Effet eff) {
		super();
		this.id = id;
		this.v = v;
		this.p = p;
		this.defai = defai;
		this.cause = cause;
		this.eff = eff;
	}

	public Detection(Vehicule v, Piece p, Defaillance defai, Cause cause, Effet eff) {
		super();
		this.v = v;
		this.p = p;
		this.defai = defai;
		this.cause = cause;
		this.eff = eff;
	}

	public Detection(int id) {
		super();
		this.id = id;
	}
	
	

	public Detection(Vehicule v, Piece p, Defaillance defai, Cause cause, Effet eff, Date date, int gravite, int freq,
			int detection) {
		super();
		this.v = v;
		this.p = p;
		this.defai = defai;
		this.cause = cause;
		this.eff = eff;
		this.date = date;
		this.gravite = gravite;
		this.freq = freq;
		this.detection = detection;
	}



	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Defaillance getDefai() {
		return defai;
	}

	public void setDefai(Defaillance defai) {
		this.defai = defai;
	}

	public Cause getCause() {
		return cause;
	}

	public void setCause(Cause cause) {
		this.cause = cause;
	}

	public Effet getEff() {
		return eff;
	}

	public void setEff(Effet eff) {
		this.eff = eff;
	}



	public int getGravite() {
		return gravite;
	}



	public void setGravite(int gravite) {
		this.gravite = gravite;
	}



	public int getFreq() {
		return freq;
	}



	public void setFreq(int freq) {
		this.freq = freq;
	}



	public int getDetection() {
		return detection;
	}



	public void setDetection(int detection) {
		this.detection = detection;
	}



	public int getCreticite() {
		return creticite;
	}



	public void setCreticite(int creticite) {
		this.creticite = creticite;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}	
	
	
	
}
