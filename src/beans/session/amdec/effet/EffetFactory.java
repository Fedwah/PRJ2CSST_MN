package beans.session.amdec.effet;


import javax.servlet.http.HttpServletRequest;

import beans.entities.amdec.Effet;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;


public class EffetFactory extends BeanFactory<Effet>{
	
	

	public EffetFactory() {
		super(Effet.class);
		// TODO Auto-generated constructor stub
	}



	@Override
	public Effet create(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return new Effet( request.getParameter( "eff" ) );
	}

	@Override
	public void validateChilds(Effet bean, BeanManager<Effet> beanM) {
	    uniqueField(beanM, "effet", bean.getEffet());
		
	}

	@Override
	public void updateChange(Effet newB, Effet old) {
		// TODO Auto-generated method stub
		
	}

}
