package beans.entities.vehicules;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import beans.entities.general.Image;


@Entity
public class Vehicule implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @NotEmpty
    @Size(min=5,max=20)
    private String num_immatriculation;
    
    
    @ManyToOne
    private Modele modele;
    @NotNull
    @ManyToOne
    private Marque marque;
    
    @NotNull
    @ManyToOne
    private EtatVehicule etat;
    
    
    @Past
    @NotNull
    @Temporal(value=TemporalType.DATE)
    private Date date_achat;  
   
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Image photo;
    
    
    public Vehicule() {
      
    }
    
    

    public Vehicule( String num_immatriculation, Modele modele, Marque marque, EtatVehicule etat, Date date_achat,
            Image photo ) {
        
        this.num_immatriculation = num_immatriculation;
        this.modele = modele;
        this.marque = marque;
        this.etat = etat;
        this.date_achat = date_achat;
        this.photo = photo;
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
        try {
            return new SimpleDateFormat("yyyy-MM-dd" ).format( date_achat );
        }catch(Exception e) {
            return "";
        }
        
    }

    public void setDate_achat( Date date_achat ) {
        this.date_achat = date_achat;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto( Image photo ) {
        this.photo = photo;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModel( Modele model ) {
        this.modele = model;
    }
    
    
    

}
