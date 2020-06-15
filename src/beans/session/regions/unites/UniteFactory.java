package beans.session.regions.unites;


import javax.servlet.http.HttpServletRequest;

import beans.entities.regions.Region;
import beans.entities.regions.unites.Unite;
import beans.session.general.BeanFactory;


public class UniteFactory extends BeanFactory<Unite>{

	@Override
	public Unite create(HttpServletRequest request) {
		String code = request.getParameter("code");
		String adress = request.getParameter("adr");
		String region = request.getParameter("region");
		Region reg = new  Region(region);
		Unite u = new Unite(code,adress,reg);
		return u;
	}

	@Override
	public void validateChilds(Unite bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateChange(Unite newB, Unite old) {
		old.setAdress(newB.getAdress());
		old.setRegion(newB.getRegion());
		
	}


}