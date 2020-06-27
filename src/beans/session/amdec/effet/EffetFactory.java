package beans.session.amdec.effet;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import beans.entities.amdec.Effet;
import beans.session.general.BeanFactory;
import beans.session.general.BeanManager;

@Stateless
public class EffetFactory extends BeanFactory<Effet>{
	
	

	public EffetFactory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EffetFactory(Class<Effet> beanClass) {
		super(beanClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Effet create(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateChilds(Effet bean, BeanManager<Effet> beanM) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateChange(Effet newB, Effet old) {
		// TODO Auto-generated method stub
		
	}

}
