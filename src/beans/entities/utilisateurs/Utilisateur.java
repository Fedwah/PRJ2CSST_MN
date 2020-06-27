package beans.entities.utilisateurs;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	    private String   codereg;
        private String codeun;

	    
		public Utilisateur() {
			super();
			 
		}
	

		public Utilisateur(String nomUtilisateur, String motDePasse, String nom, String prenom, String type,
				String role, String codereg, String codeun) {
			super();
			this.nomUtilisateur = nomUtilisateur;
			this.motDePasse = motDePasse;
			this.nom = nom;
			this.prenom = prenom;
			this.type = type;
			this.role = role;
			this.codereg = codereg;
			this.codeun = codeun;
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



		public String getCodereg() {
			return codereg;
		}



		public void setCodereg(String codereg) {
			this.codereg = codereg;
		}



		public String getCodeun() {
			return codeun;
		}



		public void setCodeun(String codeun) {
			this.codeun = codeun;
		}



		 
		
	    

}
