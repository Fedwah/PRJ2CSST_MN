package beans.entities.driver;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import beans.entities.general.Image;
import beans.entities.regions.unites.Unite;

@Entity
public class Driver implements Serializable{
	// ID
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int IDdriver;
	// prenom
	@NotEmpty
	@Size(min=3,max=25)
	private String firstN;
	// nom
	@NotEmpty
	@Size(min=3,max=25)
	private String lastN;
	// date de recrutement
	@PastOrPresent
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date recruitDate ;
	// unite
	@NotNull
	@ManyToOne
	private Unite unite;
	// image
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private Image photo;
	
	// constructor without ID
	public Driver(String firstN, String lastN, Date recruitDate, Image photo) {
		super();
		this.firstN = firstN;
		this.lastN = lastN;
		this.recruitDate = recruitDate;
		this.photo = photo;
	}
	// Default constructor
	public Driver() {
		
	}
	
	// getters and setters
	public int getIDdriver() {
		return IDdriver;
	}
	public void setIDdriver(int iDdriver) {
		IDdriver = iDdriver;
	}
	public String getFirstN() {
		return firstN;
	}
	public void setFirstN(String firstN) {
		this.firstN = firstN;
	}
	public String getLastN() {
		return lastN;
	}
	public void setLastN(String lastN) {
		this.lastN = lastN;
	}
	public Date getRecruitDate() {
		return recruitDate;
	}
	public void setRecruitDate(Date recruitDate) {
		this.recruitDate = recruitDate;
	}
	public Image getPhoto() {
		return photo;
	}
	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	public Unite getUnite() {
		return unite;
	}
	public void setUnite(Unite unite) {
		this.unite = unite;
	}
	

}
