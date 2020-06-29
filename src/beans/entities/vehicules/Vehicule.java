package beans.entities.vehicules;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


import beans.entities.general.Image;
import beans.entities.regions.unites.Unite;


@Entity
public class Vehicule implements Serializable {
    
  
    private static final long serialVersionUID = 1L;

   
    @Id
    @NotEmpty
    @Size(min=5,max=20)
    private String matricule_interne;
    
    private String matricule_externe;
    
    @NotNull
    @ManyToOne
    private Modele modele;
    
   
    @NotNull
    @ManyToOne
    private Marque marque;
    
    @NotNull
    @ManyToOne
    private EtatVehicule etat;
    
    
    
    @NotNull
    @Temporal(value=TemporalType.DATE)
    private Date date_achat;  
   
   
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private Image photo;
    
    
    @NotNull
    @ManyToOne
    private CategorieVehicule categorie;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Unite unite;
     
    
    @NotNull
    private Double km;
    
    public Vehicule() {
      
    }
    
    
    
    public Vehicule(String matricule_interne) {
		super();
		this.matricule_interne = matricule_interne;
	}





    public Vehicule( String matricule_interne, String matricule_externe, Modele modele, Marque marque,
            EtatVehicule etat, Date date_achat, Image photo, CategorieVehicule categorie, Unite unite ,Double km ) {
        super();
        this.matricule_interne = matricule_interne;
        this.matricule_externe = matricule_externe;
        this.modele = modele;
        this.marque = marque;
        this.etat = etat;
        this.date_achat = date_achat;
        this.photo = photo;
        this.categorie = categorie;
        this.unite = unite;
        this.km = km;
    }


    
    public String getMatricule_interne() {
        return matricule_interne;
    }



    public void setMatricule_interne( String matricule_interne ) {
        this.matricule_interne = matricule_interne;
    }



    public String getMatricule_externe() {
        return matricule_externe;
    }



    public void setMatricule_externe( String matricule_externe ) {
        this.matricule_externe = matricule_externe;
    }



    public Unite getUnite() {
        return unite;
    }



    public void setUnite( Unite unite ) {
        this.unite = unite;
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

    public Date getDate_achat() {
       return this.date_achat;
        
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

    public void setModele( Modele model ) {
        this.modele = model;
    }
    
    
    public CategorieVehicule getCategorie() {
        return categorie;
    }
    
    public void setCategorie( CategorieVehicule categorie ) {
        this.categorie = categorie;
    }



    public Double getKm() {
        return km;
    }



    public void setKm( Double km ) {
        this.km = km;
    }

    
    public String getMatricule() {
        return this.matricule_interne+" / "+this.matricule_externe;
    }

}
