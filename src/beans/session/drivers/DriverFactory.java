package beans.session.drivers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import beans.entities.driver.Driver;
import beans.entities.general.Image;
import beans.session.general.BeanFactory;

public class DriverFactory extends BeanFactory<Driver> {

	@Override
	public Driver create(HttpServletRequest request) {
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
		return dr;
	}

	@Override
	public void validateChilds (Driver bean) {
		// TODO Auto-generated method stub
		
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
