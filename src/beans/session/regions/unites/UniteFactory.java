package beans.session.regions.unites;


import javax.servlet.http.HttpServletRequest;

import beans.entities.regions.Region;
import beans.entities.regions.unites.Unite;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;


public class UniteFactory extends BeanFactory<Unite>{
	
	

	public UniteFactory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UniteFactory(Class<Unite> beanClass) {
		super(beanClass);
		// TODO Auto-generated constructor stub
	}

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
	public void validateChilds( Unite bean, BeanManager<Unite> beanM ) {
	    // TODO Auto-generated method stub
	    
	}

	@Override
	public void updateChange(Unite newB, Unite old) {
		old.setAdress(newB.getAdress());
		old.setRegion(newB.getRegion());
		
	}


}
