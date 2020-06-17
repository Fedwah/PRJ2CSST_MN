package beans.session.drivers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import beans.entities.driver.Driver;
import beans.entities.general.Image;
import beans.entities.regions.unites.Unite;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanFactory;
import beans.session.regions.unites.UniteManager;

public class DriverFactory extends BeanFactory<Driver> {
	
	 public DriverFactory( Class<Driver> beanClass ) {
	        super( beanClass );
	    }
	 public DriverFactory() {
	       
	    }
	 
	@Override
	public Driver create(HttpServletRequest request) {
		Driver dr = new Driver();
		dr.setFirstN(request.getParameter("prenom"));
		dr.setLastN(request.getParameter("nom"));
		try {
			dr.setRecruitDate( new SimpleDateFormat( "yyyy-MM-dd" ).parse( request.getParameter( "recruit" ) ));
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
		dr.setPhoto(this.readImage(request,"photo"));
		return dr;
	}
	public Driver create(HttpServletRequest request, UniteManager um) {
		Driver dr = new Driver();
		dr.setFirstN(request.getParameter("prenom"));
		dr.setLastN(request.getParameter("nom"));
		try {
			dr.setRecruitDate( new SimpleDateFormat( "yyyy-MM-dd" ).parse( request.getParameter( "recruit" ) ));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dr.setPhoto(this.readImage(request,"photo"));
		Unite un = um.trouver(request.getParameter("un"));
		System.out.println("un est " +un);
		dr.setUnite(un);
		return dr;
	}

	@Override
	public void validateChilds (Driver bean) {
		if(bean.getUnite() == null) this.addErreurs("unite", "Ce code unité n'existe pas");
		
	}

	@Override
	public void updateChange(Driver newB, Driver old) {
		old.setFirstN(newB.getFirstN());
		old.setLastN(newB.getLastN());
		old.setRecruitDate(newB.getRecruitDate());
		if(newB.getPhoto().getTitre() != null)
		{
			old.setPhoto(newB.getPhoto());
		}
			
	}

}
