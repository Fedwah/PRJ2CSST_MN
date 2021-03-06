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
	
	//attributes
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date date ;
	
	@ManyToOne
	private Instruction instruction;
	
	@ManyToOne
    private Vehicule vehicule;
	
	// Constructors
	public Detection() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    public Detection( int id ) {
        super();
        this.id = id;
    }

    

    public Detection( Date date, Instruction instruction , Vehicule vehicule) {
        super();
        this.date = date;
        this.instruction = instruction;
        this.vehicule = vehicule;
    }
    
    public Detection( Instruction instruction,Vehicule vehicule) {
        this( new Date(), instruction, vehicule );
    }

    public int getId() {
        return id;
    }


    public void setId( int id ) {
        this.id = id;
    }


   
    public Date getDate() {
        return date;
    }


    public void setDate( Date date ) {
        this.date = date;
    }


    public Instruction getInstruction() {
        return instruction;
    }


    public void setInstruction( Instruction instruction ) {
        this.instruction = instruction;
    }

    
    public Vehicule getVehicule() {
        return vehicule;
    }
  
    
	
}
