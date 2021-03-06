package beans.session.amdec.cause;

import javax.servlet.http.HttpServletRequest;

import beans.entities.amdec.Cause;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

public class CauseFactory extends BeanFactory<Cause> {
	
	

	public CauseFactory() {
		super(Cause.class);
	
	}

	

	@Override
	public Cause create(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return new Cause( request.getParameter("cause"));
	}

	@Override
	public void validateChilds(Cause bean, BeanManager<Cause> beanM) {
		// TODO Auto-generated method stub
	    this.uniqueField(beanM, "cause", bean.getCause());
	}

	@Override
	public void updateChange(Cause newB, Cause old) {
		// TODO Auto-generated method stub
		
	}

}
