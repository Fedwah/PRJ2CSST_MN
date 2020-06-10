package beans.entities.regions;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
		public Region() {
			
		}
		public Region(String codeReg, String adress) {
			super();
			this.codeReg = codeReg;
			this.adress = adress;
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
		
		
		
}
