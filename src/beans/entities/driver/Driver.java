package beans.entities.driver;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;

import beans.entities.general.Image;

@Entity
public class Driver implements Serializable{
	// ID
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int IDdriver;
	// prenom
	@NotEmpty
	private String firstN;
	// nom
	@NotEmpty
	private String lastN;
	// date de recrutement
	@PastOrPresent
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date recruitDate ;
	// unité
	//TODO pas faites
	// image
	@NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Image photo;
	
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
	
	
	
	

}
