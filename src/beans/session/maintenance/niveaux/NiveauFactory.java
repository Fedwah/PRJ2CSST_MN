package beans.session.maintenance.niveaux;

import javax.servlet.http.HttpServletRequest;

import beans.entities.maintenance.niveaux.Niveau;
import beans.session.general.BeanFactory;

public class NiveauFactory extends BeanFactory<Niveau> {
	
	

	public NiveauFactory() {

	}

	public NiveauFactory(Class<Niveau> beanClass) {
		super(beanClass);

	}

	@Override
	public Niveau create(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateChilds(Niveau bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateChange(Niveau newB, Niveau old) {
		// TODO Auto-generated method stub
		
	}

}
