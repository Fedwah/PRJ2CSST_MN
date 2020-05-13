package beans.vehicules;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Vehicule implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @NotEmpty
    @Size(min=5,max=20)
    private String num_immatriculation;
    
    @ManyToOne(targetEntity = Model.class)
    private Model model;
    @ManyToOne(targetEntity = Marque.class)
    private Marque marque;
    
    @ManyToOne(targetEntity = EtatVehicule.class)
    private EtatVehicule etat;
    
    @Temporal(value=TemporalType.DATE)
    private Date date_achat;  
    
    @Lob
    private byte[] photo;
    
    
    public Vehicule() {
      
    }

    public String getNum_immatriculation() {
        return num_immatriculation;
    }

    public void setNum_immatriculation( String num_immatriculation ) {
        this.num_immatriculation = num_immatriculation;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque( Marque marque ) {
        this.marque = marque;
    }

    public EtatVehicule getEtat() {
        return etat;
    }

    public void setEtat( EtatVehicule etat ) {
        this.etat = etat;
    }

    public String getDate_achat() {
        return new SimpleDateFormat("yyyy-MM-dd" ).format( date_achat );
    }

    public void setDate_achat( Date date_achat ) {
        this.date_achat = date_achat;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto( byte[] photo ) {
        this.photo = photo;
    }

    public Model getModel() {
        return model;
    }

    public void setModel( Model model ) {
        this.model = model;
    }
    
    
    
}
