package beans.session.regions;

import javax.servlet.http.HttpServletRequest;

import beans.entities.regions.Region;
import beans.session.general.BeanFactory;

public class RegionFactory extends BeanFactory<Region> {

	@Override
	public Region create(HttpServletRequest request) {
		String code = request.getParameter("code");
		String adr = request.getParameter("adr");
		Region reg = new Region(code,adr);
		return reg;
	}

	@Override
	public void validateChilds(Region bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateChange(Region newB, Region old) {
		old.setAdress(newB.getAdress());		
	}

}
