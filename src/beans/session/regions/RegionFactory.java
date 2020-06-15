package beans.session.regions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.entities.regions.Region;
import beans.entities.regions.unites.Unite;
import beans.entities.vehicules.Marque;
import beans.entities.vehicules.Modele;
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
	public List<Unite> findinListByCode(Region reg, Unite un)
    {
		List<Unite> searchedUN = new ArrayList();
    	List<Unite> unites = reg.getUnites();
    	for(Unite u : unites) {
            if(u.getCodeUN().contains(un.getCodeUN())) {
            	 searchedUN.add(u);
            }
        }
        return searchedUN;
    	
    }
	public List<Unite> findinListByAdr(Region reg, Unite un)
    {
		List<Unite> searchedUN = new ArrayList();
    	List<Unite> unites = reg.getUnites();
    	for(Unite u : unites) {
            if(u.getAdress().contains(un.getAdress())) {
            	 searchedUN.add(u);
            }
        }
        return searchedUN;
    	
    }

}
