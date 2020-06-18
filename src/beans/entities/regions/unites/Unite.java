package beans.entities.regions.unites;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import beans.entities.driver.Driver;
import beans.entities.regions.Region;

@Entity
public class Unite implements Serializable {
			// ID
			@Id
			@NotEmpty
			@Size(min=3,max=25)
			private String codeUN;
			
			// adress
			@NotEmpty
			@Size(min=3,max=25)
			private String adress;
			
			// responsable
			//TODO pas fait encore
			// region dont laquelle elle apprtient
			@NotNull
			@ManyToOne
			private Region region;
			
			@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,mappedBy = "unite")
			private List<Driver> drs;
			
			public Unite() {
			
			}
			public Unite(String codeUN, String adress) {
				super();
				this.codeUN = codeUN;
				this.adress = adress;
			}
			
			public Unite(String codeUN, String adress, Region region) {
				super();
				this.codeUN = codeUN;
				this.adress = adress;
				this.region = region;
			}
			
			
			public Unite(String codeUN) {
				super();
				this.codeUN = codeUN;
			}
			//getters and setters
			public String getCodeUN() {
				return codeUN;
			}
			public void setCodeUN(String codeUN) {
				this.codeUN = codeUN;
			}
			public String getAdress() {
				return adress;
			}
			public void setAdress(String adress) {
				this.adress = adress;
			}
			public Region getRegion() {
				return region;
			}
			public void setRegion(Region region) {
				this.region = region;
			}
			public List<Driver> getDrs() {
				return drs;
			}
			public void setDrs(List<Driver> drs) {
				this.drs = drs;
			}
			
			
			
			
			
}
