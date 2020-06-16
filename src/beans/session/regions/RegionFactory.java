package beans.session.regions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.entities.regions.Region;
import beans.entities.regions.unites.Unite;
import beans.entities.utilisateurs.Utilisateur;

import beans.session.Utilisateur.MethodeUtilisateur;
import beans.session.general.BeanFactory;

public class RegionFactory extends BeanFactory<Region> {

	@Override
	public Region create(HttpServletRequest request) {
		String code = request.getParameter("code");
		String adr = request.getParameter("adr");
		Region reg = new Region(code,adr);
		return reg;
	}
	
	public Region create(HttpServletRequest request, MethodeUtilisateur em) {
		String code = request.getParameter("code");
		String adr = request.getParameter("adr");
		String user = request.getParameter("user");
		Map<String,Object> fields = new HashMap();
		fields.put("type","Regional" );
		fields.put("nomUtilisateur", user);
		Utilisateur u = em.trouver(fields);
		Region reg = new Region(code,adr,u);
		return reg;
	}

	@Override
	public void validateChilds(Region bean) {
		if(bean.getUser() == null) this.addErreurs("user", "Ce nom utilisateur n'existe pas");
		
	}

	@Override
	public void updateChange(Region newB, Region old) {
		old.setAdress(newB.getAdress());
		old.setUser(newB.getUser());
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
