package beans.session.maintenance;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import beans.entities.maintenance.Maintenance;
import beans.entities.maintenance.niveaux.Niveau;
import beans.entities.pieces.Piece;
import beans.entities.vehicules.Vehicule;
import beans.session.general.BeanFactory;
import beans.session.vehicules.VehiculesManager;

public class MaintenanceFactory extends BeanFactory<Maintenance> {
	
	

	public MaintenanceFactory() {

	}

	public MaintenanceFactory(Class<Maintenance> beanClass) {
		super(beanClass);

	}

	@Override
	public Maintenance create(HttpServletRequest request) {
		return null;
	}
	
	public Maintenance create(HttpServletRequest request, VehiculesManager vm) {
		Maintenance m = new Maintenance();
		Vehicule v = vm.trouver(request.getParameter("matricule"));
		m.setV(v);
		m.setUn(v.getUnite());
		Piece p = new Piece(request.getParameter("piece"));
		m.setP(p);
		Niveau n = new Niveau(Integer.parseInt(request.getParameter("niv")));
		try {
			m.setStartDate( new SimpleDateFormat( "yyyy-MM-dd" ).parse( request.getParameter( "recruit" ) ));
		} catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return m ; 
	}

	@Override
	public void validateChilds(Maintenance bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateChange(Maintenance newB, Maintenance old) {
		// TODO Auto-generated method stub
		
	}

}
