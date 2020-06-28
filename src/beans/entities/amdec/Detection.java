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
	private Vehicule vehicule;
	
	@ManyToOne
	@NotNull
	private Piece piece;
	
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
	
	private int frequence;
	
	private int detection; 
	
	private int criticite;
	
	// Constructors

	public Detection() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Detection(Vehicule v, Piece p, Defaillance defai, Cause cause, Effet eff, int gravite, int freq,
			int detection) {
		super();
		this.vehicule = v;
		this.piece = p;
		this.defai = defai;
		this.cause = cause;
		this.eff = eff;
		this.gravite = gravite;
		this.frequence = freq;
		this.detection = detection;
		this.criticite = this.frequence * this.gravite * this.detection ;
	}



	public Detection(int id, Vehicule v, Piece p, Defaillance defai, Cause cause, Effet eff) {
		super();
		this.id = id;
		this.vehicule = v;
		this.piece = p;
		this.defai = defai;
		this.cause = cause;
		this.eff = eff;
	}

	public Detection(Vehicule v, Piece p, Defaillance defai, Cause cause, Effet eff) {
		super();
		this.vehicule = v;
		this.piece = p;
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
		this.vehicule = v;
		this.piece = p;
		this.defai = defai;
		this.cause = cause;
		this.eff = eff;
		this.date = date;
		this.gravite = gravite;
		this.frequence = freq;
		this.detection = detection;
	}

	

	// Getters and setters

    public int getId() {
        return id;
    }



    public void setId( int id ) {
        this.id = id;
    }



    public Vehicule getVehicule() {
        return vehicule;
    }



    public void setVehicule( Vehicule vehicule ) {
        this.vehicule = vehicule;
    }



    public Piece getPiece() {
        return piece;
    }



    public void setPiece( Piece piece ) {
        this.piece = piece;
    }



    public Defaillance getDefai() {
        return defai;
    }



    public void setDefai( Defaillance defai ) {
        this.defai = defai;
    }



    public Cause getCause() {
        return cause;
    }



    public void setCause( Cause cause ) {
        this.cause = cause;
    }



    public Effet getEff() {
        return eff;
    }



    public void setEff( Effet eff ) {
        this.eff = eff;
    }



    public Date getDate() {
        return date;
    }



    public void setDate( Date date ) {
        this.date = date;
    }



    public int getGravite() {
        return gravite;
    }



    public void setGravite( int gravite ) {
        this.gravite = gravite;
    }



    public int getFrequence() {
        return frequence;
    }



    public void setFrequence( int frequence ) {
        this.frequence = frequence;
    }



    public int getDetection() {
        return detection;
    }



    public void setDetection( int detection ) {
        this.detection = detection;
    }



    public int getCriticite() {
        return criticite;
    }



    public void setCriticite( int criticite ) {
        this.criticite = criticite;
    }



	

	
	
}
