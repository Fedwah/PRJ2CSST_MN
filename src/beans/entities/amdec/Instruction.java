package beans.entities.amdec;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.json.JSONPropertyIgnore;

import beans.entities.pieces.Piece;
import beans.entities.vehicules.Modele;
import beans.entities.vehicules.Vehicule;

@Entity
public class Instruction implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    @NotNull
    private Modele modele_vehicule;
    
    @ManyToOne
    @NotNull
    private Piece piece;
    
    
    @ManyToOne
    @NotNull
    private Defaillance defaillance;
    
    @ManyToOne
    @NotNull
    private Cause cause;
    
    @ManyToOne
    @NotNull
    private Effet effet;
    
    private int gravite;
    
    private int frequence;
    
    private int niveau_detection; 
    
    
    private String demarche_resolution;
    
    public Instruction() {
        // TODO Auto-generated constructor stub
    }

    
    public Instruction( int id ) {
        super();
        this.id = id;
    }


   
   


    public Instruction( Modele modele, Piece piece, Defaillance defaillance, Cause cause, Effet effet, int gravite,
            int frequence, int niveau_detection, String demarche_resolution ) {
        super();
        this.modele_vehicule = modele;
        this.piece = piece;
        this.defaillance = defaillance;
        this.cause = cause;
        this.effet = effet;
        this.gravite = gravite;
        this.frequence = frequence;
        this.niveau_detection = niveau_detection;
        this.demarche_resolution = demarche_resolution;
    }


    public int getId() {
        return id;
    }


    public void setId( int id ) {
        this.id = id;
    }

   
    public Defaillance getDefaillance() {
        return defaillance;
    }


    public void setDefaillance( Defaillance defaillance ) {
        this.defaillance = defaillance;
    }

   
    public Cause getCause() {
        return cause;
    }

    
    public void setCause( Cause cause ) {
        this.cause = cause;
    }



    public Effet getEffet() {
        return effet;
    }


    public void setEffet( Effet effet ) {
        this.effet = effet;
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


   

    public int getNiveau_detection() {
        return niveau_detection;
    }


    public void setNiveau_detection( int niveau_detection ) {
        this.niveau_detection = niveau_detection;
    }


    public int getCriticite() {
        return gravite * frequence * niveau_detection;
    }


    public String getDemarche_resolution() {
        return demarche_resolution;
    }


    public void setDemarche_resolution( String demarche_resolution ) {
        this.demarche_resolution = demarche_resolution;
    }


    
  
    public Modele getModele_vehicule() {
        return modele_vehicule;
    }
    
    
    public void setModele_vehicule( Modele modele_vehicule ) {
        this.modele_vehicule = modele_vehicule;
    }


  
    public Piece getPiece() {
        return piece;
    }


    public void setPiece( Piece piece ) {
        this.piece = piece;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.id +" "+this.demarche_resolution+" "+this.getCause().getCause()+" "+this.getEffet().getEffet()
                +" "+this.getDefaillance().getDefaillance();
    }
    
    
}
