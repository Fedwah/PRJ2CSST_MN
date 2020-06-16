package beans.entities.utilisateurs;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import beans.entities.regions.Region;
import beans.entities.regions.unites.Unite;

@Entity
public class Utilisateur implements Serializable {
	 @Id
	    @GeneratedValue( strategy = GenerationType.IDENTITY )
	    private int    id;
	    @Column( name = "nom_Utilisateur" )
	    private String    nomUtilisateur;
	    @Column( name = "mot_de_passe" )
	    private String    motDePasse;
	    private String    nom;
	    private String    prenom;
	    private String    type;
	    private String    role;
	    @OneToOne
	    @JoinColumn(nullable=true)
	    private Region region;
	    @OneToOne
	    @JoinColumn(nullable=true)
	    private Unite unite;
	    
		public Utilisateur() {
			super();
			 
		}
	


		public Utilisateur(String nomUtilisateur, String motDePasse, String nom, String prenom, String type,
				String role) {
			super();
			this.nomUtilisateur = nomUtilisateur;
			this.motDePasse = motDePasse;
			this.nom = nom;
			this.prenom = prenom;
			this.type = type;
			this.role = role;
		}


		
		public Utilisateur(int id, String nomUtilisateur, String motDePasse, String nom, String prenom, String type,
				String role, Region region, Unite unite) {
			super();
			this.id = id;
			this.nomUtilisateur = nomUtilisateur;
			this.motDePasse = motDePasse;
			this.nom = nom;
			this.prenom = prenom;
			this.type = type;
			this.role = role;
			this.region = region;
			this.unite = unite;
		}



		public String getType() {
			return type;
		}



		public void setType(String type) {
			this.type = type;
		}



		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getNomUtilisateur() {
			return nomUtilisateur;
		}

		public void setNomUtilisateur(String nomUtilisateur) {
			this.nomUtilisateur = nomUtilisateur;
		}

		public String getMotDePasse() {
			return motDePasse;
		}

		public void setMotDePasse(String motDePasse) {
			this.motDePasse = motDePasse;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		 
		public String getPrenom() {
			return prenom;
		}


		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}


	
		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}



		public Region getRegion() {
			return region;
		}



		public void setRegion(Region region) {
			this.region = region;
		}



		public Unite getUnite() {
			return unite;
		}



		public void setUnite(Unite unite) {
			this.unite = unite;
		}
		
	    

}
