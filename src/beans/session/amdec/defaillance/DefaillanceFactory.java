package beans.session.amdec.defaillance;

import javax.servlet.http.HttpServletRequest;

import beans.entities.amdec.Defaillance;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

public class DefaillanceFactory extends BeanFactory<Defaillance> {
	
	

	public DefaillanceFactory() {
		super(Defaillance.class);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public Defaillance create(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return new Defaillance( request.getParameter("def"));
	}

	@Override
	public void validateChilds(Defaillance bean, BeanManager<Defaillance> beanM) {
	    uniqueField(beanM, "defaillance", bean.getDefaillance());
	}

	@Override
	public void updateChange(Defaillance newB, Defaillance old) {
		// TODO Auto-generated method stub
		
	}

}
