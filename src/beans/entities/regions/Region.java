package beans.entities.regions;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import beans.entities.regions.unites.Unite;
@Entity
public class Region implements Serializable {

		// ID
		@Id
		@NotEmpty
		@Size(min=3,max=25)
		private String codeReg;
		// adress
		@NotEmpty
		@Size(min=3,max=25)
		private String adress;
		// responsable
		//TODO pas fait encore
		@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,mappedBy = "region")
	    private List<Unite> unites;
		public Region() {
			
		}
		public Region(String codeReg, String adress) {
			super();
			this.codeReg = codeReg;
			this.adress = adress;
		}
		
		public Region(String codeReg) {
			super();
			this.codeReg = codeReg;
		}
		public String getCodeReg() {
			return codeReg;
		}
		public void setCodeReg(String codeReg) {
			this.codeReg = codeReg;
		}
		public String getAdress() {
			return adress;
		}
		public void setAdress(String adress) {
			this.adress = adress;
		}
		public List<Unite> getUnites() {
			return unites;
		}
		public void setUnites(List<Unite> unites) {
			this.unites = unites;
		}
		
		
		
}
