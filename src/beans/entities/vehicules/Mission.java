package beans.entities.vehicules;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Mission {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id ;
    
    @Size(min = 1,max = 255)
    private String description;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut ;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    
    @NotNull
    @ManyToOne
    private AffectationConducteur affectation;
    
    public Mission() {
        // TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut( Date dateDebut ) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin( Date dateFin ) {
        this.dateFin = dateFin;
    }

    public AffectationConducteur getAffectation() {
        return affectation;
    }

    public void setAffectation( AffectationConducteur affectation ) {
        this.affectation = affectation;
    }

    
   
 
    
}
